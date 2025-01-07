package xyz.violaflower.legacy_tweaks.tweaks;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TweakState<T> {
	public static final HashMap<String, TweakState<?>> tweakStates = new HashMap<>();
	private final Codec<T> codec;
	private T serverState = null;
	private T localState = null;
	private T defaultState = null;
	private T effectiveState;
	private final StreamCodec<ByteBuf, T> streamCodec;
	private Consumer<TweakState<T>> onChange;
	private final String id;

	public TweakState(String id, T defaultState, Codec<T> codec, Consumer<TweakState<T>> onChange) {
		this(id, defaultState, codec, ByteBufCodecs.fromCodec(codec), onChange);
	}

	public TweakState(String id, T defaultState, Codec<T> codec, StreamCodec<ByteBuf, T> streamCodec, Consumer<TweakState<T>> onChange) {
		this.defaultState = defaultState;
		this.effectiveState = defaultState;
		this.codec = codec;
		this.streamCodec = streamCodec;
		this.onChange = onChange;
		this.id = id;
		tweakStates.put(this.id, this);
	}
	/**
	 * @return The effective state of a tweak/option accounting for server overrides.
	 */
	public @NotNull T getEffectiveState() {
		if (effectiveState == null) updateEffectiveState();
		return effectiveState;
	}

	private void updateEffectiveState() {
		this.updateEffectiveState(true);
	}

	private void updateEffectiveState(boolean triggerHooks) {
		T previousValue = this.effectiveState;
		T tempVar;
		if ((tempVar = getServerState()) != null) {
			effectiveState = tempVar;
		} else if ((tempVar = getLocalState()) != null) {
			effectiveState = tempVar;
		} else {
			effectiveState = getDefaultState();
		}
		if (!this.effectiveState.equals(previousValue)) {
			onChange.accept(this);
		}
	}

	/**
	 * @return The tweak's state that is saved locally. On the server this is the state stored there, same for the client.
	 */
	public @Nullable T getLocalState() {
		return localState;
	}

	public void setLocalState(@Nullable T state) {
		this.setLocalState(state, true);
	}

	public void setLocalState(@Nullable T state, boolean triggerHooks) {
		this.localState = state;
		updateEffectiveState(triggerHooks);
		TweakManager.save();
	}

	public void setServerState(@Nullable T state) {
		this.serverState = state;
		updateEffectiveState(true);
	}

	// null on the server, this is only used on the client.
	public @Nullable T getServerState() {
		return serverState;
	}

	public @NotNull T getDefaultState() {
		return defaultState;
	}

	public String getId() {
		return id;
	}

	// this should only run on the dedicated or integrated server!
	public void encodeLocalState(FriendlyByteBuf byteBuf) {
		if (this.getLocalState() == null) throw new IllegalStateException(); // no need to encode if it's null
		T localState1 = getLocalState();
		byteBuf.writeUtf(getId(), 32767);
		streamCodec.encode(byteBuf, localState1);
	}

	public static FriendlyByteBuf encodeStates() {
		FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
		List<TweakState<?>> list = tweakStates.values().stream().filter(a -> a.getLocalState() != null).toList();
		buf.writeInt(list.size());
		for (TweakState<?> value : list) {
			value.encodeLocalState(buf);
		}
		return buf;
	}

	public static <T> void decodeStatesFromAnActuallyReadableFormat(JsonElement element) {
		if (!(element instanceof JsonObject object)) throw new RuntimeException("what");
		//ArrayList<TweakState<T>> list = (ArrayList<TweakState<T>>) (Object) new ArrayList<>(tweakStates.values());
		for (Map.Entry<String, JsonElement> tweakEntry : object.entrySet()) {
			decodeTweak(TweakManager.getInstance().getTweak(tweakEntry.getKey()), tweakEntry.getValue());
		}
	}

	public static <T> void decodeServerStates(FriendlyByteBuf byteBuf) {
		System.out.println(tweakStates);
		ArrayList<TweakState<?>> list = new ArrayList<>(tweakStates.values());
		int length = byteBuf.readInt();
		for (int i = 0; i < length; i++) {
			String id = byteBuf.readUtf();
			TweakState<T> tweakState = (TweakState<T>) (Object) tweakStates.get(id);
			T decoded = tweakState.streamCodec.decode(byteBuf);
			tweakState.setServerState(decoded);
			System.out.println("Set " + tweakState.id + " to " + decoded);
			list.remove(tweakState);
		}
		for (TweakState<?> tweakState : list) {
			tweakState.setServerState(null);
		}
	}

	public static <T> JsonElement encodeStatesToAnActuallyReadableFormat() {
		JsonObject element = new JsonObject();
		for (Map.Entry<String, Tweak> tweakEntry : TweakManager.INSTANCE.tweaks.entrySet()) {
			String key = tweakEntry.getKey();
			Tweak value = tweakEntry.getValue();
			element.add(key, encodeTweak(value));
		}
		return element;
	}

	public JsonElement valueToJsonElement(T value) {
		return codec.encodeStart(JsonOps.INSTANCE, value).resultOrPartial(System.err::println).orElseThrow();
	}

	public T jsonElementToValue(JsonElement element) {
		return codec.parse(JsonOps.INSTANCE, element).resultOrPartial(System.err::println).orElseThrow();
	}

	private static <T> JsonElement encodeTweak(Tweak tweak) {
		JsonObject object = new JsonObject();
		@SuppressWarnings("deprecation") TweakState<Boolean> tweakState = tweak.getTweakState();
		if (tweakState.getLocalState() != null) {
			object.addProperty("enabled", tweakState.getLocalState());
		}
		for (Map.Entry<String, Tweak> tweakEntry : tweak.getSubTweaks().entrySet()) {
			object.add(tweakEntry.getKey(), encodeTweak(tweakEntry.getValue()));
		}
		options: {
			@SuppressWarnings("unchecked") List<Tweak.Option<T>> options = (List<Tweak.Option<T>>) (Object) tweak.getOptions();
			options = options.stream().filter(a -> a.getTweakState().getLocalState() != null).toList();
			if (options.isEmpty()) break options;
			JsonObject optionsObject = new JsonObject();
			for (Tweak.Option<T> option : options) {
				@SuppressWarnings("deprecation") TweakState<T> optionTweakState = option.getTweakState();
				if (optionTweakState.getLocalState() != null) {
					optionsObject.add(option.getName(), optionTweakState.valueToJsonElement(optionTweakState.getLocalState()));
				}
			}
			object.add("options", optionsObject);
		}
		return object;
	}

	private static <T> void decodeTweak(Tweak tweak, JsonElement element) {
		if (!(element instanceof JsonObject object)) throw new IllegalStateException("???");
		options: {
			boolean hasOptions = object.has("options") && object.get("options").isJsonObject();
			if (!hasOptions) break options;
			JsonObject options = object.getAsJsonObject("options");
			for (Map.Entry<String, JsonElement> optionEntry : options.entrySet()) {
				Tweak.Option<T> option = (Tweak.Option<T>) (Object) tweak.getOptions().stream().filter(a -> optionEntry.getKey().equals(a.getName())).findFirst().orElseThrow();
				option.getTweakState().setLocalState(option.getTweakState().jsonElementToValue(optionEntry.getValue()));
			}
		}
		enabled: {
			boolean hasEnabled = object.has("enabled") && object.get("enabled").isJsonPrimitive() && object.getAsJsonPrimitive("enabled").isBoolean();
			if (!hasEnabled) break enabled;
			tweak.getTweakState().setLocalState(tweak.getTweakState().jsonElementToValue(object.get("enabled")));
		}
		options:
		{
			for (Map.Entry<String, JsonElement> stringJsonElementEntry : object.entrySet()) {
				if (stringJsonElementEntry.getKey().equals("options") || stringJsonElementEntry.getKey().equals("enabled"))
					continue;
				String key = stringJsonElementEntry.getKey();
				decodeTweak(tweak.getSubTweak(key), stringJsonElementEntry.getValue());
			}
		}
	}
}

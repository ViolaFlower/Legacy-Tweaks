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

	public static <T> void decodeLocalStates(JsonElement element) {
		if (!(element instanceof JsonObject object)) throw new RuntimeException("what");
		ArrayList<TweakState<T>> list = (ArrayList<TweakState<T>>) (Object) new ArrayList<>(tweakStates.values());
		for (Map.Entry<String, JsonElement> entry : object.asMap().entrySet()) {
			TweakState<T> tweakState = (TweakState<T>) (Object) tweakStates.get(entry.getKey());
			tweakState.setLocalState(tweakState.codec.parse(JsonOps.INSTANCE, entry.getValue()).getOrThrow());
			list.remove(tweakState);
		}
		for (TweakState<?> tweakState : list) {
			tweakState.setLocalState(null);
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
		FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
		List<TweakState<T>> list = (List<TweakState<T>>) (Object) tweakStates.values().stream().filter(a -> a.getLocalState() != null).toList();

		for (TweakState<T> value : list) {
			Codec<T> codec = value.codec;
			JsonElement jsonElement = codec.encodeStart(JsonOps.INSTANCE, value.getLocalState()).resultOrPartial(System.err::println).orElseThrow();
			element.add(value.getId(), jsonElement);
		}
		return element;
	}
}

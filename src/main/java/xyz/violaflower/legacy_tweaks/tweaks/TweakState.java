package xyz.violaflower.legacy_tweaks.tweaks;

import com.mojang.serialization.Codec;
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

	public static <T> void decodeLocalStates(FriendlyByteBuf byteBuf) {
		// Dexrn: is there some way to have the server kick the client/do something else if they send the wrong version?
		System.out.println(tweakStates);
		ArrayList<TweakState<?>> list = new ArrayList<>(tweakStates.values());
		// Dexrn: Maybe should be a short? doubt we'll ever have more than 32727 tweaks
		int length = byteBuf.readInt();
		for (int i = 0; i < length; i++) {
			String id = byteBuf.readUtf();
			TweakState<T> tweakState = (TweakState<T>) (Object) tweakStates.get(id);
			T decoded = tweakState.streamCodec.decode(byteBuf);
			tweakState.setLocalState(decoded);
			System.out.println("Set " + tweakState.id + " to " + decoded);
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
}

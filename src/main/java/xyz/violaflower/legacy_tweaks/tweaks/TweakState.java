package xyz.violaflower.legacy_tweaks.tweaks;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class TweakState<T> {
	private final Codec<T> codec;
	private T serverState = null;
	private T localState = null;
	private T defaultState = null;
	private T effectiveState;
	private final StreamCodec<ByteBuf, T> streamCodec;
	private Consumer<TweakState<T>> onChange;

	public TweakState(T defaultState, Codec<T> codec, Consumer<TweakState<T>> onChange) {
		this(defaultState, codec, ByteBufCodecs.fromCodec(codec), onChange);
	}

	public TweakState(T defaultState, Codec<T> codec, StreamCodec<ByteBuf, T> streamCodec, Consumer<TweakState<T>> onChange) {
		this.defaultState = defaultState;
		this.effectiveState = defaultState;
		this.codec = codec;
		this.streamCodec = streamCodec;
		this.onChange = onChange;
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
		if (!this.effectiveState.equals(previousValue)) onChange.accept(this);
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
	}

	// null on the server, this is only used on the client.
	public @Nullable T getServerState() {
		return localState;
	}

	public @NotNull T getDefaultState() {
		return defaultState;
	}
}

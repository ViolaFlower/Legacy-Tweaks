package xyz.violaflower.legacy_tweaks.network.payload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import xyz.violaflower.legacy_tweaks.util.ModAsset;

public record TweakStatesResponsePayload() implements CustomPacketPayload {
	public static final Type<TweakStatesResponsePayload> TYPE = new Type<>(ModAsset.getResourceLocation("tweak_states_response_payload"));
	public static final StreamCodec<FriendlyByteBuf, TweakStatesResponsePayload> STREAM_CODEC = StreamCodec.unit(new TweakStatesResponsePayload());

	@Override
	public Type<TweakStatesResponsePayload> type() {
		return TYPE;
	}
}

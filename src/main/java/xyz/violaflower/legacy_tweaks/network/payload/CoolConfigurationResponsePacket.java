package xyz.violaflower.legacy_tweaks.network.payload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import xyz.violaflower.legacy_tweaks.util.common.assets.ModAsset;

public record CoolConfigurationResponsePacket(int integer) implements CustomPacketPayload {
	public static final Type<CoolConfigurationResponsePacket> TYPE = new Type<>(ModAsset.getResourceLocation("cool_configuration_response_packet"));
	public static final StreamCodec<FriendlyByteBuf, CoolConfigurationResponsePacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, CoolConfigurationResponsePacket::integer, CoolConfigurationResponsePacket::new);

	@Override
	public Type<CoolConfigurationResponsePacket> type() {
		return TYPE;
	}
}

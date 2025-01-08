package xyz.violaflower.legacy_tweaks.network.payload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import xyz.violaflower.legacy_tweaks.util.common.assets.ModAsset;

public record CoolConfigurationPacket(int integer) implements CustomPacketPayload {
	public static final Type<CoolConfigurationPacket> TYPE = new CustomPacketPayload.Type<>(ModAsset.getResourceLocation("cool_configuration_packet"));
	public static final StreamCodec<FriendlyByteBuf, CoolConfigurationPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, CoolConfigurationPacket::integer, CoolConfigurationPacket::new);

	@Override
	public Type<CoolConfigurationPacket> type() {
		return TYPE;
	}
}

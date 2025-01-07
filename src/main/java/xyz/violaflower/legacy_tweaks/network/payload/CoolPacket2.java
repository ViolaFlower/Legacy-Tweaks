package xyz.violaflower.legacy_tweaks.network.payload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

public record CoolPacket2(int number, double number2) implements CustomPacketPayload {
	public static final Type<CoolPacket2> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "cool_packet2"));
	public static final StreamCodec<FriendlyByteBuf, CoolPacket2> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, CoolPacket2::number, ByteBufCodecs.DOUBLE, CoolPacket2::number2, CoolPacket2::new);

	@Override
	public Type<CoolPacket2> type() {
		return TYPE;
	}
}
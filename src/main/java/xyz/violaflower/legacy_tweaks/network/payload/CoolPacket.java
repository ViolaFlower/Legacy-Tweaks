package xyz.violaflower.legacy_tweaks.network.payload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

public record CoolPacket(int number) implements CustomPacketPayload {
	public static final Type<CoolPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "cool_packet"));
	public static final StreamCodec<FriendlyByteBuf, CoolPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, CoolPacket::number, CoolPacket::new);

	@Override
	public Type<CoolPacket> type() {
		return TYPE;
	}
}
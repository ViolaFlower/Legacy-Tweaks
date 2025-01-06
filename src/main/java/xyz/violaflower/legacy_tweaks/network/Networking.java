package xyz.violaflower.legacy_tweaks.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;
import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket;
import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket2;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;

public class Networking {
	private static void registerNetworkingCodecs() {
		NetworkingAbstractions.registerCodec(CoolPacket.TYPE, CoolPacket.STREAM_CODEC, NetworkingAbstractions.PayloadType.PLAY_S2C);
		NetworkingAbstractions.registerCodec(CoolPacket2.TYPE, CoolPacket2.STREAM_CODEC, NetworkingAbstractions.PayloadType.PLAY_C2S);
	}
	public static void initNetworking() {
		registerNetworkingCodecs();
		NetworkingAbstractions.Server.playToServer(CoolPacket2.TYPE, (payload, context) -> {
			System.out.println("[SERVER] received packet " + payload);
		});
	}
}

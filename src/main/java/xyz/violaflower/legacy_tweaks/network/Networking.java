package xyz.violaflower.legacy_tweaks.network;

import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket;
import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket2;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;
import xyz.violaflower.legacy_tweaks.networking.play.ServerboundPlay;

public class Networking {
	private static void registerNetworkingCodecs() {
		NetworkingAbstractions.registerCodec(CoolPacket.TYPE, CoolPacket.STREAM_CODEC, NetworkingAbstractions.PayloadType.PLAY_S2C);
		NetworkingAbstractions.registerCodec(CoolPacket2.TYPE, CoolPacket2.STREAM_CODEC, NetworkingAbstractions.PayloadType.PLAY_C2S);
	}
	public static void initNetworking() {
		registerNetworkingCodecs();
		ServerboundPlay.registerHandler(CoolPacket2.TYPE, (payload, context) -> {
			System.out.println("[SERVER] received packet " + payload);
		});
	}
}

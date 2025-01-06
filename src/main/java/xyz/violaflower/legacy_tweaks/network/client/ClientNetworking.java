package xyz.violaflower.legacy_tweaks.network.client;

import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;

public class ClientNetworking {
	public static void initNetworking() {
		NetworkingAbstractions.Client.playToClient(CoolPacket.TYPE, (handler, context) -> {
			System.out.println("RECEIVED!: " + handler.number() + " " + context.player());
		});
	}
}

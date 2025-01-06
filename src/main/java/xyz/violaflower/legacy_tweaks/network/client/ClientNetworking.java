package xyz.violaflower.legacy_tweaks.network.client;

import xyz.violaflower.legacy_tweaks.network.Networking;
import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket;
import xyz.violaflower.legacy_tweaks.networking.configuration.ClientboundConfiguration;
import xyz.violaflower.legacy_tweaks.networking.play.ClientboundPlay;

public class ClientNetworking {
	public static void registerPayloadHandlers() {
		ClientboundPlay.registerHandler(CoolPacket.TYPE, (handler, context) -> {
			System.out.println("RECEIVED!: " + handler.number() + " " + context.player());
		});
	}

	/// @see Networking#registerConfigurationTasks()
	public static void registerConfigurationTasks() {

	}
}

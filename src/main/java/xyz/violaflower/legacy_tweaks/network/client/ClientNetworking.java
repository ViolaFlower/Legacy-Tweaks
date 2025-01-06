package xyz.violaflower.legacy_tweaks.network.client;

import xyz.violaflower.legacy_tweaks.network.Networking;
import xyz.violaflower.legacy_tweaks.network.payload.CoolConfigurationPacket;
import xyz.violaflower.legacy_tweaks.network.payload.CoolConfigurationResponsePacket;
import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket;
import xyz.violaflower.legacy_tweaks.networking.configuration.ClientboundConfiguration;
import xyz.violaflower.legacy_tweaks.networking.play.ClientboundPlay;

public class ClientNetworking {
	public static void registerPayloadHandlers() {
		ClientboundPlay.registerHandler(CoolPacket.TYPE, (payload, context) -> {
			System.out.println("RECEIVED!: " + payload.number() + " " + context.player());
		});

		ClientboundConfiguration.registerHandler(CoolConfigurationPacket.TYPE, (payload, context) -> {
			System.out.println("Received during configuration: " + payload);
			CoolConfigurationResponsePacket responsePacket = new CoolConfigurationResponsePacket(13371337);
			System.out.println("Responding to server with " + responsePacket);
			context.sendPacket(responsePacket);
		});
	}

	/// Note that configuration tasks for the client aren't finished.
	/// @see Networking#registerConfigurationTasks()
	public static void registerConfigurationTasks() {

	}
}

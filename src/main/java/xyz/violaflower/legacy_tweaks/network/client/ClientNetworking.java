package xyz.violaflower.legacy_tweaks.network.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.network.Networking;
import xyz.violaflower.legacy_tweaks.network.payload.*;
import xyz.violaflower.legacy_tweaks.networking.configuration.ClientboundConfiguration;
import xyz.violaflower.legacy_tweaks.networking.play.ClientboundPlay;
import xyz.violaflower.legacy_tweaks.tweaks.TweakState;

public class ClientNetworking {
	public static void registerPayloadHandlers() {
		ClientboundPlay.registerHandler(CoolPacket.TYPE, (payload, context) -> {
			System.out.println("RECEIVED!: " + payload.number() + " " + context.player());
		});

		ClientboundConfiguration.registerHandler(CoolConfigurationPacket.TYPE, (payload, context) -> {
			Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Received " + payload + " from the server while negotiating."));
			CoolConfigurationResponsePacket responsePacket = new CoolConfigurationResponsePacket(13371337);
			Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Responding with " + responsePacket));
			context.sendPacket(responsePacket);
		});

		ClientboundConfiguration.registerHandler(TweakStatesPayload.TYPE, (payload, context) -> {
			Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Received server configuration"));
			TweakState.decodeServerStates(payload.byteBuf());
			context.sendPacket(new TweakStatesResponsePayload());
		});
	}

	/// Note that configuration tasks for the client aren't finished.
	/// @see Networking#registerConfigurationTasks()
	public static void registerConfigurationTasks() {

	}
}

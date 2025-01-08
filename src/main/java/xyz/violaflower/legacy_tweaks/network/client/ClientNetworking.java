package xyz.violaflower.legacy_tweaks.network.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.network.Networking;
import xyz.violaflower.legacy_tweaks.network.payload.*;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;
import xyz.violaflower.legacy_tweaks.networking.configuration.ClientboundConfiguration;
import xyz.violaflower.legacy_tweaks.networking.play.ClientboundPlay;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.TweakState;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

public class ClientNetworking {
	/// @see Networking#registerPayloadHandlers()
	public static void registerPayloadHandlers() {
		ClientboundPlay.registerHandler(CoolPacket.TYPE, (payload, context) -> {
			System.out.println("RECEIVED!: " + payload.number() + " " + context.player());
		});

		ClientboundConfiguration.registerHandler(CoolConfigurationPacket.TYPE, (payload, context) -> {
			Minecraft.getInstance().gui.getChat().addMessage(Component.literal(Lang.Networking.RECEIVED.getString() + payload + Lang.Networking.FROM_SERVER.getString()));
			CoolConfigurationResponsePacket responsePacket = new CoolConfigurationResponsePacket(13371337);
			Minecraft.getInstance().gui.getChat().addMessage(Component.literal(Lang.Networking.RESPONDING.getString() + responsePacket));
			context.sendPacket(responsePacket);
		});

		ClientboundConfiguration.registerHandler(TweakStatesPayload.TYPE, ClientNetworking::handleTweakStates);
		ClientboundPlay.registerHandler(TweakStatesPayload.TYPE, ClientNetworking::handleTweakStates);
	}

	/// Note that configuration tasks for the client aren't finished.
	/// @see Networking#registerConfigurationTasks()
	public static void registerConfigurationTasks() {

	}

	private static void handleTweakStates(TweakStatesPayload payload, NetworkingAbstractions.Context context) {
		Minecraft.getInstance().gui.getChat().addMessage(Lang.Networking.RECEIVED_CONFIG.get());
		int configVersion = payload.configVersion();
		if (TweakManager.version != configVersion) {
			context.disconnect(Component.literal(Lang.Networking.SERVER_CONFIG_VER.getString() + payload.configVersion() + Lang.Networking.PLAYER_CONFIG_VER.getString() + TweakManager.version));
		}
		TweakState.decodeServerStates(payload.byteBuf());
		context.sendPacket(new TweakStatesResponsePayload());
	}
}

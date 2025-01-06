package xyz.violaflower.legacy_tweaks.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.network.ConfigurationTask;
import xyz.violaflower.legacy_tweaks.network.client.ClientNetworking;
import xyz.violaflower.legacy_tweaks.network.payload.CoolConfigurationPacket;
import xyz.violaflower.legacy_tweaks.network.payload.CoolConfigurationResponsePacket;
import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket;
import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket2;
import xyz.violaflower.legacy_tweaks.network.task.SendCoolConfigurationPacketTask;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;
import xyz.violaflower.legacy_tweaks.networking.configuration.ServerboundConfiguration;
import xyz.violaflower.legacy_tweaks.networking.configuration.event.ServerConfigurationEvents;
import xyz.violaflower.legacy_tweaks.networking.play.ServerboundPlay;

public class Networking {
	public static void registerNetworkingCodecs() {
		NetworkingAbstractions.registerCodec(CoolPacket.TYPE, CoolPacket.STREAM_CODEC, NetworkingAbstractions.PayloadType.PLAY_S2C);
		NetworkingAbstractions.registerCodec(CoolPacket2.TYPE, CoolPacket2.STREAM_CODEC, NetworkingAbstractions.PayloadType.PLAY_C2S);
		NetworkingAbstractions.registerCodec(CoolConfigurationPacket.TYPE, CoolConfigurationPacket.STREAM_CODEC, NetworkingAbstractions.PayloadType.CONFIGURATION_S2C);
		NetworkingAbstractions.registerCodec(CoolConfigurationResponsePacket.TYPE, CoolConfigurationResponsePacket.STREAM_CODEC, NetworkingAbstractions.PayloadType.CONFIGURATION_C2S);
	}

	public static void registerPayloadHandlers() {
		ServerboundPlay.registerHandler(CoolPacket2.TYPE, (payload, context) -> {
			System.out.println("[SERVER] received packet " + payload);
		});
		ServerboundConfiguration.registerHandler(CoolConfigurationResponsePacket.TYPE, (payload, context) -> {
			System.out.println("Client responded with " + payload + "!");
			context.finishCurrentTask(SendCoolConfigurationPacketTask.TYPE);
		});
	}

	/// @see ClientNetworking#registerConfigurationTasks()
	public static void registerConfigurationTasks() {
		ServerConfigurationEvents.registerOnConfigure(h -> {
			h.addTask(new SendCoolConfigurationPacketTask());
		});
	}
}

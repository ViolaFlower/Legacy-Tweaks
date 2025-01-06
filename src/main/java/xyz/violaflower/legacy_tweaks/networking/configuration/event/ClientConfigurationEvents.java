package xyz.violaflower.legacy_tweaks.networking.configuration.event;

//? if fabric {
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationConnectionEvents;
//?}
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;

public class ClientConfigurationEvents {
	public static void registerOnStart(Start start) {
		//? if fabric {
		ClientConfigurationConnectionEvents.START.register(start::onConfigurationStart);
		//?}
		// TODO we need a simple event system for NeoForge.
	}

	public interface Start {
		void onConfigurationStart(ClientConfigurationPacketListenerImpl handler, Minecraft client);
	}
}

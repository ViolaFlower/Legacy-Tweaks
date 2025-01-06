package xyz.violaflower.legacy_tweaks.networking.configuration.event;

//? if fabric {
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationConnectionEvents;
//?} elif neoforge {
/*import xyz.violaflower.legacy_tweaks.networking.event.InternalClientConfigurationEvents;
*///?}
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;

public class ClientConfigurationEvents {
	/// An event called after the connection has been initialized and is ready to start sending and receiving configuration packets.
	///
	/// Packets may be sent during this event.
	public static void registerOnStart(Start start) {
		//? if fabric {
		ClientConfigurationConnectionEvents.START.register(start::onConfigurationStart);
		//?} elif neoforge {
		/*InternalClientConfigurationEvents.START.register(start);
		*///?}
	}

	public interface Start {
		void onConfigurationStart(ClientConfigurationPacketListenerImpl handler, Minecraft client);
	}
}

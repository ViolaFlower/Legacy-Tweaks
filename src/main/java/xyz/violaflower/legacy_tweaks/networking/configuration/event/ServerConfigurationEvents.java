package xyz.violaflower.legacy_tweaks.networking.configuration.event;

//? if fabric {
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents;
//?} elif neoforge {
/*import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;
*///?}
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.network.ConfigurationTask;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

public class ServerConfigurationEvents {
	public static void registerOnConfigure(ConfigurationTask configurationTask) {
		//? if fabric {
		ServerConfigurationConnectionEvents.CONFIGURE.register((handler, server) -> handler.addTask(configurationTask));
		//?} elif neoforge {
		/*NetworkingAbstractions.configurationTasksEvent.register(configurationTask);
		*///?}
	}

	public interface CustomPayloadConfigurationTask extends ConfigurationTask {
		void run(Consumer<CustomPacketPayload> sender);

		@Override
		@ApiStatus.Internal
		@ApiStatus.NonExtendable
		default void start(Consumer<Packet<?>> sender) {
			run((payload) -> sender.accept(new ClientboundCustomPayloadPacket(payload)));
		}
	}
}

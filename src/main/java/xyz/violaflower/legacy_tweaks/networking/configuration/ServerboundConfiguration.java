package xyz.violaflower.legacy_tweaks.networking.configuration;

//? if fabric {
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
//?} elif neoforge {
/*import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
*///?}
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.network.ConfigurationTask;
import net.minecraft.world.entity.player.Player;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;


public class ServerboundConfiguration {
	/**
	 * Registers a handler for a payload type.
	 * A global receiver is registered to all connections, in the present and future.
	 * @param type the payload type
	 * @param handler the handler
	 */
	public static <T extends CustomPacketPayload> void registerHandler(CustomPacketPayload.Type<T> type, ConfigurationPayloadHandler<T> handler) {
		//? if fabric {
		ServerConfigurationNetworking.registerGlobalReceiver(type, (payload, context) -> {
			handler.handle(payload, new ServerContext() {
				@Override
				public void finishCurrentTask(ConfigurationTask.Type type) {
					context.networkHandler().completeTask(type);
				}
				
				@Override
				public Player player() {
					throw new UnsupportedOperationException();
				}

				@Override
				public void sendPacket(Packet<?> packet) {
					context.responseSender().sendPacket(packet);
				}

				@Override
				public void sendPacket(CustomPacketPayload payload) {
					context.responseSender().sendPacket(payload);
				}

				@Override
				public Packet<?> createPacket(CustomPacketPayload payload) {
					return context.responseSender().createPacket(payload);
				}

				@Override
				public void disconnect(Component message) {
					context.responseSender().disconnect(message);
				}
			});
		});
		//?} elif neoforge {
		/*NetworkingAbstractions.event.registrar(NetworkingAbstractions.version).configurationToServer(type, (StreamCodec<? super FriendlyByteBuf, T>) (Object) NetworkingAbstractions.CODEC_MAP.get(type), (payload, context) -> {
			handler.handle(payload, new ServerContext() {
				@Override
				public void finishCurrentTask(ConfigurationTask.Type type) {
					context.finishCurrentTask(type);
				}

				@Override
				public Player player() {
					throw new UnsupportedOperationException();
				}

				@Override
				public void sendPacket(Packet<?> packet) {
					context.listener().send(packet);
				}

				@Override
				public void sendPacket(CustomPacketPayload payload) {
					context.listener().send(payload);
				}

				@Override
				public Packet<?> createPacket(CustomPacketPayload payload) {
					return new ServerboundCustomPayloadPacket(payload);
				}

				@Override
				public void disconnect(Component message) {
					context.disconnect(message);
				}
			});
		});
		*///?}
	}

	public interface ConfigurationPayloadHandler<T extends CustomPacketPayload> {
		void handle(T payload, ServerContext context);
	}

	public interface ServerContext extends NetworkingAbstractions.Context {
		void finishCurrentTask(ConfigurationTask.Type type);
	}
}

package xyz.violaflower.legacy_tweaks.networking.configuration;

//? if fabric {
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
//?} elif neoforge {
/*import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
*///?}
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;


public class ClientboundConfiguration {
	public static <T extends CustomPacketPayload> void registerHandler(CustomPacketPayload.Type<T> type, ConfigurationPayloadHandler<T> handler) {
		//? if fabric {
		ClientConfigurationNetworking.registerGlobalReceiver(type, (payload, context) -> {
			handler.handle(payload, new NetworkingAbstractions.Context() {
				@Override
				public Player player() {
					return context.client().player;
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
		/*NetworkingAbstractions.event.registrar(NetworkingAbstractions.version).configurationToClient(type, (StreamCodec<? super FriendlyByteBuf, T>) (Object) NetworkingAbstractions.CODEC_MAP.get(type), (payload, context) -> {
			handler.handle(payload, new NetworkingAbstractions.Context() {
				@Override
				public Player player() {
					return context.player();
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
		void handle(T payload, NetworkingAbstractions.Context context);
	}
}

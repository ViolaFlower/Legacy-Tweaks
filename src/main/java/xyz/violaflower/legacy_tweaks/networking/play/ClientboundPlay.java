package xyz.violaflower.legacy_tweaks.networking.play;

//? if fabric {
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
//?} elif neoforge {
/*import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
*///?}
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;

public class ClientboundPlay {
	/**
	 * Registers a handler for a payload type.
	 * A global receiver is registered to all connections, in the present and future.
	 * @param type the payload type
	 * @param handler the handler
	 */
	public static <T extends CustomPacketPayload> void registerHandler(CustomPacketPayload.Type<T> type, PlayPayloadHandler<T> handler) {
		//? if fabric {
		ClientPlayNetworking.registerGlobalReceiver(type, (t, context) -> handler.handle(t, new NetworkingAbstractions.Context() {
			@Override
			public LocalPlayer player() {
				return context.player();
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
		}));
		//?} elif neoforge {
			/*NetworkingAbstractions.event.registrar(NetworkingAbstractions.version).playToClient(type, (StreamCodec<? super FriendlyByteBuf, T>) (Object) NetworkingAbstractions.CODEC_MAP.get(type), (t, context) -> handler.handle(t, new NetworkingAbstractions.Context() {
				@Override
				public LocalPlayer player() {
					return (LocalPlayer) context.player();
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
			}));
			*///?}
	}

	public interface PlayPayloadHandler<T> {
		void handle(T handler, NetworkingAbstractions.Context context);
	}
}

package xyz.violaflower.legacy_tweaks.networking;

//? if fabric {
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
//?} elif neoforge {
/*import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
*///?}
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;

public class NetworkingAbstractions {
	//? if neoforge
	/*public static RegisterPayloadHandlersEvent event;*/
	private static final String version = "1.0.0";
	//? if neoforge {
	/*private static final HashMap<CustomPacketPayload.Type<?>, StreamCodec<FriendlyByteBuf, ?>> CODEC_MAP = new HashMap<>();
	*///?}
	public enum PayloadType {
		PLAY_C2S,
		PLAY_S2C,
		CONFIGURATION_C2S,
		CONFIGURATION_S2C;

		//? if fabric {
		public PayloadTypeRegistry<? extends FriendlyByteBuf> registry() {
			return switch (this) {
				case PLAY_C2S -> PayloadTypeRegistry.playC2S();
				case PLAY_S2C -> PayloadTypeRegistry.playS2C();
				case CONFIGURATION_C2S -> PayloadTypeRegistry.configurationC2S();
				case CONFIGURATION_S2C -> PayloadTypeRegistry.configurationS2C();
			};
		}
		//?}
	}
	public static <T extends CustomPacketPayload> void registerCodec(CustomPacketPayload.Type<T> type, StreamCodec<FriendlyByteBuf, T> codec, PayloadType payloadType) {
		//? if fabric {
		payloadType.registry().register(type, codec);
		//?} elif neoforge {
		/*CODEC_MAP.put(type, codec);
		*///?}
	}
	public static class Client {
		public static <T extends CustomPacketPayload> void playToClient(CustomPacketPayload.Type<T> type, PlayPayloadHandler<T> handler) {
			//? if fabric {
			ClientPlayNetworking.registerGlobalReceiver(type, (t, context) -> handler.handle(t, new Context() {
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
			/*event.registrar(version).playToClient(type, (StreamCodec<? super RegistryFriendlyByteBuf, T>) (Object) CODEC_MAP.get(type), (t, context) -> handler.handle(t, new Context() {
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
			void handle(T handler, Context context);
		}
	}

	public interface Context {
		Player player();
		void sendPacket(Packet<?> packet);
		void sendPacket(CustomPacketPayload payload);
		Packet<?> createPacket(CustomPacketPayload payload);
		void disconnect(Component message);
	}
}

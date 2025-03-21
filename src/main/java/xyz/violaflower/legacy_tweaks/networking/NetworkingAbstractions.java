package xyz.violaflower.legacy_tweaks.networking;

//? if fabric {
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
//?} elif neoforge {
/*import net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import java.util.HashMap;
*///?}
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.ApiStatus;

// TODO documentation
public class NetworkingAbstractions {
	//? if neoforge {
	/*@ApiStatus.Internal
	public static RegisterConfigurationTasksEvent configurationTasksEvent;
	@ApiStatus.Internal
	public static RegisterPayloadHandlersEvent event;
	@ApiStatus.Internal
	public static final String version = "1.0.0";
	@ApiStatus.Internal
	public static final HashMap<CustomPacketPayload.Type<?>, StreamCodec<FriendlyByteBuf, ?>> CODEC_MAP = new HashMap<>();
	*///?}
	public enum PayloadType {
		PLAY_C2S,
		PLAY_S2C,
		CONFIGURATION_C2S,
		CONFIGURATION_S2C;

		//? if fabric {
		@ApiStatus.Internal
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

	public interface Context {
		/// {@link net.minecraft.client.player.LocalPlayer} if the client is on the receiving end,<br>
		/// {@link net.minecraft.server.level.ServerPlayer} if the server is on the receiving end.
		Player player();
		void sendPacket(Packet<?> packet);
		void sendPacket(CustomPacketPayload payload);
		Packet<?> createPacket(CustomPacketPayload payload);
		void disconnect(Component message);
	}
}

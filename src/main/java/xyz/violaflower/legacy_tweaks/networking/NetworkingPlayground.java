package xyz.violaflower.legacy_tweaks.networking;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.extensions.ICommonPacketListener;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class NetworkingPlayground {
	public static void test() {
		// this is how fabric does play packets
		ClientPlayNetworking.registerGlobalReceiver(Help.TYPE, (customPacketPayload, context) -> {
			Help customPacketPayload1 = customPacketPayload;
			ClientPlayNetworking.Context context1 = context;
			PacketSender packetSender = context1.responseSender();
			Minecraft client = context1.client();
			LocalPlayer player = context1.player();
		});

	}

	// NeoForge
	@SubscribeEvent
	public static void help(RegisterPayloadHandlersEvent event) {
		event.registrar("1.0.0").playToClient(Help.TYPE, Help.STREAM_CODEC, (arg, iPayloadContext) -> {
			Help arg1 = arg;
			IPayloadContext iPayloadContext1 = iPayloadContext;
			Connection connection = iPayloadContext1.connection();
			ConnectionProtocol protocol = iPayloadContext1.protocol();
			Player player = iPayloadContext1.player();
			ChannelHandlerContext channelHandlerContext = iPayloadContext1.channelHandlerContext();
			ICommonPacketListener listener = iPayloadContext1.listener();
		});
	}

	public static record Help() implements CustomPacketPayload {
		public static final StreamCodec<ByteBuf, Help> STREAM_CODEC = StreamCodec.unit(new Help());
		public static final CustomPacketPayload.Type<Help> TYPE = CustomPacketPayload.createType("help");

		@Override
		public Type<? extends CustomPacketPayload> type() {
			return TYPE;
		}
	}
}

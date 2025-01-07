package xyz.violaflower.legacy_tweaks.network.task;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import xyz.violaflower.legacy_tweaks.network.payload.CoolConfigurationPacket;
import xyz.violaflower.legacy_tweaks.networking.configuration.event.ServerConfigurationEvents;
import xyz.violaflower.legacy_tweaks.util.ModAsset;

import java.util.function.Consumer;

public class SendCoolConfigurationPacketTask implements ServerConfigurationEvents.CustomPayloadConfigurationTask {
	public static final Type TYPE = new Type(ModAsset.getResourceLocation("send_cool_configuration_packet_task").toString());
	@Override
	public void run(Consumer<CustomPacketPayload> sender) {
		sender.accept(new CoolConfigurationPacket(99));
	}

	@Override
	public Type type() {
		return TYPE;
	}
}

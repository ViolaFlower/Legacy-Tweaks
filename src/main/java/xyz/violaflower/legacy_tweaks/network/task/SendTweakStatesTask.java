package xyz.violaflower.legacy_tweaks.network.task;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import xyz.violaflower.legacy_tweaks.network.payload.CoolConfigurationPacket;
import xyz.violaflower.legacy_tweaks.network.payload.TweakStatesPayload;
import xyz.violaflower.legacy_tweaks.networking.configuration.event.ServerConfigurationEvents;
import xyz.violaflower.legacy_tweaks.tweaks.TweakState;
import xyz.violaflower.legacy_tweaks.util.ModAsset;

import java.util.function.Consumer;

public class SendTweakStatesTask implements ServerConfigurationEvents.CustomPayloadConfigurationTask {
	public static final Type TYPE = new Type(ModAsset.getResourceLocation("send_tweak_states_task").toString());
	@Override
	public void run(Consumer<CustomPacketPayload> sender) {
		sender.accept(new TweakStatesPayload(TweakState.encodeStates()));
	}

	@Override
	public Type type() {
		return TYPE;
	}
}

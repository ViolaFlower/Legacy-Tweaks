//? if neoforge {
/*package xyz.violaflower.legacy_tweaks.mixin.client.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;
import net.minecraft.network.protocol.configuration.ClientConfigurationPacketListener;
import net.neoforged.neoforge.network.registration.NetworkPayloadSetup;
import net.neoforged.neoforge.network.registration.NetworkRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.networking.event.InternalClientConfigurationEvents;

@SuppressWarnings("UnstableApiUsage")
@Mixin(NetworkRegistry.class)
public class NetworkRegistryMixin {
	@Inject(method = "initializeNeoForgeConnection(Lnet/minecraft/network/protocol/configuration/ClientConfigurationPacketListener;Lnet/neoforged/neoforge/network/registration/NetworkPayloadSetup;)V", at = @At("TAIL"))
	private static void startConfiguration(ClientConfigurationPacketListener listener, NetworkPayloadSetup setup, CallbackInfo ci) {
		InternalClientConfigurationEvents.START.invoker().onConfigurationStart((ClientConfigurationPacketListenerImpl) listener, Minecraft.getInstance());
	}
}
*///?}
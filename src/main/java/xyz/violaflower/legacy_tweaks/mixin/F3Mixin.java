package xyz.violaflower.legacy_tweaks.mixin;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.gui.LTScreen;

import java.util.List;

@Mixin(targets = "net/minecraft/client/gui/components/DebugScreenOverlay")
public class F3Mixin {
        @Inject(method = "getGameInformation", at = @At("RETURN"))
        private void getGameInformation(CallbackInfoReturnable<List<String>> cir) {
            // Dexrn: for Jab125...
            // "Enabled tweaks: %s"
        }
}
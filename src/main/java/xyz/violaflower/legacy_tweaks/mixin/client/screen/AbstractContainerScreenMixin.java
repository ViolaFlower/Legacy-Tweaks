package xyz.violaflower.legacy_tweaks.mixin.client.screen;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.hud.PaperDollHelper;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {

    // Credits: Legacy4J 1.7.4
    @Inject(method = "onClose",at = @At("RETURN"))
    public void removed(CallbackInfo ci) {
        if (Minecraft.getInstance().player.getInventory().armor.stream().anyMatch(i-> !i.isEmpty())) {
            PaperDollHelper.animatedCharacterTime = Util.getMillis();
            PaperDollHelper.remainingAnimatedCharacterTime = 1500;
        }
    }
}

package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.legacy_container;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyContainerScreen;

@Mixin(ContainerScreen.class)
public class ContainerScreenMixin {

//    @Inject(method = "<init>", at = @At("TAIL"))
//    private void changeScreen(CallbackInfo ci) {
//        Minecraft.getInstance().setScreen(new LegacyContainerScreen());
//    }
}

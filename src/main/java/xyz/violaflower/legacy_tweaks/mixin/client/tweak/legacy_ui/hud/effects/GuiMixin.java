package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.hud.effects;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Gui.class, priority = -999999999)
public class GuiMixin {

    @Inject(method = "renderEffects", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V"))
    private void startSetEffectsDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {

    }

    @Inject(method = "renderEffects", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", shift = At.Shift.AFTER))
    private void endSetEffectsDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {

    }
}

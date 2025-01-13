package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.crosshair;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudHelper;

@Mixin(value = Gui.class, priority = -999999999)
public class GuiMixin {

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    public void startCrosshairRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        HudHelper.start(guiGraphics, true, HudHelper.guiHudTweaks.generalTweaks.applyHudScaleCrosshair.isOn(), false, true, false, 3f/HudHelper.getHudScale(), 0f, 0f, 2f, 2f);
    }

    @Inject(method = "renderCrosshair", at = @At("RETURN"), cancellable = true)
    public void endCrosshairRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        HudHelper.end(guiGraphics, true);
    }
}

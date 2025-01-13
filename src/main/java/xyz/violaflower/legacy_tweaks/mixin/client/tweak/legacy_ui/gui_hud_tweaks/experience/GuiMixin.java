package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.experience;

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
public abstract class GuiMixin {

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    public void startExperienceRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"))
    private void startExperienceRender(GuiGraphics guiGraphics, int x, CallbackInfo ci) {
//        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
//            ci.cancel();
//            return;
//        }
//        HudHelper.start(guiGraphics, true, HudHelper.guiHudTweaks.hotbarTweaks.applyHudScaleHotbar.isOn(), HudHelper.guiHudTweaks.hotbarTweaks.applyScreenSpacingHotbar.isOn(), true, false, 3f/HudHelper.getHudScale(), 0f, -HudHelper.getHotbarSpacing(true, false), 2f, 1f);
        if (HudHelper.guiHudTweaks.hotbarTweaks.legacyExperienceText.isOn()) {
            HudHelper.createLegacyExperienceText(guiGraphics);
        } else {
            HudHelper.createVanillaExperienceText(guiGraphics);
        }
    }

    @Inject(method = "renderExperienceBar", at = @At("TAIL"))
    private void endExperienceRender(GuiGraphics guiGraphics, int x, CallbackInfo ci) {
//        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
//            ci.cancel();
//            return;
//        }
//        HudHelper.end(guiGraphics, true);
    }
}

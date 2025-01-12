package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.experience;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudElements;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudHelper;

@Mixin(value = Gui.class)
public abstract class GuiMixin {

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    public void startExperienceRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (HudHelper.guiHudTweaks.hotbarTweaks.legacyExperienceText.isOn()) {
            ci.cancel();
            HudHelper.startNew(guiGraphics, false, true, 3f/HudHelper.getHudScale(HudElements.EXPERIENCE), HudHelper.getHudOpacity(), 0f, -HudHelper.getHudSpacing(true, false), 2f, 2f);
//            HudHelper.startAlt(guiGraphics, HudElements.EXPERIENCE);
            HudHelper.createLegacyExperienceText(guiGraphics);
        } else {
            HudHelper.start(guiGraphics, HudElements.EXPERIENCE, true);
        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("TAIL"))
    private void endExperienceRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudHelper.end(guiGraphics);
    }
}

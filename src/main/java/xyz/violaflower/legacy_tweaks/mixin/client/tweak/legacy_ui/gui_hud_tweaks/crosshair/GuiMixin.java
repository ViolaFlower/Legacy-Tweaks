package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.crosshair;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudElements;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudHelper;

@Mixin(value = Gui.class, priority = -999999999)
public class GuiMixin {

    @Inject(method = "renderCrosshair", at = @At("HEAD"))
    public void startCrosshairRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudHelper.startNew(guiGraphics, false, false, 3f/ HudHelper.getHudScale(HudElements.HOTBAR), HudHelper.getHudOpacity(), 0f, 0f, 2f, 2f);
    }

    @Inject(method = "renderCrosshair", at = @At("RETURN"))
    public void endCrosshairRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudHelper.end(guiGraphics);
    }
}

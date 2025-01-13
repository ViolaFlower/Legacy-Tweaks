package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.scoreboard;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Gui.class, priority = -999999999)
public class GuiMixin {

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At("HEAD"))
    private void startSetScoreboardDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
//        HudHelper.startNew(guiGraphics, false, false, 1f, HudHelper.getHudOpacity(), 0f, 0f, 2f, 1f);
    }

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At("TAIL"))
    private void endSetScoreboardDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
//        HudHelper.end(guiGraphics);
    }
}

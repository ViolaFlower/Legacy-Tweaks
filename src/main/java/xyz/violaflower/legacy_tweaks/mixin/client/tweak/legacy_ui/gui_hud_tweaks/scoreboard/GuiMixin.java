package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.scoreboard;

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

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At("HEAD"))
    private void startSetScoreboardDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudHelper.start(guiGraphics, HudElements.SCOREBOARD);
    }

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At("TAIL"))
    private void endSetScoreboardDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudHelper.end(guiGraphics);
    }
}
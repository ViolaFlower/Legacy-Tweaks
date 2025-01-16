package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.hud.other;

import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Gui.class, priority = -999999999)
public class GuiMixin {

//    @Inject(method = "render", at = @At("HEAD"))
//    private void startSetOtherHeadDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
//        HudHelper.start(guiGraphics, HudElements.OTHER);
//    }
//
//    @Inject(method = "render", at = @At("TAIL"))
//    private void startSetOtherTailDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
//        HudHelper.start(guiGraphics, HudElements.OTHER);
//    }
//
//    @Inject(method = "render", at = @At("HEAD"))
//    private void endSetOtherHeadDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
//        HudHelper.end(guiGraphics);
//    }
//
//    @Inject(method = "render", at = @At("TAIL"))
//    private void endSetOtherTailDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
//        HudHelper.end(guiGraphics);
//    }

}

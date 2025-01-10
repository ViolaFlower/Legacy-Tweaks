package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.chat;

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

    @Inject(method = "renderChat", at = @At("HEAD"))
    private void startSetChatDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        float guiScale = HudHelper.guiHudTweaks.generalTweaks.hudScale.get().floatValue();
        float scaled = guiScale == 1 ? 1.0f : guiScale == 2 ? 1.5f : 2.0f;
        HudHelper.startNew(guiGraphics, false, true, 1.0f/scaled, 1.0f, 0.0f, 0.0f);
    }

    @Inject(method = "renderChat", at = @At("TAIL"))
    private void endSetChatDistance(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudHelper.end(guiGraphics);
    }
}

package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.experience_bar;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudElements;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;

@Mixin(value = Gui.class)
public abstract class GuiMixin {

    @Shadow @Final
    private Minecraft minecraft;

    @Shadow
    protected abstract boolean isExperienceBarVisible();

    @Shadow public abstract Font getFont();

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    public void startExperienceRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudHelper.start(guiGraphics, HudElements.EXPERIENCE);
//        if (Tweaks.LEGACY_UI.guiHudTweaks.legacyExperienceBar.isOn()) {
//            ci.cancel();
//            if (minecraft.screen != null) return;
//            int i = this.minecraft.player.experienceLevel;
//            if (this.isExperienceBarVisible() && i > 0) {
//                this.minecraft.getProfiler().push("expLevel");
//                String exp = "" + i;
//                int hudScale = Tweaks.LEGACY_UI.guiHudTweaks.hudScale.get();
//                ScreenUtil.drawOutlinedString(guiGraphics, getFont(), Component.literal(exp), -this.getFont().width(exp) / 2, -2, 8453920, 0, ScreenUtil.is720p() && hudScale == 3 || !ScreenUtil.is720p() && hudScale == 2 || hudScale == 1 ? 1 / 2f : 2 / 3f);
//                this.minecraft.getProfiler().pop();
//            }
//        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("TAIL"))
    private void endExperienceRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudHelper.end(guiGraphics);
    }
}

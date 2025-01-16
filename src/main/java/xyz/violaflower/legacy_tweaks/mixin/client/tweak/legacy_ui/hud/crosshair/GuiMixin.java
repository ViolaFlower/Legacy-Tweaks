package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.hud.crosshair;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.CameraType;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.hud.HudHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

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

    @WrapOperation(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;blendFuncSeparate(Lcom/mojang/blaze3d/platform/GlStateManager$SourceFactor;Lcom/mojang/blaze3d/platform/GlStateManager$DestFactor;Lcom/mojang/blaze3d/platform/GlStateManager$SourceFactor;Lcom/mojang/blaze3d/platform/GlStateManager$DestFactor;)V"))
    public void jeomjlkµåœ¥ˆøœ¥åß(GlStateManager.SourceFactor sourceFactor, GlStateManager.DestFactor destFactor, GlStateManager.SourceFactor sourceFactor2, GlStateManager.DestFactor destFactor2, Operation<Void> original) {
        if (false) {
            original.call(sourceFactor, destFactor, sourceFactor2, destFactor2);
        } else {
            RenderSystem.defaultBlendFunc();
        }
    }

    @WrapOperation(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/CameraType;isFirstPerson()Z"))
    private boolean shouldRenderCrosshairInThirdPerson(CameraType instance, Operation<Boolean> original) {
        if (Tweaks.LEGACY_UI.guiHudTweaks.generalTweaks.thirdPersonCrosshair.isOn()) return true;
        return original.call(instance);
    }
}

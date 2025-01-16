package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.hud.hotbar;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.Util;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.candy.text.TextHelper;
import xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.hud.HudHelper;
import xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.hud.PaperDollHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

@Mixin(value = Gui.class, priority = -999999999)
public class GuiMixin {

    @Shadow
    private long healthBlinkTime;

    @Unique
    private int lastHotbarSelection = -1;

    @Inject(method = "renderHotbarAndDecorations", at = @At("HEAD"), cancellable = true)
    private void startHotbarRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        PaperDollHelper.renderPaperDollStartEnd(guiGraphics, deltaTracker);
        int newSelection = Minecraft.getInstance().player != null ? Minecraft.getInstance().player.getInventory().selected : -1;
        if (lastHotbarSelection >= 0 && lastHotbarSelection != newSelection) HudHelper.lastHotbarSelectionChange = Util.getMillis();
        lastHotbarSelection = newSelection;
        HudHelper.start(guiGraphics, HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn(), HudHelper.guiHudTweaks.hotbarTweaks.applyHudScaleHotbar.isOn(), HudHelper.guiHudTweaks.hotbarTweaks.applyScreenSpacingHotbar.isOn(), true, false, 3f/HudHelper.getHudScale(), 0f, -HudHelper.getHotbarSpacing(true, false), 2f, 1f);
    }

    @Inject(method = "renderHotbarAndDecorations", at = @At("TAIL"), cancellable = true)
    private void endHotbarRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        HudHelper.end(guiGraphics, HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn());
    }

//    @Inject(method = "renderItemHotbar", at = @At("HEAD"), cancellable = true)
//    private void startItemHotbarRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
//        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
//            ci.cancel();
//            return;
//        }
//        PaperDollHelper.renderPaperDollStartEnd(guiGraphics, deltaTracker);
//        int newSelection = Minecraft.getInstance().player != null ? Minecraft.getInstance().player.getInventory().selected : -1;
//        if (lastHotbarSelection >= 0 && lastHotbarSelection != newSelection) HudHelper.lastHotbarSelectionChange = Util.getMillis();
//        lastHotbarSelection = newSelection;
//        HudHelper.start(guiGraphics, HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn(), HudHelper.guiHudTweaks.hotbarTweaks.applyHudScaleHotbar.isOn(), HudHelper.guiHudTweaks.hotbarTweaks.applyScreenSpacingHotbar.isOn(), true, false, 3f/HudHelper.getHudScale(), 0f, -HudHelper.getHotbarSpacing(true, false), 2f, 1f);
//    }
//
//    @Inject(method = "renderItemHotbar", at = @At("TAIL"), cancellable = true)
//    private void endItemHotbarRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
//        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
//            ci.cancel();
//            return;
//        }
//        HudHelper.end(guiGraphics, HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn());
//    }
//
//    @Inject(method = "renderPlayerHealth", at = @At("HEAD"), cancellable = true)
//    private void startHealthRender(GuiGraphics guiGraphics, CallbackInfo ci) {
//        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
//            ci.cancel();
//            return;
//        }
//        HudHelper.start(guiGraphics, HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn(), HudHelper.guiHudTweaks.hotbarTweaks.applyHudScaleHotbar.isOn(), HudHelper.guiHudTweaks.hotbarTweaks.applyScreenSpacingHotbar.isOn(), true, false, 3f/HudHelper.getHudScale(), 0f, -HudHelper.getHotbarSpacing(true, false), 2f, 1f);
//    }
//
//    @Inject(method = "renderPlayerHealth", at = @At("TAIL"), cancellable = true)
//    private void endHealthRender(GuiGraphics guiGraphics, CallbackInfo ci) {
//        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
//            ci.cancel();
//            return;
//        }
//        HudHelper.end(guiGraphics, HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn());
//    }

    @Inject(method = "renderSelectedItemName", at = @At("HEAD"), cancellable = true)
    private void startItemNameRender(GuiGraphics guiGraphics, CallbackInfo ci) {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        float guiScale = HudHelper.guiHudTweaks.generalTweaks.hudScale.get().floatValue();
        boolean tweak = HudHelper.guiHudTweaks.hotbarTweaks.legacyItemOverlay.isOn();
        float scaled = 1f;
        float textPos = 0f;
        float textPosMultipier = 1.5f;
        if (tweak) { // TODO fix the setting for when legacyTextOverlay is disabled
            TextHelper.setLegacyTextShadows(true);
            TextHelper.setCompactText(false);
            scaled = 1f/(guiScale == 1 ? 1f : 1.5f);
//            textPos = guiScale == 1 ? 1.5f : guiScale == 2 ? 2f : 2.5f;
            textPos = 13f;
            textPosMultipier = 1f;
        }
        HudHelper.start(guiGraphics, HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn(), HudHelper.guiHudTweaks.hotbarTweaks.applyHudScaleHotbar.isOn(), HudHelper.guiHudTweaks.hotbarTweaks.applyScreenSpacingHotbar.isOn(), true, tweak, scaled, 0f, (-HudHelper.getHotbarSpacing(true, false) * textPosMultipier) + textPos, 2f, 1f);
    }

    @Inject(method = "renderSelectedItemName", at = @At("TAIL"), cancellable = true)
    private void endItemNameRender(GuiGraphics guiGraphics, CallbackInfo ci) {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        HudHelper.end(guiGraphics, HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn());
    }

    @ModifyArgs(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1))
    private void changeHotbarTexture(Args args) {
        if (Tweaks.LEGACY_UI.guiHudTweaks.hotbarTweaks.useLegacyHotbarTexture.isOn() && HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn()) {
            args.set(0, Sprites.HOTBAR_SELECTION);
            args.set(4, 24);
        }
    }

    @WrapOperation(method = /*? if (neoforge) {*//*"renderHealthLevel"*//*?} else {*/"renderPlayerHealth"/*?}*/, at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/Gui;healthBlinkTime:J", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void renderPlayerHealth(Gui instance, long value, Operation<Void> original) {
        if (Tweaks.LEGACY_UI.guiHudTweaks.hotbarTweaks.fastHealthBlink.isOn() && HudHelper.guiHudTweaks.hotbarTweaks.legacyHotbar.isOn()) healthBlinkTime = value - 6;
        original.call(instance, value);
    }
}

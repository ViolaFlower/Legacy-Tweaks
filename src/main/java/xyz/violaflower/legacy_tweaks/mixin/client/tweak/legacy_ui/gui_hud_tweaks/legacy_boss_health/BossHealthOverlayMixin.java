package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.legacy_boss_health;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudHelper;

@Mixin(value = BossHealthOverlay.class, priority = -999999999)
public abstract class BossHealthOverlayMixin {

    @Shadow protected abstract void drawBar(GuiGraphics guiGraphics, int i, int j, BossEvent bossEvent, int k, ResourceLocation[] resourceLocations, ResourceLocation[] resourceLocations2);

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void startGeneralRender(GuiGraphics guiGraphics, CallbackInfo ci) {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        HudHelper.start(guiGraphics, HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn(), HudHelper.guiHudTweaks.bossBarTweaks.applyHudScaleBossHealth.isOn(), HudHelper.guiHudTweaks.bossBarTweaks.applyScreenSpacingBossHealth.isOn(), true, true, 1f, 0f, 0f, 2f, 2f);
    }

    @Inject(method = "render", at = @At("TAIL"), cancellable = true)
    private void endGeneralRender(GuiGraphics guiGraphics, CallbackInfo ci) {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        HudHelper.end(guiGraphics, HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn());
    }

    @Inject(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;)V", at = @At("HEAD"))
    private void startBarRender(GuiGraphics guiGraphics, int x, int y, BossEvent bossEvent, CallbackInfo ci) {
        HudHelper.start(guiGraphics, HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn(), HudHelper.guiHudTweaks.bossBarTweaks.applyHudScaleBossHealth.isOn(), HudHelper.guiHudTweaks.bossBarTweaks.applyScreenSpacingBossHealth.isOn(), false, true, 0.5f, -55f, -100f + 12f + (16f * HudHelper.guiHudTweaks.generalTweaks.screenSpacing.get().floatValue()/100), 2f, 2f);
    }
    @Inject(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;)V", at = @At("TAIL"))
    private void endBarRender(GuiGraphics guiGraphics, int x, int y, BossEvent bossEvent, CallbackInfo ci) {
        HudHelper.end(guiGraphics, HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn());

    }

    @ModifyArgs(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;I[Lnet/minecraft/resources/ResourceLocation;[Lnet/minecraft/resources/ResourceLocation;)V", ordinal = 0))
    private void modifyBarRender(Args args) {
        if (HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn()) args.set(4, 406);
    }

    @ModifyArgs(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;I[Lnet/minecraft/resources/ResourceLocation;[Lnet/minecraft/resources/ResourceLocation;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIIIIIII)V"))
    private void modifyBarTexture(Args args) {
        if (HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn()) {
            args.set(1, args.get(7).hashCode() <= 400 ? 400 : 406);
            args.set(2, args.get(2).hashCode() * 3);
            args.set(8, args.get(8).hashCode() * 3);
        }
    }

    @WrapOperation(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;I[Lnet/minecraft/resources/ResourceLocation;[Lnet/minecraft/resources/ResourceLocation;)V", ordinal = 1))
    private void modifyDrawBarProgress(BossHealthOverlay instance, GuiGraphics guiGraphics, int x, int y, BossEvent bossEvent, int progress, ResourceLocation[] barProgressSprites, ResourceLocation[] overlayProgressSprites, Operation<Void> original) {
        if (HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn()) {
            guiGraphics.pose().translate(3f, 0, 0);
            drawBar(guiGraphics, x, y, bossEvent, Mth.lerpDiscrete(bossEvent.getProgress(), 0, 400), barProgressSprites, overlayProgressSprites);
        }
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)I"))
    private int addTextShadowAndMove(GuiGraphics instance, Font font, Component text, int x, int y, int color, Operation<Integer> original) {
        if (HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn()) return instance.drawStringWithBackdrop(font, text, x, y + (16 * HudHelper.guiHudTweaks.generalTweaks.screenSpacing.get()/100), 1, color);
        return original.call(instance, font, text, x, y, color);
    }
}

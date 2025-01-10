package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.gui_hud_tweaks.legacy_boss_health;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudElements;
import xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudHelper;

@Mixin(value = BossHealthOverlay.class, priority = -999999999)
public abstract class BossHealthOverlayMixin {

    @Shadow protected abstract void drawBar(GuiGraphics guiGraphics, int i, int j, BossEvent bossEvent, int k, ResourceLocation[] resourceLocations, ResourceLocation[] resourceLocations2);
    @Inject(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;)V", at = @At("HEAD"))
    private void drawBar(GuiGraphics guiGraphics, int i, int j, BossEvent bossEvent, CallbackInfo ci) {
        if (HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn()) {
            guiGraphics.pose().pushPose();
            RenderSystem.enableBlend();
            guiGraphics.pose().translate((guiGraphics.guiWidth() - 203) / 2f, j, 0);
            guiGraphics.pose().scale(0.5f, 0.5f, 0.5f);
        }
    }
    @Inject(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;)V", at = @At("RETURN"))
    private void drawBarReturn(GuiGraphics guiGraphics, int i, int j, BossEvent bossEvent, CallbackInfo ci) {
        if (HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn()) {
            RenderSystem.disableBlend();
            guiGraphics.pose().popPose();
        }
    }
    @Redirect(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;I[Lnet/minecraft/resources/ResourceLocation;[Lnet/minecraft/resources/ResourceLocation;)V", ordinal = 0))
    private void drawBar(BossHealthOverlay instance, GuiGraphics guiGraphics, int i, int j, BossEvent bossEvent, int k, ResourceLocation[] resourceLocations, ResourceLocation[] resourceLocations2) {
        if (HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn()) drawBar(guiGraphics, 0, 0, bossEvent, 406, resourceLocations, resourceLocations2);
    }
    @Redirect(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;I[Lnet/minecraft/resources/ResourceLocation;[Lnet/minecraft/resources/ResourceLocation;)V", ordinal = 1))
    private void drawBarProgress(BossHealthOverlay instance, GuiGraphics guiGraphics, int i, int j, BossEvent bossEvent, int k, ResourceLocation[] resourceLocations, ResourceLocation[] resourceLocations2) {
        if (HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn()) {
            guiGraphics.pose().translate(3f, 0, 0);
            drawBar(guiGraphics, 0, 0, bossEvent, Mth.lerpDiscrete(bossEvent.getProgress(), 0, 400), resourceLocations, resourceLocations2);
        }
    }
    @Redirect(method = "drawBar(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/BossEvent;I[Lnet/minecraft/resources/ResourceLocation;[Lnet/minecraft/resources/ResourceLocation;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIIIIIII)V"))
    private void drawBar(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int m, int n, int o, int p) {
        if (HudHelper.guiHudTweaks.bossBarTweaks.legacyBossBar.isOn()) guiGraphics.blitSprite(resourceLocation, o <= 400 ? 400 : 406, j * 3, k, l, m, n, o, p * 3);
    }




    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startBossBarTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudHelper.start(guiGraphics, HudElements.BOSS_HEALTH, true);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
    private void endBossBarTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudHelper.end(guiGraphics);
    }
}

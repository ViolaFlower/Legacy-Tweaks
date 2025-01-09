package xyz.violaflower.legacy_tweaks.helper.tweak.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyTitleScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.LegacyUI;

public class HudHelper {
    public static final LegacyUI.GuiHudTweaks guiHudTweaks = Tweaks.LEGACY_UI.guiHudTweaks;

    public static void start(GuiGraphics guiGraphics, HudElements hudElements) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        RenderSystem.enableBlend();
        setDistanceOffset(guiGraphics, hudElements);
        startHudScaleOffsetTranslate(guiGraphics, hudElements);
        poseStack.scale(3f/getHudScale(hudElements), 3f/getHudScale(hudElements), 3f/getHudScale(hudElements));
        endHudScaleOffsetTranslate(guiGraphics, hudElements);
        guiGraphics.setColor(1.0f,1.0f,1.0f, getHudOpacity());
    }

    public static void setDistanceOffset(GuiGraphics guiGraphics, HudElements hudElements) {
        if (hudElements.isDistanceTweakOff()) return;
        PoseStack poseStack = guiGraphics.pose();
        switch (hudElements) {
            case HOTBAR -> poseStack.translate(0f, -getHudDistance(true, false), 0f);
            case EXPERIENCE -> poseStack.translate(0f, -getHudDistance(true, false) - (2.2f * getHudScaleAlt(HudElements.HOTBAR)), 0f);
            case CHAT -> poseStack.translate(0f, -getHudDistance(true, false) * getHudScaleAlt(HudElements.HOTBAR), 0f);
            case SCOREBOARD -> poseStack.translate(-getHudDistance(false, false), 0f, 0f);
            case EFFECTS, PAPER_DOLL, OTHER -> poseStack.translate(getHudDistance(false, false), getHudDistance(false, false), 0f);
        }
    }

    public static void startHudScaleOffsetTranslate(GuiGraphics guiGraphics, HudElements hudElements) {
        if (hudElements.isScaleTweakOff()) return;
        guiGraphics.pose().translate(guiGraphics.guiWidth()/2f, guiGraphics.guiHeight(),0.0F);
    }

    public static void endHudScaleOffsetTranslate(GuiGraphics guiGraphics, HudElements hudElements) {
        if (hudElements.isScaleTweakOff()) return;
        guiGraphics.pose().translate(-guiGraphics.guiWidth()/2, -guiGraphics.guiHeight(),0);
    }

    public static float getHudScale(HudElements hudElements) {
        if (hudElements.isScaleTweakOff()) return 3f;
        return Math.max(1.5f, 4 - Tweaks.LEGACY_UI.guiHudTweaks.hudScale.get());
    }

    public static float getHudScaleAlt(HudElements hudElements) {
        if (hudElements.isScaleTweakOff()) return 3f;
        return Math.max(1.5f, Tweaks.LEGACY_UI.guiHudTweaks.hudScale.get());
    }

    public static float getHudOpacity() {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && guiHudTweaks.hideHudInScreen.isOn()) return 0.0f;
        return (float) Tweaks.LEGACY_UI.guiHudTweaks.hudOpacity.get() /100;
    }

    public static float getHudDistance(boolean tooltipOffset, boolean doubleDistanceSetting){
        return guiHudTweaks.hudDistance.get().floatValue()/100 * (22.5f + (Tweaks.LEGACY_UI.guiHudTweaks.inGameTooltips.get() && tooltipOffset ? 17.5f : 0f));
    }

    public static float getHudDistanceConditional(HudElements hudElements, boolean tooltipOffset, boolean doubleDistanceSetting){
        if (hudElements.isDistanceTweakOff()) return 0.0f;
        return guiHudTweaks.hudDistance.get().floatValue()/100 * (22.5f + (Tweaks.LEGACY_UI.guiHudTweaks.inGameTooltips.get() && tooltipOffset ? 17.5f : 0f));
    }

    public static float getChatSafeZone(){
        return 29 * guiHudTweaks.hudDistance.get().floatValue()/100;
    }

//    public static float getHudSizeForItemTooltip(float hudScale){
//        Minecraft mc = Minecraft.getInstance();
//        return 6 + 3f / hudScale * (35 + (mc.gameMode.canHurtPlayer() ?  Math.max(2, Mth.ceil((Math.max(mc.player.getAttributeValue(Attributes.MAX_HEALTH), Math.max(mc.gui.displayHealth, mc.player.getHealth())) + mc.player.getAbsorptionAmount()) / 20f) + (mc.player.getArmorValue() > 0 ? 1 : 0))* 10 : 0));
//    }

    public static void end(GuiGraphics guiGraphics) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.popPose();
        guiGraphics.setColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.disableBlend();
    }
}

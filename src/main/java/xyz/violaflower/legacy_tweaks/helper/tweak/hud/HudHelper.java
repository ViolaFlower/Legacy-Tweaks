package xyz.violaflower.legacy_tweaks.helper.tweak.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.client.LegacyBufferSourceWrapper;
import xyz.violaflower.legacy_tweaks.helper.tweak.world.EyeCandyHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.LegacyUI;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;

public class HudHelper {
    public static final LegacyUI.GuiHudTweaks guiHudTweaks = Tweaks.LEGACY_UI.guiHudTweaks;
    public static long lastHotbarSelectionChange = -1;

    public static void start(GuiGraphics guiGraphics, HudElements hudElements, boolean useHudScale) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        RenderSystem.enableBlend();

        EyeCandyHelper.setCompactText(true);
        EyeCandyHelper.setLegacyTextShadows(false);

        setScreenSpacingOffset(guiGraphics, hudElements);
        startHudScaleOffsetTranslate(guiGraphics, hudElements);
        if (useHudScale) poseStack.scale(3f/getHudScale(hudElements), 3f/getHudScale(hudElements), 3f/getHudScale(hudElements));
        endHudScaleOffsetTranslate(guiGraphics, hudElements);
        guiGraphics.setColor(1.0f,1.0f,1.0f, getHudOpacity());

        if (HudHelper.getHudOpacity() < 1.0) {
            ScreenUtil.guiBufferSourceOverride = LegacyBufferSourceWrapper.translucent(guiGraphics.bufferSource());
        }
    }

    public static void startNew(GuiGraphics guiGraphics, boolean extraScale, boolean useLegacyText,  float hudScale, float hudOpacity, float screenSpaceX, float screenSpaceY) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        RenderSystem.enableBlend();

        if (!useLegacyText) {
            EyeCandyHelper.setCompactText(true);
            EyeCandyHelper.setLegacyTextShadows(false);
        }

        poseStack.translate(screenSpaceX, screenSpaceY, 0f);
        guiGraphics.pose().translate(guiGraphics.guiWidth()/2f, guiGraphics.guiHeight(),0.0F);
        poseStack.scale(hudScale, hudScale, hudScale);
        if (extraScale) poseStack.scale(hudScale, hudScale, hudScale);
        guiGraphics.pose().translate(-guiGraphics.guiWidth()/2, -guiGraphics.guiHeight(),0);
        guiGraphics.setColor(1.0f,1.0f,1.0f, hudOpacity);

        if (hudOpacity < 1.0) {
            ScreenUtil.guiBufferSourceOverride = LegacyBufferSourceWrapper.translucent(guiGraphics.bufferSource());
        }
    }

    public static void startTooltip(GuiGraphics guiGraphics, HudElements hudElements) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        RenderSystem.enableBlend();

        EyeCandyHelper.setLegacyTextShadows(true);
        EyeCandyHelper.setCompactText(false);

        float guiScale = guiHudTweaks.generalTweaks.hudScale.get().floatValue();
        float scaled = guiScale == 1 ? 1.0f : 1.5f;
        startHudScaleOffsetTranslate(guiGraphics, hudElements);
        poseStack.scale(1.0f/scaled, 1.0f/scaled, 1.0f/scaled);
//        if (guiScale != 1) guiGraphics.pose().translate(150d, 150d,0d);
        endHudScaleOffsetTranslate(guiGraphics, hudElements);
        guiGraphics.setColor(1.0f,1.0f,1.0f, getHudOpacity());
    }

    public static void startAlt(GuiGraphics guiGraphics, HudElements hudElements) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        RenderSystem.enableBlend();

        EyeCandyHelper.setCompactText(true);
        EyeCandyHelper.setLegacyTextShadows(false);

        setScreenSpacingOffset(guiGraphics, hudElements);
        startHudScaleOffsetTranslate(guiGraphics, hudElements);
        poseStack.scale(3f/getHudScale(hudElements), 3f/getHudScale(hudElements), 3f/getHudScale(hudElements));
        guiGraphics.setColor(1.0f,1.0f,1.0f, getHudOpacity());
    }

    public static void setScreenSpacingOffset(GuiGraphics guiGraphics, HudElements hudElements) {
        if (hudElements.isDistanceTweakOff()) return;
        PoseStack poseStack = guiGraphics.pose();
        switch (hudElements) {
            case HOTBAR -> poseStack.translate(0f, -getHudSpacing(true, false), 0f);
            case ITEM_OVERLAY -> poseStack.translate(0f, 0, 0f);
            case EXPERIENCE -> poseStack.translate(0f, -getHudSpacing(true, false) - (guiHudTweaks.hotbarTweaks.legacyExperienceText.isOn() ? 0.0f : 2.2f * getHudScaleAlt(HudElements.HOTBAR)), 0f);
            case CHAT -> poseStack.translate(-getHudSpacing(true, false)/getHudScaleAlt(HudElements.HOTBAR), -getHudSpacing(true, false) * getHudScaleAlt(HudElements.HOTBAR), 0f);
            case SCOREBOARD -> poseStack.translate(-getHudSpacing(false, true), 0f, 0f);
            case BOSS_HEALTH -> poseStack.translate(0f, 12f + 16f * guiHudTweaks.generalTweaks.screenSpacing.get().floatValue()/100, 0f);
            case TOAST -> poseStack.translate(-getHudSpacing(false, true), getHudSpacing(false, true), 0f);
            case EFFECTS, PAPER_DOLL, OTHER -> poseStack.translate(getHudSpacing(false, true), getHudSpacing(false, true), 0f);
        }
    }

    public static void startHudScaleOffsetTranslate(GuiGraphics guiGraphics, HudElements hudElements) {
        guiGraphics.pose().translate(guiGraphics.guiWidth()/2f, guiGraphics.guiHeight(),0.0F);
    }

    public static void endHudScaleOffsetTranslate(GuiGraphics guiGraphics, HudElements hudElements) {
        guiGraphics.pose().translate(-guiGraphics.guiWidth()/2, -guiGraphics.guiHeight(),0);
    }

    public static void fixHudScaleOffsetTranslate(GuiGraphics guiGraphics, HudElements hudElements) {
        guiGraphics.pose().translate(-guiGraphics.guiWidth()/3, -guiGraphics.guiHeight()/1.5,0);
    }

    public static float getHudScale(HudElements hudElements) {
        if (hudElements.isScaleTweakOff() || guiHudTweaks.generalTweaks.forceDisableHudScale.isOn()) return 3f;
        return Math.max(1.5f, 4 - Tweaks.LEGACY_UI.guiHudTweaks.generalTweaks.hudScale.get());
    }

    public static float getHudScaleAlt(HudElements hudElements) {
        if (hudElements.isScaleTweakOff() || guiHudTweaks.generalTweaks.forceDisableHudScale.isOn()) return 3f;
        return Math.max(1.5f, Tweaks.LEGACY_UI.guiHudTweaks.generalTweaks.hudScale.get());
    }

    public static float getHudOpacity() {
        float f = (Util.getMillis() - lastHotbarSelectionChange) / 1200f;
        return getHudOpacitySetting() <= 0.8f ? Math.min(0.8f, getHudOpacitySetting() + (1 - getHudOpacitySetting()) * (f >= 3f ? Math.max(4 - f, 0) : 1)) : getHudOpacitySetting();
    }

    public static float getHudOpacitySetting() {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) return 0.0f;
        return Tweaks.LEGACY_UI.guiHudTweaks.generalTweaks.hudOpacity.get().floatValue() /100;
    }

    public static float getHudSpacing(boolean tooltipOffset, boolean stopHudDistanceAtHalf){
        float hudDistanceValue = guiHudTweaks.generalTweaks.screenSpacing.get().floatValue()/100;
        hudDistanceValue = stopHudDistanceAtHalf ? hudDistanceValue < 0.5f ? 0.0f : hudDistanceValue - 0.5f : hudDistanceValue;
        return hudDistanceValue * ((22.5f + (Tweaks.LEGACY_UI.guiHudTweaks.generalTweaks.inGameTooltips.get() && tooltipOffset ? 17.5f : 0f)) * (stopHudDistanceAtHalf ? 2 : 1));
    }

    public static void end(GuiGraphics guiGraphics) {
        EyeCandyHelper.setLegacyTextShadows(true);
        EyeCandyHelper.setCompactText(false);

        ScreenUtil.guiBufferSourceOverride = null;

        PoseStack poseStack = guiGraphics.pose();
        poseStack.popPose();
        guiGraphics.setColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.disableBlend();
    }

    public static void createLegacyExperienceText(GuiGraphics guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        int i = minecraft.player.experienceLevel;
        if (isExperienceBarVisible() && i > 0) {
            minecraft.getProfiler().push("expLevel");
            String exp = "" + i;
            int hudScale = guiHudTweaks.generalTweaks.hudScale.get();
            guiGraphics.pose().translate(0,-38f,0);
            ScreenUtil.drawOutlinedString(guiGraphics, font, Component.literal(exp), -font.width(exp) / 2, 0, 8453920, 0, ScreenUtil.is720p() && hudScale == 3 || !ScreenUtil.is720p() && hudScale == 2 || hudScale == 1 ? 1 / 2f : 2 / 3f);
            minecraft.getProfiler().pop();
        }
    }

    public static boolean isExperienceBarVisible() {
        return Minecraft.getInstance().player.jumpableVehicle() == null && Minecraft.getInstance().gameMode.hasExperience();
    }
}

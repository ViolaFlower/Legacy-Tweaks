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

    public static void start(GuiGraphics guiGraphics, boolean shouldRender, boolean useHudScale, boolean useScreenSpace, boolean useHudOpacity, boolean useLegacyText, float hudScale, float screenSpaceX, float screenSpaceY, float guiXDiv, float guiYDiv) {
        if (!shouldRender && !guiHudTweaks.isEnabled()) return;
        float hudOpacity = getHudOpacity();
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        RenderSystem.enableBlend();

        if (!useLegacyText) {
            EyeCandyHelper.setCompactText(true);
            EyeCandyHelper.setLegacyTextShadows(false);
        }

        if (useScreenSpace) poseStack.translate(screenSpaceX, screenSpaceY, 0f);
        guiGraphics.pose().translate(guiGraphics.guiWidth()/guiXDiv, guiGraphics.guiHeight()/guiYDiv,0.0F);
        if (useHudScale && !guiHudTweaks.generalTweaks.forceDisableHudScale.isOn()) poseStack.scale(hudScale, hudScale, hudScale);
        guiGraphics.pose().translate((float) -guiGraphics.guiWidth()/guiXDiv, -guiGraphics.guiHeight()/guiYDiv,0);
        if (useHudOpacity) guiGraphics.setColor(1.0f,1.0f,1.0f, hudOpacity);

        if (hudOpacity < 1.0) {
            ScreenUtil.guiBufferSourceOverride = LegacyBufferSourceWrapper.translucent(guiGraphics.bufferSource());
        }
    }

    public static float getHudScale() {
        return Math.max(1.5f, 4 - Tweaks.LEGACY_UI.guiHudTweaks.generalTweaks.hudScale.get());
    }

    public static float getHudOpacity() {
        float f = (Util.getMillis() - lastHotbarSelectionChange) / 1200f;
        return getHudOpacitySetting() <= 0.8f ? Math.min(0.8f, getHudOpacitySetting() + (1 - getHudOpacitySetting()) * (f >= 3f ? Math.max(4 - f, 0) : 1)) : getHudOpacitySetting();
    }

    public static float getHudOpacitySetting() {
        if (Minecraft.getInstance().screen != null && Minecraft.getInstance().level != null && guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) return 0.0f;
        return Tweaks.LEGACY_UI.guiHudTweaks.generalTweaks.hudOpacity.get().floatValue() /100;
    }

    public static float getHotbarSpacing(boolean tooltipOffset, boolean stopHudDistanceAtHalf){
        float hudDistanceValue = guiHudTweaks.generalTweaks.screenSpacing.get().floatValue()/100;
        hudDistanceValue = stopHudDistanceAtHalf ? hudDistanceValue < 0.5f ? 0.0f : hudDistanceValue - 0.5f : hudDistanceValue;
        return hudDistanceValue * ((22.5f + (Tweaks.LEGACY_UI.guiHudTweaks.generalTweaks.inGameTooltips.get() && tooltipOffset ? 17.5f : 0f)) * (stopHudDistanceAtHalf ? 2 : 1));
    }

    public static void end(GuiGraphics guiGraphics, boolean shouldRender) {
        if (!shouldRender) return;
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
        Font font = minecraft.gui.getFont();
        int i = minecraft.player.experienceLevel;
        if (isExperienceBarVisible() && i > 0) {
            minecraft.getProfiler().push("expLevel");
            String exp = "" + i;
            int j = (guiGraphics.guiWidth() - font.width(exp)) / 2;
            int k = guiGraphics.guiHeight() - 38;
            int hudScale = guiHudTweaks.generalTweaks.hudScale.get();
            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();
            ScreenUtil.drawOutlinedString(guiGraphics, font, Component.literal(exp), j, k, 8453920, 0, ScreenUtil.is720p() && hudScale == 3 || !ScreenUtil.is720p() && hudScale == 2 || hudScale == 1 ? 1 / 2f : 2 / 3f);
            poseStack.popPose();
            minecraft.getProfiler().pop();
        }
    }

    public static void createVanillaExperienceText(GuiGraphics guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.gui.getFont();
        int i = minecraft.player.experienceLevel;
        if (isExperienceBarVisible() && i > 0) {
            minecraft.getProfiler().push("expLevel");
            String string = "" + i;
            int j = (guiGraphics.guiWidth() - font.width(string)) / 2;
            int k = guiGraphics.guiHeight() - 38;
            guiGraphics.drawString(font, string, j + 1, k, 0, false);
            guiGraphics.drawString(font, string, j - 1, k, 0, false);
            guiGraphics.drawString(font, string, j, k + 1, 0, false);
            guiGraphics.drawString(font, string, j, k - 1, 0, false);
            guiGraphics.drawString(font, string, j, k, 8453920, false);
            minecraft.getProfiler().pop();
        }
    }

    public static boolean isExperienceBarVisible() {
        return Minecraft.getInstance().player.jumpableVehicle() == null && Minecraft.getInstance().gameMode.hasExperience();
    }

    public static float getChatScreenSpacing(){
        return 29 * guiHudTweaks.generalTweaks.screenSpacing.get().floatValue()/100;
    }

    public static double getLineHeight() {
        return 1.0;
    }
}

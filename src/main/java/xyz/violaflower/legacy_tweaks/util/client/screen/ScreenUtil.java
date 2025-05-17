package xyz.violaflower.legacy_tweaks.util.client.screen;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.GuiGraphicsAccessor;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

import java.util.Objects;

public class ScreenUtil {
    public static MultiBufferSource.BufferSource guiBufferSourceOverride = null;

    public static boolean isLargeGui() {
        //Window screenWindow = Minecraft.getInstance().getWindow();
        return !isHD();
        //return screenWindow.getGuiScale() == screenWindow.calculateScale(0, Minecraft.getInstance().isEnforceUnicode()) || screenWindow.getGuiScale() == 0;
    }

    public static void renderPanorama(GuiGraphics guiGraphics, int width, int height, float fade, float partialTick) {
        guiGraphics.blit(Sprites.PANORAMA_DAY, 0, 0, (float) (Minecraft.getInstance().options.panoramaSpeed().get() * Util.getMillis() * (1 / 60f)), 0, width, height, (int) (height * 5.75) /*preserve aspect ratio*/, height);
    }

    public static double setBestGuiScale() {
        int lastGuiScale = getScreenWindow().calculateScale(0, Minecraft.getInstance().isEnforceUnicode());
        if (Minecraft.ON_OSX && Tweaks.LEGACY_UI.generalScreenTweaks.fixMacOSRetina.isOn()) {
            if (getScreenWindow().isFullscreen()) {
                lastGuiScale = lastGuiScale - 2;
            } else if (isHD()) {
                lastGuiScale = lastGuiScale - 2;
            }
        } else {
            if (getScreenHeight() > 1080) {
                lastGuiScale = lastGuiScale - 3;
            } else if (getScreenHeight() >= 720) {
                lastGuiScale = lastGuiScale - 1;
            }
        }

        return Math.max(1, lastGuiScale);
    }

    public static Window getScreenWindow() {
        return Minecraft.getInstance().getWindow();
    }

    public static int getScreenWidth() {
        return getScreenWindow().getScreenWidth();
    }

    public static int getScreenHeight(){
        return getScreenWindow().getScreenHeight();
    }

    public static boolean isHD() {
        return getScreenHeight() >= 720;
    }

    public static boolean is1080pOrAbove() {
        return getScreenHeight() <= 1080;
    }

    public static boolean isWidescreen() {
        return getScreenWidth() / getScreenHeight() >= 1920/1080;
    }

    public static void drawOutlinedString(GuiGraphics graphics, Font font, Component component, int x, int y, int color, int outlineColor, float outline) {
        drawStringOutline(graphics,font,component,x,y,outlineColor,outline);
        graphics.drawString(font,component, x, y, color,false);
    }

    public static void drawStringOutline(GuiGraphics graphics, Font font, Component component, int x, int y, int outlineColor, float outline) {
        float[] translations = new float[]{0,outline,-outline};
        for (float t : translations) {
            for (float t1 : translations) {
                if (t != 0 || t1 != 0) {
                    graphics.pose().pushPose();
                    graphics.pose().translate(t,t1,0F);
                    graphics.drawString(font, component, x, y, outlineColor, false);
                    graphics.pose().popPose();
                }
            }
        }
    }

    public static int drawStringWithBackdrop(GuiGraphics guiGraphics, Font font, FormattedCharSequence text, int x, int y, int xOffset, int color) {
        Minecraft minecraft = Minecraft.getInstance();
        int i = minecraft.options.getBackgroundColor(0.0F);
        if (i != 0) {
            int j = 2;
            int var10001 = x - 2;
            int var10002 = y - 2;
            int var10003 = x + xOffset + 2;
            Objects.requireNonNull(font);
            guiGraphics.fill(var10001, var10002, var10003, y + 9 + 2, FastColor.ARGB32.multiply(i, color));
        }

        return guiGraphics.drawString(font, text, x, y, color, true);
    }

    public static int drawString(GuiGraphics graphics, Font font, @Nullable String text, float x, float y, int color, boolean dropShadow) {
        PoseStack pose = graphics.pose();
        if (text == null) {
            return 0;
        } else {
            int i = font.drawInBatch(text, x, y, color, dropShadow, pose.last().pose(), ((GuiGraphicsAccessor) graphics).legacyTweaks$getBufferSource(), Font.DisplayMode.NORMAL, 0, 15728880, font.isBidirectional());
            ((GuiGraphicsAccessor) graphics).legacyTweaks$flushIfManaged();
            return i;
        }
    }
}

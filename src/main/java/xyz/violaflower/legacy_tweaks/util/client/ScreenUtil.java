package xyz.violaflower.legacy_tweaks.util.client;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

public class ScreenUtil {
    public static MultiBufferSource.BufferSource guiBufferSourceOverride = null;

    public static boolean isLargeGui() {
        Window screenWindow = Minecraft.getInstance().getWindow();
        return screenWindow.getGuiScale() == screenWindow.calculateScale(0, Minecraft.getInstance().isEnforceUnicode()) || screenWindow.getGuiScale() == 0;
    }

    public static void renderPanorama(GuiGraphics guiGraphics, int width, int height, float fade, float partialTick) {
        guiGraphics.blit(Sprites.PANORAMA_DAY, 0, 0, (float) (Minecraft.getInstance().options.panoramaSpeed().get() * Util.getMillis() * (1 / 60f)), 0, width, height, (int) (height * 5.75) /*preserve aspect ratio*/, height);
    }

    public static double setBestGuiScale() {
        Window screenWindow = Minecraft.getInstance().getWindow();
        int lastGuiScale = screenWindow.calculateScale(0, Minecraft.getInstance().isEnforceUnicode());
        if (screenWindow.getScreenHeight() > 1080) {
            lastGuiScale = lastGuiScale - 2;

        } else if (screenWindow.getScreenHeight() >= 720) {
            lastGuiScale = lastGuiScale - 1;
        }
        return Math.max(1, lastGuiScale);
    }

    public static boolean is720p() {
        Window screenWindow = Minecraft.getInstance().getWindow();
        return screenWindow.getScreenHeight() >= 720 && screenWindow.getScreenHeight() < 1080;
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
}

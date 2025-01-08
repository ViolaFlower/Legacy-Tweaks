package xyz.violaflower.legacy_tweaks.util;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

public class ScreenUtil {

    public static boolean isLargeGui() {
        Window screenWindow = Minecraft.getInstance().getWindow();
        return screenWindow.getGuiScale() == screenWindow.calculateScale(0, Minecraft.getInstance().isEnforceUnicode());
    }

    public static void renderPanorama(GuiGraphics guiGraphics, int width, int height, float fade, float partialTick) {
        guiGraphics.blit(Sprites.PANORAMA_DAY, 0, 0, (float) (Minecraft.getInstance().options.panoramaSpeed().get() * Util.getMillis() * (1 / 60f)), 0, width, height, (int) (height * 5.75) /*preserve aspect ratio*/, height);
    }

    public static double setBestGuiScale() {
        Window screenWindow = Minecraft.getInstance().getWindow();
        int lastGuiScale = screenWindow.calculateScale(0, Minecraft.getInstance().isEnforceUnicode());
        if (screenWindow.getScreenHeight() > 1080) {
            return lastGuiScale - 2;

        } else if (screenWindow.getScreenHeight() >= 720) {
            return lastGuiScale - 1;
        } else {
            return lastGuiScale;
        }
    }
}

package xyz.violaflower.legacy_tweaks.util;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;

// TODO this should be in the client package
public class ScreenUtil {

    public static boolean isLargeGui() {
        Window screenWindow = Minecraft.getInstance().getWindow();
        return Minecraft.getInstance().options.guiScale().get() == 0 || Minecraft.getInstance().options.guiScale().get() ==  screenWindow.calculateScale(0, Minecraft.getInstance().isEnforceUnicode());
    }
}

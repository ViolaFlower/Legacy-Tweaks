package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.util.ScreenUtil;

public class LegacyScreen extends Screen {
    protected LegacyScreen(Component title) {
        super(title);
    }

    public int getButtonWidth() {
        if (ScreenUtil.isLargeGui()) {
            return 150;
        } else {
            return 225;
        }
    }

    public int getFrameWidth() {
        if (ScreenUtil.isLargeGui()) {
            return 160;
        } else {
            return 210;
        }
    }

    public int getButtonHeight() {
        return 20;
    }

    public int getButtonHeightPos() {
        if (ScreenUtil.isLargeGui()) {
            return 65;
        } else {
            return 125;
        }
    }
}

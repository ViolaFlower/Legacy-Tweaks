package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;

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

    // TODO ~~double check if this is even safe; may be better to go with a different system later on~~
    // TODO: update, this is not safe - Jab125
    public void tick() {
//        Minecraft.getInstance().resizeDisplay();
//        this.init();
        super.tick();
    }

    @Override
    protected void renderPanorama(GuiGraphics guiGraphics, float f) {
        PANORAMA.render(guiGraphics, this.width, this.height, 1, f);
    }
}

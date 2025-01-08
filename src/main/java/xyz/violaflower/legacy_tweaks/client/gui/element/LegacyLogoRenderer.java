package xyz.violaflower.legacy_tweaks.client.gui.element;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.RandomSource;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

public class LegacyLogoRenderer {
    private final boolean showEasterEgg = (double) RandomSource.create().nextFloat() < 1.0E-4;
    private final boolean keepLogoThroughFade;

    public LegacyLogoRenderer(boolean keepLogoThroughFade) {
        this.keepLogoThroughFade = keepLogoThroughFade;
    }

    public void renderLogo(GuiGraphics guiGraphics, int screenWidth, float transparency) {
        this.renderLogo(guiGraphics, screenWidth, transparency, getLogoHeightPos());
    }

    public void renderLogo(GuiGraphics guiGraphics, int screenWidth, float transparency, int height) {
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.keepLogoThroughFade ? 1.0F : transparency);
        RenderSystem.enableBlend();
        int i = screenWidth / 2 - getLogoWidth()/2;
        guiGraphics.blit(this.showEasterEgg ? Sprites.EASTER_EGG_LOGO : Sprites.MINECRAFT_LOGO, i, height, 0.0F, 0.0F, getLogoWidth(), getLogoHeight(), getLogoWidth(), getLogoTextureHeight());
        int j = screenWidth / 2 - getLogoTextureHeight();
        int k = height + getEditionHeightPos();
        guiGraphics.blit(Sprites.MINECRAFT_EDITION, j, k, 0.0F, 0.0F, getEditionWidth(), getEditionHeight(), getEditionWidth(), getEditionTextureHeight());
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    }

    private static int getLogoHeightPos() {
        if (ScreenUtil.isLargeGui()) {
            return 10;
        } else {
            return 30;
        }
    }

    private static int getEditionHeightPos() {
        if (ScreenUtil.isLargeGui()) {
            return getLogoHeight() - 4;
        } else {
            return getLogoHeight() - 7;
        }
    }

    private static int getLogoWidth() {
        if (ScreenUtil.isLargeGui()) {
            return 160;
        } else {
            return 256;
        }
    }

    private static int getLogoHeight() {
        if (ScreenUtil.isLargeGui()) {
            return 27;
        } else {
            return 44;
        }
    }

    private static int getLogoTextureHeight() {
        if (ScreenUtil.isLargeGui()) {
            return 40;
        } else {
            return 64;
        }
    }

    private static int getEditionWidth() {
        if (ScreenUtil.isLargeGui()) {
            return 80;
        } else {
            return 128;
        }
    }

    private static int getEditionHeight() {
        if (ScreenUtil.isLargeGui()) {
            return 9;
        } else {
            return 14;
        }
    }

    private static int getEditionTextureHeight() {
        if (ScreenUtil.isLargeGui()) {
            return 10;
        } else {
            return 16;
        }
    }
}

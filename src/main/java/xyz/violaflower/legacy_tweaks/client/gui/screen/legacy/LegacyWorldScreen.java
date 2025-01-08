package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.storage.LevelStorageSource;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class LegacyWorldScreen extends LegacyScreen {
    private final Screen parent;
    private FrameLayout frameLayout;
    private FrameLayout innerFrameLayout;

    protected LegacyWorldScreen(Screen parent) {
        super(Component.empty());
        this.parent = parent;
    }

    @Override
    public int getButtonSpacing() {
        return 0;
    }

    @Override
    public int getButtonHeight() {
        return 25;
    }

    @Override
    public int getFrameWidth() {
        if (ScreenUtil.isLargeGui()) {
            return 180;
        } else {
            return 300;
        }
    }

    @Override
    protected void init() {
        super.init();
        frameLayout = new FrameLayout(getFrameWidth(), getFrameHeight());
        //frameLayout.newChildLayoutSettings().alignVerticallyTop();
        frameLayout.defaultChildLayoutSetting().align(0.5f, 0).padding(5);
        innerFrameLayout = new FrameLayout(getFrameWidth() - 23, getFrameHeight() - 23);
        innerFrameLayout.defaultChildLayoutSetting().alignVerticallyTop().padding(5);
        LevelStorageSource.LevelCandidates cd = this.minecraft.getLevelSource().findLevelCandidates();
        java.util.List<net.minecraft.world.level.storage.LevelSummary> summaries = null;
        try {
            summaries = this.minecraft.getLevelSource().loadLevelSummaries(cd).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        LinearLayout linearLayout = LinearLayout.vertical().spacing(4);
        {
            LinearLayout layout = LinearLayout.vertical().spacing(0);
            summaries.forEach(shart -> {
                // Should have the icon on the side of the button... can't do that yet.
                layout.addChild(Button.builder(Component.literal(shart.getLevelName()), button -> {
                    // TODO: ALLOW LOADING WORLD
                }).size(getButtonWidthAlt(), getButtonHeight()).build());
            });
            innerFrameLayout.addChild(layout);
        }

        linearLayout.addChild(innerFrameLayout);
        frameLayout.addChild(linearLayout);
        frameLayout.visitWidgets(this::addRenderableWidget);
        repositionElements();
    }

    @Override
    protected void repositionElements() {
        frameLayout.arrangeElements();
        FrameLayout.centerInRectangle(frameLayout, 0, 0, width, height);
    }

    @Override
    public int getButtonWidth() {
        return getFrameWidth() - 23 - 5;
    }

    public int getButtonWidthAlt() {
        if (ScreenUtil.isLargeGui()) {
            return 140;
        } else {
            return 190;
        }
    }

    @Override
    public void onClose() {
        minecraft.setScreen(parent);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
//		super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
//		System.out.println(frameLayout.getWidth());
        guiGraphics.blitSprite(Sprites.TOP_NAV_LEFT, frameLayout.getX() + frameLayout.getWidth() / 3, frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
        guiGraphics.blitSprite(Sprites.TOP_NAV_LEFT, frameLayout.getX() + frameLayout.getWidth() / 3 * 2, frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
        guiGraphics.blitSprite(Sprites.BACKGROUND_LOC, frameLayout.getX(), frameLayout.getY(), frameLayout.getWidth(), frameLayout.getHeight());
        guiGraphics.blitSprite(Sprites.INSET_BACKGROUND, frameLayout.getX() + 5, frameLayout.getY() + 5, innerFrameLayout.getWidth() + 9, innerFrameLayout.getHeight() + 9);
        guiGraphics.blitSprite(Sprites.TOP_NAV_LEFT, frameLayout.getX(), frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderPanorama(guiGraphics, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}

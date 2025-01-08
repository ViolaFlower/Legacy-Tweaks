package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

import java.util.Objects;

// playground for testing gui things
public class LegacyWorldScreen extends LegacyScreen {
    private final Screen parent;
    private final FrameLayout frameLayout;
    private final FrameLayout innerFrameLayout;
    private LegacyLogoRenderer logoRenderer;

    protected LegacyWorldScreen(Screen parent) {
        super(Component.empty());
        this.parent = parent;
        frameLayout = new FrameLayout(getFrameWidth(), getFrameHeight());
        //frameLayout.newChildLayoutSettings().alignVerticallyTop();
        frameLayout.defaultChildLayoutSetting().align(0.5f, 0).padding(5);
        innerFrameLayout = new FrameLayout(getFrameWidth() - 20, getFrameHeight() - 20);
        innerFrameLayout.defaultChildLayoutSetting().alignVerticallyTop().padding(5);
        this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, () -> new LegacyLogoRenderer(false));
    }

    @Override
    public void tick() {
        return;
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
        LinearLayout linearLayout = LinearLayout.vertical().spacing(getButtonSpacing());
        linearLayout.addChild(Button.builder(Component.literal("Button 1"), button -> {}).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Component.literal("Button 2"), button -> {}).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Component.literal("Button 3"), button -> {}).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Component.literal("Button 4"), button -> {}).size(getButtonWidth(), getButtonHeight()).build());

        innerFrameLayout.addChild(linearLayout);
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
        if (ScreenUtil.isLargeGui()) {
            return 202;
        } else {
            return 270;
        }
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
        guiGraphics.blitSprite(Sprites.INSET_BACKGROUND, frameLayout.getX() + 9, frameLayout.getY() + 9, innerFrameLayout.getWidth(), innerFrameLayout.getHeight());
        guiGraphics.blitSprite(Sprites.TOP_NAV_LEFT, frameLayout.getX(), frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderPanorama(guiGraphics, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        logoRenderer.renderLogo(guiGraphics, this.width, 1);
    }
}

package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.options;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyScreen;
import xyz.violaflower.legacy_tweaks.util.client.screen.ScreenUtil;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

import java.util.Objects;

public class LegacyGraphicsScreen extends LegacyScreen {
    private final Screen parent;
    private FrameLayout frameLayout;
    private FrameLayout innerFrameLayout;
    private LegacyLogoRenderer logoRenderer;

    public LegacyGraphicsScreen(Screen parent) {
        super(Component.empty());
        this.parent = parent;

        this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, LegacyLogoRenderer::getLegacyLogoRenderer);
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        this.clearFocus();
        frameLayout = new FrameLayout(getFrameWidth(), 0);
        //frameLayout.newChildLayoutSettings().alignVerticallyTop();
        frameLayout.defaultChildLayoutSetting().align(0.5f, 0).padding(5);
        innerFrameLayout = new FrameLayout(getFrameWidth() - 10, 0);
        innerFrameLayout.defaultChildLayoutSetting().alignVerticallyTop().padding(5);
        LinearLayout linearLayout = LinearLayout.vertical().spacing(getButtonSpacing());

        /* Requirements:
         * Checkbox Component
         */
        linearLayout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {
            button.setMessage(Lang.ScreenNotImplemented.WARN.get());
        }).size(getButtonWidth(), getButtonHeight()).build());

        /* Requirements:
         * Checkbox Component
         */
        linearLayout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {
            button.setMessage(Lang.ScreenNotImplemented.WARN.get());
        }).size(getButtonWidth(), getButtonHeight()).build());

        /* Requirements:
         * Slider Component
         */
        linearLayout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {
            button.setMessage(Lang.ScreenNotImplemented.WARN.get());
        }).size(getButtonWidth(), getButtonHeight()).build());

        /* Requirements:
         * Minigames
         * Checkbox Component
         */
        linearLayout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {
            button.setMessage(Lang.ScreenNotImplemented.WARN.get());
        }).size(getButtonWidth(), getButtonHeight()).build());

        /* Requirements:
         * Minigames
         * Checkbox Component
         */
        linearLayout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {
            button.setMessage(Lang.ScreenNotImplemented.WARN.get());
        }).size(getButtonWidth(), getButtonHeight()).build());


        linearLayout.addChild(innerFrameLayout);
        frameLayout.addChild(linearLayout);
        frameLayout.visitWidgets(this::addRenderableWidget);
        repositionElements();
    }

    @Override
    public int getButtonSpacing() {
        return 4;
    }

    @Override
    protected void repositionElements() {
        if (this.frameLayout == null) return;
        frameLayout.arrangeElements();
        FrameLayout.centerInRectangle(frameLayout, 0, 0, width, height);
    }

    @Override
    public int getButtonWidth() {
        if (ScreenUtil.isLargeGui()) {
            return 150;
        } else {
            return 200;
        }
    }

    @Override
    public void onClose() {
        assert minecraft != null; // bruh wtf is this on about
        minecraft.setScreen(parent);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.blitSprite(Sprites.BACKGROUND_LOC, frameLayout.getX(), frameLayout.getY(), frameLayout.getWidth(), frameLayout.getHeight());
        guiGraphics.blitSprite(Sprites.INSET_BACKGROUND, innerFrameLayout.getX(), innerFrameLayout.getY(), innerFrameLayout.getWidth(), innerFrameLayout.getHeight());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        logoRenderer.renderLogo(guiGraphics, this.width, 1);
    }
}

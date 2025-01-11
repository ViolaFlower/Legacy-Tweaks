package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.CreditsAndAttributionScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.screens.options.SkinCustomizationScreen;
import net.minecraft.client.gui.screens.options.controls.ControlsScreen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import net.minecraft.client.gui.screens.Screen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

import java.util.Objects;

/// @deprecated See the data-screens branch
@Deprecated(forRemoval = true)
public class LegacyHelpOptionsScreen extends LegacyScreen {
    private final Screen parent;
    private FrameLayout frameLayout;
    private LegacyLogoRenderer logoRenderer;

    protected LegacyHelpOptionsScreen(Screen parent) {
        super(Component.empty());
        this.parent = parent;
        this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, LegacyLogoRenderer::getLegacyLogoRenderer);
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        this.clearFocus();
        frameLayout = new FrameLayout();
        LinearLayout linearLayout = LinearLayout.vertical().spacing(getButtonSpacing());
        linearLayout.addChild(Button.builder(Lang.HelpOptionsScreen.CHANGE_SKIN.get(), button -> setScreen(something -> new SkinCustomizationScreen(this, Minecraft.getInstance().options))).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Lang.HelpOptionsScreen.HOW_TO_PLAY.get(), button -> setScreen(something -> new LegacyNotImplementedScreen(this))).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Lang.HelpOptionsScreen.CONTROLS.get(), button -> setScreen(something -> new ControlsScreen(this, Minecraft.getInstance().options))).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Lang.HelpOptionsScreen.SETTINGS.get(), button -> setScreen(something -> Tweaks.LEGACY_UI.legacyOptions.isEnabled() ? new LegacyOptionsScreen(this) : new OptionsScreen(this, Minecraft.getInstance().options))).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Lang.HelpOptionsScreen.CREDITS.get(), button -> setScreen(something -> new CreditsAndAttributionScreen(this))).size(getButtonWidth(), getButtonHeight()).build());

        frameLayout.addChild(linearLayout);
        frameLayout.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }

    @Override
    protected void repositionElements() {
        if (frameLayout == null) return;
        frameLayout.setMinWidth(this.width);
        frameLayout.setY(getButtonHeightPos());
        frameLayout.arrangeElements();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderPanorama(guiGraphics, f);
        super.render(guiGraphics, i, j, f);
        logoRenderer.renderLogo(guiGraphics, this.width, 1);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
    }

    @Override
    public void onClose() {
        minecraft.setScreen(parent);
    }
}

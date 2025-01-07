package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.client.gui.screen.LTScreen;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.options.LegacyGraphicsScreen;

import java.util.Objects;
import java.util.function.Function;

public class LegacyOptionsScreen extends LegacyScreen {
	private FrameLayout frameLayout;
	private LegacyLogoRenderer logoRenderer;

	public LegacyOptionsScreen() {
		super(Component.empty());

		this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, () -> new LegacyLogoRenderer(false));
	}

	/* Dexrn says:
	* There should probably be an easy way for other mods to add their own buttons
	 */
	@Override
	protected void init() {
		super.init();
		this.clearWidgets();
		this.clearFocus();
		frameLayout = new FrameLayout();
		LinearLayout linearLayout = LinearLayout.vertical().spacing(21/4);
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.gameOptions"), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.audio"), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.graphics"), button -> setScreen(LegacyGraphicsScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.userInterface"), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.title.ltbutton"), button -> setScreen(LTScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.newSettings"), button -> {
			Minecraft.getInstance().setScreen(new OptionsScreen(new LegacyTitleScreen(), Minecraft.getInstance().options));
		}).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.reset"), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).size(getButtonWidth(), getButtonHeight()).build());

		frameLayout.addChild(linearLayout);
		frameLayout.visitWidgets(this::addRenderableWidget);
		this.repositionElements();
	}

	private void setScreen(Function<Screen, Screen> screen) {
		Minecraft.getInstance().setScreen(screen.apply(this));
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
}

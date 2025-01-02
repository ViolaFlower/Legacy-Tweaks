package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.client.gui.screen.LTScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

import java.util.Objects;
import java.util.function.Function;

public class LegacyOptionsScreen extends Screen {
	private FrameLayout frameLayout;
	private LogoRenderer logoRenderer;

	public LegacyOptionsScreen() {
		super(Component.empty());

		this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, () -> new LogoRenderer(false));
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
		LinearLayout linearLayout = LinearLayout.vertical().spacing(4);
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.gameOptions"), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).width(200).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.audio"), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).width(200).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.graphics"), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).width(200).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.userInterface"), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).width(200).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.title.ltbutton"), button -> setScreen(LTScreen::new)).width(200).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.newSettings"), button -> {
			Minecraft.getInstance().setScreen(new OptionsScreen(new LegacyTitleScreen(), Minecraft.getInstance().options));
		}).width(200).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.optionsScreen.buttons.reset"), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).width(200).build());

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
		frameLayout.setY(height/2-144/2);
		frameLayout.arrangeElements();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {
		this.renderPanorama(guiGraphics, f);
		super.render(guiGraphics, i, j, f);
		logoRenderer.renderLogo(guiGraphics, this.width, 1);
	}

	@Override
	protected void renderPanorama(GuiGraphics guiGraphics, float f) {
		PANORAMA.render(guiGraphics, this.width, this.height, 1, f);
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
	}
}

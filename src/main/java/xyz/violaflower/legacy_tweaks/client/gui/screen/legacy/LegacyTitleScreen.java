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

public class LegacyTitleScreen extends Screen {
	private FrameLayout frameLayout;
	private LogoRenderer logoRenderer;

	public LegacyTitleScreen() {
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
		LinearLayout linearLayout = LinearLayout.vertical().spacing(3);
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.playButton"), button -> setScreen(SelectWorldScreen::new)).width(225).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.minigamesButton"), button -> setScreen(JoinMultiplayerScreen::new)).width(225).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.leaderboardsButton"), button -> setScreen(LTScreen::new)).width(225).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.optionsButton"), button -> {
			Minecraft.getInstance().setScreen(new OptionsScreen(this, Minecraft.getInstance().options));
		}).width(225).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.storeButton"), button -> setScreen(LegacyTestScreen::new)).width(225).build());
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.launchNewMinecraftButton"), button -> {
			Tweaks.LEGACY_UI.legacyTitleScreen.set(false);
			Minecraft.getInstance().reloadResourcePacks();
			new Thread(() -> {
				long l = System.currentTimeMillis() + 2000;
				while (System.currentTimeMillis() < l);
				Minecraft.getInstance().tell(() -> Minecraft.getInstance().setScreen(new TitleScreen()));
			}).start();
		}).width(225).build());
		if (Tweaks.LEGACY_UI.showQuitButton.isOn()) {
			linearLayout.addChild(Button.builder(Component.translatable("menu.quit"), button -> {
				this.minecraft.stop();
			}).width(225).build());
		}
		//this.linearLayout.visitWidgets(this::addRenderableWidget);
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
		frameLayout.arrangeElements();
		FrameLayout.centerInRectangle(frameLayout, 0, 0, width, height);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {
		this.renderPanorama(guiGraphics, f);
		super.render(guiGraphics, i, j, f);
		logoRenderer.renderLogo(guiGraphics, this.width, 1, 10);
	}

	@Override
	protected void renderPanorama(GuiGraphics guiGraphics, float f) {
		PANORAMA.render(guiGraphics, this.width, this.height, 1, f);
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
	}
}

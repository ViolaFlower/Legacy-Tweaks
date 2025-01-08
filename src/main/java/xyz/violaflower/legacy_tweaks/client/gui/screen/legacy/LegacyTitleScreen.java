package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.enums.PlayGameScreen;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

import java.util.Objects;
import java.util.function.Function;

public class LegacyTitleScreen extends LegacyScreen {
	private FrameLayout frameLayout;
	private LegacyLogoRenderer logoRenderer;
	private SplashRenderer splashRenderer;
	public LegacyTitleScreen() {
		super(Component.empty());

		this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, LegacyLogoRenderer::getLegacyLogoRenderer);
	}

	/* Dexrn says:
	* There should probably be an easy way for other mods to add their own buttons
	 */
	@Override
	protected void init() {
		super.init();
		this.clearWidgets();
		this.clearFocus();

		if (this.splashRenderer == null) {
			// oh no! minecraft can be null?!?!?!?! /s
			this.splashRenderer = Minecraft.getInstance().getSplashManager().getSplash();
		}

		frameLayout = new FrameLayout();
		LinearLayout linearLayout = LinearLayout.vertical().spacing(getButtonSpacing());
		linearLayout.addChild(Button.builder(Lang.TitleScreen.PLAY_GAME.get(), button -> setScreen(Tweaks.LEGACY_UI.legacyPlayGameScreenTweak.playGameScreenType.get() == PlayGameScreen.DISABLED ? SelectWorldScreen::new : LegacyPlayGameScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		if (Tweaks.LEGACY_UI.legacyTitleScreen.showMinigamesButton.isOn()) {
			linearLayout.addChild(Button.builder(Lang.TitleScreen.MINI_GAMES.get(), button -> setScreen(JoinMultiplayerScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		} if (Tweaks.LEGACY_UI.legacyTitleScreen.showLeaderboardsButton.isOn()) {
			linearLayout.addChild(Button.builder(Lang.TitleScreen.LEADERBOARDS.get(), button -> setScreen(LegacyNotImplementedScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		}
		linearLayout.addChild(Button.builder(Lang.TitleScreen.HELP_OPTIONS.get(), button -> {
			if (Tweaks.LEGACY_UI.legacyHelpOptionsScreen.useLegacyHelpOptionsScreen.isOn()) {
				Minecraft.getInstance().setScreen(new LegacyHelpOptionsScreen(this));
			} else {
				Minecraft.getInstance().setScreen(new LegacyOptionsScreen(this));
			}
		}).size(getButtonWidth(), getButtonHeight()).build());
		if (Tweaks.LEGACY_UI.legacyTitleScreen.showMinecraftStoreButton.isOn()) {
			linearLayout.addChild(Button.builder(Lang.TitleScreen.STORE.get(), button -> setScreen(LegacyTestScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		} if (Tweaks.LEGACY_UI.legacyTitleScreen.showNewMinecraftButton.isOn()) {
			linearLayout.addChild(Button.builder(Lang.TitleScreen.JAVA.get(), button -> {
				Tweaks.LEGACY_UI.legacyTitleScreen.legacyTitleScreen.set(false);
				Minecraft.getInstance().reloadResourcePacks();
				new Thread(() -> {
					long l = System.currentTimeMillis() + 2000;
					while (System.currentTimeMillis() < l) ;
					Minecraft.getInstance().tell(() -> Minecraft.getInstance().setScreen(new TitleScreen()));
				}).start();
			}).size(getButtonWidth(), getButtonHeight()).build());
		}
		if (Tweaks.LEGACY_UI.legacyTitleScreen.showQuitButton.isOn()) {
			linearLayout.addChild(Button.builder(Lang.TitleScreen.QUIT.get(), button -> {
				this.minecraft.stop();
			}).size(getButtonWidth(), getButtonHeight()).build());
		}
		//this.linearLayout.visitWidgets(this::addRenderableWidget);

		frameLayout.addChild(linearLayout);
		frameLayout.visitWidgets(this::addRenderableWidget);
		this.repositionElements();
	}

	@Override
	protected void repositionElements() {
		if (frameLayout == null) return;
		frameLayout.setMinWidth(this.width);
		frameLayout.setY(getButtonHeightPos());
//		frameLayout.setY(height/2-165/3);
		frameLayout.arrangeElements();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {
		this.renderPanorama(guiGraphics, f);
		super.render(guiGraphics, i, j, f);
		logoRenderer.renderLogo(guiGraphics, this.width, 1);
		if (this.splashRenderer != null) {
			this.splashRenderer.render(guiGraphics, width, this.font, 0xffffff00);
		}
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
	}
}

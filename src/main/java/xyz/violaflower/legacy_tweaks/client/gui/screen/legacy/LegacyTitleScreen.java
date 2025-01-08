package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import com.mojang.blaze3d.vertex.PoseStack;
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
import xyz.violaflower.legacy_tweaks.helper.tweak.world.EyeCandyHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

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
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.playButton"), button -> setScreen(SelectWorldScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		if (Tweaks.LEGACY_UI.legacyTitleScreen.showMinigamesButton.isOn()) {
			linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.minigamesButton"), button -> setScreen(JoinMultiplayerScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		} if (Tweaks.LEGACY_UI.legacyTitleScreen.showLeaderboardsButton.isOn()) {
			linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.leaderboardsButton"), button -> setScreen(LegacyNotImplementedScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		}
		linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.optionsButton"), button -> {
			Minecraft.getInstance().setScreen(new LegacyOptionsScreen());
		}).size(getButtonWidth(), getButtonHeight()).build());
		if (Tweaks.LEGACY_UI.legacyTitleScreen.showMinecraftStoreButton.isOn()) {
			linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.storeButton"), button -> setScreen(LegacyTestScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		} if (Tweaks.LEGACY_UI.legacyTitleScreen.showNewMinecraftButton.isOn()) {
			linearLayout.addChild(Button.builder(Component.translatable("lt.legacyScreens.titleScreen.buttons.launchNewMinecraftButton"), button -> {
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
			linearLayout.addChild(Button.builder(Component.translatable("menu.quit"), button -> {
				this.minecraft.stop();
			}).size(getButtonWidth(), getButtonHeight()).build());
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
			if (!isLargeGui()) {
				EyeCandyHelper.setCompactText(true);
				EyeCandyHelper.setLegacyTextShadows(false);
				PoseStack pose = guiGraphics.pose();
				pose.pushPose();
				pose.translate(-40, -23, 0);
				pose.scale(1.5f, 1.5f, 0);
				this.splashRenderer.render(guiGraphics, (int) (width / 1.5), this.font, 0xffffff00);
				pose.popPose();
				EyeCandyHelper.setLegacyTextShadows(true);
				EyeCandyHelper.setCompactText(false);
			} else {
				PoseStack pose = guiGraphics.pose();
				pose.pushPose();
				pose.translate(-8, -8, 0);
				pose.scale(0.8f, 0.8f, 1);
				EyeCandyHelper.setCompactText(true);
				EyeCandyHelper.setLegacyTextShadows(false);
				this.splashRenderer.render(guiGraphics, (int) (width / 0.8), this.font, 0xffffff00);
				EyeCandyHelper.setLegacyTextShadows(true);
				EyeCandyHelper.setCompactText(false);
				pose.popPose();
			}
		}
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
	}
}

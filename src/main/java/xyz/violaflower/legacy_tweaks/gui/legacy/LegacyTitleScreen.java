package xyz.violaflower.legacy_tweaks.gui.legacy;

import net.minecraft.ChatFormatting;
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
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.gui.LTScreen;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class LegacyTitleScreen extends Screen {
	private final FrameLayout frameLayout;
	private LogoRenderer logoRenderer;

	public LegacyTitleScreen() {
		super(Component.empty());
		frameLayout = new FrameLayout();
		this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, () -> new LogoRenderer(false));
	}

	@Override
	protected void init() {
		super.init();
		LinearLayout linearLayout = LinearLayout.vertical().spacing(2);
		linearLayout.addChild(Button.builder(Component.literal("Play Game"), button -> setScreen(SelectWorldScreen::new)).width(200).build());
		linearLayout.addChild(Button.builder(Component.literal("Mini Games"), button -> setScreen(JoinMultiplayerScreen::new)).width(200).build());
		linearLayout.addChild(Button.builder(Component.literal("Leaderboards"), button -> setScreen(LTScreen::new)).width(200).build());
		linearLayout.addChild(Button.builder(Component.literal("Help & Options"), button -> {
			Minecraft.getInstance().setScreen(new OptionsScreen(this, Minecraft.getInstance().options));
		}).width(200).build());
		linearLayout.addChild(Button.builder(Component.literal("Minecraft Store"), button -> {}).width(200).build());
		linearLayout.addChild(Button.builder(Component.literal("Launch New Minecraft"), button -> {button.setMessage(Component.literal("no.").withStyle(ChatFormatting.RED));}).width(200).build());
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
		frameLayout.setMinWidth(this.width);
		frameLayout.setY(height/2-132/2);
		frameLayout.arrangeElements();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {
		super.render(guiGraphics, i, j, f);
		logoRenderer.renderLogo(guiGraphics, this.width, 1);
	}

	@Override
	protected void renderPanorama(GuiGraphics guiGraphics, float f) {
		PANORAMA.render(guiGraphics, this.width, this.height, 1, f);
	}
}

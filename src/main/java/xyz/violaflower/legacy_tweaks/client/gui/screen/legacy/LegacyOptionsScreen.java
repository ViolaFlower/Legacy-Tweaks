package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import xyz.violaflower.legacy_tweaks.client.gui.screen.config.LTScreen;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.options.LegacyGraphicsScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;
import xyz.violaflower.legacy_tweaks.util.common.sound.SoundUtil;
import xyz.violaflower.legacy_tweaks.util.common.sound.Sounds;

import java.util.Objects;
import java.util.function.Function;

/// @deprecated See the data-screens branch
@Deprecated(forRemoval = true)
public class LegacyOptionsScreen extends LegacyScreen {
	private final Screen parent;
	private FrameLayout frameLayout;
	private LegacyLogoRenderer logoRenderer;

	public LegacyOptionsScreen(Screen parent) {
		super(Component.empty());
		this.parent = parent;

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
		frameLayout = new FrameLayout();
		LinearLayout linearLayout = LinearLayout.vertical().spacing(getButtonSpacing());
		linearLayout.addChild(Button.builder(Lang.OptionsScreen.GAME_OPTIONS.get(), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Lang.OptionsScreen.AUDIO.get(), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Lang.OptionsScreen.GRAPHICS.get(), button -> setScreen(LegacyGraphicsScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Lang.OptionsScreen.UI.get(), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Lang.ConfigScreen.TITLE.get(), button -> setScreen(LTScreen::new)).size(getButtonWidth(), getButtonHeight()).build());
		if (Tweaks.LEGACY_UI.legacyOptions.showJavaOptionsButton.isOn()) {
			linearLayout.addChild(Button.builder(Lang.OptionsScreen.JAVA_SETTINGS.get(), button -> {
				Minecraft.getInstance().setScreen(new OptionsScreen(this, Minecraft.getInstance().options));
			}).size(getButtonWidth(), getButtonHeight()).build());
		}
		linearLayout.addChild(Button.builder(Lang.OptionsScreen.RESET.get(), button -> setScreen(something  -> new LegacyNotImplementedScreen(this))).size(getButtonWidth(), getButtonHeight()).build());

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
	public void onClose() {
		if (Tweaks.LEGACY_UI.generalScreenTweaks.useLegacyUISounds.isOn()) SoundUtil.playFullPitchSound(Sounds.BACK, SoundSource.MASTER);
		minecraft.setScreen(parent);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {
		super.render(guiGraphics, i, j, f);
		logoRenderer.renderLogo(guiGraphics, this.width, 1);
	}
}

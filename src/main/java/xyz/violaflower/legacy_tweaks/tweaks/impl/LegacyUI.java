package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class LegacyUI extends Tweak {

	public final BooleanOption legacyTitleScreen;
	public final BooleanOption legacyPanorama;
	public final BooleanOption showQuitButton;

	public LegacyUI() {
		setTweakID("legacyUI");
		setTweakName(Component.translatable("lt.tweaks.legacyui"));
		setTweakAuthor("Jab125");
		setEnabled(true);
		setGroup();
		legacyTitleScreen = addBooleanOption(Component.translatable("lt.tweaks.legacyui.option.legacytitlescreen"));
		legacyTitleScreen.set(true); // true by default
		showQuitButton = addBooleanOption(Component.translatable("lt.tweaks.legacyui.option.showquitbutton"));
		showQuitButton.set(true);
		legacyPanorama = addBooleanOption(Component.translatable("lt.tweaks.legacyui.option.legacypanorama"));
		legacyPanorama.set(true);
	}
}

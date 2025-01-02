package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class LegacyUI extends Tweak {

	public final BooleanOption legacyTitleScreen;
	public final BooleanOption legacyPanorama;
	public final BooleanOption showQuitButton;

	public LegacyUI() {
		setTweakID("Legacy UI");
		setTweakAuthor("Jab125");
		setEnabled(true);
		setGroup();
		legacyTitleScreen = addBooleanOption("Legacy Title Screen");
		legacyTitleScreen.set(true); // true by default
		showQuitButton = addBooleanOption("Show Quit Button");
		showQuitButton.set(true);
		legacyPanorama = addBooleanOption("Legacy Panorama");
		legacyPanorama.set(true);
	}
}

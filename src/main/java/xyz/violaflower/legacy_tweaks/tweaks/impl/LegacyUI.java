package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class LegacyUI extends Tweak {

	public final BooleanOption legacyTitleScreen;
	public final BooleanOption legacyPanorama;
	public final BooleanOption showQuitButton;

	public LegacyUI() {
		setTweakID("legacyui");
		setTweakAuthor("Jab125");
		setEnabled(true);
		setGroup();
		legacyTitleScreen = addBooleanOption("legacytitlescreen");
		legacyTitleScreen.set(true); // true by default
		showQuitButton = addBooleanOption("showquitbutton");
		showQuitButton.set(true);
		legacyPanorama = addBooleanOption("legacypanorama");
		legacyPanorama.set(true);
	}
}

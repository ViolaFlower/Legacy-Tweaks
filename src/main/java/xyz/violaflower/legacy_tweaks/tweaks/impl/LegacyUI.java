package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class LegacyUI extends Tweak {

	public final BooleanOption legacyTitleScreen;

	public LegacyUI() {
		setTweakID("Legacy UI");
		setTweakAuthor("Jab125");
		setEnabled(true);
		setGroup();
		legacyTitleScreen = addBooleanOption("Legacy Title Screen");
		legacyTitleScreen.set(true); // true by default
	}
}

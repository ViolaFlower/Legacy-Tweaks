package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class LegacyUI extends Tweak {

	public final BooleanOption legacyTitleScreen;
	public final BooleanOption legacyPanorama;
	public final BooleanOption showQuitButton;

	public LegacyUI() {
		super("legacyUI", true);
		setTweakAuthor("Jab125");
		setGroup();
		legacyTitleScreen = addBooleanOption("legacyTitleScreen", true);
		showQuitButton = addBooleanOption("showQuitButton", true);
		legacyPanorama = addBooleanOption("legacyPanorama", true);
	}
}

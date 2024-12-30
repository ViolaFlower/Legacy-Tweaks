package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Gamma extends Tweak {

	private final SliderOption potency;

	public Gamma() {
		setTweakID("gamma");
		// TODO better name
		potency = addSliderOption("potency", 0, 1);
	}

	public SliderOption getPotency() {
		return potency;
	}
}

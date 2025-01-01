package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.client.renderer.PostChain;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Gamma extends Tweak {

	public final DoubleSliderOption potency;
	// note this probably needs redoing for 1.21.4
	public static PostChain gammaEffect;

	public Gamma() {
		setTweakID("gamma");
		// TODO better name
		potency = addSliderOption("potency", 0D, 1D);
		potency.set(0.5d);
	}

	public static PostChain getGammaEffect() {
		return gammaEffect;
	}
}

package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class EyeCandy extends Tweak {
	public EyeCandy() {
		setTweakID("Eye Candy");
		setGroup();
		setTweakAuthor("Jab125");
		setTweakDescription("Various cosmetic changes");
		addSubTweak(new LegacyTextShadows());
	}
	public static class LegacyTextShadows extends Tweak {
		public final IntSliderOption shadowOffset;
		public LegacyTextShadows() {
			setTweakID("Legacy Text Shadows");
			setTweakAuthor("Jab125");
			setTweakDescription("Makes shadows consistent with the screen's actual size, not the scaled size.");
			shadowOffset = addSliderOption("shadowOffset", 0, 10);
			shadowOffset.set(1);
		}
	}
}

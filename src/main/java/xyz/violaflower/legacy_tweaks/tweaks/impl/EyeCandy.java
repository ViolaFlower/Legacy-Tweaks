package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;

public class EyeCandy extends Tweak {
	public final LegacyTextShadows legacyTextShadows;
	public final FineTunedUIScale fineTunedUIScale;
	public final SunsetColors sunsetColors;
	public final Models models;
	public final Tweak smallerStars;
	public final Tweak oldButton;
	public final Tweak legacyWaterColors;

	public EyeCandy() {
		setTweakID("eyecandy");
		setGroup();
		setTweakAuthor("Jab125");
		addSubTweak(legacyTextShadows = new LegacyTextShadows());
		addSubTweak(fineTunedUIScale = new FineTunedUIScale());
		addSubTweak(sunsetColors = new SunsetColors());
		addSubTweak(models = new Models());
		addSubTweak(smallerStars = new TweakBuilder("smallerstars").authors("Permdog99").setDefaultEnabled(true).build());
		addSubTweak(oldButton = new TweakBuilder("oldbutton").authors("Jab125").setDefaultEnabled(true).build());
		addSubTweak(legacyWaterColors = new TweakBuilder("legacywatercolors").authors("Jab125", "dexrn").setDefaultEnabled(true).onToggled(() -> {
			//noinspection ConstantValue
			if (Minecraft.getInstance().levelRenderer != null) {
				Minecraft.getInstance().levelRenderer.allChanged();
			}
		}).build());
	}
	public static class LegacyTextShadows extends Tweak {
		public final IntSliderOption shadowOffset;
		public LegacyTextShadows() {
			setTweakID("legacytextshadows");
			setTweakAuthor("Jab125");
			shadowOffset = addSliderOption("shadowoffset", 0, 10);
			shadowOffset.set(1);
		}
	}
	public static class FineTunedUIScale extends Tweak {
		public final DoubleSliderOption uiScale;
		public FineTunedUIScale() {
			setTweakID("finetuneduiscale");
			setTweakAuthor("Jab125");
			setEnabled(false, false);
			uiScale = addSliderOption("uiScale", 0.8D, 5D);
			uiScale.set(1d);
			uiScale.setConsumer(d -> {if (isEnabled()) Minecraft.getInstance().resizeDisplay();});
		}

		@Override
		public void onToggled() {
			Minecraft.getInstance().resizeDisplay();
		}
	}

	public static class SunsetColors extends Tweak {
		public final IntSliderOption sunsetColors; // TODO change to Enum

		public SunsetColors() {
			setTweakID("sunsetcolors");
			setTweakAuthor("Permdog99");

			sunsetColors = addSliderOption("sunsetcolors", 1, 3);
			sunsetColors.set(3);
		}
	}

	public static class Models extends Tweak {
		public final Tweak legacyWitchHat;
		public final Tweak legacyFireworkModel;

		public Models() {
			setTweakID("models");
			setTweakAuthor("Permdog99");

			addSubTweak(legacyWitchHat = new TweakBuilder("legacywitchhat").authors("Permdog99, Legacy4J 1.7.5-beta").setDefaultEnabled(false).build());
			addSubTweak(legacyFireworkModel = new TweakBuilder("legacyfirework").authors("Permdog99, Legacy4J 1.7.5-beta").setDefaultEnabled(true).build());
		}
	}
}

package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;
import xyz.violaflower.legacy_tweaks.tweaks.enums.SunsetRiseColors;

public class EyeCandy extends Tweak {
	public final LegacyTextShadows legacyTextShadows;
	public final FineTunedUIScale fineTunedUIScale;
	public final SunsetColors sunsetColors;
	public final Models models;
	public final Tweak smallerStars;
	public final Tweak oldButton;
	public final Tweak legacyWaterColors;

	public EyeCandy() {
		super("eyeCandy", true);
		setGroup();
		setTweakAuthor("Jab125");
		addSubTweak(legacyTextShadows = new LegacyTextShadows());
		addSubTweak(fineTunedUIScale = new FineTunedUIScale());
		addSubTweak(sunsetColors = new SunsetColors());
		addSubTweak(models = new Models());
		addSubTweak(smallerStars = new TweakBuilder("smallerStars").authors("Permdog99").setDefaultEnabled(true).build());
		addSubTweak(oldButton = new TweakBuilder("oldButton").authors("Jab125").setDefaultEnabled(true).build());
		addSubTweak(legacyWaterColors = new TweakBuilder("legacyWaterColors").authors("Jab125", "dexrn").setDefaultEnabled(true).onToggled(() -> {
			//noinspection ConstantValue
			if (Minecraft.getInstance().levelRenderer != null) {
				Minecraft.getInstance().levelRenderer.allChanged();
			}
		}).build());
	}
	public static class LegacyTextShadows extends Tweak {
		public final IntSliderOption shadowOffset;
		public LegacyTextShadows() {
			super("legacyTextShadows", true);
			setTweakAuthor("Jab125");
			shadowOffset = addSliderOption("shadowOffset", 1, 0, 10);
		}
	}
	public static class FineTunedUIScale extends Tweak {
		public final DoubleSliderOption uiScale;
		public FineTunedUIScale() {
			super("fineTunedUIScale", false);
			setTweakAuthor("Jab125");
			uiScale = addSliderOption("uiScale", 1D, 0.8D, 5D);
			uiScale.setConsumer(d -> {if (isEnabled()) Minecraft.getInstance().resizeDisplay();});
		}

		@Override
		public void onToggled() {
			if (Minecraft.getInstance().options == null) return;
			Minecraft.getInstance().resizeDisplay();
		}
	}

	public static class SunsetColors extends Tweak {
		public final EnumSliderOption<SunsetRiseColors> sunsetColors;

		public SunsetColors() {
			super("sunsetColors", true);
			setTweakAuthor("Permdog99");
			sunsetColors = addSliderOption("Sunset Colors", enumProvider(SunsetRiseColors.JAVA, SunsetRiseColors::values, SunsetRiseColors::toString, SunsetRiseColors::getTitle));
		}
	}

	public static class Models extends Tweak {
		public final Tweak legacyWitchHat;
		public final Tweak legacyFireworkModel;

		public Models() {
			super("models", true);
			setTweakAuthor("Permdog99");

			addSubTweak(legacyWitchHat = new TweakBuilder("legacyWitchHat").authors("Permdog99, Legacy4J 1.7.5-beta").setDefaultEnabled(false).build());
			addSubTweak(legacyFireworkModel = new TweakBuilder("legacyFirework").authors("Permdog99, Legacy4J 1.7.5-beta").setDefaultEnabled(true).build());
		}
	}
}

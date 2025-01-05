package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
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
		setTweakID("eyeCandy");
		setTweakName(Component.translatable("lt.tweaks.eyecandy"));
		setGroup();
		setTweakAuthor("Jab125");
		setTweakDescription(Component.translatable("lt.tweaks.eyecandy.description"));
		addSubTweak(legacyTextShadows = new LegacyTextShadows());
		addSubTweak(fineTunedUIScale = new FineTunedUIScale());
		addSubTweak(sunsetColors = new SunsetColors());
		addSubTweak(models = new Models());
		addSubTweak(smallerStars = new TweakBuilder("smallerStars").name(Component.translatable("lt.tweaks.eyecandy.smallerstars")).description(Component.translatable("lt.tweaks.eyecandy.smallerstars.description")).authors("Permdog99").setDefaultEnabled(true).build());
		addSubTweak(oldButton = new TweakBuilder("oldButton").name(Component.translatable("lt.tweaks.eyecandy.oldbutton")).description(Component.translatable("lt.tweaks.eyecandy.oldbutton.description")).authors("Jab125").setDefaultEnabled(true).build());
		addSubTweak(legacyWaterColors = new TweakBuilder("legacyWaterColors").name(Component.translatable("lt.tweaks.eyecandy.legacywatercolors")).description(Component.translatable("lt.tweaks.eyecandy.legacywatercolors.description")).authors("Jab125", "dexrn").setDefaultEnabled(true).onToggled(() -> {
			//noinspection ConstantValue
			if (Minecraft.getInstance().levelRenderer != null) {
				Minecraft.getInstance().levelRenderer.allChanged();
			}
		}).build());
	}
	public static class LegacyTextShadows extends Tweak {
		public final IntSliderOption shadowOffset;
		public LegacyTextShadows() {
			setTweakID("legacyTextShadows");
			setTweakName(Component.translatable("lt.tweaks.eyecandy.legacytextshadows"));
			setTweakAuthor("Jab125");
			setTweakDescription(Component.translatable("lt.tweaks.eyecandy.legacytextshadows.description"));
			shadowOffset = addSliderOption("shadowOffset", 0, 10);
			shadowOffset.set(1);
		}
	}
	public static class FineTunedUIScale extends Tweak {
		public final DoubleSliderOption uiScale;
		public FineTunedUIScale() {
			setTweakID("fineTunedUIScale");
			setTweakName(Component.translatable("lt.tweaks.eyecandy.finetuneduiscale"));
			setTweakAuthor("Jab125");
			setTweakDescription(Component.translatable("lt.tweaks.eyecandy.finetuneduiscale.description").withStyle(ChatFormatting.RED));
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
			setTweakID("sunsetColors");
			setTweakName(Component.translatable("lt.tweaks.eyecandy.sunsetcolors"));
			setTweakAuthor("Permdog99");
			setTweakDescription(Component.translatable("lt.tweaks.eyecandy.sunsetcolors.description"));

			sunsetColors = addSliderOption("sunsetColors", 1, 3);
			sunsetColors.set(3);
		}
	}

	public static class Models extends Tweak {
		public final Tweak legacyWitchHat;
		public final Tweak legacyFireworkModel;

		public Models() {
			setTweakID("models");
			setTweakName(Component.translatable("lt.tweaks.eyecandy.models"));
			setTweakAuthor("Permdog99");
			setTweakDescription(Component.translatable("lt.tweaks.eyecandy.models.description"));

			addSubTweak(legacyWitchHat = new TweakBuilder("legacyWitchHat").name(Component.translatable("lt.tweaks.eyecandy.models.legacywitchhat")).description(Component.translatable("lt.tweaks.eyecandy.models.legacywitchhat.description")).authors("Permdog99, Legacy4J 1.7.5-beta").setDefaultEnabled(false).build());
			addSubTweak(legacyFireworkModel = new TweakBuilder("legacyFirework").name(Component.translatable("lt.tweaks.eyecandy.models.legacyfireworkmodel")).description(Component.translatable("lt.tweaks.eyecandy.models.legacyfireworkmodel.description")).authors("Permdog99, Legacy4J 1.7.5-beta").setDefaultEnabled(true).build());
		}
	}
}

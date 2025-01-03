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
		setTweakID("Eye Candy");
		setGroup();
		setTweakAuthor("Jab125");
		setTweakDescription("Various cosmetic changes");
		addSubTweak(legacyTextShadows = new LegacyTextShadows());
		addSubTweak(fineTunedUIScale = new FineTunedUIScale());
		addSubTweak(sunsetColors = new SunsetColors());
		addSubTweak(models = new Models());
		addSubTweak(smallerStars = new TweakBuilder("Smaller Stars").description("Makes the stars smaller, like LCE").authors("Permdog99").setDefaultEnabled(true).build());
		addSubTweak(oldButton = new TweakBuilder("Pre 1.13 button").description("Reverts buttons to their pre 1.14 state").authors("Jab125").setDefaultEnabled(true).build());
		addSubTweak(legacyWaterColors = new TweakBuilder("Legacy Water Colors").description("앚졸딩도알lk노wn라w소f아v이앋이온텔에잇노왜압에엣호울d베압렏오f뤼잊윙살엗오옷말l독엗잊f앋릳t렙옫요ff텍로운d텝에어f조울세f리엣안위왭엦아웃엡에엣돈t잘에w핱움안s틴깃임봈입레").authors("Jab125", "dexrn").setDefaultEnabled(true).build());
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
	public static class FineTunedUIScale extends Tweak {
		public final DoubleSliderOption uiScale;
		public FineTunedUIScale() {
			setTweakID("Fine Tuned UI Scale");
			setTweakAuthor("Jab125");
			setTweakDescription(ChatFormatting.RED + "Don't use this unless you know what you're doing!");
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
			setTweakID("Sunset Colors");
			setTweakAuthor("Permdog99");
			setTweakDescription("[Currently TU5 does nothing] Sunset & sunrise colors, based on Xbox 360 LCE update version.");

			sunsetColors = addSliderOption("sunsetColors", 1, 3);
			sunsetColors.set(3);
		}
	}

	public static class Models extends Tweak {
		public final Tweak legacyWitchHat;
		public final Tweak legacyFireworkModel;

		public Models() {
			setTweakID("Models");
			setTweakAuthor("Permdog99");
			setTweakDescription("Legacy-styled models for entities and other objects.");

			addSubTweak(legacyWitchHat = new TweakBuilder("Legacy Witch Hat").description("Legacy-styled witch hat.").authors("Permdog99, Legacy4J 1.7.5-beta").setDefaultEnabled(false).build());
			addSubTweak(legacyFireworkModel = new TweakBuilder("Legacy Firework").description("Legacy-styled firework model").authors("Permdog99, Legacy4J 1.7.5-beta").setDefaultEnabled(true).build());
		}
	}
}

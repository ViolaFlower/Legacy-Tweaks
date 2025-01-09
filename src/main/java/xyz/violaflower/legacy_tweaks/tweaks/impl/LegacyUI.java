package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.enums.PlayGameScreen;

public class LegacyUI extends Tweak {
	public final LegacyTitleScreenTweak legacyTitleScreen;
	public final LegacyOptionsTweak legacyOptions;
	public final GeneralScreenTweaks generalScreenTweaks;
	public final LegacyPlayGameScreenTweak legacyPlayGameScreenTweak;
	public final LegacyHelpOptionsScreenTweak legacyHelpOptionsScreen;
	public final GuiHudTweaks guiHudTweaks;

	public LegacyUI() {
		super("legacyUI", true);
		setTweakAuthor("Jab125", "Permdog99");
		setGroup();

		addSubTweak(guiHudTweaks = new GuiHudTweaks());
		addSubTweak(generalScreenTweaks = new GeneralScreenTweaks());
		addSubTweak(legacyTitleScreen = new LegacyTitleScreenTweak());
		addSubTweak(legacyOptions = new LegacyOptionsTweak());
		addSubTweak(legacyPlayGameScreenTweak = new LegacyPlayGameScreenTweak());
		addSubTweak(legacyHelpOptionsScreen = new LegacyHelpOptionsScreenTweak());
	}

	public static class GuiHudTweaks extends Tweak {
		public final IntSliderOption hudDistance;
		public final IntSliderOption hudScale;
		public final IntSliderOption hudOpacity;

		public final BooleanOption applyHudDistanceHotbar;
		public final BooleanOption applyHudDistanceChat;
		public final BooleanOption applyHudDistanceScoreboard;
		public final BooleanOption applyHudDistanceEffects;
		public final BooleanOption applyHudDistancePaperDoll;
		public final BooleanOption applyHudDistanceBossHealth;
		public final BooleanOption applyHudDistanceToast;
		public final BooleanOption applyHudDistanceOther;

		public final BooleanOption applyHudScaleHotbar;
		public final BooleanOption applyHudScaleChat;
		public final BooleanOption applyHudScaleScoreboard;
		public final BooleanOption applyHudScaleEffects;
		public final BooleanOption applyHudScalePaperDoll;
		public final BooleanOption applyHudScaleBossHealth;
		public final BooleanOption applyHudScaleToast;
		public final BooleanOption applyHudScaleOther;

		public final BooleanOption inGameTooltips;
		public final IntSliderOption otherDistanceX;
		public final IntSliderOption otherDistanceY;
		public final BooleanOption useLegacyHotbarTexture;
		public final BooleanOption legacyExperienceBar;
		public final BooleanOption legacyEffects;
		public final BooleanOption legacyScoreboard;
		public final BooleanOption hideHudInScreen;

		public GuiHudTweaks() {
			super("guiHudTweaks", true);
			setTweakAuthor("Permdog99");

			hudDistance = addSliderOption("hudDistance", 100, 0, 100);
			hudScale = addSliderOption("hudScale", 3, 1, 3);
			hudOpacity = addSliderOption("hudOpacity", 80, 0, 100);

			applyHudDistanceHotbar = addBooleanOption("applyHudDistanceHotbar", true);
			applyHudDistanceChat = addBooleanOption("applyHudDistanceChat", true);
			applyHudDistanceScoreboard = addBooleanOption("applyHudDistanceScoreboard", true);
			applyHudDistanceEffects = addBooleanOption("applyHudDistanceEffects", true);
			applyHudDistancePaperDoll = addBooleanOption("applyHudDistancePaperDoll", true);
			applyHudDistanceBossHealth = addBooleanOption("applyHudDistanceBossHealth", true);
			applyHudDistanceToast = addBooleanOption("applyHudDistanceToast", true);
			applyHudDistanceOther = addBooleanOption("applyHudDistanceOther", true);

			applyHudScaleHotbar = addBooleanOption("applyHudScaleHotbar", true);
			applyHudScaleChat = addBooleanOption("applyHudScaleChat", false);
			applyHudScaleScoreboard = addBooleanOption("applyHudScaleScoreboard", false);
			applyHudScaleEffects = addBooleanOption("applyHudScaleEffects", false);
			applyHudScalePaperDoll = addBooleanOption("applyHudScalePaperDoll", false);
			applyHudScaleBossHealth = addBooleanOption("applyHudScaleBossHealth", false);
			applyHudScaleToast = addBooleanOption("applyHudScaleBossHealth", false);
			applyHudScaleOther = addBooleanOption("applyHudScaleOther", false);

			otherDistanceX = addSliderOption("otherDistanceX", 100, 0, 100);
			otherDistanceY = addSliderOption("otherDistanceY", 100, 0, 100);
			inGameTooltips = addBooleanOption("inGameTooltips", true);
			useLegacyHotbarTexture = addBooleanOption("useLegacyHotbarTexture", true);
			legacyExperienceBar = addBooleanOption("legacyExperienceBar", true);
			legacyEffects = addBooleanOption("legacyEffects", true);
			legacyScoreboard = addBooleanOption("legacyScoreboard", true);
			hideHudInScreen = addBooleanOption("hideHudInScreen", true);
		}
	}

	public static class GeneralScreenTweaks extends Tweak {
		public final BooleanOption legacyPanorama;
		public final BooleanOption useLegacyTitleLogo;

		public GeneralScreenTweaks() {
            super("generalScreenTweaks", true);
			setTweakAuthor("Jab125", "Permdog99");

			legacyPanorama = addBooleanOption("legacyPanorama", true);
			useLegacyTitleLogo = addBooleanOption("useLegacyTitleLogo", true);
        }
	}

	public static class LegacyTitleScreenTweak extends Tweak {
		public final BooleanOption legacyTitleScreen;
		public final BooleanOption showNewMinecraftButton;
		public final BooleanOption showMinigamesButton;
		public final BooleanOption showQuitButton;
		public final BooleanOption showLeaderboardsButton;
		public final BooleanOption showMinecraftStoreButton;

		public LegacyTitleScreenTweak() {
            super("legacyTitleScreen", true);
			setTweakAuthor("Jab125", "Permdog99");

			legacyTitleScreen = addBooleanOption("legacyTitleScreen", true);
			showNewMinecraftButton = addBooleanOption("showNewMinecraftButton", false);
			showMinigamesButton = addBooleanOption("showMinigamesButton", true);
			showQuitButton = addBooleanOption("showQuitButton", true);
			showLeaderboardsButton = addBooleanOption("showLeaderboardsButton", false);
			showMinecraftStoreButton = addBooleanOption("showMinecraftStoreButton", false);
        }
	}

	public static class LegacyOptionsTweak extends Tweak {
		public final BooleanOption showJavaOptionsButton;

		public LegacyOptionsTweak() {
			super("legacyOptionsScreen", true);
			setTweakAuthor("Jab125", "Permdog99");

			showJavaOptionsButton = addBooleanOption("showJavaOptionsButton", false);
		}
	}

	public static class LegacyPlayGameScreenTweak extends Tweak {
		public final EnumSliderOption<PlayGameScreen> playGameScreenType;

		public LegacyPlayGameScreenTweak() {
			super("legacyPlayGameScreen", true);
			setTweakAuthor("dexrn", "Permdog99");

			playGameScreenType = addSliderOption("playGameScreenType", enumProvider(PlayGameScreen.DISABLED, PlayGameScreen::values, PlayGameScreen::toString, PlayGameScreen::getComponent));
		}
	}

	public static class LegacyHelpOptionsScreenTweak extends Tweak {
		public final BooleanOption useLegacyHelpOptionsScreen;

		public LegacyHelpOptionsScreenTweak() {
			super("legacyHelpOptionsScreen", true);
			setTweakAuthor("Permdog99");

			useLegacyHelpOptionsScreen = addBooleanOption("useLegacyHelpOptions", true);
		}
	}
}

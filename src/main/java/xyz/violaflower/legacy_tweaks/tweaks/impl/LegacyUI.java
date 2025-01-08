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

	public LegacyUI() {
		super("legacyUI", true);
		setTweakAuthor("Jab125", "Permdog99");
		setGroup();

		addSubTweak(generalScreenTweaks = new GeneralScreenTweaks());
		addSubTweak(legacyTitleScreen = new LegacyTitleScreenTweak());
		addSubTweak(legacyOptions = new LegacyOptionsTweak());
		addSubTweak(legacyPlayGameScreenTweak = new LegacyPlayGameScreenTweak());
		addSubTweak(legacyHelpOptionsScreen = new LegacyHelpOptionsScreenTweak());
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

			useLegacyHelpOptionsScreen = addBooleanOption("useLegacyHelpOptions", true);
		}
	}
}

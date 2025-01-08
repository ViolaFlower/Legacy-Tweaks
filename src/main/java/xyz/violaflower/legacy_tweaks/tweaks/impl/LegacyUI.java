package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class LegacyUI extends Tweak {
	public final LegacyTitleScreenTweak legacyTitleScreen;
	public final LegacyOptionsTweak legacyOptions;
	public final BooleanOption legacyPanorama;


	public LegacyUI() {
		super("legacyUI", true);
		setTweakAuthor("Jab125", "Permdog99");
		setGroup();

		legacyPanorama = addBooleanOption("legacyPanorama", true);
		addSubTweak(legacyTitleScreen = new LegacyTitleScreenTweak());
		addSubTweak(legacyOptions = new LegacyOptionsTweak());
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
}

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
		public final GeneralTweaks generalTweaks;
		public final HotbarTweaks hotbarTweaks;
		public final ChatTweaks chatTweaks;
		public final BossBarTweaks bossBarTweaks;
		public final EffectsTweaks effectsTweaks;
		public final ModdedTweaks moddedTweaks;
		public final ScoreboardTweaks scoreboardTweaks;
		public final PaperDollTweaks paperDollTweaks;

		public GuiHudTweaks() {
			super("guiHudTweaks", true);
			setTweakAuthor("Permdog99");

			addSubTweak(generalTweaks = new GeneralTweaks());
			addSubTweak(hotbarTweaks = new HotbarTweaks());
			addSubTweak(chatTweaks = new ChatTweaks());
			addSubTweak(paperDollTweaks = new PaperDollTweaks());
			addSubTweak(scoreboardTweaks = new ScoreboardTweaks());
			addSubTweak(bossBarTweaks = new BossBarTweaks());
			addSubTweak(effectsTweaks = new EffectsTweaks());
			addSubTweak(moddedTweaks = new ModdedTweaks());
		}

		public static class GeneralTweaks extends Tweak {
			public final IntSliderOption screenSpacing;
			public final IntSliderOption hudScale;
			public final IntSliderOption hudOpacity;
			public final BooleanOption applyScreenSpacingToast;
			public final BooleanOption applyHudScaleToast;
			public final BooleanOption inGameTooltips;
			public final BooleanOption hideHudInScreen;
			public final BooleanOption applyHudScaleTooltip;
			public final BooleanOption vignette;
			public final BooleanOption forceDisableHudScale;
			public final BooleanOption applyHudScaleCrosshair;

			public GeneralTweaks() {
				super("generalTweaks", true);
				setTweakAuthor("Permdog99");

				screenSpacing = addSliderOption("screenSpacing", 100, 0, 100);
				hudScale = addSliderOption("hudScale", 2, 1, 3);
				hudOpacity = addSliderOption("hudOpacity", 80, 0, 100);
				applyScreenSpacingToast = addBooleanOption("applyScreenSpacingToast", true);
				applyHudScaleToast = addBooleanOption("applyHudScaleBossHealth", false);
				inGameTooltips = addBooleanOption("inGameTooltips", true);
				hideHudInScreen = addBooleanOption("hideHudInScreen", true);
				applyHudScaleTooltip = addBooleanOption("applyHudScaleTooltip", false);
				vignette = addBooleanOption("vignette", false);
				forceDisableHudScale = addBooleanOption("forceDisableHudScale", false);
				applyHudScaleCrosshair = addBooleanOption("applyHudScaleCrosshair", true);
			}
		}

		public static class HotbarTweaks extends Tweak {
			public final BooleanOption legacyHotbar;
			public final BooleanOption applyScreenSpacingHotbar;
			public final BooleanOption applyHudScaleHotbar;
			public final BooleanOption useLegacyHotbarTexture;
			public final BooleanOption legacyExperienceText;
			public final BooleanOption fastHealthBlink;
			public final BooleanOption legacyItemOverlay;

			public HotbarTweaks() {
				super("healthTweaks", true);
				setTweakAuthor("Permdog99");

				legacyHotbar = addBooleanOption("legacyHotbar", true);
				legacyExperienceText = addBooleanOption("legacyExperienceText", true);
				applyScreenSpacingHotbar = addBooleanOption("applyScreenSpacingHotbar", true);
				applyHudScaleHotbar = addBooleanOption("applyHudScaleHotbar", true);
				useLegacyHotbarTexture = addBooleanOption("useLegacyHotbarTexture", true);
				fastHealthBlink = addBooleanOption("fastHealthBlink", true);
				legacyItemOverlay = addBooleanOption("legacyItemOverlay", true);
			}
		}

		public static class ChatTweaks extends Tweak {
			public final BooleanOption legacyChat;
			public final BooleanOption grayChatBackground;
			public final BooleanOption messageWidthSpansScreen;
			public final BooleanOption legacyMessageHeight;
			public final BooleanOption applyScreenSpacingChat;
			public final BooleanOption applyHudScaleChat;

			public ChatTweaks() {
				super("chatTweaks", true);
				setTweakAuthor("Permdog99", "DexrnZacAttack", "Jab125");

				legacyChat = addBooleanOption("legacyChat", true);
				grayChatBackground = addBooleanOption("grayChatBackground", true);
				applyScreenSpacingChat = addBooleanOption("applyScreenSpacingChat", true);
				applyHudScaleChat = addBooleanOption("applyHudScaleChat", false);
				messageWidthSpansScreen = addBooleanOption("messageWidthSpansScreen", true);
				legacyMessageHeight = addBooleanOption("legacyMessageHeight", true);
			}
		}

		public static class BossBarTweaks extends Tweak {
			public final BooleanOption legacyBossBar;
			public final BooleanOption applyScreenSpacingBossHealth;
			public final BooleanOption applyHudScaleBossHealth;

			public BossBarTweaks() {
				super("bossBarTweaks", true);
				setTweakAuthor("Permdog99");

				legacyBossBar = addBooleanOption("legacyBossBar", true);
				applyScreenSpacingBossHealth = addBooleanOption("applyScreenSpacingBossHealth", true);
				applyHudScaleBossHealth = addBooleanOption("applyHudScaleBossHealth", false);
			}
		}

		public static class EffectsTweaks extends Tweak {
			public final BooleanOption legacyEffects;
			public final BooleanOption applyScreenSpacingEffects;
			public final BooleanOption applyHudScaleEffects;

			public EffectsTweaks() {
				super("effectsTweaks", true);
				setTweakAuthor("Permdog99");

				legacyEffects = addBooleanOption("legacyEffects", true);
				applyScreenSpacingEffects = addBooleanOption("applyScreenSpacingEffects", true);
				applyHudScaleEffects = addBooleanOption("applyHudScaleEffects", false);
			}
		}

		public static class ModdedTweaks extends Tweak {
			public final IntSliderOption otherScreenSpacingX;
			public final IntSliderOption otherScreenSpacingY;
			public final BooleanOption applyScreenSpacingOther;
			public final BooleanOption applyHudScaleOther;

			public ModdedTweaks() {
				super("moddedTweaks", true);
				setTweakAuthor("Permdog99");

				otherScreenSpacingX = addSliderOption("otherScreenSpacingX", 100, 0, 100);
				otherScreenSpacingY = addSliderOption("otherScreenSpacingY", 100, 0, 100);
				applyScreenSpacingOther = addBooleanOption("applyScreenSpacingOther", true);
				applyHudScaleOther = addBooleanOption("applyHudScaleOther", false);
			}
		}

		public static class ScoreboardTweaks extends Tweak {
			public final BooleanOption legacyScoreboard;
			public final BooleanOption applyScreenSpacingScoreboard;
			public final BooleanOption applyHudScaleScoreboard;

			public ScoreboardTweaks() {
				super("scoreboardTweaks", true);
				setTweakAuthor("Permdog99");

				legacyScoreboard = addBooleanOption("legacyScoreboard", true);
				applyScreenSpacingScoreboard = addBooleanOption("applyScreenSpacingScoreboard", true);
				applyHudScaleScoreboard = addBooleanOption("applyHudScaleScoreboard", false);
			}
		}

		public static class PaperDollTweaks extends Tweak {
			public final BooleanOption showPaperDoll;
			public final BooleanOption smoothPaperDoll;
			public final BooleanOption applyScreenSpacingPaperDoll;
			public final BooleanOption applyHudScalePaperDoll;

			public PaperDollTweaks() {
				super("paperDollTweaks", true);
				setTweakAuthor("Permdog99");

				showPaperDoll = addBooleanOption("showPaperDoll", true);
				smoothPaperDoll = addBooleanOption("smoothPaperDoll", false);
				applyScreenSpacingPaperDoll = addBooleanOption("applyScreenSpacingPaperDoll", false);
				applyHudScalePaperDoll = addBooleanOption("applyHudScalePaperDoll", false);
			}
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

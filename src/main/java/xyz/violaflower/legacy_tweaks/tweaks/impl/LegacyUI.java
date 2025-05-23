package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.client.Minecraft;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.enums.PlayGameScreen;

public class LegacyUI extends Tweak {
	public final LegacyTitleScreenTweak legacyTitleScreen;
	public final LegacyOptionsTweak legacyOptions;
	public final GeneralScreenTweaks generalScreenTweaks;
	public final LegacyPlayGameScreenTweak legacyPlayGameScreenTweak;
	public final LegacyPauseScreenTweak legacyPauseScreenTweak;
	public final LegacyHelpOptionsScreenTweak legacyHelpOptionsScreen;
	public final LegacyInventoryScreenTweak legacyInventoryScreenTweak;
	public final LegacyContainerScreenTweak legacyContainerScreenTweak;
	public final GuiHudTweaks guiHudTweaks;
	public final F3Info f3Info;
	public final WindowTitle windowTitle;

	public LegacyUI() {
		super("legacyUI", true);
		setTweakAuthor("Jab125", "AzaleaCatgirl99");
		setGroup();

		addSubTweak(guiHudTweaks = new GuiHudTweaks());
		addSubTweak(generalScreenTweaks = new GeneralScreenTweaks());
		addSubTweak(legacyTitleScreen = new LegacyTitleScreenTweak());
		addSubTweak(legacyOptions = new LegacyOptionsTweak());
		addSubTweak(legacyPauseScreenTweak = new LegacyPauseScreenTweak());
		addSubTweak(legacyPlayGameScreenTweak = new LegacyPlayGameScreenTweak());
		addSubTweak(legacyHelpOptionsScreen = new LegacyHelpOptionsScreenTweak());
		addSubTweak(legacyInventoryScreenTweak = new LegacyInventoryScreenTweak());
		addSubTweak(legacyContainerScreenTweak = new LegacyContainerScreenTweak());
		addSubTweak(f3Info = new F3Info());
		addSubTweak(windowTitle = new WindowTitle());
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
			setTweakAuthor("AzaleaCatgirl99");

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
			public final BooleanOption thirdPersonCrosshair;

			public GeneralTweaks() {
				super("generalTweaks", true);
				setTweakAuthor("AzaleaCatgirl99");

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
				thirdPersonCrosshair = addBooleanOption("thirdPersonCrosshair", false);
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
			public final BooleanOption smallerItemIcons;

			public HotbarTweaks() {
				super("hotbarTweaks", true);
				setTweakAuthor("AzaleaCatgirl99");

				legacyHotbar = addBooleanOption("legacyHotbar", true);
				legacyExperienceText = addBooleanOption("legacyExperienceText", true);
				applyScreenSpacingHotbar = addBooleanOption("applyScreenSpacingHotbar", true);
				applyHudScaleHotbar = addBooleanOption("applyHudScaleHotbar", true);
				useLegacyHotbarTexture = addBooleanOption("useLegacyHotbarTexture", true);
				fastHealthBlink = addBooleanOption("fastHealthBlink", true);
				legacyItemOverlay = addBooleanOption("legacyItemOverlay", true);
				smallerItemIcons = addBooleanOption("smallerItemIcons", true);
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
				setTweakAuthor("AzaleaCatgirl99", "DexrnZacAttack", "Jab125");

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
				setTweakAuthor("AzaleaCatgirl99");

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
				setTweakAuthor("AzaleaCatgirl99");

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
				setTweakAuthor("AzaleaCatgirl99");

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
				setTweakAuthor("AzaleaCatgirl99");

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
				setTweakAuthor("AzaleaCatgirl99");

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
		public final BooleanOption autoFindBestUIScale;
		public final BooleanOption legacyTextShadows;
		public final IntSliderOption legacyTextShadowOffset;
		public final BooleanOption forceDisableFineTunedUIScale;
		public final DoubleSliderOption fineTunedUIScale;
		public final BooleanOption legacyCompactText;
		public final BooleanOption oldButton;
		public final BooleanOption useLegacyUISounds;
		public final BooleanOption fixMacOSRetina;

		public GeneralScreenTweaks() {
            super("generalScreenTweaks", true);
			setTweakAuthor("Jab125", "AzaleaCatgirl99");

			legacyPanorama = addBooleanOption("legacyPanorama", true);
			useLegacyTitleLogo = addBooleanOption("useLegacyTitleLogo", true);
			autoFindBestUIScale = addBooleanOption("autoFindBestUIScale", true);
			legacyTextShadows = addBooleanOption("legacyTextShadows", true);
			legacyTextShadowOffset = addSliderOption("shadowOffset", 1, 0, 10);
			forceDisableFineTunedUIScale = addBooleanOption("forceDisableFineTunedUIScale", true);
			fineTunedUIScale = addSliderOption("fineTunedUIScale", 1D, 0.8D, 5D);
			fineTunedUIScale.setConsumer(d -> {if (isEnabled()) Minecraft.getInstance().resizeDisplay();});
			legacyCompactText = addBooleanOption("legacyCompactText", true);
			oldButton = addBooleanOption("oldButton", true);
			useLegacyUISounds = addBooleanOption("useLegacyUISounds", true);
			fixMacOSRetina = addBooleanOption("fixMacOSRetina", false);
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
			setTweakAuthor("Jab125", "AzaleaCatgirl99");

			legacyTitleScreen = addBooleanOption("legacyTitleScreen", true);
			showNewMinecraftButton = addBooleanOption("showNewMinecraftButton", false);
			showMinigamesButton = addBooleanOption("showMinigamesButton", true);
			showQuitButton = addBooleanOption("showQuitButton", true);
			showLeaderboardsButton = addBooleanOption("showLeaderboardsButton", false);
			showMinecraftStoreButton = addBooleanOption("showMinecraftStoreButton", false);
        }
	}

	public static class LegacyPauseScreenTweak extends Tweak {
		public final BooleanOption useLegacyPauseMenu;
		public LegacyPauseScreenTweak() {
			super("legacyPauseScreen", true);
			setTweakAuthor("AzaleaCatgirl99");

			useLegacyPauseMenu = addBooleanOption("useLegacyPauseMenu", true);
		}
	}

	public static class LegacyOptionsTweak extends Tweak {
		public final BooleanOption showJavaOptionsButton;

		public LegacyOptionsTweak() {
			super("legacyOptionsScreen", true);
			setTweakAuthor("Jab125", "AzaleaCatgirl99");

			showJavaOptionsButton = addBooleanOption("showJavaOptionsButton", false);
		}
	}

	public static class LegacyPlayGameScreenTweak extends Tweak {
		public final EnumSliderOption<PlayGameScreen> playGameScreenType;

		public LegacyPlayGameScreenTweak() {
			super("legacyPlayGameScreen", true);
			setTweakAuthor("dexrn", "AzaleaCatgirl99");

			playGameScreenType = addSliderOption("playGameScreenType", enumProvider(PlayGameScreen.DISABLED, PlayGameScreen::values, PlayGameScreen::toString, PlayGameScreen::getComponent));
		}
	}

	public static class LegacyHelpOptionsScreenTweak extends Tweak {
		public final BooleanOption useLegacyHelpOptionsScreen;

		public LegacyHelpOptionsScreenTweak() {
			super("legacyHelpOptionsScreen", true);
			setTweakAuthor("AzaleaCatgirl99");

			useLegacyHelpOptionsScreen = addBooleanOption("useLegacyHelpOptions", true);
		}
	}

	public static class LegacyInventoryScreenTweak extends Tweak {
		public final BooleanOption useLegacyInventory;
		public final BooleanOption noOffhand;
		public final BooleanOption hideRecipeBook;
		public final BooleanOption classicCrafting;
		public LegacyInventoryScreenTweak() {
			super("legacyInventoryScreen", true);
			setTweakAuthor("AzaleaCatgirl99");
			useLegacyInventory = addBooleanOption("useLegacyInventory", true);
			noOffhand = addBooleanOption("noOffhand", false);
			hideRecipeBook = addBooleanOption("hideRecipeBook", true);
			classicCrafting = addBooleanOption("classicCrafting", false);
		}
	}

	public static class LegacyContainerScreenTweak extends Tweak {
		public final BooleanOption useLegacyGenericContainer;
		public final BooleanOption useLegacyFurnaceContainer;
		public final BooleanOption smallerItemSizes;
		public LegacyContainerScreenTweak() {
			super("legacyContainerScreen", true);
			setTweakAuthor("Jab125", "AzaleaCatgirl99");
			useLegacyGenericContainer = addBooleanOption("useLegacyGenericContainer", true);
			useLegacyFurnaceContainer = addBooleanOption("useLegacyFurnaceContainer", true);
			smallerItemSizes = addBooleanOption("smallerItemSizes", true);
		}
	}

	public static class F3Info extends Tweak {
		public final BooleanOption showEnabledTweaks;

		public F3Info() {
			super("f3Info", true);
			setTweakAuthor("DexrnZacAttack", "Jab125");
			// localize hopefully
			// LOCALISED! - S_N00B
			showEnabledTweaks = addBooleanOption("showEnabledTweaks", true);
		}
	}

	public static class WindowTitle extends Tweak {
		public final ShowTUVersion showTUVersion;
		public WindowTitle() {
			super("windowTitle", true);
			setTweakAuthor("LimeGradient", "Jab125");
			addSubTweak(showTUVersion = new ShowTUVersion());
		}

		public static class ShowTUVersion extends Tweak {
			public ShowTUVersion() {
				super("showTUVersion", false);
			}

			@Override
			public void onToggled() {
				if (Minecraft.getInstance().getWindow() == null) return;
				Minecraft.getInstance().updateTitle();
			}
		}

		@Override
		public void onToggled() {
			if (Minecraft.getInstance().getWindow() == null) return;
			Minecraft.getInstance().updateTitle();
		}
	}
}

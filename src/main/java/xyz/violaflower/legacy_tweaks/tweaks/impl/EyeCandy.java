package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;
import xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapTypes;
import xyz.violaflower.legacy_tweaks.tweaks.enums.SunsetRiseColors;

public class EyeCandy extends Tweak {
	public final SunsetColors sunsetColors;
	public final Models models;
	public final Tweak smallerStars;
	public final Tweak legacyWaterColors;
	public final Gamma gamma;
	public final Mipmapping mipmapping;
	public final LegacyFog legacyFog;

	public EyeCandy() {
		super("eyeCandy", true);
		setGroup();
		setTweakAuthor("Jab125");
		addSubTweak(gamma = new Gamma());
		addSubTweak(sunsetColors = new SunsetColors());
		addSubTweak(models = new Models());
		addSubTweak(smallerStars = new TweakBuilder("smallerStars").authors("Permdog99").setDefaultEnabled(true).build());
		addSubTweak(legacyWaterColors = new TweakBuilder("legacyWaterColors").authors("Jab125", "dexrn").setDefaultEnabled(true).onToggled(() -> {
			//noinspection ConstantValue
			if (Minecraft.getInstance().levelRenderer != null) {
				Minecraft.getInstance().levelRenderer.allChanged();
			}
		}).build());
		addSubTweak(mipmapping = new Mipmapping());
		addSubTweak(legacyFog = new LegacyFog());
	}

	public static class Gamma extends Tweak {
		public final DoubleSliderOption potency;
		// note this probably needs redoing for 1.21.4
		public static PostChain gammaEffect;

		public Gamma() {
			super("gamma", true);
			// TODO better name
			potency = addSliderOption("potency", 0.5D, 0D, 1D);
		}

		public static PostChain getGammaEffect() {
			return gammaEffect;
		}
	}

	public static class SunsetColors extends Tweak {
		public final EnumSliderOption<SunsetRiseColors> sunsetColors;

		public SunsetColors() {
			super("sunsetColors", true);
			setTweakAuthor("AzaleaCatgirl99");
			sunsetColors = addSliderOption("Sunset Colors", enumProvider(SunsetRiseColors.JAVA, SunsetRiseColors::values, SunsetRiseColors::toString, SunsetRiseColors::getComponent));
		}
	}

	public static class Models extends Tweak {
		public final Tweak legacyWitchHat;
		public final Tweak legacyFireworkModel;
		public final Tweak glowEntities;

		public Models() {
			super("models", true);
			setTweakAuthor("AzaleaCatgirl99");

			addSubTweak(glowEntities = new TweakBuilder("glowEntities").authors("Permdog99", "Legacy4J 1.7.5-beta").setDefaultEnabled(true).build());
			addSubTweak(legacyWitchHat = new TweakBuilder("legacyWitchHat").authors("Permdog99", "Legacy4J 1.7.5-beta").setDefaultEnabled(true).onToggled(() ->{
				Minecraft.getInstance().reloadResourcePacks();
			}).build());
			addSubTweak(legacyFireworkModel = new TweakBuilder("legacyFirework").authors("Permdog99", "Legacy4J 1.7.5-beta").setDefaultEnabled(true).build());
		}
	}

	public static class Mipmapping extends Tweak {
		public final MipmapType mipmapType;
		public final Tweak manualMipmapping;
		public final Tweak fullCutoutMips;
		public final Tweak halfCutoutMips;

		public Mipmapping() {
			super("mipmapping", true);
			setTweakAuthor("AzaleaCatgirl99", "Jab125");

			addSubTweak(mipmapType = new MipmapType());
			addSubTweak(manualMipmapping = new TweakBuilder("manualMipmapping").authors("Jab125").setDefaultEnabled(true).onToggled(() -> {
				Minecraft mc = Minecraft.getInstance();
				//noinspection ConstantValue
				if (mc.getResourceManager() != null)
					mc.reloadResourcePacks();
			}).build());

			addSubTweak(fullCutoutMips = new TweakBuilder("fullCutoutMips").authors("Permdog99").setDefaultEnabled(false).onToggled(() -> {
				Minecraft mc = Minecraft.getInstance();
				//noinspection ConstantValue
				if (mc.getResourceManager() != null)
					mc.reloadResourcePacks();
			}).build());
			addSubTweak(halfCutoutMips = new TweakBuilder("halfCutoutMips").authors("Permdog99").setDefaultEnabled(true).onToggled(() -> {
				Minecraft mc = Minecraft.getInstance();
				//noinspection ConstantValue
				if (mc.getResourceManager() != null)
					mc.reloadResourcePacks();
			}).build());
		}

		public static class MipmapType extends Tweak {
			public final EnumSliderOption<MipmapTypes> mipmapType;

			public MipmapType() {
				super("mipmapType", true);
				setTweakAuthor("AzaleaCatgirl99");
				mipmapType = addSliderOption("Mipmap Type", enumProvider(MipmapTypes.JAVA, MipmapTypes::values, MipmapTypes::toString, MipmapTypes::getComponent));
				mipmapType.setConsumer(t -> {
					Minecraft mc = Minecraft.getInstance();
					//noinspection ConstantValue
					if (mc.getResourceManager() != null)
						mc.reloadResourcePacks();
				});
			}
		}
	}

	// TODO find best settings for the Nether and End, based on PS4 and XONE render distance
	public static class LegacyFog extends Tweak {
		public final OverworldFog overworldFog;
		public final NetherFog netherFog;
		public final EndFog endFog;
		public final ModdedFog moddedFog;

		public LegacyFog() {
			super("legacyFog", true);
			setGroup();
			setTweakAuthor("AzaleaCatgirl99");

			addSubTweak(overworldFog = new OverworldFog());
			addSubTweak(netherFog = new NetherFog());
			addSubTweak(endFog = new EndFog());
			addSubTweak(moddedFog = new ModdedFog());
		}


		public static class OverworldFog extends Tweak {
			public final DoubleSliderOption terrainFogStartOverworld;
			public final IntSliderOption terrainFogStopOverworld;

			public OverworldFog() {
				super("overworldFog", true);
				setTweakAuthor("AzaleaCatgirl99");

				terrainFogStartOverworld = addSliderOption("terrainFogStart", 0.25d, 0d, 1d);
				terrainFogStopOverworld = addSliderOption("terrainFogStop", 0, 0, 64);
			}
		}

		public static class NetherFog extends Tweak {
			public final DoubleSliderOption terrainFogStartNether;
			public final IntSliderOption terrainFogStopNether;

			public NetherFog() {
				super("netherFog", true);
				setTweakAuthor("AzaleaCatgirl99");

				terrainFogStartNether = addSliderOption("terrainFogStart", 0.25d, 0d, 1d);
				terrainFogStopNether = addSliderOption("terrainFogStop", 0, 0, 64);
			}
		}

		public static class EndFog extends Tweak {
			public final DoubleSliderOption terrainFogStartEnd;
			public final IntSliderOption terrainFogStopEnd;

			public EndFog() {
				super("endFog", true);
				setTweakAuthor("AzaleaCatgirl99");

				terrainFogStartEnd = addSliderOption("terrainFogStart", 0.25d, 0d, 1d);
				terrainFogStopEnd = addSliderOption("terrainFogStop", 0, 0, 64);
			}
		}

		public static class ModdedFog extends Tweak {
			public final DoubleSliderOption terrainFogStartModded;
			public final IntSliderOption terrainFogStopModded;

			public ModdedFog() {
				super("moddedDimensionsFog", true);
				setTweakAuthor("AzaleaCatgirl99");

				terrainFogStartModded = addSliderOption("terrainFogStart", 0.25d, 0d, 1d);
				terrainFogStopModded = addSliderOption("terrainFogStop", 0, 0, 64);
			}
		}
	}
}

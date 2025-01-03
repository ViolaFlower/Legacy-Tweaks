package xyz.violaflower.legacy_tweaks.tweaks.impl.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Locale;

public class EyeCandyClient {
	public static Integer getWaterColor(Biome biome) {
		return getWaterColor(Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.BIOME).getKey(biome));
	}

	public static Integer getWaterFogColor(Biome biome) {
		return getWaterFogColor(Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.BIOME).getKey(biome));
	}

	public static Integer getFogColor(Biome biome) {
		return getFogColor(Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.BIOME).getKey(biome));
	}


	private static Integer getWaterColor(ResourceLocation biomeID) {
		if (biomeID.getNamespace().equals("minecraft")) {
			BiomeColors biomeColors = BiomeColors.safeValueOf(biomeID.getPath().toUpperCase(Locale.ROOT));
			if (biomeColors == null) return null;
			return biomeColors.waterColor;
		}
		return null;
	}

	private static Integer getWaterFogColor(ResourceLocation biomeID) {
		if (biomeID.getNamespace().equals("minecraft")) {
			BiomeColors biomeColors = BiomeColors.safeValueOf(biomeID.getPath().toUpperCase(Locale.ROOT));
			if (biomeColors == null) return null;
			return biomeColors.waterFogColor;
		}
		return null;
	}


	private static Integer getFogColor(ResourceLocation biomeID) {
		if (true) return null; // fog color is messed up
		if (biomeID.getNamespace().equals("minecraft")) {
			BiomeColors biomeColors = BiomeColors.safeValueOf(biomeID.getPath().toUpperCase(Locale.ROOT));
			if (biomeColors == null) return null;
			return biomeColors.fogColor;
		}
		return null;
	}

	enum BiomeColors {
		DEFAULT(0xa544aff5, 0x0044aff5, 0xa50000f5),
		PLAINS(0xa544aff5, 0x0044aff5, 0xa50000f5),
		DESERT(0xa532a598, 0x0044aff5, 0xa5000098),
		COLD_OCEAN(0xa52080c9, 0x0014559b, 0xa50000c9),
		BAMBOO_JUNGLE(0xa514a2c5, 0x0014a2c5, 0xa50000c5),
		STONY_SHORE(0xa50d67bb, 0x000d67bb, 0xa50000bb),
		SNOWY_TAIGA(0xa5205e83, 0x00205e83, 0xa5000083),
		SAVANNA_PLATEAU(0xa52590a8, 0x002590a8, 0xa50000a8),
		WARM_OCEAN(0x8c02b0e5, 0x000289d5, 0x8c000035),
		RIVER(0xa50084ff, 0x000084ff, 0xa50000ff),
		SPARSE_JUNGLE(0xa50d8ae3, 0x000d8ae3, 0xa50000e3),
		SNOWY_BEACH(0xa51463a5, 0x001463a5, 0xa50000a5),
		BIRCH_FOREST(0xa50677ce, 0x000677ce, 0xa50000ce),
		NETHER_WASTES(0xa5905957, 0x00905957, 0xa5000057),
		WARPED_FOREST(0xa5905957, 0x00905957, 0xa5000057),
		CRIMSON_FOREST(0xa5905957, 0x00905957, 0xa5000057),
		SOUL_SAND_VALLEY(0xa5905957, 0x00905957, 0xa5000057),
		BASALT_DELTAS(0xa5905957, 0x00905957, 0xa5000057),
		MUSHROOM_FIELDS(0xa58a8997, 0x008a8997, 0x97a50000);
		final Integer waterColor;
		final Integer waterFogColor;
		final Integer fogColor;
		BiomeColors(Integer waterColor, Integer waterFogColor, Integer fogColor) {
			this.waterColor = waterColor;
			this.waterFogColor = waterFogColor;
			this.fogColor = fogColor;
		}

		public static BiomeColors safeValueOf(String string) {
			try {
				return valueOf(string);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
	}
}

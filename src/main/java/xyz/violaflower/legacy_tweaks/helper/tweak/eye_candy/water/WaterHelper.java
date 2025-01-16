package xyz.violaflower.legacy_tweaks.helper.tweak.eye_candy.water;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Locale;

public class WaterHelper {
    public static Integer getWaterColor(Biome biome) {
		if (Minecraft.getInstance().level == null) return null;
		return getWaterColor(Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.BIOME).getKey(biome));
	}

	public static Integer getWaterFogColor(Biome biome) {
		if (Minecraft.getInstance().level == null) return null;
		return getWaterFogColor(Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.BIOME).getKey(biome));
	}

	public static Integer getFogColor(Biome biome) {
		if (Minecraft.getInstance().level == null) return null;
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
		// TODO Fix ocean biome colors and figure out colors for biomes that didn't exist in LE
		DEFAULT(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		BAMBOO_JUNGLE(0xa514a2c5, 0x0014a2c5, 0xa575ABFF),
		BEACH(0xa5157cab, 0x00157cab, 0xa579A7FF),
		BIRCH_FOREST(0xa50677ce, 0x000677ce, 0xa57AA5FF),
		OLD_GROWTH_BIRCH_FOREST(0xa50a74c4, 0x000a74c4, 0xa57AA5FF),
		SNOWY_BEACH(0xa51463a5, 0x001463a5, 0xa580A1FF),
		COLD_OCEAN(0xa52080c9, 0x0014559b, 0xa57FA2FF),
		SNOWY_TAIGA(0xa5205e83, 0x00205e83, 0xa5839EFF),
		DEEP_COLD_OCEAN(0xa52080c9, 0x0014559b, 0xa57FA2FF),
		DEEP_FROZEN_OCEAN(0xa52570B5, 0x002570B5, 0xa580A1FF),
		DEEP_LUKEWARM_OCEAN(0xa50D96DB, 0x000D96DB, 0xa50000c9),
		DEEP_OCEAN(0xa51787D4, 0x001787D4, 0xa57BA5FF),
		DESERT(0xa532a598, 0x0044aff5, 0xa56FB2FF),
		WINDSWEPT_HILLS(0xa5007BF7, 0x00007BF7, 0xa57EA3FF),
		WINDSWEPT_GRAVELLY_HILLS(0xa50E63AB, 0x000E63AB, 0xa57EA3FF),
		WINDSWEPT_FOREST(0xa50E63AB, 0x000E63AB, 0xa57EA3FF),
		FLOWER_FOREST(0xa520A3CC, 0x0020A3CC, 0xa57AA6FF),
		FOREST(0xa51E97F2, 0x001E97F2, 0xa57AA6FF),
		FROZEN_OCEAN(0xa52570B5, 0x002570B5, 0xa580A1FF),
		FROZEN_RIVER(0xa5185390, 0x00185390, 0xa580A1FF),
		NETHER_WASTES(0xa5905957, 0x00905957, 0xa56FB2FF),
		WARPED_FOREST(0xa5905957, 0x00905957, 0xa56FB2FF),
		CRIMSON_FOREST(0xa5905957, 0x00905957, 0xa56FB2FF),
		SOUL_SAND_VALLEY(0xa5905957, 0x00905957, 0xa56FB2FF),
		BASALT_DELTAS(0xa5905957, 0x00905957, 0xa56FB2FF),
		//ICE_MOUNTAINS(0xa51156a7, 0x001156a7, 0xa580A1FF),
		SNOWY_PLAINS(0xa14559b, 0x0014559b, 0xa580A1FF),
		SNOWY_SLOPES(0xa14559b, 0x0014559b, 0xa580A1FF),
		ICE_SPIKES(0xa14559b, 0x0014559b, 0xa580A1FF),
		JUNGLE(0xa514a2c5, 0x0014a2c5, 0xa575ABFF),
		SPARSE_JUNGLE(0xa50d8ae3, 0x000d8ae3, 0xa575ABFF),
		LUKEWARM_OCEAN(0xa50D96DB, 0x000D96DB, 0xa50000c9),
		OLD_GROWTH_SPRUCE_TAIGA(0xa52d6d77, 0x002d6d77, 0xa57FA2FF),
		//GIANT_SPRUCE_TAIGA_HILLS(0xa52d6d77, 0x002d6d77, 0xa57FA2FF),
		OLD_GROWTH_PINE_TAIGA(0xa52d6d77, 0x002d6d77, 0xa57FA2FF),
		//GIANT_TREE_TAIGA_HILLS(0xa5286378, 0x00286378, 0xa57FA2FF),
		BADLANDS(0xa54E7F81, 0x004E7F81, 0xa56FB2FF),
		ERODED_BADLANDS(0xa5497F99, 0x00497F99, 0xa56FB2FF),
		WOODED_BADLANDS(0xa555809E, 0x0055809E, 0xa56FB2FF),
		MUSHROOM_FIELDS(0xa58a8997, 0x008a8997, 0xa578A8FF),
		//MUSHROOM_FIELDS_SHORE(0xa5818193, 0x00818193, 0xa578A8FF),
		OCEAN(0xa51787D4, 0x001787D4, 0xa57BA5FF),
		CHERRY_GROVE(0xa544AFF5, 0x0044AFF5, 0xa579A7FF),
		PLAINS(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		RIVER(0xa50084ff, 0x000084ff, 0xa57BA5FF),
		DARK_FOREST(0xa53B6CD1, 0x003B6CD1, 0xa57AA6FF),
		//DARK_FOREST_HILLS(0xa53B6CD1, 0x003B6CD1, 0xa57AA6FF),
		SAVANNA(0xa52C8B9C, 0x002C8B9C, 0xa575AAFF),
		WINDSWEPT_SAVANNA(0xa52590A8, 0x002590A8, 0xa575AAFF),
		SAVANNA_PLATEAU(0xa52590a8, 0x002590a8, 0xa575AAFF),
		STONY_SHORE(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		SUNFLOWER_PLAINS(0xa544aff5, 0x0044aff5, 0xa50000f5),
		SWAMP(0xa54c6559, 0x004c6559, 0xa579A7FF),
		MANGROVE_SWAMP(0xa53A7A6A, 0x003A7A6A, 0xa579A7FF),
		TAIGA(0xa5287082, 0x00287082, 0xa57FA2FF),
		//TAIGA_MOUNTAINS(0xa5236583, 0x00236583, 0xa57FA2FF),
		THE_END(0xa562529e, 0x0062529e, 0xa50000f5),
		WARM_OCEAN(0x8c02b0e5, 0x000289d5, 0xa575AAFF),
		DRIPSTONE_CAVES(0xa544AFF5, 0x0044aff5, 0xa579A7FF),
		FROZEN_PEAKS(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		LUSH_CAVES(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		DEEP_DARK(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		END_MIDLANDS(0xa562529e, 0x0062529e, 0xa50000f5),
		END_HIGHLANDS(0xa562529e, 0x0062529e, 0xa50000f5),
		END_BARRENS(0xa562529e, 0x0062529e, 0xa50000f5),
		SMALL_END_ISLANDS(0xa562529e, 0x0062529e, 0xa50000f5),
		STONY_PEAKS(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		GROVE(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		THE_VOID(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		JAGGED_PEAKS(0xa544aff5, 0x0044aff5, 0xa579A7FF),
		MEADOW(0xa544aff5, 0x0044aff5, 0xa579A7FF);




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

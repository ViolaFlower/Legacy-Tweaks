package xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.water_colors;

import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.helper.tweak.eye_candy.water.WaterHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(Biome.class)
public class BiomeMixin {
	@Inject(method = "getWaterColor", at = @At("HEAD"), cancellable = true)
	void injectwatercolor(CallbackInfoReturnable<Integer> cir) {
		if (!Tweaks.EYE_CANDY.legacyWaterColors.isEnabled()) return;
		Integer biomeColor = WaterHelper.getWaterColor((Biome) (Object) this);
		if (biomeColor == null) return;
		cir.setReturnValue(biomeColor);
	}

	@Inject(method = "getWaterFogColor", at = @At("HEAD"), cancellable = true)
	void injectwaterfogcolor(CallbackInfoReturnable<Integer> cir) {
		if (!Tweaks.EYE_CANDY.legacyWaterColors.isEnabled()) return;
		Integer biomeColor = WaterHelper.getWaterFogColor((Biome) (Object) this);
		if (biomeColor == null) return;
		cir.setReturnValue(biomeColor);
	}

	@Inject(method = "getFogColor", at = @At("HEAD"), cancellable = true)
	void injectfogcolor(CallbackInfoReturnable<Integer> cir) {
		if (!Tweaks.EYE_CANDY.legacyWaterColors.isEnabled()) return;
		Integer biomeColor = WaterHelper.getFogColor((Biome) (Object) this);
		if (biomeColor == null) return;
		cir.setReturnValue(biomeColor);
	}
}

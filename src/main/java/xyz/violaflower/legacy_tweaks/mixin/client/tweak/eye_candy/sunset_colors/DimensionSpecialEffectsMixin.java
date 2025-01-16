package xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.sunset_colors;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.helper.tweak.eye_candy.sky.SkyHelper;

@Mixin(DimensionSpecialEffects.class)
public abstract class DimensionSpecialEffectsMixin {

    /* Unique Fields */

    /**
     * Unique creation of the sunriseCol used in the mixin'd class
     */
    @Unique
    private final float[] legacyTweaks$sunriseCol = new float[4];

    /* Injections */

    /**
     * Changes the sunset/rise colors
     * @param timeOfDay The time of day in the world
     * @param partialTicks The ticks of the particles
     * @param cir Return value for the injection
     */
    @Inject(method = "getSunriseColor", at = @At("HEAD"), cancellable = true)
    private void changeSunsetSunriseColor(float timeOfDay, float partialTicks, CallbackInfoReturnable<float[]> cir) {
        cir.setReturnValue(SkyHelper.setSunsetRiseColor(timeOfDay, legacyTweaks$sunriseCol));
        cir.cancel();
    }
}

package xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.fog;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.eye_candy.fog.FogHelper;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.FogDataAccessor;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Redirect(method = "setupFog",at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/FogRenderer$FogData;start:F", opcode = Opcodes.PUTFIELD, ordinal = 8))
    private static void changeTerrainFogStart(@Coerce FogDataAccessor instance, float value, Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean shouldCreateFog, float partialTick) {
        FogHelper.setFogStartConditional(instance);
    }

    @Redirect(method = "setupFog",at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/FogRenderer$FogData;end:F", opcode = Opcodes.PUTFIELD, ordinal = 11))
    private static void changeTerrainFogEnd(@Coerce FogDataAccessor instance, float value, Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean shouldCreateFog, float partialTick) {
        FogHelper.setFogStopConditional(instance);
    }

    @Redirect(method = "setupFog",at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/FogRenderer$FogData;start:F", opcode = Opcodes.PUTFIELD, ordinal = 7))
    private static void removeSkyFogStart(@Coerce FogDataAccessor instance, float value, Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean shouldCreateFog, float partialTick) {
        FogHelper.setFogStartConditional(instance);
    }

    @Redirect(method = "setupFog",at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/FogRenderer$FogData;end:F", opcode = Opcodes.PUTFIELD, ordinal = 10))
    private static void removeSkyFogEnd(@Coerce FogDataAccessor instance, float value, Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean shouldCreateFog, float partialTick) {
        FogHelper.setFogStopConditional(instance);
    }
}

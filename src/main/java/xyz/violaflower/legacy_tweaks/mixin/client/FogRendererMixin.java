package xyz.violaflower.legacy_tweaks.mixin.client;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.violaflower.legacy_tweaks.tweaks.impl.LegacyFog;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Redirect(method = "setupFog",at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/FogRenderer$FogData;start:F", opcode = Opcodes.PUTFIELD, ordinal = 8))
    private static void setupFog(FogRenderer.FogData instance, float value, Camera camera, FogRenderer.FogMode fogMode, float f) {
        LegacyFog.setFogStartConditional(instance);
    }

    @Redirect(method = "setupFog",at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/FogRenderer$FogData;end:F", opcode = Opcodes.PUTFIELD, ordinal = 11))
    private static void setupFogStop(FogRenderer.FogData instance, float value, Camera camera, FogRenderer.FogMode fogMode, float f) {
        LegacyFog.setFogStopConditional(instance, f);
    }
}

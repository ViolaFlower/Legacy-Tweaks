package xyz.violaflower.legacy_tweaks.mixin.client;

import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.violaflower.legacy_tweaks.helper.tweak.SkyHelper;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    /**
     * Changes the stars to be smaller
     * @param originalStars The original stars
     * @return Returns the new star size
     */
    @ModifyVariable(method = "drawStars", at = @At(value = "STORE"), ordinal = 4)
    private float drawStars(float originalStars) {
        return SkyHelper.drawSmallerStars(originalStars);
    }
}

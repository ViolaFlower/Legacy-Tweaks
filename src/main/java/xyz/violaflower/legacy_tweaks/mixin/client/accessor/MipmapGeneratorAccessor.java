package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.client.renderer.texture.MipmapGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MipmapGenerator.class)
public interface MipmapGeneratorAccessor {

    @Invoker("alphaBlend")
    static int legacyTweaks$getAlphaBlend(int col0, int col1, int col2, int col3, boolean transparent) {
        throw new AssertionError();
    }
}

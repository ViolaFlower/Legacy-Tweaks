package xyz.violaflower.legacy_tweaks.mixin.client;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.MipmapGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.helper.tweak.texture.MipmapTypeHelper;

// TODO for 1.21.2+, needs fixing
@Mixin(MipmapGenerator.class)
public class MipmapGeneratorMixin {

    /**
     * Changes the mipmap type based on setting
     * @param nativeImages The original textures to mip
     * @param i The mipmap level
     * @param cir The return value of the mipmap type
     */
    @Inject(method = "generateMipLevels", at = @At("HEAD"), cancellable = true)
    private static void changeMipmapType(NativeImage[] nativeImages, int i, CallbackInfoReturnable<NativeImage[]> cir) {
        cir.setReturnValue(MipmapTypeHelper.setMipmapType(nativeImages, i));
        cir.cancel();
    }
}

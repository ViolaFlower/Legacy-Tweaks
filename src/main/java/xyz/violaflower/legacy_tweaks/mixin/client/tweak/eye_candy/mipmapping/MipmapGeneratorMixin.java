package xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.mipmapping;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.MipmapGenerator;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.helper.tweak.eye_candy.mipmapping.MipmapTypeHelper;

import java.io.IOException;

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
    private static void changeMipmapType(NativeImage[] nativeImages, int i, CallbackInfoReturnable<NativeImage[]> cir) throws IOException {
        ResourceLocation currentResourceLocation = MipmapTypeHelper.currentResourceLocation;
        MipmapTypeHelper.setMipmapType(nativeImages, i, currentResourceLocation, cir);
    }

    // Since there is no way to inject after an AASTORE instruction, a mixin plugin is used to add aastoreMarker() after every AASTORE call.
    @Dynamic // this is some cursed stuff
    @Inject(method = "generateMipLevels", at = @At(value = "INVOKE", target = "Lxyz/violaflower/legacy_tweaks/helper/tweak/eye_candy/mipmapping/MipmapTypeHelper;aastoreMarker()V", ordinal = 1))
    private static void changeJavaMipmaps(NativeImage[] images, int mipmapLevels, CallbackInfoReturnable<NativeImage[]> cir, @Local(ordinal = 1) int level, @Local(ordinal = 1) NativeImage[] nativeImages) {
        nativeImages[level] = MipmapTypeHelper.maybeGetMipmapForLevel(level, nativeImages[level - 1], nativeImages[level], MipmapTypeHelper.currentResourceLocation);
    }
    @Dynamic // this is some cursed stuff
    @Inject(method = "generateMipLevels", at = @At(value = "INVOKE", target = "Lxyz/violaflower/legacy_tweaks/helper/tweak/eye_candy/mipmapping/MipmapTypeHelper;aastoreMarker()V", ordinal = 2))
    private static void changeJavaMipmaps2(NativeImage[] images, int mipmapLevels, CallbackInfoReturnable<NativeImage[]> cir, @Local(ordinal = 1) int level, @Local(ordinal = 1) NativeImage[] nativeImages) {
        nativeImages[level] = MipmapTypeHelper.maybeGetMipmapForLevel(level, nativeImages[level - 1], nativeImages[level], MipmapTypeHelper.currentResourceLocation);
    }
}

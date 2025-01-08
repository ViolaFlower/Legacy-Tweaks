package xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.models.legacy_firework;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.FireworkEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.helper.tweak.texture.ModelHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

@Mixin(FireworkEntityRenderer.class)
public class FireworkEntityRendererMixin {

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/projectile/FireworkRocketEntity;)Lnet/minecraft/resources/ResourceLocation;",at = @At("HEAD"), cancellable = true)
    public void getTextureLocation(FireworkRocketEntity fireworkRocketEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        if (Tweaks.EYE_CANDY.models.legacyFireworkModel.isEnabled())
            cir.setReturnValue(Sprites.FIREWORK_LEGACY);
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/projectile/FireworkRocketEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",at = @At("HEAD"), cancellable = true)
    public void render(FireworkRocketEntity fireworkRocketEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        ModelHelper.renderFirework(fireworkRocketEntity, g, poseStack, multiBufferSource, i, ci);
    }
}

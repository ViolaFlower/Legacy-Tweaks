package xyz.violaflower.legacy_tweaks.helper.tweak.eye_candy.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

/**
 * Helper for model related changes
 */
public class ModelHelper {

    /* Firework Methods */

    // CREDITS: Legacy4J 1.7.5
    public static void renderFirework(FireworkRocketEntity entity, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        if (Tweaks.EYE_CANDY.models.legacyFireworkModel.isEnabled()) {
            ci.cancel();
            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(g, entity.yRotO, entity.getYRot()) - 90.0f));
            poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(g, entity.xRotO, entity.getXRot())));
            poseStack.mulPose(Axis.XP.rotationDegrees(45.0f));
            poseStack.scale(0.05625f, 0.05625f, 0.05625f);
            poseStack.translate(-4.0f, 0.0f, 0.0f);
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(Sprites.FIREWORK_LEGACY));
            PoseStack.Pose pose = poseStack.last();
            Matrix4f matrix4f = pose.pose();
            vertexForFirework(matrix4f, pose, vertexConsumer, -7, -2, -2, 0.0f, 0.15625f, -1, 0, 0, i);
            vertexForFirework(matrix4f, pose, vertexConsumer, -7, -2, 2, 0.15625f, 0.15625f, -1, 0, 0, i);
            vertexForFirework(matrix4f, pose, vertexConsumer, -7, 2, 2, 0.15625f, 0.3125f, -1, 0, 0, i);
            vertexForFirework(matrix4f, pose, vertexConsumer, -7, 2, -2, 0.0f, 0.3125f, -1, 0, 0, i);
            vertexForFirework(matrix4f, pose, vertexConsumer, -7, 2, -2, 0.0f, 0.15625f, 1, 0, 0, i);
            vertexForFirework(matrix4f, pose, vertexConsumer, -7, 2, 2, 0.15625f, 0.15625f, 1, 0, 0, i);
            vertexForFirework(matrix4f, pose, vertexConsumer, -7, -2, 2, 0.15625f, 0.3125f, 1, 0, 0, i);
            vertexForFirework(matrix4f, pose, vertexConsumer, -7, -2, -2, 0.0f, 0.3125f, 1, 0, 0, i);
            for (int u = 0; u < 4; ++u) {
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0f));
                vertexForFirework(matrix4f, pose, vertexConsumer, -8, -2, 0, 0.0f, 0.0f, 0, 1, 0, i);
                vertexForFirework(matrix4f, pose, vertexConsumer, 8, -2, 0, 0.5f, 0.0f, 0, 1, 0, i);
                vertexForFirework(matrix4f, pose, vertexConsumer, 8, 2, 0, 0.5f, 0.15625f, 0, 1, 0, i);
                vertexForFirework(matrix4f, pose, vertexConsumer, -8, 2, 0, 0.0f, 0.15625f, 0, 1, 0, i);
            }
            poseStack.popPose();
        }
    }

    // CREDITS: Legacy4J 1.7.5
    public static void vertexForFirework(Matrix4f matrix4f, PoseStack.Pose pose, VertexConsumer vertexConsumer, int i, int j, int k, float f, float g, int l, int m, int n, int o) {
        vertexConsumer.addVertex(matrix4f, i, j, k).setColor(255, 255, 255, 255).setUv(f, g).setOverlay(OverlayTexture.NO_OVERLAY).setLight(o).setNormal(pose, l, n, m);
    }
}
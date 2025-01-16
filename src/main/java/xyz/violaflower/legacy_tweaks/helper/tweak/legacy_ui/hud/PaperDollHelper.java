package xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.hud;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

public class PaperDollHelper {
    public static long animatedCharacterTime;
    public static long remainingAnimatedCharacterTime;

    // Credits: Legacy4J 1.7.4
    public static void renderPaperDoll(GuiGraphics guiGraphics, DeltaTracker deltaTracker, float x, float y, float size){
        if (Minecraft.getInstance().getCameraEntity() instanceof LivingEntity character) {
            boolean hasRemainingTime = character.isSprinting() || character.isCrouching() || character.isFallFlying() || character.isVisuallySwimming() || !(character instanceof Player);
            if (Tweaks.LEGACY_UI.guiHudTweaks.paperDollTweaks.showPaperDoll.isOn() && (hasRemainingTime || character instanceof Player p && p.getAbilities().flying) && !character.isSleeping()) {
                animatedCharacterTime = Util.getMillis();
                remainingAnimatedCharacterTime = hasRemainingTime ? 450 : 0;
            }
            if (Util.getMillis() - animatedCharacterTime <= remainingAnimatedCharacterTime) {
                float xRot = character.getXRot();
                float xRotO = character.xRotO;
                if (!character.isFallFlying()) character.setXRot(character.xRotO = -2.5f);
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(32f,18,0);
                float f = Tweaks.LEGACY_UI.guiHudTweaks.paperDollTweaks.smoothPaperDoll.isOn() ? deltaTracker.getGameTimeDeltaPartialTick(true) : 0;


                Quaternionf quaternionf = new Quaternionf().rotationXYZ(-5* Mth.PI/180f, (165 -Mth.lerp(f, character.yBodyRotO, character.yBodyRot)) * Mth.PI/180f, Mth.PI);
                @Nullable Quaternionf quaternionf2 = null;
                Vector3f vector3f = new Vector3f();
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(x, y, 50.0);
                guiGraphics.pose().mulPose(new Matrix4f().scaling(size, size, -size));
                guiGraphics.pose().translate(vector3f.x, vector3f.y, vector3f.z);
                guiGraphics.pose().mulPose(quaternionf);
                Lighting.setupForEntityInInventory();
                EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
                if (quaternionf2 != null) {
                    quaternionf2.conjugate();
                    entityRenderDispatcher.overrideCameraOrientation(quaternionf2);
                }
                entityRenderDispatcher.setRenderShadow(false);
                RenderSystem.runAsFancy(() -> entityRenderDispatcher.render(character, 0.0, 0.0, 0.0, 0.0f, f, guiGraphics.pose(), guiGraphics.bufferSource(), 0xF000F0));
                guiGraphics.flush();
                entityRenderDispatcher.setRenderShadow(true);
                guiGraphics.pose().popPose();
                Lighting.setupFor3DItems();

                guiGraphics.pose().popPose();
                character.setXRot(xRot);
                character.xRotO = xRotO;
            }
        }
    }

    public static void renderPaperDollStartEnd(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        float guiScale = !HudHelper.guiHudTweaks.paperDollTweaks.applyHudScalePaperDoll.isOn() ? 1.5f : HudHelper.getHudScale();
        float screenSpacing = !HudHelper.guiHudTweaks.paperDollTweaks.applyScreenSpacingPaperDoll.isOn() ? guiScale : (HudHelper.guiHudTweaks.generalTweaks.screenSpacing.get().floatValue()/100) * guiScale;
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        RenderSystem.enableBlend();

        renderPaperDoll(guiGraphics, deltaTracker, 10f * screenSpacing, 36f * screenSpacing, 12f * guiScale);
        HudHelper.end(guiGraphics, true);
    }
}

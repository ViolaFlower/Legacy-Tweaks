package xyz.violaflower.legacy_tweaks.util.client;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
//? if (neoforge) {
/*import net.neoforged.neoforge.client.ItemDecoratorHandler;
*///?}
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacySlot;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.GuiGraphicsAccessor;

public class GraphicsUtil {

    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void blit(GuiGraphics graphics, ResourceLocation atlasLocation, float x, float y, float uOffset, float vOffset, float width, float height, float textureWidth, float textureHeight) {
        blit(graphics, atlasLocation, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

    public static void blit(GuiGraphics graphics, ResourceLocation atlasLocation, float x, float y, float width, float height, float uOffset, float vOffset, float uWidth, float vHeight, float textureWidth, float textureHeight) {
        blit(graphics, atlasLocation, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    static void blit(GuiGraphics graphics, ResourceLocation atlasLocation, float x1, float x2, float y1, float y2, float blitOffset, float uWidth, float vHeight, float uOffset, float vOffset, float textureWidth, float textureHeight) {
        innerBlit(graphics , atlasLocation, x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / textureWidth, (uOffset + uWidth) / textureWidth, (vOffset + 0.0F) / textureHeight, (vOffset + vHeight) / textureHeight);
    }

    static void innerBlit(GuiGraphics graphics, ResourceLocation atlasLocation, float x1, float x2, float y1, float y2, float blitOffset, float minU, float maxU, float minV, float maxV) {
        RenderSystem.setShaderTexture(0, atlasLocation);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        Matrix4f matrix4f = ((GuiGraphicsAccessor) graphics).getPose().last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.addVertex(matrix4f, x1, y1, blitOffset).setUv(minU, minV);
        bufferBuilder.addVertex(matrix4f, x1, y2, blitOffset).setUv(minU, maxV);
        bufferBuilder.addVertex(matrix4f, x2, y2, blitOffset).setUv(maxU, maxV);
        bufferBuilder.addVertex(matrix4f, x2, y1, blitOffset).setUv(maxU, minV);
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());
    }

    void innerBlit(GuiGraphics graphics, ResourceLocation atlasLocation, float x1, float x2, float y1, float y2, float blitOffset, float minU, float maxU, float minV, float maxV, float red, float green, float blue, float alpha) {
        RenderSystem.setShaderTexture(0, atlasLocation);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.enableBlend();
        Matrix4f matrix4f = ((GuiGraphicsAccessor) graphics).getPose().last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferBuilder.addVertex(matrix4f, x1, y1, blitOffset).setUv(minU, minV).setColor(red, green, blue, alpha);
        bufferBuilder.addVertex(matrix4f, x1, y2, blitOffset).setUv(minU, maxV).setColor(red, green, blue, alpha);
        bufferBuilder.addVertex(matrix4f, x2, y2, blitOffset).setUv(maxU, maxV).setColor(red, green, blue, alpha);
        bufferBuilder.addVertex(matrix4f, x2, y1, blitOffset).setUv(maxU, minV).setColor(red, green, blue, alpha);
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());
        RenderSystem.disableBlend();
    }

//    public void enableScissor(GuiGraphics guiGraphics, int minX, int minY, int maxX, int maxY) {
//        this.applyScissor(guiGraphics.scissorStack.push(new ScreenRectangle(minX, minY, maxX - minX, maxY - minY)));
//    }
//
//    private void applyScissor(GuiGraphics graphics, @Nullable ScreenRectangle rectangle) {
//        this.flushIfManaged();
//        if (rectangle != null) {
//            Window window = Minecraft.getInstance().getWindow();
//            int i = window.getHeight();
//            double d = window.getGuiScale();
//            double e = (double)rectangle.left() * d;
//            double f = (double)i - (double)rectangle.bottom() * d;
//            double g = (double)rectangle.width() * d;
//            double h = (double)rectangle.height() * d;
//            RenderSystem.enableScissor((int)e, (int)f, Math.max(0, (int)g), Math.max(0, (int)h));
//        } else {
//            RenderSystem.disableScissor();
//        }
//
//    }
//
//    private void flushIfManaged(GuiGraphics graphics) {
//        if (((GuiGraphicsAccessor) graphics).getManaged()) {
//            graphics.flush();
//        }
//    }

    public static void renderEntityInInventoryFollowsMouse(GuiGraphics guiGraphics, float x1, float y1, float x2, float y2, float scale, float yOffset, float mouseX, float mouseY, LivingEntity entity) {
        float f = (float)(x1 + x2) / 2.0F;
        float g = (float)(y1 + y2) / 2.0F;
        guiGraphics.enableScissor((int) x1, (int) y1, (int) x2, (int) y2);
        float h = (float)Math.atan((double)((f - mouseX) / 40.0F));
        float i = (float)Math.atan((double)((g - mouseY) / 40.0F));
        Quaternionf quaternionf = (new Quaternionf()).rotateZ((float)Math.PI);
        Quaternionf quaternionf2 = (new Quaternionf()).rotateX(i * 20.0F * ((float)Math.PI / 180F));
        quaternionf.mul(quaternionf2);
        float j = entity.yBodyRot;
        float k = entity.getYRot();
        float l = entity.getXRot();
        float m = entity.yHeadRotO;
        float n = entity.yHeadRot;
        entity.yBodyRot = 180.0F + h * 20.0F;
        entity.setYRot(180.0F + h * 40.0F);
        entity.setXRot(-i * 20.0F);
        entity.yHeadRot = entity.getYRot();
        entity.yHeadRotO = entity.getYRot();
        float o = entity.getScale();
        Vector3f vector3f = new Vector3f(0.0F, entity.getBbHeight() / 2.0F + yOffset * o, 0.0F);
        float p = (float)scale / o;
        renderEntityInInventory(guiGraphics, f, g, p, vector3f, quaternionf, quaternionf2, entity);
        entity.yBodyRot = j;
        entity.setYRot(k);
        entity.setXRot(l);
        entity.yHeadRotO = m;
        entity.yHeadRot = n;
        guiGraphics.disableScissor();
    }

    public static void renderEntityInInventory(GuiGraphics guiGraphics, float x, float y, float scale, Vector3f translate, Quaternionf pose, @Nullable Quaternionf cameraOrientation, LivingEntity entity) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((double)x, (double)y, (double)50.0F);
        guiGraphics.pose().scale(scale, scale, -scale);
        guiGraphics.pose().translate(translate.x, translate.y, translate.z);
        guiGraphics.pose().mulPose(pose);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (cameraOrientation != null) {
            entityRenderDispatcher.overrideCameraOrientation(cameraOrientation.conjugate(new Quaternionf()).rotateY((float)Math.PI));
        }

        entityRenderDispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> entityRenderDispatcher.render(entity, (double)0.0F, (double)0.0F, (double)0.0F, 0.0F, 1.0F, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880));
        guiGraphics.flush();
        entityRenderDispatcher.setRenderShadow(true);
        guiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
    }

    public static void renderItem(GuiGraphics guiGraphics, LegacySlot slot, ItemStack stack, int x, int y) {
        renderItem(guiGraphics, slot, minecraft.player, minecraft.level, stack, x, y, 0);
    }

    public static void renderItem(GuiGraphics guiGraphics, LegacySlot slot, ItemStack stack, int x, int y, int seed) {
        renderItem(guiGraphics, slot, minecraft.player, minecraft.level, stack, x, y, seed);
    }

    public static void renderItem(GuiGraphics guiGraphics, LegacySlot slot, ItemStack stack, int x, int y, int seed, int guiOffset) {
        renderItem(guiGraphics, slot, minecraft.player, minecraft.level, stack, x, y, seed, guiOffset);
    }

    public static void renderFakeItem(GuiGraphics guiGraphics, LegacySlot slot, ItemStack stack, int x, int y) {
        renderFakeItem(guiGraphics, slot, stack, x, y, 0);
    }

    public static void renderFakeItem(GuiGraphics guiGraphics, LegacySlot slot,  ItemStack stack, int x, int y, int seed) {
        renderItem(guiGraphics, slot, (LivingEntity)null, minecraft.level, stack, x, y, seed);
    }

    public static void renderItem(GuiGraphics guiGraphics, LegacySlot slot, LivingEntity entity, ItemStack stack, int x, int y, int seed) {
        renderItem(guiGraphics, slot, entity, entity.level(), stack, x, y, seed);
    }

    public static void renderItem(GuiGraphics guiGraphics, LegacySlot slot, @Nullable LivingEntity entity, @Nullable Level level, ItemStack stack, int x, int y, int seed) {
        renderItem(guiGraphics, slot, entity, level, stack, x, y, seed, 0);
    }

    static void renderItem(GuiGraphics guiGraphics, LegacySlot slot, @Nullable LivingEntity entity, @Nullable Level level, ItemStack stack, int x, int y, int seed, int guiOffset) {
        if (!stack.isEmpty()) {
            BakedModel bakedmodel = minecraft.getItemRenderer().getModel(stack, level, entity, seed);
            PoseStack pose = guiGraphics.pose();
            pose.pushPose();
            pose.translate((float)(x + 8), (float)(y + 8), (float)(150 + (bakedmodel.isGui3d() ? guiOffset : 0)));

            try {
                float scale = slot.scale;
                pose.scale(scale, -scale, scale);
                boolean flag = !bakedmodel.usesBlockLight();
                if (flag) {
                    Lighting.setupForFlatItems();
                }

                minecraft.getItemRenderer().render(stack, ItemDisplayContext.GUI, false, pose, guiGraphics.bufferSource(), 15728880, OverlayTexture.NO_OVERLAY, bakedmodel);
                guiGraphics.flush();
                if (flag) {
                    Lighting.setupFor3DItems();
                }
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.forThrowable(throwable, "Rendering item");
                CrashReportCategory crashreportcategory = crashreport.addCategory("Item being rendered");
                crashreportcategory.setDetail("Item Type", () -> String.valueOf(stack.getItem()));
                crashreportcategory.setDetail("Item Components", () -> String.valueOf(stack.getComponents()));
                crashreportcategory.setDetail("Item Foil", () -> String.valueOf(stack.hasFoil()));
                throw new ReportedException(crashreport);
            }

            pose.popPose();
        }

    }

    public static void renderItemDecorations(GuiGraphics guiGraphics, LegacySlot slot, Font font, ItemStack stack, int x, int y) {
        renderItemDecorations(guiGraphics, slot, font, stack, x, y, (String)null);
    }

    public static void renderItemDecorations(GuiGraphics guiGraphics, LegacySlot slot, Font font, ItemStack stack, int x, int y, @Nullable String text) {
        if (!stack.isEmpty()) {
            PoseStack pose = guiGraphics.pose();
            pose.pushPose();
            float scale = slot.scale;
            if (stack.getCount() != 1 || text != null) {
                String s = text == null ? String.valueOf(stack.getCount()) : text;
                pose.translate(0.0F, 0.0F, 200.0F);
                guiGraphics.drawString(font, s, x + 19 - 2 - font.width(s), y + 6 + 3, 16777215, true);
            }

            if (stack.isBarVisible()) {
                int l = stack.getBarWidth();
                int i = stack.getBarColor();
                int j = x + 2;
                int k = y + 13;
                guiGraphics.fill(RenderType.guiOverlay(), j, k, j + 13, k + 2, -16777216);
                guiGraphics.fill(RenderType.guiOverlay(), j, k, j + l, k + 1, i | -16777216);
            }

            LocalPlayer localplayer = minecraft.player;
            float f = localplayer == null ? 0.0F : localplayer.getCooldowns().getCooldownPercent(stack.getItem(), minecraft.getTimer().getGameTimeDeltaPartialTick(true));
            if (f > 0.0F) {
                int i1 = y + Mth.floor(scale * (1.0F - f));
                int j1 = i1 + Mth.ceil(scale * f);
                guiGraphics.fill(RenderType.guiOverlay(), x, i1, x + 16, j1, Integer.MAX_VALUE);
            }

            pose.popPose();

            //? if (neoforge) {
            /*ItemDecoratorHandler.of(stack).render(guiGraphics, font, stack, x, y);
            *///?}
        }
    }
}

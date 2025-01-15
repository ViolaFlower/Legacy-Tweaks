package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.container;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacySlot;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyAbstractContainerScreen;
import xyz.violaflower.legacy_tweaks.util.client.GraphicsUtil;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {
    @Shadow
    private ItemStack draggingItem;

    @Shadow
    protected int topPos;

//    @Unique
//    private static final AbstractContainerScreenMixin abstractContainerScreenMixin = new AbstractContainerScreenMixin();
//
//    public AbstractContainerScreenMixin() {
//
//    }

    @Shadow
    private void renderFloatingItem(GuiGraphics guiGraphics, ItemStack stack, int x, int y, String text) {

    }

    @Shadow @Nullable protected Slot hoveredSlot;

    @Unique
    private boolean shouldApply() {
        return (Object) this instanceof LegacyAbstractContainerScreen<?>;
    }

    @Inject(method = "renderSlot", at = @At("HEAD"))
    private void getSlot(GuiGraphics guiGraphics, Slot slot, CallbackInfo ci, @Share("currentSlot") LocalRef<Slot> currentSlot) {
        if (!shouldApply()) return;
        currentSlot.set(slot);
    }

    @ModifyArgs(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(IIIIILnet/minecraft/client/renderer/texture/TextureAtlasSprite;)V", ordinal = 0))
    private void changeSlotSizeRender(Args args, @Share("currentSlot") LocalRef<Slot> currentSlot) {
        if (!shouldApply()) return;
        if (currentSlot.get() instanceof LegacySlot legacySlot) {
            args.set(3, legacySlot.size);
            args.set(4, legacySlot.size);
        }
    }

    @ModifyArgs(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0))
    private void changeSlotSizeRender2(Args args, @Share("currentSlot") LocalRef<Slot> currentSlot) {
        if (!shouldApply()) return;
        if (currentSlot.get() instanceof LegacySlot legacySlot) {
            args.set(2, args.get(2).hashCode() + legacySlot.size);
            args.set(3, args.get(3).hashCode() + legacySlot.size);
        }
    }

    @ModifyArgs(method = "isHovering(Lnet/minecraft/world/inventory/Slot;DD)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;isHovering(IIIIDD)Z"))
    private void changeSlotSizeHover(Args args, @Local(argsOnly = true) Slot currentSlot) {
        if (!shouldApply()) return;
        if (currentSlot instanceof LegacySlot) {
            args.set(2, ((LegacySlot) currentSlot).size);
            args.set(3, ((LegacySlot) currentSlot).size);
        }
    }

//    @ModifyArgs(method = "renderSlotHighlight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fillGradient(Lnet/minecraft/client/renderer/RenderType;IIIIIII)V"))
//    private static void changeSlotSizeHightlight(Args args) {
//        if (abstractContainerScreenMixin.currentSlot instanceof LegacySlot) {
//            args.set(2, args.get(2).hashCode() + ((LegacySlot) abstractContainerScreenMixin.currentSlot).scale);
//            args.set(3, args.get(3).hashCode() + ((LegacySlot) abstractContainerScreenMixin.currentSlot).scale);
//        }
//    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;renderSlotHighlight(Lnet/minecraft/client/gui/GuiGraphics;III)V"))
    private void changeSlotSizeHightlight(GuiGraphics guiGraphics, int x, int y, int blitOffset, Operation<Void> original) {
        if (!shouldApply()) {original.call(guiGraphics, x, y, blitOffset); return;}
        if (this.hoveredSlot instanceof LegacySlot legacySlot) guiGraphics.fillGradient(RenderType.guiOverlay(), x, y, x + legacySlot.size, y + legacySlot.size, 0x80ffffff, 0x80ffffff, blitOffset);
        else original.call(guiGraphics, x, y, blitOffset);
    }

//    @Inject(method = "render", at = @At("TAIL"))
//    private void changeSlotSizeItem(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
//        if (currentSlot instanceof LegacySlot) {
//            int l = this.draggingItem.isEmpty() ? 8 : ((LegacySlot) currentSlot).scale;
//        }
//    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;renderFloatingItem(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", ordinal = 0))
    private void changeSlotSizeItem(AbstractContainerScreen instance, GuiGraphics guiGraphics, ItemStack itemStack, int x, int y, String text, Operation<Void> original, @Local(ordinal = 0, argsOnly = true) int mouseX, @Local(ordinal = 1, argsOnly = true) int mouseY) {
        if (!shouldApply()) {original.call(instance, guiGraphics, itemStack, x, y, text); return;}
        if (/*currentSlot*/null/*TODO*/ instanceof LegacySlot) {
            int l = this.draggingItem.isEmpty() ? 20 : ((LegacySlot) null/*currentSlot*/).size;
            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();
//
//            poseStack.translate(1/1.25f, 1/1.25f, 1/1.25f);
            guiGraphics.pose().translate(guiGraphics.guiWidth()/2f, guiGraphics.guiHeight()/2f,0.0F);
            poseStack.scale(1.5f, 1.5f, 1.5f);
            guiGraphics.pose().translate(-guiGraphics.guiWidth()/2f, -guiGraphics.guiHeight()/2f,0);
            GraphicsUtil.renderFloatingItem(instance, 28, guiGraphics, itemStack, mouseX, mouseY, text);
            poseStack.popPose();
        } else {
            original.call(instance, guiGraphics, itemStack, x, y, text);
        }
    }

    @WrapOperation(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;renderFakeItem(Lnet/minecraft/world/item/ItemStack;III)V"))
    private void changeSlotFakeItemRender(GuiGraphics instance, ItemStack stack, int x, int y, int seed, Operation<Void> original, @Local(argsOnly = true) Slot currentSlot) {
        if (!shouldApply()) {original.call(instance, stack, x, y, seed); return;}
        if (currentSlot instanceof LegacySlot legacySlot) {
            GraphicsUtil.renderFakeItem(instance, legacySlot, stack, x + 2, y + 2, seed);
        } else {
            original.call(instance, stack, x, y, seed);
        }
    }

    @WrapOperation(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/item/ItemStack;III)V"))
    private void changeSlotItemRender(GuiGraphics instance, ItemStack stack, int x, int y, int seed, Operation<Void> original, @Local(argsOnly = true) Slot currentSlot) {
        if (!shouldApply()) {original.call(instance, stack, x, y, seed); return;}
        if (currentSlot instanceof LegacySlot legacySlot) {
            GraphicsUtil.renderItem(instance, legacySlot, stack, x + 2, y + 2, seed);
        } else {
            original.call(instance, stack, x, y, seed);
        }
    }

    @WrapOperation(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V"))
    private void changeSlotItemDecorationsRender(GuiGraphics instance, Font l, ItemStack i, int j, int k, String i1, Operation<Void> original, @Local(argsOnly = true) Slot currentSlot) {
        if (!shouldApply()) {original.call(instance, l, i, j, k, i1); return;}
        if (currentSlot instanceof LegacySlot legacySlot) {
            GraphicsUtil.renderItemDecorations(instance, legacySlot.size, l, i, j + 2, k + 2, i1, true);
        } else {
            original.call(instance, l, i, j, k, i1);
        }
    }
}

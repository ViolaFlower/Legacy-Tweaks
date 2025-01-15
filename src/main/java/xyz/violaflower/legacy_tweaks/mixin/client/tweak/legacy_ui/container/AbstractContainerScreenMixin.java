package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.container;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyAbstractContainerScreen;
import xyz.violaflower.legacy_tweaks.util.client.GraphicsUtil;

import java.util.Set;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {
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

    @Shadow private @Nullable Slot clickedSlot;

    @Shadow private boolean isSplittingStack;

    @Shadow @Final protected AbstractContainerMenu menu;

    @Shadow protected boolean isQuickCrafting;

    @Shadow @Final protected Set<Slot> quickCraftSlots;

    @Shadow private int quickCraftingType;

    @Shadow protected abstract void recalculateQuickCraftRemaining();

    @Shadow protected int imageWidth;

    @Unique
    private boolean shouldApply() {
        return (Object) this instanceof LegacyAbstractContainerScreen<?>;
    }

//    @Inject(method = "renderSlot", at = @At("HEAD"))
//    private void getSlot(GuiGraphics guiGraphics, Slot slot, CallbackInfo ci, @Share("currentSlot") LocalRef<Slot> currentSlot) {
//        if (!shouldApply()) return;
//        currentSlot.set(slot);
//    }
//
//    @ModifyArgs(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(IIIIILnet/minecraft/client/renderer/texture/TextureAtlasSprite;)V", ordinal = 0))
//    private void changeSlotSizeRender(Args args, @Share("currentSlot") LocalRef<Slot> currentSlot) {
//        if (!shouldApply()) return;
//        if (currentSlot.get() instanceof SlotExtension legacySlot) {
//            args.set(3, legacySlot.lt$getSize());
//            args.set(4, legacySlot.lt$getSize());
//        }
//    }
//
//    @ModifyArgs(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0))
//    private void changeSlotSizeRender2(Args args, @Share("currentSlot") LocalRef<Slot> currentSlot) {
//        if (!shouldApply()) return;
//        if (currentSlot.get() instanceof SlotExtension legacySlot) {
//            args.set(2, args.get(2).hashCode() + legacySlot.lt$getSize());
//            args.set(3, args.get(3).hashCode() + legacySlot.lt$getSize());
//        }
//    }
//
//    @ModifyArgs(method = "isHovering(Lnet/minecraft/world/inventory/Slot;DD)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;isHovering(IIIIDD)Z"))
//    private void changeSlotSizeHover(Args args, @Local(argsOnly = true) Slot currentSlot) {
//        if (!shouldApply()) return;
//        if (currentSlot instanceof SlotExtension slotExtension) {
//            args.set(2, slotExtension.lt$getSize());
//            args.set(3, slotExtension.lt$getSize());
//        }
//    }

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
        if (this.hoveredSlot instanceof SlotExtension legacySlot) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(legacySlot.lt$getVisualX(), legacySlot.lt$getVisualY(), 0);
            guiGraphics.fillGradient(RenderType.guiOverlay(), 0, 0, 0 + legacySlot.lt$getSize(), 0 + legacySlot.lt$getSize(), 0x80ffffff, 0x80ffffff, blitOffset);
            guiGraphics.pose().popPose();
        } else original.call(guiGraphics, x, y, blitOffset);
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
        if (/*currentSlot*/null/*TODO*/ instanceof SlotExtension) {
            int l = this.draggingItem.isEmpty() ? 20 : ((SlotExtension) null/*currentSlot*/).lt$getSize();
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

//    @WrapOperation(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;renderFakeItem(Lnet/minecraft/world/item/ItemStack;III)V"))
//    private void changeSlotFakeItemRender(GuiGraphics instance, ItemStack stack, int x, int y, int seed, Operation<Void> original, @Local(argsOnly = true) Slot currentSlot) {
//        if (!shouldApply()) {original.call(instance, stack, x, y, seed); return;}
//        if (currentSlot instanceof SlotExtension legacySlot) {
//            GraphicsUtil.renderFakeItem(instance, legacySlot, stack, x + 2, y + 2, seed);
//        } else {
//            original.call(instance, stack, x, y, seed);
//        }
//    }
//
//    @WrapOperation(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/item/ItemStack;III)V"))
//    private void changeSlotItemRender(GuiGraphics instance, ItemStack stack, int x, int y, int seed, Operation<Void> original, @Local(argsOnly = true) Slot currentSlot) {
//        if (!shouldApply()) {original.call(instance, stack, x, y, seed); return;}
//        if (currentSlot instanceof SlotExtension legacySlot) {
//            GraphicsUtil.renderItem(instance, legacySlot, stack, x + 2, y + 2, seed);
//        } else {
//            original.call(instance, stack, x, y, seed);
//        }
//    }
//
//    @WrapOperation(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V"))
//    private void changeSlotItemDecorationsRender(GuiGraphics instance, Font l, ItemStack i, int j, int k, String i1, Operation<Void> original, @Local(argsOnly = true) Slot currentSlot) {
//        if (!shouldApply()) {original.call(instance, l, i, j, k, i1); return;}
//        if (currentSlot instanceof SlotExtension legacySlot) {
//            GraphicsUtil.renderItemDecorations(instance, legacySlot.lt$getSize(), l, i, j + 2, k + 2, i1, true);
//        } else {
//            original.call(instance, l, i, j, k, i1);
//        }
//    }

    @WrapOperation(method = "isHovering(Lnet/minecraft/world/inventory/Slot;DD)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;isHovering(IIIIDD)Z"))
    private boolean isHovering(AbstractContainerScreen instance, int x, int y, int width, int height, double mouseX, double mouseY, Operation<Boolean> original, @Local(argsOnly = true) Slot slot) {

        return original.call(instance, (int) (slot instanceof SlotExtension extension ? extension.lt$getVisualX() : slot.x), (int) (slot instanceof SlotExtension extension ? extension.lt$getVisualY() : slot.y), slot instanceof SlotExtension extension ? extension.lt$getSize() : width, slot instanceof SlotExtension extension ? extension.lt$getSize() : height, mouseX, mouseY);
    }

    /**
     * @author Jab125
     * @reason TODO FIX THIS LATER
     */
    @Overwrite
    public void renderSlot(GuiGraphics guiGraphics, Slot slot) {
        float i = slot instanceof SlotExtension extension ? extension.lt$getVisualX() : slot.x;
        float j = slot instanceof SlotExtension extension ? extension.lt$getVisualY() : slot.y;
        ItemStack itemStack = slot.getItem();
        boolean bl = false;
        boolean bl2 = slot == this.clickedSlot && !this.draggingItem.isEmpty() && !this.isSplittingStack;
        ItemStack itemStack2 = this.menu.getCarried();
        String string = null;
        if (slot == this.clickedSlot && !this.draggingItem.isEmpty() && this.isSplittingStack && !itemStack.isEmpty()) {
            itemStack = itemStack.copyWithCount(itemStack.getCount() / 2);
        } else if (this.isQuickCrafting && this.quickCraftSlots.contains(slot) && !itemStack2.isEmpty()) {
            if (this.quickCraftSlots.size() == 1) {
                return;
            }

            if (AbstractContainerMenu.canItemQuickReplace(slot, itemStack2, true) && this.menu.canDragTo(slot)) {
                bl = true;
                int k = Math.min(itemStack2.getMaxStackSize(), slot.getMaxStackSize(itemStack2));
                int l = slot.getItem().isEmpty() ? 0 : slot.getItem().getCount();
                int m = AbstractContainerMenu.getQuickCraftPlaceCount(this.quickCraftSlots, this.quickCraftingType, itemStack2) + l;
                if (m > k) {
                    m = k;
                    string = ChatFormatting.YELLOW.toString() + k;
                }

                itemStack = itemStack2.copyWithCount(m);
            } else {
                this.quickCraftSlots.remove(slot);
                this.recalculateQuickCraftRemaining();
            }
        }

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, 0.0F, 100.0F);
        if (itemStack.isEmpty() && slot.isActive()) {
            Pair<ResourceLocation, ResourceLocation> pair = slot.getNoItemIcon();
            if (pair != null) {
                TextureAtlasSprite textureAtlasSprite = (TextureAtlasSprite) Minecraft.getInstance().getTextureAtlas(pair.getFirst()).apply(pair.getSecond());
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(i, j, 0);
                guiGraphics.blit(0, 0, 0, 16, 16, textureAtlasSprite);
                guiGraphics.pose().popPose();
                bl2 = true;
            }
        }

        if (!bl2) {
            if (bl) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(i, j, 0);
                guiGraphics.fill(0, 0, 16, 16, 0x80ffffff);
                guiGraphics.pose().popPose();
            }

            int k = slot.x + slot.y * this.imageWidth;
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(i, j, 0);
            float scale = slot instanceof SlotExtension slotExtension ? slotExtension.lt$getSize() / 16f : 16;
            if (slot.isFake()) {
                GraphicsUtil.renderFakeItem(guiGraphics, (SlotExtension) slot, itemStack, 0, 0, k);
            } else {
                GraphicsUtil.renderItem(guiGraphics, (SlotExtension) slot, itemStack, 0, 0, k);
            }
            GraphicsUtil.renderItemDecorations(guiGraphics, scale, Minecraft.getInstance().font, itemStack, 0, 0, false/*string*/);
            guiGraphics.pose().popPose();
        }

        guiGraphics.pose().popPose();
    }
}

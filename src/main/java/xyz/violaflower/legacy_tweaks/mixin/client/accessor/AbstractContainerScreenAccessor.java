package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net/minecraft/client/gui/screens/inventory/AbstractContainerScreen")
public interface AbstractContainerScreenAccessor {

    @Accessor("clickedSlot")
    Slot legacyTweaks$getClickedSlot();

    @Accessor("draggingItem")
    ItemStack legacyTweaks$getDraggingItem();

    @Accessor("isSplittingStack")
    boolean legacyTweaks$isSplittingStack();

    @Accessor("quickCraftingType")
    int legacyTweaks$getQuickCraftingType();

    @Accessor("quickCraftingRemainder")
    int legacyTweaks$getQuickCraftingRemainder();
}

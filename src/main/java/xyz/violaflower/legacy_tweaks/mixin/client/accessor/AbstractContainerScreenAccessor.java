package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net/minecraft/client/gui/screens/inventory/AbstractContainerScreen")
public interface AbstractContainerScreenAccessor {

    @Accessor("draggingItem")
    ItemStack legacyTweaks$getDraggingItem();

}

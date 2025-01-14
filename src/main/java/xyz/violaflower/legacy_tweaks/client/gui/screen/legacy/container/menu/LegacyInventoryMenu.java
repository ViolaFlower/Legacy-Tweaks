package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.container.menu;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlot.Type;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyArmorSlot;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyResultSlot;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacySlot;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.CraftingMenuAccessor;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

public class LegacyInventoryMenu extends InventoryMenu {
    private static final Map<EquipmentSlot, ResourceLocation> TEXTURE_EMPTY_SLOTS;
    private static final EquipmentSlot[] SLOT_IDS;
    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 2, 2);
    private final ResultContainer resultSlots = new ResultContainer();
    private final Inventory playerInventory;
    public final boolean active;
    private final Player owner;


    public LegacyInventoryMenu(Inventory playerInventory, boolean active, final Player owner) {
        super(playerInventory, active, owner);
        this.playerInventory = playerInventory;
        this.active = active;
        this.owner = owner;
        setSlots();
    }

    public void setSlots() {
        this.slots.clear();
        int offset = 0;
        int offset2 = 0;
        if (!Tweaks.LEGACY_UI.legacyInventoryScreenTweak.noOffhand.isOn()) {
            offset = 5;
            offset2 = 1;
        }
        this.addSlot(new LegacyResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 156 + offset, 30-33 - offset2, Tweaks.LEGACY_UI.legacyInventoryScreenTweak.noOffhand.isOn() ? 25 : 19));

        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 2; ++j) {
                this.addSlot(new LegacySlot(this.craftSlots, j + i * 2, (100-9) + j * 21, (20-30 - offset) + i * 21, 19));
            }
        }

        for(int i = 0; i < 4; ++i) {
            EquipmentSlot equipmentSlot = SLOT_IDS[i];
            ResourceLocation resourceLocation = (ResourceLocation)TEXTURE_EMPTY_SLOTS.get(equipmentSlot);
            this.addSlot(new LegacyArmorSlot(playerInventory, owner, equipmentSlot, 39 - i, -6, -31 + i * 21, resourceLocation, 19));
        }

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new LegacySlot(playerInventory, j + (i + 1) * 9, -6 + j * 21, (93-23) + i * 21, 19));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new LegacySlot(playerInventory, i, -6 + i * 21, (151-11), 19));
        }

        if (!Tweaks.LEGACY_UI.legacyInventoryScreenTweak.noOffhand.isOn()) {
            this.addSlot(new LegacySlot(playerInventory, 40, 100-9, 32, 19) {
                public void setByPlayer(ItemStack newStack, ItemStack oldStack) {
                    owner.onEquipItem(EquipmentSlot.OFFHAND, oldStack, newStack);
                    super.setByPlayer(newStack, oldStack);
                }

                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, Sprites.EMPTY_ARMOR_SLOT_SHIELD);
                }
            });
        }
    }

    public static boolean isHotbarSlot(int index) {
        return index >= 36 && index < 45 || index == 45;
    }

    public void fillCraftSlotsStackedContents(StackedContents itemHelper) {
        this.craftSlots.fillStackedContents(itemHelper);
    }

    public void clearCraftingContent() {
        this.resultSlots.clearContent();
        this.craftSlots.clearContent();
    }

    public boolean recipeMatches(RecipeHolder<CraftingRecipe> recipe) {
        return ((CraftingRecipe)recipe.value()).matches(this.craftSlots.asCraftInput(), this.owner.level());
    }

    public void removed(Player player) {
        super.removed(player);
        this.resultSlots.clearContent();
        if (!player.level().isClientSide) {
            this.clearContainer(player, this.craftSlots);
        }
    }

    public boolean stillValid(Player player) {
        return true;
    }

    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            EquipmentSlot equipmentSlot = player.getEquipmentSlotForItem(itemStack);
            if (index == 0) {
                if (!this.moveItemStackTo(itemStack2, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemStack2, itemStack);
            } else if (index >= 1 && index < 5) {
                if (!this.moveItemStackTo(itemStack2, 9, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 5 && index < 9) {
                if (!this.moveItemStackTo(itemStack2, 9, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipmentSlot.getType() == Type.HUMANOID_ARMOR && !((Slot)this.slots.get(8 - equipmentSlot.getIndex())).hasItem()) {
                int i = 8 - equipmentSlot.getIndex();
                if (!this.moveItemStackTo(itemStack2, i, i + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipmentSlot == EquipmentSlot.OFFHAND && !((Slot)this.slots.get(45)).hasItem()) {
                if (!this.moveItemStackTo(itemStack2, 45, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 9 && index < 36) {
                if (!this.moveItemStackTo(itemStack2, 36, 45, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 36 && index < 45) {
                if (!this.moveItemStackTo(itemStack2, 9, 36, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack2, 9, 45, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY, itemStack);
            } else {
                slot.setChanged();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemStack2);
            if (index == 0) {
                player.drop(itemStack2, false);
            }
        }

        return itemStack;
    }

    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    public int getResultSlotIndex() {
        return 0;
    }

    public int getGridWidth() {
        return this.craftSlots.getWidth();
    }

    public int getGridHeight() {
        return this.craftSlots.getHeight();
    }

    public int getSize() {
        return 5;
    }

    public CraftingContainer getCraftSlots() {
        return this.craftSlots;
    }

    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    public boolean shouldMoveToInventory(int slotIndex) {
        return slotIndex != this.getResultSlotIndex();
    }

    static {
        TEXTURE_EMPTY_SLOTS = Map.of(EquipmentSlot.FEET, Sprites.EMPTY_ARMOR_SLOT_BOOTS, EquipmentSlot.LEGS, Sprites.EMPTY_ARMOR_SLOT_LEGGINGS, EquipmentSlot.CHEST, Sprites.EMPTY_ARMOR_SLOT_CHESTPLATE, EquipmentSlot.HEAD, Sprites.EMPTY_ARMOR_SLOT_HELMET);
        SLOT_IDS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    }
}

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
        int armorOffset = 50;
        int craftingOffset = 10000;
        if (!Tweaks.LEGACY_UI.legacyInventoryScreenTweak.noOffhand.isOn()) {
            offset = 5;
            offset2 = 1;
        }
        if (Tweaks.LEGACY_UI.legacyInventoryScreenTweak.classicCrafting.isOn()) {
            armorOffset = 0;
            craftingOffset = 0;
        }
        this.addSlot(new LegacyResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 156 + offset + craftingOffset, 30 - 33 - offset2 + craftingOffset, Tweaks.LEGACY_UI.legacyInventoryScreenTweak.noOffhand.isOn() ? 25 : 19));

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.addSlot(new LegacySlot(this.craftSlots, j + i * 2, (100 - 9 + craftingOffset) + j * 21, (20 - 30 - offset + craftingOffset) + i * 21, 19));
            }
        }

        for(int i = 0; i < 4; ++i) {
            EquipmentSlot equipmentSlot = SLOT_IDS[i];
            ResourceLocation resourceLocation = (ResourceLocation)TEXTURE_EMPTY_SLOTS.get(equipmentSlot);
            this.addSlot(new LegacyArmorSlot(playerInventory, owner, equipmentSlot, 39 - i, -6 + armorOffset, -31 + i * 21, resourceLocation, 19));
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
            this.addSlot(new LegacySlot(playerInventory, 40, 100-9 + armorOffset, 32, 19) {
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
        int slotOffset = Tweaks.LEGACY_UI.legacyInventoryScreenTweak.classicCrafting.isOn() ? 0 : 5;
        return index >= 36 - slotOffset && index < 45 - slotOffset || index == 45 - slotOffset;
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
            int slotIndexOffset = 0;
            boolean classicCrafing = Tweaks.LEGACY_UI.legacyInventoryScreenTweak.classicCrafting.isOn();
            if (classicCrafing) slotIndexOffset = 0;
            if (index == 0 && classicCrafing) {
                if (!this.moveItemStackTo(itemStack2, 9 - slotIndexOffset, 45 - slotIndexOffset, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemStack2, itemStack);
            } else if (index >= 1 && index < 5 && classicCrafing) {
                if (!this.moveItemStackTo(itemStack2, 9 - slotIndexOffset, 45 - slotIndexOffset, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 5 - slotIndexOffset && index < 9 - slotIndexOffset) {
                if (!this.moveItemStackTo(itemStack2, 9 - slotIndexOffset, 45 - slotIndexOffset, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipmentSlot.getType() == Type.HUMANOID_ARMOR && !((Slot)this.slots.get((8 - slotIndexOffset) - equipmentSlot.getIndex())).hasItem()) {
                int i = 8 - equipmentSlot.getIndex();
                if (!this.moveItemStackTo(itemStack2, i - slotIndexOffset, i + 1 - slotIndexOffset, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipmentSlot == EquipmentSlot.OFFHAND && !((Slot)this.slots.get(45 - slotIndexOffset)).hasItem() && !Tweaks.LEGACY_UI.legacyInventoryScreenTweak.noOffhand.isOn()) {
                if (!this.moveItemStackTo(itemStack2, 45 - slotIndexOffset, 46 - slotIndexOffset, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 9 - slotIndexOffset && index < 36 - slotIndexOffset) {
                if (!this.moveItemStackTo(itemStack2, 36 - slotIndexOffset, 45 - slotIndexOffset, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 36 - slotIndexOffset && index < 45 - slotIndexOffset) {
                if (!this.moveItemStackTo(itemStack2, 9 - slotIndexOffset, 36 - slotIndexOffset, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack2, 9 - slotIndexOffset, 45 - slotIndexOffset, false)) {
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

package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.crafting;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyAutoCraftingScreen;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyInventoryScreen;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

public class LegacyRecipeBookCraftingScreen extends LegacyAutoCraftingScreen<InventoryMenu> implements RecipeUpdateListener {
    private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
    private final Screen parent;
    private static float staticLeftPos;
    private static float staticTopPos;
    public LegacyRecipeBookCraftingScreen(Player player, Screen parent) {
        super(manipulateInventorySlots(player.inventoryMenu), player.getInventory(), Component.translatable("container.crafting"));
        this.parent = parent;
        staticLeftPos = this.leftPos - (useSmallCrafting() ? 36.5f : 0f);
        staticTopPos = this.topPos;
    }

    @Override
    protected void init() {
        super.init();

    }

    @Override
    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    @Override
    public @NotNull RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }

    @Override
    public void onClose() {
        super.onClose();
        if (parent instanceof LegacyInventoryScreen) this.minecraft.setScreen(new LegacyInventoryScreen(this.minecraft.player));
    }

    private static InventoryMenu manipulateInventorySlots(InventoryMenu menu) {
        int i = 0;
        for (Slot slot : menu.slots) {
            if (slot instanceof SlotExtension extension) {
                if (i == InventoryMenu.RESULT_SLOT) {
                    extension.lt$setVisualX(staticLeftPos + 128f);
                    extension.lt$setVisualY(staticTopPos + 145.45f);
                    extension.lt$setSize(32);
                } else {
                    // yes, this is painful
                    if (i >= InventoryMenu.ARMOR_SLOT_START && i < InventoryMenu.ARMOR_SLOT_END) {
                        extension.lt$setVisualX(staticLeftPos + 10000f);
                    } else if (i >= InventoryMenu.CRAFT_SLOT_START && i < InventoryMenu.ARMOR_SLOT_END) {
                        extension.lt$setVisualX(staticLeftPos + slot.x * 21.1559f/16f - 76.9f);
                        extension.lt$setVisualY(staticTopPos + slot.y * 21.1559f/16f + 115.9f);
                        extension.lt$setSize(21);
                    } else if (i == InventoryMenu.SHIELD_SLOT) {
                        extension.lt$setVisualX(staticLeftPos + 10000f);
                    } else if (InventoryMenu.INV_SLOT_START <= i) {
                        extension.lt$setVisualX(staticLeftPos + slot.x * 14.22f/16f + 169.5f);
                        extension.lt$setVisualY(staticTopPos + slot.y * 14.22f/16f + 55.3f + (InventoryMenu.isHotbarSlot(i) ? 4.5f : 0f));
                        extension.lt$setSize(14);
                    }
                }
            }
            i++;
        }
        return menu;
    }
}

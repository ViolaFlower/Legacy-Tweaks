package xyz.violaflower.legacy_tweaks.client.gui.element;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeType;

public class LegacyResultSlot extends LegacySlot {
    private final CraftingContainer craftSlots;
    private final Player player;
    private int removeCount;

    public LegacyResultSlot(Player player, CraftingContainer craftSlots, Container container, int slot, int xPosition, int yPosition, int size) {
        super(container, slot, xPosition, yPosition, size);
        this.player = player;
        this.craftSlots = craftSlots;
    }

    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    public ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(amount, this.getItem().getCount());
        }

        return super.remove(amount);
    }

    protected void onQuickCraft(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    protected void onSwapCraft(int numItemsCrafted) {
        this.removeCount += numItemsCrafted;
    }

    protected void checkTakeAchievements(ItemStack stack) {
        if (this.removeCount > 0) {
            stack.onCraftedBy(this.player.level(), this.player, this.removeCount);
        }

        Container var3 = this.container;
        if (var3 instanceof RecipeCraftingHolder recipeCraftingHolder) {
            recipeCraftingHolder.awardUsedRecipes(this.player, this.craftSlots.getItems());
        }

        this.removeCount = 0;
    }

    public void onTake(Player player, ItemStack stack) {
        this.checkTakeAchievements(stack);
        CraftingInput.Positioned positioned = this.craftSlots.asPositionedCraftInput();
        CraftingInput craftingInput = positioned.input();
        int i = positioned.left();
        int j = positioned.top();
        NonNullList<ItemStack> nonNullList = player.level().getRecipeManager().getRemainingItemsFor(RecipeType.CRAFTING, craftingInput, player.level());

        for(int k = 0; k < craftingInput.height(); ++k) {
            for(int l = 0; l < craftingInput.width(); ++l) {
                int m = l + i + (k + j) * this.craftSlots.getWidth();
                ItemStack itemStack = this.craftSlots.getItem(m);
                ItemStack itemStack2 = (ItemStack)nonNullList.get(l + k * craftingInput.width());
                if (!itemStack.isEmpty()) {
                    this.craftSlots.removeItem(m, 1);
                    itemStack = this.craftSlots.getItem(m);
                }

                if (!itemStack2.isEmpty()) {
                    if (itemStack.isEmpty()) {
                        this.craftSlots.setItem(m, itemStack2);
                    } else if (ItemStack.isSameItemSameComponents(itemStack, itemStack2)) {
                        itemStack2.grow(itemStack.getCount());
                        this.craftSlots.setItem(m, itemStack2);
                    } else if (!this.player.getInventory().add(itemStack2)) {
                        this.player.drop(itemStack2, false);
                    }
                }
            }
        }

    }

    public boolean isFake() {
        return true;
    }
}

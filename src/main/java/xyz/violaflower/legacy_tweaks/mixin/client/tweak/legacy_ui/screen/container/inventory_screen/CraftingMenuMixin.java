package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.screen.container.inventory_screen;

import net.minecraft.world.inventory.*;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import xyz.violaflower.legacy_tweaks.client.gui.extention.CraftingMenuExtension;
import xyz.violaflower.legacy_tweaks.client.gui.extention.VirtualCraftingInventory;
import xyz.violaflower.legacy_tweaks.client.gui.extention.VirtualPlayerInventory;

import java.util.Iterator;

@Mixin(CraftingMenu.class)
public abstract class CraftingMenuMixin extends RecipeBookMenu<CraftingInput, CraftingRecipe> implements CraftingMenuExtension {
	@Shadow @Final private static int CRAFT_SLOT_START;

	@Shadow @Final public static int RESULT_SLOT;

	public CraftingMenuMixin(MenuType<?> menuType, int containerId) {
		super(menuType, containerId);
	}

	@Unique
	private void assertSlot(int slot, int min, int max) {
		if (slot >= min && slot < max) return;
		throw new IllegalArgumentException();
	}
	@Override
	public VirtualPlayerInventory lt$getVirtualPlayerInventory() {
		return new VirtualPlayerInventory() {
			@Override
			public Slot getSlot(int slot) {
				assertSlot(slot, 0, 36);
				return CraftingMenuMixin.this.getSlot(10 + slot);
			}

			@Override
			public Slot getInventorySlot(int slot) {
				assertSlot(slot, 0, 27);
				return getSlot(slot);
			}

			@Override
			public Slot getHotbarSlot(int slot) {
				assertSlot(slot, 0, 9);
				return getSlot(slot+27);
			}

			@Override
			public Iterable<Slot> iterateInventorySlots() {
				return CraftingMenuMixin.this::createInventoryIterator;
			}

			@Override
			public Iterable<Slot> iterateHotbarSlots() {
				return CraftingMenuMixin.this::createHotbarIterator;
			}

			@Override
			public @NotNull Iterator<Slot> iterator() {
				return CraftingMenuMixin.this.createIterator();
			}
		};
	}

	@Unique
	private Iterator<Slot> createInventoryIterator() {
		VirtualPlayerInventory inventory = lt$getVirtualPlayerInventory();
		return new Iterator<>() {
			int i = 0;
			@Override
			public boolean hasNext() {
				return i < 27;
			}

			@Override
			public Slot next() {
				return inventory.getInventorySlot(i++);
			}
		};
	}
	@Unique
	private Iterator<Slot> createHotbarIterator() {
		VirtualPlayerInventory inventory = lt$getVirtualPlayerInventory();
		return new Iterator<>() {
			int i = 0;
			@Override
			public boolean hasNext() {
				return i < 9;
			}

			@Override
			public Slot next() {
				return inventory.getHotbarSlot(i++);
			}
		};
	}
	@Unique
	private Iterator<Slot> createIterator() {
		VirtualPlayerInventory inventory = lt$getVirtualPlayerInventory();
		return new Iterator<>() {
			int i = 0;
			@Override
			public boolean hasNext() {
				return i < 36;
			}

			@Override
			public Slot next() {
				return inventory.getSlot(i++);
			}
		};
	}

	@Override
	public VirtualCraftingInventory lt$getVirtualCraftingInventory() {
		return new VirtualCraftingInventory() {
			@Override
			public int getWidth() {
				return CraftingMenuMixin.this.getGridWidth();
			}

			@Override
			public int getHeight() {
				return CraftingMenuMixin.this.getGridHeight();
			}

			@Override
			public Slot getCraftingSlot(int num) {
				assertSlot(num, 0, getSize());
				return CraftingMenuMixin.this.getSlot(CRAFT_SLOT_START+num);
			}

			@Override
			public Slot getResultSlot() {
				return CraftingMenuMixin.this.getSlot(RESULT_SLOT);
			}
		};
	}
}

package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.screen.container.inventory_screen;

import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.violaflower.legacy_tweaks.client.gui.extention.CraftingMenuExtension;
import xyz.violaflower.legacy_tweaks.client.gui.extention.VirtualPlayerInventory;

import java.util.Iterator;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends RecipeBookMenu<CraftingInput, CraftingRecipe> implements CraftingMenuExtension {
	public InventoryMenuMixin(MenuType<?> menuType, int containerId) {
		super(menuType, containerId);
	}

	@Override
	public VirtualPlayerInventory lt$getVirtualPlayerInventory() {
		return new VirtualPlayerInventory() {
			private void assertSlot(int slot, int min, int max) {
				if (slot >= min && slot < max) return;
				throw new IllegalArgumentException();
			}
			@Override
			public Slot getSlot(int slot) {
				assertSlot(slot, 0, 36);
				return InventoryMenuMixin.this.getSlot(InventoryMenu.INV_SLOT_START+slot);
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
				return InventoryMenuMixin.this::createInventoryIterator;
			}

			@Override
			public Iterable<Slot> iterateHotbarSlots() {
				return InventoryMenuMixin.this::createHotbarIterator;
			}

			@Override
			public @NotNull Iterator<Slot> iterator() {
				return InventoryMenuMixin.this.createIterator();
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
}

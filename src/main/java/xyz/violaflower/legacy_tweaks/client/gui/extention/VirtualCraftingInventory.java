package xyz.violaflower.legacy_tweaks.client.gui.extention;

import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface VirtualCraftingInventory extends Iterable<Slot> {
	int getWidth();
	int getHeight();
	default int getSize() { return getWidth()*getHeight(); }
	Slot getCraftingSlot(int num);
	// 0-indexed
	default Slot getCraftingSlot(int x, int y) { return getCraftingSlot(x+y*getWidth()); }
	Slot getResultSlot();

	@Override
	default @NotNull Iterator<Slot> iterator() {
		return new Iterator<>() {
			int i = 0;

			@Override
			public boolean hasNext() {
				return i < getSize();
			}

			@Override
			public Slot next() {
				return getCraftingSlot(i++);
			}
		};
	}
}

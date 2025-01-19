package xyz.violaflower.legacy_tweaks.client.gui.extention;

import net.minecraft.world.inventory.Slot;

public interface VirtualPlayerInventory extends Iterable<Slot> {
	// from 0 to 36
	Slot getSlot(int slot);
	// from 0 to 27
	Slot getInventorySlot(int slot);
	// from 0 to 9
	Slot getHotbarSlot(int slot);
	default Iterable<Slot> iterateSlots() { return this; }
	Iterable<Slot> iterateInventorySlots();
	Iterable<Slot> iterateHotbarSlots();
}

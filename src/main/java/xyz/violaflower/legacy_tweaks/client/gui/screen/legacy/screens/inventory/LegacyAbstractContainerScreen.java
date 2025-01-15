package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

/// @see AbstractContainerScreen
public abstract class LegacyAbstractContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
	public LegacyAbstractContainerScreen(T menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
	}

	// don't break accesswideners!
	private boolean lt$isHovering(Slot slot, double mouseX, double mouseY) {
		return this.lt$isHovering(slot.x, slot.y, 16, 16, mouseX, mouseY);
	}

	// don't break accesswideners!
	protected boolean lt$isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
		int i = this.leftPos;
		int j = this.topPos;
		mouseX -= (double)i;
		mouseY -= (double)j;
		return mouseX >= (double)(x - 1) && mouseX < (double)(x + width + 1) && mouseY >= (double)(y - 1) && mouseY < (double)(y + height + 1);
	}
}

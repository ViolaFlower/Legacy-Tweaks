package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.container.menu;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public abstract class LegacyAbstractContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
	public LegacyAbstractContainerScreen(T menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
	}
}

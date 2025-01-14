package xyz.violaflower.legacy_tweaks.client.gui.element;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class LegacySlot extends Slot {
    public final int scale;

    public LegacySlot(Container container, int slot, int x, int y, int scale) {
        super(container, slot, x, y);
        this.scale = scale;
    }
}

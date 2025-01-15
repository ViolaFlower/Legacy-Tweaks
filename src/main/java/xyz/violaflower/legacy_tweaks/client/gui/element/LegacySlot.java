package xyz.violaflower.legacy_tweaks.client.gui.element;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

@Deprecated(forRemoval = true)
public class LegacySlot extends Slot {
    public final int size;
    public final float x;
    public final float y;

    public LegacySlot(Container container, int slot, float x, float y, int size) {
        super(container, slot, (int) x, (int) y);
        this.size = size;
        this.x = x;
        this.y = y;
    }
}

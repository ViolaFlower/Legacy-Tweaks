package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.slot;

import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;

@Mixin(Slot.class)
public class SlotMixin implements SlotExtension {
	@Unique
	private float visualX;
	@Unique
	private float visualY;
	@Unique
	private int size;
	@Override
	public void lt$setVisualX(float x) {
		this.visualX = x;
	}

	@Override
	public void lt$setVisualY(float y) {
		this.visualY = y;
	}

	@Override
	public float lt$getVisualX() {
		return this.visualX;
	}

	@Override
	public float lt$getVisualY() {
		return this.visualY;
	}

	@Override
	public void lt$setSize(int size) {
		this.size = size;
	}

	@Override
	public int lt$getSize() {
		return this.size;
	}
}

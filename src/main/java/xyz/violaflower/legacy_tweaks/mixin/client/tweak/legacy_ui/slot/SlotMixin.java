package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.slot;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;

@Mixin(Slot.class)
public class SlotMixin implements SlotExtension {
	@Shadow @Final public int x;
	@Shadow @Final public int y;
	@Unique
	private Float visualX;
	@Unique
	private Float visualY;
	@Unique
	private float size = 16;
	@Unique
	private Pair<ResourceLocation, ResourceLocation> noIconItem;
	@Override
	public void lt$setVisualX(Float x) {
		this.visualX = x;
	}

	@Override
	public void lt$setVisualY(Float y) {
		this.visualY = y;
	}

	@Override
	public Float lt$getVisualX() {
		return this.visualX == null ? this.x : this.visualX;
	}

	@Override
	public Float lt$getVisualY() {
		return this.visualY == null ? this.y : this.visualY;
	}

	@Override // TODO account for null
	public void lt$setSize(Float size) {
		this.size = size;
	}

	@Override
	public float lt$getSize() {
		return this.size;
	}

	@Override
	public void lt$setNoItemIcon(Pair<ResourceLocation, ResourceLocation> pair) {
		this.noIconItem = pair;
	}

	@Override
	public Pair<ResourceLocation, ResourceLocation> lt$getNoItemIcon() {
		return this.noIconItem;
	}
}

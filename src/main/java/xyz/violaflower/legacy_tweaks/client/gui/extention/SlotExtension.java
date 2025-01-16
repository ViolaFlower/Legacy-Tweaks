package xyz.violaflower.legacy_tweaks.client.gui.extention;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;

public interface SlotExtension {
	void lt$setVisualX(Float x);
	void lt$setVisualY(Float y);
	default void lt$setVisualX(float x) {
		this.lt$setVisualX((Float) x);
	}
	default void lt$setVisualY(float y) {
		this.lt$setVisualY((Float) y);
	}
	Float lt$getVisualX();
	Float lt$getVisualY();

	/// @param size size in scaled pixels
	void lt$setSize(Float size);
	default void lt$setSize(float size) {
		this.lt$setSize((Float) size);
	}
	/*let's assume it's an int*/float lt$getSize();

	void lt$setNoItemIcon(Pair<ResourceLocation, ResourceLocation> pair);

	Pair<ResourceLocation, ResourceLocation> lt$getNoItemIcon();
}

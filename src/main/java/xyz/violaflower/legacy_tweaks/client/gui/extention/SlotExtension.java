package xyz.violaflower.legacy_tweaks.client.gui.extention;

public interface SlotExtension {
	void lt$setVisualX(float x);
	void lt$setVisualY(float y);
	float lt$getVisualX();
	float lt$getVisualY();

	/// @param size size in scaled pixels
	void lt$setSize(int size);
	/*let's assume it's an int*/int lt$getSize();
}

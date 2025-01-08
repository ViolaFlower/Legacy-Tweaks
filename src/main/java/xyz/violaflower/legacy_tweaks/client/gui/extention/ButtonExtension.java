package xyz.violaflower.legacy_tweaks.client.gui.extention;

import net.minecraft.Util;

public interface ButtonExtension {
	long legacyTweaks$getClickDecayTo();
	void legacyTweaks$setClickDecayTo(long to);

	default boolean legacyTweaks$isDecayed() {
		return Util.getMillis() < legacyTweaks$getClickDecayTo();
	}
}

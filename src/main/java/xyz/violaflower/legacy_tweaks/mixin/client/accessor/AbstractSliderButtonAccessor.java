package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.client.gui.components.AbstractSliderButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractSliderButton.class)
public interface AbstractSliderButtonAccessor {
	@Invoker
	void callUpdateMessage();

	@Accessor("value") // need to namespace as setValue already exists.
	void legacyTweaks$setValue(double value);
}

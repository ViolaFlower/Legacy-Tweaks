package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net/minecraft/client/renderer/FogRenderer$FogData")
public interface FogDataAccessor {
	@Accessor
	void setStart(float start);

	@Accessor
	void setEnd(float end);
}

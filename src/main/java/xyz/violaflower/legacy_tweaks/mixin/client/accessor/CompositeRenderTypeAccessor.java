package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.client.renderer.RenderType.CompositeState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net/minecraft/client/renderer/RenderType$CompositeRenderType")
public interface CompositeRenderTypeAccessor {

    @Accessor(value = "state")
    CompositeState legacyTweaks$getState();
}

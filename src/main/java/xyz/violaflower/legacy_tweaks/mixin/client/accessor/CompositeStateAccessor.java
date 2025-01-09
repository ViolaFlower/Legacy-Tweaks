package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.client.renderer.RenderStateShard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net/minecraft/client/renderer/RenderType$CompositeState")
public interface CompositeStateAccessor {

    @Accessor("textureState")
    RenderStateShard.EmptyTextureStateShard legacyTweaks$getTextureState();
}

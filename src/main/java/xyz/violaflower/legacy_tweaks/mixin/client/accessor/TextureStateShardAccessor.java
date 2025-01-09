package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Optional;

@Mixin(targets = "net/minecraft/client/renderer/RenderStateShard$TextureStateShard")
public interface TextureStateShardAccessor {

    @Accessor("texture")
    Optional<ResourceLocation> legacyTweaks$getTexture();
}

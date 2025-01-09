package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.SequencedMap;

@Mixin(targets = "net/minecraft/client/renderer/MultiBufferSource$BufferSource")
public interface BufferSourceAccessor {

    @Accessor("sharedBuffer")
    ByteBufferBuilder legacyTweaks$getSharedBuffer();

    @Accessor("fixedBuffers")
    SequencedMap<RenderType, ByteBufferBuilder> legacyTweaks$getFixedBuffers();
}

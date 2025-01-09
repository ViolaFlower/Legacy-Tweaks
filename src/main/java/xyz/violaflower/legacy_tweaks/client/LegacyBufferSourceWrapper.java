package xyz.violaflower.legacy_tweaks.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.BufferSourceAccessor;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.CompositeRenderTypeAccessor;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.CompositeStateAccessor;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.TextureStateShardAccessor;

public class LegacyBufferSourceWrapper extends MultiBufferSource.BufferSource {
    public final MultiBufferSource.BufferSource source;
    private RenderType overrideRenderTpe;

    public LegacyBufferSourceWrapper(MultiBufferSource.BufferSource source) {
        super(((BufferSourceAccessor) source).legacyTweaks$getSharedBuffer(), ((BufferSourceAccessor) source).legacyTweaks$getFixedBuffers());
        this.source = source;
    }

    public static LegacyBufferSourceWrapper translucent(BufferSource source) {
        return new LegacyBufferSourceWrapper(source) {
            @Override
            public VertexConsumer getBuffer(RenderType renderType) {
                if (renderType == RenderType.cutout() || renderType == RenderType.solid() || renderType == RenderType.cutoutMipped())
                    return super.getBuffer(RenderType.translucent());
                if (renderType.format() == DefaultVertexFormat.NEW_ENTITY && renderType instanceof RenderType.CompositeRenderType r && ((CompositeStateAccessor)(Object) ((CompositeRenderTypeAccessor)(Object) r).legacyTweaks$getState()).legacyTweaks$getTextureState() instanceof RenderStateShard.TextureStateShard s && ((TextureStateShardAccessor) s).legacyTweaks$getTexture().isPresent())
                    return super.getBuffer(RenderType.entityTranslucentCull(((TextureStateShardAccessor) s).legacyTweaks$getTexture().get()));
                return super.getBuffer(renderType);
            }
        };
    }

    public static LegacyBufferSourceWrapper of(BufferSource source, RenderType overrideType) {
        LegacyBufferSourceWrapper wrapper = new LegacyBufferSourceWrapper(source);
        wrapper.setOverrideRenderType(overrideType);
        return wrapper;
    }

    @Override
    public void endLastBatch() {
        source.endLastBatch();
    }

    @Override
    public void endBatch() {
        source.endBatch();
    }

    @Override
    public void endBatch(RenderType renderType) {
        source.endBatch(renderType);
    }

    public void setOverrideRenderType(RenderType overrideRenderTpe) {
        this.overrideRenderTpe = overrideRenderTpe;
    }

    @Override
    public VertexConsumer getBuffer(RenderType renderType) {
        return source.getBuffer(overrideRenderTpe == null ? renderType : overrideRenderTpe);
    }
}

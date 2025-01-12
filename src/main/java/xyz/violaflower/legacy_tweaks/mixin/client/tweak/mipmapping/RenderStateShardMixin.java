package xyz.violaflower.legacy_tweaks.mixin.client.tweak.mipmapping;

import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.texture.TextureAtlas;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(RenderStateShard.class)
public class RenderStateShardMixin {

    @Mutable
    @Final
    @Shadow
    public static RenderStateShard.TextureStateShard BLOCK_SHEET;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void changeBlockSheet(CallbackInfo ci) {
        if (Tweaks.MIPMAPPING.fullCutoutMips.isEnabled()) BLOCK_SHEET = new RenderStateShard.TextureStateShard(TextureAtlas.LOCATION_BLOCKS, false, true);
    }
}

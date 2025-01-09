package xyz.violaflower.legacy_tweaks.mixin.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {
    @Inject(method = "bufferSource", at = @At("HEAD"), cancellable = true)
    private void bufferSource(CallbackInfoReturnable<MultiBufferSource.BufferSource> cir){
        if (ScreenUtil.guiBufferSourceOverride != null) cir.setReturnValue(ScreenUtil.guiBufferSourceOverride);
    }
}

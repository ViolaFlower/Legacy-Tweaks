package xyz.violaflower.legacy_tweaks.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.PanoramaRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.tweaks.impl.client.LegacyUIClient;

@Mixin(PanoramaRenderer.class)
public class PanoramaRendererMixin {
	@Inject(method = "render", at = @At("HEAD"), cancellable = true)
	private void rneder(GuiGraphics guiGraphics, int i, int j, float f, float g, CallbackInfo ci) {
		LegacyUIClient.render(guiGraphics, i, j, f, g);
		ci.cancel();
	}
}

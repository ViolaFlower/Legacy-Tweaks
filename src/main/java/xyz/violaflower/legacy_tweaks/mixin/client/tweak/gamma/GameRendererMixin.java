// Credits: Legacy4J 1.7.5

package xyz.violaflower.legacy_tweaks.mixin.client.tweak.gamma;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.PostChainAccessor;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

import static xyz.violaflower.legacy_tweaks.tweaks.impl.Gamma.gammaEffect;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;flush()V"))
	private void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
		if (gammaEffect != null && Tweaks.GAMMA.isEnabled()) {
			float gamma = (float) /* Yuck! */ (double) Tweaks.GAMMA.potency.get();
			guiGraphics.flush();
			RenderSystem.enableBlend();
			RenderSystem.disableDepthTest();
			((PostChainAccessor) gammaEffect).getPasses().forEach(p-> p.getEffect().safeGetUniform("gamma").set(gamma >= 0.5f ? (gamma - 0.5f) * 1.4f + 1.08f : gamma * 1.26f + 0.45f));
			gammaEffect.process(deltaTracker.getRealtimeDeltaTicks());
			RenderSystem.enableDepthTest();
			RenderSystem.disableBlend();
		}
		guiGraphics.flush();
	}


	@Inject(method = "resize",at = @At("RETURN"))
	public void resize(int i, int j, CallbackInfo ci) {
		if (gammaEffect != null) gammaEffect.resize(i,j);
	}
}

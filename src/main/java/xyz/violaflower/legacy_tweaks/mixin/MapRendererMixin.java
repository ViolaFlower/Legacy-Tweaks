package xyz.violaflower.legacy_tweaks.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.MapRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

@Mixin(MapRenderer.class)
public class MapRendererMixin {
	@Mixin(targets = "net/minecraft/client/gui/MapRenderer$MapInstance")
	public static class Instance {
		@Inject(method = "draw", at = @At("HEAD"))
		void injectDraw(PoseStack arg, MultiBufferSource bufferSource, boolean active, int packedLight, CallbackInfo ci) {
			if (TweakManager.getInstance().getTweak("mapcoords").isEnabled()) {
				Minecraft minecraft = Minecraft.getInstance();
				arg.pushPose();
				arg.translate(0, 0, -0.1);
				int x = minecraft.player.getBlockX();
				int y = minecraft.player.getBlockY();
				int z = minecraft.player.getBlockZ();
				minecraft.font.drawInBatch("X:%s Y:%s Z:%s".formatted(x, y, z), 0, 0, 0, false, arg.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, packedLight);
				arg.popPose();
			}
		}
	}
}
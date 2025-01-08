package xyz.violaflower.legacy_tweaks.mixin.common.tweak.map_changes;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

@Mixin(targets = "net/minecraft/client/gui/MapRenderer$MapInstance")
public class MapRendererMixin {
	@Inject(method = "draw", at = @At("HEAD"))
	private void injectDraw(PoseStack arg, MultiBufferSource bufferSource, boolean active, int packedLight, CallbackInfo ci) {
		if (active) return;
		if (mapCoords()) {
			Minecraft minecraft = Minecraft.getInstance();
			arg.pushPose();
			arg.translate(0, 0, -0.1);
			assert minecraft.player != null; // why would this be null
			int x = minecraft.player.getBlockX();
			int y = (int)minecraft.player.getEyeY();
			int z = minecraft.player.getBlockZ();
			minecraft.font.drawInBatch(Lang.Gameplay.MAP_COORDS.getString().formatted(x, y, z), /* I have no idea what these 3 numbers are */ 0, 0, 0, false, arg.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, packedLight);
			arg.popPose();
		}

		if (smallerMapContents()) {
			arg.pushPose();
			// the map is now 115.2
			arg.translate(10f, 10f, 0);
			arg.scale(27/32f, 27/32f, 1);
		}
	}

	@Inject(method = "draw", at = @At("RETURN"))
	private void injectDrawEnd(PoseStack arg, MultiBufferSource bufferSource, boolean active, int packedLight, CallbackInfo ci) {
		if (active) return;

		if (smallerMapContents()) {
			arg.popPose();
		}
	}
	@Unique
	private static boolean mapCoords() {
		return Tweaks.MAP_CHANGES.getSubTweak("mapCoordinates").isEnabled();
	}

	@Unique
	private static boolean smallerMapContents() {
		return Tweaks.MAP_CHANGES.getSubTweak("smallerMapContents").isEnabled();
	}
}

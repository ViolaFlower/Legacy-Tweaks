package xyz.violaflower.legacy_tweaks.tweaks.impl.client;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

public class LegacyUIClient {
	private static final ResourceLocation PANORAMA_DAY = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "textures/panorama/panorama_day.png");
	public static void renderPanorama(GuiGraphics guiGraphics, int width, int height, float fade, float partialTick) {
		guiGraphics.blit(PANORAMA_DAY, 0, 0, (float) (Minecraft.getInstance().options.panoramaSpeed().get() * Util.getMillis() * (1 / 60f)), 0, width, height, (int) (height * 5.75) /*preserve aspect ratio*/, height);
	}
}

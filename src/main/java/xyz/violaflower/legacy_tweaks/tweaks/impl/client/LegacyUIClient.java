package xyz.violaflower.legacy_tweaks.tweaks.impl.client;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

public class LegacyUIClient {
	private static final ResourceLocation PANORAMA_DAY = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "textures/panorama/panorama_day.png");
	public static void render(GuiGraphics guiGraphics, int width, int height, float fade, float partialTick) {
		guiGraphics.blit(PANORAMA_DAY, 0, 0, (float) (Math.sin(Util.getMillis() / 1000f) * 5), 0, width, height, (int) (width * 5.75), height);
	}
}

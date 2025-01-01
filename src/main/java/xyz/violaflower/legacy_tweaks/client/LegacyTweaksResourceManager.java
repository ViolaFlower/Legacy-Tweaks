// Credits: Legacy4J 1.7.5
package xyz.violaflower.legacy_tweaks.client;

import com.google.gson.JsonSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.Gamma;

import java.io.IOException;

public class LegacyTweaksResourceManager implements ResourceManagerReloadListener {
	public static final ResourceLocation GAMMA_LOCATION = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "shaders/post/gamma.json");
	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		Minecraft minecraft = Minecraft.getInstance();
		if (Gamma.gammaEffect != null) {
			Gamma.gammaEffect.close();
		}
		try {
			Gamma.gammaEffect = new PostChain(minecraft.getTextureManager(), resourceManager, minecraft.getMainRenderTarget(), GAMMA_LOCATION);
			Gamma.gammaEffect.resize(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
		} catch (IOException e) {
			System.err.printf("Failed to load gamma: %s, %s%n", GAMMA_LOCATION, e);
		} catch (JsonSyntaxException jsonSyntaxException) {
			System.err.printf("Failed to parse shader: %s, %s%n", GAMMA_LOCATION, jsonSyntaxException);
		}
	}
}

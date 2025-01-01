// Credits: Legacy4J 1.7.5
package xyz.violaflower.legacy_tweaks.client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.Gamma;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class LegacyTweaksResourceManager implements ResourceManagerReloadListener {
	public static final ResourceLocation GAMMA_LOCATION = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "shaders/post/gamma.json");
	public static final Codec<List<ResourceLocation>> RESOURCE_LOCATION_LIST_CODEC = ResourceLocation.CODEC.listOf();
	public static final Codec<List<ResourceLocation>> INTROS_CODEC = RecordCodecBuilder.create(instance ->
			instance.group(
					RESOURCE_LOCATION_LIST_CODEC.fieldOf("intros").forGetter(f -> f)
			).apply(instance, resourceLocations -> resourceLocations)
	);

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

		// TODO: we need the resources loaded _before_ the loading overlay starts, not after, probably move this into LoadingOverlayMixin?
		Optional<Resource> resource = resourceManager.getResource(ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "intros.json"));
		if (resource.isPresent()) {
			List<ResourceLocation> resourceLocations = null;
			try {
				resourceLocations = INTROS_CODEC.parse(JsonOps.INSTANCE, new Gson().fromJson(new String(resource.get().open().readAllBytes()), JsonElement.class)).resultOrPartial(System.err::println).orElseThrow();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("Intros: " + resourceLocations);
			//TODO please actually implement this
		}
	}
}

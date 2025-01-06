package xyz.violaflower.legacy_tweaks.client;

import xyz.violaflower.legacy_tweaks.LegacyTweaks;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;
//? if fabric {
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
//?} elif neoforge {
/*import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
*///?}

public class LegacyTweaksClient {
	public static void init() {
		//? if fabric {
		ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new IdentifiableResourceReloadListener() {
			private final LegacyTweaksResourceManager legacyTweaksResourceManager = new LegacyTweaksResourceManager();
			@Override
			public ResourceLocation getFabricId() {
				return ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "reload");
			}

			@Override
			public String getName() {
				return legacyTweaksResourceManager.getName();
			}

			@Override
			public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) {
				return legacyTweaksResourceManager.reload(preparationBarrier, resourceManager, profilerFiller,profilerFiller2, executor, executor2);
			}
		});
		//?} elif neoforge
		/*((ReloadableResourceManager) Minecraft.getInstance().getResourceManager()).registerReloadListener(new LegacyTweaksResourceManager());*/
	}
}

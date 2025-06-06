package xyz.violaflower.legacy_tweaks.tweaks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
//? if neoforge
/*import net.neoforged.fml.loading.FMLPaths;*/
//? if fabric
import net.fabricmc.loader.api.FabricLoader;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TweakManager implements TweakParent {
	public static final int version = 1;
    public static final TweakManager INSTANCE = new TweakManager();
    public static TweakManager getInstance() {
        return INSTANCE;
    }

    public Map<String, Tweak> tweaks = new LinkedHashMap<>();

    public void register(Tweak tweak) {
        tweaks.put(tweak.getTweakID(), tweak);
        tweak.onRegister();
    }

    @SuppressWarnings("unchecked")
    public <T extends Tweak> T getTweak(String id) {
        return (T) tweaks.get(id);
    }

    public static final Codec<List<String>> TWEAKS = Codec.STRING.listOf();
    public static void save() {
//        // get enabled tweaks and save them to a JSON?
//        JsonElement jsonElement = TWEAKS.encodeStart(JsonOps.INSTANCE, getInstance().tweaks.entrySet().stream().filter(a -> a.getValue().isEnabled()).map(Map.Entry::getKey).toList()).resultOrPartial(System.err::println).orElseThrow();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String json = gson.toJson(jsonElement);
		try {
			Files.writeString(getConfigFolder().resolve("legacy-tweaks.json"), new GsonBuilder().setPrettyPrinting().create().toJson(TweakState.encodeStatesToAnActuallyReadableFormat()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T extends Tweak> T getSubTweak(String tweakID) {
		return (T) tweaks.get(tweakID);
	}

	@Override
	public Map<String, Tweak> getSubTweaks() {
		return tweaks;
	}

	private static Path getConfigFolder() {
		//? if fabric
		return FabricLoader.getInstance().getConfigDir();
		//? if neoforge
		/*return FMLPaths.CONFIGDIR.get();*/
	}

    public static void load() {
		if (!getConfigFolder().resolve("legacy-tweaks.json").toFile().isFile()) save();
		try {
			TweakState.decodeStatesFromAnActuallyReadableFormat(new Gson().fromJson(Files.readString(getConfigFolder().resolve("legacy-tweaks.json")), JsonElement.class));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

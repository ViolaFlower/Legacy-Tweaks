package xyz.violaflower.legacy_tweaks.util;

//? if fabric {
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
//?} elif neoforge {
/*import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLLoader;
*///?}

public class PlatformUtil {
	public static Environment environment() {
		//? if fabric
		return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT ? Environment.CLIENT : Environment.DEDICATED_SERVER;
		//? if neoforge
		/*return FMLLoader.getDist() == Dist.CLIENT ? Environment.CLIENT : Environment.DEDICATED_SERVER;*/
	}

	public enum Environment {
		CLIENT,
		DEDICATED_SERVER
	}
}

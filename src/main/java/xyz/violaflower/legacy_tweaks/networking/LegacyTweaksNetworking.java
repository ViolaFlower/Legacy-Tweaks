package xyz.violaflower.legacy_tweaks.networking;

//? if fabric {
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
//?} elif neoforge {
/*import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
*///?}
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

//? if neoforge
/*@EventBusSubscriber(modid = LegacyTweaks.MOD_ID, bus = EventBusSubscriber.Bus.MOD)*/
public class LegacyTweaksNetworking {
	private static boolean initted = false;
	//? if neoforge {
	/*@SubscribeEvent
	public static void event(RegisterPayloadHandlersEvent event) {
		NetworkingAbstractions.event = event;
		init();
		NetworkingAbstractions.event = null;
	}
	*///?}

	public static void init() {
		//? if neoforge {
		/*if (NetworkingAbstractions.event == null) throw new IllegalStateException("init called outside of RegisterPayloadHandlersEvent.");
		*///?}
		if (initted) throw new IllegalStateException("init called more than once!");
		initted = true;
		LegacyTweaks.initNetworking();
		if (isClient()) {
			new ClientNetworking();
		}
	}

	private static boolean isClient() {
		//? if neoforge
		/*return FMLLoader.getDist().isClient();*/
		//? if fabric
		return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
	}
}

package xyz.violaflower.legacy_tweaks.networking;

//? if fabric {
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
//?} elif neoforge {
/*import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
*///?}
import org.jetbrains.annotations.ApiStatus;
import xyz.violaflower.legacy_tweaks.network.Networking;

@ApiStatus.Internal
//? if neoforge
/*@EventBusSubscriber(modid = LegacyTweaks.MOD_ID, bus = EventBusSubscriber.Bus.MOD)*/
public class LegacyTweaksNetworking {
	private static boolean payloadHandlersInitted = false;
	private static boolean configurationTasksInitted = false;
	private static boolean registeredCodecs = false;
	//? if neoforge {
	/*@SubscribeEvent
	public static void event(RegisterPayloadHandlersEvent event) {
		if (!registeredCodecs) {
			registerCodecs();
		}
		NetworkingAbstractions.event = event;
		registerPayloadHandlers();
		NetworkingAbstractions.event = null;
	}

	@SubscribeEvent
	public static void event(RegisterConfigurationTasksEvent event) {
		if (!registeredCodecs) {
			registerCodecs();
		}
		NetworkingAbstractions.configurationTasksEvent = event;
		registerConfigurationTasks();
		NetworkingAbstractions.configurationTasksEvent = null;
	}
	*///?}

	public static void registerPayloadHandlers() {
		//? if neoforge {
		/*if (NetworkingAbstractions.event == null) throw new IllegalStateException("registerPayloadHandlers called outside of RegisterPayloadHandlersEvent.");
		*///?}
		if (payloadHandlersInitted) throw new IllegalStateException("registerPayloadHandlers called more than once!");
		payloadHandlersInitted = true;
		Networking.registerPayloadHandlers();
		if (isClient()) {
			new ClientPayloadHandlerNetworking();
		}
	}

	public static void registerConfigurationTasks() {
		//? if neoforge {
		/*if (NetworkingAbstractions.configurationTasksEvent == null) throw new IllegalStateException("registerConfigurationTasks called outside of RegisterConfigurationTasksEvent.");
		*///?}
		if (configurationTasksInitted) throw new IllegalStateException("registerConfigurationTasks called more than once!");
		configurationTasksInitted = true;
		Networking.registerConfigurationTasks();
		if (isClient()) {
			new ClientConfigurationNetworking();
		}
	}

	public static void registerCodecs() {
		if (registeredCodecs) throw new IllegalStateException("registerCodecs called more than once!");
		registeredCodecs = true;
		Networking.registerNetworkingCodecs();
	}

	private static boolean isClient() {
		//? if neoforge
		/*return FMLLoader.getDist().isClient();*/
		//? if fabric
		return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
	}
}

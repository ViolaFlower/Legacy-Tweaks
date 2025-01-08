package xyz.violaflower.legacy_tweaks;

//? if neoforge
/*import net.neoforged.fml.common.Mod;*/
//? if fabric {
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
//?}
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.violaflower.legacy_tweaks.client.LegacyTweaksClient;
import xyz.violaflower.legacy_tweaks.client.gui.screen.config.LTScreen;
import xyz.violaflower.legacy_tweaks.helper.manager.item.ItemManager;
import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket;
import xyz.violaflower.legacy_tweaks.network.payload.CoolPacket2;
import xyz.violaflower.legacy_tweaks.networking.LegacyTweaksNetworking;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

import java.util.function.Supplier;

// TODO properly credit Legacy4J for gamma
//? if neoforge
/*@Mod(LegacyTweaks.MOD_ID)*/
public final class LegacyTweaks {
    public static final String MOD_ID = "legacy_tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Supplier<Boolean> isIntegratedServer = () -> false;

    //? if neoforge {
    /*public LegacyTweaks() {
        init();
    }
    *///?}

    public static void init() {
        LOGGER.info("initialized Legacy Tweaks successfully");
        ItemManager.init();

        TweakManager tweakManager = TweakManager.getInstance();
        tweakManager.register(Tweaks.WINDOW_TITLE);
        tweakManager.register(Tweaks.STUB);
        tweakManager.register(Tweaks.MAP_CHANGES);
        tweakManager.register(Tweaks.GAMMA);
        tweakManager.register(Tweaks.F3INFO);
        tweakManager.register(Tweaks.LEGACY_CHAT);
        tweakManager.register(Tweaks.CRASH);
        tweakManager.register(Tweaks.LEGACY_ATTACK);
        tweakManager.register(Tweaks.LEGACY_UI);
        tweakManager.register(Tweaks.EYE_CANDY);
        tweakManager.register(Tweaks.LEGACY_FOG);
        tweakManager.register(Tweaks.MIPMAPPING);
        TweakManager.load();

        // this only works on Fabric!
        //? if fabric {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(ClientCommandManager.literal("ltscreen").executes(c -> {Minecraft.getInstance().tell(() -> Minecraft.getInstance().setScreen(new LTScreen(Minecraft.getInstance().screen)));return 0;}));
            dispatcher.register(ClientCommandManager.literal("testc2s").executes(c -> {
                ClientPlayNetworking.send(new CoolPacket2(90, 25.50));
                return 0;
            }));
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, context, selection) -> {
            dispatcher.register(Commands.literal("tests2c").executes(c -> {
                ServerPlayNetworking.getSender(c.getSource().getPlayerOrException()).sendPacket(new CoolPacket(1));
                return 0;
            }));
        });
        //?}

        // TODO check for client side
        LegacyTweaksClient.init();

        //? if fabric {
        LegacyTweaksNetworking.registerCodecs();
        LegacyTweaksNetworking.registerPayloadHandlers();
        LegacyTweaksNetworking.registerConfigurationTasks();
        //?}
    }
}

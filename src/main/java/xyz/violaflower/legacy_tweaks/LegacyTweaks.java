package xyz.violaflower.legacy_tweaks;

//? if neoforge
/*import net.neoforged.fml.common.Mod;*/
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.violaflower.legacy_tweaks.gui.LTScreen;
import xyz.violaflower.legacy_tweaks.items.ItemManager;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;import xyz.violaflower.legacy_tweaks.tweaks.impl.*;

//? if neoforge
/*@Mod(LegacyTweaks.MOD_ID)*/
public final class LegacyTweaks {
    public static final String MOD_ID = "legacy_tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //? if neoforge {
    /*public LegacyTweaks() {
        init();
    }
    *///?}

    public static void init() {
        LOGGER.info("initialized Legacy Tweaks successfully");
        ItemManager.init();

        TweakManager tweakManager = TweakManager.getInstance();
        tweakManager.register(new WindowTitle());
       // tweakManager.register(new Stub());
        tweakManager.register(new MapChanges());
        tweakManager.register(new Gamma());
        tweakManager.register(Tweaks.F3INFO);
        tweakManager.register(new FatChat());
        tweakManager.register(Tweaks.CRASH);
        tweakManager.register(new LegacyAttack());

        // this only works on Fabric!
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {

            dispatcher.register(ClientCommandManager.literal("ltscreen").executes(c -> {Minecraft.getInstance().tell(() -> Minecraft.getInstance().setScreen(new LTScreen(Minecraft.getInstance().screen)));return 0;}));
        });
    }
}

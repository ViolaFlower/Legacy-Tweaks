package xyz.violaflower.legacy_tweaks.client;

//? if (fabric) {
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
//?}
import net.minecraft.client.KeyMapping;
//? if (neoforge) {
/*import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
*///?}
import xyz.violaflower.legacy_tweaks.client.gui.screen.config.LTScreen;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

public abstract class ClientKeyMapping {
    public static KeyMapping OPEN_CONFIG;

    public static void register() {
        OPEN_CONFIG = new KeyMapping(Lang.KeyBindings.OPEN_CONFIG.getString(), 90, Lang.KeyBindings.MAIN_CATEGORY.getString());

        //? if (fabric) {
        KeyBindingHelper.registerKeyBinding(OPEN_CONFIG);
        ClientTickEvents.END_CLIENT_TICK.register(client ->{
            while (OPEN_CONFIG.isDown()) client.setScreen(new LTScreen(client.screen));
        });
        //?}
        //? if (neoforge) {
        /*NeoForge.EVENT_BUS.addListener(ClientKeyMapping::goToConfig);
        *///?}
    }

    //? if (neoforge) {
    /*@SubscribeEvent
    public void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CONFIG);
    }

    public static void goToConfig(ClientTickEvent.Post event) {
        while (OPEN_CONFIG.consumeClick()) {
            Minecraft.getInstance().setScreen(new LTScreen(Minecraft.getInstance().screen));
        }
    }
    *///?}
}

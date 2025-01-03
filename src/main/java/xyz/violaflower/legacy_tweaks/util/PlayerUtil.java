package xyz.violaflower.legacy_tweaks.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.Level;

public class PlayerUtil {

    public static boolean isInOverworld() {
        ClientLevel level = Minecraft.getInstance().level;
        return level != null && level.dimension().equals(Level.OVERWORLD);
    }

    public static boolean isInNether() {
        ClientLevel level = Minecraft.getInstance().level;
        return level != null && level.dimension().equals(Level.NETHER);
    }

    public static boolean isInEnd() {
        ClientLevel level = Minecraft.getInstance().level;
        return level != null && level.dimension().equals(Level.END);
    }

    public static int getMinecraftRenderDistance() {
        return Minecraft.getInstance().options.renderDistance().get();
    }
}

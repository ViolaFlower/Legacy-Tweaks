package xyz.violaflower.legacy_tweaks.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

// These only work for the physical client!
public class PlayerUtil {

    public static ResourceKey<Level> getDimension() {
        ClientLevel level = Minecraft.getInstance().level;
        assert level != null;
        return level.dimension();
    }

    public static boolean isInOverworld() {
        return getDimension().equals(Level.OVERWORLD);
    }

    public static boolean isInNether() {
        return getDimension().equals(Level.NETHER);
    }

    public static boolean isInEnd() {
        return getDimension().equals(Level.END);
    }

    public static int getMinecraftRenderDistance() {
        return Minecraft.getInstance().options.renderDistance().get();
    }
}

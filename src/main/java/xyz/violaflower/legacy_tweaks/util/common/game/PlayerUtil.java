package xyz.violaflower.legacy_tweaks.util.common.game;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

// These only work for the physical client!
public class PlayerUtil {

    public static ResourceKey<Level> getDimension() {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return null;
        return level.dimension();
    }

    public static boolean isInOverworld() {
        ResourceKey<Level> dimension = getDimension();
        return dimension != null && dimension.equals(Level.OVERWORLD);
    }

    public static boolean isInNether() {
        ResourceKey<Level> dimension = getDimension();
        return dimension != null && dimension.equals(Level.OVERWORLD);
    }

    public static boolean isInEnd() {
        ResourceKey<Level> dimension = getDimension();
        return dimension != null && dimension.equals(Level.OVERWORLD);
    }

    public static int getMinecraftRenderDistance() {
        return Minecraft.getInstance().options.renderDistance().get();
    }
}

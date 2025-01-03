package xyz.violaflower.legacy_tweaks.util;

import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

public class ModAsset {

    public static ResourceLocation getResourceLocation(String path)
    {
        return ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, path);
    }

    public static ResourceLocation textureLocation(String path)
    {
        return getResourceLocation("textures/" + path);
    }

    public static ResourceLocation guiLocation(String path)
    {
        return getResourceLocation("textures/gui/" + path);
    }

    public static ResourceLocation spriteLocation(String path)
    {
        return ResourceLocation.withDefaultNamespace(LegacyTweaks.MOD_ID + "/" + path);
    }
}

package xyz.violaflower.legacy_tweaks.util.common.sound;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;
import xyz.violaflower.legacy_tweaks.util.common.assets.ModAsset;

public class SoundAsset {

    public static SoundEvent createVariableRangeSound(String path)
    {
        return SoundEvent.createVariableRangeEvent(ModAsset.getResourceLocation(path));
    }

    public static SoundEvent createFixedRangeSound(String path, float range)
    {
        return SoundEvent.createFixedRangeEvent(ModAsset.getResourceLocation(path), range);
    }

    public static SoundEvent createSound(String path) {
        ResourceLocation location = ModAsset.getResourceLocation(path);
        //? if neoforge {
        /*return createVariableRangeSound(path);
         *///?} elif fabric
        return Registry.register(BuiltInRegistries.SOUND_EVENT, location, SoundEvent.createVariableRangeEvent(location));
    }
}

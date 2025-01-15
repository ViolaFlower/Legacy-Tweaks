package xyz.violaflower.legacy_tweaks.util.common.sound;

import net.minecraft.sounds.SoundEvent;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

public interface Sounds {
    SoundEvent PRESS = SoundAsset.createSound("legacy.ui.press");
    SoundEvent FOCUS = SoundAsset.createSound("legacy.ui.focus");
    SoundEvent BACK = SoundAsset.createSound("legacy.ui.back");
    SoundEvent SCROLL = SoundAsset.createSound("legacy.ui.scroll");
    SoundEvent CRAFT = SoundAsset.createSound("legacy.ui.craft");
    SoundEvent CRAFT_FAIL = SoundAsset.createSound("legacy.ui.craft_fail");

    static void init() {
        LegacyTweaks.LOGGER.info("Sounds initialized!");
    }
}

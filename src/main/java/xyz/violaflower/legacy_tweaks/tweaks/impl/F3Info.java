package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.fabricmc.loader.impl.util.Localization;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class F3Info extends Tweak {
    public final BooleanOption showEnabledTweaks;

    public F3Info() {
        setTweakID("F3 Info");
        setTweakAuthor("DexrnZacAttack", "Jab125");
        setTweakDescription("Adds LegacyTweaks related info to the F3 debug screen.");
        // localize hopefully
        showEnabledTweaks = addBooleanOption("lt.tweaks.f3info.setting.showEnabledTweaks");
    }
}
package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class F3Info extends Tweak {
    public final BooleanOption showEnabledTweaks;

    public F3Info() {
        setTweakID("f3info");
        setTweakAuthor("DexrnZacAttack", "Jab125");
        // localize hopefully
        showEnabledTweaks = addBooleanOption("showenabledtweaks");
    }
}
package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class F3Info extends Tweak {
    public final BooleanOption showEnabledTweaks;

    public F3Info() {
        super("f3Info", true);
        setTweakAuthor("DexrnZacAttack", "Jab125");
        // localize hopefully
        // LOCALISED! - S_N00B
        showEnabledTweaks = addBooleanOption("showEnabledTweaks", true);
    }
}
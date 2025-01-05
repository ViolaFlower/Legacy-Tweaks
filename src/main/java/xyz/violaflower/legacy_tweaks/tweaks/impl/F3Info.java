package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class F3Info extends Tweak {
    public final BooleanOption showEnabledTweaks;

    public F3Info() {
        setTweakID("f3Info");
        setTweakName(Component.translatable("lt.tweaks.f3info"));
        setTweakAuthor("DexrnZacAttack", "Jab125");
        setTweakDescription(Component.translatable("lt.tweaks.f3info.enabledTweaks"));
        // localize hopefully
        // LOCALISED! - S_N00B
        showEnabledTweaks = addBooleanOption(Component.translatable("lt.tweaks.f3info.option.showenabledtweaks"));
    }
}
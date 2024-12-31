package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Crash extends Tweak {
    public Crash() {
        setTweakID("Crash");
        setTweakDescription("You should enable this ;)");
        setEnabled(false);
    }
    @Override
    public void onEnable() {
        onEnable();
    }
}

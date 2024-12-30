package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Crash extends Tweak {
    public Crash() {
        setTweakID("crash");
        setEnabled(false);
    }
    @Override
    public void onEnable() {
        onEnable();
    }
}

package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Stub extends Tweak {
    @Override
    public String getTweakAuthor() {
        return "DexrnZacAttack";
    }

    @Override
    public String getTweakDescription() {
        return "This does absolutely nothing.";
    }

    @Override
    public String getTweakID() {
        return "StubTweak";
    }
}

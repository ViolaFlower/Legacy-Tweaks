package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class FatChat extends Tweak {
    public FatChat() {
        setTweakID("Legacy Chat");
        setTweakAuthor("Jab125");
        setTweakDescription("Changes the chat size to be the same as it is in LCE. (fat chat :})");
    }

    public double getLineHeight() {
        return 1.0;
    }
}

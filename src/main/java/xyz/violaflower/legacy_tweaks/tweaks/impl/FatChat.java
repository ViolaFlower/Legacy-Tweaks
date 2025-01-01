package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class FatChat extends Tweak {
    public final BooleanOption messageWidthSpansScreen;
    public final BooleanOption legacyMessageHeight;

    public FatChat() {
        setTweakID("Legacy Chat");
        setTweakAuthor("DexrnZacAttack", "Jab125");
        setTweakDescription("Various tweaks to make the chat look like it does in LCE.");
        messageWidthSpansScreen = addBooleanOption("Legacy Chat Message Width");
        messageWidthSpansScreen.set(true);
        legacyMessageHeight = addBooleanOption("Legacy Chat Message Height");
        legacyMessageHeight.set(true);
    }

    public double getLineHeight() {
        return 1.0;
    }
}

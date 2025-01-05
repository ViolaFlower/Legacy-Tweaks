package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class FatChat extends Tweak {
    public final BooleanOption messageWidthSpansScreen;
    public final BooleanOption legacyMessageHeight;

    // Dexrn: TODO: Adding a sub tweak breaks top-level settings?
    public FatChat() {
        setTweakID("legacychat");
        setTweakAuthor("DexrnZacAttack", "Jab125");
        messageWidthSpansScreen = addBooleanOption("messagewidthspansscreen");
        messageWidthSpansScreen.set(true);
        legacyMessageHeight = addBooleanOption("legacymessageheight");
        legacyMessageHeight.set(true);
        addSubTweak(new DimensionNotification());
        addSubTweak(new NoAdvancementMessage());
    }

    public static class DimensionNotification extends Tweak {
        public final BooleanOption leaveMessage;
        public final BooleanOption entranceMessage;
        public DimensionNotification() {
            setTweakID("dimensionnotif");
            setTweakAuthor("DexrnZacAttack", "Jab125");
            setTweakVersion("1.0.0");
            setDefaultEnabled(true);
            entranceMessage = addBooleanOption("endenter");
            entranceMessage.set(true);
            leaveMessage = addBooleanOption("endleave");
            leaveMessage.set(true);
        }
    }

    public static class NoAdvancementMessage extends Tweak {
        public NoAdvancementMessage() {
            setTweakID("noachievementannouncement");
            setTweakAuthor("DexrnZacAttack");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }

    public double getLineHeight() {
        return 1.0;
    }
}

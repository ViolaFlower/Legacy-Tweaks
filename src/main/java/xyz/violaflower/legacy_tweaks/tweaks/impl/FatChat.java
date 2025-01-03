package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class FatChat extends Tweak {
    public final BooleanOption messageWidthSpansScreen;
    public final BooleanOption legacyMessageHeight;

    // Dexrn: TODO: Adding a sub tweak breaks top-level settings?
    public FatChat() {
        setTweakID("Legacy Chat");
        setTweakAuthor("DexrnZacAttack", "Jab125");
        setTweakDescription("Various tweaks to make the chat look and function like it does in LCE.");
        messageWidthSpansScreen = addBooleanOption("Legacy Chat Message Width");
        messageWidthSpansScreen.set(true);
        legacyMessageHeight = addBooleanOption("Legacy Chat Message Height");
        legacyMessageHeight.set(true);
        addSubTweak(new DimensionNotification());
        addSubTweak(new NoAdvancementMessage());
    }

    public static class DimensionNotification extends Tweak {
        public final BooleanOption leaveMessage;
        public final BooleanOption entranceMessage;
        public DimensionNotification() {
            setTweakID("Dimension Notifications");
            setTweakAuthor("DexrnZacAttack", "Jab125");
            setTweakDescription("Sends a message when a player enters/leaves a dimension.");
            setTweakVersion("1.0.0");
            setDefaultEnabled(true);
            entranceMessage = addBooleanOption("The End Entrance Message");
            entranceMessage.set(true);
            leaveMessage = addBooleanOption("The End Leave Message");
            leaveMessage.set(true);
        }
    }

    public static class NoAdvancementMessage extends Tweak {
        public NoAdvancementMessage() {
            setTweakID("No achievement announcement");
            setTweakAuthor("DexrnZacAttack");
            setTweakDescription("Doesn't send a message when a player gets an achievement.");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }

    public double getLineHeight() {
        return 1.0;
    }
}

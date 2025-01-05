package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class FatChat extends Tweak {
    public final BooleanOption messageWidthSpansScreen;
    public final BooleanOption legacyMessageHeight;

    // Dexrn: TODO: Adding a sub tweak breaks top-level settings?
    public FatChat() {
        setTweakID("legacyChat");
        setTweakAuthor("DexrnZacAttack", "Jab125");
        addSubTweak(new DimensionNotification());
        addSubTweak(new NoAdvancementMessage());
        messageWidthSpansScreen = addBooleanOption("messageWidthSpansScreen");
        messageWidthSpansScreen.set(true);
        legacyMessageHeight = addBooleanOption("legacyMessageHeight");
        legacyMessageHeight.set(true);
    }

    public static class DimensionNotification extends Tweak {
        public final BooleanOption leaveMessage;
        public final BooleanOption entranceMessage;
        public DimensionNotification() {
            setTweakID("dimensionNotifications");
            setTweakAuthor("DexrnZacAttack", "Jab125");
            setTweakVersion("1.0.0");
            setDefaultEnabled(true);
            entranceMessage = addBooleanOption("entranceMessage");
            entranceMessage.set(true);
            leaveMessage = addBooleanOption("departureMessage");
            leaveMessage.set(true);
        }
    }

    public static class NoAdvancementMessage extends Tweak {
        public NoAdvancementMessage() {
            setTweakID("noAchievementAnnouncement");
            setTweakAuthor("DexrnZacAttack");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }

    public double getLineHeight() {
        return 1.0;
    }
}

package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class FatChat extends Tweak {
    public final BooleanOption messageWidthSpansScreen;
    public final BooleanOption legacyMessageHeight;

    // Dexrn: TODO: Adding a sub tweak breaks top-level settings?
    public FatChat() {
        super("legacyChat", true);
        setTweakAuthor("DexrnZacAttack", "Jab125");
        addSubTweak(new DimensionNotification());
        addSubTweak(new NoAdvancementMessage());
        messageWidthSpansScreen = addBooleanOption("messageWidthSpansScreen", true);
        legacyMessageHeight = addBooleanOption("legacyMessageHeight", true);
    }

    public static class DimensionNotification extends Tweak {
        public final BooleanOption leaveMessage;
        public final BooleanOption entranceMessage;
        public DimensionNotification() {
            super("dimensionNotifications", true);
            setTweakAuthor("DexrnZacAttack", "Jab125");
            setTweakVersion("1.0.0");
            entranceMessage = addBooleanOption("entranceMessage", true);
            leaveMessage = addBooleanOption("departureMessage", true);
        }
    }

    public static class NoAdvancementMessage extends Tweak {
        public NoAdvancementMessage() {
            super("noAchievementAnnouncement", false);
            setTweakAuthor("DexrnZacAttack");
            setTweakVersion("1.0.0");
        }
    }

    public double getLineHeight() {
        return 1.0;
    }
}

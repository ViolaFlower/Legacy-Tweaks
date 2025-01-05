package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class FatChat extends Tweak {
    public final BooleanOption messageWidthSpansScreen;
    public final BooleanOption legacyMessageHeight;

    // Dexrn: TODO: Adding a sub tweak breaks top-level settings?
    public FatChat() {
        setTweakID("legacyChat");
        setTweakName(Component.translatable("lt.tweaks.legacychat"));
        setTweakAuthor("DexrnZacAttack", "Jab125");
        setTweakDescription(Component.translatable("lt.tweaks.legacychat.description"));
        addSubTweak(new DimensionNotification());
        addSubTweak(new NoAdvancementMessage());
        messageWidthSpansScreen = addBooleanOption(Component.translatable("lt.tweaks.legacychat.option.messagewidthspansscreen"));
        messageWidthSpansScreen.set(true);
        legacyMessageHeight = addBooleanOption(Component.translatable("lt.tweaks.legacychat.option.legacymessageheight"));
        legacyMessageHeight.set(true);
    }

    public static class DimensionNotification extends Tweak {
        public final BooleanOption leaveMessage;
        public final BooleanOption entranceMessage;
        public DimensionNotification() {
            setTweakID("dimensionNotifications");
            setTweakName(Component.translatable("lt.tweaks.legacychat.dimensionnotif"));
            setTweakAuthor("DexrnZacAttack", "Jab125");
            setTweakDescription(Component.translatable("lt.tweaks.legacychat.dimensionnotif.description"));
            setTweakVersion("1.0.0");
            setDefaultEnabled(true);
            entranceMessage = addBooleanOption(Component.translatable("lt.tweaks.legacychat.dimensionnotif.option.enter"));
            entranceMessage.set(true);
            leaveMessage = addBooleanOption(Component.translatable("lt.tweaks.legacychat.dimensionnotif.option.leave"));
            leaveMessage.set(true);
        }
    }

    public static class NoAdvancementMessage extends Tweak {
        public NoAdvancementMessage() {
            setTweakID("noAchievementAnnouncement");
            setTweakName(Component.translatable("lt.tweaks.legacychat.noachievementannouncement"));
            setTweakAuthor("DexrnZacAttack");
            setTweakDescription(Component.translatable("lt.tweaks.legacychat.noachievementannouncement.description"));
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }

    public double getLineHeight() {
        return 1.0;
    }
}

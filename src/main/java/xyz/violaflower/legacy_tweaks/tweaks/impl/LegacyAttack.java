package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

public class LegacyAttack extends Tweak {
    public LegacyAttack() {
        setTweakID("legacyAttack");
        setTweakName(Component.translatable("lt.tweaks.legacyattack"));
        setTweakAuthor("Jab125", "DexrnZacAttack");
        // pls change later
        setTweakDescription(Component.translatable("lt.tweaks.legacyattack.description"));
        setTweakVersion("1.0.0");
        setDefaultEnabled(false);
        setGroup();
        addSubTweak(new LegacyAttack.LessAxeDamage());
        addSubTweak(new LegacyAttack.RemoveCooldown());
    }

    public static class RemoveCooldown extends Tweak {
        public RemoveCooldown() {
            setTweakID("noAttackCooldown");
            setTweakName(Component.translatable("lt.tweaks.legacyattack.cooldown"));
            setTweakAuthor("Jab125");
            setTweakDescription(Component.translatable("lt.tweaks.legacyattack.cooldown.description"));
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }
    public static class LessAxeDamage extends Tweak {
        public LessAxeDamage() {
            setTweakID("lessAxeDamage");
            setTweakName(Component.translatable("lt.tweaks.legacyattack.lessaxedamage"));
            setTweakAuthor("Jab125");
            setTweakDescription(Component.translatable("lt.tweaks.legacyattack.lessaxedamage.description"));
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }
}

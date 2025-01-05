package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

public class LegacyAttack extends Tweak {
    public LegacyAttack() {
        setTweakID("legacyAttack");
        setTweakAuthor("Jab125", "DexrnZacAttack");
        // pls change later
        setTweakVersion("1.0.0");
        setDefaultEnabled(false);
        setGroup();
        addSubTweak(new LegacyAttack.LessAxeDamage());
        addSubTweak(new LegacyAttack.RemoveCooldown());
    }

    public static class RemoveCooldown extends Tweak {
        public RemoveCooldown() {
            setTweakID("noAttackCooldown");
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }
    public static class LessAxeDamage extends Tweak {
        public LessAxeDamage() {
            setTweakID("lessAxeDamage");
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }
}

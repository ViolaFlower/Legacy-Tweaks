package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

public class LegacyAttack extends Tweak {
    public LegacyAttack() {
        super("legacyAttack", false);
        setTweakAuthor("Jab125", "DexrnZacAttack");
        // pls change later
        setTweakVersion("1.0.0");
        setGroup();
        addSubTweak(new LegacyAttack.LessAxeDamage());
        addSubTweak(new LegacyAttack.RemoveCooldown());
    }

    public static class RemoveCooldown extends Tweak {
        public RemoveCooldown() {
            super("noAttackCooldown", false);
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
        }
    }
    public static class LessAxeDamage extends Tweak {
        public LessAxeDamage() {
            super("lessAxeDamage", false);
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
        }
    }
}

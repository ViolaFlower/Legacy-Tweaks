package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

public class LegacyAttack extends Tweak {
    public LegacyAttack() {
        setTweakID("legacyattack");
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
            setTweakID("cooldown");
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }
    public static class LessAxeDamage extends Tweak {
        public LessAxeDamage() {
            setTweakID("lessaxedamage");
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }
}

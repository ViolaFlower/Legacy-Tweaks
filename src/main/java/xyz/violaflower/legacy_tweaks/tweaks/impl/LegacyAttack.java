package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

public class LegacyAttack extends Tweak {
    public LegacyAttack() {
        setTweakID("Legacy Attack");
        setTweakAuthor("Jab125", "DexrnZacAttack");
        // pls change later
        setTweakDescription("Various tweaks to fighting.");
        setTweakVersion("1.0.0");
        setDefaultEnabled(false);
        setGroup();
        addSubTweak(new LegacyAttack.LessAxeDamage());
        addSubTweak(new LegacyAttack.RemoveCooldown());
    }

    public static class RemoveCooldown extends Tweak {
        public RemoveCooldown() {
            setTweakID("No Attack Cooldown");
            setTweakAuthor("Jab125");
            setTweakDescription("Removes attack cooldown from every item, excluding the Mace.");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }
    public static class LessAxeDamage extends Tweak {
        public LessAxeDamage() {
            setTweakID("Less Axe Damage");
            setTweakAuthor("Jab125");
            setTweakDescription("Restores the Axe's damage to its pre-Combat Update (1.9) values.");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }
}

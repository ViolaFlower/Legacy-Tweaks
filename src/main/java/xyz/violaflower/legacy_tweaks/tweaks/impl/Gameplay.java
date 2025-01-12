package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Gameplay extends Tweak {
    public final WaterMechanics waterMechanics;
    public final CombatMechanics combatMechanics;

    public Gameplay() {
        super("gameplay", true);
        setTweakAuthor("Permdog99");
        setTweakVersion("1.0.0");
        setGroup();
        addSubTweak(waterMechanics = new WaterMechanics());
        addSubTweak(combatMechanics = new CombatMechanics());
    }

    public static class WaterMechanics extends Tweak {
        public final BooleanOption alwaysSwimInWater;
        public WaterMechanics() {
            super("waterMechanics", true);
            setTweakAuthor("Permdog99");
            setTweakVersion("1.0.0");
            alwaysSwimInWater = addBooleanOption("alwaysSwimInWater", true);
        }
    }

    public static class CombatMechanics extends Tweak {
        public final BooleanOption noAttackCooldown;
        public final BooleanOption lessAxeDamage;
        public CombatMechanics() {
            super("combatMechanics", true);
            setTweakAuthor("Jab125", "DexrnZacAttack");
            setTweakVersion("1.0.0");
            noAttackCooldown = addBooleanOption("noAttackCooldown", true);
            lessAxeDamage = addBooleanOption("lessAxeDamage", true);
        }
    }
}

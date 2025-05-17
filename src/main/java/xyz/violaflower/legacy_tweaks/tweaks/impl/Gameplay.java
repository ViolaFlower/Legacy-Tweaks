package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Gameplay extends Tweak {
    public final WaterMechanics waterMechanics;
    public final CombatMechanics combatMechanics;
    public final MapMechanics mapMechanics;
    public final ChatMechanics chatMechanics;

    public Gameplay() {
        super("gameplay", true);
        setTweakAuthor("AzaleaCatgirl99");
        setTweakVersion("1.0.0");
        setGroup();
        addSubTweak(waterMechanics = new WaterMechanics());
        addSubTweak(combatMechanics = new CombatMechanics());
        addSubTweak(mapMechanics = new MapMechanics());
        addSubTweak(chatMechanics = new ChatMechanics());
    }

    public static class WaterMechanics extends Tweak {
        public final BooleanOption alwaysSwimInWater;
        public WaterMechanics() {
            super("waterMechanics", true);
            setTweakAuthor("AzaleaCatgirl99");
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

    public static class MapMechanics extends Tweak {
        public final BooleanOption mapCoordinates;
        public final BooleanOption smallerMapContents;
        public MapMechanics() {
            super("mapMechanics", true);
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
            mapCoordinates = addBooleanOption("mapCoordinates", true);
            smallerMapContents = addBooleanOption("smallerMapContents", true);
        }
    }

    public static class ChatMechanics extends Tweak {
        public final DimensionNotifications dimensionNotifications;
        public final AdvancementsChat advancementsChat;
        public ChatMechanics() {
            super("chatMechanics", true);
            setTweakAuthor("DexrnZacAttack");
            addSubTweak(dimensionNotifications = new DimensionNotifications());
            addSubTweak(advancementsChat = new AdvancementsChat());
        }

        public static class DimensionNotifications extends Tweak {
            public final BooleanOption leaveMessage;
            public final BooleanOption entranceMessage;
            public DimensionNotifications() {
                super("dimensionNotifications", true);
                setTweakAuthor("DexrnZacAttack");
                entranceMessage = addBooleanOption("entranceMessage", true);
                leaveMessage = addBooleanOption("leaveMessage", true);
            }
        }

        public static class AdvancementsChat extends Tweak {
            public final BooleanOption noAdvancementMessage;
            public AdvancementsChat() {
                super("advancementsChat", true);
                setTweakAuthor("DexrnZacAttack");
                noAdvancementMessage = addBooleanOption("noAdvancementMessage", true);
            }
        }
    }
}

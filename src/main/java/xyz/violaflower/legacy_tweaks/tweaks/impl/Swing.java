package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Swing extends Tweak {
    public final Camera camera;

    public Swing() {
        super("swing", true);
        setTweakAuthor("AzaleaCatgirl99");
        setGroup();

        addSubTweak(camera = new Camera());
    }

    public static class Camera extends Tweak {
        public final BooleanOption attachThirdPersonToHead;
        public final BooleanOption invertedThirdPerson;
        public Camera() {
            super("camera", true);
            setTweakAuthor("AzaleaCatgirl99");

            attachThirdPersonToHead = addBooleanOption("attachThirdPersonToHead", true);
            invertedThirdPerson = addBooleanOption("invertedThirdPerson", false);
        }
    }
}

package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class MapChanges extends Tweak {
    public MapChanges() {
        setTweakID("Map Changes");
        setTweakAuthor("Jab125");
        setTweakDescription("Various tweaks to maps.");
        setTweakVersion("1.0.0");
        setGroup();
        addSubTweak(new MapCoordinates());
        addSubTweak(new SmallerMapContents());
    }
    public static class MapCoordinates extends Tweak {
        public MapCoordinates() {
            setTweakID("Map Coordinates");
            setTweakAuthor("Jab125");
            setTweakVersion("1.1.0");
            setTweakDescription("Adds coordinates to maps!");
        }
    }

    public static class SmallerMapContents extends Tweak {
        public SmallerMapContents() {
            setTweakID("Smaller Map Contents");
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
            setTweakDescription("Makes the contents of maps smaller.");
            setDefaultEnabled(false);
        }
    }
}
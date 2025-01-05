package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class MapChanges extends Tweak {
    public MapChanges() {
        setTweakID("mapChanges");
        setTweakAuthor("Jab125");
        setTweakVersion("1.0.0");
        setGroup();
        addSubTweak(new MapCoordinates());
        addSubTweak(new SmallerMapContents());
    }
    public static class MapCoordinates extends Tweak {
        public MapCoordinates() {
            setTweakID("mapCoordinates");
            setTweakAuthor("Jab125");
            setTweakVersion("1.1.0");
        }
    }

    public static class SmallerMapContents extends Tweak {
        public SmallerMapContents() {
            setTweakID("smallerMapContents");
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
            setDefaultEnabled(false);
        }
    }
}
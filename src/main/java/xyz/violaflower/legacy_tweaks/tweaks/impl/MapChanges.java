package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class MapChanges extends Tweak {
    public MapChanges() {
        setTweakID("mapChanges");
        setTweakName(Component.translatable("lt.tweaks.mapchanges"));
        setTweakAuthor("Jab125");
        setTweakDescription(Component.translatable("lt.tweaks.mapchanges.description"));
        setTweakVersion("1.0.0");
        setGroup();
        addSubTweak(new MapCoordinates());
        addSubTweak(new SmallerMapContents());
    }
    public static class MapCoordinates extends Tweak {
        public MapCoordinates() {
            setTweakID("mapCoordinates");
            setTweakName(Component.translatable("lt.tweaks.mapchanges.coords"));
            setTweakAuthor("Jab125");
            setTweakVersion("1.1.0");
            setTweakDescription(Component.translatable("lt.tweaks.mapchanges.coords.description"));
        }
    }

    public static class SmallerMapContents extends Tweak {
        public SmallerMapContents() {
            setTweakID("smallerMapContents");
            setTweakName(Component.translatable("lt.tweaks.mapchanges.smallercontent"));
            setTweakAuthor("Jab125");
            setTweakVersion("1.0.0");
            setTweakDescription(Component.translatable("lt.tweaks.mapchanges.smallercontent.description"));
            setDefaultEnabled(false);
        }
    }
}
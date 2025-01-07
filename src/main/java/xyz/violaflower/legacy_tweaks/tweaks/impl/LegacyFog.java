package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.mixin.client.FogDataAccessor;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.PlayerUtil;

// TODO find best settings for the Nether and End, based on PS4 and XONE render distance
public class LegacyFog extends Tweak {
    public final OverworldFog overworldFog;
    public final NetherFog netherFog;
    public final EndFog endFog;
    public final ModdedFog moddedFog;

    public LegacyFog() {
        super("legacyFog", true);
        setGroup();
        setTweakAuthor("Permdog99");

        addSubTweak(overworldFog = new OverworldFog());
        addSubTweak(netherFog = new NetherFog());
        addSubTweak(endFog = new EndFog());
        addSubTweak(moddedFog = new ModdedFog());
    }


    public static class OverworldFog extends Tweak {
        public final DoubleSliderOption terrainFogStartOverworld;
        public final DoubleSliderOption terrainFogStopOverworld;
        public OverworldFog() {
            super("overworldFog", true);
            setTweakAuthor("Permdog99");

            terrainFogStartOverworld = addSliderOption("terrainFogStart", 0.75d, 0d, 1d);
            terrainFogStopOverworld = addSliderOption("terrainFogStop", 0.5d, 0d, 1d);
        }
    }

    public static class NetherFog extends Tweak {
        public final DoubleSliderOption terrainFogStartNether;
        public final DoubleSliderOption terrainFogStopNether;
        public NetherFog() {
            super("netherFog", true);
            setTweakAuthor("Permdog99");

            terrainFogStartNether = addSliderOption("terrainFogStart", 0.75d, 0d, 1d);
            terrainFogStopNether = addSliderOption("terrainFogStop", 0.5d, 0d, 1d);
        }
    }

    public static class EndFog extends Tweak {
        public final DoubleSliderOption terrainFogStartEnd;
        public final DoubleSliderOption terrainFogStopEnd;
        public EndFog() {
            super("endFog", true);
            setTweakAuthor("Permdog99");

            terrainFogStartEnd = addSliderOption("terrainFogStart", 0.75d, 0d, 1d);
            terrainFogStopEnd = addSliderOption("terrainFogStop", 0.5d, 0d, 1d);
        }
    }

    public static class ModdedFog extends Tweak {
        public final DoubleSliderOption terrainFogStartModded;
        public final DoubleSliderOption terrainFogStopModded;
        public ModdedFog() {
            super("moddedDimensionsFog", true);
            setTweakAuthor("Permdog99");

            terrainFogStartModded = addSliderOption("terrainFogStart", 0.75d, 0d, 1d);
            terrainFogStopModded = addSliderOption("terrainFogEnd", 0.5d, 0d, 1d);
        }
    }

    public static void setFogStartConditional(FogDataAccessor fogData) {
        if (PlayerUtil.isInOverworld() && Tweaks.LEGACY_FOG.overworldFog.isEnabled()) {
            changeFogStart(Tweaks.LEGACY_FOG.overworldFog.terrainFogStartOverworld, fogData);
        } else if (PlayerUtil.isInNether() && Tweaks.LEGACY_FOG.netherFog.isEnabled()) {
            changeFogStart(Tweaks.LEGACY_FOG.netherFog.terrainFogStartNether, fogData);
        } else if (PlayerUtil.isInEnd() && Tweaks.LEGACY_FOG.endFog.isEnabled()) {
            changeFogStart(Tweaks.LEGACY_FOG.endFog.terrainFogStartEnd, fogData);
        } else if (Tweaks.LEGACY_FOG.moddedFog.isEnabled()) {
            changeFogStart(Tweaks.LEGACY_FOG.moddedFog.terrainFogStartModded, fogData);
        }
    }

    public static void setFogStopConditional(FogDataAccessor fogData, float f) {
        if (PlayerUtil.isInOverworld() && Tweaks.LEGACY_FOG.overworldFog.isEnabled()) {
            changeFogStop(Tweaks.LEGACY_FOG.overworldFog.terrainFogStopOverworld, fogData, f);
        } else if (PlayerUtil.isInNether() && Tweaks.LEGACY_FOG.netherFog.isEnabled()) {
            changeFogStop(Tweaks.LEGACY_FOG.netherFog.terrainFogStopNether, fogData, f);
        } else if (PlayerUtil.isInEnd() && Tweaks.LEGACY_FOG.endFog.isEnabled()) {
            changeFogStop(Tweaks.LEGACY_FOG.endFog.terrainFogStopEnd, fogData, f);
        } else {
            changeFogStop(Tweaks.LEGACY_FOG.moddedFog.terrainFogStopModded, fogData, f);
        }
    }

    public static void changeFogStart(DoubleSliderOption tweak, FogDataAccessor fogData) {
        fogData.setStart((PlayerUtil.getMinecraftRenderDistance()*16)/tweak.get().floatValue() - (PlayerUtil.getMinecraftRenderDistance()*16));
    }

    public static void changeFogStop(DoubleSliderOption tweak, FogDataAccessor fogData, float f) {
        fogData.setEnd(f * tweak.get().floatValue() * 2);
    }
}

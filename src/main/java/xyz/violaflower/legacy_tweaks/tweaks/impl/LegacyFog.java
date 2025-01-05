package xyz.violaflower.legacy_tweaks.tweaks.impl;

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
        setTweakID("legacyfog");
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
            setTweakID("overworldfog");
            setTweakAuthor("Permdog99");
            setEnabled(true, false);

            terrainFogStartOverworld = addSliderOption("overworldstartfog", 0d, 1d);
            terrainFogStopOverworld = addSliderOption("overworldendfog", 0d, 1d);

            terrainFogStartOverworld.set(0.75d);
            terrainFogStopOverworld.set(0.5d);
        }
    }

    public static class NetherFog extends Tweak {
        public final DoubleSliderOption terrainFogStartNether;
        public final DoubleSliderOption terrainFogStopNether;
        public NetherFog() {
            setTweakID("netherfog");
            setTweakAuthor("Permdog99");
            setEnabled(true, false);

            terrainFogStartNether = addSliderOption("netherstartfog", 0d, 1d);
            terrainFogStopNether = addSliderOption("netherendfog", 0d, 1d);

            terrainFogStartNether.set(0.75d);
            terrainFogStopNether.set(0.5d);
        }
    }

    public static class EndFog extends Tweak {
        public final DoubleSliderOption terrainFogStartEnd;
        public final DoubleSliderOption terrainFogStopEnd;
        public EndFog() {
            setTweakID("endfog");
            setTweakAuthor("Permdog99");
            setEnabled(true, false);

            terrainFogStartEnd = addSliderOption("endstartfog", 0d, 1d);
            terrainFogStopEnd = addSliderOption("endendfog", 0d, 1d);

            terrainFogStartEnd.set(0.75d);
            terrainFogStopEnd.set(0.5d);
        }
    }

    public static class ModdedFog extends Tweak {
        public final DoubleSliderOption terrainFogStartModded;
        public final DoubleSliderOption terrainFogStopModded;
        public ModdedFog() {
            setTweakID("moddedfog");
            setTweakAuthor("Permdog99");
            setEnabled(true, false);

            terrainFogStartModded = addSliderOption("moddedstartfog", 0d, 1d);
            terrainFogStopModded = addSliderOption("moddedendfog", 0d, 1d);

            terrainFogStartModded.set(0.75d);
            terrainFogStopModded.set(0.5d);
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

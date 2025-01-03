package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.PlayerUtil;

// TODO find best settings for the Nether and End, based on PS4 and XONE render distance
public class LegacyFog extends Tweak {
    public final OverworldFog overworldFog;
    public final NetherFog netherFog;
    public final EndFog endFog;
    public final ModdedFog moddedFog;

    public LegacyFog() {
        setTweakID("Legacy Fog");
        setGroup();
        setTweakAuthor("Permdog99");
        setTweakDescription("Customize the fog of certain or all dimensions");

        addSubTweak(overworldFog = new OverworldFog());
        addSubTweak(netherFog = new NetherFog());
        addSubTweak(endFog = new EndFog());
        addSubTweak(moddedFog = new ModdedFog());
    }


    public static class OverworldFog extends Tweak {
        public final DoubleSliderOption terrainFogStartOverworld;
        public final DoubleSliderOption terrainFogStopOverworld;
        public OverworldFog() {
            setTweakID("Overworld Fog");
            setTweakAuthor("Permdog99");
            setTweakDescription("Customize the fog of the Overworld");
            setEnabled(true, false);

            terrainFogStartOverworld = addSliderOption("fogStart", 0d, 1d);
            terrainFogStopOverworld = addSliderOption("fogStop", 0d, 1d);

            terrainFogStartOverworld.set(0.75d);
            terrainFogStopOverworld.set(0.5d);
        }
    }

    public static class NetherFog extends Tweak {
        public final DoubleSliderOption terrainFogStartNether;
        public final DoubleSliderOption terrainFogStopNether;
        public NetherFog() {
            setTweakID("Nether Fog");
            setTweakAuthor("Permdog99");
            setTweakDescription("Customize the fog of the Nether");
            setEnabled(true, false);

            terrainFogStartNether = addSliderOption("fogStart", 0d, 1d);
            terrainFogStopNether = addSliderOption("fogStop", 0d, 1d);

            terrainFogStartNether.set(0.75d);
            terrainFogStopNether.set(0.5d);
        }
    }

    public static class EndFog extends Tweak {
        public final DoubleSliderOption terrainFogStartEnd;
        public final DoubleSliderOption terrainFogStopEnd;
        public EndFog() {
            setTweakID("End Fog");
            setTweakAuthor("Permdog99");
            setTweakDescription("Customize the fog of the End");
            setEnabled(true, false);

            terrainFogStartEnd = addSliderOption("fogStart", 0d, 1d);
            terrainFogStopEnd = addSliderOption("fogStop", 0d, 1d);

            terrainFogStartEnd.set(0.75d);
            terrainFogStopEnd.set(0.5d);
        }
    }

    public static class ModdedFog extends Tweak {
        public final DoubleSliderOption terrainFogStartModded;
        public final DoubleSliderOption terrainFogStopModded;
        public ModdedFog() {
            setTweakID("Modded Dimensions Fog");
            setTweakAuthor("Permdog99");
            setTweakDescription("Customize the fog of all modded dimensions");
            setEnabled(true, false);

            terrainFogStartModded = addSliderOption("fogStart", 0d, 1d);
            terrainFogStopModded = addSliderOption("fogStop", 0d, 1d);

            terrainFogStartModded.set(0.75d);
            terrainFogStopModded.set(0.5d);
        }
    }

    public static void setFogStartConditional(FogRenderer.FogData fogData) {
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

    public static void setFogStopConditional(FogRenderer.FogData fogData, float f) {
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

    public static void changeFogStart(DoubleSliderOption tweak, FogRenderer.FogData fogData) {
        fogData.start = (PlayerUtil.getMinecraftRenderDistance()*16)/tweak.get().floatValue() - (PlayerUtil.getMinecraftRenderDistance()*16);
    }

    public static void changeFogStop(DoubleSliderOption tweak, FogRenderer.FogData fogData, float f) {
        fogData.end = f * tweak.get().floatValue() * 2;
    }
}

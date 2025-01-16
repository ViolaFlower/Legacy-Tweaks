package xyz.violaflower.legacy_tweaks.helper.tweak.eye_candy.fog;

import xyz.violaflower.legacy_tweaks.mixin.client.accessor.FogDataAccessor;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.game.PlayerUtil;

public class FogHelper {

    public static void setFogStartConditional(FogDataAccessor fogData) {
        if (PlayerUtil.isInOverworld() && Tweaks.EYE_CANDY.legacyFog.overworldFog.isEnabled()) {
            changeFogStart(Tweaks.EYE_CANDY.legacyFog.overworldFog.terrainFogStartOverworld, fogData);
        } else if (PlayerUtil.isInNether() && Tweaks.EYE_CANDY.legacyFog.netherFog.isEnabled()) {
            changeFogStart(Tweaks.EYE_CANDY.legacyFog.netherFog.terrainFogStartNether, fogData);
        } else if (PlayerUtil.isInEnd() && Tweaks.EYE_CANDY.legacyFog.endFog.isEnabled()) {
            changeFogStart(Tweaks.EYE_CANDY.legacyFog.endFog.terrainFogStartEnd, fogData);
        } else if (Tweaks.EYE_CANDY.legacyFog.moddedFog.isEnabled()) {
            changeFogStart(Tweaks.EYE_CANDY.legacyFog.moddedFog.terrainFogStartModded, fogData);
        }
    }

    public static void setFogStopConditional(FogDataAccessor fogData, float f) {
        if (PlayerUtil.isInOverworld() && Tweaks.EYE_CANDY.legacyFog.overworldFog.isEnabled()) {
            changeFogStop(Tweaks.EYE_CANDY.legacyFog.overworldFog.terrainFogStopOverworld, fogData, f);
        } else if (PlayerUtil.isInNether() && Tweaks.EYE_CANDY.legacyFog.netherFog.isEnabled()) {
            changeFogStop(Tweaks.EYE_CANDY.legacyFog.netherFog.terrainFogStopNether, fogData, f);
        } else if (PlayerUtil.isInEnd() && Tweaks.EYE_CANDY.legacyFog.endFog.isEnabled()) {
            changeFogStop(Tweaks.EYE_CANDY.legacyFog.endFog.terrainFogStopEnd, fogData, f);
        } else {
            changeFogStop(Tweaks.EYE_CANDY.legacyFog.moddedFog.terrainFogStopModded, fogData, f);
        }
    }

    public static void changeFogStart(Tweak.DoubleSliderOption tweak, FogDataAccessor fogData) {
        fogData.setStart((PlayerUtil.getMinecraftRenderDistance()*16)/tweak.get().floatValue() - (PlayerUtil.getMinecraftRenderDistance()*16));
    }

    public static void changeFogStop(Tweak.DoubleSliderOption tweak, FogDataAccessor fogData, float f) {
        fogData.setEnd(f * tweak.get().floatValue() * 2);
    }
}

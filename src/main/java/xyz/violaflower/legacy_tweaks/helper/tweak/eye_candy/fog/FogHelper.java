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

    public static void setFogStopConditional(FogDataAccessor fogData) {
        if (PlayerUtil.isInOverworld() && Tweaks.EYE_CANDY.legacyFog.overworldFog.isEnabled()) {
            changeFogStop(Tweaks.EYE_CANDY.legacyFog.overworldFog.terrainFogStopOverworld, fogData);
        } else if (PlayerUtil.isInNether() && Tweaks.EYE_CANDY.legacyFog.netherFog.isEnabled()) {
            changeFogStop(Tweaks.EYE_CANDY.legacyFog.netherFog.terrainFogStopNether, fogData);
        } else if (PlayerUtil.isInEnd() && Tweaks.EYE_CANDY.legacyFog.endFog.isEnabled()) {
            changeFogStop(Tweaks.EYE_CANDY.legacyFog.endFog.terrainFogStopEnd, fogData);
        } else {
            changeFogStop(Tweaks.EYE_CANDY.legacyFog.moddedFog.terrainFogStopModded, fogData);
        }
    }

    public static void changeFogStart(Tweak.DoubleSliderOption tweak, FogDataAccessor fogData) {
        fogData.setStart((PlayerUtil.getMinecraftRenderDistance() * 16) * tweak.get().floatValue());
        //fogData.setStart((PlayerUtil.getMinecraftRenderDistance()*16)/tweak.get().floatValue() - (PlayerUtil.getMinecraftRenderDistance()*16));
    }

    public static void changeFogStop(Tweak.IntSliderOption tweak, FogDataAccessor fogData) {
        fogData.setEnd(tweak.get() == 0 ? (PlayerUtil.getMinecraftRenderDistance() * 16) : tweak.get() * 16.0F);
    }
}

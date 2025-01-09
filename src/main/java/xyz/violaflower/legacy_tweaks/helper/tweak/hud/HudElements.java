package xyz.violaflower.legacy_tweaks.helper.tweak.hud;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.LegacyUI;

import static xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudHelper.guiHudTweaks;

public enum HudElements {
    HOTBAR(guiHudTweaks.applyHudDistanceHotbar, guiHudTweaks.applyHudScaleHotbar),
    EXPERIENCE(guiHudTweaks.applyHudDistanceHotbar, guiHudTweaks.applyHudScaleHotbar),
    CHAT(guiHudTweaks.applyHudDistanceChat, guiHudTweaks.applyHudScaleChat),
    SCOREBOARD(guiHudTweaks.applyHudDistanceScoreboard, guiHudTweaks.applyHudScaleScoreboard),
    EFFECTS(guiHudTweaks.applyHudDistanceEffects, guiHudTweaks.applyHudScaleEffects),
    PAPER_DOLL(guiHudTweaks.applyHudDistancePaperDoll, guiHudTweaks.applyHudScalePaperDoll),
    BOSS_HEALTH(guiHudTweaks.applyHudDistanceBossHealth, guiHudTweaks.applyHudScaleBossHealth),
    TOAST(guiHudTweaks.applyHudDistanceToast, guiHudTweaks.applyHudScaleToast),
    OTHER(guiHudTweaks.applyHudDistanceOther, guiHudTweaks.applyHudScaleOther);

    private final Tweak.BooleanOption distanceTweak;
    private final Tweak.BooleanOption scaleTweak;

    HudElements(Tweak.BooleanOption distanceTweak, Tweak.BooleanOption scaleTweak) {
        this.distanceTweak = distanceTweak;
        this.scaleTweak = scaleTweak;
    }

    public boolean isDistanceTweakOff() {
        return !this.distanceTweak.isOn();
    }

    public boolean isScaleTweakOff() {
        return !this.scaleTweak.isOn();
    }
}

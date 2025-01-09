package xyz.violaflower.legacy_tweaks.helper.tweak.hud;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

import static xyz.violaflower.legacy_tweaks.helper.tweak.hud.HudHelper.guiHudTweaks;

public enum HudElements {
    HOTBAR(guiHudTweaks.hotbarTweaks.applyScreenSpacingHotbar, guiHudTweaks.hotbarTweaks.applyHudScaleHotbar),
    EXPERIENCE(guiHudTweaks.hotbarTweaks.applyScreenSpacingHotbar, guiHudTweaks.hotbarTweaks.applyHudScaleHotbar),
    CHAT(guiHudTweaks.chatTweaks.applyScreenSpacingChat, guiHudTweaks.chatTweaks.applyHudScaleChat),
    SCOREBOARD(guiHudTweaks.scoreboardTweaks.applyScreenSpacingScoreboard, guiHudTweaks.scoreboardTweaks.applyHudScaleScoreboard),
    EFFECTS(guiHudTweaks.effectsTweaks.applyScreenSpacingEffects, guiHudTweaks.effectsTweaks.applyHudScaleEffects),
    PAPER_DOLL(guiHudTweaks.paperDollTweaks.applyScreenSpacingPaperDoll, guiHudTweaks.paperDollTweaks.applyHudScalePaperDoll),
    BOSS_HEALTH(guiHudTweaks.bossBarTweaks.applyScreenSpacingBossHealth, guiHudTweaks.bossBarTweaks.applyHudScaleBossHealth),
    TOAST(guiHudTweaks.generalTweaks.applyScreenSpacingToast, guiHudTweaks.generalTweaks.applyHudScaleToast),
    ITEM_OVERLAY(guiHudTweaks.hotbarTweaks.applyScreenSpacingHotbar, guiHudTweaks.generalTweaks.applyHudScaleTooltip),
    OTHER(guiHudTweaks.moddedTweaks.applyScreenSpacingOther, guiHudTweaks.moddedTweaks.applyHudScaleOther);

    private final Tweak.BooleanOption spacingTweak;
    private final Tweak.BooleanOption scaleTweak;

    HudElements(Tweak.BooleanOption spacingTweak, Tweak.BooleanOption scaleTweak) {
        this.spacingTweak = spacingTweak;
        this.scaleTweak = scaleTweak;
    }

    public boolean isDistanceTweakOff() {
        return !this.spacingTweak.isOn();
    }

    public boolean isScaleTweakOff() {
        return !this.scaleTweak.isOn();
    }
}

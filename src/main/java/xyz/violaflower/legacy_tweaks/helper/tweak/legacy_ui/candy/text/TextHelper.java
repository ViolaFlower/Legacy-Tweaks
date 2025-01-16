package xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.candy.text;

import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

public class TextHelper {

    private static boolean compactText;
    private static boolean legacyTextShadows = true;
    public static boolean isCompactTextEnabled() {
        return Tweaks.LEGACY_UI.generalScreenTweaks.legacyCompactText.isOn() && compactText;
    }

    public static void setCompactText(boolean b) {
        compactText = b;
    }

    public static void setLegacyTextShadows(boolean b) {
        legacyTextShadows = b;
    }

    public static boolean isLegacyTextShadowsEnabled() {
        return Tweaks.LEGACY_UI.generalScreenTweaks.legacyTextShadows.isOn() && legacyTextShadows;
    }
}

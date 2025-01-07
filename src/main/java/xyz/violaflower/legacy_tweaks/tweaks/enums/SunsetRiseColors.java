package xyz.violaflower.legacy_tweaks.tweaks.enums;

import net.minecraft.network.chat.Component;

public enum SunsetRiseColors implements EnumTweak {
    TU1("lt.tweak..tu1"),
    TU5("lt.tweak.tu5"),
    JAVA("lt.tweak.java");

    private final String title;


    SunsetRiseColors(String title)
    {
        this.title = title;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(this.title);
    }
}

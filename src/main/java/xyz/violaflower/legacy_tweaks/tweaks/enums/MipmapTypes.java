package xyz.violaflower.legacy_tweaks.tweaks.enums;

import net.minecraft.network.chat.Component;

public enum MipmapTypes implements EnumTweak {
    TU1("lt.tweak.tu1"),
    TU3("lt.tweak.tu3"),
    TU12("lt.tweak.tu12"),
    JAVA("lt.tweak.java");

    private final String title;


    MipmapTypes(String title)
    {
        this.title = title;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(this.title);
    }
}

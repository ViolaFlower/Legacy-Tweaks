package xyz.violaflower.legacy_tweaks.tweaks.enums;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;
import xyz.violaflower.legacy_tweaks.util.common.lang.Translate;

public enum SunsetRiseColors implements EnumTweak {
    TU1(Lang.TitleUpdates.TU1),
    TU5(Lang.TitleUpdates.TU5),
    JAVA(Lang.EnumTweak.JAVA);

    private final Translate title;

    SunsetRiseColors(Translate title)
    {
        this.title = title;
    }

    @Override
    public Translate getTitle() {
        return this.title;
    }

    @Override
    public Component getComponent() {
        return this.title.get();
    }
}

package xyz.violaflower.legacy_tweaks.tweaks.enums;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;
import xyz.violaflower.legacy_tweaks.util.common.lang.Translate;

public enum PlayGameScreen implements EnumTweak {
    DISABLED(Lang.EnumTweak.DISABLED),
    TU1(Lang.TitleUpdates.TU1),
    TU25(Lang.TitleUpdates.TU25);

    private final Translate title;

    PlayGameScreen(Translate title)
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

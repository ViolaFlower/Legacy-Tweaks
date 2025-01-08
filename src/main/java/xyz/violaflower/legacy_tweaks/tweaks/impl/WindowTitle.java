package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

public class WindowTitle extends Tweak {

    public WindowTitle() {
        super("windowTitle", true);
        setTweakAuthor("LimeGradient", "Jab125");
        ShowTUVersion showTUVersion = new ShowTUVersion();
        this.addSubTweak(showTUVersion);
    }

    public String getTitle() {
        ShowTUVersion showTUVersion = this.getSubTweak("showTUVersion");
        if (showTUVersion != null && showTUVersion.isEnabled()) {
            return String.format(Lang.WINDOW_TITLE_TU.getString(), showTUVersion.getTUVersion());
        } else {
            return String.format(Lang.WINDOW_TITLE.getString(), SharedConstants.getCurrentVersion().getName());
        }
    }

    public static class ShowTUVersion extends Tweak {
        public ShowTUVersion() {
            super("showTUVersion", false);
        }

        public String getTUVersion() {
            return Lang.TitleUpdates.TU_VERSION.getString();
        }

        @Override
        public void onToggled() {
            if (Minecraft.getInstance().getWindow() == null) return;
            Minecraft.getInstance().updateTitle();
        }
    }

    @Override
    public void onToggled() {
        if (Minecraft.getInstance().getWindow() == null) return;
        Minecraft.getInstance().updateTitle();
    }
}

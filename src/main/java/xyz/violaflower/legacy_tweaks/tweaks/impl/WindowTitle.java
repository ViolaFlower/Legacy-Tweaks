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

    public static class ShowTUVersion extends Tweak {
        public ShowTUVersion() {
            super("showTUVersion", false);
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

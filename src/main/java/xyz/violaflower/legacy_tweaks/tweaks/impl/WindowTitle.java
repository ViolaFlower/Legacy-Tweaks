package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class WindowTitle extends Tweak {

    public WindowTitle() {
        setTweakID("windowTitle");
        setTweakName(Component.translatable("lt.tweaks.windowtitle"));
        setTweakAuthor("LimeGradient", "Jab125");
        ShowTUVersion showTUVersion = new ShowTUVersion();
        showTUVersion.setEnabled(false);
        this.addSubTweak(showTUVersion);
    }

    public String getTitle() {
        ShowTUVersion showTUVersion = this.getSubTweak("showTUVersion");
        if (showTUVersion != null && showTUVersion.isEnabled()) {
            return String.format("Minecraft: Console Edition - %s", showTUVersion.getTUVersion());
        } else {
            return String.format("Console: %s Edition - Java", SharedConstants.getCurrentVersion().getName());
        }
    }

    public static class ShowTUVersion extends Tweak {
        public ShowTUVersion() {
            setTweakID("showTUVersion");
            setTweakName(Component.translatable("lt.tweaks.windowtitle.showtuversion"));
        }

        public String getTUVersion() {
            return "TU199";
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

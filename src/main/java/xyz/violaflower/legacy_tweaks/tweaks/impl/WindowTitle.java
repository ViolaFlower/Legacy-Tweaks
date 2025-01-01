package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class WindowTitle extends Tweak {
    public static class ShowTUVersion extends Tweak {
        public ShowTUVersion() {
            setTweakID("Show TU Version");
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

    public WindowTitle() {
        setTweakID("Window Title");
        setTweakAuthor("LimeGradient", "Jab125");
        ShowTUVersion showTUVersion = new ShowTUVersion();
        showTUVersion.setEnabled(false);
        this.addSubTweak(showTUVersion);
    }

    public String getTitle() {
        ShowTUVersion showTUVersion = this.getSubTweak("Show TU Version");
        if (showTUVersion != null && showTUVersion.isEnabled()) {
            return String.format("Minecraft: Console Edition - %s", showTUVersion.getTUVersion());
        } else {
            return String.format("Console: %s Edition - Java", SharedConstants.getCurrentVersion().getName());
        }
    }

    @Override
    public void onToggled() {
        if (Minecraft.getInstance().getWindow() == null) return;
        Minecraft.getInstance().updateTitle();
    }
}

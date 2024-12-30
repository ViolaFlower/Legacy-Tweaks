package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class WindowTitle extends Tweak {
    public static class ShowTUVersion extends Tweak {
        public ShowTUVersion() {
            setTweakID("showtuversion");
        }

        public String getTUVersion() {
            return "TU199";
        }
    }

    public WindowTitle() {
        setTweakID("windowtitle");
        setTweakAuthor("LimeGradient", "Jab125");
        ShowTUVersion showTUVersion = new ShowTUVersion();
        showTUVersion.setEnabled(false);
        this.addSubTweak(showTUVersion);
    }

    public String getTitle() {
        ShowTUVersion showTUVersion = this.getSubTweak("showtuversion");
        if (showTUVersion != null && showTUVersion.isEnabled()) {
            return String.format("Minecraft: Console Edition - %s", showTUVersion.getTUVersion());
        } else {
            return String.format("Console: %s Edition - Java", SharedConstants.getCurrentVersion().getName());
        }
    }

    @Override
    public void onEnable() {
        Minecraft.getInstance().updateTitle();
    }

    @Override
    public void onDisable() {
        Minecraft.getInstance().updateTitle();
    }
}

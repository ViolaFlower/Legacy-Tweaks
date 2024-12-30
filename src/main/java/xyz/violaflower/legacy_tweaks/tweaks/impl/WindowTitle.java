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
            return "TU12";
        }
    }

    public WindowTitle() {
        setTweakID("windowtitle");
        this.addSubTweak(new ShowTUVersion());
    }

    public String getTitle() {
        ShowTUVersion showTUVersion = this.getSubTweak("showtuversion");
        if (showTUVersion != null && showTUVersion.isEnabled()) {
            return String.format("Minecraft Legacy Console Edition - %s", showTUVersion.getTUVersion());
        } else {
            return String.format("Minecraft Legacy Console Edition - %s", SharedConstants.getCurrentVersion().getName());
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

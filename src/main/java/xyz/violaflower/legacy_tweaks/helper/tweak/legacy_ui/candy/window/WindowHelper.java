package xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.candy.window;

import net.minecraft.SharedConstants;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.LegacyUI.WindowTitle;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

public class WindowHelper {

    public static String getTitle() {
        WindowTitle.ShowTUVersion showTUVersion = Tweaks.LEGACY_UI.windowTitle.getSubTweak("showTUVersion");
        if (showTUVersion != null && showTUVersion.isEnabled()) {
            return String.format(Lang.WINDOW_TITLE_TU.getString(), getTUVersion());
        } else {
            return String.format(Lang.WINDOW_TITLE.getString(), SharedConstants.getCurrentVersion().getName());
        }
    }

    public static String getTUVersion() {
        return Lang.TitleUpdates.TU_VERSION.getString();
    }
}

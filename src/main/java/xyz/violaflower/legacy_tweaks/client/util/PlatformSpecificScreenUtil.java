package xyz.violaflower.legacy_tweaks.client.util;

import net.minecraft.client.gui.screens.Screen;
//? if neoforge {
/*import net.neoforged.neoforge.client.gui.ModListScreen;
*///?} elif fabric {
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyNotImplementedScreen;
//?}

import java.util.function.Function;

public class PlatformSpecificScreenUtil {
	public static Function<Screen, Screen> openModsScreen() {
		//? if neoforge {
		/*return ModListScreen::new;
		*///?} elif fabric
		return LegacyNotImplementedScreen::new;
	}
}

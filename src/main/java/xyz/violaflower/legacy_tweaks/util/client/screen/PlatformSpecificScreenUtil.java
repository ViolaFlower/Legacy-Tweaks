package xyz.violaflower.legacy_tweaks.util.client.screen;

import net.minecraft.client.gui.screens.Screen;
//? if neoforge {
/*import net.neoforged.neoforge.client.gui.ModListScreen;
*///?} elif fabric {
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyNotImplementedScreen;
//?}

import java.util.function.Function;

public class PlatformSpecificScreenUtil {
	public static Function<Screen, Screen> getModsScreen() {
		//? if neoforge {
		/*return ModListScreen::new;
		*///?} elif fabric
		return LegacyNotImplementedScreen::new;
	}

	public static boolean hasModsScreen() {
		//? if neoforge {
		/*return true;
		*///?} elif fabric
		return false;
	}
}

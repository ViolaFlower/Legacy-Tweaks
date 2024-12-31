package xyz.violaflower.legacy_tweaks.tweaks;

import java.util.Map;

public interface TweakParent {
	<T extends Tweak> T getSubTweak(String tweakID);
	Map<String, Tweak> getSubTweaks();
}

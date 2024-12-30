package xyz.violaflower.legacy_tweaks.tweaks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TweakManager {
    public static final TweakManager INSTANCE = new TweakManager();
    public static TweakManager getInstance() {
        return INSTANCE;
    }

    public Map<String, Tweak> tweaks = new HashMap<>();

    public void register(Tweak tweak) {
        tweaks.put(tweak.getTweakID(), tweak);
        tweak.onRegister();
    }

    public Tweak getTweak(String id) {
        return tweaks.get(id);
    }
}

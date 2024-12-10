package xyz.violaflower.legacy_tweaks.tweaks;

import java.util.ArrayList;

public class TweakManager {
    public static final TweakManager INSTANCE = new TweakManager();
    public static TweakManager getInstance() {
        return INSTANCE;
    }

    ArrayList<Tweak> tweaks = new ArrayList<Tweak>();

    public void register(Tweak tweak) {
        tweaks.add(tweak);
    }

    public Tweak getTweak(String id) {
        for (Tweak tweak : tweaks) {
            if (tweak.getTweakID().equals(id)) {
                return tweak;
            }
        }

        return null;
    }
}

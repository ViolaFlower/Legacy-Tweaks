package xyz.violaflower.legacy_tweaks.tweaks;

import java.util.ArrayList;

public class Tweak {
    private boolean isEnabled = true;
    private String tweakID;
    private ArrayList<Tweak> subTweaks = new ArrayList<Tweak>();

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getTweakID() {
        return tweakID;
    }

    public void setTweakID(String tID) {
        tweakID = tID;
    }

    public Tweak getSubTweak(String tweakID) {
        for (Tweak tweak : subTweaks) {
            if (tweak.getTweakID().equals(tweakID)) {
                return tweak;
            }
        }

        return null;
    }

    public void addSubTweak(Tweak tweak) {
        subTweaks.add(tweak);
    }
}

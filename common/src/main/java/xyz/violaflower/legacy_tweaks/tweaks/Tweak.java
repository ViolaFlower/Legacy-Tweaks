package xyz.violaflower.legacy_tweaks.tweaks;

public class Tweak {
    private boolean isEnabled = true;
    private String tweakID;

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getTweakID() {
        return tweakID;
    }

    public void setTweakID(String tID) {
        tweakID = tID;
    }
}

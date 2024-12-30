package xyz.violaflower.legacy_tweaks.tweaks;

import xyz.violaflower.legacy_tweaks.LegacyTweaks;

import java.util.ArrayList;

public abstract class Tweak {
    private boolean isEnabled = true;
    private ArrayList<Tweak> subTweaks = new ArrayList<Tweak>();

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getTweakID() {
        return "My Tweak";
    }
    public String getTweakAuthor() {
        return "John LegacyTweaks";
    }
    public String getTweakDescription() {
        return "Changes... something.";
    }
    // TODO: readd setter for tweak id, I don't have any idea on how to override a field.

    public void onRegister() {
        LegacyTweaks.LOGGER.info("Registered tweak: {}", getTweakID());
    }

    public void setTweakID(String tweakID) {
        // oops! does nothing atm.
        return;
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

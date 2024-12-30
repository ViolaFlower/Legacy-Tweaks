package xyz.violaflower.legacy_tweaks.tweaks;

import xyz.violaflower.legacy_tweaks.LegacyTweaks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Tweak {
    private String id; // this probably shouldn't have a default
    private String author = "John LegacyTweaks";
    private String description = "Changes... something.";
    private boolean isEnabled = true;
    private final Map<String, Tweak> subTweaks = new HashMap<>();

    public Tweak() {

    }

    public Tweak(String id) {
        this.id = id;
    }

    public Tweak(String id, String author, String description) {
        this.id = id;
        this.author = author;
        this.description = description;
    }
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        boolean changed = isEnabled != enabled;
        if (!changed) return;
        isEnabled = enabled;
        if (isEnabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public String getTweakID() {
        return id;
    }

    public void setTweakID(String id) {
        this.id = id;
    }

    public void setTweakAuthor(String author) {
        this.author = author;
    }

    public String getTweakAuthor() {
        return this.author;
    }

    public void setTweakDescription(String description) {
        this.description = description;
    }

    public String getTweakDescription() {
        return this.description;
    }

    public void onRegister() {
        LegacyTweaks.LOGGER.info("Registered tweak: {}", getTweakID());
    }

    @SuppressWarnings("unchecked") // shut up IDE i know what i'm doing
	public <T extends Tweak> T getSubTweak(String tweakID) {
        return (T) subTweaks.get(tweakID);
    }

    public void addSubTweak(Tweak tweak) {
        subTweaks.put(tweak.getTweakID(), tweak);
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public BooleanOption addBooleanOption(String name) {
        return new BooleanOption();
    }

    // TODO get this saved to a file and vice versa
    public class BooleanOption {
        public boolean isOn() {
            return true;
        }
    }
}

package xyz.violaflower.legacy_tweaks.tweaks;

import xyz.violaflower.legacy_tweaks.LegacyTweaks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Tweak implements TweakParent {
    private String id; // this probably shouldn't have a default
    private String author = "John LegacyTweaks";
    private String description = "Changes... something.";
    private String exDescription = "Changes something, I think... Maybe?";
    private String version = "1.0.0";
    private boolean isEnabled = true;
    private boolean isGroup = false;
    private final Map<String, Tweak> subTweaks = new LinkedHashMap<>();

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

    public void setDefaultEnabled(boolean enabled) {
        // TODO for when we do file saving
        setEnabled(enabled);
    }

    public void setGroup() {
        this.isGroup = true;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public String getTweakID() {
        return id;
    }

    public void setTweakID(String id) {
        this.id = id;
    }

    public void setTweakAuthor(String... author) {
        this.author = Arrays.stream(author).collect(Collectors.joining(", "));
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

    public void setTweakExtendedDescription(String exDescription) {
        this.exDescription = exDescription;
    }

    public String getTweakExtendedDescription() {
        return this.exDescription;
    }

    public void setTweakVersion(String version) {
        this.version = version;
    }

    public String getTweakVersion() {
        return this.version;
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

    public void onToggled() {

    }

    public void onEnable() {
        onToggled();
    }

    public void onDisable() {
        onToggled();
    }

    @Override
    public Map<String, Tweak> getSubTweaks() {
        return subTweaks;
    }

    public BooleanOption addBooleanOption(String name) {
        return new BooleanOption();
    }

    public SliderOption addSliderOption(String name, double min, double max) {
        return new SliderOption();
    }

    // TODO get this saved to a file and vice versa
    public static class BooleanOption {
        public boolean isOn() {
            return true;
        }
    }

    public static class SliderOption {
        public double get() {
            return 0.0;
        }
    }
}

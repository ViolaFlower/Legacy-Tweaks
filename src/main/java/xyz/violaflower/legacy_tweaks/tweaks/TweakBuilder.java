package xyz.violaflower.legacy_tweaks.tweaks;

public class TweakBuilder {

	private String id;
	private String description;
	private boolean defaultEnabled = true;

	public TweakBuilder(String tweakID) {
		this.id = tweakID;
	}

	@Deprecated(forRemoval = true)
	public TweakBuilder tweakID(String string) {
		this.id = string;
		return this;
	}

	public TweakBuilder description(String description) {
		this.description = description;
		return this;
	}

	public TweakBuilder setDefaultEnabled(boolean defaultEnabled) {
		this.defaultEnabled = defaultEnabled;
		return this;
	}

	public Tweak build() {
		Tweak tweak = new Tweak(id) {
		};
		tweak.setTweakDescription(description);
		tweak.setDefaultEnabled(defaultEnabled);
		return tweak;
	}
}

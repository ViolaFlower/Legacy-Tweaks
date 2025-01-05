package xyz.violaflower.legacy_tweaks.tweaks;

import net.minecraft.network.chat.Component;

public class TweakBuilder {

	private String id;
	private Component name;
	private Component description;
	private boolean defaultEnabled = true;
	private String[] authors;
	private Runnable onToggled = () -> {};
	private Runnable onEnable = () -> {};
	private Runnable onDisable = () -> {};

	public TweakBuilder(String tweakID) {
		this.id = tweakID;
	}

	@Deprecated(forRemoval = true)
	public TweakBuilder tweakID(String string) {
		this.id = string;
		return this;
	}

	public TweakBuilder name(Component name) {
		this.name = name;
		return this;
	}

	public TweakBuilder description(Component description) {
		this.description = description;
		return this;
	}

	public TweakBuilder authors(String... authors) {
		this.authors = authors;
		return this;
	}

	public TweakBuilder setDefaultEnabled(boolean defaultEnabled) {
		this.defaultEnabled = defaultEnabled;
		return this;
	}

	public TweakBuilder onToggled(Runnable onToggled) {
		this.onToggled = onToggled;
		return this;
	}

	public Tweak build() {
		Tweak tweak = new Tweak(id) {
			@Override
			public void onEnable() {
				this.onToggled();
				onEnable.run();
			}

			@Override
			public void onDisable() {
				this.onToggled();
				onDisable.run();
			}

			@Override
			public void onToggled() {
				onToggled.run();
			}
		};
		tweak.setTweakDescription(description);
		tweak.setTweakName(name);
		tweak.setDefaultEnabled(defaultEnabled);
		tweak.setTweakAuthor(authors);
		return tweak;
	}
}

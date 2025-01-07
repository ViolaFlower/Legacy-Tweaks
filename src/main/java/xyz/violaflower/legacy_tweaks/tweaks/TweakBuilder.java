package xyz.violaflower.legacy_tweaks.tweaks;

import net.minecraft.network.chat.Component;

public class TweakBuilder {

	private String id;
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
		Tweak tweak = new Tweak(id, defaultEnabled) {
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
		tweak.setTweakAuthor(authors);
		return tweak;
	}
}

package xyz.violaflower.legacy_tweaks.tweaks;

public class TweakBuilder {

	private String id;

	public TweakBuilder(String tweakID) {
		this.id = tweakID;
	}

	public TweakBuilder tweakID(String string) {
		this.id = string;
		return this;
	}

	public Tweak build() {
		return new Tweak(id){};
	}
}

package xyz.violaflower.legacy_tweaks.tweaks.impl;

import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;

public class Mipmapping extends Tweak {
    public final MipmapType mipmapType;
    public final Tweak manualMipmapping;

    public Mipmapping() {
        setTweakID("Mipmapping");
        setTweakAuthor("Permdog99");
        setTweakDescription("Various mipmap settings");
        setEnabled(true, false);

        addSubTweak(mipmapType = new MipmapType());
        addSubTweak(manualMipmapping = new TweakBuilder("Manual Mipmapping").description("[CURRENTLY DOES NOTHING] Allow resource packs to add in manual mipmaps for fine tuning.").authors("Permdog99").setDefaultEnabled(true).build());
    }

    public static class MipmapType extends Tweak {
        public final IntSliderOption mipmapType; // TODO change to Enum
        public MipmapType() {
            setTweakID("Mipmap Type");
            setTweakAuthor("Permdog99");
            setTweakDescription("Set the mipmapping/LOD style based on a specific LCE update.");
            mipmapType = addSliderOption("mipmapType", 1, 4);
            mipmapType.set(4);
        }
    }
}

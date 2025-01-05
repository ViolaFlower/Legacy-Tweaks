package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;

public class Mipmapping extends Tweak {
    public final MipmapType mipmapType;
    public final Tweak manualMipmapping;

    public Mipmapping() {
        setTweakID("mipmapping");
        setTweakAuthor("Permdog99");
        setEnabled(true, false);

        addSubTweak(mipmapType = new MipmapType());
        addSubTweak(manualMipmapping = new TweakBuilder("manualMipmapping").authors("Permdog99").setDefaultEnabled(true).build());
    }

    public static class MipmapType extends Tweak {
        public final IntSliderOption mipmapType; // TODO change to Enum
        public MipmapType() {
            setTweakID("mipmapType");
            setTweakAuthor("Permdog99");
            mipmapType = addSliderOption("type", 1, 4);
            mipmapType.set(4);
        }
    }
}

package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;
import xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapType;

public class Mipmapping extends Tweak {
    public final MipmapType mipmapType;
    public final Tweak manualMipmapping;

    public Mipmapping() {
        super("mipmapping", true);
        setTweakAuthor("Permdog99");

        addSubTweak(mipmapType = new MipmapType());
        addSubTweak(manualMipmapping = new TweakBuilder("manualMipmapping").authors("Permdog99").setDefaultEnabled(true).build());
    }

    public static class MipmapType extends Tweak {
        public final EnumSliderOption<xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapType> mipmapType;
        public MipmapType() {
            super("mipmapType", true);
            setTweakAuthor("Permdog99");
            mipmapType = addSliderOption("Mipmap Type", enumProvider(xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapType.JAVA, xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapType::values, xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapType::toString, xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapType::getTitle));
        }
    }
}

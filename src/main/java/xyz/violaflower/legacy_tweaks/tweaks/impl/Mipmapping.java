package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.client.Minecraft;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;
import xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapTypes;

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
        public final EnumSliderOption<MipmapTypes> mipmapType;
        public MipmapType() {
            super("mipmapType", true);
            setTweakAuthor("Permdog99");
            mipmapType = addSliderOption("Mipmap Type", enumProvider(MipmapTypes.JAVA, MipmapTypes::values, MipmapTypes::toString, MipmapTypes::getComponent));
            mipmapType.setConsumer(MipmapTypes -> Minecraft.getInstance().reloadResourcePacks());
        }
    }
}

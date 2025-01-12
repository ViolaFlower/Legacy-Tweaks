package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.client.Minecraft;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;
import xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapTypes;

public class Mipmapping extends Tweak {
    public final MipmapType mipmapType;
    public final Tweak manualMipmapping;
    public final Tweak fullCutoutMips;

    public Mipmapping() {
        super("mipmapping", true);
        setTweakAuthor("Permdog99", "Jab125");

        addSubTweak(mipmapType = new MipmapType());
        addSubTweak(manualMipmapping = new TweakBuilder("manualMipmapping").authors("Jab125").setDefaultEnabled(true).onToggled(() -> {
            Minecraft mc = Minecraft.getInstance();
            //noinspection ConstantValue
            if (mc.getResourceManager() != null)
                mc.reloadResourcePacks();
        }).build());

        addSubTweak(fullCutoutMips = new TweakBuilder("fullCutoutMips").authors("Permdog99").setDefaultEnabled(true).onToggled(() -> {
            Minecraft mc = Minecraft.getInstance();
            //noinspection ConstantValue
            if (mc.getResourceManager() != null)
                mc.reloadResourcePacks();
        }).build());
    }

    public static class MipmapType extends Tweak {
        public final EnumSliderOption<MipmapTypes> mipmapType;
        public MipmapType() {
            super("mipmapType", true);
            setTweakAuthor("Permdog99");
            mipmapType = addSliderOption("Mipmap Type", enumProvider(MipmapTypes.JAVA, MipmapTypes::values, MipmapTypes::toString, MipmapTypes::getComponent));
            mipmapType.setConsumer(t -> {
                Minecraft mc = Minecraft.getInstance();
                //noinspection ConstantValue
                if (mc.getResourceManager() != null)
                    mc.reloadResourcePacks();
            });
        }
    }
}

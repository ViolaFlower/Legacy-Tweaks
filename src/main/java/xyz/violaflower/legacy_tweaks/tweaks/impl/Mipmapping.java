package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakBuilder;

public class Mipmapping extends Tweak {
    public final MipmapType mipmapType;
    public final Tweak manualMipmapping;

    public Mipmapping() {
        setTweakID("mipmapping");
        setTweakName(Component.translatable("lt.tweaks.mipmapping"));
        setTweakAuthor("Permdog99");
        setTweakDescription(Component.translatable("lt.tweaks.mipmapping.description"));
        setEnabled(true, false);

        addSubTweak(mipmapType = new MipmapType());
        addSubTweak(manualMipmapping = new TweakBuilder("manualMipmapping").name(Component.translatable("lt.tweaks.mipmapping.manual")).description(Component.translatable("lt.tweaks.mipmapping.manual.description")).authors("Permdog99").setDefaultEnabled(true).build());
    }

    public static class MipmapType extends Tweak {
        public final IntSliderOption mipmapType; // TODO change to Enum
        public MipmapType() {
            setTweakID("mipmapType");
            setTweakName(Component.translatable("lt.tweaks.mipmapping.type"));
            setTweakAuthor("Permdog99");
            setTweakDescription(Component.translatable("lt.tweaks.mipmapping.type.description"));
            mipmapType = addSliderOption(Component.translatable("lt.tweaks.mipmapping.type.option.type"), 1, 4);
            mipmapType.set(4);
        }
    }
}

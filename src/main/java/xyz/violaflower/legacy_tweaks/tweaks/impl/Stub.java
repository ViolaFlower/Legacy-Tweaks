package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Rarity;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Stub extends Tweak {
    public Stub() {
        setTweakID("stubTweak");
        setTweakName(Component.translatable("lt.tweaks.stub"));
        setTweakAuthor("DexrnZacAttack", "Jab125");
        setTweakDescription(Component.translatable("lt.tweaks.stub.description"));
        addSliderOption(Component.translatable("lt.tweaks.stub.option.chatformatting"), enumProvider(ChatFormatting.RED, ChatFormatting::values, ChatFormatting::getName, f -> Component.literal(f.getName()).withStyle(f)));
        addSliderOption(Component.translatable("lt.tweaks.stub.option.rarity"), enumProvider(Rarity.COMMON, Rarity::values, Rarity::getSerializedName, f -> Component.literal(f.getSerializedName()).withStyle(f.color())));
    }
}

package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Rarity;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

public class Stub extends Tweak {
    public Stub() {
        super("stubTweak", true);
        setTweakAuthor("DexrnZacAttack", "Jab125");
        addSliderOption("chatFormatting", enumProvider(ChatFormatting.RED, ChatFormatting::values, ChatFormatting::getName, f -> Component.literal(f.getName()).withStyle(f)));
        addSliderOption("rarity", enumProvider(Rarity.COMMON, Rarity::values, Rarity::getSerializedName, f -> Component.literal(f.getSerializedName()).withStyle(f.color())));
    }
}

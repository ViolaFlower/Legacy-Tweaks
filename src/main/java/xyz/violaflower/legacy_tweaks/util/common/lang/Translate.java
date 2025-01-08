package xyz.violaflower.legacy_tweaks.util.common.lang;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public record Translate(String lang)
{
    public MutableComponent get(Object... args)
    {
        return Component.translatable(this.lang, args);
    }

    public MutableComponent withStyle(ChatFormatting... formatting)
    {
        return this.get().withStyle(formatting);
    }

    public String getString(Object... args)
    {
        return this.get(args).getString();
    }

    public boolean isBlank()
    {
        return this.lang.isBlank();
    }
}

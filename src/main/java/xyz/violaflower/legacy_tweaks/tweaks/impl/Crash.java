package xyz.violaflower.legacy_tweaks.tweaks.impl;

import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class Crash extends Tweak {
    public Crash() {
        setTweakID("crash");
        setTweakName(Component.translatable("lt.tweaks.crash"));
        setTweakDescription(Component.translatable("lt.tweaks.crash.description"));
        setTweakAuthor("Jeremiah");
        setEnabled(false);
    }
    @Override
    public void onEnable() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafe.get(null);
            unsafe.setMemory(0, 16, (byte)0);
        } catch (Throwable t) {
            onEnable();
        }
    }
}

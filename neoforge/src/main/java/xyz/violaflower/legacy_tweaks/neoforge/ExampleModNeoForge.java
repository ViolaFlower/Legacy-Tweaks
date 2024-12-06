package xyz.violaflower.legacy_tweaks.neoforge;

import net.neoforged.fml.common.Mod;

import xyz.violaflower.legacy_tweaks.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}

package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.legacy_inventory_screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.container.LegacyInventoryScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @ModifyArg(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V", ordinal = 1))
    private Screen changeInventoryScreen(Screen guiScreen) {
        if (Tweaks.LEGACY_UI.legacyInventoryScreenTweak.useLegacyInventory.isOn()) return new LegacyInventoryScreen(Minecraft.getInstance().player);
        return guiScreen;
    }
}

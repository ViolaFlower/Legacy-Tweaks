package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.screen.pause_screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyPauseMenu;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @ModifyArg(method = "pauseGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V", ordinal = 0))
    private Screen changePauseScreen(Screen guiScreen) {
        if (Tweaks.LEGACY_UI.legacyPauseScreenTweak.useLegacyPauseMenu.isOn()) return new LegacyPauseMenu();
        return guiScreen;
    }

    @ModifyArg(method = "pauseGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V", ordinal = 1))
    private Screen changePauseScreen2(Screen guiScreen) {
        if (Tweaks.LEGACY_UI.legacyPauseScreenTweak.useLegacyPauseMenu.isOn()) return new LegacyPauseMenu();
        return guiScreen;
    }
}

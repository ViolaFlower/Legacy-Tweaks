package xyz.violaflower.legacy_tweaks.mixin;

import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.gui.screen.legacy.LegacyTitleScreen;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.WindowTitle;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @ModifyArg(method = "updateTitle", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;setTitle(Ljava/lang/String;)V"))
    private String setWindowTitleMixin(String title) {
        TweakManager tweakManager = TweakManager.getInstance();
        WindowTitle windowTitle = Tweaks.WINDOW_TITLE;
        if (windowTitle == null || !windowTitle.isEnabled()) {
            return title;
        } else {
            return windowTitle.getTitle();
        }
    }

    @Inject(method = "setScreen", at = @At("RETURN"))
    private void setScreenMixin(Screen screen, CallbackInfo ci) {
        if (Tweaks.LEGACY_UI.legacyTitleScreen.isOn()) {
            if (screen instanceof TitleScreen) Minecraft.getInstance().setScreen(new LegacyTitleScreen());
        } else {
            if (screen instanceof LegacyTitleScreen) Minecraft.getInstance().setScreen(new TitleScreen());
        }
    }
}

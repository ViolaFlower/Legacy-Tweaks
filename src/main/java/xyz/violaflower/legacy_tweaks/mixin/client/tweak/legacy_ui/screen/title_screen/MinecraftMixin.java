package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.screen.title_screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.client.gui.screen.DataScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "setScreen", at = @At("RETURN"))
    private void setScreenMixin(Screen screen, CallbackInfo ci) {
        if (Tweaks.LEGACY_UI.legacyTitleScreen.legacyTitleScreen.isOn()) {
            if (screen instanceof TitleScreen) Minecraft.getInstance().setScreen(DataScreen.makeDataDrivenScreen(null, ScreenUtil.TITLE_SCREEN));
        } else {
            if (screen instanceof DataScreen screen1 && screen1.getId().equals(ScreenUtil.TITLE_SCREEN)) Minecraft.getInstance().setScreen(new TitleScreen());
        }
    }
}

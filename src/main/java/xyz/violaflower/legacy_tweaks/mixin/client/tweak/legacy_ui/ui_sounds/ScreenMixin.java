package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.ui_sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.sound.SoundUtil;
import xyz.violaflower.legacy_tweaks.util.common.sound.Sounds;

@Mixin(Screen.class)
public class ScreenMixin {

    @Inject(method = "changeFocus",at = @At("HEAD"))
    private void addChangeFocusSound(ComponentPath componentPath, CallbackInfo ci){
        if (Tweaks.LEGACY_UI.generalScreenTweaks.useLegacyUISounds.isOn()) SoundUtil.playRandomSound(Sounds.FOCUS, SoundSource.MASTER, 1.0f);
    }
    @Inject(method = "onClose",at = @At("HEAD"))
    private void addOnCloseSound(CallbackInfo ci){
        if (Minecraft.getInstance().screen instanceof LevelLoadingScreen) return;
        if (Tweaks.LEGACY_UI.generalScreenTweaks.useLegacyUISounds.isOn()) SoundUtil.playFullPitchSound(Sounds.BACK, SoundSource.MASTER);
    }
}

package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.ui_sounds;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.sound.SoundUtil;
import xyz.violaflower.legacy_tweaks.util.common.sound.Sounds;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "pauseGame", at = @At("HEAD"))
    private void addPauseSound(boolean pauseOnly, CallbackInfo ci) {
        if (Minecraft.getInstance().screen == null && Tweaks.LEGACY_UI.generalScreenTweaks.useLegacyUISounds.isOn()) {
            SoundUtil.playFullPitchSound(Sounds.PRESS, SoundSource.MASTER);
        }
    }

    @WrapOperation(method = "pauseGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sounds/SoundManager;pause()V"))
    private void removeSoundManagerPause(SoundManager instance, Operation<Void> original) {
        if (!Tweaks.LEGACY_UI.generalScreenTweaks.useLegacyUISounds.isOn()) original.call(instance);
    }
}

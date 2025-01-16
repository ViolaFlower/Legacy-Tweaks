package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.sounds;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.sound.SoundUtil;
import xyz.violaflower.legacy_tweaks.util.common.sound.Sounds;

@Mixin(AbstractWidget.class)
public class AbstractWidgetMixin {

    @WrapOperation(method = "playDownSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sounds/SoundManager;play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V"))
    private void changePressSound(SoundManager instance, SoundInstance sound, Operation<Void> original) {
        if (Tweaks.LEGACY_UI.generalScreenTweaks.useLegacyUISounds.isOn()) {
            SoundUtil.playFullPitchSound(Sounds.PRESS, SoundSource.MASTER);
        } else {
            original.call(instance, sound);
        }
    }
}

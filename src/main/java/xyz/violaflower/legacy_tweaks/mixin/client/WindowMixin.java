package xyz.violaflower.legacy_tweaks.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.EyeCandy;

@Mixin(Window.class)
public class WindowMixin {
	@Inject(method = "setGuiScale", at = @At("HEAD"))
	private void guiScaleInject(double d, CallbackInfo ci, @Local(argsOnly = true) LocalDoubleRef ref) {
		EyeCandy.FineTunedUIScale fineTunedUiScale = Tweaks.EYE_CANDY.getSubTweak("Fine Tuned UI Scale");
		if (fineTunedUiScale.isEnabled()) {
			 ref.set(fineTunedUiScale.uiScale.get());
		}
	}
}

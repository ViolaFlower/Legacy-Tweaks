package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.candy.ui_scale;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.client.screen.ScreenUtil;

@Mixin(Window.class)
public class WindowMixin {
	@Inject(method = "setGuiScale", at = @At("HEAD"))
	private void guiScaleInject(double d, CallbackInfo ci, @Local(argsOnly = true) LocalDoubleRef ref) {
		Tweak.BooleanOption fineTunedUiScale = Tweaks.LEGACY_UI.generalScreenTweaks.forceDisableFineTunedUIScale;
		Tweak.DoubleSliderOption uiScale = Tweaks.LEGACY_UI.generalScreenTweaks.fineTunedUIScale;
		if (Tweaks.LEGACY_UI.generalScreenTweaks.autoFindBestUIScale.isOn() && fineTunedUiScale.isOn()) {
			ref.set(ScreenUtil.setBestGuiScale());
		} else if (!fineTunedUiScale.isOn()) {
			ref.set(uiScale.get());
		}
	}
}

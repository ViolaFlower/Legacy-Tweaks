package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.screen.title_screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.client.gui.screen.DataScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;

@Mixin(Screen.class)
public class ScreenMixin {
	@Shadow private boolean initialized;
	@Unique
	private Boolean previousQuitButtonOptionValue;
	@Inject(method = "init(Lnet/minecraft/client/Minecraft;II)V", at = @At("HEAD"))
	void init(Minecraft minecraft, int width, int height, CallbackInfo ci) {
		if (previousQuitButtonOptionValue == null) {
			previousQuitButtonOptionValue = Tweaks.LEGACY_UI.legacyTitleScreen.showQuitButton.get();
		}
		boolean valueChanged = previousQuitButtonOptionValue != Tweaks.LEGACY_UI.legacyTitleScreen.showQuitButton.get();
		Object this_ = this;
		if (this_ instanceof DataScreen screen && screen.getId().equals(ScreenUtil.TITLE_SCREEN) && valueChanged) {
			previousQuitButtonOptionValue = Tweaks.LEGACY_UI.legacyTitleScreen.showQuitButton.get();
			initialized = false;
		}
	}
}

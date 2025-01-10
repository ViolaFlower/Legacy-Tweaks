package xyz.violaflower.legacy_tweaks.mixin.client.screen;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyScreen;

@Mixin(Screen.class)
public class ScreenMixin {
	@Shadow private boolean initialized;
	@Unique
	private Boolean previousScreenHeightIsLargeGui;
	@Inject(method = "init(Lnet/minecraft/client/Minecraft;II)V", at = @At("HEAD"))
	void init(Minecraft minecraft, int width, int height, CallbackInfo ci) {
		Window window = Minecraft.getInstance().getWindow();
		int screenHeight = window.getScreenHeight();
		if (previousScreenHeightIsLargeGui == null) {
			previousScreenHeightIsLargeGui = screenHeight >= 720;
		}
		boolean valueChanged = previousScreenHeightIsLargeGui != screenHeight > 720;
		Object this_ = this;
		if (this_ instanceof LegacyScreen screen && valueChanged) {
			previousScreenHeightIsLargeGui = screenHeight > 720;
			initialized = false;
		}
	}
}

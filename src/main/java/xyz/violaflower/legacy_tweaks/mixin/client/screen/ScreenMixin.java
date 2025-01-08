package xyz.violaflower.legacy_tweaks.mixin.client.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyScreen;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyTitleScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;

@Mixin(Screen.class)
public abstract class ScreenMixin {
	@Shadow private boolean initialized;

	@Shadow public abstract void init(Minecraft minecraft, int width, int height);

	@Shadow @Nullable protected Minecraft minecraft;
	@Shadow public int width;
	@Unique
	private Boolean hadLargeGui;
	@Inject(method = "resize", at = @At("HEAD"))
	void re(Minecraft minecraft, int width, int height, CallbackInfo ci) {
		if (hadLargeGui == null) {
			hadLargeGui = ScreenUtil.isLargeGui();
		}
		boolean valueChanged = hadLargeGui != ScreenUtil.isLargeGui();
		Object this_ = this;
		if (this_ instanceof LegacyScreen screen && valueChanged) {
			hadLargeGui = ScreenUtil.isLargeGui();
			initialized = false;
			this.init(minecraft, width, height);
		}
	}
}

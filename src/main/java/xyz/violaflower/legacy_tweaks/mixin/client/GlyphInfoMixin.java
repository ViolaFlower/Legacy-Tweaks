package xyz.violaflower.legacy_tweaks.mixin.client;

import com.mojang.blaze3d.font.GlyphInfo;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.EyeCandy;

@Mixin(GlyphInfo.class)
public interface GlyphInfoMixin {
	@Inject(method = "getShadowOffset", at = @At("HEAD"), cancellable = true)
	private void getShadowOffset(CallbackInfoReturnable<Float> cir) {
		EyeCandy.LegacyTextShadows legacyTextShadows = Tweaks.EYE_CANDY.legacyTextShadows;
		if (!legacyTextShadows.isEnabled()) return;
		cir.setReturnValue((float)(legacyTextShadows.shadowOffset.get() / Minecraft.getInstance().getWindow().getGuiScale()));
	}
}

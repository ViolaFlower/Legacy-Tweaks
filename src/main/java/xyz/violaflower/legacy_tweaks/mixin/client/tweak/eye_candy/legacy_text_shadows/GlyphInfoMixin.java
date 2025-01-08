package xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.legacy_text_shadows;

import com.mojang.blaze3d.font.GlyphInfo;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.helper.tweak.world.EyeCandyHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.EyeCandy;

@Mixin(GlyphInfo.class)
public interface GlyphInfoMixin {
	@Inject(method = "getShadowOffset", at = @At("HEAD"), cancellable = true)
	private void getShadowOffset(CallbackInfoReturnable<Float> cir) {
		if (!EyeCandyHelper.isLegacyTextShadowsEnabled()) return;
		EyeCandy.LegacyTextShadows legacyTextShadows = Tweaks.EYE_CANDY.legacyTextShadows;
		cir.setReturnValue((float)(legacyTextShadows.shadowOffset.get() / Minecraft.getInstance().getWindow().getGuiScale()));
	}
}

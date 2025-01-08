package xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.legacy_compact_text;

import com.mojang.blaze3d.font.GlyphInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.helper.tweak.world.EyeCandyHelper;

@Mixin(GlyphInfo.class)
public interface GlyphInfoMixin {
	@Inject(method = "getAdvance(Z)F", at = @At("RETURN"), cancellable = true)
	default void getAdvance(boolean bold, CallbackInfoReturnable<Float> cir) {
		if (EyeCandyHelper.isCompactTextEnabled()) {
			cir.setReturnValue(cir.getReturnValueF()-0.6f);
		}
	}
}

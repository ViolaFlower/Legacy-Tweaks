package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.candy.legacy_text_shadows;

import com.mojang.blaze3d.font.GlyphInfo;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.candy.text.TextHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(GlyphInfo.class)
public interface GlyphInfoMixin {
	@Inject(method = "getShadowOffset", at = @At("HEAD"), cancellable = true)
	private void getShadowOffset(CallbackInfoReturnable<Float> cir) {
		if (!TextHelper.isLegacyTextShadowsEnabled()) return;
		Tweak.IntSliderOption legacyTextShadowOffset = Tweaks.LEGACY_UI.generalScreenTweaks.legacyTextShadowOffset;
		cir.setReturnValue((float)(legacyTextShadowOffset.get() / Minecraft.getInstance().getWindow().getGuiScale()));
	}
}

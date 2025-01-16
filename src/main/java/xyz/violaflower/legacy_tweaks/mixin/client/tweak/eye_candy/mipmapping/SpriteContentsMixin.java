package xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.mipmapping;

import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.eye_candy.mipmapping.MipmapTypeHelper;

@Mixin(SpriteContents.class)
public class SpriteContentsMixin {
	@Shadow @Final private ResourceLocation name;

	@Inject(method = "increaseMipLevel", at = @At("HEAD"))
	private void legacyTweaks$increaseMipLevel(int mipLevel, CallbackInfo ci) {
		MipmapTypeHelper.currentResourceLocation = this.name;
		if (this.name == null) {
			System.err.println(this.name + " is null!");
		}
	}
}

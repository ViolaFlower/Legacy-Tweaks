package xyz.violaflower.legacy_tweaks.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.Util;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.extension.client.ButtonExtension;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(AbstractButton.class)
public class AbstractButtonMixin implements ButtonExtension {
	@Unique
	private long clickDecay = Long.MIN_VALUE;
	@Inject(method = "onClick", at = @At("HEAD"))
	void onClick(double mouseX, double mouseY, CallbackInfo ci) {
		legacyTweaks$setClickDecayTo(Util.getMillis() + 125);
	}


	@WrapOperation(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/WidgetSprites;get(ZZ)Lnet/minecraft/resources/ResourceLocation;"))
	ResourceLocation renderWidget(WidgetSprites instance, boolean enabled, boolean focused, Operation<ResourceLocation> original) {
		l: {
			if (!Tweaks.EYE_CANDY.oldButton.isEnabled()) break l;
			if (enabled && focused) return ResourceLocation.fromNamespaceAndPath("legacy_tweaks", "widget/old_button_highlighted");
		}
		return original.call(instance, enabled, focused);
	}

	@Override
	public long legacyTweaks$getClickDecayTo() {
		return clickDecay;
	}

	@Override
	public void legacyTweaks$setClickDecayTo(long to) {
		this.clickDecay = to;
	}
}

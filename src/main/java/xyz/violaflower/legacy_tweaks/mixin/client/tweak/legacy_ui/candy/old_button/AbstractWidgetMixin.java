package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.candy.old_button;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.client.gui.extention.ButtonExtension;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(AbstractWidget.class)
public abstract class AbstractWidgetMixin {
	@Shadow public abstract boolean isHoveredOrFocused();

	@Shadow public boolean active;

	@WrapOperation(method = "renderScrollingString(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/Font;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/AbstractWidget;getMessage()Lnet/minecraft/network/chat/Component;"))
	private Component render(
			AbstractWidget instance, Operation<Component> original
	) {
		bloc:if (this instanceof ButtonExtension buttonExtension) {
			if (Tweaks.LEGACY_UI.generalScreenTweaks.oldButton.isOn()) {
				if (!this.active) break bloc;                                                 // mom: we have goto at home
				if (!buttonExtension.legacyTweaks$isDecayed() && this.isHoveredOrFocused()) { // goto at home:
					return original.call(instance).copy().withStyle(ChatFormatting.YELLOW);
				}
			}
		}
		return original.call(instance);
	}
}

package xyz.violaflower.legacy_tweaks.mixin.client.tweak.chat;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(ChatComponent.class)
public class ChatComponentMixin {
    @WrapOperation(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0)
    )
    void widthSpan(GuiGraphics instance, int minX, int minY, int maxX, int maxY, int color, Operation<Void> original) {
        if (!Tweaks.LEGACY_CHAT.isEnabled() || !Tweaks.LEGACY_CHAT.messageWidthSpansScreen.get()) { original.call(instance, minX, minY, maxX, maxY, color); return; }
        // Dexrn: This was all done on my own, it may or may not work well.
        original.call(instance, minX, minY, Minecraft.getInstance().getWindow().getWidth() - minX, maxY, color);
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;", ordinal = 2))
    Object gydsfuiysdivkksyoi(OptionInstance instance, Operation<Object> original) {
        if (!Tweaks.LEGACY_CHAT.isEnabled() || !Tweaks.LEGACY_CHAT.legacyMessageHeight.get()) return original.call(instance);
        return Tweaks.LEGACY_CHAT.getLineHeight();
    }

    @WrapOperation(method = "getLineHeight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"))
    Object gydsfuiysdivkksyo6i(OptionInstance instance, Operation<Object> original) {
        if (!Tweaks.LEGACY_CHAT.isEnabled() || !Tweaks.LEGACY_CHAT.legacyMessageHeight.get()) return original.call(instance);
        return Tweaks.LEGACY_CHAT.getLineHeight();
    }
}

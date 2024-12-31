package xyz.violaflower.legacy_tweaks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.FatChat;

@Mixin(ChatComponent.class)
public class ChatComponentMixin {
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;", ordinal = 2))
    Object gydsfuiysdivkksyoi(OptionInstance instance, Operation<Object> original) {
        if (!Tweaks.FAT_CHAT.isEnabled()) return original.call(instance);
        return Tweaks.FAT_CHAT.getLineHeight();
    }

    @WrapOperation(method = "getLineHeight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"))
    Object gydsfuiysdivkksyo6i(OptionInstance instance, Operation<Object> original) {
        if (!Tweaks.FAT_CHAT.isEnabled()) return original.call(instance);
        return Tweaks.FAT_CHAT.getLineHeight();
    }
}

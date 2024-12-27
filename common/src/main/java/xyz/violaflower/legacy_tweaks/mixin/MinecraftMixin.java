package xyz.violaflower.legacy_tweaks.mixin;

import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.impl.WindowTitle;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {
    @ModifyArg(method = "updateWindowTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setTitle(Ljava/lang/String;)V"))
    private String setWindowTitleMixin(String title) {
        TweakManager tweakManager = TweakManager.getInstance();
        WindowTitle windowTitle = (WindowTitle) tweakManager.getTweak("windowtitle");
        if (!windowTitle.isEnabled()) {
            return title;
        } else {
            return windowTitle.getTitle();
        }
    }
}

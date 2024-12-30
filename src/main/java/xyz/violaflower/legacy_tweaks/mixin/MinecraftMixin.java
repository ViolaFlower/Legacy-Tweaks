package xyz.violaflower.legacy_tweaks.mixin;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.impl.WindowTitle;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @ModifyArg(method = "updateTitle", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;setTitle(Ljava/lang/String;)V"))
    private String setWindowTitleMixin(String title) {
        TweakManager tweakManager = TweakManager.getInstance();
        WindowTitle windowTitle = (WindowTitle) tweakManager.getTweak("windowtitle");
        if (windowTitle == null || !windowTitle.isEnabled()) {
            return title;
        } else {
            return windowTitle.getTitle();
        }
    }
}

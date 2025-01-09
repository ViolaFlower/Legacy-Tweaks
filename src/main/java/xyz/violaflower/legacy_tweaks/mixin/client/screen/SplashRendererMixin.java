package xyz.violaflower.legacy_tweaks.mixin.client.screen;

import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.helper.tweak.world.EyeCandyHelper;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;

@Mixin(SplashRenderer.class)
public abstract class SplashRendererMixin {
    @Mutable
    @Final
    @Shadow
    private final String splash;

    @Shadow @Final private static int WIDTH_OFFSET;
    @Shadow @Final private static int HEIGH_OFFSET; // why...

    protected SplashRendererMixin(String splash) {
        this.splash = splash;
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void render(GuiGraphics guiGraphics, int screenWidth, Font font, int color, CallbackInfo ci) {
        int y = 0;
        float scale = 1.5f;
        float xPos = 0;
        float yPos = 0;
        if (Tweaks.LEGACY_UI.generalScreenTweaks.useLegacyTitleLogo.isOn()) y = 0;
        if (ScreenUtil.isLargeGui()) {
            scale = 0.8f;
            xPos = -45;
            yPos = (float) -34.5;
        }
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(screenWidth / 2f + WIDTH_OFFSET + xPos, HEIGH_OFFSET + yPos, 0.0F);
        guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(-15.0F));
        float animScale = 1.8F - Mth.abs(Mth.sin((float) (Util.getMillis() % 1000L) / 1000F * ((float) Math.PI * 2F)) * 0.1F);
        animScale = (animScale * 100.0F / (float) (font.width(this.splash) + 32));
        guiGraphics.pose().scale(animScale * scale, animScale * scale, animScale * scale);
        EyeCandyHelper.setCompactText(true);
        EyeCandyHelper.setLegacyTextShadows(false);
        guiGraphics.drawCenteredString(font, this.splash, 0, y, 0xffff00 | color);
        EyeCandyHelper.setLegacyTextShadows(true);
        EyeCandyHelper.setCompactText(false);
        guiGraphics.pose().popPose();
        ci.cancel();
    }
}

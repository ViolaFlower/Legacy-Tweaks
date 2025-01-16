package xyz.violaflower.legacy_tweaks.mixin.client.tweak.swing.camera.third_person;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {

    public LocalPlayerMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }

    @Inject(method = "getViewXRot", at = @At("RETURN"), cancellable = true)
    private void changeViewRotX(float partialTicks, CallbackInfoReturnable<Float> cir) {
        if (Tweaks.SWING.camera.attachThirdPersonToHead.isOn() && !Minecraft.getInstance().options.getCameraType().isFirstPerson()) cir.setReturnValue(super.getViewXRot(partialTicks));
    }

    @Inject(method = "getViewYRot", at = @At("RETURN"), cancellable = true)
    private void changeViewRotY(float partialTicks, CallbackInfoReturnable<Float> cir) {
        if (Tweaks.SWING.camera.attachThirdPersonToHead.isOn() && !Minecraft.getInstance().options.getCameraType().isFirstPerson()) cir.setReturnValue(super.getViewYRot(partialTicks));
    }
}

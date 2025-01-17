package xyz.violaflower.legacy_tweaks.mixin.client.tweak.swing.camera.third_person;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(Camera.class)
public class CameraMixin {

    //? if (neoforge) {
    /*@WrapOperation(method = "setup", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setRotation(FFF)V", ordinal = 1))
    *///?} else {
    @WrapOperation(method = "setup", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setRotation(FF)V", ordinal = 1))
    //?}
    private void makeThirdPersonCameraInverted(Camera instance, float yRot, float xRot, /*? if (neoforge) {*//*float roll, *//*?}*/Operation<Void> original) {
        if (Tweaks.SWING.camera.invertedThirdPerson.isOn()) {
            original.call(instance, yRot, -xRot/*? if (neoforge) {*//*, roll*//*?}*/);
        } else {
            original.call(instance, yRot, xRot/*? if (neoforge) {*//*, roll*//*?}*/);
        }
    }
}

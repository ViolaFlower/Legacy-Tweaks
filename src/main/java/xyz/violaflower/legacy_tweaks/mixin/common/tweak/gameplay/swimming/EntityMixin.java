package xyz.violaflower.legacy_tweaks.mixin.common.tweak.gameplay.swimming;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(Entity.class)
public class EntityMixin {

    @WrapOperation(method = "updateSwimming", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;setSwimming(Z)V", ordinal = 1))
    protected void changeSwimmingMechanics(Entity instance, boolean swimming, Operation<Void> original) {
        if (Tweaks.GAMEPLAY.waterMechanics.alwaysSwimInWater.isOn()) {
            instance.setSwimming(instance.isInWater() && instance.isSprinting() && !instance.isPassenger());
        } else {
            original.call(instance, swimming);
        }
    }
}

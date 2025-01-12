package xyz.violaflower.legacy_tweaks.mixin.common.tweak.gameplay;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(Entity.class)
public class EntityMixin {

    @Redirect(method = "updateSwimming", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isUnderWater()Z"))
    protected boolean updateSwimming(Entity instance) {
        return Tweaks.GAMEPLAY.waterMechanics.alwaysSwimInWater.isOn() && instance.isInWater();
    }
}

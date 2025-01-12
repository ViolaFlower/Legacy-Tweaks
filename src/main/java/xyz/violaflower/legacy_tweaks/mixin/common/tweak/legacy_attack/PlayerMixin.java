package xyz.violaflower.legacy_tweaks.mixin.common.tweak.legacy_attack;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.MaceItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(Player.class)
public abstract class PlayerMixin {
	@Inject(method = "getCurrentItemAttackStrengthDelay", at = @At("RETURN"), cancellable = true)
	private void getAttribute(CallbackInfoReturnable<Float> cir) {
		if (!Tweaks.GAMEPLAY.combatMechanics.noAttackCooldown.isOn()) return;
		if (((LivingEntity)(Object)this).getMainHandItem().getItem() instanceof MaceItem) return;
		cir.setReturnValue(0.0f);
	}
}

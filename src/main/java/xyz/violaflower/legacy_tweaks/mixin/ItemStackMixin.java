package xyz.violaflower.legacy_tweaks.mixin;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

import java.util.ArrayList;

import static net.minecraft.world.item.Item.BASE_ATTACK_DAMAGE_ID;
import static net.minecraft.world.item.Item.BASE_ATTACK_SPEED_ID;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow public abstract boolean isEmpty();

	@Shadow public abstract Item getItem();

	@Inject(method = "getComponents", at = @At("RETURN"), cancellable = true)
	void attributeModifications(CallbackInfoReturnable<DataComponentMap> cir) {
		lessAxeDamage: {
			if (!Tweaks.LEGACY_ATTACK.getSubTweak("lessAxeDamage").isEnabled()) break lessAxeDamage;
			if (this.isEmpty()) return;
			if (this.getItem() instanceof AxeItem axeItem) {
				DataComponentMap returnValue = cir.getReturnValue();
				if (returnValue.has(DataComponents.ATTRIBUTE_MODIFIERS)) {
					ItemAttributeModifiers itemAttributeModifiers = returnValue.get(DataComponents.ATTRIBUTE_MODIFIERS);
					if (itemAttributeModifiers == null) break lessAxeDamage;
					ItemAttributeModifiers itemAttributeModifiers1 = nerfAttackDamage(itemAttributeModifiers);
					DataComponentPatch build = DataComponentPatch.builder().remove(DataComponents.ATTRIBUTE_MODIFIERS).set(DataComponents.ATTRIBUTE_MODIFIERS, itemAttributeModifiers1).build();
					cir.setReturnValue(PatchedDataComponentMap.fromPatch(returnValue, build));
				}
			}
		}
		removeCooldown: {
			if (!Tweaks.LEGACY_ATTACK.getSubTweak("noAttackCooldown").isEnabled()) break removeCooldown;
			if (this.isEmpty()) return;
			if (this.getItem() instanceof MaceItem) break removeCooldown;
			DataComponentMap returnValue = cir.getReturnValue();
			if (returnValue.has(DataComponents.ATTRIBUTE_MODIFIERS)) {
				ItemAttributeModifiers itemAttributeModifiers = returnValue.get(DataComponents.ATTRIBUTE_MODIFIERS);
				if (itemAttributeModifiers == null) break removeCooldown;
				ItemAttributeModifiers itemAttributeModifiers1 = removeAttackCooldown(itemAttributeModifiers);
				DataComponentPatch build = DataComponentPatch.builder().remove(DataComponents.ATTRIBUTE_MODIFIERS).set(DataComponents.ATTRIBUTE_MODIFIERS, itemAttributeModifiers1).build();
				cir.setReturnValue(PatchedDataComponentMap.fromPatch(returnValue, build));
			}
		}
	}

	@Unique
	private static @NotNull ItemAttributeModifiers nerfAttackDamage(ItemAttributeModifiers itemAttributeModifiers) {
		ArrayList<ItemAttributeModifiers.Entry> entries = new ArrayList<>();
		for (ItemAttributeModifiers.Entry modifier : itemAttributeModifiers.modifiers()) {
			if (modifier.modifier().is(BASE_ATTACK_DAMAGE_ID)) {
				entries.add(new ItemAttributeModifiers.Entry(modifier.attribute(), new AttributeModifier(modifier.modifier().id(), modifier.modifier().amount() - 3, modifier.modifier().operation()), modifier.slot()));
			} else {
				entries.add(modifier);
			}
		}
		return new ItemAttributeModifiers(entries, itemAttributeModifiers.showInTooltip());
	}

	@Unique
	private static @NotNull ItemAttributeModifiers removeAttackCooldown(ItemAttributeModifiers itemAttributeModifiers) {
		ArrayList<ItemAttributeModifiers.Entry> entries = new ArrayList<>();
		for (ItemAttributeModifiers.Entry modifier : itemAttributeModifiers.modifiers()) {
			if (modifier.modifier().is(BASE_ATTACK_SPEED_ID)) {
				//entries.add(new ItemAttributeModifiers.Entry(modifier.attribute(), new AttributeModifier(modifier.modifier().id(), modifier.modifier().amount() - 3, modifier.modifier().operation()), modifier.slot()));
			} else {
				entries.add(modifier);
			}
		}
		return new ItemAttributeModifiers(entries, itemAttributeModifiers.showInTooltip());
	}
}

package xyz.violaflower.legacy_tweaks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.FatChat;


@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract Level level();

    @WrapOperation(
            method = "handlePortal",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;changeDimension(Lnet/minecraft/world/level/portal/DimensionTransition;)Lnet/minecraft/world/entity/Entity;", ordinal = 0)
    )
    private Entity sendChatMessage(Entity instance, DimensionTransition transition, Operation<Entity> original) {
        if (!(this.level() instanceof ServerLevel serverLevel)) return original.call(instance, transition);
        if (!Tweaks.LEGACY_CHAT.getSubTweak("Dimension Notifications").isEnabled()) {
            return original.call(instance, transition);
        }
        if (!(instance instanceof ServerPlayer player)) return original.call(instance, transition);

        FatChat.DimensionNotification dn = Tweaks.LEGACY_CHAT.getSubTweak("Dimension Notifications"); // deez nuts

        ResourceLocation dimension = (transition.newLevel()).dimension().location();
        ResourceLocation playerDimension = instance.level().dimension().location();
        Component displayName = player.getDisplayName();

        if (dimension.toString().equals("minecraft:the_end") && dn.entranceMessage.get())
            serverLevel.getServer().sendSystemMessage(Component.translatable("lt.tweaks.dimensionNotification.endEntranceMessage", displayName));
        else if (playerDimension.toString().equals("minecraft:the_end") && dn.leaveMessage.get())
            serverLevel.getServer().sendSystemMessage(Component.translatable("lt.tweaks.dimensionNotification.endLeaveMessage", displayName));

        return original.call(instance, transition);
    }
}

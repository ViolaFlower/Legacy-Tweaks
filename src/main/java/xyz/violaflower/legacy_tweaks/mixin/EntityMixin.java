package xyz.violaflower.legacy_tweaks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
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

import static xyz.violaflower.legacy_tweaks.util.PlayerUtil.isInEnd;


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

        ResourceKey<Level> currentDimension = instance.level().dimension();
        ResourceKey<Level> newDimension = (transition.newLevel()).dimension();
        Component displayName = player.getDisplayName();

        if (newDimension.equals(Level.END) && dn.entranceMessage.get()) {
			serverLevel.getServer().getPlayerList().broadcastSystemMessage(Component.translatable("lt.tweaks.dimensionNotification.endEntranceMessage", displayName), false);
		} else if (currentDimension.equals(Level.END) && dn.leaveMessage.get()) {
			serverLevel.getServer().getPlayerList().broadcastSystemMessage(Component.translatable("lt.tweaks.dimensionNotification.endLeaveMessage", displayName), false);
		}

        return original.call(instance, transition);
    }
}

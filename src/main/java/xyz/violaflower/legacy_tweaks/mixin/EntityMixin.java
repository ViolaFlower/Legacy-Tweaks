package xyz.violaflower.legacy_tweaks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.DimensionTransition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.FatChat;


@Mixin(Entity.class)
public class EntityMixin {
    @WrapOperation(
            method = "handlePortal",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;changeDimension(Lnet/minecraft/world/level/portal/DimensionTransition;)Lnet/minecraft/world/entity/Entity;", ordinal = 0)
    )
    private Entity sendChatMessage(Entity instance, DimensionTransition transition, Operation<Entity> original) {
        if (!Tweaks.LEGACY_CHAT.getSubTweak("Dimension Notifications").isEnabled()) {
            original.call(instance, transition);
            return instance;
        }

        FatChat.DimensionNotification dn = Tweaks.LEGACY_CHAT.getSubTweak("Dimension Notifications"); // deez nuts

        ResourceLocation dimension = (transition.newLevel()).dimension().location();
        ResourceLocation playerDimension = instance.level().dimension().location();

        assert Minecraft.getInstance().player != null; // shut upppppppppppppppppppppppp
        ClientPacketListener connection = Minecraft.getInstance().player.connection;

        // Dexrn: TODO this needs to send globally on the server, instead of the player sending a chat message.
        if (dimension.toString().equals("minecraft:the_end") && dn.entranceMessage.get())
            connection.sendChat(Component.translatable("lt.tweaks.dimensionNotification.endEntranceMessage").getString().formatted(Minecraft.getInstance().player.getDisplayName().getString()));
        else if (playerDimension.toString().equals("minecraft:the_end") && dn.leaveMessage.get())
            connection.sendChat(Component.translatable("lt.tweaks.dimensionNotification.endLeaveMessage").getString().formatted(Minecraft.getInstance().player.getDisplayName().getString()));

        original.call(instance, transition);
        return instance;
    }
}

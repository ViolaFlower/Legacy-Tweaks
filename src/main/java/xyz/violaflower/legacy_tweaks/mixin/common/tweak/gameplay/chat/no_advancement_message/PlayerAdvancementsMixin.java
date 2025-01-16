package xyz.violaflower.legacy_tweaks.mixin.common.tweak.gameplay.chat.no_advancement_message;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(PlayerAdvancements.class)
public class PlayerAdvancementsMixin {
        // Dexrn: what the fuck is this
        @WrapOperation(
                method = "method_53637(Lnet/minecraft/advancements/AdvancementHolder;Lnet/minecraft/advancements/DisplayInfo;)V",
                at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/PlayerList;broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V", ordinal = 0)
        )
        void noAdvancement(PlayerList instance, Component message, boolean bypassHiddenChat, Operation<Void> original) {
                if (!Tweaks.GAMEPLAY.chatMechanics.advancementsChat.noAdvancementMessage.isOn())
                        original.call(instance, message, bypassHiddenChat);
        }
}

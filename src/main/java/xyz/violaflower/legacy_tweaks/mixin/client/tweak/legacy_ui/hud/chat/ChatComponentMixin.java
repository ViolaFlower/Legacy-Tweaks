package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.hud.chat;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import xyz.violaflower.legacy_tweaks.helper.tweak.legacy_ui.hud.HudHelper;
import xyz.violaflower.legacy_tweaks.util.client.screen.ScreenUtil;

@Mixin(value = ChatComponent.class, priority = -999999999)
public abstract class ChatComponentMixin {

    @Shadow protected abstract double screenToChatX(double d);

    @Shadow
    public static int getWidth(double width) {
        throw new RuntimeException("Failed to mixin!");
    }

    @ModifyArg(method = "render",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0), index = 4)
    private int changeChatBackground(int i) {
        if (HudHelper.guiHudTweaks.chatTweaks.grayChatBackground.isOn()) return 0x323232 + i;
        return i;
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void startChatRender(GuiGraphics guiGraphics, int tickCount, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen instanceof ChatScreen) return;
        if (minecraft.screen != null && minecraft.level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        float posX = HudHelper.getChatScreenSpacing();
        float posY = -95;
        HudHelper.start(guiGraphics, HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn(), HudHelper.guiHudTweaks.chatTweaks.applyHudScaleChat.isOn(), HudHelper.guiHudTweaks.chatTweaks.applyScreenSpacingChat.isOn(), false, true, 1f, posX, posY, 2f, 1f);
    }

    @Inject(method = "render", at = @At("TAIL"), cancellable = true)
    private void endChatRender(GuiGraphics guiGraphics, int tickCount, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen instanceof ChatScreen) return;
        if (minecraft.screen != null && minecraft.level != null && HudHelper.guiHudTweaks.generalTweaks.hideHudInScreen.isOn()) {
            ci.cancel();
            return;
        }
        HudHelper.end(guiGraphics, HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn());
    }

    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0))
    private void modifyChatX(Args args) {
        if (HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn()) {
            args.set(0, args.get(0).hashCode() - Math.round(HudHelper.getChatScreenSpacing()));
            args.set(2, args.get(2).hashCode() + Math.round(HudHelper.getChatScreenSpacing()));
        }
    }

    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 1))
    private void modifyMessageX(Args args) {
        if (HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn()) {
            args.set(0, args.get(0).hashCode() - Math.round(HudHelper.getChatScreenSpacing()));
            args.set(2, args.get(2).hashCode() - Math.round(HudHelper.getChatScreenSpacing()));
        }
    }

    @WrapOperation(method = "getMessageTagAt",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;screenToChatX(D)D"))
    private double modifyMessageTagXPos(ChatComponent instance, double x, Operation<Double> original) {
        if (HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn()) return screenToChatX(x) + Math.round(HudHelper.getChatScreenSpacing());
        return original.call(instance, x);
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;III)I"))
    private int addTextShadow(GuiGraphics instance, Font font, FormattedCharSequence text, int x, int y, int color, Operation<Integer> original) {
        if (HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn()) return ScreenUtil.drawStringWithBackdrop(instance, font, text, x, y, 1, color);
        return original.call(instance, font, text, x, y, color);
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0))
    private void setWidthSpan(GuiGraphics instance, int minX, int minY, int maxX, int maxY, int color, Operation<Void> original) {
        if (!HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn() || !HudHelper.guiHudTweaks.chatTweaks.messageWidthSpansScreen.get()) { original.call(instance, minX, minY, maxX, maxY, color); return; }
        // Dexrn: This was all done on my own, it may or may not work well.
        original.call(instance, minX, minY, Minecraft.getInstance().getWindow().getWidth() - minX, maxY, color);
    }

    @WrapOperation(method = "getWidth()I", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;getWidth(D)I"))
    private int changeChatWidth(double width, Operation<Integer> original) {
        if (!HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn() || !HudHelper.guiHudTweaks.chatTweaks.messageWidthSpansScreen.get()) return original.call(width);
        return (int) (Minecraft.getInstance().getWindow().getGuiScaledWidth() - HudHelper.getChatScreenSpacing() * 2);
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;", ordinal = 2))
    private Object startSetLineHeight(OptionInstance instance, Operation<Object> original) {
        if (!HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn() || !HudHelper.guiHudTweaks.chatTweaks.legacyMessageHeight.isOn()) return original.call(instance);
        return HudHelper.getLineHeight();
    }

    @WrapOperation(method = "getLineHeight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"))
    private Object endSetLineHeight(OptionInstance instance, Operation<Object> original) {
        if (!HudHelper.guiHudTweaks.chatTweaks.legacyChat.isOn() || !HudHelper.guiHudTweaks.chatTweaks.legacyMessageHeight.isOn()) return original.call(instance);
        return HudHelper.getLineHeight();
    }
}

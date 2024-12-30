package xyz.violaflower.legacy_tweaks.mixin;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.gui.LTScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(targets = "net/minecraft/client/gui/components/DebugScreenOverlay")
public class F3Mixin {
        @Inject(method = "getGameInformation", at = @At("RETURN"), cancellable = true)
        private void getGameInformation(CallbackInfoReturnable<List<String>> cir) {
                if (!TweakManager.getInstance().getTweak("F3 Info").isEnabled()) return;
                List<String> returnValue = cir.getReturnValue();
                ArrayList<String> strings = new ArrayList<>(returnValue);
                Map<String, Tweak> tweaks = TweakManager.getInstance().tweaks;
                strings.add("Active Tweaks (%s): ".formatted(tweaks.size()) + tweaks.values().stream().filter(Tweak::isEnabled).map(Tweak::getTweakID).collect(Collectors.joining(", ")));
                cir.setReturnValue(strings);
        }
}
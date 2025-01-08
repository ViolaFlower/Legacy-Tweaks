package xyz.violaflower.legacy_tweaks.mixin.client.tweak.f3_info.show_enabled_tweaks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(targets = "net/minecraft/client/gui/components/DebugScreenOverlay")
public class F3Mixin {
        @Inject(method = "getGameInformation", at = @At("RETURN"), cancellable = true)
        private void getGameInformation(CallbackInfoReturnable<List<String>> cir) {
                if (!Tweaks.F3INFO.isEnabled() || !Tweaks.F3INFO.showEnabledTweaks.get()) return;
                List<String> returnValue = cir.getReturnValue();
                ArrayList<String> strings = new ArrayList<>(returnValue);
                Map<String, Tweak> tweaks = TweakManager.getInstance().tweaks;
                // TODO: localize
                strings.add("Active Tweaks (%s): ".formatted(tweaks.size()) + tweaks.values().stream().filter(Tweak::isEnabled).map(Tweak::getTweakID).collect(Collectors.joining(", ")));
                cir.setReturnValue(strings);
        }
}
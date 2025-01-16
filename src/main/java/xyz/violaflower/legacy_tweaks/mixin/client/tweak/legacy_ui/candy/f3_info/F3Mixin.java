package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.candy.f3_info;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(targets = "net/minecraft/client/gui/components/DebugScreenOverlay")
public class F3Mixin {
        @Inject(method = "getGameInformation", at = @At("RETURN"), cancellable = true)
        private void getGameInformation(CallbackInfoReturnable<List<String>> cir) {
                if (!Tweaks.LEGACY_UI.f3Info.isEnabled() || !Tweaks.LEGACY_UI.f3Info.showEnabledTweaks.get()) return;
                List<String> returnValue = cir.getReturnValue();
                ArrayList<String> strings = new ArrayList<>(returnValue);
                Map<String, Tweak> tweaks = TweakManager.getInstance().tweaks;
                // TODO: localize
                strings.add(Lang.ACTIVE_TWEAKS.getString().formatted(tweaks.size()) + tweaks.values().stream().filter(Tweak::isEnabled).map(Tweak::getTweakID).collect(Collectors.joining(", ")));
                cir.setReturnValue(strings);
        }
}
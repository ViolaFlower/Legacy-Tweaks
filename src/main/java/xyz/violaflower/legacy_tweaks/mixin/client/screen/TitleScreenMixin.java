package xyz.violaflower.legacy_tweaks.mixin.client.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.client.gui.screen.config.LTScreen;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void initInject(CallbackInfo ci) {
        // Dexrn: this is in a bad location :thumbsup:
        this.addRenderableWidget(Button.builder(
                Component.translatable("lt.title.ltbutton"),
                button -> {
                    assert minecraft != null;
                    Screen old = minecraft.screen;
                    minecraft.setScreen(new LTScreen(old));
                }
        ).bounds(this.width / 2 - 100, this.height / 4 + 120, 200, 20).build());
    }
}

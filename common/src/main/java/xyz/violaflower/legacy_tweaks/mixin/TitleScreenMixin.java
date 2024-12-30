package xyz.violaflower.legacy_tweaks.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;
import xyz.violaflower.legacy_tweaks.gui.LTScreen;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void addButton(CallbackInfo ci) {
        // Dexrn: this is in a bad location :thumbsup:
        this.addDrawableChild(ButtonWidget.builder(
                Text.translatable("lt.title.ltbutton"),
                button -> {
                    MinecraftClient.getInstance().setScreen(new LTScreen());
                }
        ).dimensions(this.width / 2 - 100, this.height / 4 + 120, 200, 20).build());
    }
}

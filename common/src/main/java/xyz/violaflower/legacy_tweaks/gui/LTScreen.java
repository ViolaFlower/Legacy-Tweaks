package xyz.violaflower.legacy_tweaks.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

@Environment(EnvType.CLIENT)
public class LTScreen extends Screen {

    public LTScreen() {
        super(Text.translatable("lt.screens.ltscreen.title"));
    }

    @Override
    public void init() {
        TweakManager managerInstance = TweakManager.getInstance();
        int i = 60;
        for (Tweak tweak : managerInstance.tweaks.values()) {
            int finalI = i;
            this.addDrawable((context, mouseX, mouseY, delta) -> context.drawCenteredTextWithShadow(textRenderer, Text.literal(tweak.getTweakID() + " - " + tweak.getTweakAuthor()), width / 2, height / 2 - finalI, 0xffffff));
            this.addDrawableChild(ButtonWidget.builder(Text.literal(tweak.isEnabled() ? "Enabled" : "Disabled"), button -> {
                tweak.setEnabled(!tweak.isEnabled());
                button.setMessage(Text.literal(tweak.isEnabled() ? "Enabled" : "Disabled"));
                TweakManager.save();
            }).dimensions(width / 2, height / 2 - i, 200, 20).build());
            i -= 40;
        }
    }

    // Dexrn: Made this screen really quickly, now we have something to work on.
    // Also, some of this is janky. (expected)
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("lt.screens.ltscreen.title"), width / 2, height / 2 - 80, 0xffffff);

        // oops apparently creating 20 buttons every frame :)))))))

    }
}

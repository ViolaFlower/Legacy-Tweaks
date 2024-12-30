package xyz.violaflower.legacy_tweaks.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

public class LTScreen extends Screen {

    public LTScreen() {
        super(Component.translatable("lt.screens.ltscreen.title"));
    }

    @Override
    public void init() {
        TweakManager managerInstance = TweakManager.getInstance();
        int i = 60;
        for (Tweak tweak : managerInstance.tweaks.values()) {
            int finalI = i;
            this.addRenderableOnly((graphics, mouseX, mouseY, delta) -> graphics.drawCenteredString(font, Component.literal(tweak.getTweakID() + " - " + tweak.getTweakAuthor()), width / 2, height / 2 - finalI, 0xffffff));
            this.addRenderableWidget(Button.builder(Component.translatable(tweak.isEnabled() ? "lt.main.enabled" : "lt.main.disabled"), button -> {
                tweak.setEnabled(!tweak.isEnabled());
                button.setMessage(Component.translatable(tweak.isEnabled() ? "lt.main.enabled" : "lt.main.disabled"));
                TweakManager.save();
            }).bounds(width / 2, height / 2 - i, 200, 20).build());
            i -= 40;
        }
    }

    // Dexrn: Made this screen really quickly, now we have something to work on.
    // Also, some of this is janky. (expected)
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(font, Component.translatable("lt.screens.ltscreen.title"), width / 2, height / 2 - 80, 0xffffff);

        // oops apparently creating 20 buttons every frame :)))))))

    }
}

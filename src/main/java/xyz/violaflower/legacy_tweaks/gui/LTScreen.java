package xyz.violaflower.legacy_tweaks.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;

public class LTScreen extends Screen {

    public LTScreen() {
        super(Component.translatable("lt.screens.ltscreen.title"));
    }

    /* === Dexrn Says ===
    *  TODO: text wrapping
    *  TODO: scrolling
    *  Also, the UI is mainly a placeholder.
    */
    @Override
    public void init() {
        TweakManager managerInstance = TweakManager.getInstance();
        int i = 60;
        for (Tweak tweak : managerInstance.tweaks.values()) {
            int finalI = i;
            this.addRenderableOnly((graphics, mouseX, mouseY, delta) -> graphics.drawString(font, Component.literal(tweak.getTweakID() + " - " + tweak.getTweakAuthor()), width / 4, height / 2 - finalI, 0xffffff));
            this.addRenderableOnly((graphics, mouseX, mouseY, delta) -> graphics.drawString(font, Component.literal(tweak.getTweakDescription()), width / 4, height / 2 - finalI + 10, 0x505050));
            this.addRenderableWidget(Button.builder(Component.translatable(tweak.isEnabled() ? "lt.main.enabled" : "lt.main.disabled"), button -> {
                tweak.setEnabled(!tweak.isEnabled());
                button.setMessage(Component.translatable(tweak.isEnabled() ? "lt.main.enabled" : "lt.main.disabled"));
                TweakManager.save();
            }).bounds((width / 2) + 90, height / 2 - (i), 20, 20).build());
            this.addRenderableWidget(Button.builder(Component.translatable("lt.main.settings"), button -> {
                // https://www.minecraftforum.net/forums/archive/alpha/alpha-survival-single-player/798878-dohasdoshih-analysis-of-glitched-chunks
                LegacyTweaks.LOGGER.info("DOHASDOSHIH!");
            }).bounds((width / 2) + 70, height / 2 - (i), 20, 20).build());
            i -= 40;
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(font, Component.translatable("lt.screens.ltscreen.title"), width / 2, height / 2 - 80, 0xffffff);
    }
}

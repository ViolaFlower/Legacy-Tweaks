package xyz.violaflower.legacy_tweaks.client.gui.element;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

import java.util.function.Consumer;
import java.util.function.Function;

public class RecipeBookTabListing implements Renderable, GuiEventListener, NarratableEntry {
    public RecipeBookTabListing(RecipeBookCategories category) {
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

    }

    @Override
    public void setFocused(boolean focused) {

    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    public NarrationPriority narrationPriority() {
        return null;
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {

    }
}

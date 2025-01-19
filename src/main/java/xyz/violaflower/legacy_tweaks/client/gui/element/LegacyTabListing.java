package xyz.violaflower.legacy_tweaks.client.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class LegacyTabListing implements Renderable, GuiEventListener, NarratableEntry {
    public final List<LegacyTabButton> tabs;
    public int selectedIndex = 0;
    public boolean isFocused = false;
    public LegacyWidgetSprites genericTabWidgets = new LegacyWidgetSprites(Sprites.CRAFTING_TAB_LEFT_SIDE, Sprites.CRAFTING_TAB_CENTER, Sprites.CRAFTING_TAB_RIGHT_SIDE, Sprites.CRAFTING_TAB_SELECTED_SIDE, Sprites.CRAFTING_TAB_SELECTED, Sprites.CRAFTING_TAB_SELECTED_SIDE);

    public LegacyTabListing() {
        this.tabs = new ArrayList<>();
    }

    public void addTab(LegacyTabButton tab) {
        this.tabs.add(tab);
    }

    public void addTab(int x, int y, int width, int height, boolean isStateTriggered, Component text, LegacyWidgetSprites sprites, Function<LegacyTabButton, Renderable> icon, Consumer<LegacyTabButton> onPress) {
        addTab(new LegacyTabButton(x, y, width, height, isStateTriggered, selectedIndex, text, sprites, icon, p -> {
            if (selectedIndex != this.tabs.indexOf(p)) {
                selectedIndex = this.tabs.indexOf(p);
                onPress.accept(p);
            }
        }));
    }

    public LegacyTabListing createTab(int x, int y, int width, int height, boolean isStateTriggered, Component text, LegacyWidgetSprites sprites, Function<LegacyTabButton, Renderable> icon, Consumer<LegacyTabButton> onPress) {
        this.addTab(x, y, width, height, isStateTriggered, text, sprites, icon, onPress);
        return this;
    }

    public LegacyTabListing createTabWithPredeterminedTabWidget(int x, int y, int width, int height, boolean isStateTriggered, Component text, Function<LegacyTabButton, Renderable> icon, Consumer<LegacyTabButton> onPress) {
        this.addTab(x, y, width, height, isStateTriggered, text, this.genericTabWidgets, icon, onPress);
        return this;
    }

    public LegacyTabListing createMultipleTabs(int tabAmount, int x, int y, int width, int height, Component text, @Nullable LegacyWidgetSprites sprites, List<Function<LegacyTabButton, Renderable>> icons, Consumer<LegacyTabButton> onPress) {
        for (int i = 0; i <= tabAmount; i++) {
            LegacyWidgetSprites widgets = sprites != null ? sprites : this.genericTabWidgets;
            this.addTab(x, y, width, height, false, text, widgets, icons.get(i), onPress);
        }
        return this;
    }

    public void init(int x, int y, int width, boolean isVertical) {
        int xPos = 0;

        BiConsumer<LegacyTabButton, Integer> finalTabManager = (tabList, i)->{};
        BiConsumer<LegacyTabButton, Integer> tabManager = (tabList, i) -> {
            tabList.setWidth(width / this.tabs.size());
            tabList.setX(x + i);
            tabList.setY(y);
            finalTabManager.accept(tabList, i);
        };

        for (LegacyTabButton tabList : this.tabs) {
            tabManager.accept(tabList, xPos);
            xPos += isVertical ? tabList.getHeight() : tabList.getWidth();
        }
    }

    public void setSelectedTab(int index){
        if (!this.tabs.isEmpty()) {
            selectedIndex = -1;
            this.tabs.get(index).onPress();
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        for (int index = 0; index < this.tabs.size(); index++) {
            LegacyTabButton tab = this.tabs.get(index);
            tab.setStateTriggered(this.selectedIndex == index);
            tab.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    @Override
    public void setFocused(boolean focused) {
        focused = this.isFocused;
    }

    @Override
    public boolean isFocused() {
        return isFocused;
    }

    @Override
    public boolean mouseClicked(double a, double b, int c) {
        return !this.tabs.stream().filter(tabs-> tabs.mouseClicked(a, b, c)).toList().isEmpty();
    }

    @Override
    public NarrationPriority narrationPriority() {
        return null;
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {

    }
}

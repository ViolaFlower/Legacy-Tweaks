package xyz.violaflower.legacy_tweaks.client.gui.element;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

/// @see StateSwitchingButton
public class LegacyTabButton extends StateSwitchingButton {
    public final Function<LegacyTabButton, Renderable> icon;
    public final Consumer<LegacyTabButton> onPress;
    protected int selectedTabIndex;
    public LegacyWidgetSprites widgets;
    public final Component text;

    public LegacyTabButton(int x, int y, int width, int height, boolean isStateTriggered, int selectedTabIndex, Component text, LegacyWidgetSprites sprites, Function<LegacyTabButton, Renderable> icon, Consumer<LegacyTabButton> onPress) {
        super(x, y, width, height, isStateTriggered);
        this.icon = icon;
        this.onPress = onPress;
        this.selectedTabIndex = selectedTabIndex;
        this.text = text;
        this.isStateTriggered = isStateTriggered;
        this.initTextureValues(sprites);
    }

    public void initTextureValues(LegacyWidgetSprites widgets) {
        this.widgets = widgets;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (this.widgets != null) {
            int i = this.getY();
            if (this.isStateTriggered) {
                i -= 2;
            }
            RenderSystem.disableDepthTest();
            guiGraphics.blitSprite(this.widgets.get(this.isStateTriggered, this.selectedTabIndex), this.getX(), i, this.width, this.height);
            RenderSystem.enableDepthTest();
        }
    }

    public void setStateTriggered(boolean triggered) {
        this.isStateTriggered = triggered;
    }

    public boolean isStateTriggered() {
        return this.isStateTriggered;
    }

    public static Function<LegacyTabButton,Renderable> createIconItem(Item item){
        return b-> (guiGraphics, i, j, f) -> b.renderItem(guiGraphics, item.getDefaultInstance());
    }

    public static Function<LegacyTabButton,Renderable> createIconItem(ItemStack itemStack){
        return b-> (guiGraphics, i, j, f) -> b.renderItem(guiGraphics, itemStack);
    }

    public static Function<LegacyTabButton,Renderable> createIconSprite(ResourceLocation icon){
        return b-> (guiGraphics, i, j, f) -> b.renderIcon(guiGraphics, icon);
    }

    public static Function<LegacyTabButton,Renderable> createTextIcon(int color) {
        return b -> (graphics, i, j, f) -> b.renderString(graphics, Minecraft.getInstance().font, color);
    }

    private void renderItem(GuiGraphics guiGraphics, ItemStack itemStack) {
        int i = this.isStateTriggered ? -2 : 0;
        guiGraphics.renderFakeItem(itemStack, this.getX() + this.width/2 - 12, this.getY() + this.height/2 - 12 + i);
    }

    private void renderIcon(GuiGraphics guiGraphics, ResourceLocation icon){
        int i = this.isStateTriggered ? -2 : 0;
        guiGraphics.blitSprite(icon, getX() + this.width/2 - 12, getY() + this.height/2 - 12 + i, 24, 24);
    }

    protected void renderString(GuiGraphics guiGraphics, Font font, int color) {
        guiGraphics.drawString(font, this.text, getX() + (this.width - font.width(this.text))/2, getY() + (this.height - 7)/2, color, false);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        isStateTriggered = !isStateTriggered;
        onPress.accept(this);
    }

    public void onPress() {
        isStateTriggered = !isStateTriggered;
        onPress.accept(this);
    }

    @Override
    protected boolean clicked(double d, double e) {
        return isMouseOver(d,e);
    }

    @Override
    public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, Component.translatable(this.text.getString()));
    }
}

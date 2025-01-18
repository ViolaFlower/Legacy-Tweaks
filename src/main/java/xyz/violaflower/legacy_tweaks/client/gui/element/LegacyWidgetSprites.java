package xyz.violaflower.legacy_tweaks.client.gui.element;

import net.minecraft.resources.ResourceLocation;

public record LegacyWidgetSprites(ResourceLocation enabledLeft, ResourceLocation enabledCenter, ResourceLocation enabledRight, ResourceLocation focusedLeft, ResourceLocation focusedCenter, ResourceLocation focusedRight) {

    public LegacyWidgetSprites(ResourceLocation enabled, ResourceLocation focused) {
        this(enabled, enabled, enabled, focused, focused, focused);
    }

    public LegacyWidgetSprites(ResourceLocation enabledLeft, ResourceLocation enabledCenter, ResourceLocation enabledRight, ResourceLocation focusedLeft, ResourceLocation focusedCenter, ResourceLocation focusedRight) {
        this.enabledLeft = enabledLeft;
        this.enabledCenter = enabledCenter;
        this.enabledRight = enabledRight;
        this.focusedLeft = focusedLeft;
        this.focusedCenter = focusedCenter;
        this.focusedRight = focusedRight;
    }

    public ResourceLocation get(boolean focused, int type) {
        if (type == 0) {
            return focused ? this.focusedLeft : this.enabledLeft;
        } else if (type == 1) {
            return focused ? this.focusedCenter : this.enabledCenter;
        } else if (type == 2) {
            return focused ? this.focusedRight : this.enabledRight;
        }
        return this.enabledCenter;
    }
}
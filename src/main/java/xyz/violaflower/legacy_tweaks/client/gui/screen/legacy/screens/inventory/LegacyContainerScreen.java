package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

public class LegacyContainerScreen extends LegacyAbstractContainerScreen<ChestMenu> implements MenuAccess<ChestMenu> {
    private final int containerRows;

    public LegacyContainerScreen(ChestMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        int i = 222;
        int j = 114;
        this.containerRows = menu.getRowCount();
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(Sprites.CHEST_SMALL, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
        guiGraphics.blit(Sprites.CHEST_SMALL, i, j + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
    }
}

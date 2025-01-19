package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.util.client.screen.graphics.GraphicsUtil;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

/// Screen used in chest-like blocks like chests, shulker boxes, and barrels
/// @see ContainerScreen
public class LegacyContainerScreen extends LegacyAbstractContainerScreen<ChestMenu> implements MenuAccess<ChestMenu> {
    private final int containerRows;

    public LegacyContainerScreen(ChestMenu menu, Inventory playerInventory, Component title) {
        super(manipulateSlots(menu), playerInventory, title);
        this.containerRows = menu.getRowCount();
        this.imageWidth = 430 / 2;
        this.imageHeight = (282 / 2) + (this.containerRows * 42);
        this.titleLabelY = this.imageHeight;
    }

    private static ChestMenu manipulateSlots(ChestMenu chestMenu) {
        int i = 0;
        for (Slot slot : chestMenu.slots) {
            if (slot instanceof SlotExtension extension) {
                int slotOffset = (21 * chestMenu.getRowCount()) /2;
                if (i <= (9 * chestMenu.getRowCount()) - 1) {
                    extension.lt$setVisualX(slot.x * 18.66667f / 16 + 4.66667f);
                    extension.lt$setVisualY(slot.y * 18.66667f / 16 + 5.6f + slotOffset);
                } else if (i > (9 * chestMenu.getRowCount() - 1) && i <= (9 * chestMenu.getRowCount()) + 36) {
                    extension.lt$setVisualX(slot.x * 18.66667f / 16 + 4.66667f);
                    extension.lt$setVisualY(slot.y * 18.66667f / 16 + 3.56667f + (isHotbarSlot(chestMenu, i) ? 6.46667f : 4.54444f) + slotOffset - 0.33333f);
                }
                extension.lt$setSize(19);
            }
            i++;
        }
        return chestMenu;
    }

    public static boolean isHotbarSlot(ChestMenu chestMenu, int index) {
        int chest = (9 * chestMenu.getRowCount());
        return index >= chest + 27 && index < chest + 36 || index == chest + 36;
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int xPos = (this.width - this.imageWidth) / 2;
        int yPos = ((this.height - this.imageHeight) / 2) + (21 * this.containerRows)/2;
        float top = ((this.containerRows * 42f + 51f)/2);
        int bottom = 240/2;
        GraphicsUtil.blit(guiGraphics, Sprites.CHEST_LARGE, xPos, yPos, this.imageWidth, top, 0, 0, 430, top*2, 440, 550);
        GraphicsUtil.blit(guiGraphics, Sprites.CHEST_LARGE, xPos, yPos + top, this.imageWidth, bottom, 0, 303, 430, 240, 440, 550);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int yPos = ((this.height - this.imageHeight) / 2) + (21 * this.containerRows);
        int containerOffset = -105 + (10 * this.containerRows);
        int inventoryOffset = containerOffset + (21 * this.containerRows) + 20 + (this.containerRows <= 3 ? -1 : 0);
        guiGraphics.drawString(this.font, this.title, this.titleLabelX + 6, yPos + containerOffset, 4210752, false);
        guiGraphics.drawString(this.font, Lang.Container.INVENTORY.getString(), this.titleLabelX + 6, yPos + inventoryOffset, 0x404040, false);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
    }
}

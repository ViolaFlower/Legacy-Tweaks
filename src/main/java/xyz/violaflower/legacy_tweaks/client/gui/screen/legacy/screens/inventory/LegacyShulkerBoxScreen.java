package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.inventory.Slot;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.util.client.screen.graphics.GraphicsUtil;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

public class LegacyShulkerBoxScreen extends LegacyAbstractContainerScreen<ShulkerBoxMenu> {
    public LegacyShulkerBoxScreen(ShulkerBoxMenu menu, Inventory playerInventory, Component title) {
        super(manipulateSlots(menu), playerInventory, title);
        this.imageWidth = 430 / 2;
        this.imageHeight = 417 / 2;
    }

    private static ShulkerBoxMenu manipulateSlots(ShulkerBoxMenu shulkerBoxMenu) {
        int i = 0;
        for (Slot slot : shulkerBoxMenu.slots) {
            if (slot instanceof SlotExtension extension) {
                int slotOffset = (21 * 3) /2;
                if (i <= (9 * 3) - 1) {
                    extension.lt$setVisualX(slot.x * 18.66667f / 16 + 4.66667f);
                    extension.lt$setVisualY(slot.y * 18.66667f / 16 + 5.6f);
                } else if (i > ((9 * 3) - 1) && i <= (9 * 3) + 36) {
                    extension.lt$setVisualX(slot.x * 18.66667f / 16 + 4.66667f);
                    extension.lt$setVisualY(slot.y * 18.66667f / 16 + 4.56667f + (isHotbarSlot(i) ? 6.46667f : 4.54444f) - 0.33333f);
                }
                extension.lt$setSize(19);
            }
            i++;
        }
        return shulkerBoxMenu;
    }

    public static boolean isHotbarSlot(int index) {
        int chest = (9 * 3);
        return index >= chest + 27 && index < chest + 36 || index == chest + 36;
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int xPos = (this.width - this.imageWidth) / 2;
        int yPos = (this.height - this.imageHeight) / 2;
        GraphicsUtil.blit(guiGraphics, Sprites.CHEST_SMALL, xPos, yPos, this.imageWidth, 417f/2f, 0, 0, 430, 417, 440, 440);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int yPos = ((this.height - this.imageHeight) / 2);
        int containerOffset = -74;
        int inventoryOffset = containerOffset + 82;
        guiGraphics.drawString(this.font, this.title, this.titleLabelX + 6, yPos + containerOffset, 4210752, false);
        guiGraphics.drawString(this.font, Lang.Container.INVENTORY.getString(), this.titleLabelX + 6, yPos + inventoryOffset, 0x404040, false);
    }
}

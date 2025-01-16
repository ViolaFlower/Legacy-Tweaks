package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;


public class LegacyContainerScreen extends LegacyAbstractContainerScreen<ChestMenu> implements MenuAccess<ChestMenu> {
    private final int containerRows;

    public LegacyContainerScreen(ChestMenu menu, Inventory playerInventory, Component title) {
        super(manipulateSlots(menu), playerInventory, title);
        this.containerRows = menu.getRowCount();
        this.imageWidth = 430 / 2;
        this.imageHeight = (282 / 2) + this.containerRows * 42;
        int i = 222;
        int j = 114;
        this.titleLabelY = this.imageHeight - 194;
    }

    private static ChestMenu manipulateSlots(ChestMenu chestMenu) {
        int i = 0;
        for (Slot slot : chestMenu.slots) {
            if (slot instanceof SlotExtension extension) {
                if (i <= (9 * chestMenu.getRowCount()) - 1) {
                    extension.lt$setVisualX(slot.x * 18.66667f / 16 + 4.66667f);
                    extension.lt$setVisualY(slot.y * 18.66667f / 16 + 6f);
                } else if (i > (9 * chestMenu.getRowCount() - 1) && i <= (9 * chestMenu.getRowCount()) + 36) {
//                    extension.lt$setVisualX(slot.x * 18.66667f / 16 + 4.66667f);
//                    extension.lt$setVisualY(slot.y * 18.66667f / 16 + 6f);
                    extension.lt$setVisualX(slot.x * 18.66667f / 16 + 4.66667f);
                    extension.lt$setVisualY(slot.y * 18.66667f / 16 + 3.66667f + (isHotbarSlot(chestMenu, i) ? 6.46667f : 4.54444f));
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
//        int i = this.leftPos;
//        int j = this.topPos;
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        int topD = (this.containerRows * 42 + 52);
        int top = topD/2;
        float topF = ((this.containerRows * 42f + 52f)/2);
        int bottom = 240/2;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(1, topF / top, 1);
        guiGraphics.blit(Sprites.CHEST_LARGE, i, j, this.imageWidth, top, 0, 0, 430, topD, 440, 550);
        guiGraphics.pose().popPose();

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(1, (240f/2f) / bottom, 1);
        guiGraphics.blit(Sprites.CHEST_LARGE, i, j + 1 + top, this.imageWidth, 240/2, 0, 303, 430, 240, 440, 550);
        guiGraphics.pose().popPose();

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX + 6, this.titleLabelY + 2, 4210752, false);
        guiGraphics.drawString(this.font, Lang.Container.INVENTORY.getString(), this.titleLabelX + 6, this.titleLabelY + 84, 0x404040, false);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
    }
}

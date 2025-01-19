package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.crafting;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import xyz.violaflower.legacy_tweaks.client.gui.extention.CraftingMenuExtension;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyAbstractContainerScreen;
import xyz.violaflower.legacy_tweaks.util.client.screen.graphics.GraphicsUtil;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

public class LegacyQuickCraftingScreen<T extends AbstractContainerMenu & CraftingMenuExtension> extends LegacyAbstractContainerScreen<T> {
	 public <R extends AbstractContainerMenu> LegacyQuickCraftingScreen(R menu, Inventory playerInventory, Component title) {
		super(arr((T) menu), playerInventory, title);
		// 1035x635 aka 517.5x317.5
		this.imageWidth = 345;
		this.imageHeight = 212;
	}

	private static <T extends AbstractContainerMenu & CraftingMenuExtension> T arr(T menu) {
		int i = 0;
		{
			int invXo = 0;
			int invYo = 0;
			for (Slot slot : menu.lt$getVirtualPlayerInventory().iterateInventorySlots()) {
				if (!(slot instanceof SlotExtension extension)) continue;
				extension.lt$setVisualX(553f / 3f + invXo * 16);
				extension.lt$setVisualY(366f / 3f + invYo * 16);
				i++;
				if (++invXo >= 9) {
					invXo = 0;
					invYo++;
				}
			}
			for (Slot slot : menu.lt$getVirtualPlayerInventory().iterateHotbarSlots()) {
				if (!(slot instanceof SlotExtension extension)) continue;
				extension.lt$setVisualX(553f / 3f + invXo * 16);
				extension.lt$setVisualY(533f / 3f);
				i++;
				invXo++;
			}
			menu.lt$getVirtualPlayerInventory().iterateSlots().forEach(f -> ((SlotExtension)f).lt$setSize(14 + 1 / 3f));
		}//554 534
		return menu;
	}

	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		float trueHeight = 211.66667f;
		int xPos = (this.width - this.imageWidth) / 2;
		float yPos = ((this.height - trueHeight) / 2);
		//float top = ((this.containerRows * 42f + 51f)/2);
		//int bottom = 240/2;
		PoseStack pose = guiGraphics.pose();
		pose.pushPose();
		pose.translate(xPos, yPos, 0);
		pose.scale(1, trueHeight / this.imageHeight, 1);
		GraphicsUtil.blit(guiGraphics, Sprites.CRAFTING_BASIS, 0, 0, this.imageWidth, this.imageHeight, 0, 0, 1035, 635, 1035, 635);
		pose.popPose();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}
}

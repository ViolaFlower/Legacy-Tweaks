package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.inventory.Slot;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;

/// @see net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen
public abstract class LegacyAbstractFurnaceScreen<T extends AbstractFurnaceMenu> extends LegacyAbstractContainerScreen<T> implements RecipeUpdateListener {
	public final AbstractFurnaceRecipeBookComponent recipeBookComponent;
	private boolean widthTooNarrow;
	private final ResourceLocation texture;
	private final ResourceLocation litProgressSprite;
	private final ResourceLocation burnProgressSprite;

	public LegacyAbstractFurnaceScreen(
		T menu,
		AbstractFurnaceRecipeBookComponent recipeBookComponent,
		Inventory playerInventory,
		Component title,
		ResourceLocation texture,
		ResourceLocation listProgressSprite,
		ResourceLocation burnProgressSprite
	) {
		super(manip(menu), playerInventory, title);
		this.recipeBookComponent = recipeBookComponent;
		this.texture = texture;
		this.litProgressSprite = listProgressSprite;
		this.burnProgressSprite = burnProgressSprite;
		this.imageWidth = 214;
		this.imageHeight = 215; // 322.5
	}

	private static <T extends AbstractFurnaceMenu> T manip(T inventoryMenu) {
		int i = 0;
		for (Slot slot : inventoryMenu.slots) {
			if (slot instanceof SlotExtension extension) {
				if (3 <= i) {
					extension.lt$setVisualX(slot.x * 18.66667f / 16 + 3.66667f+0.33333f);
					extension.lt$setVisualY(slot.y * 18.66667f / 16 + 3.66667f + 3.66667f + 3.66667f + 3.66667f + (i > 29 ? 5.03333f : 3.44444f) - 5);
				}
				extension.lt$setSize(19);
			}
			i++;
		}
		return inventoryMenu;
	}

	@Override
	public void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
		this.addRenderableWidget(new ImageButton(this.leftPos + 20, this.height / 2 - 49, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, button -> {
			this.recipeBookComponent.toggleVisibility();
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
			button.setPosition(this.leftPos + 20, this.height / 2 - 49);
		}));
		this.titleLabelX = 14;
		this.titleLabelY = 12;

		this.inventoryLabelX = 14;
		this.inventoryLabelY = 98;
	}

	@Override
	public void containerTick() {
		super.containerTick();
		this.recipeBookComponent.tick();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
			this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTick);
		} else {
			super.render(guiGraphics, mouseX, mouseY, partialTick);
			this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTick);
			this.recipeBookComponent.renderGhostRecipe(guiGraphics, this.leftPos, this.topPos, true, partialTick);
		}

		this.renderTooltip(guiGraphics, mouseX, mouseY);
		this.recipeBookComponent.renderTooltip(guiGraphics, this.leftPos, this.topPos, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int leftPos = this.leftPos;
		int topPos = this.topPos;
		guiGraphics.pose().pushPose();
		guiGraphics.pose().scale(1, 1, 1);
		guiGraphics.blit(this.texture, leftPos, topPos, this.imageWidth, this.imageHeight, 0, 0, 642, 645, 642, 645);
		guiGraphics.pose().popPose();
//		if (this.menu.isLit()) {
//			int flameHeight = 14;
//			float progressHeight = this.menu.getLitProgress() * 14F;// Mth.ceil(this.menu.getLitProgress() * 13.0F) + 1;
//			//77 48
//			// topPos + 48 + flameHeight - progressHeight
//			int magic = 3;
//			int flameHeigh = flameHeight * magic;
//			int prog = (int) (progressHeight * magic);
//			int i = flameHeigh - prog;
//			guiGraphics.pose().pushPose();
//			guiGraphics.pose().translate(leftPos + 76 - 0.666667f, topPos + 48 + i/2, 0);
//			guiGraphics.pose().scale(1.5f, 1f/magic*1.5f, 1);
//			guiGraphics.blitSprite(this.litProgressSprite, 14, 14*magic, 0, i, 0, 0, 14, prog);
//			guiGraphics.pose().popPose();
//		}
		if (this.menu.isLit()) {
			int k = 14;
			int magic = 10;
			int l = Mth.ceil(this.menu.getLitProgress() * (209.0F)) + 1;
			guiGraphics.pose().pushPose();
			//guiGraphics.pose().scale(1, (float) 1 / 10, 1); // 231 144
			//guiGraphics.pose().scale(1.5f, 1.5f, 1.5f);
			guiGraphics.pose().scale(1, (float) 1 / 10, 1);
			guiGraphics.pose().translate(0-0.666667, 0, 0);
			guiGraphics.blitSprite(this.litProgressSprite, 20, 210, 0, 210-l, leftPos+77, topPos*10+460+210-l, 20, l);
			//guiGraphics.blitSprite(this.litProgressSprite, 14, 14*magic, 0, 140 - l, (int)(leftPos*1.5) + 16, ((int)(topPos*1.5)) + 480 + 14 - l, 14, l);
			guiGraphics.pose().popPose();
		}

		int k = 24;
		int l = Mth.ceil(this.menu.getBurnProgress() * k);
		guiGraphics.blitSprite(this.burnProgressSprite, k, 16, 0, 0, leftPos + 79, topPos + 34, l, 16);
	} // 42 314

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, button)) {
			return true;
		} else {
			return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? true : super.mouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	protected void slotClicked(Slot slot, int slotId, int mouseButton, ClickType type) {
		super.slotClicked(slot, slotId, mouseButton, type);
		this.recipeBookComponent.slotClicked(slot);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		return this.recipeBookComponent.keyPressed(keyCode, scanCode, modifiers) ? true : super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
		boolean bl = mouseX < (double)guiLeft
			|| mouseY < (double)guiTop
			|| mouseX >= (double)(guiLeft + this.imageWidth)
			|| mouseY >= (double)(guiTop + this.imageHeight);
		return this.recipeBookComponent.hasClickedOutside(mouseX, mouseY, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, mouseButton) && bl;
	}

	@Override
	public boolean charTyped(char codePoint, int modifiers) {
		return this.recipeBookComponent.charTyped(codePoint, modifiers) ? true : super.charTyped(codePoint, modifiers);
	}

	@Override
	public void recipesUpdated() {
		this.recipeBookComponent.recipesUpdated();
	}

	@Override
	public RecipeBookComponent getRecipeBookComponent() {
		return this.recipeBookComponent;
	}
}

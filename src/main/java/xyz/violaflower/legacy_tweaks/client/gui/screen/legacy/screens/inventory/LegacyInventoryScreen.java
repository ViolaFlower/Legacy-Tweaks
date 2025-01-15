package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;
import xyz.violaflower.legacy_tweaks.util.common.sound.SoundUtil;
import xyz.violaflower.legacy_tweaks.util.common.sound.Sounds;

public class LegacyInventoryScreen extends LegacyEffectRenderingInventoryScreen<InventoryMenu> implements RecipeUpdateListener {
	/**
	 * The old x position of the mouse pointer
	 */
	private float xMouse;
	/**
	 * The old y position of the mouse pointer
	 */
	private float yMouse;
	private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
	private boolean widthTooNarrow;
	private boolean buttonClicked;
	public LegacyInventoryScreen(Player player) {
		super(manip(player.inventoryMenu), player.getInventory(), Component.translatable("container.crafting"));
		this.titleLabelX = 97;
		this.imageWidth = 430 / 2;
		this.imageHeight = 435 / 2;
	}

	private static InventoryMenu manip(InventoryMenu inventoryMenu) {
		int i = 0;
		for (Slot slot : inventoryMenu.slots) {
			if (slot instanceof SlotExtension extension) {
				if (i == InventoryMenu.RESULT_SLOT) {
					System.out.println("moving the result slot...");
					extension.lt$setVisualX(0);
					extension.lt$setVisualY(0);
				} else {
					// yes, this is painful
					if (InventoryMenu.INV_SLOT_START <= i) {
						extension.lt$setVisualX(slot.x * 18.66667f / 16 + 3.66667f);
						extension.lt$setVisualY(slot.y * 18.66667f / 16 + 3.66667f + 3.66667f + 3.66667f + 3.66667f + (InventoryMenu.isHotbarSlot(i) ? 5.16667f : 3.33333f));
					}
				}
				extension.lt$setSize(19);
			}
			i++;
		}
		return inventoryMenu;
	}

	@Override
	public void containerTick() {
		if (this.minecraft.gameMode.hasInfiniteItems()) {
			this.minecraft
					.setScreen(
							new CreativeModeInventoryScreen(this.minecraft.player, this.minecraft.player.connection.enabledFeatures(), this.minecraft.options.operatorItemsTab().get())
					);
		} else {
			this.recipeBookComponent.tick();
		}
	}

	@Override
	protected void init() {
		if (false && this.minecraft.gameMode.hasInfiniteItems()) {
			this.minecraft
					.setScreen(
							new CreativeModeInventoryScreen(this.minecraft.player, this.minecraft.player.connection.enabledFeatures(), this.minecraft.options.operatorItemsTab().get())
					);
		} else {
			super.init();
			this.topPos-=20;
			this.widthTooNarrow = this.width < 379;
			this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
			int fjd = (width - (this.imageWidth-39)) / 2;
			int fje = (width - (this.imageWidth)) / 2;
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth-39);
			if (this.leftPos == fjd) this.leftPos = fje;
			this.addRenderableWidget(new ImageButton(this.leftPos + 104, this.height / 2 - 22, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, button -> {
				this.recipeBookComponent.toggleVisibility();
				this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth-39);
				if (this.leftPos == fjd) this.leftPos = fje;
				button.setPosition(this.leftPos + 104, this.height / 2 - 22);
				this.buttonClicked = true;
			}));
			this.addWidget(this.recipeBookComponent);
		}
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x404040, false);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
			this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTick);
		} else {
			super.render(guiGraphics, mouseX, mouseY, partialTick);
			this.recipeBookComponent.render(guiGraphics, mouseX, mouseY, partialTick);
			this.recipeBookComponent.renderGhostRecipe(guiGraphics, this.leftPos, this.topPos, false, partialTick);
		}

		this.renderTooltip(guiGraphics, mouseX, mouseY);
		this.recipeBookComponent.renderTooltip(guiGraphics, this.leftPos, this.topPos, mouseX, mouseY);
		this.xMouse = (float)mouseX;
		this.yMouse = (float)mouseY;
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int i = this.leftPos;
		int j = this.topPos;
		// the scale is there so 217px is properly stretched out to 217.5px
		guiGraphics.pose().pushPose();
		guiGraphics.pose().scale(1, 217.5F / this.imageHeight, 1);
		guiGraphics.blit(Sprites.INVENTORY(), i, j, this.imageWidth, this.imageHeight, 0, 0, 430, 435, 440, 440);
		guiGraphics.pose().popPose();
		InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, i + 26, j + 8, i + 75, j + 78, 30, 0.0625F, this.xMouse, this.yMouse, this.minecraft.player);
	}


	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		return this.recipeBookComponent.keyPressed(keyCode, scanCode, modifiers) ? true : super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean charTyped(char codePoint, int modifiers) {
		return this.recipeBookComponent.charTyped(codePoint, modifiers) ? true : super.charTyped(codePoint, modifiers);
	}

	@Override
	protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
		return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(x, y, width, height, mouseX, mouseY);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, button)) {
			this.setFocused(this.recipeBookComponent);
			return true;
		} else {
			return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? false : super.mouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (this.buttonClicked) {
			this.buttonClicked = false;
			return true;
		} else {
			return super.mouseReleased(mouseX, mouseY, button);
		}
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
	protected void slotClicked(Slot slot, int slotId, int mouseButton, ClickType type) {
		super.slotClicked(slot, slotId, mouseButton, type);
		this.recipeBookComponent.slotClicked(slot);
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

package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.util.client.screen.ScreenUtil;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;
import xyz.violaflower.legacy_tweaks.util.common.sound.SoundUtil;
import xyz.violaflower.legacy_tweaks.util.common.sound.Sounds;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/// @see InventoryScreen
public class LegacyInventoryScreen extends LegacyEffectRenderingInventoryScreen<InventoryMenu> implements RecipeUpdateListener {
	private static final Map<EquipmentSlot, ResourceLocation> TEXTURE_EMPTY_SLOTS = ((Supplier<LinkedHashMap<EquipmentSlot, ResourceLocation>>) () -> {
		LinkedHashMap<EquipmentSlot, ResourceLocation> equipmentSlotResourceLocationLinkedHashMap = new LinkedHashMap<>();
		equipmentSlotResourceLocationLinkedHashMap.put(EquipmentSlot.FEET, Sprites.EMPTY_ARMOR_SLOT_BOOTS);
		equipmentSlotResourceLocationLinkedHashMap.put(EquipmentSlot.LEGS, Sprites.EMPTY_ARMOR_SLOT_LEGGINGS);
		equipmentSlotResourceLocationLinkedHashMap.put(EquipmentSlot.CHEST, Sprites.EMPTY_ARMOR_SLOT_CHESTPLATE);
		equipmentSlotResourceLocationLinkedHashMap.put(EquipmentSlot.HEAD, Sprites.EMPTY_ARMOR_SLOT_HELMET);
		return equipmentSlotResourceLocationLinkedHashMap;
	}).get();
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
					float resultOffsetX = hideOffhand() ? 5.7f : 0f;
					float resultOffsetY = hideOffhand() ? 1.25f : 0f;
					float resultOffsetCrafting = isClassicCraftingOn() ? 0f : 10000f;
					extension.lt$setVisualX(180.5f - resultOffsetX + resultOffsetCrafting);
					extension.lt$setVisualY(41.5f + resultOffsetY);
					extension.lt$setSize(hideOffhand() ? 25 : 19);
				} else {
					// yes, this is painful
					float classicCraftingOffset = isClassicCraftingOn() ? 50 : 0;
					if (i >= InventoryMenu.ARMOR_SLOT_START && i < InventoryMenu.ARMOR_SLOT_END) {
						extension.lt$setVisualX((126/2f) - classicCraftingOffset);
						int fsu = i - InventoryMenu.ARMOR_SLOT_START;
						extension.lt$setVisualY(14.5f + fsu * 21);
						extension.lt$setNoItemIcon(Pair.of(InventoryMenu.BLOCK_ATLAS, TEXTURE_EMPTY_SLOTS.entrySet().stream().toList().get(TEXTURE_EMPTY_SLOTS.size()-fsu-1).getValue()));
					} else if (i >= InventoryMenu.CRAFT_SLOT_START && i < InventoryMenu.ARMOR_SLOT_END) {
						float craftingSlotOffset = isClassicCraftingOn() ? 0f : 10000f;
						float noOffhandOffset = hideOffhand() ? 4.5f : 0;
						extension.lt$setVisualX(slot.x * 18.66667f / 16 - 4.3f + craftingSlotOffset);
						extension.lt$setVisualY(slot.y * 18.66667f / 16 + 10.1f + craftingSlotOffset + noOffhandOffset);
					} else if (i == InventoryMenu.SHIELD_SLOT) {
						float shieldOffset = hideOffhand() ? 10000f : 0f;
						extension.lt$setVisualX((320/2f) - classicCraftingOffset + shieldOffset);
						extension.lt$setVisualY(155/2f);
						extension.lt$setNoItemIcon(Pair.of(InventoryMenu.BLOCK_ATLAS, Sprites.EMPTY_ARMOR_SLOT_SHIELD));
					} else if (InventoryMenu.INV_SLOT_START <= i) {
						extension.lt$setVisualX(slot.x * 18.66667f / 16 + 3.66667f);
						extension.lt$setVisualY(slot.y * 18.66667f / 16 + 3.66667f + 3.66667f + 3.66667f + 3.66667f + (InventoryMenu.isHotbarSlot(i) ? 5.16667f : 3.44444f));
					}
					extension.lt$setSize(19);
				}
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
		SoundUtil.playFullPitchSound(Sounds.PRESS, SoundSource.MASTER);
		if (false && this.minecraft.gameMode.hasInfiniteItems()) {
			this.minecraft
					.setScreen(
							new CreativeModeInventoryScreen(this.minecraft.player, this.minecraft.player.connection.enabledFeatures(), this.minecraft.options.operatorItemsTab().get())
					);
		} else {
			super.init();
			if (!ScreenUtil.isLargeGui()) this.topPos-=20;
			this.widthTooNarrow = this.width < 379;
			this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
			int fjd = (width - (this.imageWidth-39)) / 2;
			int fje = (width - (this.imageWidth)) / 2;
			int classicCraftingOffset = isClassicCraftingOn() ? 50 : 0;
			int noOffhandOffset = isClassicCraftingOn() ? hideOffhand() ? 0 : -21 : 0;
			int noOffhandAndClassicCraftingOffset = !isClassicCraftingOn() ? hideOffhand() ? 0 : 21 : 0;
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth-39);
			if (this.leftPos == fjd) this.leftPos = fje;
			if (showRecipeBook()) {
				this.addRenderableWidget(new ImageButton(this.leftPos + 160 - classicCraftingOffset - noOffhandOffset, this.topPos + 78 - noOffhandAndClassicCraftingOffset, 19, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, button -> {
					this.recipeBookComponent.toggleVisibility();
					this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth - 39);
					if (this.leftPos == fjd) this.leftPos = fje;
					button.setPosition(this.leftPos + 160 - classicCraftingOffset - noOffhandOffset, this.topPos + 78 - noOffhandAndClassicCraftingOffset);
					this.buttonClicked = true;
				}));
				this.addWidget(this.recipeBookComponent);
			}
		}
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		int craftingLabelOffset = hideOffhand() ? 4 : 0;
		if (isClassicCraftingOn()) guiGraphics.drawString(this.font, this.title, this.titleLabelX + 13, this.titleLabelY + 11 + craftingLabelOffset, 0x404040, false);
		guiGraphics.drawString(this.font, Lang.Container.INVENTORY.getString(), this.titleLabelX - 84, this.titleLabelY + 97, 0x404040, false);
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
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		int i = this.leftPos;
		int j = this.topPos;
		int playerOffset = isClassicCraftingOn() ? 0 : 50;
		// the scale is there so 217px is properly stretched out to 217.5px
		guiGraphics.pose().pushPose();
		guiGraphics.pose().scale(1, 217.5F / this.imageHeight, 1);
		guiGraphics.blit(Sprites.INVENTORY(), i, j, this.imageWidth, this.imageHeight, 0, 0, 430, 435, 440, 440);
		guiGraphics.pose().popPose();
		InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, i + 31 + playerOffset, j + 20, i + 110 + playerOffset, j + 90, 35, 0.0625F, this.xMouse, this.yMouse, this.minecraft.player);
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

	private static boolean isClassicCraftingOn() {
		return Tweaks.LEGACY_UI.legacyInventoryScreenTweak.classicCrafting.isOn();
	}

	private static boolean showRecipeBook() {
		return !Tweaks.LEGACY_UI.legacyInventoryScreenTweak.hideRecipeBook.isOn();
	}

	private static boolean hideOffhand() {
		return Tweaks.LEGACY_UI.legacyInventoryScreenTweak.noOffhand.isOn();
	}
}

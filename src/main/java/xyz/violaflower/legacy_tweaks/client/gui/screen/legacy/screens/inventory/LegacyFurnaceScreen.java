package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.util.common.assets.ModAsset;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

@Environment(EnvType.CLIENT)
public class LegacyFurnaceScreen extends LegacyAbstractFurnaceScreen<FurnaceMenu> {
	private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/furnace/lit_progress");
	private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/furnace/burn_progress");
	private static final ResourceLocation TEXTURE = ModAsset.textureLocation("gui/container/furnace.png");

	public LegacyFurnaceScreen(FurnaceMenu menu, Inventory playerInventory, Component title) {
		super(menu, new SmeltingRecipeBookComponent(), playerInventory, title, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
	}
}

package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.crafting;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.StateSwitchingButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.recipebook.*;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundRecipeBookChangeSettingsPacket;
import net.minecraft.recipebook.PlaceRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyTabButton;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyWidgetSprites;
import xyz.violaflower.legacy_tweaks.client.gui.extention.SlotExtension;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyAutoCraftingScreen;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyInventoryScreen;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class LegacyRecipeBookCraftingScreen extends LegacyAutoCraftingScreen<InventoryMenu> implements PlaceRecipe<Ingredient>, Renderable, GuiEventListener, NarratableEntry, RecipeShownListener {
//    private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
    private final Screen parent;
    private static float staticLeftPos;
    private static float staticTopPos;
    public ClientRecipeBook book;
    public final List<LegacyTabButton> tabs = new ArrayList<>();
    public LegacyTabButton selectedTab;
    protected RecipeBookMenu<?, ?> menu;
    private final RecipeBookPage recipeBookPage = new RecipeBookPage();
    protected final GhostRecipe ghostRecipe = new GhostRecipe();
    protected StateSwitchingButton filterButton;
    @Nullable
    private EditBox searchBox;
    private String lastSearch = "";
    private final StackedContents stackedContents = new StackedContents();
    public final LegacyWidgetSprites sprites = new LegacyWidgetSprites(Sprites.CRAFTING_TAB_LEFT_SIDE, Sprites.CRAFTING_TAB_CENTER, Sprites.CRAFTING_TAB_RIGHT_SIDE, Sprites.CRAFTING_TAB_SELECTED_SIDE, Sprites.CRAFTING_TAB_SELECTED, Sprites.CRAFTING_TAB_SELECTED_SIDE);
    public LegacyRecipeBookCraftingScreen(Player player, Screen parent) {
        super(manipulateInventorySlots(player.inventoryMenu), player.getInventory(), Component.translatable("container.crafting"));
        this.parent = parent;
        staticLeftPos = this.leftPos - (useSmallCrafting() ? 36.5f : 0f);
        staticTopPos = this.topPos;

    }

    protected void init(Minecraft minecraft, RecipeBookMenu<?, ?> menu) {
        this.book = minecraft.player.getRecipeBook();
        this.menu = menu;
        super.init();
        initBook();
    }

    public void initBook() {
        this.tabs.clear();
        createTabs();
        this.recipeBookPage.init(this.minecraft, (int) this.leftPos, (int) this.topPos);
//        this.recipeBookPage.addListener(this);
        this.selectedTab.setStateTriggered(true);
        setVisible();
        this.updateCollections(false);
        this.createTabs1();
        this.updateTabs();
    }

    private void createTabs() {
        for(RecipeBookCategories recipeBookCategories : RecipeBookCategories.getCategories(RecipeBookType.CRAFTING)) {
            List<RecipeBookCategories> categoryIndex = RecipeBookCategories.getCategories(RecipeBookType.CRAFTING);
            for (int i = 0; i == categoryIndex.size(); i++) {
                Function<LegacyTabButton, Renderable> icon = LegacyTabButton.createIconItem(recipeBookCategories.getIconItems().get(i));
                int xPos = (int) (this.leftPos - 20) + (i * 20);
                this.tabs.add(new LegacyTabButton(xPos, (int) this.topPos - 20, 101, 66, false, i == 0 ? 0 : i == categoryIndex.getLast().hashCode() ? 2 : 1, categoryIndex.indexOf(i), this.title, this.sprites, icon, b -> changeTab()));
            }
        }
    }

    private void createTabs1() {
        this.tabs.add(new LegacyTabButton((int) this.leftPos, (int) this.topPos - 20, 101, 66, false, 1, 0, this.title, this.sprites, LegacyTabButton.createIconItem(ItemStack.EMPTY), b -> changeTab()));
    }

    private RecipeBookCategories getCategory() {
        return RecipeBookCategories.getCategories(RecipeBookType.CRAFTING).get(this.tabs.get(this.selectedTab.tabIndex).tabIndex);
    }

    protected void setVisible() {
        this.initBook();
        this.book.setOpen(RecipeBookType.CRAFTING, true);
        this.sendUpdateSettings();
    }

    private void updateCollections(boolean resetPageNumber) {
        List<RecipeCollection> list = this.book.getCollection(this.getCategory());
        list.forEach((recipeCollection) -> recipeCollection.canCraft(this.stackedContents, this.menu.getGridWidth(), this.menu.getGridHeight(), this.book));
        List<RecipeCollection> list2 = Lists.newArrayList(list);
        list2.removeIf((recipeCollection) -> !recipeCollection.hasKnownRecipes());
        list2.removeIf((recipeCollection) -> !recipeCollection.hasFitting());
        String string = this.searchBox.getValue();
        if (!string.isEmpty()) {
            ClientPacketListener clientPacketListener = this.minecraft.getConnection();
            if (clientPacketListener != null) {
                ObjectSet<RecipeCollection> objectSet = new ObjectLinkedOpenHashSet(clientPacketListener.searchTrees().recipes().search(string.toLowerCase(Locale.ROOT)));
                list2.removeIf((recipeCollection) -> !objectSet.contains(recipeCollection));
            }
        }

        if (this.book.isFiltering(this.menu)) {
            list2.removeIf((recipeCollection) -> !recipeCollection.hasCraftable());
        }

        this.recipeBookPage.updateCollections(list2, resetPageNumber);
    }

    private void updateTabs() {
//        float i = this.leftPos;
//        float j = this.topPos;
//        int k = 27;
//        int l = 0;
//
//        for(LegacyTabButton recipeBookTabButton : this.tabs) {
//            RecipeBookCategories recipeBookCategories = this.getCategory();
//            if (recipeBookCategories != RecipeBookCategories.CRAFTING_SEARCH && recipeBookCategories != RecipeBookCategories.FURNACE_SEARCH) {
//                if (recipeBookTabButton.updateVisibility(this.book)) {
//                    recipeBookTabButton.setPosition(i, j + 27 * l++);
//                    recipeBookTabButton.startAnimation(this.minecraft);
//                }
//            } else {
//                recipeBookTabButton.visible = true;
//                recipeBookTabButton.setPosition(i, j + 27 * l++);
//            }
//        }

    }

    private void updateStackedContents() {
        this.stackedContents.clear();
        this.minecraft.player.getInventory().fillStackedContents(this.stackedContents);
        this.menu.fillCraftSlotsStackedContents(this.stackedContents);
        this.updateCollections(false);
    }

    public void recipesUpdated() {
        this.updateTabs();
        this.updateCollections(false);
    }

    public void recipesShown(List<RecipeHolder<?>> recipes) {
        for(RecipeHolder<?> recipeHolder : recipes) {
            this.minecraft.player.removeRecipeHighlight(recipeHolder);
        }

    }

    public void setupGhostRecipe(RecipeHolder<?> recipe, List<Slot> slots) {
        ItemStack itemStack = recipe.value().getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(recipe);
        this.ghostRecipe.addIngredient(Ingredient.of(new ItemStack[]{itemStack}), ((Slot)slots.get(0)).x, ((Slot)slots.get(0)).y);
        this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.value().getIngredients().iterator(), 0);
    }

    public void addItemToSlot(Ingredient item, int slot, int maxAmount, int x, int y) {
        if (!item.isEmpty()) {
            Slot slot2 = (Slot)this.menu.slots.get(slot);
            this.ghostRecipe.addIngredient(item, slot2.x, slot2.y);
        }

    }

    protected void sendUpdateSettings() {
        if (this.minecraft.getConnection() != null) {
            RecipeBookType recipeBookType = RecipeBookType.CRAFTING;
            boolean bl = this.book.getBookSettings().isOpen(recipeBookType);
            boolean bl2 = this.book.getBookSettings().isFiltering(recipeBookType);
            this.minecraft.getConnection().send(new ServerboundRecipeBookChangeSettingsPacket(recipeBookType, bl, bl2));
        }

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, partialTick, mouseX, mouseY);
        for(LegacyTabButton recipeBookTabButton : this.tabs) {
            recipeBookTabButton.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    //    @Override
//    public void recipesUpdated() {
//        this.recipeBookComponent.recipesUpdated();
//    }
//
//    @Override
//    public @NotNull RecipeBookComponent getRecipeBookComponent() {
//        return this.recipeBookComponent;
//    }

    @Override
    public void onClose() {
        super.onClose();
        if (parent instanceof LegacyInventoryScreen) this.minecraft.setScreen(new LegacyInventoryScreen(this.minecraft.player));
    }

    private static InventoryMenu manipulateInventorySlots(InventoryMenu menu) {
        int i = 0;
        for (Slot slot : menu.slots) {
            if (slot instanceof SlotExtension extension) {
                if (i == InventoryMenu.RESULT_SLOT) {
                    extension.lt$setVisualX(staticLeftPos + 128f);
                    extension.lt$setVisualY(staticTopPos + 145.45f);
                    extension.lt$setSize(32);
                } else {
                    // yes, this is painful
                    if (i >= InventoryMenu.ARMOR_SLOT_START && i < InventoryMenu.ARMOR_SLOT_END) {
                        extension.lt$setVisualX(staticLeftPos + 10000f);
                    } else if (i >= InventoryMenu.CRAFT_SLOT_START && i < InventoryMenu.ARMOR_SLOT_END) {
                        extension.lt$setVisualX(staticLeftPos + slot.x * 21.1559f/16f - 76.9f);
                        extension.lt$setVisualY(staticTopPos + slot.y * 21.1559f/16f + 115.9f);
                        extension.lt$setSize(21);
                    } else if (i == InventoryMenu.SHIELD_SLOT) {
                        extension.lt$setVisualX(staticLeftPos + 10000f);
                    } else if (InventoryMenu.INV_SLOT_START <= i) {
                        extension.lt$setVisualX(staticLeftPos + slot.x * 14.22f/16f + 169.5f);
                        extension.lt$setVisualY(staticTopPos + slot.y * 14.22f/16f + 55.3f + (InventoryMenu.isHotbarSlot(i) ? 4.5f : 0f));
                        extension.lt$setSize(14);
                    }
                }
            }
            i++;
        }
        return menu;
    }

    @Override
    protected void slotClicked(Slot slot, int slotId, int mouseButton, ClickType type) {

    }

    private void changeTab() {

    }

    @Override
    public NarrationPriority narrationPriority() {
        return null;
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {

    }
}

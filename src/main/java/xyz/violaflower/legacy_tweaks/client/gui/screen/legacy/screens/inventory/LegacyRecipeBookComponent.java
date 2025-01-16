package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;

public class LegacyRecipeBookComponent extends RecipeBookComponent {

    @Override
    public void initVisuals() {
//        ((RecipeBookComponentAccessor) this).legacyTweaks$xOffset() = ((RecipeBookComponentAccessor) this).legacyTweaks$widthTooNarrow() ? 0 : 86;
//        int i = (((RecipeBookComponentAccessor) this).legacyTweaks$width() - 147) / 2 - ((RecipeBookComponentAccessor) this).legacyTweaks$xOffset();
//        int j = (((RecipeBookComponentAccessor) this).legacyTweaks$height() - 166) / 2;
//        ((RecipeBookComponentAccessor) this).legacyTweaks$stackedContents().clear();
//        this.minecraft.player.getInventory().fillStackedContents(((RecipeBookComponentAccessor) this).legacyTweaks$stackedContents());
//        this.menu.fillCraftSlotsStackedContents(((RecipeBookComponentAccessor) this).legacyTweaks$stackedContents());
//        String string = ((RecipeBookComponentAccessor) this).legacyTweaks$searchBox() != null ? ((RecipeBookComponentAccessor) this).legacyTweaks$searchBox().getValue() : "";
//        Font var10003 = this.minecraft.font;
//        int var10004 = i + 25;
//        int var10005 = j + 13;
//        Objects.requireNonNull(this.minecraft.font);
//        ((RecipeBookComponentAccessor) this).legacyTweaks$searchBox() = new EditBox(var10003, var10004, var10005, 81, 9 + 5, Component.translatable("itemGroup.search"));
//        ((RecipeBookComponentAccessor) this).legacyTweaks$searchBox().setMaxLength(50);
//        ((RecipeBookComponentAccessor) this).legacyTweaks$searchBox().setVisible(true);
//        ((RecipeBookComponentAccessor) this).legacyTweaks$searchBox().setTextColor(16777215);
//        ((RecipeBookComponentAccessor) this).legacyTweaks$searchBox().setValue(string);
//        ((RecipeBookComponentAccessor) this).legacyTweaks$searchBox().setHint(((RecipeBookComponentAccessor) this).legacyTweaks$SEARCH_HINT());
//        ((RecipeBookComponentAccessor) this).legacyTweaks$recipeBookPage().init(this.minecraft, i, j);
//        ((RecipeBookComponentAccessor) this).legacyTweaks$recipeBookPage().addListener(this);
//        this.filterButton = new StateSwitchingButton(i + 110, j + 12, 26, 16, ((RecipeBookComponentAccessor) this).legacyTweaks$book().isFiltering(this.menu));
//        ((RecipeBookComponentAccessor) this).legacyTweaks$updateFilterButtonTooltip();
//        this.initFilterButtonTextures();
//        ((RecipeBookComponentAccessor) this).legacyTweaks$tabButtons().clear();
//
//        for(RecipeBookCategories recipeBookCategories : RecipeBookCategories.getCategories(this.menu.getRecipeBookType())) {
//            this.tabButtons.add(new RecipeBookTabButton(recipeBookCategories));
//        }
//
//        if (this.selectedTab != null) {
//            this.selectedTab = (RecipeBookTabButton)this.tabButtons.stream().filter((recipeBookTabButton) -> recipeBookTabButton.getCategory().equals(this.selectedTab.getCategory())).findFirst().orElse((Object)null);
//        }
//
//        if (this.selectedTab == null) {
//            this.selectedTab = (RecipeBookTabButton)this.tabButtons.get(0);
//        }
//
//        this.selectedTab.setStateTriggered(true);
//        this.updateCollections(false);
//        this.updateTabs();
    }
}

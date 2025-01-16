package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.StackedContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(targets = "net/minecraft/client/gui/screens/recipebook/RecipeBookComponent")
public interface RecipeBookComponentAccessor {

    @Accessor("xOffset")
    int legacyTweaks$xOffset();

    @Accessor("widthTooNarrow")
    boolean legacyTweaks$widthTooNarrow();

    @Accessor("width")
    int legacyTweaks$width();

    @Accessor("height")
    int legacyTweaks$height();

    @Accessor("stackedContents")
    StackedContents legacyTweaks$stackedContents();

    @Accessor("searchBox")
    EditBox legacyTweaks$searchBox();

    @Accessor("SEARCH_HINT")
    Component legacyTweaks$SEARCH_HINT();

    @Accessor("recipeBookPage")
    RecipeBookPage legacyTweaks$recipeBookPage();

    @Accessor("book")
    ClientRecipeBook legacyTweaks$book();

    @Accessor("tabButtons")
    List<RecipeBookTabButton> legacyTweaks$tabButtons();

    @Accessor("selectedTab")
    RecipeBookTabButton legacyTweaks$selectedTab();

    @Invoker("updateFilterButtonTooltip")
    void legacyTweaks$updateFilterButtonTooltip();

    @Invoker("updateCollections")
    void legacyTweaks$updateCollections(boolean resetPageNumber);

    @Invoker("updateTabs")
    void legacyTweaks$updateTabs();
}

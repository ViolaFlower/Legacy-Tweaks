package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.screen.container;

import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(RecipeBookPage.class)
public interface RecipeBookPageAccessor {
	@Accessor
	List<RecipeCollection> getRecipeCollections();
}

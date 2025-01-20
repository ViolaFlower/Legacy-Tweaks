package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.screen.container;

import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RecipeBookComponent.class)
public interface RecipeBookComponentAccessor {
	@Accessor
	RecipeBookPage getRecipeBookPage();
	@Invoker
	void callUpdateCollections(boolean resetPage);
}

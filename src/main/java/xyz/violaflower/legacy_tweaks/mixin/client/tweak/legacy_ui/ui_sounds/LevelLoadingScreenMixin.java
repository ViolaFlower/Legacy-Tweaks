package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.ui_sounds;

import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LevelLoadingScreen.class)
public abstract class LevelLoadingScreenMixin extends Screen {

    protected LevelLoadingScreenMixin(Component title) {
        super(title);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(null);
    }
}

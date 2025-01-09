package xyz.violaflower.legacy_tweaks.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.client.gui.screen.config.LTScreen;
import xyz.violaflower.legacy_tweaks.client.gui.screen.config.SettingsScreen;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyTitleScreen;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.AbstractSliderButtonAccessor;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

import java.util.ArrayList;
import java.util.List;

public class LaunchScreen extends Screen {
    private final int defaultPanoramaTimer = 50;
    private int panoramaTimer = 50;
    private final int defaultTimer = 200;
    private int nextScreenTimer = defaultTimer;
    private byte screensShown = 0;
    private final List<ResourceLocation> splashes = new ArrayList<>();
    private boolean isTransitioning = false;
    private boolean transitionDirection = false; // false for hide, true for show.

    // ============= WARNING =============
    // This class is RADIOACTIVE
    // Being near this code for too long will cause your brain to start dissolving
    //
    // On a serious note this code is probably shit because I can't be bothered to make it better *at the moment*.
    // I want it to Just Work for when I push it, someone else can TODO: fix. (it might end up being me anyway)

    public LaunchScreen() {
        super(Component.empty());
        splashes.add(ResourceLocation.tryBuild("legacy_tweaks", "textures/gui/launch/tweaks.png"));
        splashes.add(ResourceLocation.tryBuild("legacy_tweaks", "textures/gui/launch/nefarious.png"));
        splashes.add(ResourceLocation.tryBuild("legacy_tweaks", "textures/gui/launch/idk.png"));
    }

    @Override
    protected void init() {
    }

    @Override
    public void render(GuiGraphics guiGraphics, int width, int height, float f) {
        if (isTransitioning) {
            // enable the shits so we can change opacity
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, (float) panoramaTimer / 50);

            // so if the transition direction is true, we want to fade in, otherwise fade out.
            if (transitionDirection) {
                panoramaTimer++;
            } else {
                panoramaTimer--;
            }

            // if panorama timer is 0 or lower, then we want to stop the transition.
            if (panoramaTimer <= 0) {
                this.isTransitioning = false;
            }
        } else {
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        }

        RenderSystem.setShaderTexture(0, splashes.get(screensShown));

        // HOW THE FUCK DO I SCALE THIS CORRECTLY
        guiGraphics.blit(splashes.get(screensShown), 0, 0, 0, 0, width, height, width, height);

        // this doesn't fucking work because can't think correctly right now
        // it needs to fade in and fade out.
        // not gonna even try to comment this, I barely even understand it now
        if (nextScreenTimer > 0) {
            nextScreenTimer -= 1;
            if (nextScreenTimer <= 50) {
                transitionDirection = false;
                isTransitioning = true;
            } else {
                panoramaTimer = defaultPanoramaTimer;
            }
        } else {
            if (screensShown >= splashes.size() - 1) {
                Minecraft.getInstance().setScreen(new LegacyTitleScreen());
            } else {
                transitionDirection = true;
                isTransitioning = true;
                screensShown += 1;
                nextScreenTimer = defaultTimer;
            }
        }
    }
}

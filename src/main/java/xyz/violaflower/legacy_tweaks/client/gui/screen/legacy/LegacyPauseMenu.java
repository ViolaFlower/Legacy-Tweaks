package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import com.mojang.realmsclient.RealmsMainScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.achievement.StatsScreen;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import xyz.violaflower.legacy_tweaks.client.gui.screen.DataScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.enums.PlayGameScreen;
import xyz.violaflower.legacy_tweaks.util.common.assets.ModAsset;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;
import xyz.violaflower.legacy_tweaks.util.common.sound.SoundUtil;
import xyz.violaflower.legacy_tweaks.util.common.sound.Sounds;

import java.util.Objects;

public class LegacyPauseMenu extends LegacyScreen {
    private FrameLayout frameLayout;
    private LegacyLogoRenderer logoRenderer;

    public LegacyPauseMenu() {
        super(Component.empty());
        this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, LegacyLogoRenderer::getLegacyLogoRenderer);
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        this.clearFocus();

        frameLayout = new FrameLayout();
        LinearLayout linearLayout = LinearLayout.vertical().spacing(getButtonSpacing());
        linearLayout.addChild(Button.builder(Lang.PauseScreen.RESUME_GAME.get(), button -> {
            this.minecraft.setScreen(null);
            this.minecraft.mouseHandler.grabMouse();
        }).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Lang.TitleScreen.HELP_OPTIONS.get(), button -> {
            if (Tweaks.LEGACY_UI.legacyHelpOptionsScreen.useLegacyHelpOptionsScreen.isOn()) {
                Minecraft.getInstance().setScreen(DataScreen.makeDataDrivenScreen(this, ModAsset.getResourceLocation("ltguis/help-and-options-screen.json")));
            } else {
                Minecraft.getInstance().setScreen(DataScreen.makeDataDrivenScreen(this, ModAsset.getResourceLocation("ltguis/optionsscreen.json")));
            }
        }).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Lang.PauseScreen.ACHIEVEMENTS.get(), button -> this.minecraft.setScreen(new AdvancementsScreen(this.minecraft.player.connection.getAdvancements(), this))).size(getButtonWidth(), getButtonHeight()).build());
        linearLayout.addChild(Button.builder(Lang.TitleScreen.LEADERBOARDS.get(), button -> this.minecraft.setScreen(new StatsScreen(this, this.minecraft.player.getStats()))).size(getButtonWidth(), getButtonHeight()).build());

        Component component = this.minecraft.isLocalServer() ? Lang.PauseScreen.EXIT_GAME.get() : Lang.PauseScreen.DISCONNECT.get();
        if (Tweaks.LEGACY_UI.legacyTitleScreen.showQuitButton.isOn()) {
            linearLayout.addChild(Button.builder(component, button -> {
                button.active = false;
                this.minecraft.getReportingContext().draftReportHandled(this.minecraft, this, this::onDisconnect, true);
            }).size(getButtonWidth(), getButtonHeight()).build());
        }
        //this.linearLayout.visitWidgets(this::addRenderableWidget);

        frameLayout.addChild(linearLayout);
        frameLayout.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }



    private void onDisconnect() {
        boolean bl = this.minecraft.isLocalServer();
        ServerData serverData = this.minecraft.getCurrentServer();
        this.minecraft.level.disconnect();
        if (bl) {
            this.minecraft.disconnect(new GenericMessageScreen(Component.translatable("menu.savingLevel")));
        } else {
            this.minecraft.disconnect();
        }

        TitleScreen titleScreen = new TitleScreen();
        if (bl) {
            this.minecraft.setScreen(titleScreen);
        } else if (serverData != null && serverData.isRealm()) {
            this.minecraft.setScreen(new RealmsMainScreen(titleScreen));
        } else {
            this.minecraft.setScreen(new JoinMultiplayerScreen(titleScreen));
        }

    }

    @Override
    protected void repositionElements() {
        if (frameLayout == null) return;
        frameLayout.setMinWidth(this.width);
        frameLayout.setY(getButtonHeightPos());
//		frameLayout.setY(height/2-165/3);
        frameLayout.arrangeElements();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        logoRenderer.renderLogo(guiGraphics, this.width, 1);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(null);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256 && this.shouldCloseOnEsc()) {
            if (Tweaks.LEGACY_UI.generalScreenTweaks.useLegacyUISounds.isOn()) SoundUtil.playFullPitchSound(Sounds.BACK, SoundSource.MASTER);
            this.minecraft.setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}

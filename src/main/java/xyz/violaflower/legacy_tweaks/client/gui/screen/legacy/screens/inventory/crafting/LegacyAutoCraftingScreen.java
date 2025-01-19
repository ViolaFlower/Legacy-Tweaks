//package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.crafting;
//
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.network.chat.Component;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.inventory.AbstractContainerMenu;
//import net.minecraft.world.inventory.InventoryMenu;
//import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyAbstractContainerScreen;
//import xyz.violaflower.legacy_tweaks.util.client.screen.ScreenUtil;
//import xyz.violaflower.legacy_tweaks.util.client.screen.graphics.GraphicsUtil;
//import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;
//import xyz.violaflower.legacy_tweaks.util.common.sound.SoundUtil;
//import xyz.violaflower.legacy_tweaks.util.common.sound.Sounds;
//
//public abstract class LegacyAutoCraftingScreen <T extends AbstractContainerMenu> extends LegacyAbstractContainerScreen<T> {
//    public LegacyAutoCraftingScreen(T menu, Inventory playerInventory, Component title) {
//        super(menu, playerInventory, title);
//        if (useSmallCrafting()) {
//            this.imageWidth = (int) (591f / 2f);
//        } else {
//            this.imageWidth = (int) (689f / 2f);
//        }
//        this.imageHeight = (int) (424f / 2f);
//        //this.leftPos = ((this.width + this.imageWidth) / 2);
//        //this.topPos = ((this.height + this.imageHeight) / 2) - 20f;
//    }
//
//    @Override
//    protected void init() {
//        super.init();
//        if (!ScreenUtil.isLargeGui()) this.topPos-=20;
//        SoundUtil.playFullPitchSound(Sounds.PRESS, SoundSource.MASTER);
//        //this.leftPos = this.leftPos - (useSmallCrafting() ? 0f : 40f);
//    }
//
//    @Override
//    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
//
//    }
//
//    @Override
//    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
//        this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
//    }
//
//    @Override
//    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
//        //this.leftPos = this.leftPos - (useSmallCrafting() ? 0f : 40f);
//        if (useSmallCrafting()) {
//            GraphicsUtil.blit(guiGraphics, Sprites.SMALL_CRAFTING_1080, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, 0f, 0f, 591f, 424f, 700f, 440f);
//        } else {
//            GraphicsUtil.blit(guiGraphics, Sprites.LARGE_CRAFTING_1080, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, 0f, 0f, 689f, 424f, 700f, 440f);
//        }
//    }
//
//    public boolean useSmallCrafting() {
//        return menu instanceof InventoryMenu;
//    }
//}

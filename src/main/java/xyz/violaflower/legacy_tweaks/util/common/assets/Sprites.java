package xyz.violaflower.legacy_tweaks.util.common.assets;

import net.minecraft.resources.ResourceLocation;

public interface Sprites {
    ResourceLocation FIREWORK_LEGACY = ModAsset.textureLocation("entity/projectiles/firework.png");
    ResourceLocation BACKGROUND_LOC = ModAsset.getResourceLocation("container/gui_background");
    ResourceLocation INSET_BACKGROUND = ModAsset.getResourceLocation("container/inset_background");
    ResourceLocation TOP_NAV_LEFT = ModAsset.getResourceLocation("container/top_nav_left");
    ResourceLocation PANORAMA_DAY = ModAsset.guiLocation("panorama/panorama_day.png");
    ResourceLocation PANORAMA_NIGHT = ModAsset.guiLocation("panorama/panorama_night.png");

    ResourceLocation HOTBAR_SELECTION = ModAsset.getResourceLocation("hud/hotbar_selection");

    ResourceLocation MINECRAFT_LEGACY = ModAsset.guiLocation("title/minecraft.png");

    ResourceLocation OLD_BUTTON_HIGHLIGHTED = ModAsset.getResourceLocation("widget/old_button_highlighted");

    ResourceLocation MINECRAFT_LOGO = ResourceLocation.withDefaultNamespace("textures/gui/title/minecraft.png");
    ResourceLocation EASTER_EGG_LOGO = ResourceLocation.withDefaultNamespace("textures/gui/title/minceraft.png");
    ResourceLocation MINECRAFT_EDITION = ResourceLocation.withDefaultNamespace("textures/gui/title/edition.png");
}

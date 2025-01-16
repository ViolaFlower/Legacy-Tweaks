package xyz.violaflower.legacy_tweaks.util.common.assets;

import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.LegacyUI;

public interface Sprites {
    ResourceLocation FIREWORK_LEGACY = ModAsset.textureLocation("entity/projectiles/firework.png");
    ResourceLocation BACKGROUND_LOC = ModAsset.getResourceLocation("container/gui_background");
    ResourceLocation INSET_BACKGROUND = ModAsset.getResourceLocation("container/inset_background");
    ResourceLocation TOP_NAV_LEFT = ModAsset.getResourceLocation("container/top_nav_left");
    ResourceLocation PANORAMA_DAY = ModAsset.guiLocation("panorama/panorama_day.png");
    ResourceLocation PANORAMA_NIGHT = ModAsset.guiLocation("panorama/panorama_night.png");

    ResourceLocation HOTBAR_SELECTION = ModAsset.getResourceLocation("hud/hotbar_selection");

    ResourceLocation EMPTY_ARMOR_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/legacy_armor_slot_helmet");
    ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/legacy_armor_slot_chestplate");
    ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/legacy_armor_slot_leggings");
    ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/legacy_armor_slot_boots");
    ResourceLocation EMPTY_ARMOR_SLOT_SHIELD = ResourceLocation.withDefaultNamespace("item/legacy_armor_slot_shield");

    ResourceLocation MINECRAFT_LEGACY = ModAsset.guiLocation("title/minecraft.png");

    ResourceLocation MENU_BACKGROUND = ModAsset.guiLocation("menu_background.png");

    ResourceLocation OLD_BUTTON_HIGHLIGHTED = ModAsset.getResourceLocation("widget/old_button_highlighted");

    ResourceLocation MINECRAFT_LOGO = ResourceLocation.withDefaultNamespace("textures/gui/title/minecraft.png");
    ResourceLocation EASTER_EGG_LOGO = ResourceLocation.withDefaultNamespace("textures/gui/title/minceraft.png");
    ResourceLocation MINECRAFT_EDITION = ResourceLocation.withDefaultNamespace("textures/gui/title/edition.png");

    ResourceLocation CHEST_SMALL = ModAsset.guiLocation("container/small_chest.png");
    ResourceLocation CHEST_LARGE = ModAsset.guiLocation("container/large_chest.png");

    static ResourceLocation INVENTORY() {
        LegacyUI.LegacyInventoryScreenTweak tweak = Tweaks.LEGACY_UI.legacyInventoryScreenTweak;
        if (tweak.classicCrafting.isOn()) {
            if (tweak.noOffhand.isOn()) return ModAsset.guiLocation("container/classic_inventory.png");
            return ModAsset.guiLocation("container/classic_inventory_tu53.png");
        }

        if (tweak.noOffhand.isOn())
            return ModAsset.guiLocation("container/main_inventory.png");
        return ModAsset.guiLocation("container/main_inventory_tu53.png");
    }
}

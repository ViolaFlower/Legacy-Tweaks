package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.legacy_container;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyContainerScreen;

import java.util.Map;

@Mixin(MenuScreens.class)
public class MenuScreenMixin {

    @Final
    @Shadow private static Map<MenuType<?>, MenuScreens.ScreenConstructor<?, ?>> SCREENS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void changeMenuScreens(CallbackInfo ci) {
        SCREENS.clear();
        MenuScreens.register(MenuType.GENERIC_9x1, LegacyContainerScreen::new);
        MenuScreens.register(MenuType.GENERIC_9x2, LegacyContainerScreen::new);
        MenuScreens.register(MenuType.GENERIC_9x3, LegacyContainerScreen::new);
        MenuScreens.register(MenuType.GENERIC_9x4, LegacyContainerScreen::new);
        MenuScreens.register(MenuType.GENERIC_9x5, LegacyContainerScreen::new);
        MenuScreens.register(MenuType.GENERIC_9x6, LegacyContainerScreen::new);
        MenuScreens.register(MenuType.GENERIC_3x3, DispenserScreen::new);
        MenuScreens.register(MenuType.CRAFTER_3x3, CrafterScreen::new);
        MenuScreens.register(MenuType.ANVIL, AnvilScreen::new);
        MenuScreens.register(MenuType.BEACON, BeaconScreen::new);
        MenuScreens.register(MenuType.BLAST_FURNACE, BlastFurnaceScreen::new);
        MenuScreens.register(MenuType.BREWING_STAND, BrewingStandScreen::new);
        MenuScreens.register(MenuType.CRAFTING, CraftingScreen::new);
        MenuScreens.register(MenuType.ENCHANTMENT, EnchantmentScreen::new);
        MenuScreens.register(MenuType.FURNACE, FurnaceScreen::new);
        MenuScreens.register(MenuType.GRINDSTONE, GrindstoneScreen::new);
        MenuScreens.register(MenuType.HOPPER, HopperScreen::new);
        MenuScreens.register(MenuType.LECTERN, LecternScreen::new);
        MenuScreens.register(MenuType.LOOM, LoomScreen::new);
        MenuScreens.register(MenuType.MERCHANT, MerchantScreen::new);
        MenuScreens.register(MenuType.SHULKER_BOX, ShulkerBoxScreen::new);
        MenuScreens.register(MenuType.SMITHING, SmithingScreen::new);
        MenuScreens.register(MenuType.SMOKER, SmokerScreen::new);
        MenuScreens.register(MenuType.CARTOGRAPHY_TABLE, CartographyTableScreen::new);
        MenuScreens.register(MenuType.STONECUTTER, StonecutterScreen::new);
    }

//    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
//    private static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void changeScreenRegister(MenuType<? extends M> type, MenuScreens.ScreenConstructor<M, U> factory, CallbackInfo ci) {
//        ci.cancel();
//        MenuScreens.register(MenuType.GENERIC_9x1, LegacyContainerScreen::new);
//        MenuScreens.register(MenuType.GENERIC_9x2, LegacyContainerScreen::new);
//        MenuScreens.register(MenuType.GENERIC_9x3, LegacyContainerScreen::new);
//        MenuScreens.register(MenuType.GENERIC_9x4, LegacyContainerScreen::new);
//        MenuScreens.register(MenuType.GENERIC_9x5, LegacyContainerScreen::new);
//        MenuScreens.register(MenuType.GENERIC_9x6, LegacyContainerScreen::new);
//        MenuScreens.register(MenuType.GENERIC_3x3, DispenserScreen::new);
//        MenuScreens.register(MenuType.CRAFTER_3x3, CrafterScreen::new);
//        MenuScreens.register(MenuType.ANVIL, AnvilScreen::new);
//        MenuScreens.register(MenuType.BEACON, BeaconScreen::new);
//        MenuScreens.register(MenuType.BLAST_FURNACE, BlastFurnaceScreen::new);
//        MenuScreens.register(MenuType.BREWING_STAND, BrewingStandScreen::new);
//        MenuScreens.register(MenuType.CRAFTING, CraftingScreen::new);
//        MenuScreens.register(MenuType.ENCHANTMENT, EnchantmentScreen::new);
//        MenuScreens.register(MenuType.FURNACE, FurnaceScreen::new);
//        MenuScreens.register(MenuType.GRINDSTONE, GrindstoneScreen::new);
//        MenuScreens.register(MenuType.HOPPER, HopperScreen::new);
//        MenuScreens.register(MenuType.LECTERN, LecternScreen::new);
//        MenuScreens.register(MenuType.LOOM, LoomScreen::new);
//        MenuScreens.register(MenuType.MERCHANT, MerchantScreen::new);
//        MenuScreens.register(MenuType.SHULKER_BOX, ShulkerBoxScreen::new);
//        MenuScreens.register(MenuType.SMITHING, SmithingScreen::new);
//        MenuScreens.register(MenuType.SMOKER, SmokerScreen::new);
//        MenuScreens.register(MenuType.CARTOGRAPHY_TABLE, CartographyTableScreen::new);
//        MenuScreens.register(MenuType.STONECUTTER, StonecutterScreen::new);
//    }
//
//    @Unique
//    private static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void newRegister(MenuType<? extends M> type, MenuScreens.ScreenConstructor<M, U> factory) {
//        MenuScreens.ScreenConstructor<?, ?> screenConstructor = (MenuScreens.ScreenConstructor)SCREENS.put(type, factory);
//        if (screenConstructor != null) {
//            throw new IllegalStateException("Duplicate registration for " + String.valueOf(BuiltInRegistries.MENU.getKey(type)));
//        }
//    }
}

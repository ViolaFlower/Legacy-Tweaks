package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.legacy_container;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory.LegacyContainerScreen;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

import java.util.Map;

@Mixin(MenuScreens.class)
public class MenuScreenMixin {

    @Final
    @Shadow private static Map<MenuType<?>, MenuScreens.ScreenConstructor<?, ?>> SCREENS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void changeMenuScreens(CallbackInfo ci) {
        SCREENS.clear();
        if (Tweaks.LEGACY_UI.legacyContainerScreenTweak.useLegacyGenericContainer.isOn()) {
            MenuScreens.register(MenuType.GENERIC_9x1, LegacyContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x2, LegacyContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x3, LegacyContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x4, LegacyContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x5, LegacyContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x6, LegacyContainerScreen::new);
        } else {
            MenuScreens.register(MenuType.GENERIC_9x1, ContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x2, ContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x3, ContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x4, ContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x5, ContainerScreen::new);
            MenuScreens.register(MenuType.GENERIC_9x6, ContainerScreen::new);
        }
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
}

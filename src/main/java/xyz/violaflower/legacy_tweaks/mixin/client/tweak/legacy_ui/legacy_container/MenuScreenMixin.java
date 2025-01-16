package xyz.violaflower.legacy_tweaks.mixin.client.tweak.legacy_ui.legacy_container;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.*;
import net.minecraft.world.inventory.AbstractContainerMenu;
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


    @Shadow
    /*? if (neoforge) {*//*private*//*?} else {*/public/*?}*/ static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void register(MenuType<? extends M> type, MenuScreens.ScreenConstructor<M, U> factory) {

    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void changeMenuScreens(CallbackInfo ci) {
        SCREENS.clear();
        if (Tweaks.LEGACY_UI.legacyContainerScreenTweak.useLegacyGenericContainer.isOn()) {
            register(MenuType.GENERIC_9x1, LegacyContainerScreen::new);
            register(MenuType.GENERIC_9x2, LegacyContainerScreen::new);
            register(MenuType.GENERIC_9x3, LegacyContainerScreen::new);
            register(MenuType.GENERIC_9x4, LegacyContainerScreen::new);
            register(MenuType.GENERIC_9x5, LegacyContainerScreen::new);
            register(MenuType.GENERIC_9x6, LegacyContainerScreen::new);
        } else {
            register(MenuType.GENERIC_9x1, ContainerScreen::new);
            register(MenuType.GENERIC_9x2, ContainerScreen::new);
            register(MenuType.GENERIC_9x3, ContainerScreen::new);
            register(MenuType.GENERIC_9x4, ContainerScreen::new);
            register(MenuType.GENERIC_9x5, ContainerScreen::new);
            register(MenuType.GENERIC_9x6, ContainerScreen::new);
        }
        register(MenuType.GENERIC_3x3, DispenserScreen::new);
        register(MenuType.CRAFTER_3x3, CrafterScreen::new);
        register(MenuType.ANVIL, AnvilScreen::new);
        register(MenuType.BEACON, BeaconScreen::new);
        register(MenuType.BLAST_FURNACE, BlastFurnaceScreen::new);
        register(MenuType.BREWING_STAND, BrewingStandScreen::new);
        register(MenuType.CRAFTING, CraftingScreen::new);
        register(MenuType.ENCHANTMENT, EnchantmentScreen::new);
        register(MenuType.FURNACE, FurnaceScreen::new);
        register(MenuType.GRINDSTONE, GrindstoneScreen::new);
        register(MenuType.HOPPER, HopperScreen::new);
        register(MenuType.LECTERN, LecternScreen::new);
        register(MenuType.LOOM, LoomScreen::new);
        register(MenuType.MERCHANT, MerchantScreen::new);
        register(MenuType.SHULKER_BOX, ShulkerBoxScreen::new);
        register(MenuType.SMITHING, SmithingScreen::new);
        register(MenuType.SMOKER, SmokerScreen::new);
        register(MenuType.CARTOGRAPHY_TABLE, CartographyTableScreen::new);
        register(MenuType.STONECUTTER, StonecutterScreen::new);
    }
}

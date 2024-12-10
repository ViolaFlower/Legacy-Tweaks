package xyz.violaflower.legacy_tweaks.items;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

import java.util.function.Supplier;

public class ItemManager {
    private static final ItemManager INSTANCE = new ItemManager();
    public static ItemManager getInstance() {
        return INSTANCE;
    }

    public final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(LegacyTweaks.MOD_ID));
    public Registrar<Item> items = MANAGER.get().get(Registries.ITEM);

    /**
     *
     * @param itemID The resource location
     * @param _item The item to register
     */
    public Item registerItem(Identifier itemID, Item _item) {
        RegistrySupplier<Item> item = items.register(itemID, () -> _item);
        return item.get();
    }

    public static void init() {

    }
}

package xyz.violaflower.legacy_tweaks.helper.manager.item;

public class ItemManager {
    private static final ItemManager INSTANCE = new ItemManager();
    public static ItemManager getInstance() {
        return INSTANCE;
    }

    // todo fix this
//    public final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(LegacyTweaks.MOD_ID));
//    public Registrar<Item> items = MANAGER.get().get(Registries.ITEM);
//
//    /**
//     *
//     * @param itemID The resource location
//     * @param _item The item to register
//     */
//    public Item registerItem(ResourceLocation itemID, Item _item) {
//        RegistrySupplier<Item> item = items.register(itemID, () -> _item);
//        return item.get();
//    }

    public static void init() {

    }
}

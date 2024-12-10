package xyz.violaflower.legacy_tweaks.items;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

public class LegacyItem {
    public static Item register(Item item, String id ) {
        Identifier itemID = Identifier.of(LegacyTweaks.MOD_ID, id);
        Item registeredItem = ItemManager.getInstance().registerItem(itemID, item);
        return registeredItem;
    }
}

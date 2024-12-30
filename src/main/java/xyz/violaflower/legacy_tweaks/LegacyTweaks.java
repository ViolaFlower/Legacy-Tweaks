package xyz.violaflower.legacy_tweaks;

//? if neoforge
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.violaflower.legacy_tweaks.items.ItemManager;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.impl.MapCoords;
import xyz.violaflower.legacy_tweaks.tweaks.impl.Stub;
import xyz.violaflower.legacy_tweaks.tweaks.impl.WindowTitle;

//? if neoforge
@Mod(LegacyTweaks.MOD_ID)
public final class LegacyTweaks {
    public static final String MOD_ID = "legacy_tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //? if neoforge {
    public LegacyTweaks() {
        init();
    }
    //?}

    public static void init() {
        LOGGER.info("initialized Legacy Tweaks successfully");
        ItemManager.init();

        TweakManager tweakManager = TweakManager.getInstance();
        tweakManager.register(new WindowTitle());
        tweakManager.register(new Stub());
        tweakManager.register(new MapCoords());
    }
}

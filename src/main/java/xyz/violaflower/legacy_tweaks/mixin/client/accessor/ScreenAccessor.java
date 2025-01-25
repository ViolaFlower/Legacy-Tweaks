package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.client.gui.components.Renderable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(targets = "net/minecraft/client/gui/screens/Screen")
public interface ScreenAccessor {

    @Accessor("renderables")
    List<Renderable> legacyTweaks$getRenderables();
}

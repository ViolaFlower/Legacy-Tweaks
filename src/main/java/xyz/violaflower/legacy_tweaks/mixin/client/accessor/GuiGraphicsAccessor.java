package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net/minecraft/client/gui/GuiGraphics")
public interface GuiGraphicsAccessor {

    @Accessor
    PoseStack getPose();

    @Accessor
    boolean getManaged();


}

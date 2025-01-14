package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.MultiBufferSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "net/minecraft/client/gui/GuiGraphics")
public interface GuiGraphicsAccessor {

    @Accessor
    PoseStack getPose();

    @Invoker("flushIfManaged")
    void legacyTweaks$flushIfManaged();

    @Accessor("bufferSource")
    MultiBufferSource.BufferSource legacyTweaks$getBufferSource();
}

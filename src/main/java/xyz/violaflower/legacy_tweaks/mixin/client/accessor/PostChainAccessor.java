package xyz.violaflower.legacy_tweaks.mixin.client.accessor;

import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(PostChain.class)
public interface PostChainAccessor {
	@Accessor
	List<PostPass> getPasses();
}

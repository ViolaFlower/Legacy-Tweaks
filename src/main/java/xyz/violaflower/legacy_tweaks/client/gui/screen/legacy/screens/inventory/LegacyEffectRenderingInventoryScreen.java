package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.screens.inventory;

import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.container.menu.LegacyAbstractContainerScreen;


/// @see net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen
public abstract class LegacyEffectRenderingInventoryScreen<T extends AbstractContainerMenu> extends LegacyAbstractContainerScreen<T> {
	private static final ResourceLocation EFFECT_BACKGROUND_LARGE_SPRITE = ResourceLocation.withDefaultNamespace("container/inventory/effect_background_large");
	private static final ResourceLocation EFFECT_BACKGROUND_SMALL_SPRITE = ResourceLocation.withDefaultNamespace("container/inventory/effect_background_small");

	public LegacyEffectRenderingInventoryScreen(T menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		this.renderEffects(guiGraphics, mouseX, mouseY);
	}

	// TODO make Gui take this into consideration
	public boolean canSeeEffects() {
		int effectsXPos = this.leftPos + this.imageWidth + 2;
		int effectsSpace = this.width - effectsXPos;
		return effectsSpace >= 32;
	}

	private void renderEffects(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		int effectsXPos = this.leftPos + this.imageWidth + 2;
		int effectsSpace = this.width - effectsXPos;
		Collection<MobEffectInstance> collection = this.minecraft.player.getActiveEffects();
		if (!collection.isEmpty() && effectsSpace >= 32) {
			boolean isSmall = effectsSpace >= 120;
			int yOffset = 33;
			if (collection.size() > 5) {
				yOffset = 132 / (collection.size() - 1);
			}

			Iterable<MobEffectInstance> iterable = Ordering.natural().sortedCopy(collection);
			this.renderBackgrounds(guiGraphics, effectsXPos, yOffset, iterable, isSmall);
			this.renderIcons(guiGraphics, effectsXPos, yOffset, iterable, isSmall);
			if (isSmall) {
				this.renderLabels(guiGraphics, effectsXPos, yOffset, iterable);
			} else if (mouseX >= effectsXPos && mouseX <= effectsXPos + 33) {
				int effectYPos = this.topPos;
				MobEffectInstance mobEffectInstance = null;

				for (MobEffectInstance mobEffectInstance2 : iterable) {
					if (mouseY >= effectYPos && mouseY <= effectYPos + yOffset) {
						mobEffectInstance = mobEffectInstance2;
					}

					effectYPos += yOffset;
				}

				if (mobEffectInstance != null) {
					List<Component> list = List.of(
						this.getEffectName(mobEffectInstance), MobEffectUtil.formatDuration(mobEffectInstance, 1.0F, this.minecraft.level.tickRateManager().tickrate())
					);
					guiGraphics.renderTooltip(this.font, list, Optional.empty(), mouseX, mouseY);
				}
			}
		}
	}

	private void renderBackgrounds(GuiGraphics guiGraphics, int renderX, int yOffset, Iterable<MobEffectInstance> effects, boolean isSmall) {
		int effectY = this.topPos;

		for (MobEffectInstance mobEffectInstance : effects) {
			if (isSmall) {
				guiGraphics.blitSprite(EFFECT_BACKGROUND_LARGE_SPRITE, renderX, effectY, 120, 32);
			} else {
				guiGraphics.blitSprite(EFFECT_BACKGROUND_SMALL_SPRITE, renderX, effectY, 32, 32);
			}

			effectY += yOffset;
		}
	}

	private void renderIcons(GuiGraphics guiGraphics, int renderX, int yOffset, Iterable<MobEffectInstance> effects, boolean isSmall) {
		MobEffectTextureManager mobEffectTextureManager = this.minecraft.getMobEffectTextures();
		int effectY = this.topPos;

		for (MobEffectInstance mobEffectInstance : effects) {
			Holder<MobEffect> holder = mobEffectInstance.getEffect();
			TextureAtlasSprite textureAtlasSprite = mobEffectTextureManager.get(holder);
			guiGraphics.blit(renderX + (isSmall ? 6 : 7), effectY + 7, 0, 18, 18, textureAtlasSprite);
			effectY += yOffset;
		}
	}

	private void renderLabels(GuiGraphics guiGraphics, int renderX, int yOffset, Iterable<MobEffectInstance> effects) {
		int effectY = this.topPos;

		for (MobEffectInstance mobEffectInstance : effects) {
			Component effectName = this.getEffectName(mobEffectInstance);
			guiGraphics.drawString(this.font, effectName, renderX + 10 + 18, effectY + 6, 16777215);
			Component durationText = MobEffectUtil.formatDuration(mobEffectInstance, 1.0F, this.minecraft.level.tickRateManager().tickrate());
			guiGraphics.drawString(this.font, durationText, renderX + 10 + 18, effectY + 6 + 10, 8355711);
			effectY += yOffset;
		}
	}

	private Component getEffectName(MobEffectInstance effect) {
		MutableComponent mutableComponent = effect.getEffect().value().getDisplayName().copy();
		if (effect.getAmplifier() >= 1 && effect.getAmplifier() <= 9) {
			mutableComponent.append(CommonComponents.SPACE).append(Component.translatable("enchantment.level." + (effect.getAmplifier() + 1)));
		}

		return mutableComponent;
	}
}

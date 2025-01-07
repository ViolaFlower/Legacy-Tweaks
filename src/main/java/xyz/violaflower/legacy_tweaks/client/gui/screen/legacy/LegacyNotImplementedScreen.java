package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;
import xyz.violaflower.legacy_tweaks.util.ScreenUtil;

import java.util.Objects;

// Used if the thing hasn't been implemented yet.
public class LegacyNotImplementedScreen extends LegacyScreen {
	public static final ResourceLocation BACKGROUND_LOC = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "gui_background");
	public static final ResourceLocation INSET_BACKGROUND = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID,  "inset_background");
	private final Screen parent;
	private final FrameLayout frameLayout;
	private final FrameLayout innerFrameLayout;
	private LegacyLogoRenderer logoRenderer;

	protected LegacyNotImplementedScreen(Screen parent) {
		super(Component.empty());
		this.parent = parent;
		frameLayout = new FrameLayout(getFrameWidth(), 0);
		//frameLayout.newChildLayoutSettings().alignVerticallyTop();
		frameLayout.defaultChildLayoutSetting().align(0.5f, 0).padding(5);
		innerFrameLayout = new FrameLayout(getFrameWidth() - 10, 0);
		innerFrameLayout.defaultChildLayoutSetting().alignVerticallyTop().padding(5);
		this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, () -> new LegacyLogoRenderer(false));
	}

	@Override
	protected void init() {
		super.init();
		LinearLayout linearLayout = LinearLayout.vertical().spacing(4);
		linearLayout.addChild(Button.builder(Component.literal("Not implemented yet."), button -> {
			Minecraft.getInstance().setScreen(parent);
		}).size(getButtonWidth(), getButtonHeight()).build());


		linearLayout.addChild(innerFrameLayout);
		frameLayout.addChild(linearLayout);
		frameLayout.visitWidgets(this::addRenderableWidget);
		repositionElements();
	}

	@Override
	public int getButtonWidth() {
		if (ScreenUtil.isLargeGui()) {
			return 150;
		} else {
			return 200;
		}
	}

	@Override
	protected void repositionElements() {
		frameLayout.arrangeElements();
		FrameLayout.centerInRectangle(frameLayout, 0, 0, width, height);
	}

	@Override
	public void onClose() {
		minecraft.setScreen(parent);
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderPanorama(guiGraphics, partialTick);
//		super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		guiGraphics.blitSprite(BACKGROUND_LOC, frameLayout.getX(), frameLayout.getY(), frameLayout.getWidth(), frameLayout.getHeight());
		guiGraphics.blitSprite(INSET_BACKGROUND, innerFrameLayout.getX(), innerFrameLayout.getY(), innerFrameLayout.getWidth(), innerFrameLayout.getHeight());
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		logoRenderer.renderLogo(guiGraphics, this.width, 1);
	}
}

package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;
import xyz.violaflower.legacy_tweaks.util.ScreenUtil;

import java.util.Objects;

// playground for testing gui things
public class LegacyTestScreen extends LegacyScreen {
	public static final ResourceLocation BACKGROUND_LOC = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "gui_background");
	public static final ResourceLocation TOP_NAV_LEFT = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "top_nav_left");
	public static final ResourceLocation INSET_BACKGROUND = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID,  "inset_background");
	private final Screen parent;
	private final FrameLayout frameLayout;
	private final FrameLayout innerFrameLayout;
	private LegacyLogoRenderer logoRenderer;

	protected LegacyTestScreen(Screen parent) {
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
		linearLayout.addChild(Button.builder(Component.literal("Button 1"), button -> {}).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Component.literal("Button 2"), button -> {}).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Component.literal("Button 3"), button -> {}).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Component.literal("Button 4"), button -> {}).size(getButtonWidth(), getButtonHeight()).build());


		{
			LinearLayout layout = LinearLayout.vertical().spacing(2);
			layout.addChild(Button.builder(Component.literal("Button 5"), button -> {}).size(getButtonWidthAlt(), 30).build());
			innerFrameLayout.addChild(layout);
		}

		linearLayout.addChild(innerFrameLayout);
		frameLayout.addChild(linearLayout);
		frameLayout.visitWidgets(this::addRenderableWidget);
		repositionElements();
	}

	@Override
	protected void repositionElements() {
		frameLayout.arrangeElements();
		FrameLayout.centerInRectangle(frameLayout, 0, 0, width, height);
	}

	@Override
	public int getButtonWidth() {
		if (ScreenUtil.isLargeGui()) {
			return 150;
		} else {
			return 200;
		}
	}

	public int getButtonWidthAlt() {
		if (ScreenUtil.isLargeGui()) {
			return 140;
		} else {
			return 190;
		}
	}

	@Override
	public void onClose() {
		minecraft.setScreen(parent);
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		System.out.println(frameLayout.getWidth());
		guiGraphics.blitSprite(TOP_NAV_LEFT, frameLayout.getX() + frameLayout.getWidth() / 3, frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
		guiGraphics.blitSprite(TOP_NAV_LEFT, frameLayout.getX() + frameLayout.getWidth() / 3 * 2, frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
		guiGraphics.blitSprite(BACKGROUND_LOC, frameLayout.getX(), frameLayout.getY(), frameLayout.getWidth(), frameLayout.getHeight());
		guiGraphics.blitSprite(INSET_BACKGROUND, innerFrameLayout.getX(), innerFrameLayout.getY(), innerFrameLayout.getWidth(), innerFrameLayout.getHeight());
		guiGraphics.blitSprite(TOP_NAV_LEFT, frameLayout.getX(), frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		logoRenderer.renderLogo(guiGraphics, this.width, 1);
	}
}

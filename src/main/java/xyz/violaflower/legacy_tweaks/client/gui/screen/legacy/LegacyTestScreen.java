package xyz.violaflower.legacy_tweaks.client.gui.screen.legacy;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import xyz.violaflower.legacy_tweaks.util.client.ScreenUtil;
import xyz.violaflower.legacy_tweaks.util.common.assets.Sprites;
import xyz.violaflower.legacy_tweaks.util.common.lang.Lang;

import java.util.Objects;

// playground for testing gui things
public class LegacyTestScreen extends LegacyScreen {
	private final Screen parent;
	private FrameLayout frameLayout;
	private FrameLayout innerFrameLayout;

	protected LegacyTestScreen(Screen parent) {
		super(Component.empty());
		this.parent = parent;
	}

	@Override
	protected void init() {
		super.init();
		this.clearWidgets();
		this.clearFocus();
		frameLayout = new FrameLayout(getFrameWidth(), 0);
		//frameLayout.newChildLayoutSettings().alignVerticallyTop();
		frameLayout.defaultChildLayoutSetting().align(0.5f, 0).padding(5);
		innerFrameLayout = new FrameLayout(getFrameWidth() - 10, 0);
		innerFrameLayout.defaultChildLayoutSetting().alignVerticallyTop().padding(5);
		LinearLayout linearLayout = LinearLayout.vertical().spacing(getButtonSpacing());
		linearLayout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {}).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {}).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {}).size(getButtonWidth(), getButtonHeight()).build());
		linearLayout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {}).size(getButtonWidth(), getButtonHeight()).build());


		{
			LinearLayout layout = LinearLayout.vertical().spacing(2);
			layout.addChild(Button.builder(Lang.ScreenNotImplemented.PLACEHOLDER_BUTTON.get(), button -> {}).size(getButtonWidthAlt(), 30).build());
			innerFrameLayout.addChild(layout);
		}

		linearLayout.addChild(innerFrameLayout);
		frameLayout.addChild(linearLayout);
		frameLayout.visitWidgets(this::addRenderableWidget);
		repositionElements();
	}

	@Override
	protected void repositionElements() {
		if (this.frameLayout == null) return;
		frameLayout.arrangeElements();
		FrameLayout.centerInRectangle(frameLayout, 0, 0, width, height);
	}

	@Override
	public int getButtonHeight() {
		return 25;
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
	public int getButtonSpacing() {
		return 0;
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
//		super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
//		System.out.println(frameLayout.getWidth());
		guiGraphics.blitSprite(Sprites.TOP_NAV_LEFT, frameLayout.getX() + frameLayout.getWidth() / 3, frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
		guiGraphics.blitSprite(Sprites.TOP_NAV_LEFT, frameLayout.getX() + frameLayout.getWidth() / 3 * 2, frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
		guiGraphics.blitSprite(Sprites.BACKGROUND_LOC, frameLayout.getX(), frameLayout.getY(), frameLayout.getWidth(), frameLayout.getHeight());
		guiGraphics.blitSprite(Sprites.INSET_BACKGROUND, innerFrameLayout.getX(), innerFrameLayout.getY(), innerFrameLayout.getWidth(), innerFrameLayout.getHeight());
		guiGraphics.blitSprite(Sprites.TOP_NAV_LEFT, frameLayout.getX(), frameLayout.getY() - 15, frameLayout.getWidth() / 3, 19);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}
}

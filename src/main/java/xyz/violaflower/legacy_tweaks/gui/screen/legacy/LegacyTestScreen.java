package xyz.violaflower.legacy_tweaks.gui.screen.legacy;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

// playground for testing gui things
public class LegacyTestScreen extends Screen {
	public static final ResourceLocation BACKGROUND_LOC = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "gui_background");
	public static final ResourceLocation INSET_BACKGROUND = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID,  "inset_background");
	private final Screen parent;
	private final FrameLayout frameLayout;
	private final FrameLayout innerFrameLayout;

	protected LegacyTestScreen(Screen parent) {
		super(Component.empty());
		this.parent = parent;
		frameLayout = new FrameLayout(220, 200);
		//frameLayout.newChildLayoutSettings().alignVerticallyTop();
		frameLayout.defaultChildLayoutSetting().align(0.5f, 0).padding(5);
		innerFrameLayout = new FrameLayout(200, 50);
		innerFrameLayout.defaultChildLayoutSetting().alignVerticallyTop().padding(5);
	}

	@Override
	protected void init() {
		super.init();
		LinearLayout linearLayout = LinearLayout.vertical().spacing(4);
		linearLayout.addChild(Button.builder(Component.literal("Button 1"), button -> {}).width(200).build());
		linearLayout.addChild(Button.builder(Component.literal("Button 2"), button -> {}).width(200).build());
		linearLayout.addChild(Button.builder(Component.literal("Button 3"), button -> {}).width(200).build());
		linearLayout.addChild(Button.builder(Component.literal("Button 4"), button -> {}).width(200).build());


		{
			LinearLayout layout = LinearLayout.vertical().spacing(2);
			layout.addChild(Button.builder(Component.literal("Button 5"), button -> {}).size(180, 30).build());
			innerFrameLayout.addChild(layout);
		}

		linearLayout.addChild(innerFrameLayout);
		frameLayout.addChild(linearLayout);
		frameLayout.visitWidgets(this::addRenderableWidget);
		repositionElements();
	}

	@Override
	protected void repositionElements() {
		FrameLayout.centerInRectangle(frameLayout, 0, 0, width, height);
		frameLayout.arrangeElements();
	}

	@Override
	public void onClose() {
		minecraft.setScreen(parent);
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		System.out.println(frameLayout.getWidth());
		guiGraphics.blitSprite(BACKGROUND_LOC, frameLayout.getX(), frameLayout.getY(), frameLayout.getWidth(), frameLayout.getHeight());
		guiGraphics.blitSprite(INSET_BACKGROUND, innerFrameLayout.getX(), innerFrameLayout.getY(), innerFrameLayout.getWidth(), innerFrameLayout.getHeight());
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
	}
}

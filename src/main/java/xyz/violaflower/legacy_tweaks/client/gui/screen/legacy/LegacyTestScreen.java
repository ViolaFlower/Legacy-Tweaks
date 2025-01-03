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

import java.util.Objects;

// playground for testing gui things
public class LegacyTestScreen extends Screen {
	public static final ResourceLocation BACKGROUND_LOC = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "gui_background");
	public static final ResourceLocation INSET_BACKGROUND = ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID,  "inset_background");
	private final Screen parent;
	private final FrameLayout frameLayout;
	private final FrameLayout innerFrameLayout;
	private LogoRenderer logoRenderer;

	protected LegacyTestScreen(Screen parent) {
		super(Component.empty());
		this.parent = parent;
		frameLayout = new FrameLayout(210, 0);
		//frameLayout.newChildLayoutSettings().alignVerticallyTop();
		frameLayout.defaultChildLayoutSetting().align(0.5f, 0).padding(5);
		innerFrameLayout = new FrameLayout(200, 0);
		innerFrameLayout.defaultChildLayoutSetting().alignVerticallyTop().padding(5);
		this.logoRenderer = Objects.requireNonNullElseGet(logoRenderer, () -> new LogoRenderer(false));
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
			layout.addChild(Button.builder(Component.literal("Button 5"), button -> {}).size(190, 30).build());
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
		logoRenderer.renderLogo(guiGraphics, this.width, 1);
	}
}
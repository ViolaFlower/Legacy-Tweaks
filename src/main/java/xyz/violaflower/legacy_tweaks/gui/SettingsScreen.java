package xyz.violaflower.legacy_tweaks.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

import java.util.ArrayList;
import java.util.List;

public class SettingsScreen extends Screen {

	private final Screen parent;
	private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
	private final Tweak tweak;
	private SettingList settingList;

	public SettingsScreen(Screen parent, Tweak tweak) {
		super(Component.empty());
		this.parent = parent;
		this.tweak = tweak;
	}

	@Override
	protected void init() {
		this.layout.addTitleHeader(getTitle(), font);
		this.settingList = this.layout.addToContents(new SettingList(tweak));
		LinearLayout linearLayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
		linearLayout.addChild(Button.builder(CommonComponents.GUI_DONE, button -> this.minecraft.setScreen(parent)).build());
		this.layout.visitWidgets(this::addRenderableWidget);
		this.repositionElements();
	}

	@Override
	protected void repositionElements() {
		this.layout.arrangeElements();
		if (this.settingList != null) {
			this.settingList.updateSize(this.width, this.layout);
		}
	}

	@Override
	public void onClose() {
		this.minecraft.setScreen(parent);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {
		super.render(guiGraphics, i, j, f);
		//guiGraphics.drawCenteredString(font, "Tweak Settings", width / 2, 5, 0xffffffff);
	}

	class SettingList extends ContainerObjectSelectionList<SettingList.SettingEntry> {
		private static final int ITEM_HEIGHT = 40;
		private Tweak tweak;
		public SettingList(Tweak option) {
			super(
					SettingsScreen.this.minecraft,
					SettingsScreen.this.width,
					SettingsScreen.this.layout.getContentHeight(),
					SettingsScreen.this.layout.getHeaderHeight(),
					ITEM_HEIGHT
			);
			this.tweak = option;
			tweak.getOptions().forEach(option1 -> this.addEntry(new SettingEntry(option1)));
		}

		class SettingEntry extends ContainerObjectSelectionList.Entry<SettingList.SettingEntry> {
			private @Nullable Tweak.Option<?> option;
			private final ArrayList<? extends GuiEventListener> children = new ArrayList<>();
			public SettingEntry(@Nullable Tweak.Option<?> option) {
				this.option = option;
				if (option instanceof Tweak.SliderOption<?> sliderOption) {

				}
			}
			@Override
			public List<? extends NarratableEntry> narratables() {
				return children;
			}

			@Override
			public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
				guiGraphics.drawString(font, option.getName(), left, top + 5, 0xffffffff);
				//guiGraphics.fill(left, top, left+width, top+height, 0xffffffff);
			}

			@Override
			public List<? extends GuiEventListener> children() {
				return children;
			}
		}
	}
}

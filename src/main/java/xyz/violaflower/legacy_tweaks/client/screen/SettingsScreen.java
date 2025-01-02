package xyz.violaflower.legacy_tweaks.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.tweaks.Tweak;

import java.text.DecimalFormat;
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

	public Screen getParent() {
		return parent;
	}

	@Override
	protected void init() {
		this.layout.addTitleHeader(getTitle(), font);
		this.settingList = this.layout.addToContents(new SettingList(tweak));
		LinearLayout linearLayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
		linearLayout.addChild(Button.builder(CommonComponents.GUI_DONE, button -> this.minecraft.setScreen(parent)).build());
		linearLayout.addChild(Button.builder(Component.literal("Sub tyaska"), button -> this.minecraft.setScreen(new LTScreen(this, tweak))).build());
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
		private static final int ITEM_HEIGHT = 25;
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
			private final ArrayList<GuiEventListener> children = new ArrayList<>();
			private AbstractSliderButton sliderButton;
			private Button toggleButton;
			public SettingEntry(@Nullable Tweak.Option<?> option) {
				this.option = option;
				if (option instanceof Tweak.SliderOption<?> sliderOption) {
					sliderButton = new AbstractSliderButton(100, 0, 100, 20, Component.literal("Value " + new DecimalFormat("#0.00").format(sliderOption.get())), sliderOption.normalize()) {
						@Override
						protected void updateMessage() {
							setMessage(Component.literal("Value " + new DecimalFormat("#0.00").format(sliderOption.get())));
						}

						@Override
						protected void applyValue() {
							((Tweak.SliderOption) sliderOption).set(sliderOption.unNormalize(value));
						}
					};
					children.add(sliderButton);
				} else if (option instanceof Tweak.BooleanOption booleanOption) {
					Component component = booleanOption.get() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF;
					toggleButton = Button.builder(component, b -> {
						booleanOption.set(!booleanOption.get());
						b.setMessage(booleanOption.get() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF);
					}).bounds(100, 0, 100, 20).build();
					children.add(toggleButton);
				}
			}
			@Override
			public List<? extends NarratableEntry> narratables() {
				return (List<? extends NarratableEntry>) (Object) children;
			}

			@Override
			public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
				guiGraphics.drawString(font, option.getName(), left, top + 5, 0xffffffff);
				if (sliderButton != null) {
					sliderButton.setY(top);
					sliderButton.setX(left + width / 2 + 10);
					sliderButton.setWidth(width / 2 - 10);
				}
				if (toggleButton != null) {
					toggleButton.setY(top);
					toggleButton.setX(left + width / 2 + 10);
					toggleButton.setWidth(width / 2 - 10);
				}
				for (GuiEventListener child : this.children) {
					if (child instanceof Renderable renderable) {
						renderable.render(guiGraphics, mouseX, mouseY, partialTick);
					}
				}
				//guiGraphics.fill(left, top, left+width, top+height, 0xffffffff);
			}

			@Override
			public List<? extends GuiEventListener> children() {
				return children;
			}
		}
	}
}

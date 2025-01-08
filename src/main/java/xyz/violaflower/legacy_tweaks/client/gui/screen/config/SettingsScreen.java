package xyz.violaflower.legacy_tweaks.client.gui.screen.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.AbstractSliderButtonAccessor;
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
			private Button resetButton;
			public <T> SettingEntry(@Nullable Tweak.Option<?> option) {
				this.option = option;
				Button[] delayedResetButton = new Button[1];
				if (option instanceof Tweak.SliderOption<?> sliderOption0) {
					Tweak.SliderOption<T> sliderOption = (Tweak.SliderOption<T>) sliderOption0;
					sliderButton = new AbstractSliderButton(100, 0, 100, 20, Component.literal("Value ").append(sliderOption.fancyFormat(sliderOption.get())), sliderOption.normalize()) {
						@Override
						protected void updateMessage() {
							setMessage(Component.literal("Value ").append(sliderOption.fancyFormat(sliderOption.get())));
						}

						@Override
						protected void applyValue() {
							sliderOption.set(sliderOption.unNormalize(value));
							delayedResetButton[0].active = true;
						}
					};
					children.add(sliderButton);
				} else if (option instanceof Tweak.BooleanOption booleanOption) {
					Component component = booleanOption.get() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF;
					toggleButton = Button.builder(component, b -> {
						booleanOption.set(!booleanOption.get());
						b.setMessage(booleanOption.get() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF);
						delayedResetButton[0].active = true;
					}).bounds(100, 0, 100, 20).build();
					children.add(toggleButton);
				}
				delayedResetButton[0] = resetButton = Button.builder(Component.translatable("lt.main.reset"), button -> {
					option.getTweakState().setLocalState(null);
					if (sliderButton != null) {
						((AbstractSliderButtonAccessor) sliderButton).callUpdateMessage();
						((AbstractSliderButtonAccessor) sliderButton).legacyTweaks$setValue(((Tweak.SliderOption)option).normalize());
					}
					if (toggleButton != null) {
						toggleButton.setMessage(((Tweak.BooleanOption) option).get() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF);
					}
					button.active = false;
				}).size(20, 20).build();
				resetButton.active = option.getTweakState().getLocalState() != null;
				children.add(resetButton);
				if (option.getTweakState().isValueLocked()) {
					resetButton.active = false;
					resetButton.setTooltip(Tooltip.create(Component.literal("Option locked by server.")));
					if (sliderButton != null) {
						sliderButton.active = false;
						sliderButton.setTooltip(Tooltip.create(Component.literal("Option locked by server.")));
					}
					if (toggleButton != null) {
						toggleButton.active = false;
						toggleButton.setTooltip(Tooltip.create(Component.literal("Option locked by server.")));
					}
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
					sliderButton.setX(left + width / 2 - 12);
					sliderButton.setWidth(width / 2 - 10);
				}
				if (toggleButton != null) {
					toggleButton.setY(top);
					toggleButton.setX(left + width / 2 - 12);
					toggleButton.setWidth(width / 2 - 10);
				}
				if (resetButton != null) {
					resetButton.setY(top);
					resetButton.setX(left + width - 20);
					resetButton.setWidth(20);
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

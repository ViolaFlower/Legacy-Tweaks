package xyz.violaflower.legacy_tweaks.client.gui.screen;

import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.layouts.AbstractLayout;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.Layout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.function.TriConsumer;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.client.LegacyTweaksResourceManager;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.*;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.impl.LegacyUI;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DataScreen extends LegacyScreen {
	private final JsonObject data;
	private final Screen parent;
	private final ResourceLocation location;

	// use this as a dummy when a method isn't meant to return anything
	private Reference _void() {
		return new Reference();
	}

	private Reference _return() {
		return new Reference();
	}
	public DataScreen(Screen parent, ResourceLocation location) {
		super(Component.empty());
		this.parent = parent;
		this.location = location;
		GLOBALS.put("parent", new Reference(this.parent));
		GLOBALS.put("null", new Reference(null));
		this.data = LegacyTweaksResourceManager.dataGuis.get(location);
		setup();
		ACTIONS.get("<init>").execute(new Reference(this), _void());
	}

	private final Map<String, Reference> GLOBALS = new HashMap<>();
	public final Map<String, Action> ACTIONS = new HashMap<>();


	@Override
	public void onClose() {
		System.err.println("Closing screen");
		ACTIONS.get("onClose").execute(new Reference(this), _void());
	}

	@Override
	public int getButtonSpacing() {
		Reference reference = _return();
		ACTIONS.get("getButtonSpacing").execute(new Reference(this), _return());
		return reference.getAsInt();
	}

	@Override
	public int getButtonHeightPos() {
		Reference reference = _return();
		ACTIONS.get("getButtonHeightPos").execute(new Reference(this), _return());
		return reference.getAsInt();
	}

	@Override
	protected void repositionElements() {
		ACTIONS.get("repositionElements").execute(new Reference(this), _void());
	}

	public void addActions() {
		ACTIONS.put("<init>", noArgsMethod().of((_this) -> {}));
		ACTIONS.put("isPauseScreen", noArgsMethod().of((_this, _return) -> ACTIONS.get("super").execute(_this, _return, new Reference("isPauseScreen"))));
		ACTIONS.put("shouldCloseOnEsc", noArgsMethod().of((_this, _return) -> ACTIONS.get("super").execute(_this, _return, new Reference("shouldCloseOnEsc"))));
		ACTIONS.put("width", noArgsMethod().of((_this, _return) -> {
			_return.set(_this.<DataScreen>get().width);
		}));
		ACTIONS.put("getButtonSpacing", noArgsMethod().of((_this, _return) -> {
			ACTIONS.get("super").execute(_this, _return, new Reference("getButtonSpacing"));
		}));
		ACTIONS.put("font", noArgsMethod().of((_this, _return) -> {
			_return.set(_this.<DataScreen>get().font);
		}));
		ACTIONS.put("onClose", noArgsMethod().of(_this -> {
			System.out.println("what");
			ACTIONS.get("setScreen").execute(_this, _void(), GLOBALS.get("null"));
			//ACTIONS.get("setScreen").execute(_this, _void(), GLOBALS.get("parent"));
		}));
		ACTIONS.put("init", noArgsMethod().of(_this -> {
			ACTIONS.get("super").execute(_this, _void(), new Reference("init"));
		}));
		ACTIONS.put("repositionElements", noArgsMethod().of(_this -> {
			ACTIONS.get("super").execute(_this, _void(), new Reference("repositionElements"));
		}));
		ACTIONS.put("setScreen", oneArgMethod().of((_this, screen) -> {
			Minecraft.getInstance().setScreen(screen.get());
		}));
		ACTIONS.put("render", (_this, _return, args) -> {
			ACTIONS.get("super").execute(_this, _void(), new Reference("render"), args[0], args[1], args[2], args[3]);
		});
		ACTIONS.put("renderBackground", (_this, _return, args) -> {
			ACTIONS.get("super").execute(_this, _void(), new Reference("renderBackground"), args[0], args[1], args[2], args[3]);
		});
		ACTIONS.put("SplashRenderer.render", (_this, _return, args) -> {
			//System.out.println(Arrays.toString(args));
			_this.<SplashRenderer>get().render(args[0].get(), args[1].getAsInt(), args[2].get(), args[3].getAsInt());
		});
		ACTIONS.put("LegacyLogoRenderer.renderLogo", (_this, _return, args) -> {
			//System.out.println(Arrays.toString(args));
			if (args.length == 3) {
				_this.<LegacyLogoRenderer>get().renderLogo(args[0].get(), args[1].getAsInt(), args[2].getAsFloat());
			} else if (args.length == 4) {
				_this.<LegacyLogoRenderer>get().renderLogo(args[0].get(), args[1].getAsInt(), args[2].getAsFloat(), args[3].getAsInt());
			} else fail();
		});

		ACTIONS.put("LinearLayout.spacing", oneArgMethod().of((_this, _return, _arg) -> {
			_return.set(_this.<LinearLayout>get().spacing(_arg.getAsInt()));
		}));

		ACTIONS.put("getButtonHeightPos", noArgsMethod().of((_this, _return) -> {
			ACTIONS.get("super").execute(_this, _return, new Reference("getButtonHeightPos"));
		}));

		// TODO, shouldn't this be its own type like putGlobal?
		ACTIONS.put("super", (_this, _return, args) -> {
			if (args.length >= 1) {
				Reference arg = args[0];
				String method = arg.get();
				switch (method) {
					case "init" -> {
						if (args.length != 1) fail();
						super.init();
					} case "onClose" -> {
						if (args.length != 1) fail();
						super.onClose();
					} case "repositionElements" -> {
						if (args.length != 1) fail();
						super.repositionElements();
					} case "getButtonSpacing" -> {
						if (args.length != 1) fail();
						_return.set(super.getButtonSpacing());
					} case "getButtonHeightPos" -> {
						if (args.length != 1) fail();
						_return.set(super.getButtonHeightPos());
					} case "isPauseScreen" -> {
						if (args.length != 1) fail();
						_return.set(super.isPauseScreen());
					} case "shouldCloseOnEsc" -> {
						if (args.length != 1) fail();
						_return.set(super.shouldCloseOnEsc());
					} case "render" -> {
						// super.render(guiGraphics, mouseX, mouseY, partialTick);
						if (args.length != 5) fail();
						super.render(args[1].get(), args[2].getAsInt(), args[3].getAsInt(), args[4].getAsFloat());
					} case "renderBackground" -> {
						if (args.length != 5) fail();
						super.renderBackground(args[1].get(), args[2].getAsInt(), args[3].getAsInt(), args[4].getAsFloat());
					}
				}
				return;
			}
			fail();
		});
		ACTIONS.put("clearWidgets", noArgsMethod().of(this_ -> this_.<DataScreen>get().clearWidgets()));
		ACTIONS.put("clearFocus", noArgsMethod().of(this_ -> this_.<DataScreen>get().clearFocus()));

		ACTIONS.put("newFrameLayout", noArgsMethod().of((_this, _return) -> _return.set(new FrameLayout())));
		ACTIONS.put("newLinearLayout", oneArgMethod().of((_this, _return, arg) -> _return.set("vertical".equals(arg.get()) ? LinearLayout.vertical() : "horizontal".equals(arg.get()) ? LinearLayout.horizontal() : fail())));
		ACTIONS.put("newSplashRenderer", noArgsMethod().of((_this, _return) -> _return.set(Minecraft.getInstance().getSplashManager().getSplash())));
		ACTIONS.put("newSplashManagerWithCustomText", oneArgMethod().of((_this, _return, arg) -> _return.set(new SplashRenderer(arg.get()))));
		ACTIONS.put("getLegacyLogoRenderer", noArgsMethod().of((_this, _return) -> _return.set(LegacyLogoRenderer.getLegacyLogoRenderer())));

		ACTIONS.put("print", oneArgMethod().of((_this, arg) -> System.out.println(arg)));
		ACTIONS.put("renderPanorama", (_this, _return, args) -> {
			this.renderPanorama(args[0].get(), args[1].getAsFloat());
		});

		ACTIONS.put("Component.translatable", oneArgMethod().of((_this, _return, arg) -> {
			_return.set(Component.translatable(arg.get()));
		}));
		ACTIONS.put("newButtonBuilder", (_this, _return, _args) -> {
			Consumer<Button> consumer = _args.length < 2 ? b -> {} : b -> _args[1].<Action>get().execute(new Reference(b), _void());
			_return.set(Button.builder(_args[0].get(), consumer::accept));
		});
		ACTIONS.put("Button.Builder.build", (_this, _return, _args) -> {
			_return.set(_this.<Button.Builder>get().build());
		});
		ACTIONS.put("LinearLayout.addChild", (_this, _return, args) -> {
			// i guess we return void?
			_this.<LinearLayout>get().addChild(args[0].get());
		});
		ACTIONS.put("FrameLayout.addChild", (_this, _return, args) -> {
			// i guess we return void?
			_this.<FrameLayout>get().addChild(args[0].get());
		});
		ACTIONS.put("FrameLayout.setMinWidth", (_this, _return, args) -> {
			_this.<FrameLayout>get().setMinWidth(args[0].get());
			_return.setReference(_this);
		});
		ACTIONS.put("AbstractLayout.setY", (_this, _return, args) -> {
			_this.<AbstractLayout>get().setY(args[0].get());
		});
		ACTIONS.put("AbstractLayout.arrangeElements", (_this, _return, args) -> {
			_this.<AbstractLayout>get().arrangeElements();
		});
		ACTIONS.put("buttonAutoSize", (_this, _return, args) -> {
			_this.<Button.Builder>get().size(this.getButtonWidth(), this.getButtonHeight());
			_return.setReference(_this);
		});
		ACTIONS.put("addLayoutWidgets", (_this, _return, args) -> {
			args[0].<Layout>get().visitWidgets(_this.<DataScreen>get()::addRenderableWidget);
		});
		Map<String, Runnable> runnableActions = Map.of(
				"quitGame", () -> Minecraft.getInstance().stop(),
				"openMultiplayerScreen", () -> setScreen(JoinMultiplayerScreen::new),
				"openSelectWorldScreen", () -> setScreen(SelectWorldScreen::new),
				"openNotImplementedScreen", () -> setScreen(LegacyNotImplementedScreen::new),
				"openHelpOptionsScreen", () -> setScreen(LegacyHelpOptionsScreen::new),
				"openTestScreen", () -> setScreen(LegacyTestScreen::new),
				"switchToNewMinecraft", () -> {
					Tweaks.LEGACY_UI.legacyTitleScreen.legacyTitleScreen.set(false);
					Minecraft.getInstance().reloadResourcePacks();
					new Thread(() -> {
						long l = System.currentTimeMillis() + 2000;
						while (System.currentTimeMillis() < l) ;
						Minecraft.getInstance().tell(() -> Minecraft.getInstance().setScreen(new TitleScreen()));
					}).start();
				}
		);
		runnableActions.forEach((k, v) -> ACTIONS.put(k, noArgsMethod().of(r -> v.run())));
		LegacyUI.LegacyTitleScreenTweak legacyTitleScreen = Tweaks.LEGACY_UI.legacyTitleScreen;
		LegacyUI.LegacyHelpOptionsScreenTweak legacyHelpOptionsScreen = Tweaks.LEGACY_UI.legacyHelpOptionsScreen;
		Map<String, Supplier<Boolean>> customizationStuff = Map.of(
				"showMinigamesButton", legacyTitleScreen.showMinigamesButton::isOn,
				"showLeaderboardsButton", legacyTitleScreen.showLeaderboardsButton::isOn,
				"useLegacyHelpOptionsScreen", legacyHelpOptionsScreen.useLegacyHelpOptionsScreen::isOn,
				"showMinecraftStoreButton", legacyTitleScreen.showMinecraftStoreButton::isOn,
				"showNewMinecraftButton", legacyTitleScreen.showNewMinecraftButton::isOn,
				"showQuitButton", legacyTitleScreen.showQuitButton::isOn
		);
		customizationStuff.forEach((k, v) -> ACTIONS.put("_internal.customization." + k, noArgsMethod().of((r, a) -> a.set(v.get()))));
	}

	private NoArgsActionBuilder noArgsMethod() {
		return new NoArgsActionBuilder();
	}

	private OneArgActionBuilder oneArgMethod() {
		return new OneArgActionBuilder();
	}

	private class NoArgsActionBuilder {
		private Action of(Consumer<Reference> consumer) {
			return of((_this, _return) -> consumer.accept(_this));
		}

		private Action of(BiConsumer<Reference, Reference> consumer) {
			return (_this, _return, args) -> {
				if (args.length != 0) fail();
				consumer.accept(_this, _return);
			};
		}
	}

	private class OneArgActionBuilder {
		private Action of(BiConsumer<Reference, Reference> consumer) {
			return of((_this, _return, arg) -> consumer.accept(_this, arg));
		}

		private Action of(TriConsumer<Reference, Reference, Reference> consumer) {
			return (_this, _return, args) -> {
				if (args.length != 1) fail();
				consumer.accept(_this, _return, args[0]);
			};
		}
	}

	public class Reference {
		public <T> Reference(T value) {
			set(value);
		}

		public Reference() {

		}

		private Object t;
		<T> T get() {
			return (T) t;
		}

		int getAsInt() {
			return getAsNumber().intValue();
		}

		float getAsFloat() {
			return getAsNumber().floatValue();
		}

		boolean getAsBoolean() {
			if (this.get() instanceof Boolean _boolean) return _boolean;
			if (this.get() instanceof Number number) return number.doubleValue() > 0;
			if (this.get() instanceof String string) return Boolean.parseBoolean(string);
			return Boolean.parseBoolean(Objects.toString(this.get()));
		}

		private Number getAsNumber() {
			if (this.get() instanceof Number number) return number;
			else if (this.get() instanceof String string) {
				try {
					return NumberFormat.getInstance(Locale.ROOT).parse(string);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
			return this.get();
		}

		void setReference(Reference reference) {
			if (reference == null) this.t = null;
			else this.t = reference.get();
		}

		<T> void setReferenceBypass(T t) {
			if (!(t instanceof Reference)) throw new RuntimeException("NOT A REFERENCE");
			this.t = t;
		}

		<T> void set(T t) {
			if (t instanceof Reference) throw new RuntimeException("ILSFJSLJKL");
			this.t = t;
		}

		@Override
		public String toString() {
			return Objects.toString(t);
		}
	}
	public interface Action {
		void execute(Reference this_, Reference return_, Reference... args);
	}

	private static <T> T fail() {
		throw new RuntimeException("Failed to interpret action!");
	}
	private static <T> T fail(JsonElement e) {
		throw new RuntimeException("Failed to interpret action! " + e);
	}

	public static Screen makeDataDrivenScreen(Screen parent, ResourceLocation location) {
		return new DataScreen(parent, location);
	}

	private void setup() {
		addActions();
		for (String key : data.getAsJsonObject("actions").keySet()) {
			JsonArray array = data.getAsJsonObject("actions").getAsJsonArray(key);
			ACTIONS.put(key, fromJsonArray(array));
		}
		System.out.println("Setup: actions:" + ACTIONS);
	}

	private Reference fromJsonElement(Reference _this, JsonElement element) {
		return fromJsonElement(_this, element, _void(), null);
	}
	// perfect
	private Reference fromJsonElement(Reference _this, JsonElement element, Reference _return, @Nullable Reference[] optArgs) {
		//@Nullable Reference[] optArgs;
		//if (_optArgs != null) optArgs = Arrays.stream(_optArgs).map(a -> new Reference(a.get())).toArray(Reference[]::new);
		//else optArgs = null;
		Reference r = _return();
		c: {
			if (element instanceof JsonPrimitive primitive && primitive.isString() && element.getAsString() instanceof String string) {
				if (string.startsWith("?")) {
					//System.out.println("executing" + string.substring(1));
					ACTIONS.get(string.substring(1)).execute(_this, r);
					break c; // returns the result
				} else if (string.startsWith(":")) {
					r.setReference(GLOBALS.get(string.substring(1)));
					break c; // returns the result
				} else if (string.startsWith("^") && optArgs != null) {
					//System.out.println(Arrays.toString(optArgs));
					r.setReference(optArgs[Integer.parseInt(string.substring(1))]);
					break c;
				} else if (string.startsWith("@")) {
					r.set(ACTIONS.get(string.substring(1)));
					break c;
				} else {
					r.set(string);
					break c; // returns the steing
					//throw new RuntimeException();
				}
			} else if (element instanceof JsonPrimitive primitive && primitive.isNumber()) {
				r.set(element.getAsNumber());
				break c;
			} else if (element instanceof JsonNull) {
				r.set(null);
				break c;
			} else if (element instanceof JsonPrimitive primitive && primitive.isBoolean() && ((Boolean)primitive.getAsBoolean()) instanceof Boolean _boolean) {
				r.set(_boolean);
				break c;
			} else if (element instanceof JsonObject object) {
				String type = object.get("type").getAsString();
				if (type.equals("action")) {
					Reference[] args = new Reference[0];
					if (object.has("args")) {
						args = object.getAsJsonArray("args").asList().stream().map(a -> fromJsonElement(_this, a, _return, optArgs)).toList().toArray(Reference[]::new);
					}
					ACTIONS.get(object.get("action").getAsString()).execute(_this, r, args);
					break c; // returns the result
				} else if (type.equals("actionTo")) {
					Reference instance = fromJsonElement(_this, object.get("instance"), _return, optArgs);
					Reference[] args = new Reference[0];
					if (object.has("args")) {
						args = object.getAsJsonArray("args").asList().stream().map(a -> fromJsonElement(_this, a, _return, optArgs)).toList().toArray(Reference[]::new);
					}
					ACTIONS.get(object.get("action").getAsString()).execute(instance, r, args);
					break c; // returns the result
				} else if (type.equals("putGlobal")) {
					String name = object.get("name").getAsString();
					JsonElement jsonElement = object.get("value");
					GLOBALS.put(name, fromJsonElement(_this, jsonElement, _return, optArgs));
					break c; // returns null
				} else if (type.equals("getGlobal")) {
					String name = object.get("name").getAsString();
					r.setReference(GLOBALS.get(name));
					break c; // returns the global
				} else if (type.equals("ifNull")) {
					JsonElement value = object.get("value");
					if (fromJsonElement(_this, value, _return, optArgs).get() == null) {
						JsonArray actions = object.getAsJsonArray("actions");
						for (JsonElement action : actions) {
							fromJsonElement(_this, action, _return, optArgs);
						}
					}
					break c;
				} else if (type.equals("ifNotNull")) {
					JsonElement value = object.get("value");
					if (fromJsonElement(_this, value, _return, optArgs).get() != null) {
						JsonArray actions = object.getAsJsonArray("actions");
						for (JsonElement action : actions) {
							fromJsonElement(_this, action, _return, optArgs);
						}
					}
					break c;
				} else if (type.equals("ifTrue")) {
					JsonElement value = object.get("value");
					//noinspection PointlessBooleanExpression as it is easier to read this way.
					if (fromJsonElement(_this, value, _return, optArgs).getAsBoolean() == true) {
						JsonArray actions = object.getAsJsonArray("actions");
						for (JsonElement action : actions) {
							fromJsonElement(_this, action, _return, optArgs);
						}
					}
					break c;
				} else if (type.equals("return")) {
					JsonElement value = object.get("value");
					_return.setReference(fromJsonElement(_this, value, _return, optArgs));
					break c;
				}
			}
			fail(element);
		}
		return r;
	}

	private Action fromJsonArray(JsonArray array) {
		return (_this, _return, args) -> {
			for (JsonElement jsonElement : array) {
				fromJsonElement(_this, jsonElement, _return, args);
			}
		};
	}

	@Override
	protected void init() {
		ACTIONS.get("init").execute(new Reference(this), _void());
		//LinearLayout linearLayout = GLOBALS.get("linearLayout").get();

	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		ACTIONS.get("render").execute(new Reference(this), _void(), new Reference(guiGraphics), new Reference(mouseX), new Reference(mouseY), new Reference(partialTick));
//		SplashRenderer renderer = null;
//		renderer.render(guiGraphics, width, font, 0xffffff00);
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		ACTIONS.get("renderBackground").execute(new Reference(this), _void(), new Reference(guiGraphics), new Reference(mouseX), new Reference(mouseY), new Reference(partialTick));
	}

	@Override
	public boolean isPauseScreen() {
		Reference reference = _return();
		ACTIONS.get("isPauseScreen").execute(new Reference(this), reference);
		return reference.getAsBoolean();
	}

	@Override
	public boolean shouldCloseOnEsc() {
		Reference reference = _return();
		ACTIONS.get("shouldCloseOnEsc").execute(new Reference(this), reference);
		return reference.getAsBoolean();
	}

	public ResourceLocation getId() {
		return location;
	}
}

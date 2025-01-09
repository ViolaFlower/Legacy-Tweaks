package xyz.violaflower.legacy_tweaks.client.gui.screen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.function.TriConsumer;
import org.jetbrains.annotations.Nullable;
import xyz.violaflower.legacy_tweaks.client.LegacyTweaksResourceManager;
import xyz.violaflower.legacy_tweaks.client.gui.element.LegacyLogoRenderer;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyScreen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class DataScreen extends LegacyScreen {
	private final JsonObject data;
	private final Screen parent;
	// use this as a dummy when a method isn't meant to return anything
	private Reference _void() {
		return new Reference();
	}

	private Reference _return() {
		return new Reference();
	}
	public DataScreen(Screen parent, JsonObject data) {
		super(Component.empty());
		this.parent = parent;
		GLOBALS.put("parent", new Reference(this.parent));
		GLOBALS.put("null", new Reference(null));
		this.data = data;
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

	public void addActions() {
		ACTIONS.put("<init>", noArgsMethod().of((_this) -> {}));
		ACTIONS.put("width", noArgsMethod().of((_this, _return) -> {
			_return.set(_this.<DataScreen>get().width);
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
		ACTIONS.put("setScreen", oneArgMethod().of((_this, screen) -> {
			Minecraft.getInstance().setScreen(screen.get());
		}));
		ACTIONS.put("render", (_this, _return, args) -> {
			if (_this.get() instanceof DataScreen) {
				ACTIONS.get("super").execute(_this, _void(), args[0], args[1], args[2], args[3]);
			}
		});
		ACTIONS.put("SplashRenderer.render", (_this, _return, args) -> {
			System.out.println(Arrays.toString(args));
			_this.<SplashRenderer>get().render(args[0].get(), args[1].getAsInt(), args[2].get(), args[3].getAsInt());
		});
		ACTIONS.put("LegacyLogoRenderer.renderLogo", (_this, _return, args) -> {
			System.out.println(Arrays.toString(args));
			if (args.length == 3) {
				_this.<LegacyLogoRenderer>get().renderLogo(args[0].get(), args[1].getAsInt(), args[2].getAsFloat());
			} else if (args.length == 4) {
				_this.<LegacyLogoRenderer>get().renderLogo(args[0].get(), args[1].getAsInt(), args[2].getAsFloat(), args[3].getAsInt());
			} else fail();
		});
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
					} case "render" -> {
						// super.render(guiGraphics, mouseX, mouseY, partialTick);
						if (args.length != 5) fail();
						super.render(args[1].get(), args[2].getAsInt(), args[3].getAsInt(), args[4].getAsFloat());
					}
				}
				return;
			}
			fail();
		});
		ACTIONS.put("clearWidgets", noArgsMethod().of(this_ -> this_.<DataScreen>get().clearWidgets()));
		ACTIONS.put("clearFocus", noArgsMethod().of(this_ -> this_.<DataScreen>get().clearFocus()));

		ACTIONS.put("newFrameLayout", noArgsMethod().of((_this, _return) -> _return.set(new FrameLayout())));
		ACTIONS.put("newSplashRenderer", noArgsMethod().of((_this, _return) -> _return.set(Minecraft.getInstance().getSplashManager().getSplash())));
		ACTIONS.put("getLegacyLogoRenderer", noArgsMethod().of((_this, _return) -> _return.set(LegacyLogoRenderer.getLegacyLogoRenderer())));

		ACTIONS.put("print", oneArgMethod().of((_this, arg) -> System.out.println(arg)));
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

		private Number getAsNumber() {
			return this.get();
		}

		void setReference(Reference reference) {
			if (reference == null) this.t = null;
			else this.t = reference.get();
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

	private static void fail() {
		throw new RuntimeException("Failed to interpret action!");
	}

	public static Screen makeDataDrivenScreen(Screen parent, ResourceLocation location) {
		return makeDataDrivenScreen(parent, LegacyTweaksResourceManager.dataGuis.get(location));
	}

	private static Screen makeDataDrivenScreen(Screen parent, JsonObject object) {
		return new DataScreen(parent, object);
	}

	public class ScreenData {

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
		return fromJsonElement(_this, element, null);
	}
	// perfect
	private Reference fromJsonElement(Reference _this, JsonElement element, @Nullable Reference[] optArgs) {
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
					System.out.println(Arrays.toString(optArgs));
					r.setReference(optArgs[Integer.parseInt(string.substring(1))]);
					break c;
				} else {
					r.set(string);
					break c; // returns the steing
					//throw new RuntimeException();
				}
			} else if (element instanceof JsonPrimitive primitive && primitive.isNumber()) {
				r.set(element.getAsNumber());
				break c;
			} else if (element instanceof JsonObject object) {
				String type = object.get("type").getAsString();
				if (type.equals("action")) {
					Reference[] args = new Reference[0];
					if (object.has("args")) {
						args = object.getAsJsonArray("args").asList().stream().map(a -> fromJsonElement(_this, a, optArgs)).toList().toArray(Reference[]::new);
					}
					ACTIONS.get(object.get("action").getAsString()).execute(_this, r, args);
					break c; // returns the result
				} else if (type.equals("actionTo")) {
					Reference instance = fromJsonElement(_this, object.get("instance"), optArgs);
					Reference[] args = new Reference[0];
					if (object.has("args")) {
						args = object.getAsJsonArray("args").asList().stream().map(a -> fromJsonElement(_this, a, optArgs)).toList().toArray(Reference[]::new);
					}
					ACTIONS.get(object.get("action").getAsString()).execute(instance, r, args);
					break c; // returns the result
				} else if (type.equals("putGlobal")) {
					String name = object.get("name").getAsString();
					JsonElement jsonElement = object.get("value");
					GLOBALS.put(name, fromJsonElement(_this, jsonElement, optArgs));
					break c; // returns null
				} else if (type.equals("getGlobal")) {
					String name = object.get("name").getAsString();
					r.setReference(GLOBALS.get(name));
					break c; // returns the global
				} else if (type.equals("ifNull")) {
					JsonElement value = object.get("value");
					if (fromJsonElement(_this, value, optArgs).get() == null) {
						JsonArray actions = object.getAsJsonArray("actions");
						for (JsonElement action : actions) {
							fromJsonElement(_this, action, optArgs);
						}
					}
					break c;
				} else if (type.equals("ifNotNull")) {
					JsonElement value = object.get("value");
					if (fromJsonElement(_this, value, optArgs).get() != null) {
						JsonArray actions = object.getAsJsonArray("actions");
						for (JsonElement action : actions) {
							fromJsonElement(_this, action, optArgs);
						}
					}
					break c;
				}
			}
		}
		return r;
	}

	private Action fromJsonArray(JsonArray array) {
		return (_this, _return, args) -> {
			for (JsonElement jsonElement : array) {
				fromJsonElement(_this, jsonElement, args);
			}
		};
	}

	@Override
	protected void init() {
		ACTIONS.get("init").execute(new Reference(this), _void());
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		ACTIONS.get("render").execute(new Reference(this), _void(), new Reference(guiGraphics), new Reference(mouseX), new Reference(mouseY), new Reference(partialTick));
//		SplashRenderer renderer = null;
//		renderer.render(guiGraphics, width, font, 0xffffff00);
	}
}

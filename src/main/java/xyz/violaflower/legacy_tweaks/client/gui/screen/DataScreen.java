package xyz.violaflower.legacy_tweaks.client.gui.screen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import xyz.violaflower.legacy_tweaks.client.gui.screen.legacy.LegacyScreen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class DataScreen extends LegacyScreen {
	private final JsonObject data;
	// use this as a dummy when a method isn't meant to return anything
	private Reference _void() {
		return new Reference();
	}

	private Reference _return() {
		return new Reference();
	}
	public DataScreen(Component title, JsonObject data) {
		super(title);
		this.data = data;
		setup();
	}

	private final Map<String, Reference> GLOBALS = new HashMap<>();
	public final Map<String, Action> ACTIONS = new HashMap<>();


	public void addActions() {
		ACTIONS.put("init", noArgsMethod().of(_this -> {
			ACTIONS.get("super").execute(_this, new Reference(), new Reference("init"));
		}));
		ACTIONS.put("super", (_this, _return, args) -> {
			if (args.length >= 1) {
				Reference arg = args[0];
				String method = arg.get();
				switch (method) {
					case "init": {
						if (args.length > 1) fail();
						super.init();
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
	}

	private NoArgsActionBuilder noArgsMethod() {
		return new NoArgsActionBuilder();
	}

	private class NoArgsActionBuilder {
		private Action of(Consumer<Reference> consumer) {
			return of((_this, _return) -> consumer.accept(_this));
		}

		private Action of(BiConsumer<Reference, Reference> consumer) {
			return (_this, _return, args) -> {
				if (args.length != 1) fail();
				consumer.accept(_this, _return);
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

		<T> void set(T t) {
			this.t = t;
		}
	}
	public interface Action {
		void execute(Reference this_, Reference return_, Reference... args);
	}

	private static void fail() {
		throw new RuntimeException("Failed to interpret action!");
	}

	public static Screen makeDataDrivenScreen(Screen parent, ScreenData data) {

	}

	public class ScreenData {

	}

	private void setup() {
		addActions();
		for (String key : data.getAsJsonObject("actions").keySet()) {
			JsonArray array = data.getAsJsonArray(key);
			ACTIONS.put(key, fromJsonArray(array));
		}
	}

	private Reference fromJsonElement(Reference _this, JsonElement element) {
		Reference r = _return();
		c: {
			if (element instanceof JsonPrimitive primitive && primitive.isString() && element.getAsString() instanceof String string) {
				if (string.startsWith("?")) {
					ACTIONS.get(string.substring(1)).execute(_this, r);
					break c;
				} else if (string.startsWith(":")) {
					r.set(GLOBALS.get(string.substring(1)));
					break c;
				} else {
					throw new RuntimeException();
				}
			} else if (element instanceof JsonObject object) {
				String type = object.get("type").getAsString();
				if (type.equals("action")) {
					Reference[] args = new Reference[0];
					if (object.has("args")) {
						args = object.getAsJsonArray("args").asList().stream().map(a -> fromJsonElement(_this, a)).toList().toArray(Reference[]::new);
					}
					ACTIONS.get(object.get("action").getAsString()).execute(_this, r, args);
					break c;
				} else if (type.equals("putGlobal")) {

				}
			}
		}
		return r;
	}

	private Action fromJsonArray(JsonArray array) {
		return new Action() {
			@Override
			public void execute(Reference _this, Reference _return, Reference... args) {
				for (JsonElement jsonElement : array) {
					if (jsonElement instanceof JsonPrimitive primitive && primitive.isString() && jsonElement.getAsString() instanceof String string) {
						if (string.startsWith("?")) {
							ACTIONS.get(string.substring(1)).execute(_this, _void());
						} else {
							throw new RuntimeException();
						}
					}
				}
			}
		};
	}

	@Override
	protected void init() {
		ACTIONS.get("init").execute(new Reference(this), _void());
	}
}

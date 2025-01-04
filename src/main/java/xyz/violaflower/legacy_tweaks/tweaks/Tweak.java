package xyz.violaflower.legacy_tweaks.tweaks;

import net.minecraft.util.Mth;
import xyz.violaflower.legacy_tweaks.LegacyTweaks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class Tweak implements TweakParent {
    private String id; // this probably shouldn't have a default
    private String author = "Legacy JohnTweaks";
    private String description = "Changes... something.";
    private String exDescription = "Changes something, I think... Maybe?";
    private String version = "1.0.0";
    private boolean isEnabled = true;
    private boolean isGroup = false;
    private final Map<String, Tweak> subTweaks = new LinkedHashMap<>();

    public Tweak() {

    }

    public Tweak(String id) {
        this.id = id;
    }

    public Tweak(String id, String author, String description) {
        this.id = id;
        this.author = author;
        this.description = description;
    }
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        setEnabled(enabled, true);
    }
    public void setEnabled(boolean enabled, boolean triggerHooks) {
        boolean changed = isEnabled != enabled;
        if (!changed) return;
        isEnabled = enabled;
        if (!triggerHooks) return;
        if (isEnabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void setDefaultEnabled(boolean enabled) {
        // TODO for when we do file saving
        setEnabled(enabled, false);
    }

    public void setGroup() {
        this.isGroup = true;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public String getTweakID() {
        return id;
    }

    public void setTweakID(String id) {
        this.id = id;
    }

    public void setTweakAuthor(String... author) {
        this.author = Arrays.stream(author).collect(Collectors.joining(", "));
    }

    public String getTweakAuthor() {
        return this.author;
    }

    public void setTweakDescription(String description) {
        this.description = description;
    }

    public String getTweakDescription() {
        return this.description;
    }

    public void setTweakExtendedDescription(String exDescription) {
        this.exDescription = exDescription;
    }

    public String getTweakExtendedDescription() {
        return this.exDescription;
    }

    public void setTweakVersion(String version) {
        this.version = version;
    }

    public String getTweakVersion() {
        return this.version;
    }

    public void onRegister() {
        LegacyTweaks.LOGGER.info("Registered tweak: {}", getTweakID());
    }

    @SuppressWarnings("unchecked") // shut up IDE i know what i'm doing
	public <T extends Tweak> T getSubTweak(String tweakID) {
        return (T) subTweaks.get(tweakID);
    }

    public void addSubTweak(Tweak tweak) {
        subTweaks.put(tweak.getTweakID(), tweak);
    }

    public void onToggled() {

    }

    public void onEnable() {
        onToggled();
    }

    public void onDisable() {
        onToggled();
    }

    @Override
    public Map<String, Tweak> getSubTweaks() {
        return subTweaks;
    }

    public BooleanOption addBooleanOption(String name) {
        return add(new BooleanOption(name, ignored -> {}));
    }

    private final ArrayList<Option<?>> options = new ArrayList<>();
    private <T extends Option<?>> T add(T option) {
        options.add(option);
        return option;
    }

    public DoubleSliderOption addSliderOption(String name, double min, double max) {
        return add(new DoubleSliderOption(name, ignored -> {}) {
            @Override
            public Double getMin() {
                return min;
            }

            @Override
            public Double getMax() {
                return max;
            }
        });
    }

    public static class EnumProvider<T extends Enum<T>> {
        private final T defaultValue;
        private final Supplier<T[]> values;
        private EnumProvider(T defaultValue, Supplier<T[]> values) {
            this.defaultValue = defaultValue;
            this.values = values;
        }
    }

    public <T extends Enum<T>> EnumProvider<T> enumProvider(T defaultValue, Supplier<T[]> values) {
        return new EnumProvider<>(defaultValue, values);
    }

    public <T extends Enum<T>> EnumSliderOption<T> addSliderOption(String name, EnumProvider<T> enumProvider) {
        return new EnumSliderOption<>(name, enumProvider, ignored -> {}) {};
    }

    public IntSliderOption addSliderOption(String name, int min, int max) {
        return add(new IntSliderOption(name, ignored -> {}) {
            @Override
            public Integer getMin() {
                return min;
            }

            @Override
            public Integer getMax() {
                return max;
            }
        });
    }

    public List<Option<?>> getOptions() {
        return options;
    }

    // TODO get this saved to a file and vice versa
    public static class BooleanOption extends Option<Boolean> {
        public BooleanOption(String name, Consumer<Boolean> onChanged) {
            super(name, onChanged);
            this.value = false;
        }

        @Override
        public String format(Boolean aBoolean) {
            return aBoolean == null ? null : aBoolean.toString();
        }

        public boolean isOn() {
            return get();
        }
    }

    public abstract static class NumberSliderOption<T extends Number> extends SliderOption<T> {
        private final Function<Double, T> newT;
        public NumberSliderOption(String name, Function<Double, T> newT, Consumer<T> onChanged) {
            super(name, newT, onChanged);
            this.newT = newT;
        }

        public abstract T getMin();
        public abstract T getMax();
        public double normalize() {
            double value = get().doubleValue();
            double min = getMin().doubleValue();
            double max = getMax().doubleValue();
            return Mth.clampedMap(value, min, max, 0, 1);
        }

        @Override
        public double doubleValue(T value) {
            return value.doubleValue();
        }

        public T unNormalize(double normalise) {
            // double value = get().doubleValue();
            double min = getMin().doubleValue();
            double max = getMax().doubleValue();
            return newT.apply(Mth.map(normalise, 0, 1, min, max));
        }
    }

    public abstract static class SliderOption<T> extends Option<T> {
        private final Function<Double, T> newT;
        public SliderOption(String name, Function<Double, T> newT, Consumer<T> onChanged) {
            super(name, onChanged);
            this.newT = newT;
        }

        public abstract T getMin();
        public abstract T getMax();
        public double normalize() {
            double value = doubleValue(get());
            double min = doubleValue(getMin());
            double max = doubleValue(getMax());
            return Mth.clampedMap(value, min, max, 0, 1);
        }

        public abstract double doubleValue(T value);

        public T unNormalize(double normalise) {
            // double value = get().doubleValue();
            double min = doubleValue(getMin());
            double max = doubleValue(getMax());
            return newT.apply(Mth.map(normalise, 0, 1, min, max));
        }

        @Override
        public String format(T num) {
            return num == null ? "null" : num.toString();
        }
    }

    public static class EnumSliderOption<T extends Enum<T>> extends SliderOption<T> {
        private final EnumProvider<T> provider;
        public EnumSliderOption(String name, EnumProvider<T> provider, Consumer<T> onChanged) {
            super(name, f -> provider.values.get()[f.intValue()], onChanged);
            this.provider = provider;
            this.value = this.provider.defaultValue;
        }

        @Override
        public T getMax() {
            return provider.values.get()[provider.values.get().length-1];
        }

        @Override
        public double doubleValue(T value) {
            return value.ordinal();
        }

        @Override
        public T getMin() {
            return provider.values.get()[0];
        }

        @Override
        public String format(T t) {
            return t == null ? "null" : t.toString();
        }
    }

    public static class DoubleSliderOption extends NumberSliderOption<Double> {
        public DoubleSliderOption(String name, Consumer<Double> onChanged) {
            super(name, f -> f, onChanged);
            this.value = 0D;
        }

        @Override
        public Double getMax() {
            return 1.0;
        }

        @Override
        public Double getMin() {
            return 0.0;
        }

        @Override
        public String format(Double double_) {
            return double_ == null ? "null" : double_.toString();
        }
    }

    public static class IntSliderOption extends NumberSliderOption<Integer> {
        public IntSliderOption(String name, Consumer<Integer> onChanged) {
            super(name, Double::intValue, onChanged);
            this.value = 0;
        }

        @Override
        public Integer getMax() {
            return 1;
        }

        @Override
        public Integer getMin() {
            return 0;
        }

        @Override
        public String format(Integer integer) {
            return integer == null ? "null" : integer.toString();
        }
    }

    public abstract static class Option<T> {
        private final String name;
        T value;
        Consumer<T> onChanged;
        public Option(String name, Consumer<T> onChanged) {
            this.name = name;
            this.onChanged = onChanged;
        }
        public String getName() {
            return this.name;
        }
        public T get() {
            return value;
        }

        public void set(T t) {
            this.value = t;
            onChanged.accept(t);
        }

        public void setConsumer(Consumer<T> onChanged) {
            this.onChanged = onChanged;
        }

        public abstract String format(T t);
    }
}

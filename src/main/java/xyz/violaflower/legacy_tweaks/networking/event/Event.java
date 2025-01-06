package xyz.violaflower.legacy_tweaks.networking.event;

public interface Event<T> {
	T invoker();
	void register(T t);
}

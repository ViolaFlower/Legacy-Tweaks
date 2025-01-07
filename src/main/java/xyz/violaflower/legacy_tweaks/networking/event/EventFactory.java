package xyz.violaflower.legacy_tweaks.networking.event;

import com.google.common.reflect.AbstractInvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class EventFactory {
	public static <T> Event<T> create(Class<T> eventClass) {
		ArrayList<T> subscribers = new ArrayList<>();
		T o = (T) Proxy.newProxyInstance(eventClass.getClassLoader(), new Class[]{eventClass}, new AbstractInvocationHandler() {
			@Override
			protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
				for (T subscriber : subscribers) {
					method.invoke(subscriber, args);
				}
				return null;
			}
		});
		return new Event<>() {
			@Override
			public T invoker() {
				return o;
			}

			@Override
			public void register(T t) {
				subscribers.add(t);
			}
		};
	}
}

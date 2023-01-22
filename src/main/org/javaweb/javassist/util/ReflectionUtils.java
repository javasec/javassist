package org.javaweb.javassist.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtils {

	public static Method getMethod(Class<?> clazz, String name, Class<?>... argTypes) throws NoSuchMethodException {
		Method method = null;

		while (clazz != Object.class) {
			try {
				method = clazz.getDeclaredMethod(name, argTypes);
				break;
			} catch (NoSuchMethodException e) {
				clazz = clazz.getSuperclass();
			}
		}

		if (method == null) {
			throw new NoSuchMethodException(name);
		} else {
			method.setAccessible(true);
		}

		return method;
	}

	public static Object invokeMethod(Object instance, String name, Class<?>[] argTypes, Object... args)
			throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

		Method method = getMethod(instance.getClass(), name, argTypes);

		return method.invoke(instance, args);
	}

	public static Object invokeStaticMethod(Class<?> clazz, String name, Class<?>[] argTypes, Object... args)
			throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

		Method method = getMethod(clazz, name, argTypes);

		return method.invoke(null, args);
	}

	public static Field getField(Class<?> clazz, String name) throws NoSuchFieldException {
		Field field = null;

		while (clazz != Object.class) {
			try {
				field = clazz.getDeclaredField(name);
				break;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			}
		}

		if (field == null) {
			throw new NoSuchFieldException(name);
		} else {
			field.setAccessible(true);
		}

		return field;
	}

	public static Object invokeField(Object instance, String name)
			throws NoSuchFieldException, IllegalAccessException {

		Field field = getField(instance.getClass(), name);

		return field.get(instance);
	}

	public static <T> T invokeMethodProxy(Object instance, String name, Class<?>[] argTypes, Object... args) {
		try {
			return (T) invokeMethod(instance, name, argTypes, args);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> T invokeFieldProxy(Object instance, String name) {
		try {
			return (T) invokeField(instance, name);
		} catch (Exception e) {
			return null;
		}
	}

}

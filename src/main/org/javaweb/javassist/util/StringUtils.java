package org.javaweb.javassist.util;

public class StringUtils {

	public static int countOf(CharSequence str, char chr) {
		if (str == null) return -1;

		int count = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == chr) {
				count++;
			}
		}

		return count;
	}

	public static String join(final Object[] array, final char separator) {
		if (array == null) {
			return null;
		}

		StringBuilder buf = new StringBuilder();

		for (int i = 1; i < array.length; i++) {
			buf.append(separator);

			if (array[i] != null) {
				buf.append(array[i]);
			}
		}

		return buf.toString();
	}

}

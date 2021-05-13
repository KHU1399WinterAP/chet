package main.java.utils;

public class StringUtils {
	public static String normalize(String text) {
		text = text.replaceAll(" +", " ");
		return text.trim();
	}
}

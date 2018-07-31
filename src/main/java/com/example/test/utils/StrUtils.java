package com.example.test.utils;

public class StrUtils {

	public static boolean isEmpty(String value) {
		if (value == null) return true;
		if (value.isEmpty()) return true;
		return false;
	}
	
	public static int StringToInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return 0;
		}
	}
}

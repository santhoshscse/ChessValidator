package com.target.chess.util;

import com.target.chess.model.Location;

public class CommandUtil {

	public static Object[] getTarget(String input) {
		int length = input.length();
		char toRank = input.charAt(length - 1);
		char toFile = input.charAt(length - 2);
		input = input.substring(0, length - 2);
		Location loc = new Location();
		loc.setFile(toFile);
		loc.setRank(toRank);
		return new Object[] { input, loc };
	}

	public static Object[] getCapture(String input) {
		boolean isCapture = false;
		int length = input.length();
		if (length >= 1) {
			isCapture = input.charAt(length - 1) == 'x' ? true : false;
			if (isCapture) {
				input = input.substring(0, length - 1);
			}
		}
		return new Object[] { input, isCapture };
	}

}

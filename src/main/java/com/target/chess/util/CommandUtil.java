package com.target.chess.util;

import com.target.chess.exception.MoveException;
import com.target.chess.exception.MoveException.ErrorCode;
import com.target.chess.model.Location;

public class CommandUtil {

	public static Object[] getTarget(String input) throws MoveException {
		int length = input.length();
		char toRank = input.charAt(length - 1);
		if (toRank < '1' || toRank > '8') {
			throw new MoveException(ErrorCode.INVALIDRANK);
		}
		char toFile = input.charAt(length - 2);
		if (toFile < 'a' || toFile > 'h') {
			throw new MoveException(ErrorCode.INVALIDFILE);
		}
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

package com.target.chess.util;

import com.target.chess.model.Player;

public class PlayerUtil {
	public static Player getPlayerType(String in) {
		switch (in) {
		case "w":
			return Player.W;
		case "b":
			return Player.B;
		default:
			return null;
		}
	}

	public static String getPlayerName(Player in) {
		switch (in) {
		case W:
			return "w";
		case B:
			return "b";
		default:
			return null;
		}
	}
}

package com.target.chess.util;

import com.target.chess.model.Piece;
import com.target.chess.model.Player;

public class ChessUtil {

	public static boolean isSameCategory(Piece srcPiece, Piece tarPiece) {
		if (srcPiece == null) {
			return false;
		}
		if (tarPiece == null) {
			return false;
		}
		return srcPiece.isWhite() == tarPiece.isWhite();
	}

	public static boolean isEmpty(char file) {
		return Character.MIN_VALUE == file;
	}

	public static boolean isSame(char s, char t) {
		return s == t;
	}

	public static boolean isWhite(Player player) {
		boolean isWhite;
		if (player == Player.W) {
			isWhite = true;
		} else {
			isWhite = false;
		}
		return isWhite;
	}
}

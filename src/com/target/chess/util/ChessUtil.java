package com.target.chess.util;

import com.target.chess.model.Piece;

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

}

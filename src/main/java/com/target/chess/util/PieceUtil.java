package com.target.chess.util;

import com.target.chess.model.PieceType;

public class PieceUtil {
	public static PieceType getType(char piece) {
		switch (piece) {
		case 'p':
		case 'P':
			return PieceType.P;
		case 'r':
		case 'R':
			return PieceType.R;
		case 'n':
		case 'N':
			return PieceType.N;
		case 'b':
		case 'B':
			return PieceType.B;
		case 'q':
		case 'Q':
			return PieceType.Q;
		case 'k':
		case 'K':
			return PieceType.K;
		default:
			return null;

		}
	}

	public static boolean isWhite(char ch) throws Exception {
		if (ch >= 'a' && ch <= 'z') {
			return false;
		} else if (ch >= 'A' && ch <= 'Z') {
			return true;
		}
		throw new Exception("Invalid input");
	}

	public static char getPieceName(PieceType type, boolean isWhite) {
		if (isWhite) {
			return type.name().charAt(0);
		} else {
			return (char) (type.name().charAt(0) + 32);
		}
	}

	public static void main(String[] args) {
		System.out.println(getPieceName(PieceType.P, false));
		System.out.println((int) 'p');
		System.out.println((int) 'P');
	}
}

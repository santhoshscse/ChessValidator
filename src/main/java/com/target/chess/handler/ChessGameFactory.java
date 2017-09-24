package com.target.chess.handler;

import com.target.chess.ChessGame;

public class ChessGameFactory {
	public static ChessGame getChessGame() throws Exception {
		return new ChessGameImpl();
	}
}

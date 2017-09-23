package com.target.chess;

import com.target.chess.ChessBoardManager;

public class ChessCommandTest {
	public static void main(String[] args) throws Exception {
		ChessBoardManager game = new ChessBoardManager();
		String out = game.display();
		System.out.println(out);
		out = game.move("e4");
		System.out.println(out);
		out = game.move("c5");
		System.out.println(out);
		out = game.move("e3");
		System.out.println(out);
	}
}

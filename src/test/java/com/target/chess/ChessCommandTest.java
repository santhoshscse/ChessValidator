package com.target.chess;

import org.testng.annotations.Test;

import com.target.chess.handler.ChessGameFactory;

public class ChessCommandTest {

	@Test
	public void kingMoveTest() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);
			out = game.move("f3");
			System.out.println(out);
			out = game.move("d6");
			System.out.println(out);
			out = game.move("Kf2");
			System.out.println(out);
			out = game.move("b6");
			System.out.println(out);
			out = game.move("Kg3");
			System.out.println(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void queenMoveTest() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);
			out = game.move("e3");
			System.out.println(out);
			out = game.move("d6");
			System.out.println(out);
			out = game.move("Qh5");
			System.out.println(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void bishopMoveTest() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);
			out = game.move("d3");
			System.out.println(out);
			out = game.move("d6");
			System.out.println(out);
			out = game.move("Bf4");
			System.out.println(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void knightMoveTest() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);
			out = game.move("Na3");
			System.out.println(out);
			out = game.move("Nc6");
			System.out.println(out);
			out = game.move("Nc4");
			System.out.println(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void rookMoveTest() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			out = game.move("e4");
			System.out.println(out);
			out = game.move("c5");
			System.out.println(out);
			out = game.move("Rxa8");
			System.out.println(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

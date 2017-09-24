package com.target.chess;

import com.target.chess.ChessBoardManager;

public class ChessCommandTest {
	public static void main(String[] args) throws Exception {
		rookMoveTest();

		knightMoveTest();

		bishopMoveTest();

		queenMoveTest();

		kingMoveTest();
	}

	private static void kingMoveTest() throws Exception {
		try {
			ChessBoardManager game = new ChessBoardManager();
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

	private static void queenMoveTest() throws Exception {
		try {
			ChessBoardManager game = new ChessBoardManager();
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

	private static void bishopMoveTest() throws Exception {
		try {
			ChessBoardManager game = new ChessBoardManager();
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

	private static void knightMoveTest() throws Exception {
		try {
			ChessBoardManager game = new ChessBoardManager();
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

	private static void rookMoveTest() throws Exception {
		try {
			ChessBoardManager game = new ChessBoardManager();
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

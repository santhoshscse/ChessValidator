package com.target.chess;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.target.chess.exception.MoveException;
import com.target.chess.exception.MoveException.ErrorCode;
import com.target.chess.handler.ChessGameFactory;

public class KingMoveTest {

	@Test
	public void test5KingMove() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			String expected = "rnbqkbnr/pppppppp/8/8/8/5P2/PPPPP1PP/RNBQKBNR b KQkq -  0 1";
			String actual = game.move("f3");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/ppp1pppp/3p4/8/8/5P2/PPPPP1PP/RNBQKBNR w KQkq -  0 2";
			actual = game.move("d6");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/ppp1pppp/3p4/8/8/5P2/PPPPPKPP/RNBQ1BNR b KQkq -  0 2";
			actual = game.move("Kf2");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/p1p1pppp/1p1p4/8/8/5P2/PPPPPKPP/RNBQ1BNR w KQkq -  0 3";
			actual = game.move("b6");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/p1p1pppp/1p1p4/8/8/5PK1/PPPPP1PP/RNBQ1BNR b KQkq -  0 3";
			actual = game.move("Kg3");
			Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test5KingMoveInvalid() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			String expected = "rnbqkbnr/pppppppp/8/8/8/5P2/PPPPP1PP/RNBQKBNR b KQkq -  0 1";
			String actual = game.move("f3");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/ppp1pppp/3p4/8/8/5P2/PPPPP1PP/RNBQKBNR w KQkq -  0 2";
			actual = game.move("d6");
			Assert.assertEquals(actual, expected);

			try {
				game.move("Kf3");

			} catch (MoveException e) {
				Assert.assertEquals(e.getCode(), ErrorCode.INVALIDSOURCE);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

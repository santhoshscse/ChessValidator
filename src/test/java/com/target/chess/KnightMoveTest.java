package com.target.chess;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.target.chess.exception.MoveException;
import com.target.chess.exception.MoveException.ErrorCode;
import com.target.chess.handler.ChessGameFactory;

public class KnightMoveTest {
	@Test
	public void test0KnightMove() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			String expected = "rnbqkbnr/pppppppp/8/8/8/N7/PPPPPPPP/R1BQKBNR b KQkq -  0 1";
			String actual = game.move("Na3");
			Assert.assertEquals(actual, expected);

			expected = "r1bqkbnr/pppppppp/2n5/8/8/N7/PPPPPPPP/R1BQKBNR w KQkq -  0 2";
			actual = game.move("Nc6");
			Assert.assertEquals(actual, expected);

			expected = "r1bqkbnr/pppppppp/2n5/8/2N5/8/PPPPPPPP/R1BQKBNR b KQkq -  0 2";
			actual = game.move("Nc4");
			Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test0KnightMoveInvalidCapture() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			try {
				game.move("Nxa3");
			} catch (MoveException e) {
				Assert.assertEquals(e.getCode(), ErrorCode.TARGETEMPTY);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test0KnightMoveInvalidMove() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			try {
				game.move("Na4");
			} catch (MoveException e) {
				Assert.assertEquals(e.getCode(), ErrorCode.INVALIDSOURCE);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

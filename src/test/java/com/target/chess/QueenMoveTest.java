package com.target.chess;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.target.chess.exception.MoveException;
import com.target.chess.exception.MoveException.ErrorCode;
import com.target.chess.handler.ChessGameFactory;

public class QueenMoveTest {
	@Test
	public void test4QueenMove() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();

			String expected = "rnbqkbnr/pppppppp/8/8/8/4P3/PPPP1PPP/RNBQKBNR b KQkq -  0 1";
			String actual = game.move("e3");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/ppp1pppp/3p4/8/8/4P3/PPPP1PPP/RNBQKBNR w KQkq -  0 2";
			actual = game.move("d6");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/ppp1pppp/3p4/7Q/8/4P3/PPPP1PPP/RNB1KBNR b KQkq -  0 2";
			actual = game.move("Qh5");
			Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test4QueenMoveInvalid() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();

			String expected = "rnbqkbnr/pppppppp/8/8/8/4P3/PPPP1PPP/RNBQKBNR b KQkq -  0 1";
			String actual = game.move("e3");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/ppp1pppp/3p4/8/8/4P3/PPPP1PPP/RNBQKBNR w KQkq -  0 2";
			actual = game.move("d6");
			Assert.assertEquals(actual, expected);

			try {
				game.move("Qh4");
			} catch (MoveException e) {
				Assert.assertEquals(e.getCode(), ErrorCode.INVALIDSOURCE);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

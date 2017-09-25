package com.target.chess;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.target.chess.exception.MoveException;
import com.target.chess.exception.MoveException.ErrorCode;
import com.target.chess.handler.ChessGameFactory;

public class BishopMoveTest {
	@Test
	public void test3bishopMove() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			String expected = "rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b KQkq -  0 1";
			String actual = game.move("d3");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/ppp1pppp/3p4/8/8/3P4/PPP1PPPP/RNBQKBNR w KQkq -  0 2";
			actual = game.move("d6");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/ppp1pppp/3p4/8/5B2/3P4/PPP1PPPP/RN1QKBNR b KQkq -  0 2";
			actual = game.move("Bf4");
			Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test3bishopMoveInvalid() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			String expected = "rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b KQkq -  0 1";
			String actual = game.move("d3");
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/ppp1pppp/3p4/8/8/3P4/PPP1PPPP/RNBQKBNR w KQkq -  0 2";
			actual = game.move("d6");
			Assert.assertEquals(actual, expected);

			try {
				game.move("Bf3");
			} catch (MoveException e) {
				Assert.assertEquals(e.getCode(), ErrorCode.INVALIDSOURCE);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

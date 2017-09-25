package com.target.chess;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.target.chess.exception.MoveException;
import com.target.chess.exception.MoveException.ErrorCode;
import com.target.chess.handler.ChessGameFactory;

public class PawnMoveTest {

	@Test
	public void test0PawnMove() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			String expected = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
			String actual = game.move("e4");
			System.out.println(actual);
			Assert.assertEquals(actual, expected);

			expected = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2";
			actual = game.move("c5");
			System.out.println(actual);
			Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test0PawnMoveInvalid() throws Exception {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);

			try {
				game.move("xe4");
			} catch (MoveException e) {
				Assert.assertEquals(ErrorCode.INVALIDSOURCE, e.getCode());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

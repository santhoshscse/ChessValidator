package com.target.chess;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.target.chess.exception.MoveException;
import com.target.chess.exception.MoveException.ErrorCode;
import com.target.chess.handler.ChessGameFactory;

public class ChessCommandTest {

	@Test
	public void test1InvalidFile() {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			try {
				game.move("i3");
			} catch (MoveException e) {
				Assert.assertEquals(e.getCode(), ErrorCode.INVALIDFILE);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test1InvalidRank() {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			try {
				game.move("g9");
			} catch (MoveException e) {
				Assert.assertEquals(e.getCode(), ErrorCode.INVALIDRANK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test1CheckMove() {
		try {
			ChessGame game = ChessGameFactory.getChessGame();
			String out = game.display();
			System.out.println(out);
			
			String expected = "rnbqkbnr/pppppppp/8/8/8/4P3/PPPP1PPP/RNBQKBNR b KQkq -  0 1";
			String actual = game.move("e3");
			Assert.assertEquals(actual, expected);
			
			expected = "rnbqkbnr/ppp1pppp/3p4/8/8/4P3/PPPP1PPP/RNBQKBNR w KQkq -  0 2";
			actual = game.move("d6");
			Assert.assertEquals(actual, expected);
			
			expected = "rnbqkbnr/ppp1pppp/3p4/1B6/8/4P3/PPPP1PPP/RNBQK1NR b KQkq -  0 2";
			actual = game.move("Bb5");
			Assert.assertEquals(actual, expected);
			
			try {
				game.move("f6");
			} catch (MoveException e) {
				Assert.assertEquals(e.getCode(), ErrorCode.CHECKMOVE);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

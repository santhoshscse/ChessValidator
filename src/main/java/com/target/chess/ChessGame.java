package com.target.chess;

import java.util.List;

import com.target.chess.model.Move;

public interface ChessGame {
	public String move(String input) throws Exception;

	public String display();

	public List<Move> getMoveList();
}

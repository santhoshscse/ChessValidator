package com.target.chess.handler;

import java.util.HashMap;

import com.target.chess.model.Board;
import com.target.chess.model.Command;
import com.target.chess.model.Move;
import com.target.chess.model.PieceType;
import com.target.chess.model.Player;
import com.target.chess.validator.PawnMoveValidator;

public class CommandValidator {
	private static HashMap<PieceType, PawnMoveValidator> handlers = new HashMap<>();
	static {
		handlers.put(PieceType.P, new PawnMoveValidator());
	}

	public Move validateCommandAndGetMove(PieceType type, Board board, Player player, Command command)
			throws Exception {
		return handlers.get(type).validateCommandAndGetMove(board, player, command);
	}
}

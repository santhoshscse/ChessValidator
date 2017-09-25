package com.target.chess.handler;

import java.util.HashMap;

import com.target.chess.model.Board;
import com.target.chess.model.Command;
import com.target.chess.model.Location;
import com.target.chess.model.Move;
import com.target.chess.model.PieceType;
import com.target.chess.model.Player;
import com.target.chess.validator.BishopMoveValidator;
import com.target.chess.validator.KingMoveValidator;
import com.target.chess.validator.KnightMoveValidator;
import com.target.chess.validator.PawnMoveValidator;
import com.target.chess.validator.PieceMoveValidator;
import com.target.chess.validator.QueenMoveValidator;
import com.target.chess.validator.RookMoveValidator;

public class CommandValidator {
	private static HashMap<PieceType, PieceMoveValidator> handlers = new HashMap<>();
	static {
		handlers.put(PieceType.P, new PawnMoveValidator());
		handlers.put(PieceType.R, new RookMoveValidator());
		handlers.put(PieceType.N, new KnightMoveValidator());
		handlers.put(PieceType.B, new BishopMoveValidator());
		handlers.put(PieceType.K, new KingMoveValidator());
		handlers.put(PieceType.Q, new QueenMoveValidator());
	}

	public Move validateCommandAndGetMove(PieceType type, Board board, Player player, Command command,
			Location enPassantLoc) throws Exception {
		return handlers.get(type).validateCommandAndGetMove(board, player, command, enPassantLoc);
	}
}

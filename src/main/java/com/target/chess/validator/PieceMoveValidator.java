package com.target.chess.validator;

import com.target.chess.model.Board;
import com.target.chess.model.Command;
import com.target.chess.model.Move;
import com.target.chess.model.Player;

public interface PieceMoveValidator {
	Move validateCommandAndGetMove(Board borad, Player player, Command command) throws Exception;
}

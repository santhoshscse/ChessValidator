package com.target.chess.handler;

import java.util.List;

import com.target.chess.ChessGame;
import com.target.chess.handler.BoardHandler;
import com.target.chess.handler.CommandReader;
import com.target.chess.handler.CommandValidator;
import com.target.chess.model.Command;
import com.target.chess.model.Move;

class ChessGameImpl implements ChessGame {

	private CommandReader commandReader = null;
	private CommandValidator commandValidator = null;
	private BoardHandler boardHandler = null;

	public ChessGameImpl() throws Exception {
		commandReader = new CommandReader();
		commandValidator = new CommandValidator();
		boardHandler = new BoardHandler();
	}

	public String move(String input) throws Exception {
		Command command = commandReader.getAsCommand(input);
		Move move = commandValidator.validateCommandAndGetMove(command.getSourcePiece(), boardHandler.getBoard(),
				boardHandler.getCurrentPlayer(), command, boardHandler.getEnPassant());
		boardHandler.makeMove(move);
		return display();
	}

	public String display() {
		return boardHandler.display();
	}

	public List<Move> getMoveList() {
		return boardHandler.getMoveList();
	}
}

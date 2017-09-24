package com.target.chess.handler;

import java.util.List;

import com.target.chess.ChessGame;
import com.target.chess.handler.ChessBoardHandler;
import com.target.chess.handler.CommandReader;
import com.target.chess.handler.CommandValidator;
import com.target.chess.model.Command;
import com.target.chess.model.Move;

class ChessGameImpl implements ChessGame {

	private CommandReader commandReader = null;
	private CommandValidator pieceHandler = null;
	private ChessBoardHandler boardHandler = null;

	public ChessGameImpl() throws Exception {
		commandReader = new CommandReader();
		pieceHandler = new CommandValidator();
		boardHandler = new ChessBoardHandler();
	}

	public String move(String input) throws Exception {
		Command command = commandReader.getAsCommand(input);
		Move move = pieceHandler.validateCommandAndGetMove(command.getSourcePiece(), boardHandler.getBoard(),
				boardHandler.getCurrentPlayer(), command);
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

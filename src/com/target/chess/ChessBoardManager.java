package com.target.chess;

import com.target.chess.handler.ChessBoardHandler;
import com.target.chess.handler.CommandReader;
import com.target.chess.handler.CommandValidator;
import com.target.chess.model.Command;
import com.target.chess.model.Move;

public class ChessBoardManager {

	private CommandReader commandReader = null;
	private CommandValidator pieceHandler = null;
	private ChessBoardHandler boardHandler = null;

	public ChessBoardManager() throws Exception {
		commandReader = new CommandReader();
		pieceHandler = new CommandValidator();
		boardHandler = new ChessBoardHandler();
	}

	public void move(String input) throws Exception {
		Command command = commandReader.getAsMoveCommand(input);
		Move move = pieceHandler.validateCommandAndGetMove(command.getSourcePiece(), boardHandler.getBoard(),
				boardHandler.getCurrentPlayer(), command);
		boardHandler.makeMove(move);
	}

	public String display() {
		return boardHandler.display();
	}
}

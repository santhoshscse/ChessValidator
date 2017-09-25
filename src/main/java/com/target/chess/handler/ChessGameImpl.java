package com.target.chess.handler;

import java.util.List;

import com.target.chess.ChessGame;
import com.target.chess.exception.MoveException;
import com.target.chess.exception.MoveException.ErrorCode;
import com.target.chess.handler.BoardHandler;
import com.target.chess.handler.CommandReader;
import com.target.chess.handler.CommandValidator;
import com.target.chess.model.Board;
import com.target.chess.model.Command;
import com.target.chess.model.Location;
import com.target.chess.model.Move;
import com.target.chess.model.PieceType;
import com.target.chess.model.Player;

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
		validateCheckMove(boardHandler.getBoard(), move);
		boardHandler.makeMove(move);
		return display();
	}

	private void validateCheckMove(Board board, Move move) throws Exception {
		boolean isCheck = isCheckMove(board, move);
		if (isCheck) {
			throw new MoveException(ErrorCode.CHECKMOVE);
		}
	}

	private boolean isCheckMove(Board board, Move move) {
		board.movePiece(move.getSourceLocation(), move.getTargetLocation(), move.isEnPassantCapture());
		Player player = move.getPlayer();
		boolean isNextWhite;
		if (player == Player.B) {
			player = Player.W;
			isNextWhite = true;
		} else {
			player = Player.B;
			isNextWhite = false;
		}
		boolean isCurrWhite = !isNextWhite;
		Location kingLoc = board.getLocationOfKing(isCurrWhite);

		for (PieceType type : PieceType.values()) {
			List<Location> locList = board.getAllLocationsOfPiece(type, isNextWhite);
			for (Location srcLoc : locList) {
				Command command = new Command();
				command.setSourceLocation(srcLoc);
				command.setTargetLocation(kingLoc);
				command.setCapture(true);
				command.setSourcePiece(type);
				try {
					Move tmpMove = commandValidator.validateCommandAndGetMove(type, board, player, command,
							boardHandler.getEnPassant());
					if (tmpMove != null) {
						return true;
					}
				} catch (MoveException me) {
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public String display() {
		return boardHandler.display();
	}

	public List<Move> getMoveList() {
		return boardHandler.getMoveList();
	}
}

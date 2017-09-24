package com.target.chess.validator;

import com.target.chess.model.Board;
import com.target.chess.model.Command;
import com.target.chess.model.Location;
import com.target.chess.model.Move;
import com.target.chess.model.Piece;
import com.target.chess.model.PieceType;
import com.target.chess.model.Player;
import com.target.chess.util.ChessUtil;

public abstract class PieceMoveValidator {

	enum MoveType {
		MoveAlongRank, MoveAlongFile, MoveAlongFileRank;
	}

	public final Move validateCommandAndGetMove(Board board, Player player, Command command, Location enPassantLoc)
			throws Exception {
		boolean isCapture = command.isCapture();
		Location target = command.getTargetLocation();

		Location source = getSourceLocation(board, player, command);

		Move move = new Move();
		move.setCapture(isCapture);
		move.setPlayer(player);
		move.setSourceLocation(source);
		move.setTargetLocation(target);

		Piece srcPiece = board.getPieceByLocation(source);
		Piece tarPiece = board.getPieceByLocation(target);

		validateCaptureOrEnPassantCapture(enPassantLoc, isCapture, target, move, srcPiece, tarPiece);

		validateCategory(srcPiece, tarPiece);

		move.setSourcePiece(srcPiece);
		move.setTargetPiece(tarPiece);

		move.setEnPassant(getEnPassant(board, source, target));
		return move;

	}

	private void validateCaptureOrEnPassantCapture(Location enPassant, boolean isCapture, Location target, Move move,
			Piece srcPiece, Piece tarPiece) throws Exception {
		boolean isEnPassantMove = enPassant != null && srcPiece.getPieceType() == PieceType.P
				&& enPassant.getFile() == target.getFile() && enPassant.getRank() == target.getRank();

		boolean validMove = false;
		if (isCapture) {
			if (tarPiece != null) {
				validMove = true;
			} else if (isEnPassantMove) {
				validMove = true;
			}
		} else {
			if (tarPiece == null) {
				validMove = true;
			} else if (!isEnPassantMove) {
				validMove = true;
			}
		}
		if (validMove) {
			move.setEnPassantCapture(isEnPassantMove);
		}

		if (!validMove) {
			if (isCapture) {
				throw new Exception("target is empty to capture or not enPassant capture");
			} else {
				throw new Exception("target is not empty or it must be enPassantCapture");
			}
		}
	}

	public abstract Location getSourceLocation(Board borad, Player player, Command command) throws Exception;

	public Location getEnPassant(Board board, Location source, Location target) {
		return null;
	}

	private void validateCategory(Piece srcPiece, Piece tarPiece) throws Exception {
		if (ChessUtil.isSameCategory(srcPiece, tarPiece)) {
			throw new Exception("source & target pieces are same category");
		}
	}

	private void validateCapture(Board board, Location enPassant, Location target, boolean isCapture, Piece tarPiece,
			Move move) throws Exception {
		if (isCapture) {
			if (tarPiece != null) {
				return;
			} else if (enPassant != null && enPassant.getFile() == target.getFile()
					&& enPassant.getRank() == target.getRank()) {
				move.setEnPassantCapture(true);
				return;
			}
		}
		if (isCapture && tarPiece == null) {
			throw new Exception("target is empty to capture");
		} else if (!isCapture && tarPiece != null) {
			throw new Exception("target is not empty, so it must be capture");
		}
	}

	public boolean isPieceExist(Board board, Location source, Location target, MoveType type) {
		if (type == MoveType.MoveAlongRank) {
			return checkAlongRank(board, source, target);
		} else if (type == MoveType.MoveAlongFile) {
			return checkAlongFile(board, source, target);
		} else if (type == MoveType.MoveAlongFileRank) {
			return checkAlongFileRank(board, source, target);
		}
		return false;
	}

	private boolean checkAlongFileRank(Board board, Location source, Location target) {
		char startFile;
		char endFile;
		char startRank;
		char endRank;
		if (source.getFile() > target.getFile()) {
			startFile = target.getFile();
			endFile = source.getFile();
		} else {
			startFile = source.getFile();
			endFile = target.getFile();
		}

		if (source.getRank() > target.getRank()) {
			startRank = target.getRank();
			endRank = source.getRank();
		} else {
			startRank = source.getRank();
			endRank = target.getRank();
		}

		for (char file = (char) (startFile + 1), rank = (char) (startRank + 1); file < endFile
				&& rank < endRank; file++, rank++) {
			Location tmpLoc = new Location();
			tmpLoc.setFile(file);
			tmpLoc.setRank(rank);
			if (board.getPieceByLocation(tmpLoc) != null) {
				return true;
			}
		}
		return false;
	}

	private boolean checkAlongFile(Board board, Location source, Location target) {
		char start;
		char end;
		char rank = source.getRank();
		if (source.getFile() > target.getFile()) {
			start = target.getFile();
			end = source.getFile();
		} else {
			start = source.getFile();
			end = target.getFile();
		}

		for (char st = (char) (start + 1); st < end; st++) {
			Location tmpLoc = new Location();
			tmpLoc.setFile(st);
			tmpLoc.setRank(rank);
			if (board.getPieceByLocation(tmpLoc) != null) {
				return true;
			}
		}
		return false;
	}

	private boolean checkAlongRank(Board board, Location source, Location target) {
		char start;
		char end;
		char file = source.getFile();
		if (source.getRank() > target.getRank()) {
			start = target.getRank();
			end = source.getRank();
		} else {
			start = source.getRank();
			end = target.getRank();
		}

		for (char st = (char) (start + 1); st < end; st++) {
			Location tmpLoc = new Location();
			tmpLoc.setFile(file);
			tmpLoc.setRank(st);
			Piece pieceByLocation = board.getPieceByLocation(tmpLoc);
			if (pieceByLocation != null) {
				return true;
			}
		}
		return false;
	}

}

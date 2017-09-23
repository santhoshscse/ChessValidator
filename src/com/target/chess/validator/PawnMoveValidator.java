package com.target.chess.validator;

import java.util.List;

import com.target.chess.model.Board;
import com.target.chess.model.Command;
import com.target.chess.model.Location;
import com.target.chess.model.Move;
import com.target.chess.model.Piece;
import com.target.chess.model.PieceType;
import com.target.chess.model.Player;
import com.target.chess.util.MoveUtil;

public class PawnMoveValidator implements PieceMoveValidator {

	@Override
	public Move validateCommandAndGetMove(Board board, Player player, Command command) throws Exception {

		Location source = command.getSourceLocation();
		Location target = command.getTargetLocation();
		boolean isCapture = command.isCapture();

		Move move = getMove(board, player, source, target, isCapture);

		Piece srcPiece = board.getPieceByLocation(source);
		Piece tarPiece = board.getPieceByLocation(target);
		if (MoveUtil.isSame(srcPiece, tarPiece)) {
			throw new Exception("source & target pieces are same category");
		}
		move.setSourcePiece(srcPiece);
		move.setTargetPiece(tarPiece);

		move.setEnPassant(getEnPassant(board, source, target));
		return move;
	}

	private String getEnPassant(Board board, Location source, Location target) {
		if (source.getFile() == target.getFile()) {
			if (source.getRank() - target.getRank() == 2) {
				char tmpRank = (char) (source.getRank() - 1);
				return source.getFile() + "" + tmpRank;
			} else if (target.getRank() - source.getRank() == 2) {
				char tmpRank = (char) (target.getRank() - 1);
				return source.getFile() + "" + tmpRank;
			}
		}
		return "-";
	}

	private Move getMove(Board board, Player player, Location source, Location target, boolean isCapture)
			throws Exception {
		char sourceFile = 0;
		char sourceRank = 0;
		if (source != null) {
			sourceFile = source.getFile();
			sourceRank = source.getRank();
		}
		if (isEmpty(sourceFile) && isEmpty(sourceRank)) {
			source = getMove(board, player, target, isCapture);
		} else if (!isEmpty(sourceFile) && isEmpty(sourceRank)) {
			source = getMoveWithFile(board, player, sourceFile, target, isCapture);
		} else if (isEmpty(sourceFile) && !isEmpty(sourceRank)) {
			source = getMoveWithRank(board, player, sourceRank, target, isCapture);
		}

		Move move = new Move();
		move.setCapture(isCapture);
		move.setPlayer(player);
		move.setSourceLocation(source);
		move.setTargetLocation(target);
		move.setSourcePiece(board.getPieceByLocation(source));
		move.setTargetPiece(board.getPieceByLocation(target));
		return move;
	}

	private Location getMoveWithRank(Board board, Player player, char sourceRank, Location target, boolean isCapture)
			throws Exception {

		boolean isWhite;
		if (player == Player.W) {
			isWhite = true;
		} else {
			isWhite = false;
		}

		List<Location> locList = board.getAllLocationsOfPiece(PieceType.P, isWhite);

		Location source = null;
		Location tmpLoc = null;
		for (Location loc : locList) {

			if (tmpLoc != null) {
				if (source == null) {
					source = tmpLoc;
					tmpLoc = null;
				} else {
					throw new Exception("Ambiguity");
				}
			}

			if (loc.getRank() == sourceRank && loc.getFile() - 1 == target.getFile()) {
				tmpLoc = loc;
			} else if (loc.getRank() == sourceRank && loc.getFile() + 1 == target.getFile()) {
				tmpLoc = loc;
			}
		}

		return source;

	}

	private Location getMoveWithFile(Board board, Player player, char sourceFile, Location target, boolean isCapture)
			throws Exception {
		boolean isWhite;
		if (player == Player.W) {
			isWhite = true;
		} else {
			isWhite = false;
		}

		List<Location> locList = board.getAllLocationsOfPiece(PieceType.P, isWhite);

		Location source = null;
		Location tmpLoc = null;
		for (Location loc : locList) {

			if (tmpLoc != null) {
				if (source == null) {
					source = tmpLoc;
					tmpLoc = null;
				} else {
					throw new Exception("Ambiguity");
				}
			}

			if (isWhite) {

				if (isCapture) {

					if (loc.getRank() - 1 == target.getRank() && sourceFile == target.getFile()) {
						tmpLoc = loc;
					}

				} else {

					if (loc.getRank() - 1 == target.getRank() && sourceFile == target.getFile()) {
						tmpLoc = loc;
					} else if (loc.getRank() == 6 && loc.getRank() - 2 == target.getRank()
							&& sourceFile == target.getFile()) {
						tmpLoc = loc;
					}

				}

			} else {

				if (isCapture) {

					if (loc.getRank() + 1 == target.getRank() && sourceFile == target.getFile()) {
						tmpLoc = loc;
					}

				} else {

					if (loc.getRank() + 1 == target.getRank() && sourceFile == target.getFile()) {
						tmpLoc = loc;
					} else if (loc.getRank() == 1 && sourceFile == target.getRank()
							&& loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					}

				}
			}
		}

		return source;
	}

	private Location getMove(Board board, Player player, Location target, boolean isCapture) throws Exception {
		boolean isWhite;
		if (player == Player.W) {
			isWhite = true;
		} else {
			isWhite = false;
		}

		List<Location> locList = board.getAllLocationsOfPiece(PieceType.P, isWhite);

		Location source = null;
		Location tmpLoc = null;
		for (Location loc : locList) {

			if (tmpLoc != null) {
				if (source == null) {
					source = tmpLoc;
					tmpLoc = null;
				} else {
					throw new Exception("Ambiguity");
				}
			}

			if (isWhite) {

				if (isCapture) {

					if (loc.getRank() - 1 == target.getRank() && loc.getFile() - 1 == target.getFile()) {
						tmpLoc = loc;
					} else if (loc.getRank() - 1 == target.getRank() && loc.getFile() + 1 == target.getFile()) {
						tmpLoc = loc;
					}

				} else {

					if (loc.getRank() - 1 == target.getRank() && loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					} else if (tmpLoc == null && loc.getRank() == 6 && loc.getRank() - 2 == target.getRank()
							&& loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					}

				}

			} else {

				if (isCapture) {

					if (loc.getRank() + 1 == target.getRank() && loc.getFile() - 1 == target.getFile()) {
						tmpLoc = loc;
					} else if (loc.getRank() + 1 == target.getRank() && loc.getFile() + 1 == target.getFile()) {
						tmpLoc = loc;
					}

				} else {

					if (loc.getRank() + 1 == target.getRank() && loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					} else if (tmpLoc == null && loc.getRank() == 1 && loc.getRank() + 2 == target.getRank()
							&& loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					}

				}
			}
		}

		return source;
	}

	private boolean isEmpty(char file) {
		return Character.MIN_VALUE == file;
	}

	// @Override
	// public String getEnPassant(Move move) {
	// int fromFile = move.getFromFile();
	// int fromRank = move.getFromRank();
	//
	// int toFile = move.getToFile();
	// int toRank = move.getToRank();
	//
	// if (fromFile == toFile) {
	// if (Math.abs(fromRank - toRank) == 2) {
	// if (fromRank > toRank) {
	// return ChessUtil.getAsFile(fromFile) + "" + ChessUtil.getAsRank(toRank +
	// 1);
	// } else {
	// return ChessUtil.getAsFile(fromFile) + "" + ChessUtil.getAsRank(toRank -
	// 1);
	// }
	// }
	// }
	// return "-";
	// }

}

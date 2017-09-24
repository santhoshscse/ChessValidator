package com.target.chess.validator;

import java.util.List;

import com.target.chess.model.Board;
import com.target.chess.model.Command;
import com.target.chess.model.Location;
import com.target.chess.model.Piece;
import com.target.chess.model.PieceType;
import com.target.chess.model.Player;
import com.target.chess.util.ChessUtil;

public class PawnMoveValidator extends PieceMoveValidator {

	private static final PieceType PAWN = PieceType.P;

	@Override
	public Location getSourceLocation(Board board, Player player, Command command) throws Exception {
		Location source = command.getSourceLocation();
		Location target = command.getTargetLocation();
		boolean isCapture = command.isCapture();
		return fillSource(board, player, source, target, isCapture);
	}

	@Override
	public Location getEnPassant(Board board, Location source, Location target) {
		Location loc = null;
		if (source.getFile() == target.getFile()) {
			if (source.getRank() - target.getRank() == 2) {
				char tmpRank = (char) (source.getRank() - 1);
				loc = new Location();
				loc.setFile(source.getFile());
				loc.setRank(tmpRank);
			} else if (target.getRank() - source.getRank() == 2) {
				char tmpRank = (char) (target.getRank() - 1);
				loc = new Location();
				loc.setFile(source.getFile());
				loc.setRank(tmpRank);
			}
		}
		return loc;
	}

	private Location fillSource(Board board, Player player, Location source, Location target, boolean isCapture)
			throws Exception {
		char sourceFile = 0;
		char sourceRank = 0;
		if (source != null) {
			sourceFile = source.getFile();
			sourceRank = source.getRank();
		}
		if (ChessUtil.isEmpty(sourceFile) && ChessUtil.isEmpty(sourceRank)) {
			source = fillSource(board, player, target, isCapture);
		} else if (!ChessUtil.isEmpty(sourceFile) && ChessUtil.isEmpty(sourceRank)) {
			source = fillSourceWithFile(board, player, sourceFile, target, isCapture);
		} else if (ChessUtil.isEmpty(sourceFile) && !ChessUtil.isEmpty(sourceRank)) {
			source = fillSourceWithRank(board, player, sourceRank, target, isCapture);
		} else {
			source = fillSourceWithFileRank(board, player, source, target, isCapture);
		}
		if (source == null || ChessUtil.isEmpty(source.getFile()) || ChessUtil.isEmpty(source.getRank())) {
			throw new Exception("No source found");
		}
		return source;

	}

	private Location fillSourceWithFileRank(Board board, Player player, Location source, Location target,
			boolean isCapture) {
		Piece srcPiece = board.getPieceByLocation(source);
		if (srcPiece.getPieceType() == PAWN) {
			return source;
		}
		return null;
	}

	private Location fillSourceWithRank(Board board, Player player, char sourceRank, Location target, boolean isCapture)
			throws Exception {

		boolean isWhite = ChessUtil.isWhite(player);

		List<Location> locList = board.getAllLocationsOfPiece(PAWN, isWhite);

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
		if (source == null) {
			source = tmpLoc;
		}
		return source;

	}

	private Location fillSourceWithFile(Board board, Player player, char sourceFile, Location target, boolean isCapture)
			throws Exception {
		boolean isWhite = ChessUtil.isWhite(player);

		List<Location> locList = board.getAllLocationsOfPiece(PAWN, isWhite);

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

					if (loc.getRank() + 1 == target.getRank() && sourceFile == target.getFile()) {
						tmpLoc = loc;
					}

				} else {

					if (loc.getRank() + 1 == target.getRank() && sourceFile == target.getFile()) {
						tmpLoc = loc;
					} else if (loc.getRank() == '2' && loc.getRank() + 2 == target.getRank()
							&& sourceFile == target.getFile()) {
						tmpLoc = loc;
					}

				}

			} else {

				if (isCapture) {

					if (loc.getRank() - 1 == target.getRank() && sourceFile == target.getFile()) {
						tmpLoc = loc;
					}

				} else {

					if (loc.getRank() - 1 == target.getRank() && sourceFile == target.getFile()) {
						tmpLoc = loc;
					} else if (loc.getRank() == '7' && sourceFile == target.getRank()
							&& loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					}

				}
			}
		}
		if (source == null) {
			source = tmpLoc;
		}
		return source;
	}

	private Location fillSource(Board board, Player player, Location target, boolean isCapture) throws Exception {
		boolean isWhite = ChessUtil.isWhite(player);

		List<Location> locList = board.getAllLocationsOfPiece(PAWN, isWhite);

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

					if (loc.getRank() + 1 == target.getRank() && loc.getFile() - 1 == target.getFile()) {
						tmpLoc = loc;
					} else if (loc.getRank() + 1 == target.getRank() && loc.getFile() + 1 == target.getFile()) {
						tmpLoc = loc;
					}

				} else {

					if (loc.getRank() + 1 == target.getRank() && loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					} else if (tmpLoc == null && loc.getRank() == '2' && loc.getRank() + 2 == target.getRank()
							&& loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					}

				}

			} else {

				if (isCapture) {

					if (loc.getRank() - 1 == target.getRank() && loc.getFile() - 1 == target.getFile()) {
						tmpLoc = loc;
					} else if (loc.getRank() - 1 == target.getRank() && loc.getFile() + 1 == target.getFile()) {
						tmpLoc = loc;
					}

				} else {

					if (loc.getRank() - 1 == target.getRank() && loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					} else if (loc.getRank() == '7' && loc.getRank() - 2 == target.getRank()
							&& loc.getFile() == target.getFile()) {
						tmpLoc = loc;
					}

				}
			}
		}
		if (source == null) {
			source = tmpLoc;
		}
		return source;
	}

}

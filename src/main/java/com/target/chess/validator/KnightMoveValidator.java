package com.target.chess.validator;

import java.util.List;

import com.target.chess.exception.MoveException;
import com.target.chess.exception.MoveException.ErrorCode;
import com.target.chess.model.Board;
import com.target.chess.model.Command;
import com.target.chess.model.Location;
import com.target.chess.model.Piece;
import com.target.chess.model.PieceType;
import com.target.chess.model.Player;
import com.target.chess.util.ChessUtil;

public class KnightMoveValidator extends PieceMoveValidator {

	private static final PieceType KNIGHT = PieceType.N;

	@Override
	public Location getSourceLocation(Board board, Player player, Command command) throws Exception {
		Location source = command.getSourceLocation();
		Location target = command.getTargetLocation();
		boolean isCapture = command.isCapture();
		return fillSource(board, player, source, target, isCapture);
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
			if (ChessUtil.isSame(sourceFile, target.getFile())) {
				source = fillSourceWithFile(board, player, sourceFile, target, isCapture);
			}
		} else if (ChessUtil.isEmpty(sourceFile) && !ChessUtil.isEmpty(sourceRank)) {
			if (ChessUtil.isSame(sourceRank, target.getRank())) {
				source = fillSourceWithRank(board, player, sourceRank, target, isCapture);
			}
		} else {
			source = fillSourceWithFileRank(board, player, source, target, isCapture);
		}
		if (source == null || ChessUtil.isEmpty(source.getFile()) || ChessUtil.isEmpty(source.getRank())) {
			throw new MoveException(ErrorCode.INVALIDSOURCE);
		}
		return source;

	}

	private Location fillSourceWithFileRank(Board board, Player player, Location source, Location target,
			boolean isCapture) {
		Piece srcPiece = board.getPieceByLocation(source);
		if (srcPiece.getPieceType() == KNIGHT) {
			int fileDiff = Math.abs(source.getFile() - target.getFile());
			int rankDiff = Math.abs(source.getRank() - target.getRank());
			if (fileDiff == 2 && rankDiff == 1) {
				return source;
			} else if (fileDiff == 1 && rankDiff == 2) {
				return source;
			}
		}
		return null;
	}

	private Location fillSourceWithRank(Board board, Player player, char sourceRank, Location target, boolean isCapture)
			throws Exception {
		boolean isWhite = ChessUtil.isWhite(player);

		List<Location> locList = board.getAllLocationsOfPiece(KNIGHT, isWhite);

		Location source = null;
		Location tmpLoc = null;
		for (Location loc : locList) {
			if (tmpLoc != null) {
				if (source == null) {
					source = tmpLoc;
					tmpLoc = null;
				} else {
					throw new MoveException(ErrorCode.AMBIGIUTY);
				}
			}
			int fileDiff = Math.abs(loc.getFile() - target.getFile());
			int rankDiff = Math.abs(sourceRank - target.getRank());
			if (fileDiff == 2 && rankDiff == 1) {
				tmpLoc = loc;
			} else if (fileDiff == 1 && rankDiff == 2) {
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

		List<Location> locList = board.getAllLocationsOfPiece(KNIGHT, isWhite);

		Location source = null;
		Location tmpLoc = null;
		for (Location loc : locList) {
			if (tmpLoc != null) {
				if (source == null) {
					source = tmpLoc;
					tmpLoc = null;
				} else {
					throw new MoveException(ErrorCode.AMBIGIUTY);
				}
			}
			int fileDiff = Math.abs(sourceFile - target.getFile());
			int rankDiff = Math.abs(loc.getRank() - target.getRank());
			if (fileDiff == 2 && rankDiff == 1) {
				tmpLoc = loc;
			} else if (fileDiff == 1 && rankDiff == 2) {
				tmpLoc = loc;
			}
		}
		if (source == null) {
			source = tmpLoc;
		}
		return source;
	}

	private Location fillSource(Board board, Player player, Location target, boolean isCapture) throws Exception {
		boolean isWhite = ChessUtil.isWhite(player);

		List<Location> locList = board.getAllLocationsOfPiece(KNIGHT, isWhite);

		Location source = null;
		Location tmpLoc = null;
		for (Location loc : locList) {
			if (tmpLoc != null) {
				if (source == null) {
					source = tmpLoc;
					tmpLoc = null;
				} else {
					throw new MoveException(ErrorCode.AMBIGIUTY);
				}
			}
			int fileDiff = Math.abs(loc.getFile() - target.getFile());
			int rankDiff = Math.abs(loc.getRank() - target.getRank());
			if (fileDiff == 2 && rankDiff == 1) {
				tmpLoc = loc;
			} else if (fileDiff == 1 && rankDiff == 2) {
				tmpLoc = loc;
			}
		}
		if (source == null) {
			source = tmpLoc;
		}
		return source;
	}
}

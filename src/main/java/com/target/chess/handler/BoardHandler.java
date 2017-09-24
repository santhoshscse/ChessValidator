package com.target.chess.handler;

import java.util.ArrayList;
import java.util.List;

import com.target.chess.model.Board;
import com.target.chess.model.Location;
import com.target.chess.model.Move;
import com.target.chess.model.Player;
import com.target.chess.util.PlayerUtil;

class BoardHandler {
	private Board board;
	private Player nextPlayer;
	private String castling;
	private Location enPassant;
	private int halfMoveClock;
	private int fullMoveNo;
	private List<Move> moveList;

	public BoardHandler() throws Exception {
		init();
	}

	private void init() throws Exception {
		String str = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String[] strArr = str.split(" ");
		board = new Board(strArr[0]);
		nextPlayer = PlayerUtil.getPlayerType(strArr[1]);
		castling = strArr[2];
		enPassant = getAsLoc(strArr[3]);
		halfMoveClock = Integer.parseInt(strArr[4]);
		fullMoveNo = Integer.parseInt(strArr[5]);
		moveList = new ArrayList<>();
	}

	private Location getAsLoc(String loc) {
		if (!loc.equals("-")) {
			Location enPassantLoc = new Location();
			enPassantLoc.setFile(loc.charAt(0));
			enPassantLoc.setRank(loc.charAt(1));
			return enPassantLoc;
		}
		return null;

	}

	public String display() {
		StringBuilder builder = new StringBuilder();
		builder.append(board.toFen());
		builder.append(" " + PlayerUtil.getPlayerName(nextPlayer));
		builder.append(" " + castling);
		if (enPassant == null) {
			builder.append(" - ");
		} else {
			builder.append(" " + enPassant.getFile() + "" + enPassant.getRank());
		}
		builder.append(" " + halfMoveClock);
		builder.append(" " + fullMoveNo);
		return builder.toString();
	}

	public Board getBoard() throws CloneNotSupportedException {
		return board.clone();
	}

	public void makeMove(Move move) throws Exception {
		makeMoveInBoard(move);
		updateEnPassant(move);
		updateNextPlayer(move);
		updateFullMoveCount(move);
		addMoveToList(move);
	}

	private void addMoveToList(Move move) {
		moveList.add(move);
	}

	private void updateFullMoveCount(Move move) {
		if (move.getPlayer() == Player.B) {
			fullMoveNo++;
		}
	}

	private void updateNextPlayer(Move move) {

		if (move.getPlayer() == Player.W) {
			nextPlayer = Player.B;
		} else {
			nextPlayer = Player.W;
		}
	}

	private void updateEnPassant(Move move) {
		enPassant = move.getEnPassant();
	}

	private void makeMoveInBoard(Move move) {
		board.movePiece(move.getSourceLocation(), move.getTargetLocation(), move.isEnPassantCapture());
	}

	public Player getCurrentPlayer() {
		return nextPlayer;
	}

	public List<Move> getMoveList() {
		return moveList;
	}

	public Location getEnPassant() {
		if (enPassant != null) {
			Location loc = new Location();
			loc.setFile(enPassant.getFile());
			loc.setRank(enPassant.getRank());
			return loc;
		}
		return null;
	}
}

package com.target.chess.handler;

import java.util.ArrayList;
import java.util.List;

import com.target.chess.model.Board;
import com.target.chess.model.Move;
import com.target.chess.model.Player;
import com.target.chess.util.PlayerUtil;

class ChessBoardHandler {
	private Board board;
	private Player nextPlayer;
	private String castling;
	private String enPassant;
	private int halfMoveClock;
	private int fullMoveNo;
	private List<Move> moveList;

	public ChessBoardHandler() throws Exception {
		init();
	}

	private void init() throws Exception {
		String str = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String[] strArr = str.split(" ");
		board = new Board(strArr[0]);
		nextPlayer = PlayerUtil.getPlayerType(strArr[1]);
		castling = strArr[2];
		enPassant = strArr[3];
		halfMoveClock = Integer.parseInt(strArr[4]);
		fullMoveNo = Integer.parseInt(strArr[5]);
		moveList = new ArrayList<>();
	}

	public String display() {
		StringBuilder builder = new StringBuilder();
		builder.append(board.toFen());
		builder.append(" " + PlayerUtil.getPlayerName(nextPlayer));
		builder.append(" " + castling);
		builder.append(" " + enPassant);
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
		board.movePiece(move.getSourceLocation(), move.getTargetLocation());
	}

	public Player getCurrentPlayer() {
		return nextPlayer;
	}

	public List<Move> getMoveList() {
		return moveList;
	}
}

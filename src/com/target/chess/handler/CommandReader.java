package com.target.chess.handler;

import com.target.chess.model.Command;
import com.target.chess.model.Location;
import com.target.chess.model.PieceType;
import com.target.chess.util.CommandUtil;
import com.target.chess.util.PieceUtil;

public class CommandReader {
	public Command getAsMoveCommand(String input) throws Exception {
		if (input.length() <= 1) {
			throw new Exception("Invalid input");
		}
		Object[] res = null;

		res = CommandUtil.getTarget(input);
		input = (String) res[0];
		Location target = (Location) res[1];

		res = CommandUtil.getCapture(input);
		input = (String) res[0];
		boolean isCapture = (boolean) res[1];

		if (input.isEmpty()) {
			return getAsMove(PieceType.P, null, target, isCapture);
		}
		int len = input.length();
		if (len == 1) {
			char firstChar = input.charAt(0);
			if (isPiece(firstChar)) {
				return getAsMove(PieceUtil.getType(firstChar), null, target, isCapture);
			} else if (isFile(firstChar)) {
				Location source = new Location();
				source.setFile(firstChar);
				return getAsMove(PieceType.P, source, target, isCapture);
			} else if (isRank(firstChar)) {
				Location source = new Location();
				source.setRank(firstChar);
				return getAsMove(PieceType.P, source, target, isCapture);
			}
		} else if (len == 2) {
			char firstChar = input.charAt(0);
			if (isPiece(firstChar)) {
				char secondChar = input.charAt(1);
				if (isFile(secondChar)) {
					Location source = new Location();
					source.setFile(secondChar);
					return getAsMove(PieceUtil.getType(firstChar), source, target, isCapture);
				} else if (isRank(secondChar)) {
					Location source = new Location();
					source.setRank(secondChar);
					return getAsMove(PieceUtil.getType(firstChar), source, target, isCapture);
				}
			} else if (isFile(firstChar)) {
				char secondChar = input.charAt(1);
				Location source = new Location();
				source.setFile(firstChar);
				if (isRank(secondChar)) {
					source.setRank(secondChar);
					return getAsMove(PieceType.P, source, target, isCapture);
				}
			}
		} else if (len == 3) {
			char piece = input.charAt(0);
			char fromFile = input.charAt(1);
			char fromRank = input.charAt(2);
			if (isPiece(piece) && isFile(fromFile) && isRank(fromRank)) {
				Location source = new Location();
				source.setFile(fromFile);
				source.setRank(fromRank);
				return getAsMove(PieceUtil.getType(piece), source, target, isCapture);
			}
		}
		return null;
	}

	/**
	 * @param firstChar
	 * @return
	 */
	private static boolean isRank(char firstChar) {
		return firstChar >= '1' && firstChar <= '8';
	}

	/**
	 * @param firstChar
	 * @return
	 */
	private static boolean isFile(char firstChar) {
		return firstChar >= 'a' && firstChar <= 'h';
	}

	/**
	 * @param firstChar
	 * @return
	 */
	private static boolean isPiece(char firstChar) {
		if (firstChar == 'r' || firstChar == 'R') {
			return true;
		} else if (firstChar == 'n' || firstChar == 'N') {
			return true;
		} else if (firstChar == 'b' || firstChar == 'B') {
			return true;
		} else if (firstChar == 'q' || firstChar == 'Q') {
			return true;
		} else if (firstChar == 'k' || firstChar == 'K') {
			return true;
		}
		return false;
	}

	private static Command getAsMove(PieceType pieceType, Location source, Location target, boolean isCapture) {
		Command command = new Command();
		command.setSourcePiece(pieceType);
		command.setSourceLocation(source);
		command.setTargetLocation(target);
		command.setCapture(isCapture);
		return command;
	}
}

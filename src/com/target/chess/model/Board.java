package com.target.chess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.target.chess.util.PieceUtil;

public class Board implements Cloneable {

	private Square[][] elements;
	private HashMap<String, Location> pieceVSLocation;

	public Board(String input) throws Exception {
		init(input);
	}

	private void init(String input) throws Exception {
		elements = new Square[8][8];
		pieceVSLocation = new HashMap<>();
		String[] strArr = input.split("/");
		for (int rankNo = 0; rankNo < 8; rankNo++) {
			String row = strArr[rankNo];
			for (int fileNo = 0; fileNo < row.length(); fileNo++) {
				char piece = row.charAt(fileNo);
				if (piece >= '1' && piece <= '8') {
					int num = piece - '0';
					for (int k = 0; k < num; k++) {
						elements[rankNo][fileNo] = null;
						fileNo++;
					}
				} else {
					char asFile = (char) fileNo;
					char asRank = (char) rankNo;

					String id = piece + "" + asFile;
					Location loc = getAsLocation(asFile, asRank);
					Piece p = getAsPiece(id, piece);

					elements[rankNo][fileNo] = getAsSquare(loc, p);
					pieceVSLocation.put(id, loc);
				}
			}
		}

	}

	private Square getAsSquare(Location loc, Piece p) {
		Square sq = new Square();
		sq.setLocation(loc);
		sq.setPiece(p);
		return sq;
	}

	private Location getAsLocation(char asFile, char asRank) {
		Location loc = new Location();
		loc.setFile(asFile);
		loc.setRank(asRank);
		return loc;
	}

	private Piece getAsPiece(String id, char pieceName) throws Exception {
		Piece piece = new Piece();
		piece.setId(id);
		piece.setName(pieceName);
		piece.setPieceType(PieceUtil.getType(pieceName));
		piece.setWhite(PieceUtil.isWhite(pieceName));
		return piece;
	}

	public String toFen() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int count = 0;
			for (int j = 0; j < 8; j++) {
				char piece = elements[i][j].getPiece().getName();
				if (piece == '-') {
					count++;
				} else {
					if (count != 0) {
						builder.append(count);
						count = 0;
					}
					builder.append(piece);
				}
			}
			if (count != 0) {
				builder.append(count);
				count = 0;
			}

			if (i < 7) {
				builder.append("/");
			}
		}
		return builder.toString();
	}

	public List<Location> getAllLocationsOfPiece(PieceType pieceType, boolean isWhite) {
		List<Location> list = new ArrayList<>();
		char pieceName = PieceUtil.getPieceName(pieceType, isWhite);
		for (char ch = 'a'; ch <= 'h'; ch++) {
			String key = pieceName + "" + ch;
			Location location = pieceVSLocation.get(key);
			if (location != null) {
				list.add(location);
			}
		}
		return list;
	}

	@Override
	public Board clone() throws CloneNotSupportedException {
		try {
			return new Board(this.toFen());
		} catch (Exception e) {
			throw new CloneNotSupportedException("Error in cloning");
		}
	}

	public Piece getPieceByLocation(Location loc) {
		int file = loc.getFile() - 'a';
		int rank = loc.getRank() - '1';
		return elements[rank][file].getPiece();
	}

	public void movePiece(Location sourceLocation, Location targetLocation) {
		int srcFile = sourceLocation.getFile() - 'a';
		int srcRank = sourceLocation.getRank() - '1';

		int tarFile = targetLocation.getFile() - 'a';
		int tarRank = targetLocation.getRank() - '1';

		Piece piece = elements[srcRank][srcFile].getPiece();
		elements[tarRank][tarFile].setPiece(piece);
		elements[srcRank][srcFile].setPiece(null);

		pieceVSLocation.put(piece.getId(), targetLocation);
	}

}

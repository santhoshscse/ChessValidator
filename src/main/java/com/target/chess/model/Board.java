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
			int sLen = row.length();
			int cIndex = 0;
			for (int fileNo = 0; fileNo < 8;) {
				char piece = row.charAt(cIndex++);
				if (piece >= '1' && piece <= '8') {
					int num = piece - '0';
					for (int k = 0; k < num; k++) {

						char asFile = getAsFileChar(fileNo);
						char asRank = getAsRankChar(rankNo);
						Piece p = null;
						Location loc = getAsLocation(asFile, asRank);
						elements[rankNo][fileNo] = getAsSquare(loc, p);
						fileNo++;
					}
				} else {
					char asFile = getAsFileChar(fileNo);
					char asRank = getAsRankChar(rankNo);
					String id = piece + "" + asFile;
					Piece p = getAsPiece(id, piece);
					Location loc = getAsLocation(asFile, asRank);
					elements[rankNo][fileNo] = getAsSquare(loc, p);
					pieceVSLocation.put(id, loc);
					fileNo++;
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
				Piece pieceObj = elements[i][j].getPiece();

				if (pieceObj == null) {
					count++;
				} else {
					char piece = pieceObj.getName();
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

	public Location getLocationOfKing(boolean isWhite) {
		char pieceName = PieceUtil.getPieceName(PieceType.K, isWhite);
		for (char ch = 'a'; ch <= 'h'; ch++) {
			String key = pieceName + "" + ch;
			Location location = pieceVSLocation.get(key);
			if (location != null) {
				return location;
			}
		}
		return null;
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
			e.printStackTrace();
			throw new CloneNotSupportedException("Error in cloning");
		}
	}

	public Piece getPieceByLocation(Location loc) {
		int file = getAsFileNo(loc.getFile());
		int rank = getAsRankNo(loc.getRank());
		return elements[rank][file].getPiece();
	}

	private char getAsRankChar(int rankNo) {
		return (char) (Math.abs(8 - rankNo) + '0');
	}

	private char getAsFileChar(int fileNo) {
		return (char) (fileNo + (int) 'a');
	}

	private int getAsRankNo(char rank) {
		return '8' - rank;
	}

	private int getAsFileNo(char file) {
		return file - 'a';
	}

	public void movePiece(Location sourceLocation, Location targetLocation, boolean isEnPassantCpature) {
		int srcFile = getAsFileNo(sourceLocation.getFile());
		int srcRank = getAsRankNo(sourceLocation.getRank());

		int tarFile = getAsFileNo(targetLocation.getFile());
		int tarRank = getAsRankNo(targetLocation.getRank());

		Piece tarPiece = elements[tarRank][tarFile].getPiece();
		Piece srcPiece = elements[srcRank][srcFile].getPiece();
		elements[tarRank][tarFile].setPiece(srcPiece);
		elements[srcRank][srcFile].setPiece(null);
		pieceVSLocation.put(srcPiece.getId(), targetLocation);

		if (tarPiece == null && isEnPassantCpature) {
			tarPiece = elements[srcRank][tarFile].getPiece();
		}
		if (tarPiece != null) {
			elements[srcRank][tarFile].setPiece(null);
			pieceVSLocation.remove(tarPiece.getId());
		}
	}

}

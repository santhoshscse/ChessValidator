package com.target.chess.model;

public class Piece {
	private char name;
	private PieceType pieceType;
	private boolean isWhite;
	private String id;

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Piece [name=");
		builder.append(name);
		builder.append(", pieceType=");
		builder.append(pieceType);
		builder.append(", isWhite=");
		builder.append(isWhite);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}

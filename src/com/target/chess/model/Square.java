package com.target.chess.model;

public class Square {
	private Location location;
	private Piece piece;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Square [location=");
		builder.append(location);
		builder.append(", piece=");
		builder.append(piece);
		builder.append("]");
		return builder.toString();
	}

}

package com.target.chess.model;

public class Move {
	private boolean isCapture;
	private boolean isEnPassantCapture;
	private Location targetLocation;
	private Location sourceLocation;
	private Piece sourcePiece;
	private Piece targetPiece;
	private Player player;
	private Location enPassant;

	public boolean isCapture() {
		return isCapture;
	}

	public void setCapture(boolean isCapture) {
		this.isCapture = isCapture;
	}

	public boolean isEnPassantCapture() {
		return isEnPassantCapture;
	}

	public void setEnPassantCapture(boolean isEnPassantCapture) {
		this.isEnPassantCapture = isEnPassantCapture;
	}

	public Location getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(Location targetLocation) {
		this.targetLocation = targetLocation;
	}

	public Location getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(Location sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Location getEnPassant() {
		return enPassant;
	}

	public void setEnPassant(Location enPassant) {
		this.enPassant = enPassant;
	}

	public Piece getSourcePiece() {
		return sourcePiece;
	}

	public void setSourcePiece(Piece sourcePiece) {
		this.sourcePiece = sourcePiece;
	}

	public Piece getTargetPiece() {
		return targetPiece;
	}

	public void setTargetPiece(Piece targetPiece) {
		this.targetPiece = targetPiece;
	}

}

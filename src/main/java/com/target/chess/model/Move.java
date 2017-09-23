package com.target.chess.model;

public class Move {
	private boolean isCapture;
	private Location targetLocation;
	private Location sourceLocation;
	private Piece sourcePiece;
	private Piece targetPiece;
	private Player player;
	private String enPassant;

	public boolean isCapture() {
		return isCapture;
	}

	public void setCapture(boolean isCapture) {
		this.isCapture = isCapture;
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

	public String getEnPassant() {
		return enPassant;
	}

	public void setEnPassant(String enPassant) {
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

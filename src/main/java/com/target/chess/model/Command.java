package com.target.chess.model;

public class Command {
	private boolean isCapture;
	private Location targetLocation;
	private Location sourceLocation;
	private PieceType sourcePieceType;

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

	public PieceType getSourcePiece() {
		return sourcePieceType;
	}

	public void setSourcePiece(PieceType sourcePiece) {
		this.sourcePieceType = sourcePiece;
	}

}

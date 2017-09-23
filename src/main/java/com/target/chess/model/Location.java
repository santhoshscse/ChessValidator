package com.target.chess.model;

public class Location {
	private char file;
	private char rank;

	public char getFile() {
		return file;
	}

	public void setFile(char file) {
		this.file = file;
	}

	public char getRank() {
		return rank;
	}

	public void setRank(char rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Location [file=");
		builder.append(file);
		builder.append(", rank=");
		builder.append(rank);
		builder.append("]");
		return builder.toString();
	}

}

package com.target.chess.exception;

public class MoveException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ErrorCode {
		CHECKMOVE, TARGETEMPTY, TARGETNOTEMPTY, INVALIDSOURCE, SAMECATEGORY, INVALIDRANK, INVALIDINPUT, INVALIDFILE, AMBIGIUTY,
	}

	private ErrorCode code;

	public MoveException(ErrorCode code) {
		this.code = code;
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

	public String getMessage() {
		switch (code) {
		case CHECKMOVE:
			return "It will be check for you";
		case TARGETEMPTY:
			return "target is empty to capture or not enPassant capture";
		case TARGETNOTEMPTY:
			return "target is not empty or it must be enPassantCapture";
		case INVALIDSOURCE:
			return "source not found, or in between some other pieces exist";
		case SAMECATEGORY:
			return "source & target pieces are same category";
		case INVALIDINPUT:
			return "Invalid input";
		case INVALIDRANK:
			return "Invalid rank";
		case INVALIDFILE:
			return "Invalid file";

		default:
			return super.getMessage();
		}
	}
}

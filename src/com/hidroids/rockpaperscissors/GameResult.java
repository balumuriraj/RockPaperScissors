package com.hidroids.rockpaperscissors;

import java.io.Serializable;

public class GameResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Choice userChoice;
	private Choice computerChoice;
	private Result gameResult;
	public Choice getUserChoice() {
		return userChoice;
	}
	public void setUserChoice(Choice userChoice) {
		this.userChoice = userChoice;
	}
	public Choice getComputerChoice() {
		return computerChoice;
	}
	public void setComputerChoice(Choice computerChoice) {
		this.computerChoice = computerChoice;
	}
	public Result getGameResult() {
		return gameResult;
	}
	public void setGameResult(Result gameResult) {
		this.gameResult = gameResult;
	}
	@Override
	public String toString() {
		return "GameResult [userChoice=" + userChoice + ", computerChoice="
				+ computerChoice + ", gameResult=" + gameResult + "]";
	}
	
	public enum Choice {
		ROCK, PAPER, SCISSORS
	}
	
	public enum Result {
		WIN, LOSS, DRAW
	}
}

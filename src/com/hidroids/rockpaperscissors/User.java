package com.hidroids.rockpaperscissors;

import java.io.Serializable;

public class User implements Serializable{
	
	private String username;
	private int age;
	private String gender;
	private int won;
	private int lost;
	private int draw;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getWon() {
		return won;
	}
	public void setWon(int won) {
		this.won = won;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + ", gender="
				+ gender + ", won=" + won + ", lost=" + lost + ", draw=" + draw
				+ "]";
	}
	
	
}

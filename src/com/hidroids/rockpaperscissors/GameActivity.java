package com.hidroids.rockpaperscissors;

import com.hidroids.rockpaperscissors.GameResult.Choice;
import com.hidroids.rockpaperscissors.GameResult.Result;

import android.support.v7.app.ActionBarActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GameActivity extends ActionBarActivity implements android.view.View.OnClickListener {

	private GameResult gameResult;
	private User user;
	private UserOperations userdbops;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
		setContentView(R.layout.activity_game);
		
		Button rockButton = (Button) findViewById(R.id.rockButtonId);
		Button paperButton = (Button) findViewById(R.id.paperButtonId);
		Button scissorsButton = (Button) findViewById(R.id.scissorsButtonId);
		
		rockButton.setOnClickListener(this);
		paperButton.setOnClickListener(this);
		scissorsButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		
		gameResult = new GameResult();
		
		switch(v.getId()){
			case R.id.rockButtonId:
				gameResult.setUserChoice(Choice.ROCK);
				break;
			case R.id.paperButtonId:
				gameResult.setUserChoice(Choice.PAPER);
				break;
			case R.id.scissorsButtonId:
				gameResult.setUserChoice(Choice.SCISSORS);
				break;
		}
		
		playGame(gameResult);
	}
	
	public void playGame(GameResult gameResult){
		int random = ((int)(Math.random() * 10)) % 3;
		
		switch(random){
			case 0:
				gameResult.setComputerChoice(Choice.ROCK);
				break;
			case 1:
				gameResult.setComputerChoice(Choice.PAPER);
				break;
			case 2:
				gameResult.setComputerChoice(Choice.SCISSORS);
				break;
		}
		
		if(gameResult.getUserChoice() == gameResult.getComputerChoice()){
			gameResult.setGameResult(Result.DRAW);
			int currentdraws = user.getDraw();
			System.out.println("Current draws: " + currentdraws);
			currentdraws++;
			user.setDraw(currentdraws);
			System.out.println("New draws: " + currentdraws);
		}
		else if(gameResult.getUserChoice() == Choice.SCISSORS && gameResult.getComputerChoice() == Choice.ROCK){
			gameResult.setGameResult(Result.LOSS);
			int currentlost = user.getLost();
			System.out.println("Current loss: " + currentlost);
			currentlost++;
			user.setLost(currentlost);
			System.out.println("new loss: " + currentlost);
		}
		else if(gameResult.getUserChoice() == Choice.ROCK && gameResult.getComputerChoice() == Choice.PAPER){
			gameResult.setGameResult(Result.LOSS);
			int currentlost = user.getLost();
			System.out.println("Current loss: " + currentlost);
			currentlost++;
			user.setLost(currentlost);
			System.out.println("new loss: " + currentlost);
		}
		else if(gameResult.getUserChoice() == Choice.PAPER && gameResult.getComputerChoice() == Choice.SCISSORS){
			gameResult.setGameResult(Result.LOSS);
			int currentlost = user.getLost();
			System.out.println("Current loss: " + currentlost);
			currentlost++;
			user.setLost(currentlost);
			System.out.println("new loss: " + currentlost);
		}
		else {
			gameResult.setGameResult(Result.WIN);
			int currentwon = user.getWon();
			System.out.println("Current win: " + currentwon);
			currentwon++;
			user.setWon(currentwon);
			System.out.println("new win: " + currentwon);
		}	
		
		//store in db
		userdbops = new UserOperations(getBaseContext());
        userdbops.open();
        System.out.println("Before Update: " + user);
		user = userdbops.updateUser(user);
		System.out.println("After Update: " + user);
		userdbops.close();
		
		//show result page
		Intent intent = new Intent(this, ResultActivity.class);
		intent.putExtra("user", user);
		intent.putExtra("gameResult", gameResult);
		startActivity(intent);
	}
}

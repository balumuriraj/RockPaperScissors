package com.hidroids.rockpaperscissors;

import com.hidroids.rockpaperscissors.GameResult.Result;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends ActionBarActivity implements OnClickListener{

	private GameResult gameResult;
	private User user;
	private UserOperations userdbops;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
		gameResult = (GameResult) intent.getSerializableExtra("gameResult");
		setContentView(R.layout.activity_result);
		
		TextView user_choice = (TextView) findViewById(R.id.userview);
		user_choice.setText(gameResult.getUserChoice().toString());
		
		TextView com_choice = (TextView) findViewById(R.id.comview);
		com_choice.setText(gameResult.getComputerChoice().toString());
		
		TextView result_msg = (TextView) findViewById(R.id.resultview);
		if(gameResult.getGameResult() == Result.DRAW){
			result_msg.setText("Its a DRAW!");
		} else {
			result_msg.setText("You " + gameResult.getGameResult().toString() + "!");
		}
		
		TextView won_count = (TextView) findViewById(R.id.wonCount);
		won_count.setText(String.valueOf(user.getWon()));
		
		TextView draw_count = (TextView) findViewById(R.id.drawCount);
		draw_count.setText(String.valueOf(user.getDraw()));
		
		TextView lost_count = (TextView) findViewById(R.id.lossCount);
		lost_count.setText(String.valueOf(user.getLost()));
		
		Button playButton = (Button) findViewById(R.id.playAgainId);
		playButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
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
		if(v.getId() == R.id.playAgainId){
			Intent intent = new Intent(this, GameActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
		}
	}
}

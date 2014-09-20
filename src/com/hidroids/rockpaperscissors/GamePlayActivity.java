package com.hidroids.rockpaperscissors;

import java.io.Serializable;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GamePlayActivity extends ActionBarActivity implements OnClickListener{

	private UserOperations userdbops;
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");		
		setContentView(R.layout.activity_game_play);
		
		TextView  username_text = (TextView) findViewById(R.id.profile_username);
		username_text.setText(user.getUsername());
		
		TextView  age_text = (TextView) findViewById(R.id.profile_age);
		age_text.setText(String.valueOf(user.getAge()));
		
		TextView  gender_text = (TextView) findViewById(R.id.profile_gender);
		gender_text.setText(user.getGender());
		
		TextView  won_text = (TextView) findViewById(R.id.profile_won);
		won_text.setText(String.valueOf(user.getWon()));
		
		TextView  lost_text = (TextView) findViewById(R.id.profile_lost);
		lost_text.setText(String.valueOf(user.getLost()));	
		
		TextView  draw_text = (TextView) findViewById(R.id.profile_draw);
		draw_text.setText(String.valueOf(user.getDraw()));	
		
		Button playButton = (Button) findViewById(R.id.playButtonId);
		playButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_play, menu);
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
		if(v.getId() == R.id.playButtonId){
			Intent intent = new Intent(this, GameActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
		}
	}
}

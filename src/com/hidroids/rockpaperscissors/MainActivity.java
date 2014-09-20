package com.hidroids.rockpaperscissors;

import java.io.Serializable;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
	
	private UserOperations userdbops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    public void submitUser(View view){
    	Intent intent = new Intent(this, GamePlayActivity.class);
    	EditText username_edit = (EditText) findViewById(R.id.usernameId);
    	EditText age_edit = (EditText) findViewById(R.id.ageId); 
    	EditText gender_edit = (EditText) findViewById(R.id.genderId); 
    	
    	if(!username_edit.getText().toString().equals("") && !age_edit.getText().toString().equals("") && !gender_edit.getText().toString().equals("")){
    		
    		String username = username_edit.getText().toString();
        	int age = Integer.parseInt(age_edit.getText().toString());
        	String gender = gender_edit.getText().toString();
    		
	    	userdbops = new UserOperations(getBaseContext());
	        userdbops.open();
	        User user = userdbops.getUser(username);
	        if(user == null){
	        	user = userdbops.insertUser(username, age, gender, 0, 0, 0);
	        }
	    	
	        userdbops.close();
	        
	    	intent.putExtra("user", user);
	    	startActivity(intent);
    	}
    }
}

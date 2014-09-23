package com.hidroids.rockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {
	
	private UserOperations userdbops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Spinner spinner = (Spinner) findViewById(R.id.genderId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_spinner, R.layout.my_spinner_textview);
        adapter.setDropDownViewResource(R.layout.my_spinner_textview);
        spinner.setAdapter(adapter);
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
    	Spinner gender_edit = (Spinner) findViewById(R.id.genderId); 
    	
    	if(!username_edit.getText().toString().equals("") && !age_edit.getText().toString().equals("")
    			&& !gender_edit.getSelectedItem().toString().equals("Select Gender")){
    		
    		String username = username_edit.getText().toString();
        	int age = Integer.parseInt(age_edit.getText().toString());
        	String gender = gender_edit.getSelectedItem().toString();
    		
	    	userdbops = new UserOperations(getBaseContext());
	        userdbops.open();
	        User user = userdbops.getUser(username, age, gender);
	        if(user == null){
	        	user = userdbops.insertUser(username, age, gender, 0, 0, 0);
	        }
	    	
	        userdbops.close();
	        
	    	intent.putExtra("user", user);
	    	startActivity(intent);
    	}
    }
}

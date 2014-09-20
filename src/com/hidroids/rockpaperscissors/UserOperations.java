package com.hidroids.rockpaperscissors;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserOperations {
	
	private DataHandler handler;
	private String[] USER_TABLE_COLUMNS = {DataHandler.USERNAME, DataHandler.AGE, 
			DataHandler.GENDER, DataHandler.WON, DataHandler.LOST, DataHandler.DRAW};
	
	//used for opening the db
	SQLiteDatabase db;
	
	public UserOperations(Context context){
		handler = new DataHandler(context);
	}
	
	public void open(){
		db = handler.getWritableDatabase();
	}
	
	public void close(){
		handler.close();
	}
	
	//insert values into db
	public User insertUser(String username, int age, String gender, int won, int lost, int draw){
		ContentValues content = new ContentValues();
		content.put(DataHandler.USERNAME, username);
		content.put(DataHandler.AGE, age);
		content.put(DataHandler.GENDER, gender);
		content.put(DataHandler.WON, won);
		content.put(DataHandler.LOST, lost);
		content.put(DataHandler.DRAW, draw);
		
		//insert
		Log.d("INSERT", "inserting data..." + DataHandler.TABLE_NAME);
		db.insert(DataHandler.TABLE_NAME, null, content);
		Log.d("INSERT", "inserting data done..." + DataHandler.TABLE_NAME);
		//get
		User user = getUser(username);		
		return user;
	}
	
	//update values in db
		public User updateUser(User user){
			ContentValues content = new ContentValues();
			content.put(DataHandler.USERNAME, user.getUsername());
			content.put(DataHandler.AGE, user.getAge());
			content.put(DataHandler.GENDER, user.getGender());
			content.put(DataHandler.WON, user.getWon());
			content.put(DataHandler.LOST, user.getLost());
			content.put(DataHandler.LOST, user.getDraw());
			
			//update
			Log.d("UPDATE", "updating data..." + DataHandler.TABLE_NAME);
			db.update(DataHandler.TABLE_NAME, content, DataHandler.USERNAME +" = '"+ user.getUsername() +"'", null);
			Log.d("UPDATE", "updating data done..." + DataHandler.TABLE_NAME);
		
			return user;
		}
	
	//retrieving all values from db
	public User getUser(String username){
		Cursor cursor = db.query(DataHandler.TABLE_NAME, USER_TABLE_COLUMNS, DataHandler.USERNAME +" = '"+ username +"'", null, null, null, null);
		
		User user =null;
		
		if(cursor != null && cursor.getCount()>0){
			cursor.moveToFirst();			
			user = parseUser(cursor);
			cursor.close();
		}
		
		return user;
	}
	
	//retrieving all values from db
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		Cursor cursor = db.query(DataHandler.TABLE_NAME, USER_TABLE_COLUMNS, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			User user = parseUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}
		cursor.close();
		return users;
	}
	
	private User parseUser(Cursor cursor){
		User user = new User();
		user.setUsername((cursor.getString(0)));
		user.setAge(cursor.getInt(1));
		user.setGender(cursor.getString(2));
		user.setWon(cursor.getInt(3));
		user.setLost(cursor.getInt(4));
		user.setDraw(cursor.getInt(5));
		return user;
	}
	
}

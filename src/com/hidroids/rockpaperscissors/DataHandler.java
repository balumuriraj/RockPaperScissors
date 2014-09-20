package com.hidroids.rockpaperscissors;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHandler extends SQLiteOpenHelper {
	public static final String USERNAME = "username";
	public static final String AGE = "age";
	public static final String GENDER = "gender";
	public static final String WON = "won";
	public static final String LOST = "lost";
	public static final String DRAW = "draw";
	public static final String TABLE_NAME = "usertable";
	public static final String DATABASE_NAME = "rpcdatabase";
	public static final int DATABASE_VERSION = 3;
	public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
		    + USERNAME + " TEXT NOT NULL, "
		    + AGE + " INTEGER, "
			+ GENDER + " TEXT NOT NULL, "
			+ WON + " INTEGER, "
		    + LOST + " INTEGER, "
		    + DRAW + " INTEGER "
			+ ");";
	
	
	public DataHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create the Users table
		db.beginTransaction();
		try{
			Log.d("CREATE", "Creating table..." + TABLE_NAME);
			db.execSQL(TABLE_CREATE);
			db.setTransactionSuccessful();
		}
		catch(SQLException e){
			Log.e(DataHandler.class.getName(), "Error Creating table " + TABLE_NAME + " ... " + e.getMessage());
			e.printStackTrace();
		}
		finally {
		    db.endTransaction();
		}
	}	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS usertable");
		onCreate(db);
	}	
}

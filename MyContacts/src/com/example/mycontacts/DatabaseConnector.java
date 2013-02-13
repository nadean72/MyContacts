package com.example.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.*;



public class DatabaseConnector {

	private SQLiteDatabase database;
	
	private DatabaseOpenHelper databaseOpenHelper;
	
	public DatabaseConnector(Context context){
		databaseOpenHelper = new DatabaseOpenHelper(context, "MyContacts", null, 1);
	}
	
	public void open() throws SQLException{
		database = databaseOpenHelper.getReadableDatabase();
	}
	
	public void close(){
		if(database != null)
			database.close();
	}
	
	public void insertRow(String name, String address, String cell, String altcell, String email, String comments, int category){
		ContentValues newRow = new ContentValues();
		newRow.put("name", name);
		newRow.put("address", address);
		newRow.put("cell", cell);
		newRow.put("altcell", altcell);
		newRow.put("email", email);
		newRow.put("comments", comments);
		newRow.put("category", category);
		
		open();
		database.insert("Contacts", null, newRow);
		close();
	}
}

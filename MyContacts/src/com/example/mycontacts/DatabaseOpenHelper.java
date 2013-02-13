package com.example.mycontacts;
import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
public class DatabaseOpenHelper extends SQLiteOpenHelper{

	public DatabaseOpenHelper(Context context, String name, CursorFactory factory, int version){
		super(context, name, factory, version);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		String createContacts = "CREATE TABLE Contacts " +
				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"name TEXT, " +
				"address TEXT, " +
				"cell TEXT, " +
				"altcell TEXT, " +
				"email TEXT, " +
				"comments TEXT, " +
				"category INTEGER FOREIGN KEY REFERENCES Categories(id)); ";
		String createCategories = "CREATE TABLE Categories" +
				"(id INTEGER PRIMARY KEY NOT NULL," +
				"name TEXT);";
		String defaultCategories = "INSERT INTO Categories VALUES(0, 'Category1');" +
				"INSERT INTO Categories VALUES(1, 'Category2');" +
				"INSERT INTO Categories VALUES(2, 'Category3');" +
				"INSERT INTO Categories VALUES(3, 'Category4');" +
				"INSERT INTO Categories VALUES(4, 'Category5');";
		db.execSQL(createContacts);
		db.execSQL(createCategories);
		db.execSQL(defaultCategories);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		
	}
}

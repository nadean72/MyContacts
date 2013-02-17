package com.example.mycontacts;

import java.util.ArrayList;

import com.example.mycontacts.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ContactList extends Activity {

	private ArrayList<String> contactNames;
	private ArrayList<Long> contactIds;
	
    
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        
        databaseConnector.open();
        Cursor categories = databaseConnector.getAllCategories();
        if (categories.getCount() == 0){
        	databaseConnector.insertCategory(0, "Category1");
        	databaseConnector.insertCategory(1, "Category2");
        	databaseConnector.insertCategory(2, "Category3");
        	databaseConnector.insertCategory(3, "Category4");
        	databaseConnector.insertCategory(4, "Category5");
        	
        }
        //add a person
        //databaseConnector.insertContact("Charlie", "123 Street St.", "920-123-4567", "920-987-6543", "email@email.com", "Hi", 2);
        
        databaseConnector.close();
        		

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contact_list);
        
    }
    
    protected void onResume(){
    	super.onResume();

        populateContactList();
        //String[] values = new String[] { "John", "Sue", "Frankfurt", "Germany" };
        String[] cats = new String[] { "Cat1", "Cat2", "Cat3", "Cat4", "Cat5", "Edit" };
        //ListView list = (ListView) findViewById(R.id.listView1);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        //list.setAdapter(adapter);
        
        Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cats);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(catAdapter);
        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView parent, View child, int pos, long id){
        		Intent intent = new Intent(getApplicationContext(), ContactView.class);
        		startActivity(intent);
        	}
		});*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	@Override
        	public void onItemSelected(AdapterView parent, View child, int pos, long id){
        		if(parent.getSelectedItemPosition() == 5){
        			parent.setSelection(0);
        			Intent intent = new Intent(getApplicationContext(), EditCategories.class);
        			startActivity(intent);
        		}
        	}
        	
        	@Override
        	public void onNothingSelected(AdapterView parent){
        		
        	}
		});
    }
    
    
    public void populateContactList(){
    	contactNames = new ArrayList<String>();
    	contactIds = new ArrayList<Long>();
    	DatabaseConnector db = new DatabaseConnector(this);
    	db.open();
    	Cursor contacts = db.getAllContacts();
	    if(contacts.getCount() > 0){
	    	contacts.moveToFirst();
	    	boolean pastEnd = false;
	    	do{
	            
	            int nameIndex = contacts.getColumnIndex("name");
	            int idIndex = contacts.getColumnIndex("_id");
	            
	            
	            contactNames.add(contacts.getString(nameIndex));
	            contactIds.add(Long.parseLong(contacts.getString(idIndex)));
	            contacts.moveToNext();
	    		if(contacts.getPosition() == contacts.getCount())
	    			pastEnd = true;
	    	}while(!pastEnd);
	        ListView list = (ListView) findViewById(R.id.contactList);
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, contactNames.toArray(new String[contactNames.size()]));
	        list.setAdapter(adapter);
	        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        	@Override
	        	public void onItemClick(AdapterView parent, View child, int pos, long id){
	        		System.out.println(id);
	        		Intent intent = new Intent(getApplicationContext(), ContactView.class);
	        		intent.putExtra("ID", contactIds.get((int)id));
	        		startActivity(intent);
	        	}
			});
	    }else{
	    	ListView list = (ListView) findViewById(R.id.contactList);
	    	list.setAdapter(null);
	    }
	    contacts.close();
    	db.close();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	if (item.getItemId() == R.id.menu_add_contact){
    		Intent intent = new Intent(getApplicationContext(), ContactView.class);
    		DatabaseConnector database = new DatabaseConnector(this);
    		long id = database.insertContact("New Contact", "", "", "", "", "", 0);
    		intent.putExtra("ID", id);
    		startActivity(intent);
    		return true;
    	}else
    		return super.onOptionsItemSelected(item);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_contact_list, menu);
        return true;
    }
    
}

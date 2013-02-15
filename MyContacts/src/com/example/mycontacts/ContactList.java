package com.example.mycontacts;

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
        //databaseConnector.insertRow("Charlie", "123 Street St.", "920-123-4567", "920-987-6543", "email@email.com", "Hi", 2);
        
        databaseConnector.close();
        		

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contact_list);
        String[] values = new String[] { "John", "Sue", "Frankfurt", "Germany" };
        String[] cats = new String[] { "Cat1", "Cat2", "Cat3", "Cat4", "Cat5", "Edit" };
        ListView list = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        list.setAdapter(adapter);
        
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cats);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(catAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView parent, View child, int pos, long id){
        		Intent intent = new Intent(getApplicationContext(), ContactView.class);
        		startActivity(intent);
        	}
		});
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
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	if (item.getItemId() == R.id.menu_add_contact){
    		Intent intent = new Intent(getApplicationContext(), ContactView.class);
    		DatabaseConnector database = new DatabaseConnector(this);
    		long id = database.insertContact("Name", "Address", "Cell", "Alternate Cell", "Email", "Comments", 0);
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

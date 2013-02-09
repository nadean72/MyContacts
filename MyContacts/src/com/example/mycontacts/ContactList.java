package com.example.mycontacts;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_contact_list, menu);
        return true;
    }
    
}

package com.example.mycontacts;

import com.example.mycontacts.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ContactView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_contact_view);
        String[] cats = new String[] { "Cat1", "Cat2", "Cat3", "Cat4", "Cat5"};
        Spinner spinner = (Spinner) findViewById(R.id.contactCat);
        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cats);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(catAdapter);
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_contact_view, menu);
		return true;
	}

}

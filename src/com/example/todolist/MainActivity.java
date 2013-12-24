package com.example.todolist;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Assign listview
		ListView list =(ListView) findViewById(R.id.main_listview);
		SimpleAdapter listAdapter = this.listviewSetUp();
		list.setAdapter(listAdapter);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * initialize listview in main activity
	 */
	public SimpleAdapter listviewSetUp(){
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		for(int i = 0;i<10;i++){
			HashMap<String,Object> map = new HashMap<String,Object>();
			if(i%2==0)
				map.put("Done", true);
			else
				map.put("Done", false);
			
			map.put("Content", "This is" + i);
			
			listItem.add(map);	
		}
		//ListView Adapter
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem, R.layout.listview01, 
					new String[]{"Done","Content"}, new int[]{R.id.item_checkbox,R.id.item_textview});
		
		return listItemAdapter;
	}
	
}

package com.example.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		TextView addText = (TextView) findViewById(R.id.add_testview);
		ListView addList = (ListView) findViewById(R.id.add_listview);
		Button addButton = (Button) findViewById(R.id.add_button);
		Button doneButton = (Button) findViewById(R.id.done_button);
		
//		ListView Adapter
		final simpleListAdapter thisAdapter =initializeListAdapter();
		addList.setAdapter(thisAdapter);
		
		addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
           	 Toast.makeText(AddActivity.this, "testADD",Toast.LENGTH_SHORT).show();
           	 //test group
           	 HashMap<String,Object> testNewMap = new HashMap<String,Object>();
           	 testNewMap.put("Content", "test");
           	 thisAdapter.addItem(testNewMap);
           	 thisAdapter.notifyDataSetChanged();
//				
//				testNewChild.add(nchildMap);	
//			}
//           	 
//    			listAdapter.addItem(testNewMap,testNewChild);
//    			listAdapter.notifyDataSetChanged();
            }
        });
		
		doneButton.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
	           	 Toast.makeText(AddActivity.this, "testDone",Toast.LENGTH_SHORT).show();
	            }
	        });
		}
	
	public simpleListAdapter  initializeListAdapter(){
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		simpleListAdapter newAdapter = new simpleListAdapter(this, listItem, R.layout.listview01, 
				new String[]{"Content"}, new int[]{R.id.item_textview});;
			
		return newAdapter;
		
	}
	

}


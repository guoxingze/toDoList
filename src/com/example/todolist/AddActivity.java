package com.example.todolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AddActivity extends Activity {
	private simpleListAdapter listData;
	private AlertDialog mPop;
	private String userInput = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final projectDB testDB = new projectDB(this);
	//	printData(testDB);
		
		setContentView(R.layout.activity_add);
		
		System.out.println("add active");
		
		final TextView addText = (TextView) findViewById(R.id.add_testview);
		ListView addList = (ListView) findViewById(R.id.add_listview);
		Button addButton = (Button) findViewById(R.id.add_button);
		Button doneButton = (Button) findViewById(R.id.done_button);
		
//		ListView Adapter
		//final simpleListAdapter thisAdapter =initializeListAdapter();
		simpleListAdapter thisAdapter = this.initializeListAdapter();
		listData = thisAdapter;
		addList.setAdapter(listData);
		
		addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//test popup 
            	dialogShow();
            }
        });
		
		doneButton.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
            	 //generate a intent
            	 Intent addIntent = new Intent();
            	 HashMap<String,Object> listGroup = new HashMap<String,Object>();
            	 ArrayList<HashMap<String,Object>> listChild;
        		
            	 //group
            	 listGroup.put("gContent", addText.getText().toString());
            	 listGroup.put("gCheck", "false");
         		System.out.println("~~~~~~~~~~~text start~~~~~~~~~~~");
         		System.out.println(addText.getText());
         		System.out.println("~~~~~~~~~text end~~~~~~~~~~~~~~");
            	 //child
            	 listChild = (ArrayList<HashMap<String, Object>>) listData.getConponent();
 
        		testDB.insert(listGroup, listChild);
        		System.out.println("~~~~~~~~~~~DB test start~~~~~~~~~~~");
        		printData(testDB);
        		System.out.println("~~~~~~~~~DB test end~~~~~~~~~~~~~~");
        			
            	 addIntent.setClass(AddActivity.this, MainActivity.class);
            	 AddActivity.this.startActivity(addIntent);
            	 
	            }
	        });
		}
	
	public simpleListAdapter  initializeListAdapter(){
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		simpleListAdapter newAdapter = new simpleListAdapter(this, listItem, R.layout.listview01, 
				new String[]{"cContent"}, new int[]{R.id.item_textview});;
			
		return newAdapter;
		
	}
	
	
	//////////////
	public void printData(projectDB testDB){
		Cursor cursor = testDB.select(); 
        if(cursor.moveToFirst()){  
            System.out.println("The List In DB:"+cursor.getCount());  
            do{  
                System.out.println(cursor.getString(0)+cursor.getString(1)+cursor.getString(2));                  
            }while(cursor.moveToNext());  
        }  
        cursor.close();
		
	}

	
	public void dialogShow(){
		LayoutInflater inflater = LayoutInflater.from(this);  
		final View view = inflater.inflate(R.layout.add_popup, null);
		//create diaglo
		 AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		 builder.setTitle("Input");
		 builder.setView(view);  
		 builder.setPositiveButton("Add", new OnClickListener()
		 {  
			  
	            @Override  
	            public void onClick(DialogInterface dialog, int which)  
	            {  
	                // get textview from xml
	                EditText inputText = (EditText) view.findViewById(R.id.testview_addpopup);  
	 
	                // get value  
	                userInput = inputText.getText().toString(); 
	        //        listData.a
	                System.out.println(userInput);
	                HashMap<String,Object> newAdd = new HashMap<String,Object>();
	                newAdd.put("cContent",userInput);
	                newAdd.put("cCheck","false");
	                listData.addItem(newAdd);
	                listData.notifyDataSetChanged();
	            }  
	        }); 
		 builder.setNegativeButton("Cancel", new OnClickListener()  
	        {  
	  
	            @Override  
	            public void onClick(DialogInterface dialog, int which)  
	            {  
	                dialog.dismiss();  
	            }  
	        });	 
		 
		 builder.show(); 

	}
}


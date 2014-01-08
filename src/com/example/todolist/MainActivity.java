package com.example.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//initial Database
		projectDB testDB = new projectDB(this);
		
		
		setContentView(R.layout.activity_main);
		Intent returnValue =getIntent();
		ArrayList<HashMap<String,Object>> addedData = (ArrayList<HashMap<String,Object>>)returnValue.getSerializableExtra("newData");
		
		//Assign listview
		ExpandableListView list =(ExpandableListView) findViewById(R.id.main_exlistview);
		Button newButton = (Button) findViewById(R.id.button1);
		final main_listAdapter listAdapter = this.listviewSetUp();
		list.setAdapter(listAdapter);
		
		//test intent
		if(addedData!=null){
			   	 HashMap<String,Object> testNewMap = new HashMap<String,Object>();
			   	 testNewMap.put("gCheck", true);
			   	 testNewMap.put("gContent", "test");
				listAdapter.addItem(testNewMap, addedData);
		}
		else {
			System.out.println("This is main");
			printData(testDB);
			System.out.println("main over");
		}
		
		
		//test finished
//		list.expandGroup(0);   //test expand
		
		//set listener
		//list
		list.setOnChildClickListener(new OnChildClickListener(){

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "group" + groupPosition + "child" + childPosition, Toast.LENGTH_SHORT).show();
                return false;

			}			
		});


		//button
		newButton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 //generate a intent
            	 Intent addIntent = new Intent();
            	 addIntent.setClass(MainActivity.this, AddActivity.class);
            	 MainActivity.this.startActivity(addIntent);
             }
         });
		
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
	public main_listAdapter listviewSetUp(){
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
//		for(int i = 0;i<3;i++){
//			HashMap<String,Object> map = new HashMap<String,Object>();
//			if(i%2==0)
//				map.put("gCheck", true);
//			else
//				map.put("gCheck", false);
//			
//			map.put("gContent", "This is" + i);
//			
//			listItem.add(map);	
//		}
		//Child
		ArrayList<ArrayList<HashMap<String,Object>>> childs = new ArrayList<ArrayList<HashMap<String,Object>>>();
//		for(int i =0;i<3;i++){
//			ArrayList<HashMap<String,Object>> subCHild = new ArrayList<HashMap<String,Object>>();
//			for(int j =0; j<3;j++){
//				HashMap<String,Object> childMap = new HashMap<String,Object>();
//				if(j%2==0)
//					childMap.put("cCheck", true);
//				else
//					childMap.put("cCheck", false);
//				
//				childMap.put("cContent", "This is" + j);
//				
//				subCHild.add(childMap);	
//			}
//			childs.add(subCHild);
//		}
		
//		 /**
//         * 使用SimpleExpandableListAdapter显示ExpandableListView
//         * 参数1.上下文对象Context
//         * 参数2.一级条目目录集合
//         * 参数3.一级条目对应的布局文件
//         * 参数4.fromto，就是map中的key，指定要显示的对象
//         * 参数5.与参数4对应，指定要显示在groups中的id
//         * 参数6.二级条目目录集合
//         * 参数7.二级条目对应的布局文件
//         * 参数8.fromto，就是map中的key，指定要显示的对象
//         * 参数9.与参数8对应，指定要显示在childs中的id
//         */
//		ListView Adapter
//		SimpleExpandableListAdapter  listItemAdapter = new SimpleExpandableListAdapter(this, listItem, R.layout.listview01, 
//					new String[]{"Done","Content"}, new int[]{R.id.item_checkbox,R.id.item_textview});
		
		main_listAdapter  listItemAdapter = new main_listAdapter(this,
				listItem,
				R.layout.parentlistview_main,
				new String[]{"gCheck","gContent"}, 
				new int[]{R.id.parentListView_checkbox_main,R.id.parentListView_textview_main},
				childs,
				R.layout.childlistview_main,
				new String[]{"cCheck","cContent"},
				new int[]{R.id.childListView_checkbox_main,R.id.childListView_textview_main});
		
		return listItemAdapter;
	}
	
	public void testAddButton(){
   	 Toast.makeText(MainActivity.this, "test",Toast.LENGTH_SHORT).show();
   	 //test group
   	 HashMap<String,Object> testNewMap = new HashMap<String,Object>();
   	 testNewMap.put("gCheck", true);
   	 testNewMap.put("gContent", "test");
   	 
   	 //test child
   	 ArrayList<HashMap<String,Object>> testNewChild = new ArrayList<HashMap<String,Object>>();
		for(int j =0; j<3;j++){
		HashMap<String,Object> nchildMap = new HashMap<String,Object>();
		if(j%2==0)
			nchildMap.put("cCheck", true);
		else
			nchildMap.put("cCheck", false);
		
		nchildMap.put("cContent", "testChild");
		
		testNewChild.add(nchildMap);	
	}
   	 
//		listAdapter.addItem(testNewMap,testNewChild);
//		listAdapter.notifyDataSetChanged();
	}
	
	public void interToDB( HashMap<String,Object> group, ArrayList<HashMap<String,Object>> child){
		
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
}

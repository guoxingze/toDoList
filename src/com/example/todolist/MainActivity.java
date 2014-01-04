package com.example.todolist;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
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
		setContentView(R.layout.activity_main);
		
		//Assign listview
		ExpandableListView list =(ExpandableListView) findViewById(R.id.main_exlistview);
		Button newButton = (Button) findViewById(R.id.button1);
		final main_listAdapter listAdapter = this.listviewSetUp();
		list.setAdapter(listAdapter);
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
            	 Toast.makeText(MainActivity.this, "test",Toast.LENGTH_SHORT).show();
            	 //test group
            	 HashMap<String,Object> testNewMap = new HashMap<String,Object>();
            	 testNewMap.put("PDone", true);
            	 testNewMap.put("PContent", "test");
            	 
            	 //test child
            	 ArrayList<HashMap<String,Object>> testNewChild = new ArrayList<HashMap<String,Object>>();
     			for(int j =0; j<3;j++){
 				HashMap<String,Object> nchildMap = new HashMap<String,Object>();
 				if(j%2==0)
 					nchildMap.put("CDone", true);
 				else
 					nchildMap.put("CDone", false);
 				
 				nchildMap.put("CContent", "testChild");
 				
 				testNewChild.add(nchildMap);	
 			}
            	 
     			listAdapter.addItem(testNewMap,testNewChild);
     			listAdapter.notifyDataSetChanged();
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
		for(int i = 0;i<3;i++){
			HashMap<String,Object> map = new HashMap<String,Object>();
			if(i%2==0)
				map.put("PDone", true);
			else
				map.put("PDone", false);
			
			map.put("PContent", "This is" + i);
			
			listItem.add(map);	
		}
		//Child
		ArrayList<ArrayList<HashMap<String,Object>>> childs = new ArrayList<ArrayList<HashMap<String,Object>>>();
		for(int i =0;i<3;i++){
			ArrayList<HashMap<String,Object>> subCHild = new ArrayList<HashMap<String,Object>>();
//			for(int j =0; j<3;j++){
//				HashMap<String,Object> childMap = new HashMap<String,Object>();
//				if(j%2==0)
//					childMap.put("CDone", true);
//				else
//					childMap.put("CDone", false);
//				
//				childMap.put("CContent", "This is" + j);
//				
//				subCHild.add(childMap);	
//			}
			childs.add(subCHild);
		}
		
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
				new String[]{"PDone","PContent"}, 
				new int[]{R.id.parentListView_checkbox_main,R.id.parentListView_textview_main},
				childs,
				R.layout.childlistview_main,
				new String[]{"CDone","CContent"},
				new int[]{R.id.childListView_checkbox_main,R.id.childListView_textview_main});
		
//		SimpleExpandableListAdapter  listItemAdapter = new SimpleExpandableListAdapter(this,
//				listItem,
//				R.layout.parentlistview_main,
//				new String[]{"PContent"}, 
//				new int[]{R.id.parentListView_textview_main},
//				childs,
//				R.layout.childlistview_main,
//				new String[]{"CContent","CDone"},
//				new int[]{R.id.childListView_textview_main,R.id.childListView_checkbox_main});
		return listItemAdapter;
	}
	
}

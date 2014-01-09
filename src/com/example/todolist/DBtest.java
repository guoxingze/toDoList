package com.example.todolist;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

public class DBtest extends Activity{
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		projectDB testDB = new projectDB(this);
		
		///////////////////////////////////////
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
			
			nchildMap.put("cContent", "testChild"+ j);
			
			testNewChild.add(nchildMap);
		
	}
//			testDB.insert(testNewMap, testNewChild);
//			printData(testDB);
//			testDB.readParent();
			String t1 = "2010-01-20 20:20:20";
			String t2 = "2011-01-20 20:20:20";
			String t3 = "2011-02-20 20:20:20";
			String t4 = "2011-02-26 20:20:20";
			String t5 = "2011-02-26 21:20:20";
			String t6 = "2011-02-26 21:21:20";
			String t7 = "2011-02-26 21:21:21";
			testDB.insertTime(t3);
			testDB.insertTime(t1);
			testDB.insertTime(t5);
			testDB.insertTime(t4);
			testDB.insertTime(t2);
			testDB.insertTime(t7);
			testDB.insertTime(t6);
			testDB.sortTime();
	}
	
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

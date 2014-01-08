package com.example.todolist;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class projectDB extends SQLiteOpenHelper{
    private final static String DATABASE_NAME = "list01";  
    private final static int DATABASE_VERSION = 1;  
    private final static String TABLE_NAME = "list_table";  
    public final static String LIST_ID = "list_id";  
    public final static String LIST_CHECK = "list_check";  
    public final static String LIST_PARENT = "list_parent";  
    public final static String LIST_CONTENT = "list_content";  
    
 
    
    
	public projectDB(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);	
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("DB start");
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+LIST_PARENT +" TEXT, "
				+LIST_CHECK +" TEXT, "
				+LIST_CONTENT+ " TEXT);";
//		String sql = "CREATE TABLE "+DATABASE_NAME+" ( "+LIST_PARENT +" TEXT, "+ LIST_CHECK +" TEXT );";
				db.execSQL(sql);
				System.out.println("DB created");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("DB upgrade");
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME; 
		db.execSQL(sql);
		onCreate(db);
		
	}
	
    public Cursor select() {  
        SQLiteDatabase db = this.getReadableDatabase();  
        Cursor cursor = db  
                .query(TABLE_NAME, null, null, null, null, null, null);  
        return cursor;  
    }  
    
    //inter 
    public void insert(HashMap<String,Object> group,ArrayList<HashMap<String,Object>> child) {
    	System.out.println("DB start 1");
       SQLiteDatabase db = this.getWritableDatabase();  
       System.out.println("DB start 2");
       /* ContentValues */  
       //parent
       String parentContent = (String) group.get("gContent");
       String parentCheck = String.valueOf(group.get("gCheck"));
       ContentValues parent = new ContentValues();
       
       parent.put(LIST_CONTENT, parentContent);
       parent.put(LIST_CHECK, parentCheck);  
       parent.put(LIST_PARENT, "null");  
       db.insert(TABLE_NAME, null, parent);  

       //child
       int count = child.size();
       for(int i = 0;i<count;i++){
    	   HashMap<String,Object> tempChild = child.get(i);
           String childContent = (String) tempChild.get("cContent");
           String childtCheck = String.valueOf(tempChild.get("cCheck"));
           ContentValues childIn = new ContentValues();
           
           childIn.put(LIST_CONTENT, childContent);
           childIn.put(LIST_CHECK, childtCheck);  
           childIn.put(LIST_PARENT, parentContent);  
           db.insert(TABLE_NAME, null, childIn);  
           
       }
       db.close();
     } 
    
    //delete
    public void delete(String[] name){
        String where = LIST_CONTENT+" = ?";  
        String[] whereValues = name;   
        this.getWritableDatabase().delete(TABLE_NAME, where, whereValues);  
        this.getWritableDatabase().close();  
        
        this.getWritableDatabase().close();
    }
	
}

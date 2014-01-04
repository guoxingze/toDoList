package com.example.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class main_listAdapter extends SimpleExpandableListAdapter{
	
	private String[] childFrom;
	private int[] childTo;
	private ArrayList<ArrayList<HashMap<String,Object>>> childData;
	
	private ArrayList<HashMap<String,Object>> groupData;
    private String[] groupFrom;
    private int[] groupTo;
	
	public main_listAdapter(Context context,
			ArrayList<HashMap<String,Object>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			ArrayList<ArrayList<HashMap<String,Object>>> childData,
			int childLayout, int lastChildLayout, String[] childFrom,
			int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, lastChildLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		
		this.childFrom = childFrom;
		this.childTo = childTo;
		this.childData = childData;
		this.groupData = groupData;
		this.groupFrom = groupFrom;
		this.groupTo = groupTo;
	}
	
	

	public main_listAdapter(Context context,
			ArrayList<HashMap<String,Object>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			ArrayList<ArrayList<HashMap<String,Object>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		
		this.childFrom = childFrom;
		this.childTo = childTo;
		this.childData = childData;
		this.groupData = groupData;
		this.groupFrom = groupFrom;
		this.groupTo = groupTo;
	}



	public main_listAdapter(Context context,
			ArrayList<HashMap<String,Object>> groupData, int groupLayout,
			String[] groupFrom, int[] groupTo,
			ArrayList<ArrayList<HashMap<String,Object>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, groupLayout, groupFrom, groupTo, childData,
				childLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		
		this.childFrom = childFrom;
		this.childTo = childTo;
		this.childData = childData;
		this.groupData = groupData;
		this.groupFrom = groupFrom;
		this.groupTo = groupTo;
	}



	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = newChildView(isLastChild, parent);
        } else {
            v = convertView;
        }
        cbindView(v,childData.get(groupPosition).get(childPosition), childFrom, childTo);
        return v;
	}
	
	
	
    @Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        View v;
        if (convertView == null) {
            v = newGroupView(isExpanded, parent);
        } else {
            v = convertView;
        }
        cbindView(v, groupData.get(groupPosition), groupFrom, groupTo);
        return v;
	}



	private void cbindView(View view, Map<String, ?> data, String[] from, int[] to) {
        int len = to.length;
        for (int i = 0; i < len; i++) {
            View v = view.findViewById(to[i]);
            final Object fData = data.get(from[i]);
            String text = data == null ? "" : String.valueOf(data.get(from[i]));
            
            if (v instanceof Checkable) {
                if (fData instanceof Boolean) {
                    ((Checkable) v).setChecked((Boolean) fData);
                } else if (v instanceof TextView) {
                    // Note: keep the instanceof TextView check at the bottom of these
                    // ifs since a lot of views are TextViews (e.g. CheckBoxes).
                    setViewText((TextView) v, text);
                }
            }
            else if (v instanceof TextView){
            	setViewText((TextView) v, text);
            }
            ////////////////////////////
            
//            if (v instanceof Checkable) {
//                if (data.get(from[i]) instanceof Boolean) {
//                    ((Checkable) v).setChecked((Boolean) data.get(from[i]));
//                } else if (v instanceof TextView) {
//                    // Note: keep the instanceof TextView check at the bottom of these
//                    // ifs since a lot of views are TextViews (e.g. CheckBoxes).
//                    setViewText((TextView) v, (String)data.get(from[i]));
//                }
//            }
//            else if (v instanceof TextView){
//            	setViewText((TextView) v, (String)data.get(from[i]));
//            }
            
            ////////////////////////////
        }
        
    }
    
    /**
     * Called by bindView() to set the text for a TextView but only if
     * there is no existing ViewBinder or if the existing ViewBinder cannot
     * handle binding to a TextView.
     *
     * @param v TextView to receive text
     * @param text the text to be set for the TextView
     */
    public void setViewText(TextView v, String text) {
        v.setText(text);
    }
    
    
    public void addItem(HashMap<String,Object> newGroup,ArrayList<HashMap<String,Object>> newChild){
    	groupData.add(newGroup);
    	childData.add(newChild);
    	
    }

}

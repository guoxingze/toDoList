package com.example.todolist;

import android.app.DatePickerDialog;
import android.content.Context;

public class myDatePicker extends DatePickerDialog{


	public myDatePicker(Context context, int theme, OnDateSetListener callBack,
			int year, int monthOfYear, int dayOfMonth) {
		super(context, theme, callBack, year, monthOfYear, dayOfMonth);
		// TODO Auto-generated constructor stub
	}

	public myDatePicker(Context context, OnDateSetListener callBack, int year,
			int monthOfYear, int dayOfMonth) {
		super(context, callBack, year, monthOfYear, dayOfMonth);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
//		super.onStop();
	}

	
}

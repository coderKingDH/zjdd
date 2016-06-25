package com.it.zjdd.pop;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.it.zjdd.R;


public class WaitDialog extends Dialog{

	public WaitDialog(Context context) {
		super(context, R.style.MyPopStyle);
		// TODO Auto-generated constructor stub
	}

	public WaitDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dlog_wait);
	}
}

/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */ 

package com.evolution515.adw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.evolution515.adw.R;

public class main extends Activity implements OnItemClickListener {
	private static final String ACTION_ADW_PICK_ICON="org.adw.launcher.icons.ACTION_PICK_ICON";
	private boolean mPickerMode=false;
	// Called when the activity is first created.
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int iconSize=getResources().getDimensionPixelSize(android.R.dimen.app_icon_size);
		setContentView(R.layout.main);
		GridView g=(GridView) findViewById(R.id.icon_grid);
		g.setNumColumns(GridView.AUTO_FIT);
		g.setColumnWidth(iconSize);
		g.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
		g.setVerticalSpacing(iconSize/3);
		g.setOnItemClickListener(this);
		if(getIntent().getAction().equals(ACTION_ADW_PICK_ICON)){
			mPickerMode=true;
		}
	}
	
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		if(mPickerMode){
			Intent intent=new Intent();
			Bitmap bitmap=null;
			try{
				bitmap=(Bitmap) adapter.getAdapter().getItem(position);
			}catch (Exception e) {
			}
			if(bitmap!=null){
				intent.putExtra("icon",bitmap);
				setResult(RESULT_OK, intent);
			}else{
				setResult(RESULT_CANCELED, intent);
			}
			finish();
		}
	}
}

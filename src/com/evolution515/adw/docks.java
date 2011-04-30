/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */ 

package com.evolution515.adw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;

public class docks extends Activity implements AdapterView.OnItemClickListener {
	public Uri CONTENT_URI;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView lv=new ListView(this);
		lv.setAdapter(new DocksAdapter(this));
		lv.setOnItemClickListener(this);
		setContentView(lv);
		CONTENT_URI=Uri.parse("content://"+docksProvider.class.getCanonicalName());
	}

	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		String dock=adapterView.getItemAtPosition(i).toString();
		Intent result = new Intent(null, Uri.withAppendedPath(CONTENT_URI,dock));
		setResult(RESULT_OK, result);
		finish();
	}
	
	private class DocksAdapter extends BaseAdapter {
		
		private Context mContext;
		public DocksAdapter(Context mContext) {
			super();
			this.mContext = mContext;
			loadDocks();
		}

		public int getCount() {
			return mThumbs.size();
		}

		public Object getItem(int position) {
			return mThumbs.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView=new ImageView(docks.this);
				convertView.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.FILL_PARENT, ListView.LayoutParams.WRAP_CONTENT));
			}
			((ImageView)convertView).setImageResource(mThumbs.get(position));
			return convertView;
		}

		private ArrayList<Integer> mThumbs;

		private void loadDocks() {
			mThumbs = new ArrayList<Integer>();

			final Resources resources = getResources();
			final String packageName = getApplication().getPackageName();

			addDocks(resources, packageName, R.array.dock_pack);
		}
		private void addDocks(Resources resources, String packageName, int list) {
			final String[] extras = resources.getStringArray(list);
			for (String extra : extras) {
				int res = resources.getIdentifier(extra, "drawable", packageName);
				if (res != 0) {
					final int thumbRes = resources.getIdentifier(extra,"drawable", packageName);
					if (thumbRes != 0) {
						mThumbs.add(thumbRes);
					}
				}
			}
		}
	}
}

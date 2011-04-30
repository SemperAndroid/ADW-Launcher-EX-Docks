/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */ 

package com.evolution515.adw;

import java.io.FileNotFoundException;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;


/**
 * Created by Gustavo Claramunt.
 * User: adw
 * Date: 23/01/11
 * Time: 18:31
 */
public class docksProvider extends ContentProvider {
	
	@Override
	public boolean onCreate() {
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
		return null;
	}

	@Override
	public String getType(Uri uri) {
		return "image/*";
	}

	@Override
	public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
		if(uri.getPathSegments().size()>0){
			try{
				int resId=Integer.valueOf(uri.getPathSegments().get(0));
				return this.getContext().getResources().openRawResourceFd(resId);
			}catch (Throwable t){
				return null;
			}
		}else{
			return null;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues contentValues) {
		return null;
	}

	@Override
	public int delete(Uri uri, String s, String[] strings) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
		return 0;
	}
}

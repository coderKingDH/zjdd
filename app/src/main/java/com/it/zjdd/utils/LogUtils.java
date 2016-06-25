package com.it.zjdd.utils;

import android.util.Log;

public class LogUtils {
	private static boolean isDemo=true;
	public static boolean isTest=true;
	public static void loge(String tag,String msg){
		if(isDemo){
			Log.e(tag, msg);
		}else{
			
		}
	}
}

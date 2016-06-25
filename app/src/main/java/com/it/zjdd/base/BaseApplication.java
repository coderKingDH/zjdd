package com.it.zjdd.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import com.it.zjdd.utils.TLog;


public class BaseApplication extends Application {
	private String tag = BaseApplication.class.getName();
	private static String PREF_NAME = "creativelocker.pref";//配置文件名
	private static String LAST_REFRESH_TIME = "last_refresh_time.pref";

	private static Context _context = null;
	static Resources _resource;

	private static String lastToast = "";
	private static long lastToastTime;

	private static boolean sIsAtLeastGB;
	static {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) { // android》2.3
			sIsAtLeastGB = true;
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		TLog.e(tag, " wdemo app is init!!!");

		_context = this;
		_resource = _context.getResources();

	}

	public static synchronized BaseApplication context() {
		return (BaseApplication) _context;
	}

	public static Resources resources() {
		return _resource;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static void apply(Editor editor) {
		if (sIsAtLeastGB) {
			editor.apply();
		} else {
			editor.commit();
		}
	}

	public static void set(String key, boolean value) {
		Editor editor = getPreferences().edit();
		editor.putBoolean(key, value);
		apply(editor);
	}

	public static void set(String key, String value) {
		Editor editor = getPreferences().edit();
		editor.putString(key, value);
		apply(editor);
	}

	public static boolean get(String key, boolean defValue) {
		return getPreferences().getBoolean(key, defValue);
	}

	public static String get(String key, String defValue) {
		return getPreferences().getString(key, defValue);
	}

	public static int get(String key, int defValue) {
		return getPreferences().getInt(key, defValue);
	}

	public static long get(String key, long defValue) {
		return getPreferences().getLong(key, defValue);
	}

	public static float get(String key, float defValue) {
		return getPreferences().getFloat(key, defValue);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static SharedPreferences getPreferences() {
		SharedPreferences pre = context().getSharedPreferences(PREF_NAME,
				Context.MODE_MULTI_PROCESS);
		return pre;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static SharedPreferences getPreferences(String prefName) {
		return context().getSharedPreferences(prefName,
				Context.MODE_MULTI_PROCESS);
	}

	public static int[] getDisplaySize() {
		return new int[] { getPreferences().getInt("screen_width", 480),
				getPreferences().getInt("screen_height", 854) };
	}

	public static void saveDisplaySize(Activity activity) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		Editor editor = getPreferences().edit();
		editor.putInt("screen_width", displaymetrics.widthPixels);
		editor.putInt("screen_height", displaymetrics.heightPixels);
		editor.putFloat("density", displaymetrics.density);
		editor.commit();
	}

	public static String string(int id) {
		return _resource.getString(id);
	}

	public static String string(int id, Object... args) {
		return _resource.getString(id, args);
	}


}

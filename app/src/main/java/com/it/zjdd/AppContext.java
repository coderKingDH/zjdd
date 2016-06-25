package com.it.zjdd;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Log;


import com.it.zjdd.api.ApiHttpClient;
import com.it.zjdd.base.BaseApplication;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import java.net.CookieStore;
import java.util.Properties;
import java.util.UUID;

/**
 * 作用于全局的操作的context
 * 
 * @author Administrator
 *
 */
public class AppContext extends BaseApplication {
	private static String TAG =AppContext.class.getName();
	private static AppContext instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		initHttp();
	}
	//初始化 async http
	private void initHttp() {
		try {
			AsyncHttpClient client = new AsyncHttpClient(); 
//			PersistentCookieStore myCookieStore = new PersistentCookieStore(this);

//			client.setCookieStore(myCookieStore);

			ApiHttpClient.setHttpClient(client); 
//			ApiHttpClient.setCookie(ApiHttpClient.getCookie(this));
			
		} catch (Exception e) {
			Log.e(TAG, "initHttp 出错！");
		}
	}

	/**
	 * 获取App唯一标识
	 * 
	 * @return
	 */
	public String getAppId() {
		String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
		if (TextUtils.isEmpty(uniqueID)) {
			uniqueID = UUID.randomUUID().toString();
			setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
		}
		return uniqueID;
	}

	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	/**
	 * 获得当前app运行的AppContext
	 * 
	 * @return
	 */
	public static AppContext getInstance() {
		return instance;
	}

	// 读取后 看时候包含这个key
	public boolean containsProperty(String key) {
		Properties props = getProperties();
		return props.containsKey(key);
	}

	public void setProperties(Properties ps) {
		AppConfig.getAppConfig(this).set(ps);
	}

	// 读取配置文件
	public Properties getProperties() {
		return AppConfig.getAppConfig(this).get();
	}

	public void setProperty(String key, String value) {
		AppConfig.getAppConfig(this).set(key, value);
	}

	/**
	 * 获取cookie时传AppConfig.CONF_COOKIE
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		String res = AppConfig.getAppConfig(this).get(key);
		return res;
	}

	public void removeProperty(String... key) {
		AppConfig.getAppConfig(this).remove(key);
	}

}

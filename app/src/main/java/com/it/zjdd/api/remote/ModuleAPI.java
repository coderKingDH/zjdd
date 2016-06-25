package com.it.zjdd.api.remote;


import com.it.zjdd.api.ApiHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 类名称：ModuleAPI.java <br>
 * 内容摘要： //模块api。<br>
 * 修改备注： <br>
 * 创建时间： 2016-4-7下午5:27:52<br>
 * 公司：baseWin<br>
 * 
 * @author Administrator<br>
 */
public class ModuleAPI {

	/**
	 * 方法名：  checkUpdate	<br>
	 * 方法描述：TODO( 获取版本信息 )<br>
	 * 修改备注：<br>
	 * 创建时间： 2016-4-7下午5:28:47<br>
	 * @param handler
	 */
	public static void checkUpdate(AsyncHttpResponseHandler handler) {
//		ApiHttpClient.get("MobileAppVersion.xml", handler);
		ApiHttpClient.getHttpClient().get("http://www.oschina.net/MobileAppVersion.xml",handler);
	}
	public static void UpdateApk(AsyncHttpResponseHandler handler){
		//uploads/osc-android-app-2.5.5-release.apk
		ApiHttpClient.get("uploads/osc-android-app-2.5.5-release.apk", handler);
	}
}

package com.it.zjdd.api;

import java.util.Locale;



import android.content.Context;
import android.util.Log;


import com.it.zjdd.AppContext;
import com.it.zjdd.utils.LogUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ApiHttpClient {
	private static final String TAG = ApiHttpClient.class.getName();

	public final static String HOST = "http://112.74.196.203:8080/";
	//
	// private static String API_URL = "http://112.74.196.203:8080/Gem/%s";

	private static String API_URL = "http://www.oschina.net/%s";

	public static final String DELETE = "DELETE";
	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String PUT = "PUT";
	public static AsyncHttpClient client;

	public ApiHttpClient() {
	}

	public static AsyncHttpClient getHttpClient() {
		return client;
	}

	public static void cancelAll(Context context) {
		client.cancelRequests(context, true);
	}

	public static void setHttpClient(AsyncHttpClient c) {
		LogUtils.loge(TAG, "asyncHttp 初始化！！！");
		client = c;
//		client.addHeader("Accept-Language", Locale.getDefault().toString());
		// client.addHeader("Host", HOST);

//		client.addHeader("Connection", "Keep-Alive");
//		client.getHttpClient().getParams()
//				.setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);

//		setUserAgent(ApiClientHelper.getUserAgent(AppContext.getInstance()));
	}

	// 设置一个用户手机信息
	public static void setUserAgent(String userAgent) {
		client.setUserAgent(userAgent);
	}

	public static void setCookie(String cookie) {
		client.addHeader("Cookie", cookie);
	}

	// app 的cookie
	private static String appCookie;

	public static void cleanCookie() {
		appCookie = "";
	}

	public static String getCookie(AppContext appContext) {
		if (appCookie == null || appCookie == "") {
			appCookie = appContext.getProperty("cookie");
		}
		return appCookie;
	}

	/**
	 * 获取一个绝对路径 相当于： api_url+parturl
	 * 
	 * @param partUrl
	 * @return
	 */
	public static String getAbsoluteApiUrl(String partUrl) {
		String url = String.format(API_URL, partUrl);
		Log.e("BASE_CLIENT", "request:" + url);
		return url;
	}

	public static String getApiUrl() {
		return API_URL;
	}

	public static void get(String partUrl, AsyncHttpResponseHandler handler) {
		client.get(getAbsoluteApiUrl(partUrl), handler);
		log(new StringBuilder("GET ").append(partUrl).toString());
	}

	public static void get(String partUrl, RequestParams params,
			AsyncHttpResponseHandler handler) {
		client.get(getAbsoluteApiUrl(partUrl), params, handler);
		log(new StringBuilder("GET ").append(partUrl).append("&")
				.append(params).toString());
	}

	public static void post(String partUrl, AsyncHttpResponseHandler handler) {
		client.post(getAbsoluteApiUrl(partUrl), handler);
		log(new StringBuilder("POST ").append(partUrl).toString());
	}

	public static void post(String partUrl, RequestParams params,
			AsyncHttpResponseHandler handler) {
		client.post(getAbsoluteApiUrl(partUrl), params, handler);
		log(new StringBuilder("POST ").append(getAbsoluteApiUrl(partUrl))
				.append("&").append(params).toString());
	}

	public static void postDirect(String url, RequestParams params,
			AsyncHttpResponseHandler handler) {
		client.post(url, params, handler);
		log(new StringBuilder("POST ").append(url).append("&").append(params)
				.toString());
	}

	public static void put(String partUrl, AsyncHttpResponseHandler handler) {
		client.put(getAbsoluteApiUrl(partUrl), handler);
		log(new StringBuilder("PUT ").append(partUrl).toString());
	}

	public static void put(String partUrl, RequestParams params,
			AsyncHttpResponseHandler handler) {
		client.put(getAbsoluteApiUrl(partUrl), params, handler);
		log(new StringBuilder("PUT ").append(partUrl).append("&")
				.append(params).toString());
	}

	public static void clearUserCookies(Context context) {
		// (new HttpClientCookieStore(context)).a();
	}

	public static void delete(String partUrl, AsyncHttpResponseHandler handler) {
		client.delete(getAbsoluteApiUrl(partUrl), handler);
		log(new StringBuilder("DELETE ").append(partUrl).toString());
	}

	public static void setApiUrl(String apiUrl) {
		API_URL = apiUrl;
	}

	public static void log(String log) {
		Log.e("BaseApi client", log);
	}

}

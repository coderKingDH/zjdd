package com.it.zjdd.api.remote;



import android.util.Log;

import com.it.zjdd.api.ApiHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
/**
 * 这个包下是分模块获取数据的
 * 
 * @author Administrator
 *
 */
public class WdhApi {
	protected static final String TAG = WdhApi.class.getName();

	/**
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 * @param handler
	 */
	public static void login(String username, String password,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("username", username);
		params.put("pwd", password);
		params.put("keep_login", 1);
		String loginurl = "action/api/login_validate";
		ApiHttpClient.post(loginurl, params, handler);
	}

	public static void demo() {
		RequestParams requestParams = new RequestParams();
		requestParams.put("id", "112486");
		requestParams.put("secret", "6a020c3c922eb088801988086852db7b");
		requestParams.put("com", "shentong");
		requestParams.put("nu", "229431907288");
		requestParams.put("type", "json");
		requestParams.put("ord", "desc");
		requestParams.put("encode", "utf8");
		requestParams.put("ver", "2");

		ApiHttpClient.post("http://api.ickd.cn/", requestParams,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						Log.e(TAG, "onSuccess" + new String(arg2));

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						Log.e(TAG, "onFailure" + arg3.toString());

					}
				});
	}

	public static void GetCat() {
		// http://112.74.196.203:8080/Gem/shopping/queryShoppingCat?pageNumber=0&pageNumber=20&uid=1
		RequestParams requestParams = new RequestParams();
		requestParams.put("pageNumber", 0);
		requestParams.put("pageCount", 20);
		requestParams.put("uid", 1);
		ApiHttpClient.post("shopping/queryShoppingCat", requestParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						Log.e(TAG, "onFailure");
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						Log.e(TAG, "onSuccess" + new String(arg2).toString());

					}

				});
	}
}

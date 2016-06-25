package com.it.zjdd;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	protected static final String TAG = MainActivity.class.getName();
	private WebView webView;
	private ImageView iv_wait;
	private LinearLayout w;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			w.setVisibility(View.GONE);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.wv);
		w = (LinearLayout) findViewById(R.id.lin_laoding);
		iv_wait = (ImageView) findViewById(R.id.iv_wait);

		AnimationDrawable animationDrawable = (AnimationDrawable) iv_wait
				.getBackground();
		animationDrawable.start();
		initWB();
	}

	private void initWB() {
		//

		CookieSyncManager.createInstance(MainActivity.this);
		CookieManager instance = CookieManager.getInstance();

		// w = new WaitDialog(this);

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);// 设置js可以直接打开窗口，如window.open()，默认为false
		webSettings.setJavaScriptEnabled(true);// 是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
		webSettings.setSupportZoom(true);// 是否可以缩放，默认true
		webSettings.setBuiltInZoomControls(true);// 是否显示缩放按钮，默认false
		webSettings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放。大视图模式
		webSettings.setLoadWithOverviewMode(true);// 和setUseWideViewPort(true)一起解决网页自适应问题
		webSettings.setAppCacheEnabled(true);// 是否使用缓存
		webSettings.setDomStorageEnabled(true);// DOM Storage
		// webSettings.setUserAgentString("User-Agent:Android");//设置用户代理，一般不用
		//
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.e(TAG, "访问"+url+"之前");
				CookieManager cookieManager = CookieManager.getInstance();
//				cookieManager.removeAllCookie();
				view.loadUrl(url);
				return true;
			}

			// onPageStarted
			// onPageFinished
			@Override
			public void onPageFinished(WebView view, String url) {
				Log.e(TAG, "访问"+url+"之后");
				if (w != null && w.getVisibility() == View.VISIBLE) {
					w.setVisibility(View.GONE);
				}

				CookieManager cookieManager = CookieManager.getInstance();
				String CookieStr = cookieManager.getCookie(url);
				if(CookieStr!=null){
					Log.e("sunzn", "Cookies = " + CookieStr);
				}else{
					Log.e("sunzn", "Cookies = is null " );
				}
				// super.onPageFinished(view, url);
			}
			
			

		});
		
		// CookieSyncManager createInstance =
		// CookieSyncManager.createInstance(this);
		// createInstance.setAcceptCookie(true);
		String url = "";
		url = "http://www.xyrr.com/mobile/Index?from=groupmessage&isappinstalled=0";
		// url="http://113.11.213.156/index.php"; //最新
		webView.loadUrl(url);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();// 返回前一个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


}

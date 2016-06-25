package com.it.zjdd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.it.zjdd.ui.DemoActivity;

public class appStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.app_start, null);
		setContentView(view);
		// 渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(1.0f, 1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
	}

	private void redirectTo() {
//		Intent uploadLog = new Intent(this, LogUploadService.class);
//		startService(uploadLog);
		Intent intent = new Intent(this, DemoActivity.class);
		startActivity(intent);
		finish();

	}
}

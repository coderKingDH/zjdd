package com.it.zjdd.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;


import com.it.zjdd.AppContext;
import com.it.zjdd.api.remote.ModuleAPI;
import com.it.zjdd.base.BaseApplication;
import com.it.zjdd.bean.Update;
import com.it.zjdd.ui.dialog.DialogHelper;
import com.it.zjdd.ui.dialog.WaitDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


/**
 * 更新管理类
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年11月18日 下午4:21:00
 * 
 */

public class UpdateManager {

	private Update mUpdate; //更新数据的实体类

	private Context mContext;
	
	private boolean isShow = false;
	
	private WaitDialog _waitDialog;

	private AsyncHttpResponseHandler mCheckUpdateHandle = new AsyncHttpResponseHandler() {

		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							  Throwable arg3) {
			Log.e("","onFailure");
			hideCheckDialog();
			if (isShow) {
				showFaileDialog();
			}
		}

		@Override
		public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
			Log.e("","onSuccess");
			hideCheckDialog();
			//获取 更新code 数据 并解析
			mUpdate=new Update();
			onFinshCheck();
		}
	};

	public UpdateManager(Context context, boolean isShow) {
		this.mContext = context;
		this.isShow = isShow;
	}
	//判断是否需要更新
	public boolean haveNew() {
		if (this.mUpdate == null) {
			return false;
		}
		boolean haveNew = false;
		int curVersionCode = TDevice.getVersionCode(AppContext
				.getInstance().getPackageName());
		if (curVersionCode < mUpdate.getVersionCode()) {
			haveNew = true;
		}
		return haveNew;
	}
	//访问网络 得到更新数据
	public void checkUpdate() {
		if (isShow) {
			showCheckDialog();
		}
		ModuleAPI.checkUpdate(mCheckUpdateHandle);
	}
	
	private void onFinshCheck() {
		//判断是否有哦新版本
		if (haveNew()) {
			Log.e("","发现了新版本");
			showUpdateInfo();
		} else {
			Log.e("","没有发现了新版本");
			if (isShow) {
				showLatestDialog();
			}
		}
	}

	private void showCheckDialog() {
		DialogHelper.getWaitDialog(mContext,"正在获取新版本信息...").show();
		t("正在获取新版本信息...");
	}

	private void hideCheckDialog() {
		if (_waitDialog != null) {
			_waitDialog.dismiss();
		}
	}
	
	private void showUpdateInfo() {
		if (mUpdate == null) {
			return;
		}
//		CommonDialog dialog = DialogHelper.getPinterestDialogCancelable(mContext);
//		dialog.setTitle("发现新版本");
//		dialog.setMessage(mUpdate.getUpdate().getAndroid().getUpdateLog());
//		dialog.setNegativeButton(R.string.cancle, null);
//		dialog.setPositiveButton("更新版本", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				UIHelper.openDownLoadService(mContext, mUpdate.getUpdate().getAndroid().getDownloadUrl(), mUpdate.getUpdate().getAndroid().getVersionName());
//				dialog.dismiss();
//			}
//		});
//		dialog.show();
		AlertDialog.Builder confirmDialog = DialogHelper.getConfirmDialog(mContext, "发现新版本,是否更新?", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UIHelper.openDownLoadService(mContext, mUpdate.getDownloadUrl(), mUpdate.getVersionName());
			}
		});
		confirmDialog.show();
	}
	
	private void showLatestDialog() {
//		CommonDialog dialog = DialogHelper.getPinterestDialogCancelable(mContext);
//		dialog.setMessage("已经是最新版本了");
//		dialog.setPositiveButton("", null);
//		dialog.show();
		DialogHelper.getMessageDialog(mContext,"已经是最新版本了").show();
//		t("已经是最新版本了");
	}
	
	private void showFaileDialog() {
//		CommonDialog dialog = DialogHelper.getPinterestDialogCancelable(mContext);
//		dialog.setMessage("网络异常，无法获取新版本信息");
//		dialog.setPositiveButton("", null);
//		dialog.show();
		DialogHelper.getMessageDialog(mContext,"网络异常，无法获取新版本信息").show();
//		t("网络异常，无法获取新版本信息");
	}
	private void t(String text){
//		Toast.makeText(BaseApplication.context(), text, 0).show();
	}
}

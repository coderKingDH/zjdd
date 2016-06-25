package com.it.zjdd.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.it.zjdd.interf.ICallbackResult;
import com.it.zjdd.service.DownloadService;


public class UIHelper {

	 //开启下载服务
    public static void openDownLoadService(Context context, String downurl,
            String tilte) {
    	//创建一个自定义的回调  这里就设置为 如果服务 开启了  的回调
        final ICallbackResult callback = new ICallbackResult() {

            @Override
            public void OnBackResult(Object s) {}
        };
        ServiceConnection conn = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {}

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
                binder.addCallback(callback);
                binder.start();

            }
        };
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, downurl);
        intent.putExtra(DownloadService.BUNDLE_KEY_TITLE, tilte);
        context.startService(intent);
        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

}

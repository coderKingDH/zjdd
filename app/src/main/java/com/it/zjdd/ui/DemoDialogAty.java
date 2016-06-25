package com.it.zjdd.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.it.zjdd.R;
import com.it.zjdd.ui.dialog.DialogHelper;
import com.it.zjdd.ui.dialog.WaitDialog;
import com.it.zjdd.utils.UpdateManager;

/**
 * Created by wang03989 on 2016/6/11.
 */
public class DemoDialogAty extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        new UpdateManager(this,true).checkUpdate();
    }
}

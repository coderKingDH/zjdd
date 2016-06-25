package com.it.zjdd.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.it.zjdd.MainActivity;
import com.it.zjdd.R;
import com.it.zjdd.base.BaseBarActivity;

public class DemoActivity extends BaseBarActivity {
    private ListView lv_demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("你这骚猪！");
        super.onCreate(savedInstanceState);
        setTitle("你这骚猪2！");
        setContentView(R.layout.activity_demo);
        AlertDialog.Builder builder = new AlertDialog.Builder (this);

        lv_demo = (ListView) findViewById(R.id.lv_demo);
        lv_demo.setAdapter(new ArrayAdapter<String>(this, R.layout.item_text, R.id.tv_item, new String[]{"webView", "Dialog", "3", "4", "5", "6", "7", "7",}));
        lv_demo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class c=null;
                switch (position) {
                    case 0:
                        c=MainActivity.class;
                        break;
                    case 1:
                        c=DemoDialogAty.class;
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                }
                if(c!=null){
                    Intent intent = new Intent(DemoActivity.this, c);
                    startActivity(intent);
                }
            }
        });
    }
}

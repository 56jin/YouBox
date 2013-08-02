package com.youbox.activity;

import com.youbox.util.plug.MsgDialog;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * 设置Activity
 * @author zhongdaxia
 *
 */

public class SettingActivity extends Activity {

	RelativeLayout btn_account;
	RelativeLayout btn_nodesearch;
	RelativeLayout btn_advice;
	RelativeLayout btn_about;
	ImageButton btn_jmp_main;
	RelativeLayout btn_contact;
	RelativeLayout btn_call;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		 IntentFilter filter = new IntentFilter();
         filter.addAction("finish");
         registerReceiver(mFinishReceiver, filter);
		FindView();
		SetClick();
	}
	private BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	           if("finish".equals(intent.getAction())) {
	              finish();
	       }
	    }
	};
	private void FindView() {
		btn_account = (RelativeLayout) findViewById(R.id.btn_account);
		btn_nodesearch = (RelativeLayout) findViewById(R.id.btn_nodesearch);
		btn_advice = (RelativeLayout) findViewById(R.id.btn_advice);
		btn_about = (RelativeLayout) findViewById(R.id.btn_about);
		btn_jmp_main = (ImageButton) findViewById(R.id.btn_jmp_main);
		btn_contact = (RelativeLayout) findViewById(R.id.btn_contact);
		btn_call = (RelativeLayout) findViewById(R.id.btn_call);
	}

	private void SetClick() {
		btn_jmp_main.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SettingActivity.this.finish();
			}
		});
		btn_contact.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MsgDialog msgDialog = new MsgDialog(v.getContext(),"提示","未开放");
				msgDialog.CreateDialog(0);
			}
		});
		btn_call.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MsgDialog msgDialog = new MsgDialog(v.getContext(),"提示","未开放");
				msgDialog.CreateDialog(0);
			}
		});
		btn_account.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this, AccountActivity.class);
				SettingActivity.this.startActivity(intent);
			}
		});
		btn_nodesearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MsgDialog msgDialog = new MsgDialog(SettingActivity.this,"提示","未开放");
				msgDialog.CreateDialog(0);
			/*	Intent intent = new Intent();
				intent.setClass(SettingActivity.this, NodeActivity.class);
				SettingActivity.this.finish();
				SettingActivity.this.startActivity(intent);*/
			}
		});
		btn_advice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this, AdviceActivity.class);
				SettingActivity.this.startActivity(intent);
			}
		});
		btn_about.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this, AboutActivity.class);
				SettingActivity.this.startActivity(intent);
			}
		});

	}
}
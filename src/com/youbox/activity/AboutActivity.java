package com.youbox.activity;

import com.youbox.db.DefaultData;
import com.youbox.util.plug.MsgDialog;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 关于Activity
 * @author zhongdaxia
 *
 */

public class AboutActivity extends Activity {
	Button btn_userprotocol ;
	ImageButton btn_jmp_setting;
	TextView tv_version;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IntentFilter filter = new IntentFilter();
        filter.addAction("finish");
        registerReceiver(mFinishReceiver, filter);

		setContentView(R.layout.activity_about);
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
		btn_userprotocol = (Button)findViewById(R.id.btn_userprotocol);
		btn_jmp_setting = (ImageButton)findViewById(R.id.btn_jmp_setting);
		tv_version = (TextView) findViewById(R.id.tv_version);
		if(!DefaultData.STRING_CURRENT_VERSION.equals(""))
		{
			tv_version.setText("版本" + DefaultData.STRING_CURRENT_VERSION);
		}
	}

	private void SetClick() {
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AboutActivity.this.finish();
			}
		});
		// 跳转到用户协议
		btn_userprotocol.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MsgDialog msgDialog = new MsgDialog(v.getContext(),"提示","未开放");
				msgDialog.CreateDialog(0);
				
			/*	Intent intent = new Intent();
				intent.setClass(AboutActivity.this, ProtocolActivity.class);
				AboutActivity.this.startActivity(intent);*/
			}
		});
	}

}
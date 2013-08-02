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
import android.widget.Button;
import android.widget.ImageButton;

/**
 * 建议Activity
 * @author zhongdaxia
 *
 */

public class AdviceActivity extends Activity {
	ImageButton btn_jmp_setting;
	Button btn_sumbit;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice);
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
		btn_jmp_setting = (ImageButton) findViewById(R.id.btn_jmp_setting);
		btn_sumbit = (Button) findViewById(R.id.btn_sumbit);
	}

	private void SetClick() {
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AdviceActivity.this.finish();
			}
		});
		btn_sumbit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MsgDialog msgDialog = new MsgDialog(v.getContext(),"提示","未开放");
				msgDialog.CreateDialog(0);
			}
		});
	}

}
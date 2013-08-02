package com.youbox.activity;

import com.youbox.db.DefaultData;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * ’À∫≈π‹¿ÌActivity
 * @author zhongdaxia
 *
 */

public class AccountActivity extends Activity {

	ImageButton btn_jmp_setting;
	Button btn_logoff;
	EditText et_username;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
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
		btn_logoff = (Button) findViewById(R.id.btn_logoff);
		et_username = (EditText) findViewById(R.id.et_username);
		et_username.setText(DefaultData.STRING_USERNAME);
	}

	private void SetClick() {
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AccountActivity.this.finish();
			}
		});
		// ÕÀ≥ˆ’À∫≈
		btn_logoff.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					SharedPreferences sharedPrefrences;
					Editor editor;
					sharedPrefrences = v.getContext().getSharedPreferences(DefaultData.FILENAME, 1);
					editor =  v.getContext().getSharedPreferences(DefaultData.FILENAME,1).edit();
					editor.putString("username", "");
					editor.putString("password", "");
					editor.commit();
					DefaultData.STRING_USERNAME = "";
					
					Intent intent = new Intent();
					intent.setClass(AccountActivity.this, LoginActivity.class);
					getApplicationContext().sendBroadcast(new Intent("finish"));
					intent.putExtra("IsLastActivity", true);
					AccountActivity.this.finish();
					AccountActivity.this.startActivity(intent);
			}
		});
	}

}
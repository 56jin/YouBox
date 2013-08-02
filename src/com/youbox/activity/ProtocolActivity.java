package com.youbox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * 用户协议Activity
 * @author zhongdaxia
 *
 */

public class ProtocolActivity extends Activity {

	ImageButton btn_jmp_about;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_protocol);
		FindView();
		SetClick();
	}

	private void FindView() {
		btn_jmp_about = (ImageButton)findViewById(R.id.btn_jmp_about);
	}

	private void SetClick() {
		btn_jmp_about.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				ProtocolActivity.this.finish();
			}
		});
	}

}
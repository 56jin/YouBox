package com.youbox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * ÍøµãActivity
 * @author zhongdaxia
 *
 */

public class NodeActivity extends Activity {

	ImageButton btn_jmp_setting;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
//		FindView();
//		SetClick();
	}

	private void FindView() {
		btn_jmp_setting = (ImageButton) findViewById(R.id.btn_jmp_setting);
	}

	private void SetClick() {
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NodeActivity.this.finish();
			}
		});
	}

}
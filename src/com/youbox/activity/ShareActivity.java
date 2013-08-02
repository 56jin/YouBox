package com.youbox.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * ∑÷œÌœ¬‘ÿActivity
 * @author zhongdaxia
 *
 */

public class ShareActivity extends Activity
{
	Button comment_back;
	ImageButton btn_jmp_setting;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		findView();
		setClick();
	}

	private void findView() {
		btn_jmp_setting = (ImageButton)findViewById(R.id.btn_jmp_setting);
	}

	private void setClick() {
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity) v.getContext()).finish();
			}
		});
	}
}
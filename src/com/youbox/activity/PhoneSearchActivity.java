package com.youbox.activity;

import com.youbox.util.net.HttpActionController; 
import com.youbox.util.plug.MsgDialog;
import com.youbox.util.plug.PopuDialog;
import android.app.Activity; 
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 手机号码搜索快件TabActivity
 * @author zhongdaxia
 *
 */

public class PhoneSearchActivity extends Activity { 

	EditText et_phone;
	Button btn_phonesearch;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 IntentFilter filter = new IntentFilter();
         filter.addAction("finish");
         registerReceiver(mFinishReceiver, filter);
		setContentView(R.layout.tab_phonesearch);

		findView();
		setClick();

	}
	private BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	           if("finish".equals(intent.getAction())) {
	              finish();
	       }
	    }
	};
	private void findView() {
		et_phone = (EditText) findViewById(R.id.et_phone);
		btn_phonesearch = (Button) findViewById(R.id.btn_phonesearch);
	}

	private void setClick() {
		btn_phonesearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String phoneString = et_phone.getText().toString();
				if(phoneString.equals("") || (phoneString.length() != 11) )
				{
					MsgDialog msgDialog = new MsgDialog(v.getContext(), "提示", "请输出正确手机号");
					msgDialog.CreateDialog(0);
					return;
				}

				ProgressDialog proDialog = ProgressDialog.show(v.getContext(), "", "正在获取数据……", true, true);
				proDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				HttpActionController httpActionController = new HttpActionController(PhoneSearchActivity.this,proDialog);
				httpActionController.packageDetail(phoneString, "json");
			}
		});
	}

	// 返回键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			PopuDialog popuDialog = new PopuDialog(this);
			popuDialog.CreateDialog();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
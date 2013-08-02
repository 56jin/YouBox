package com.youbox.activity;

import com.youbox.db.DefaultData;
import com.youbox.util.net.HttpActionController;
import com.youbox.util.plug.MsgDialog;
import com.youbox.util.plug.PopuDialog;
import com.youbox.util.plug.UpdateDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * 登录Activity
 * @author zhongdaxia
 *
 */

public class LoginActivity extends Activity {

	ImageButton btn_jmp_setting;
	Button btn_forgetpsw;
	Button btn_login;
	Button btn_regedit;
	EditText et_username;
	EditText et_password;
	CheckBox checkBox_autologin;
	
	private boolean IsLastActivity = false; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		 IntentFilter filter = new IntentFilter();
         filter.addAction("finish");
         registerReceiver(mFinishReceiver, filter);
         
 		if(!DefaultData.STRING_versioninfo_version.equals(""))
 		{
 			
 			SharedPreferences sharedPrefrences;
 			sharedPrefrences = LoginActivity.this.getSharedPreferences(DefaultData.FILENAME, MODE_WORLD_READABLE);
 			String ignoreversion= sharedPrefrences.getString("ignoreversion", "");
 			if(!ignoreversion.equals(""))
 			{
 				if(ignoreversion.equals(DefaultData.STRING_versioninfo_version))
 				{
 					
 				}
 				else {

// 					DefaultData.STRING_versioninfo_url = "http://down.angeeks.com/c/d2/d10112/10112281.apk";
// 					DefaultData.STRING_versioninfo_url = "http://192.168.1.116:9997/Views/YouBox.apk";
 					// 非必须 可以取消提示
 					if(DefaultData.STRING_versioninfo_action.equals("0"))
 					{
 						UpdateDialog updateDialog = new UpdateDialog(LoginActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
 						updateDialog.CreateWouldDialog();
 					}
 					// 非必须一直提示
 					else if(DefaultData.STRING_versioninfo_action.equals("1"))
 					{
 						UpdateDialog updateDialog = new UpdateDialog(LoginActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
 						updateDialog.CreateCanDialog();
 					}
 					// 必须
 					else if(DefaultData.STRING_versioninfo_action.equals("2"))
 					{
 						UpdateDialog updateDialog = new UpdateDialog(LoginActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
 						updateDialog.CreateMustDialog();
 					}
 				}
 			}
 			else {

// 				DefaultData.STRING_versioninfo_url = "http://192.168.1.116:9997/Views/YouBox.apk";
// 				DefaultData.STRING_versioninfo_url = "http://down.angeeks.com/c/d2/d10112/10112281.apk";
 				// 非必须 可以取消提示
 				if(DefaultData.STRING_versioninfo_action.equals("0"))
 				{
 					UpdateDialog updateDialog = new UpdateDialog(LoginActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
 					updateDialog.CreateWouldDialog();
 				}
 				// 非必须一直提示
 				else if(DefaultData.STRING_versioninfo_action.equals("1"))
 				{
 					UpdateDialog updateDialog = new UpdateDialog(LoginActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
 					updateDialog.CreateCanDialog();
 				}
 				// 必须
 				else if(DefaultData.STRING_versioninfo_action.equals("2"))
 				{
 					UpdateDialog updateDialog = new UpdateDialog(LoginActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
 					updateDialog.CreateMustDialog();
 				}
 			}
 		}
         
         Bundle extras = getIntent().getExtras();
         if(extras!= null)
         IsLastActivity = extras.getBoolean("IsLastActivity", false);
         
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
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_forgetpsw = (Button) findViewById(R.id.btn_forgetpsw);
		btn_regedit = (Button) findViewById(R.id.btn_regedit);
		checkBox_autologin = (CheckBox) findViewById(R.id.checkBox_autologin);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
	}

	private void SetClick() {
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PopuDialog popuDialog = new PopuDialog(v.getContext());
				popuDialog.CreateDialog();
			}
		});
		// 登录
		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String usernameString = et_username.getText().toString();
				String passwordString = et_password.getText().toString();
				if(usernameString.equals(""))
				{
					et_username.requestFocus();
					return;
				}
				else if(passwordString.equals(""))
				{
					et_password.requestFocus();
					return;
				}
				ProgressDialog proDialog = ProgressDialog.show(v.getContext(), "", "正在获取数据……", true, true);
				HttpActionController httpActionController = new HttpActionController(LoginActivity.this,proDialog);
				// 自动登录
				if(checkBox_autologin.isChecked())
				{
					httpActionController.login(usernameString, passwordString, "json" ,true);
				}
				else {
					httpActionController.login(usernameString, passwordString, "json" ,false);
				}
			
			}
		});
		// 忘记密码
		btn_forgetpsw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MsgDialog popuDialog = new MsgDialog(v.getContext(),"提示","未开放");
				popuDialog.CreateDialog(0);
			}
		});
		// 注册
		btn_regedit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MsgDialog popuDialog = new MsgDialog(v.getContext(),"提示","未开放");
				popuDialog.CreateDialog(0);
			}
		});			
	}
	// 返回键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && IsLastActivity) {
			PopuDialog popuDialog = new PopuDialog(this);
			popuDialog.CreateDialog();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
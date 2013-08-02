package com.youbox.activity;

import com.youbox.db.DefaultData;
import com.youbox.util.net.HttpActionController;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

/**
 * 欢迎界面 
 * 
 * @author zhongdaxia
 * 
 */

public class WelcomeActivity extends Activity {
	private String VersionNameString ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		try {
			VersionNameString = getVersionName();
			HttpActionController httpActionController = new HttpActionController(WelcomeActivity.this);
		    httpActionController.getVersion(VersionNameString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				SharedPreferences sharedPrefrences;
				sharedPrefrences = WelcomeActivity.this.getSharedPreferences(DefaultData.FILENAME, MODE_WORLD_READABLE);
				String username= sharedPrefrences.getString("username", "");
				String password= sharedPrefrences.getString("password", "");
				if((!username.equals("")) && (!password.equals("")))
				{
					ProgressDialog proDialog = ProgressDialog.show(WelcomeActivity.this, "", "正在获取数据……", true, true);
					HttpActionController httpActionController = new HttpActionController(WelcomeActivity.this,proDialog);
				    httpActionController.login(username, password, "json" ,true);
				}
				else
				{
					Intent intent = new Intent();
					intent.setClass(WelcomeActivity.this, LoginActivity.class);
					getApplicationContext().sendBroadcast(new Intent("finish"));
					intent.putExtra("IsLastActivity", true);
					WelcomeActivity.this.finish();
					WelcomeActivity.this.startActivity(intent);
//					startActivity(new Intent(getApplication(), LoginActivity.class));
//					WelcomeActivity.this.finish();
				}
			}
		}, 2000);
	}

	/**
	 * 获取版本号
	 * @return 版本号 - AndroidManifest.xml 中 android:versionName 
	 * @throws Exception
	 */
	private String getVersionName() throws Exception{  
	    PackageManager packageManager = getPackageManager();  
	    PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);  
	    DefaultData.STRING_CURRENT_VERSION = packInfo.versionName;
	    return packInfo.versionName;   
	}  

	// 返回键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			System.exit(0);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
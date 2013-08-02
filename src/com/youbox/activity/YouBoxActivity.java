package com.youbox.activity;

import com.youbox.db.DefaultData;
import com.youbox.util.plug.UpdateDialog;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 主Activity
 * @author zhongdaxia
 *
 */

public class YouBoxActivity extends TabActivity implements OnCheckedChangeListener {

	private RadioGroup mainTab;
	private RadioButton radioButtonPhone;
	private RadioButton radioButtonTrackingid;
	private TabHost tabhost;
	private ImageButton btn_jmp_setting;

	// 内容Intent
	private Intent PhoneSearchIntent;
	private Intent TrackingidSearchIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IntentFilter filter = new IntentFilter();
	    filter.addAction("finish");
	    registerReceiver(mFinishReceiver, filter);
		setContentView(R.layout.main);
		
		if(!DefaultData.STRING_versioninfo_version.equals(""))
		{
			
			SharedPreferences sharedPrefrences;
			sharedPrefrences = YouBoxActivity.this.getSharedPreferences(DefaultData.FILENAME, MODE_WORLD_READABLE);
			String ignoreversion= sharedPrefrences.getString("ignoreversion", "");
			if(!ignoreversion.equals(""))
			{
				if(ignoreversion.equals(DefaultData.STRING_versioninfo_version))
				{
					
				}
				else {

//					DefaultData.STRING_versioninfo_url = "http://down.angeeks.com/c/d2/d10112/10112281.apk";
//					DefaultData.STRING_versioninfo_url = "http://192.168.1.116:9997/Views/YouBox.apk";
					// 非必须 可以取消提示
					if(DefaultData.STRING_versioninfo_action.equals("0"))
					{
						UpdateDialog updateDialog = new UpdateDialog(YouBoxActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
						updateDialog.CreateWouldDialog();
					}
					// 非必须一直提示
					else if(DefaultData.STRING_versioninfo_action.equals("1"))
					{
						UpdateDialog updateDialog = new UpdateDialog(YouBoxActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
						updateDialog.CreateCanDialog();
					}
					// 必须
					else if(DefaultData.STRING_versioninfo_action.equals("2"))
					{
						UpdateDialog updateDialog = new UpdateDialog(YouBoxActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
						updateDialog.CreateMustDialog();
					}
				}
			}
			else {

//				DefaultData.STRING_versioninfo_url = "http://192.168.1.116:9997/Views/YouBox.apk";
//				DefaultData.STRING_versioninfo_url = "http://down.angeeks.com/c/d2/d10112/10112281.apk";
				// 非必须 可以取消提示
				if(DefaultData.STRING_versioninfo_action.equals("0"))
				{
					UpdateDialog updateDialog = new UpdateDialog(YouBoxActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
					updateDialog.CreateWouldDialog();
				}
				// 非必须一直提示
				else if(DefaultData.STRING_versioninfo_action.equals("1"))
				{
					UpdateDialog updateDialog = new UpdateDialog(YouBoxActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
					updateDialog.CreateCanDialog();
				}
				// 必须
				else if(DefaultData.STRING_versioninfo_action.equals("2"))
				{
					UpdateDialog updateDialog = new UpdateDialog(YouBoxActivity.this, "提示", DefaultData.STRING_versioninfo_description,DefaultData.STRING_versioninfo_url);
					updateDialog.CreateMustDialog();
				}
			}
		}
		
		mainTab = (RadioGroup) findViewById(R.id.radioGroup);
		mainTab.setOnCheckedChangeListener(this);
		tabhost = getTabHost();

		PhoneSearchIntent = new Intent(this, PhoneSearchActivity.class);
		tabhost.addTab(tabhost.newTabSpec("Phone").setIndicator("",getResources().getDrawable(R.drawable.ic_launcher)).setContent(PhoneSearchIntent));

		TrackingidSearchIntent = new Intent(this,TrackingidSearchActivity.class);
		tabhost.addTab(tabhost.newTabSpec("Tracking").setIndicator("",getResources().getDrawable(R.drawable.ic_launcher)).setContent(TrackingidSearchIntent));
		tabhost.setCurrentTab(0);

		radioButtonPhone = (RadioButton) findViewById(R.id.rd1);
		radioButtonTrackingid = (RadioButton) findViewById(R.id.rd2);
		radioButtonPhone.setChecked(true);

		btn_jmp_setting = (ImageButton) findViewById(R.id.btn_jmp_setting);
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(YouBoxActivity.this, SettingActivity.class);
				YouBoxActivity.this.startActivity(intent);
			}
		});

	}

	private BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	           if("finish".equals(intent.getAction())) {
	              finish();
	       }
	    }
	};
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rd1:
			this.tabhost.setCurrentTabByTag("Phone");
			radioButtonPhone.setTextColor(Color.parseColor("#54ADB4"));
			radioButtonTrackingid.setTextColor(Color.parseColor("#646464"));
			break;
		case R.id.rd2:
			this.tabhost.setCurrentTabByTag("Tracking");
			radioButtonPhone.setTextColor(Color.parseColor("#646464"));
			radioButtonTrackingid.setTextColor(Color.parseColor("#54ADB4"));
			break;
		}
	}


	/**
	 * menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, Menu.FIRST + 1, 3, "分享下载").setIcon(android.R.drawable.ic_menu_send);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			Intent intent = new Intent();
			intent.setClass(YouBoxActivity.this, ShareActivity.class);
			YouBoxActivity.this.startActivity(intent);
			break;
		}
		return false;
	}
}
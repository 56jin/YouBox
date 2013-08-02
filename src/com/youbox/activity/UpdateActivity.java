package com.youbox.activity;

import java.io.File;

import com.youbox.db.DefaultData;
import com.youbox.util.file.DownloadProgressListener;
import com.youbox.util.file.Downloader;
import com.youbox.util.plug.MsgDialog;
import com.youbox.util.plug.PopuDialog;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 更新Activity
 * 
 * @author zhongdaxia
 * 
 */

public class UpdateActivity extends Activity {
	private ProgressBar downloadbar;
	ImageButton btn_jmp_setting;
	Button btn_logoff;
	EditText et_username;
	Button btn_install;
	File dir;
	private TextView resultView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		IntentFilter filter = new IntentFilter();
		filter.addAction("finish");
		registerReceiver(mFinishReceiver, filter);

		findView();
		setClick();

		Bundle bundle = this.getIntent().getExtras();
		String urlString = bundle.getString("url");

		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			dir = Environment.getExternalStorageDirectory();
			download(urlString, dir);
		} else {
			MsgDialog msgDialog = new MsgDialog(UpdateActivity.this, "提示","无法加载SD存储设备");
			msgDialog.CreateDialog(1);
		}
	}

	private void findView() {
		resultView = (TextView) this.findViewById(R.id.result);
		downloadbar = (ProgressBar) this.findViewById(R.id.downloadbar);
		btn_install = (Button) findViewById(R.id.btn_install);
		btn_install.setEnabled(false);
		btn_jmp_setting = (ImageButton) findViewById(R.id.btn_jmp_setting);
	}

	private void setClick() {
		btn_install.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(Downloader.saveFile),"application/vnd.android.package-archive");
				startActivity(intent);
			}
		});
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (btn_install.getText().toString().equals("未完成")) {
					PopuDialog popuDialog = new PopuDialog(v.getContext(),"提示", "程序将保持断点，是否退出?");
					popuDialog.CreateDialog();
				} else {
					PopuDialog popuDialog = new PopuDialog(v.getContext(),"提示", "是否退出?");
					popuDialog.CreateDialog();
				}
			}
		});
	}

	private void download(final String path, final File dir) {
		new Thread(new Runnable() {
			public void run() {
				try {

					// 文件已经存在
					String filename = path.substring(path.lastIndexOf('/') + 1);
					File saveFile = new File(dir, filename);
					boolean b = saveFile.exists();
					if (b) {
						SharedPreferences sharedPrefrences;
						sharedPrefrences = UpdateActivity.this.getSharedPreferences(DefaultData.FILENAME,MODE_WORLD_READABLE);
						String downloadstate = sharedPrefrences.getString("downloadstate", "");
						if (downloadstate.equals("end")) {
							Downloader.saveFile = saveFile;
							Message msg = new Message();
							msg.what = 2;
							handler.sendMessage(msg);
							return;
						}

					}

					SharedPreferences sharedPrefrences;
					Editor editor;
					sharedPrefrences = UpdateActivity.this.getSharedPreferences(DefaultData.FILENAME, 1);
					editor = UpdateActivity.this.getSharedPreferences(DefaultData.FILENAME, 1).edit();
					editor.putString("downloadstate", "ing");
					editor.commit();

					Downloader loader = new Downloader(UpdateActivity.this,path, dir, 5);
					int length = loader.getFileSize(); 
					downloadbar.setMax(length); 
					loader.download(new DownloadProgressListener() {
						public void onDownloadSize(int size) { 
							Message msg = new Message();
							msg.what = 1;
							msg.getData().putInt("size", size);
							handler.sendMessage(msg);
						}
					});
				} catch (Exception e) {
					Message msg = new Message();
					msg.what = -1;
					msg.getData().putString("error", "下载失败");
					handler.sendMessage(msg);
				}
			}
		}).start();

	}

	private BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if ("finish".equals(intent.getAction())) {
				finish();
			}
		}
	};

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				int size = msg.getData().getInt("size");
				downloadbar.setProgress(size);
				float result = (float) downloadbar.getProgress() / (float) downloadbar.getMax();
				int p = (int) (result * 100);
				resultView.setText(p + "%");
				// 下载完成
				if (downloadbar.getProgress() == downloadbar.getMax()) {
					SharedPreferences sharedPrefrences;
					Editor editor;
					sharedPrefrences = UpdateActivity.this.getSharedPreferences(DefaultData.FILENAME, 1);
					editor = UpdateActivity.this.getSharedPreferences(DefaultData.FILENAME, 1).edit();
					editor.putString("downloadstate", "end");
					editor.commit();

					btn_install.setText("安装");
					btn_install.setEnabled(true);
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.setDataAndType(Uri.fromFile(Downloader.saveFile),"application/vnd.android.package-archive");
					startActivity(intent);
				}
				break;
			case 2:
				downloadbar.setMax(100);
				downloadbar.setProgress(100);
				btn_install.setText("安装");
				btn_install.setEnabled(true);
				break;
			case -1:
				// 下载失败
				MsgDialog msgDialog = new MsgDialog(UpdateActivity.this, "提示","下载失败");
				msgDialog.CreateDialog(1);
				break;
			}
		}
	};

	// 返回键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (btn_install.getText().toString().equals("未完成")) {
				PopuDialog popuDialog = new PopuDialog(this, "提示","程序将保持断点，是否退出?");
				popuDialog.CreateDialog();
			} else {
				PopuDialog popuDialog = new PopuDialog(this, "提示", "是否退出?");
				popuDialog.CreateDialog();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
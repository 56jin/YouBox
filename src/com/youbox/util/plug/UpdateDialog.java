package com.youbox.util.plug;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.youbox.activity.R;
import com.youbox.activity.UpdateActivity;
import com.youbox.db.DefaultData;

/**
 * 消息提示对话框
 * @author zhongdaxia
 * 
 */

public class UpdateDialog {

	private Context context;

	private String titleString;
	private String contentString;
	private String urlString;


	public UpdateDialog(Context context, String titleString, String contentString ,String urlString) {
		this.context = context;
		this.titleString = titleString;
		this.contentString = contentString;
		this.urlString = urlString;
	}


	public void CreateWouldDialog() {
		final Dialog dialog;
		Window dialogWindow;
		dialog = new Dialog(context, R.style.dialog);
		dialog.setContentView(R.layout.dialog_would_update);
		dialog.setCancelable(true);

		dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
		
		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay();
		WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); 

		p.height = (int) (d.getHeight() * 0.5);
		p.y = -p.height;
		p.width = (int) (d.getWidth() * 1);
		dialogWindow.setAttributes(p);
		
		
		dialogWindow.setWindowAnimations(R.style.msgdialoganimationstyle);
		dialog.show();
		

		final CheckBox checkBox = (CheckBox) dialogWindow.findViewById(R.id.checkBox_notask);
		Button ok = (Button) dialogWindow.findViewById(R.id.btn_ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, UpdateActivity.class);
				intent.putExtra("url", urlString);
				((Activity) context).finish();
				context.startActivity(intent);
			}
		});
		
		Button cancel = (Button) dialogWindow.findViewById(R.id.btn_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(checkBox.isChecked())
				{
					SharedPreferences sharedPrefrences;
					Editor editor;
					sharedPrefrences = context.getSharedPreferences(DefaultData.FILENAME, 1);
					editor = context.getSharedPreferences(DefaultData.FILENAME,1).edit();
					editor.putString("ignoreversion", DefaultData.STRING_versioninfo_version);
					editor.commit();
				}
				dialog.cancel();
			}
		});
		
		TextView title = (TextView) dialogWindow.findViewById(R.id.tv_title);
		title.setText(titleString);

		TextView content = (TextView) dialogWindow.findViewById(R.id.tv_content);
		content.setText(contentString);
	}
	
	public void CreateCanDialog() {
		final Dialog dialog;
		Window dialogWindow;
		dialog = new Dialog(context, R.style.dialog);
		dialog.setContentView(R.layout.dialog_can_update);
		dialog.setCancelable(true);

		dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
		
		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay();
		WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); 

		p.height = (int) (d.getHeight() * 0.5);
		p.y = -p.height;
		p.width = (int) (d.getWidth() * 1);
		dialogWindow.setAttributes(p);
		
		
		dialogWindow.setWindowAnimations(R.style.msgdialoganimationstyle);
		dialog.show();
		

		Button ok = (Button) dialogWindow.findViewById(R.id.btn_ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, UpdateActivity.class);
				intent.putExtra("url", urlString);
				((Activity) context).finish();
				context.startActivity(intent);
			}
		});
		
		Button cancel = (Button) dialogWindow.findViewById(R.id.btn_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		
		TextView title = (TextView) dialogWindow.findViewById(R.id.tv_title);
		title.setText(titleString);

		TextView content = (TextView) dialogWindow.findViewById(R.id.tv_content);
		content.setText(contentString);
	}
	
	public void CreateMustDialog() {
		final Dialog dialog;
		Window dialogWindow;
		dialog = new Dialog(context, R.style.dialog);
		dialog.setContentView(R.layout.dialog_must_update);
		dialog.setCancelable(false);

		dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
		
		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay();
		WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); 

		p.height = (int) (d.getHeight() * 0.5);
		p.y = -p.height;
		p.width = (int) (d.getWidth() * 1);
		dialogWindow.setAttributes(p);
		
		
		dialogWindow.setWindowAnimations(R.style.msgdialoganimationstyle);
		dialog.show();
		

		Button ok = (Button) dialogWindow.findViewById(R.id.btn_ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, UpdateActivity.class);
				intent.putExtra("url", urlString);
				((Activity) context).finish();
				context.startActivity(intent);
			}
		});
		

		Button cancel = (Button) dialogWindow.findViewById(R.id.btn_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				((Activity) context).finish();
				System.exit(0);
			}
		});
		
		TextView title = (TextView) dialogWindow.findViewById(R.id.tv_title);
		title.setText(titleString);

		TextView content = (TextView) dialogWindow.findViewById(R.id.tv_content);
		content.setText(contentString);
	}
	
	
}

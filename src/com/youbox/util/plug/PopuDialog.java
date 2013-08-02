package com.youbox.util.plug;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.youbox.activity.R;

public class PopuDialog {

	private Context context;

	private String titleString;
	private String contentString;

	public PopuDialog(Context context, String titleString, String contentString) {
		this.context = context;
		this.titleString = titleString;
		this.contentString = contentString;
	}

	public PopuDialog(Context context) {
		this.context = context;
		this.titleString = "提示";
		this.contentString = "退出程序?";
	}

	public void CreateDialog()
	{
		final Dialog dialog = new Dialog(context, R.style.dialog);
		dialog.setContentView(R.layout.dialog_exit);
		dialog.setCancelable(true);

		Window dialogWindow = dialog.getWindow();
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
				((Activity) context).finish();
				System.exit(0);
			}
		});

		Button cancel = (Button) dialogWindow.findViewById(R.id.btn_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				dialog.cancel();

			}

		});
		
		TextView title = (TextView) dialogWindow.findViewById(R.id.textView1);
		title.setText(titleString);

		TextView content = (TextView) dialogWindow.findViewById(R.id.textView2);
		content.setText(contentString);
	}
}

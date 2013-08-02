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

/**
 * 消息提示对话框
 * 
 * @author zhongdaxia
 * 
 */

public class MsgDialog {

	private Context context;

	private String titleString;
	private String contentString;

	public Button ok ;

	public MsgDialog(Context context, String titleString, String contentString) {
		this.context = context;
		this.titleString = titleString;
		this.contentString = contentString;
	}

	public void CreateDialog(int clickType) {
		final Dialog dialog;
		Window dialogWindow;
		dialog = new Dialog(context, R.style.dialog);
		dialog.setContentView(R.layout.dialog_msg);
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
		

		ok = (Button) dialogWindow.findViewById(R.id.btn_ok);
		switch (clickType) {
		case 0:
			ok.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					dialog.cancel();
				}
			});
			break;
	    case 1:
			ok.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					((Activity) context).finish();
					System.exit(0);
				}
			});
			break;
		default:
			break;
		}
		

		TextView title = (TextView) dialogWindow.findViewById(R.id.tv_title);
		title.setText(titleString);

		TextView content = (TextView) dialogWindow.findViewById(R.id.tv_content);
		content.setText(contentString);
	}
	
}

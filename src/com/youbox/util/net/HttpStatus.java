package com.youbox.util.net;


import com.youbox.util.plug.MsgDialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * 服务器端返回的状态提示
 * @author zhongdaxia
 *
 */

public class HttpStatus {
	
	public static final String HTTP_FAIL="失败";
	public static final String HTTP_SUCCESS="成功";
	public static final String HTTP_404="404请求链接无效";
	public static final String HTTP_500="500服务器错误";
	public static final String HTTP_900="900网络传输协议错误";
	public static final String HTTP_901="901连接超时";
	public static final String HTTP_902="902网络中断";
	public static final String HTTP_903="903网络连接错误";
	public static final String HTTP_UNKONW="未知的错误";
	
	//自定义的提示
	private String tips;
	
	public  void ShowHttpStatusTips(int status,Context context,ProgressDialog proDialog){
		MsgDialog msgDialog;
		switch(status){
		case 0:
			Toast.makeText(context, HTTP_FAIL, Toast.LENGTH_LONG).show();
			break;
			// 成功
		case 1:
//			Toast.makeText(context, HTTP_SUCCESS, Toast.LENGTH_LONG).show();
			break;
			// 自定义提示
		case 2:
			msgDialog = new MsgDialog(context, "提示", getTips());
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, getTips(), Toast.LENGTH_LONG).show();
			break;
		case 404:
			msgDialog = new MsgDialog(context, "提示", HTTP_404);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_404, Toast.LENGTH_LONG).show();
			break;
		case 500:
			msgDialog = new MsgDialog(context, "提示", HTTP_500);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_500,Toast.LENGTH_LONG).show();			
			break;
		case 900:
			msgDialog = new MsgDialog(context, "提示", HTTP_900);
			msgDialog.CreateDialog(1);
//            Toast.makeText(context, HTTP_900, Toast.LENGTH_LONG).show();			
			break;
		case 901:
			msgDialog = new MsgDialog(context, "提示", HTTP_901);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_901, Toast.LENGTH_LONG).show();
			break;
		case 902:
			msgDialog = new MsgDialog(context, "提示", HTTP_902);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_902, Toast.LENGTH_LONG).show();
			break;
		case 903:
			msgDialog = new MsgDialog(context, "提示", HTTP_903);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_903, Toast.LENGTH_LONG).show();
			break;
		default:
			msgDialog = new MsgDialog(context, "提示", HTTP_UNKONW);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_UNKONW, Toast.LENGTH_LONG).show();
			break;			
		}
		if(proDialog!=null)
			proDialog.dismiss();
	}
	
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	
}
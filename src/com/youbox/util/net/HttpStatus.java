package com.youbox.util.net;


import com.youbox.util.plug.MsgDialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * �������˷��ص�״̬��ʾ
 * @author zhongdaxia
 *
 */

public class HttpStatus {
	
	public static final String HTTP_FAIL="ʧ��";
	public static final String HTTP_SUCCESS="�ɹ�";
	public static final String HTTP_404="404����������Ч";
	public static final String HTTP_500="500����������";
	public static final String HTTP_900="900���紫��Э�����";
	public static final String HTTP_901="901���ӳ�ʱ";
	public static final String HTTP_902="902�����ж�";
	public static final String HTTP_903="903�������Ӵ���";
	public static final String HTTP_UNKONW="δ֪�Ĵ���";
	
	//�Զ������ʾ
	private String tips;
	
	public  void ShowHttpStatusTips(int status,Context context,ProgressDialog proDialog){
		MsgDialog msgDialog;
		switch(status){
		case 0:
			Toast.makeText(context, HTTP_FAIL, Toast.LENGTH_LONG).show();
			break;
			// �ɹ�
		case 1:
//			Toast.makeText(context, HTTP_SUCCESS, Toast.LENGTH_LONG).show();
			break;
			// �Զ�����ʾ
		case 2:
			msgDialog = new MsgDialog(context, "��ʾ", getTips());
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, getTips(), Toast.LENGTH_LONG).show();
			break;
		case 404:
			msgDialog = new MsgDialog(context, "��ʾ", HTTP_404);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_404, Toast.LENGTH_LONG).show();
			break;
		case 500:
			msgDialog = new MsgDialog(context, "��ʾ", HTTP_500);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_500,Toast.LENGTH_LONG).show();			
			break;
		case 900:
			msgDialog = new MsgDialog(context, "��ʾ", HTTP_900);
			msgDialog.CreateDialog(1);
//            Toast.makeText(context, HTTP_900, Toast.LENGTH_LONG).show();			
			break;
		case 901:
			msgDialog = new MsgDialog(context, "��ʾ", HTTP_901);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_901, Toast.LENGTH_LONG).show();
			break;
		case 902:
			msgDialog = new MsgDialog(context, "��ʾ", HTTP_902);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_902, Toast.LENGTH_LONG).show();
			break;
		case 903:
			msgDialog = new MsgDialog(context, "��ʾ", HTTP_903);
			msgDialog.CreateDialog(1);
//			Toast.makeText(context, HTTP_903, Toast.LENGTH_LONG).show();
			break;
		default:
			msgDialog = new MsgDialog(context, "��ʾ", HTTP_UNKONW);
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
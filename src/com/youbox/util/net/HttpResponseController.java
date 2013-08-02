package com.youbox.util.net;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.youbox.activity.SearchResultActivity;
import com.youbox.activity.YouBoxActivity;
import com.youbox.db.DefaultData;
import com.youbox.util.plug.MsgDialog;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * http��Ӧ�ӿ�
 * @author zhongdaxia
 *
 */

public class HttpResponseController {

	public Context context;
	private Map contentMap;

	public HttpResponseController(Context context) {
		this.context = context;
	}
	
	public HttpResponseController(Context context ,Map contentMap) {
		this.context = context;
		this.contentMap = contentMap;
	}

	public void init(String result) {
		    String funcid = null;
			if (contentMap != null) {
				funcid = contentMap.get("funcID").toString();
			}
			else
			{
				Log.v(DefaultData.LOG_TYPE_ERROR, "����json����-funcid��ƥ��  - ����ֵ:"+ result);
				return;
			}
			int funID = Integer.parseInt(funcid);
			switch (funID) {

			// ��ȡ�汾
			case 1001:
				GetVersionResponse(result);
				break;
			// ��¼
			case 1002:
				LoginResponse(result);
				break;
		    // �ֻ��Ż�ȡ����
			case 2001:
				packageDetailResponse(result);
				break;
			// ����Ż�ȡ����
			case 2002:
				packageDetailByParcelIDResponse(result);
				break;
			default:
				Log.v(DefaultData.LOG_TYPE_ERROR, "����json����-funcid��ƥ��  - ����ֵ:"+ result);
				break;
			}

	}
	
	/**
	 * ��ȡ�汾
	 * @param result
	 */
	public void GetVersionResponse(String result) {
		String current_versionName = null;
		String new_versionName;
		String action;
		String url;
		String description;
		
		if (contentMap != null) {
			current_versionName = contentMap.get("versionName").toString();
		}
		else {
			return;
		}
		try {
			JSONObject object = new JSONObject(result);
			new_versionName = object.getString("version");
			action = object.getString("action");
			url = object.getString("url");
			description = object.getString("description");
			
			if(!current_versionName.equals(new_versionName))
			{
				DefaultData.STRING_versioninfo_version = new_versionName;
				DefaultData.STRING_versioninfo_action = action;
				DefaultData.STRING_versioninfo_url = url;
				DefaultData.STRING_versioninfo_description = description;
//				int actionInt = Integer.parseInt(action);
//				switch (actionInt) {
//				// ����
//				case 0:
//					 MsgDialog msgDialog = new MsgDialog(context, "��ʾ", description);
//	                 msgDialog.CreateDialog();
//					break;
//				// �Ǳ���һֱ��ʾ
//				case 1:
//
//					break;
//				// �Ǳ��� ����ȡ����ʾ
//				case 2:
//
//					break;
//				default:
//					break;
//				}
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��¼
	 * @param result
	 */
	public void LoginResponse(String result) {
		
		boolean IsAutoLogin = false;
		String usernameString = "";
		String passwordString = "";
		if (contentMap != null) {
			if(contentMap.get("isAutoLogin").toString().equals("true"))
				IsAutoLogin = true;
			usernameString = contentMap.get("username").toString();
			passwordString = contentMap.get("password").toString();
		}
		
		String string = null;
		JSONObject object;
		try {
			object = new JSONObject(result);
			string = object.getString("result");
			int state = Integer.parseInt(string);
			switch (state) {
			// ��¼�ɹ�
			case 0:  
				if (IsAutoLogin) {
					SharedPreferences sharedPrefrences;
					Editor editor;

					sharedPrefrences = context.getSharedPreferences(DefaultData.FILENAME, 1);
					editor = context.getSharedPreferences(DefaultData.FILENAME,1).edit();
					editor.putString("username", usernameString);
					editor.putString("password", passwordString);
					editor.commit();
				}

				Intent intent = new Intent();
				intent.setClass(context, YouBoxActivity.class);
				((Activity) context).finish();
				DefaultData.STRING_USERNAME = usernameString;
				intent.putExtra("username", usernameString);
				((Activity) context).finish();
				context.startActivity(intent);
				break;
			// ��¼ʧ��
			case 1:  
				 MsgDialog msgDialog = new MsgDialog(context, "��ʾ", "��������˺Ŵ���");
                 msgDialog.CreateDialog(0);
                 
//				 Intent intentLoginActivity = new Intent();
//				 intentLoginActivity.setClass(context, LoginActivity.class);
//                 ((Activity) context).finish();
//				 context.startActivity(intentLoginActivity);	
				break;
			default:
				Log.v(DefaultData.LOG_TYPE_ERROR, "����json����-�����������Ӧ��״̬  - ����ֵ:"+ result);
				break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����Ų�ѯ����
	 * @param result
	 */
	public void packageDetailByParcelIDResponse(String result) {
	
		try {
			JSONArray jsonArray = new JSONArray(result);

			int count = jsonArray.length();
			
			// ������
			if(count == 0 )
			{
				 MsgDialog msgDialog = new MsgDialog(context, "��ʾ", "������");
                 msgDialog.CreateDialog(0);
				 return;
			}
			
			 Intent newIntent = new Intent(context, SearchResultActivity.class);       
		     newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		     newIntent.putExtra("parcellist", result);
		     context.startActivity(newIntent);
		     
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ֻ������ѯ����
	 * @param result
	 */
	public void packageDetailResponse(String result) {
	
		try {
			JSONArray jsonArray = new JSONArray(result);

			int count = jsonArray.length();
			
			// ������
			if(count == 0 )
			{
				 MsgDialog msgDialog = new MsgDialog(context, "��ʾ", "������");
                 msgDialog.CreateDialog(0);
				 return;
			}
			
			 Intent newIntent = new Intent(context, SearchResultActivity.class);       
		     newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		     newIntent.putExtra("parcellist", result);
		     context.startActivity(newIntent);
		     
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

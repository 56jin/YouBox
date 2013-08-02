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
 * http响应接口
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
				Log.v(DefaultData.LOG_TYPE_ERROR, "返回json错误-funcid无匹配  - 返回值:"+ result);
				return;
			}
			int funID = Integer.parseInt(funcid);
			switch (funID) {

			// 获取版本
			case 1001:
				GetVersionResponse(result);
				break;
			// 登录
			case 1002:
				LoginResponse(result);
				break;
		    // 手机号获取包裹
			case 2001:
				packageDetailResponse(result);
				break;
			// 快件号获取包裹
			case 2002:
				packageDetailByParcelIDResponse(result);
				break;
			default:
				Log.v(DefaultData.LOG_TYPE_ERROR, "返回json错误-funcid无匹配  - 返回值:"+ result);
				break;
			}

	}
	
	/**
	 * 获取版本
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
//				// 必须
//				case 0:
//					 MsgDialog msgDialog = new MsgDialog(context, "提示", description);
//	                 msgDialog.CreateDialog();
//					break;
//				// 非必须一直提示
//				case 1:
//
//					break;
//				// 非必须 可以取消提示
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
	 * 登录
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
			// 登录成功
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
			// 登录失败
			case 1:  
				 MsgDialog msgDialog = new MsgDialog(context, "提示", "密码或者账号错误");
                 msgDialog.CreateDialog(0);
                 
//				 Intent intentLoginActivity = new Intent();
//				 intentLoginActivity.setClass(context, LoginActivity.class);
//                 ((Activity) context).finish();
//				 context.startActivity(intentLoginActivity);	
				break;
			default:
				Log.v(DefaultData.LOG_TYPE_ERROR, "返回json错误-错误的心跳包应答状态  - 返回值:"+ result);
				break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 快件号查询包裹
	 * @param result
	 */
	public void packageDetailByParcelIDResponse(String result) {
	
		try {
			JSONArray jsonArray = new JSONArray(result);

			int count = jsonArray.length();
			
			// 无数据
			if(count == 0 )
			{
				 MsgDialog msgDialog = new MsgDialog(context, "提示", "无数据");
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
	 * 手机号码查询包裹
	 * @param result
	 */
	public void packageDetailResponse(String result) {
	
		try {
			JSONArray jsonArray = new JSONArray(result);

			int count = jsonArray.length();
			
			// 无数据
			if(count == 0 )
			{
				 MsgDialog msgDialog = new MsgDialog(context, "提示", "无数据");
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

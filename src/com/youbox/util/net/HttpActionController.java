package com.youbox.util.net;


import java.util.HashMap;
import java.util.Map;
import com.youbox.db.DefaultData;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;

/**
 * 登录
 * 根据手机号查询包裹详情
 * @author zhongdaxia
 *
 */

public class HttpActionController {
	public Context context;
	public String devId;
	public String softVer;
	public String hardVer;
	private ProgressDialog proDialog;
	
	public HttpActionController(Context context)
	{
		this.context = context;
	}

	public HttpActionController(Context context , ProgressDialog proDialog)
	{
		this.context = context;
		this.proDialog = proDialog;
	}
	
	
	
	/**
	 * 登录
	 * @param usernameString    用户名
 	 * @param password          密码
	 * @param returnType        返回类型 - 默认json
	 * @param IsAutoLogin       是否自动登录
	 */
	public void login(String username , String password , String returnType ,boolean isAutoLogin)
	{
		String [] key = {"username","password","returnType"};
		String [] value = {username,password,returnType};
		//String urlAddress = DefaultData.HTTP_URLADDR_LOGIN;
		String urlAddress = DefaultData.HTTP_URLADDR_LOGIN;
		Map map = new HashMap() ;
		map.put("funcID", DefaultData.HTTP_FUNCID_login);
		map.put("username", username);
		map.put("password", password);
		map.put("isAutoLogin", isAutoLogin);
		
		HttpGetThread Thread ;
		if(proDialog != null)
		{
			Thread = new HttpGetThread(context,proDialog,map);
		}
		else {
			Thread = new HttpGetThread(context,map);
		}
		
		Thread.setUrl(urlAddress);
		Thread.setKey(key);
		Thread.setValue(value);
		Thread.start();
	}
	
	/**
	 * 根据手机号查询包裹详情
	 * @param phoneNum     手机号
	 * @param returnType   返回类型 - 默认json
	 */
	public void packageDetail(String phoneNum , String returnType)
	{
		String [] key = {"phoneNum","returnType"};
		String [] value = {phoneNum,returnType};
		String urlAddress = DefaultData.HTTP_URLADDR_PHONESEARCH;
		Map map = new HashMap() ;
		map.put("funcID", DefaultData.HTTP_FUNCID_packageDetail);
		
		HttpGetThread Thread ;
		if(proDialog != null)
		{
			Thread = new HttpGetThread(context,proDialog,map);
		}
		else {
			Thread = new HttpGetThread(context,map);
		}
		
		Thread.setUrl(urlAddress);
		Thread.setKey(key);
		Thread.setValue(value);
		Thread.start();
	}
	
	/**
	 * 根据快件号查询包裹详情
	 * @param ParcelID     快件号
	 * @param returnType   返回类型 - 默认json
	 */
	public void packageDetailByParcelID(String ParcelID , String returnType)
	{
		String [] key = {"ParcelID","returnType"};
		String [] value = {ParcelID,returnType};
		String urlAddress = DefaultData.HTTP_URLADDR_packageDetailByParcelID;
		Map map = new HashMap() ;
		map.put("funcID", DefaultData.HTTP_FUNCID_packageDetailByParcelID);
		
		HttpGetThread Thread ;
		if(proDialog != null)
		{
			Thread = new HttpGetThread(context,proDialog,map);
		}
		else {
			Thread = new HttpGetThread(context,map);
		}
		
		Thread.setUrl(urlAddress);
		Thread.setKey(key);
		Thread.setValue(value);
		Thread.start();
	}
	
	/**
	 * 获取版本信息
	 * @param returnType   返回类型 - 默认json
	 */
	public void getVersion(String versionName)
	{
		String sdk=Build.VERSION.SDK;          // SDK号
	    String model=Build.MODEL;              // 手机型号
	    String release=Build.VERSION.RELEASE;  // android系统版本号
	    model = model.replace(" ","_");
	    release = release.replace(" ","_");
		String [] key = {"sdk","model","release","versionName"};
		String [] value = {sdk,model,release,versionName};
		String urlAddress = "http://new.jkson.me/YouBoxClientVersionInfo.aspx";
		Map map = new HashMap() ;
		map.put("funcID", DefaultData.HTTP_FUNCID_VERSION);
		map.put("versionName", versionName);
		HttpGetThread Thread ;
		if(proDialog != null)
		{
			Thread = new HttpGetThread(context,proDialog,map);
		}
		else {
			Thread = new HttpGetThread(context,map);
		}
		
		Thread.setUrl(urlAddress);
		Thread.setKey(key);
		Thread.setValue(value);
		Thread.start();
	}

}

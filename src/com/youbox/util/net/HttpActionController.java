package com.youbox.util.net;


import java.util.HashMap;
import java.util.Map;
import com.youbox.db.DefaultData;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;

/**
 * ��¼
 * �����ֻ��Ų�ѯ��������
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
	 * ��¼
	 * @param usernameString    �û���
 	 * @param password          ����
	 * @param returnType        �������� - Ĭ��json
	 * @param IsAutoLogin       �Ƿ��Զ���¼
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
	 * �����ֻ��Ų�ѯ��������
	 * @param phoneNum     �ֻ���
	 * @param returnType   �������� - Ĭ��json
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
	 * ���ݿ���Ų�ѯ��������
	 * @param ParcelID     �����
	 * @param returnType   �������� - Ĭ��json
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
	 * ��ȡ�汾��Ϣ
	 * @param returnType   �������� - Ĭ��json
	 */
	public void getVersion(String versionName)
	{
		String sdk=Build.VERSION.SDK;          // SDK��
	    String model=Build.MODEL;              // �ֻ��ͺ�
	    String release=Build.VERSION.RELEASE;  // androidϵͳ�汾��
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

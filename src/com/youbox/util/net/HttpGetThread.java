package com.youbox.util.net;


import java.util.Map;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * get线程
 * 
 * @author zhongdaxia
 * 
 */

public class HttpGetThread extends Thread {
	private Context context;
	private ProgressDialog proDialog;
	private String url;
	private String[] key;
	private String[] value;
	private String result = null;
	
	private Map contentMap ;    //传递的数据

	public HttpGetThread(Context context) {
		this.context = context;
	}
	
	
	//需要传值的构造
	public HttpGetThread(Context context,Map contentMap) {
		this.context = context;
		this.contentMap = contentMap;
	}
	
	public HttpGetThread(Context context, ProgressDialog proDialog,Map contentMap) {
		this.context = context;
		this.proDialog = proDialog;
		this.contentMap = contentMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getKey() {
		return key;
	}

	public void setKey(String[] key) {
		this.key = key;
	}

	public String[] getValue() {
		return value;
	}

	public void setValue(String[] value) {
		this.value = value;
	}

	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		HttpHelper get = new HttpHelper();
		int res = get.doGet(url, key, value);
		String str = get.toString();
		msg.what = res;
		if (res == 1) {
			result = get.getWebContext();
		}
		handler.sendMessage(msg);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int what = msg.what;
			HttpStatus status = new HttpStatus();
			if (what == 1) {
				HttpResponseController hResponseController;
				if(contentMap != null)
				{
					hResponseController = new HttpResponseController(context,contentMap);
				}
				else 
					hResponseController = new HttpResponseController(context);
				hResponseController.init(result);
			}
			else if (what == 2) {
				status.setTips("自定义提示2");
			}
			status.ShowHttpStatusTips(what, context, proDialog);
			
		}
	};

}

package com.youbox.util.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.youbox.db.DefaultData;

public class HttpHelper {

	public HttpHelper() {

	}
	
	private static CookieStore cookieStore;
	
	private String webContext;

	public String getWebContext() {
		return webContext;
	}

	public void setWebContext(String webContext) {
		this.webContext = webContext;
	}

	/**
	 * Get
	 * @param url
	 * @param key
	 * @param value
	 * @return
	 */
	public int doGet(String url, String[] key, String[] value) {
		// http״̬
		int status = 0;
		String executeUrlAddr = null;
		executeUrlAddr = url + "?";
		List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		int size = key.length;
		for (int i = 0; i < size; i++) {
			pairs.add(new BasicNameValuePair(key[i], value[i]));
			executeUrlAddr = executeUrlAddr + key[i] + "=" + value[i];
			if (i != size - 1)
				executeUrlAddr = executeUrlAddr + "&";
		}
		HttpGet httpGet = new HttpGet(executeUrlAddr);
		DefaultHttpClient  hc = new DefaultHttpClient();
		// ���Cookie   
		if (cookieStore != null) {  
			hc.setCookieStore(cookieStore);               
		}  

		try {
			HttpResponse ht = hc.execute(httpGet);
			int res = ht.getStatusLine().getStatusCode();
			if (ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = ht.getEntity();
				InputStream is = he.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String response = "";
				String readLine = null;
//				String info =EntityUtils.toString(ht.getEntity());  
//				setWebContext(info);
				while ((readLine = br.readLine()) != null) {
					// response = br.readLine();
					response = response + readLine;
				}
				setWebContext(response);
				is.close();
				br.close();
				status = 1;
				cookieStore = ((AbstractHttpClient) hc).getCookieStore();  

		        return status;
			} else if (res == 404) {// ����������Ч
				status = 404;
			} else if (res == 500) {// �������˳������
				status = 500;
			}
		} catch (ClientProtocolException e) {// ����Э�����
			e.printStackTrace();
			status = 900;
		} catch (ConnectTimeoutException e) {// ���ӳ�ʱ
			e.printStackTrace();
			status = 901;
		} catch (InterruptedIOException e) {// �����ж�
			e.printStackTrace();
			status = 902;
		} catch (IOException e) {// ���ݴ������
			e.printStackTrace();
			status = 903;
		}
		return status;
	}

	/**
	 * url ���ʵ������ַ key ���ݲ��������� value ���ݲ�����ֵ
	 * key��value���鳤�ȶ�Ӧ,��һ�Լ�ֵ��,�������Բ����Ʋ������ݵĸ���
	 */
	public int doPost(String url, String[] key, String[] value) {
		// http״̬
		int status = 0;
		String executeUrlAddr = null; 
		DefaultHttpClient mHttpClient = new DefaultHttpClient();
		HttpPost mPost = new HttpPost(url);
		List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		int size = key.length;
		executeUrlAddr = url + "?";
		for (int i = 0; i < size; i++) {
			pairs.add(new BasicNameValuePair(key[i], value[i]));
			executeUrlAddr = executeUrlAddr + key[i] + "=" + value[i];
			if (i != size - 1)
				executeUrlAddr = executeUrlAddr + "&";
		}
		try {
			mPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		try {
			mHttpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, DefaultData.HTTP_TIMEOUT); // Socket��ʱ����10s
			mHttpClient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT,DefaultData.HTTP_TIMEOUT);// ���ӳ�ʱ10s
			HttpResponse response = mHttpClient.execute(mPost);
			int res = response.getStatusLine().getStatusCode();
			if (res == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String info = EntityUtils.toString(entity);
					setWebContext(info);
					status = 1;
					return status;
				}
			} else if (res == 404) {// ����������Ч
				status = 404;
			} else if (res == 500) {// �������˳������
				status = 500;
			}
		} catch (ClientProtocolException e) {// ����Э�����
			e.printStackTrace();
			status = 900;
		} catch (ConnectTimeoutException e) {// ���ӳ�ʱ
			e.printStackTrace();
			status = 901;
		} catch (InterruptedIOException e) {// �����ж�
			e.printStackTrace();
			status = 902;
		} catch (IOException e) {// ���ݴ������
			e.printStackTrace();
			status = 903;
		}
		return status;
	}
}

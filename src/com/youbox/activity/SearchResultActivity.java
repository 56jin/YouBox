package com.youbox.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.youbox.db.ParcelBean;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * ËÑË÷½á¹ûActivity
 * @author zhongdaxia
 *
 */

public class SearchResultActivity extends Activity {

	ImageButton btn_jmp_setting;
	ListView  listview;
	List<ParcelBean> parcelBeanlist = new ArrayList();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IntentFilter filter = new IntentFilter();
        filter.addAction("finish");
        registerReceiver(mFinishReceiver, filter);
		setContentView(R.layout.activity_searchresult);
		
		
		
	 	Bundle extras = getIntent().getExtras();
	 	String parcellist = extras.getString("parcellist");
		
//		String parcellist = "[{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':2,'getTime':'2013-03-18T10:03:35','packageId':72,'packageStatus':1,'parcelId':'abcd12bacd','storeTime':'2013-03-18T10:01:59','terminalId':1,'userPhone':18960877052},{'boxId':12,'courierCorp':'Ë³·á¿ìµÝ','courierId':1,'getTime':'2013-05-17T15:54:49','packageId':71,'packageStatus':1,'parcelId':'abcd12341bacd','storeTime':'2013-03-18T09:48:48','terminalId':1,'userPhone':18960877052}]";
		
//		String parcellist = "[{'boxId': 9, 'courierCorp': 'ÉêÍ¨¿ìµÝ', 'courierId': 1, 'getTime': null, 'packageId': 23, 'packageStatus': 0, 'parcelId': '23456789023456', 'storeTime': '2013-03-12T17:46:03', 'terminal': {'branch': {'branchAddress': '¸£ÖÝÊÐ¶«Ë®Â·', 'branchId': 2,  'branchName': '½»Í¨Ìü', 'branchShortAddress': '½»Í¨ÌüÍøµã' }, 'hardVer': '1', 'name': 'Ò»ºÅ»ú', 'softVer': '1', 'status': 0, 'terminalId': 3},  'userPhone': 15060065536 }]";
		 
		
		try {
			JSONArray jsonArray = new JSONArray(parcellist);
			int count = jsonArray.length();
			for(int i=0;i<count;i++)
            {
				ParcelBean parcelBean = new ParcelBean();
				parcelBean.setBoxId(jsonArray.getJSONObject(i).getString("boxId"));
				parcelBean.setCourierCorp(jsonArray.getJSONObject(i).getString("courierCorp"));
				parcelBean.setCourierId(jsonArray.getJSONObject(i).getString("courierId"));
				parcelBean.setGetTime(jsonArray.getJSONObject(i).getString("getTime"));
				parcelBean.setPackageStatus(jsonArray.getJSONObject(i).getString("packageStatus"));
				parcelBean.setParcelId(jsonArray.getJSONObject(i).getString("parcelId"));
				parcelBean.setStoreTime(jsonArray.getJSONObject(i).getString("storeTime"));
				
				JSONObject  response=jsonArray.getJSONObject(i).getJSONObject("terminal");				
				parcelBean.setHardVer(response.getString("hardVer"));
                parcelBean.setName(response.getString("name"));
                parcelBean.setSoftVer(response.getString("softVer"));
                parcelBean.setStatus(response.getString("status"));
                parcelBean.setTerminalId(response.getString("terminalId"));
                
                response=response.getJSONObject("branch");
                parcelBean.setBranchAddress(response.getString("branchAddress"));
                parcelBean.setBranchId(response.getString("branchId"));
                parcelBean.setBranchName(response.getString("branchName"));
                parcelBean.setBranchShortAddress(response.getString("branchShortAddress"));	
                
				parcelBean.setUserPhone(jsonArray.getJSONObject(i).getString("userPhone"));
				parcelBeanlist.add(parcelBean);
            }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		int[] test = { 3, 4, 2, 6, 2, 1, 9, 5 };
		for (int i = 0; i < test.length - 1; i++) {
			for (int j = i + 1; j < test.length; j++) {
				if (test[i] > test[j]) {
					int temp = test[i];
					test[i] = test[j];
					test[j] = temp;
				}
			}
		}
	      int x1  =   test.length;

			System.out.print(x1);
		for(int i = 0 ; i < test.length ; i ++)
		{
			int x = test[i];
			System.out.print(x);
		}
		
		sortStoreTime(parcelBeanlist);
		FindView();
		SetClick();
		init();
	}
	
	/**
	 * Ê±¼äÅÅÐò
	 */
	public List<ParcelBean> sortStoreTime(List<ParcelBean> parcelBeanlist)
	{
		
		//int [] test2 = test; 
		return parcelBeanlist;
	}
	
	private BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	           if("finish".equals(intent.getAction())) {
	              finish();
	       }
	    }
	};
	/**
	 * Êý¾Ý³õÊ¼»¯
	 */
	private void init()
	{
		//Éú³É¶¯Ì¬Êý×é£¬¼ÓÈëÊý¾Ý  
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
        for(int i=0;i<parcelBeanlist.size();i++)  
        {  
            HashMap<String, Object> map = new HashMap<String, Object>();  
            map.put("numID",   "" + (i + 1));  
            map.put("userPhone",   parcelBeanlist.get(i).getUserPhone());  
            map.put("boxId",   parcelBeanlist.get(i).getBoxId());  
            map.put("courierCorp",   parcelBeanlist.get(i).getCourierCorp());  
            map.put("courierId",  parcelBeanlist.get(i).getCourierId());  
            map.put("getTime",  parcelBeanlist.get(i).getGetTime());  
            map.put("packageStatus",   parcelBeanlist.get(i).getPackageStatus());  
            map.put("parcelId",   parcelBeanlist.get(i).getParcelId());  
            map.put("storeTime",   parcelBeanlist.get(i).getStoreTime());  
            

            map.put("branchAddress",   parcelBeanlist.get(i).getBranchAddress()); 
            map.put("branchId",   parcelBeanlist.get(i).getBranchId()); 
            map.put("branchName",   parcelBeanlist.get(i).getBranchName()); 
            map.put("branchShortAddress",   parcelBeanlist.get(i).getBranchShortAddress()); 
            map.put("hardVer",   parcelBeanlist.get(i).getHardVer()); 
            map.put("name",   parcelBeanlist.get(i).getName()); 
            map.put("softVer",   parcelBeanlist.get(i).getSoftVer()); 
            map.put("status",   parcelBeanlist.get(i).getStatus()); 
            
            
            
            
            map.put("terminalId",   parcelBeanlist.get(i).getTerminalId());  
            listItem.add(map);  
        }  
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,
            R.layout.listitem_searchresult,
            new String[] {"numID","userPhone","boxId", "courierCorp","courierId","getTime", "packageStatus","parcelId","storeTime", "terminalId", "branchAddress", "branchId", "branchName", "branchShortAddress", "hardVer", "name", "softVer", "status"},  
            new int[] {R.id.numID,R.id.userPhone,R.id.boxId,R.id.courierCorp,R.id.courierId,R.id.getTime,R.id.packageStatus,R.id.parcelId,R.id.storeTime,R.id.terminalId,R.id.branchAddress,R.id.branchId,R.id.branchName,R.id.branchShortAddress,R.id.hardVer,R.id.name,R.id.softVer,R.id.status}
               
//            new String[] {"numID","userPhone","boxId", "courierCorp","courierId","getTime", "packageStatus","parcelId","storeTime", "terminalId", "terminalId", "terminalId", "terminalId", "terminalId", "terminalId", "terminalId", "terminalId", "terminalId", "terminalId", "terminalId"},  
//            new int[] {R.id.numID,R.id.userPhone,R.id.boxId,R.id.courierCorp,R.id.courierId,R.id.getTime,R.id.packageStatus,R.id.parcelId,R.id.storeTime,R.id.terminalId}  
    );  
        listview.setAdapter(listItemAdapter);   
        listview.setOnItemClickListener(new OnItemClickListener() {  
            @Override  
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {   
            }  
        });  
	}

	private void FindView() {
		btn_jmp_setting = (ImageButton) findViewById(R.id.btn_jmp_setting);
		listview = (ListView) findViewById(R.id.ListView_searchresult); 
	}
  
	private void SetClick() {
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SearchResultActivity.this.finish();
			}
		});
	}
}
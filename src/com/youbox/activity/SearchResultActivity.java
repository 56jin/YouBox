package com.youbox.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.youbox.buss.Parcel;
import com.youbox.buss.parcelListAdapter;

import android.R.integer;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

/**
 * 搜索结果Activity
 * @author zhongdaxia
 *
 */

public class SearchResultActivity extends Activity {

	ImageButton btn_jmp_setting;
	ListView  listview;
	

	  List<Parcel> finalparcellist = new ArrayList();
	List<Parcel> parcellist = new ArrayList();
	
	
	parcelListAdapter mAdapter;
	
	private Button bt;
    private ProgressBar pg;
    private View moreView;
    
    public int size = 5;
    public int pParcel = 0 ;
    

	CheckBox checkBox_autologin;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 关闭响应
		IntentFilter filter = new IntentFilter();
        filter.addAction("finish");
        registerReceiver(mFinishReceiver, filter);
        
		setContentView(R.layout.activity_searchresult);
		
		// 获取json
	 	Bundle extras = getIntent().getExtras();
	 	String parcelJson = extras.getString("parcellist");
	 	
	 	// jSON 转换
	 	parcellist = JsonToTrackList(parcelJson);
	 	// 初始化处理
		FindView();
		SetClick();
		initData();
		
	}
	
	private void FindView() {
		btn_jmp_setting = (ImageButton) findViewById(R.id.btn_jmp_setting);
		listview = (ListView) findViewById(R.id.ListView_searchresult); 
		moreView = getLayoutInflater().inflate(R.layout.listitem_searchresult_loadmore, null);
	    bt = (Button) moreView.findViewById(R.id.bt_load);
	    pg = (ProgressBar) moreView.findViewById(R.id.pg);
		checkBox_autologin = (CheckBox) findViewById(R.id.checkBox_autologin);
		listview.addFooterView(moreView);
	}
  
	private void SetClick() {
		btn_jmp_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SearchResultActivity.this.finish();
			}
		});
		checkBox_autologin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(checkBox_autologin.isChecked())
				{
//					parcellist.clear();
					int num = finalparcellist.size();
					for(int i = 0 ; i < finalparcellist.size() ; i ++)
					{
						if(finalparcellist.get(i).getPackageStatus().equals("已取"));
						parcellist.add(finalparcellist.get(i));
					}
				}
				else {	
					parcellist = finalparcellist;
				}
				mAdapter.clear();
				mAdapter.loadMore(parcellist);

			}
		});
		 bt.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                pg.setVisibility(View.VISIBLE);// 将进度条可见
	                bt.setVisibility(View.GONE);// 按钮不可见
	                 Handler handler = handler = new Handler();
	                 handler.postDelayed(new Runnable() {
	                    @Override
	                    public void run() {
	                    	
	            			int max = parcellist.size();
	                    	
	                    	if(pParcel == max)
	                    	{
	                    		listview.removeFooterView(moreView);
	                    	}
	                    	else
	                    	{
	                    		List<Parcel> currentparcel  = new ArrayList();
		            			int i = pParcel ;
		            			
		            			pParcel = pParcel + size;
		            			if(pParcel > max)
		            			{
		            				pParcel = max;
		            				listview.removeFooterView(moreView);
		            			}
		            			
		            			for(; i < pParcel; i ++)
		            			{
		            				currentparcel.add(parcellist.get(i));
		            			}
		            			
		                    	mAdapter.loadMore(currentparcel);
		                    	
		                    	int result = max - pParcel ;
		                    	bt.setText("更多    (还有:" + result + "条)" );
		                    	
		                    	
//		                    	bt.setText("更多    (max:" + max + "  loaded:" + pParcel + ")" );
		                        bt.setVisibility(View.VISIBLE);
		                        pg.setVisibility(View.GONE);
	                    	}
	                    	
	                    }
	                }, 1000);
	            }
	        });
	}
	
	private void initData() {
		// 空数据
		if (parcellist == null) {
			// 无数据
		} else {
			List<Parcel> currentparcel  = new ArrayList();
			int i = 0 ;
			int num = 0; 
			if(parcellist.size() < size)
			{
				num = parcellist.size();
			}
			else {
				num = size;
			}
			for(; i < num ; i ++)
			{
				currentparcel.add(parcellist.get(i));
			}
			pParcel = pParcel + i;
			mAdapter = new parcelListAdapter(this, currentparcel);
			listview.setAdapter(mAdapter);
			
			if(num < size)
			{
				listview.removeFooterView(moreView);
			}
			int result = parcellist.size() - pParcel ;
			bt.setText("更多    (还有:" + result + "条)" );
			
//			bt.setText("更多    (max:" + parcellist.size() + "  loaded:" + pParcel + ")" );
		}
	}

	
	/**
	 * jSON TO Track
	 * @param Json
	 * @return
	 */
	public List<Parcel> JsonToTrackList(String Json)
	{

		List<Parcel> pl = new ArrayList();
		
			JSONArray jsonArray = null;
			int count = 0;
			try {
				jsonArray = new JSONArray(Json);
				count = jsonArray.length();
			} catch (JSONException e1) {
				return null;
			}
			
			for(int i=0 ; i < count ; i++)
            {

				Parcel parcelBean = new Parcel();
				try {

				parcelBean.setNumID("" + (i + 1));
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
//                parcelBean.setTerminalId(response.getString("terminalId"));
                
                response=response.getJSONObject("branch");
                parcelBean.setBranchAddress(response.getString("branchAddress"));
//                parcelBean.setBranchId(response.getString("branchId"));
                parcelBean.setBranchName(response.getString("branchName"));
//                parcelBean.setBranchShortAddress(response.getString("branchShortAddress"));	
                
				parcelBean.setUserPhone(jsonArray.getJSONObject(i).getString("userPhone"));
				}
				catch (JSONException e) {
				   parcelBean = null;
    			   e.printStackTrace();
    		    }
				if(parcelBean!= null)
					pl.add(parcelBean);
            }
		return pl;
	}
	
	/**
	 * 关闭响应
	 */
	private BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	           if("finish".equals(intent.getAction())) {
	              finish();
	       }
	    }
	};
}
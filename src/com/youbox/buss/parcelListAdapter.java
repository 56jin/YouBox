package com.youbox.buss;

import java.util.ArrayList;
import java.util.List;
import com.youbox.activity.R;

import android.R.integer;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class parcelListAdapter extends BaseAdapter {

	private ProgressDialog m_dialog;
	private Context mContext;	
	
	List<Parcel> parcel = new ArrayList();
	
    
	public parcelListAdapter(Context mContext,List<Parcel> parcel) {
		this.mContext = mContext;
        this.parcel = parcel;
	}


	@Override
    public int getCount() {
        return parcel.size();
    }

    @Override
    public Parcel getItem(int position) {
        return parcel.get(position);
    }

    @Override
	public long getItemId(int position) {
		return position;
	}
    
    @Override
	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (view == null) {
			
			view = LayoutInflater.from(mContext).inflate(R.layout.listitem_searchresult, null);
			viewHolder = new ViewHolder();		

	        viewHolder. numID         = (TextView)view.findViewById(R.id.numID        );
	        viewHolder. userPhone     = (TextView)view.findViewById(R.id.userPhone    );
	        viewHolder. boxId         = (TextView)view.findViewById(R.id.boxId        );
	        viewHolder. packageStatus = (TextView)view.findViewById(R.id.packageStatus);
	        viewHolder. courierId     = (TextView)view.findViewById(R.id.courierId    );
	        viewHolder. courierCorp   = (TextView)view.findViewById(R.id.courierCorp  );
	        viewHolder. parcelId      = (TextView)view.findViewById(R.id.parcelId     );
	        viewHolder. storeTime     = (TextView)view.findViewById(R.id.storeTime    );
	        viewHolder. getTime       = (TextView)view.findViewById(R.id.getTime      );
	        viewHolder. branchAddress = (TextView)view.findViewById(R.id.branchAddress);
	        viewHolder. branchName    = (TextView)view.findViewById(R.id.branchName   );
	        viewHolder. hardVer       = (TextView)view.findViewById(R.id.hardVer      );
	        viewHolder. name          = (TextView)view.findViewById(R.id.name         );
	        viewHolder. softVer       = (TextView)view.findViewById(R.id.softVer      );
	        viewHolder. status        = (TextView)view.findViewById(R.id.status       );
	        
	    	
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
	
		final Parcel pc = getItem(position);
        
        viewHolder. numID.setText(pc.getNumID());
        viewHolder. userPhone    .setText(pc.getUserPhone());
        viewHolder. boxId        .setText(pc.getBoxId());
        viewHolder. packageStatus.setText(pc.getPackageStatus());
        viewHolder. courierId    .setText(pc.getCourierId());
        viewHolder. courierCorp  .setText(pc.getCourierCorp());
        viewHolder. parcelId     .setText(pc.getParcelId());
        viewHolder. storeTime    .setText(pc.getStoreTime());
        viewHolder. getTime      .setText(pc.getGetTime());
        viewHolder. branchAddress.setText(pc.getBranchAddress());
        viewHolder. branchName   .setText(pc.getBranchName());
        viewHolder. hardVer      .setText(pc.getHardVer());
        viewHolder. name         .setText(pc.getName());
        viewHolder. softVer      .setText(pc.getSoftVer());
        viewHolder. status       .setText(pc.getStatus());
		
		return view;

	}
    
    public void loadMore(List<Parcel> pa)
    {
    	if(null != pa)
    	{
        	for(int i = 0 ; i < pa.size() ; i ++)
        	{
            	parcel.add(pa.get(i));
        	}
    	}
    	notifyDataSetChanged();
    }
    
    public void clear()
    {
    	parcel.clear();
    }
    
	final static class ViewHolder {	 
		TextView  numID;
		TextView  userPhone;
		TextView  boxId;
		TextView  packageStatus;
		TextView  courierId;
		TextView  courierCorp;
		TextView  parcelId;
		TextView  storeTime;
		TextView  getTime;
		TextView  branchAddress;
		TextView  branchName;
		TextView  hardVer;
		TextView  name;
		TextView  softVer;
		TextView  status;
	}
}

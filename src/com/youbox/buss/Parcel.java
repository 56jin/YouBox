package com.youbox.buss;

import android.renderscript.Element;

public class Parcel {
	public String getNumID() {
		return numID;
	}
	public void setNumID(String numID) {
		this.numID = numID;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public String getPackageStatus() {
		return packageStatus;
	}
	public void setPackageStatus(String packageStatus) {
		
		if(packageStatus.equals("0"))
		{
			packageStatus = "Î´È¡";
		}
		else if(packageStatus.equals("1"))
		{
			packageStatus = "ÒÑÈ¡";
		}
		
		this.packageStatus = packageStatus;
	}
	public String getCourierId() {
		return courierId;
	}
	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}
	public String getCourierCorp() {
		return courierCorp;
	}
	public void setCourierCorp(String courierCorp) {
		this.courierCorp = courierCorp;
	}
	public String getParcelId() {
		return parcelId;
	}
	public void setParcelId(String parcelId) {
		this.parcelId = parcelId;
	}
	public String getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(String storeTime) {
		this.storeTime = storeTime;
	}
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getHardVer() {
		return hardVer;
	}
	public void setHardVer(String hardVer) {
		this.hardVer = hardVer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSoftVer() {
		return softVer;
	}
	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}
	public String getStatus() {
		
		
		
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String numID;
	private String userPhone;
	private String boxId;
	private String packageStatus;
	private String courierId;
	private String courierCorp;
	private String parcelId;
	private String storeTime;
	private String getTime;
	private String branchAddress;
	private String branchName;
	private String hardVer;
	private String name;
	private String softVer;
	private String status;

}

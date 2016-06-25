package com.it.zjdd.bean;


public class Update {
	
	private int versionCode=255;
	private String versionName="v2.5.5 (1605101724)";
	private String downloadUrl="http://www.oschina.net/uploads/osc-android-app-2.5.5-release.apk";
	private String updateLog="OSChina.net 新版发布 v2.5.5 <br/> <br/> 1、对动弹列表进行一部分优化<br/> 2、性能方面调整<br/> 3、(就不告诉你……)<br/> <br/> <br/> 大小：8.8M";
	private String coverUpdate="false";
	private String coverStartDate="2014-03-05";
	private String coverEndDate="2014-03-05";
	private String coverURL;
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getUpdateLog() {
		return updateLog;
	}
	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}
	public String getCoverUpdate() {
		return coverUpdate;
	}
	public void setCoverUpdate(String coverUpdate) {
		this.coverUpdate = coverUpdate;
	}
	public String getCoverStartDate() {
		return coverStartDate;
	}
	public void setCoverStartDate(String coverStartDate) {
		this.coverStartDate = coverStartDate;
	}
	public String getCoverEndDate() {
		return coverEndDate;
	}
	public void setCoverEndDate(String coverEndDate) {
		this.coverEndDate = coverEndDate;
	}
	public String getCoverURL() {
		return coverURL;
	}
	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}
	@Override
	public String toString() {
		return "Update [versionCode=" + versionCode + ", versionName="
				+ versionName + ", downloadUrl=" + downloadUrl + ", updateLog="
				+ updateLog + ", coverUpdate=" + coverUpdate
				+ ", coverStartDate=" + coverStartDate + ", coverEndDate="
				+ coverEndDate + ", coverURL=" + coverURL + "]";
	}
	

}

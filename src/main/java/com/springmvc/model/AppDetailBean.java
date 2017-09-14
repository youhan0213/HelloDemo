package com.springmvc.model;

/**
 * @author li
 *
 */
public class AppDetailBean implements Comparable<AppDetailBean> {
	private Integer  onUse;
	private Long createTime;
	private String createBy;
	private Long updateTime = System.currentTimeMillis();
	private String updateBy;
	private String appType;
	private String iconAddress;
	private Long appId;
	private String appName;
	private String packageName;
	private String menuName;
	private Long menuId;
	private String versionCode;
	private String area;
	private String versionName;
	private String datetime;
	private Long appVersionId;
	private String appGalleryName;
	private Integer isDefault=1;
	private String fileAddress;
	private String linuxLink;
	private Integer sort;
	private Integer payType;
	private int divisionProportion;
	private double price;
	private Integer isDiscount;
	private double discountPrice;
	private Long startDiscount;
	private Long endDiscount;
	private String startTime;
	private String endTime;
	private int appPushOnUse;
	
	public Integer getOnUse() {
		return onUse;
	}
	public void setOnUse(Integer onUse) {
		this.onUse = onUse;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getIconAddress() {
		return iconAddress;
	}
	public void setIconAddress(String iconAddress) {
		this.iconAddress = iconAddress;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public Long getAppVersionId() {
		return appVersionId;
	}
	public void setAppVersionId(Long appVersionId) {
		this.appVersionId = appVersionId;
	}
	public String getAppGalleryName() {
		return appGalleryName;
	}
	public void setAppGalleryName(String appGalleryName) {
		this.appGalleryName = appGalleryName;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public String getFileAddress() {
		return fileAddress;
	}
	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	public String getLinuxLink() {
		return linuxLink;
	}
	public void setLinuxLink(String linuxLink) {
		this.linuxLink = linuxLink;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public int getDivisionProportion() {
		return divisionProportion;
	}
	public void setDivisionProportion(int divisionProportion) {
		this.divisionProportion = divisionProportion;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(Integer isDiscount) {
		this.isDiscount = isDiscount;
	}
	public double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Long getStartDiscount() {
		return startDiscount;
	}
	public void setStartDiscount(Long startDiscount) {
		this.startDiscount = startDiscount;
	}
	public Long getEndDiscount() {
		return endDiscount;
	}
	public void setEndDiscount(Long endDiscount) {
		this.endDiscount = endDiscount;
	}
	@Override
	public int compareTo(AppDetailBean obj) {
		
		return this.sort-obj.sort;
	}
	public int getAppPushOnUse() {
		return appPushOnUse;
	}
	public void setAppPushOnUse(int appPushOnUse) {
		this.appPushOnUse = appPushOnUse;
	}
	
	
	
	
	
	
	

}

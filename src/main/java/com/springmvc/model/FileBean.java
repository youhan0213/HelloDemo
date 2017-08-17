package com.springmvc.model;

public class FileBean {

	private String fileName;
	private String filePath;
	private Long releaseDate;
	private String releaseDate_str;
	private Long fileSize;
	private String digitalSignature;
	
	
	private int sort;
	
	
	private String packageName;
	
	private long versionCode;
	
	private String versionName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Long releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getReleaseDate_str() {
		return releaseDate_str;
	}
	public void setReleaseDate_str(String releaseDate_str) {
		this.releaseDate_str = releaseDate_str;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public long getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(long versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getDigitalSignature() {
		return digitalSignature;
	}
	public void setDigitalSignature(String digitalSignature) {
		this.digitalSignature = digitalSignature;
	}
	
	
}

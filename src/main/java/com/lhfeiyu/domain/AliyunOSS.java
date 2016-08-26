package com.lhfeiyu.domain;
/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务模型层：阿里云OSS服务器业务对象 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
public class AliyunOSS {

	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketName;
	private String bucketEndpoint;
    
	private String fileName;
	private String fileRealPath;
	
	public static AliyunOSS buildOSS(String fileName, String fileRealPath,
    		String oss_endpoint, String oss_accessKeyId, String oss_accessKeySecret, 
    		String oss_bucketName, String oss_bucketEndpoint){
		
		AliyunOSS oss = new AliyunOSS();
		oss.setFileName(fileName);
		oss.setFileRealPath(fileRealPath);
		oss.setEndpoint(oss_endpoint);
		oss.setAccessKeyId(oss_accessKeyId);
		oss.setAccessKeySecret(oss_accessKeySecret);
		oss.setBucketName(oss_bucketName);
		oss.setBucketEndpoint(oss_bucketEndpoint);
		
		return oss;
	}
	
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getBucketEndpoint() {
		return bucketEndpoint;
	}
	public void setBucketEndpoint(String bucketEndpoint) {
		this.bucketEndpoint = bucketEndpoint;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileRealPath() {
		return fileRealPath;
	}
	public void setFileRealPath(String fileRealPath) {
		this.fileRealPath = fileRealPath;
	}
    
    
}
	
	
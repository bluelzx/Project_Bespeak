package com.lhfeiyu.thirdparty.aliyunoss.business;

import java.io.File;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.lhfeiyu.domain.AliyunOSS;

/**
 * This sample demonstrates how to get started with basic requests to Aliyun OSS 
 * using the OSS SDK for Java.
 */
public class OSS {
	
	/*public static void main(String[] args) {
		String path = "D:\\workspace\\LhBase\\src\\main\\webapp\\file\\default\\1458201044390__20160112132715844_thumb.jpg";
		uploadObject("1458201044390__20160112132715844_thumb.jpg", path);
	}*/
	
    private static OSSClient client = null;
    
    public static String uploadObject(AliyunOSS oss){
    	String fileName = oss.getFileName();
    	String fileRealPath = oss.getFileRealPath();
		String oss_endpoint = oss.getEndpoint();
		String oss_accessKeyId = oss.getAccessKeyId();
		String oss_accessKeySecret = oss.getAccessKeySecret();
		String oss_bucketName = oss.getBucketName();
		String oss_bucketEndpoint = oss.getBucketEndpoint();
        return uploadObject(fileName, fileRealPath, oss_endpoint, oss_accessKeyId, oss_accessKeySecret, oss_bucketName, oss_bucketEndpoint);
    }

    public static String uploadObject(String fileName, String fileRealPath,
    		String oss_endpoint, String oss_accessKeyId, String oss_accessKeySecret, 
    		String oss_bucketName, String oss_bucketEndpoint){
        client = new OSSClient(oss_endpoint, oss_accessKeyId, oss_accessKeySecret);
        String key = fileName;
        String url = null;
        try {
        	File file  = new File(fileRealPath);
        	if(!file.exists())return null;
            System.out.println("Uploading a new object to OSS from a file\n");
            client.putObject(new PutObjectRequest(oss_bucketName, key, file));//Upload an object to your bucket
            System.out.println("upload success success success! \n");
            url = oss_bucketEndpoint+"/"+fileName;
        } catch (OSSException oe) {
        	oe.printStackTrace();
            System.out.println("OSSException_Error Message:    " + oe.getErrorCode());
            System.out.println("OSSException_Error Code:       " + oe.getErrorCode());
            System.out.println("OSSException_Request ID:       " + oe.getRequestId());
            System.out.println("OSSException_Host ID:          " + oe.getHostId());
            return null;
        } catch (ClientException ce) {
        	ce.printStackTrace();
            System.out.println("ClientException_Error Message: " + ce.getMessage());
		} finally {
            client.shutdown();// Do not forget to shut down the client finally to release all allocated resources.
        }
        return url;
    }

}

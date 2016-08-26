package com.lhfeiyu.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具类包：加密工具类 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
public class Md5Util {

	/**
	 * 将字符串进行MD5加密，并返回加密后了字符串
	 * @param str 待MD5加密字符串
	 * @return MD5加密字符串
	 */
	public static String encrypt(String str) {   
		if(null != str && !"".equals(str)){
			try {   
				MessageDigest md = MessageDigest.getInstance("MD5");   
			    md.update(str.getBytes());   
			    str = new BigInteger(1, md.digest()).toString(16);   
			   } catch (Exception e) {   
				   e.printStackTrace(); 
				   str = "";
			   }   
		}else{
			str = "";
		}
		return str;   
	}
	
	
	/** 
	 * BASE64解码为 byte数组
     * @param bytes 
     * @return byte[]
     */  
    public static byte[] base64Decode(final byte[] bytes) {  
        return Base64.decodeBase64(bytes);  
    }  
  
    /** 
     * 二进制数据编码为BASE64字符串 
     * @param bytes 
     * @return String 结果字符串
     */  
    public static String base64Encode(final byte[] bytes) {  
        return new String(Base64.encodeBase64(bytes));  
    }  
	
}

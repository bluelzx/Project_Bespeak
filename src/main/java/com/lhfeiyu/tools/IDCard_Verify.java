package com.lhfeiyu.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

import com.alibaba.fastjson.JSONObject;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具类com.lhfeiyu.tools/IDCard_Verify.java <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 王家明 <p>
 * <strong> 编写时间：</strong>2016年7月25日 上午11:27:12<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
public class IDCard_Verify {
	private final static String mall_id="110552";
	private final static String appkey="a853b07659475426bea133958a480dad";
	private final static String url="http://121.41.42.121:8080/v3/id2-server?";								
	
	public JSONObject idcard_verify(String realname,String idcard){
		idcard=idcard.toLowerCase();
		long tm=System.currentTimeMillis();
		String md5_param=mall_id+realname+idcard+tm+appkey;
		String sign=md5(md5_param);
		String param=new StringBuffer().append("mall_id="+mall_id)
				.append("&realname="+realname)
				.append("&idcard="+idcard)
				
				.append("&tm="+tm)
				.append("&sign="+sign).toString();
		String url_v=url+param;
		try {
			url_v=url_v.replace(realname,URLEncoder.encode(realname,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonString=url2string(url_v);
		
		JSONObject result=JSONObject.parseObject(jsonString);
		System.out.println(result);
		//int status=Integer.parseInt(result.getString("status"));
		//int code=Integer.parseInt(result.getJSONObject("data").getString("code"));
		//String message=result.getJSONObject("data").getString("message");
				
			
		return result;
	}
	private String md5(String s){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	private String url2string(String url){
		StringBuffer sb=new StringBuffer();
		try {
			InputStream is=new URL(url).openStream();
			byte[] buf=new byte[1024*10];
			int len=0;
			while((len=is.read(buf, 0, 1024*10))>0){
				sb.append(new String(buf,0,len));
			}
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}

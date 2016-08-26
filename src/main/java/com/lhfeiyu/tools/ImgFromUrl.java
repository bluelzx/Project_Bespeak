package com.lhfeiyu.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.lhfeiyu.domain.AliyunOSS;
import com.lhfeiyu.thirdparty.wx.business.AuthAccess;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：从网络获取图片到本地 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.tools.ImgFromUrl <p>
 */
public class ImgFromUrl {
	
	/**
	 * 默认文件类型：jpg
	 */
	public static String fileType = "jpg";
	
	/** 测试  */
	public static void main(String[] args) {
		String url = "http://www.baidu.com/img/baidu_sylogo1.gif";
		byte[] btImg = getImageFromNetByUrl(url);
		if (null != btImg && btImg.length > 0) {
			System.out.println("读取到：" + btImg.length + " 字节");
			String fileName = "百度.gif";
			String filePath = writeImageToDisk("", btImg, fileName, null);
			System.out.println(filePath);
		} else {
			System.out.println("没有从该连接获得内容");
		}
	}
	
	/**
	 * 根据URL地址将资源下载到本地服务器，返回图片存放路径
	 * @param basePath 文件基础路径
	 * @param mediaId 微信服务器附件ID
	 * @param url 资源URL路径
	 * @param oss AliyunOSS 阿里云OSS服务器对象（参数配置）
	 * @return path 图片存放路径
	 */
	public static String saveImgFromUrl(String basePath, String mediaId, String url, AliyunOSS oss){
		String path = "";
		if(Check.isNotNull(mediaId)){
			String access_token = AuthAccess.getWxDataFromProperty("access_token");//从Property文件中获取access_token,如果文件中没有，则会远程获取
			url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+mediaId;
		}else if(Check.isNull(url)){return "";}
		byte[] btImg = getImageFromNetByUrl(url);
		if (null != btImg && btImg.length > 0) {
			//System.out.println("读取到：" + btImg.length + " 字节");
			String fileName = CommonGenerator.getSerialByDate();
			fileName += "."+fileType;
			path = writeImageToDisk(basePath, btImg, fileName, oss);
		} else {
			System.out.println("没有从该连接获得内容");
		}
		return path;
	}

	/**
	 * 将图片写入到磁盘</p>
	 * String relativePath = "file" + File.separator + "wx" + File.separator + fileName;
	 * @param basePath 基本路径
	 * @param img 图片数据流
	 * @param fileName 文件名称
	 * @param oss AliyunOSS 阿里云OSS服务器对象（参数配置）
	 * @return path 图片存放路径
	 */
	public static String writeImageToDisk(String basePath, byte[] img, String fileName, AliyunOSS oss) {
		String filePath = "";
		try {
			//path = "D:/file/default/" + fileName;
			String relativePath = "file" + File.separator + "wx" + File.separator + fileName;
			String realPath = basePath + relativePath;
			//path = "E:/3_project/LhBase/file/wx/" + fileName;
			File file = new File(realPath);
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
			System.out.println("ImgFromUrl-writeImageToDisk图片已经保存：" + realPath);
			
			if(null != oss){
				oss.setFileName(fileName);
				oss.setFileRealPath(realPath);
				//String picOSSUrl = OSS.uploadObject(oss);
				//picOSSUrl = picOSSUrl.replaceFirst("oss", "img");//访问图片资源
				//filePath = picOSSUrl;
			}else{
				filePath = realPath;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}

	/**
	 * 根据地址获得数据的字节流
	 * @param strUrl  网络连接地址
	 * @return byte[]
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			//System.out.println(conn.getHeaderField("Content-Type"));
			//System.out.println(conn.getHeaderField("Content-disposition"));
			//String name = conn.getHeaderField("filename");
			String contentType = conn.getHeaderField("Content-Type");
			//System.out.println(name);
			if(Check.isNotNull(contentType)){
				//attachment; filename= OR text/plain
				fileType = contentType.replace("image/", "");
				if(fileType.equals("text/plain")){
					fileType = "jpg";
					System.out.println("getImageFromNetByUrl wx upload pic content-type:text/plain.fix it!");
				}
			}
			/*if(Check.isNotNull(name)){
				fileType = name.replace("attachment; filename=", "");
			}*/
			
			byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 * @param inStream  输入流
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}

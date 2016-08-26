package com.lhfeiyu.util;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.lhfeiyu.po.domain.User;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具类包：request工具类 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
public class RequestUtil {

	/**
	 * 从request中取得参数，装于HashMap中
	 * @param request
	 * @return HashMap<String,Object>
	 */
    public static HashMap<String,Object> getRequestParam(HttpServletRequest request) {
        HashMap<String,Object> data = new HashMap<String,Object>();
        Enumeration<String> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            data.put(key, request.getParameter(key));
        }
        return data;
    }

    /**
     * 将页面取得的字符串id转化成整型数组
     * @param idStr
     * @return
     */
    public static int[] pkeyStrToIntArray(String idStr){
        String[] str = idStr.split(",");
        int[] ids = new int[str.length];
        for(int i=0;i<str.length;i++){
            ids[i] = Integer.parseInt(str[i]);
        }
        return ids;
    }

    /**  取得当前登录用户ID  */
    public static String getLoginUserId(HttpServletRequest request){
           return (String) request.getSession().getAttribute("userId");
    }
    
    /**  取得当前登录用户对象  */
    public static User getLoginUser(HttpServletRequest request){
           return (User) request.getSession().getAttribute("user");
    }
}

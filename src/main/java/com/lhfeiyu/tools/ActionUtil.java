package com.lhfeiyu.tools;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Provider;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AdminService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：控制层工具类 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.tools.ActionUtil <p>
 */
public class ActionUtil {
	
	/**
	 * 检查Session是否存在user，存在即返回，不存在则返回null
	 * @param session
	 * @return User session-user
	 */
	public static User checkSession4User(HttpSession session){
		User user = null;
		Object userObj = session.getAttribute("user");
		if(null != userObj){
			user = (User)userObj;
		}
		return user;
	}
	public static User checkSession4User(HttpServletRequest request){
		return checkSession4User(request.getSession());
	}
	/**
	 * 检查Session是否存在user，存在即返回，不存在则返回null
	 * @param session
	 * @return User session-user
	 */
	public static Provider checkSession4Provider(HttpSession session){
		Provider provider = null;
		Object providerObj = session.getAttribute("provider");
		if(null != providerObj){
			provider = (Provider)providerObj;
		}
		return provider;
	}
	public static Provider checkSession4Provider(HttpServletRequest request){
		return checkSession4Provider(request.getSession());
	}
	
	/**
	 * 检查Session是否存在admin，存在即返回，不存在则返回null
	 * @param session
	 * @return Admin session-admin
	 */
	public static Admin checkSession4Admin(HttpSession session){
		Admin admin = null;
		Object adminObj = session.getAttribute("admin");
		if(null != adminObj){
			admin = (Admin)adminObj;
		}
		return admin;
	}
	public static Admin checkSession4Admin(HttpServletRequest request){
		return checkSession4Admin(request.getSession());
	}
	/**
	 * 检查Session是否存在shop，存在即返回，不存在则返回null
	 * @param session
	 * @return Shop session-shop
	 */
	public static Shop checkSession4Shop(HttpSession session){
		Shop shop = null;
		Object adminObj = session.getAttribute("shop");
		if(null != adminObj){
			shop = (Shop)adminObj;
		}
		return shop;
	}
	public static Shop checkSession4Shop(HttpServletRequest request){
		return checkSession4Shop(request.getSession());
	}
	/**
	 * 检查Session是否存在userId，存在即返回，不存在则返回null
	 * @param session
	 * @return int session-userId
	 */
	public static int checkSession4UserId(HttpSession session){
		int userId = 0;
		Object userIdObj = session.getAttribute("userId");
		if(null != userIdObj){
			userId = (Integer)userIdObj;
		}
		return userId;
	}
	public static int checkSession4UserId(HttpServletRequest request){
		return checkSession4UserId(request.getSession());
	}
	
	/**
	 * 检查Session是否存在adminId，存在即返回，不存在则返回null
	 * @param session
	 * @return int session-adminId
	 */
	public static int checkSession4AdminId(HttpSession session){
		int adminId = 0;
		Object adminIdObj = session.getAttribute("adminId");
		if(null != adminIdObj){
			adminId = (Integer)adminIdObj;
		}
		return adminId;
	}
	public static int checkSession4AdminId(HttpServletRequest request){
		return checkSession4AdminId(request.getSession());
	}
	
	/**
	 * 检查Session是否存在userId，存在则数据库中查询最新user对象返回，不存在则返回null
	 * @param session
	 * @param userService 用户Service
	 * @return User DB-user
	 */
	public static User loadUserBySessionUserId(HttpSession session, UserService userService){
		User user = null;
		Object userIdObj = session.getAttribute("userId");
		if(null != userIdObj){
			Integer userId = (Integer)userIdObj;
			user = userService.selectByPrimaryKey(userId);
		}
		return user;
	}
	public static User loadUserBySessionUserId(HttpServletRequest request, UserService userService){
		return loadUserBySessionUserId(request.getSession(), userService);
	}
	
	/**
	 * 检查Session是否存在adminId，存在则数据库中查询最新admin对象返回，不存在则返回null
	 * @param session
	 * @param adminService 后台用户Service
	 * @return Admin DB-admin
	 */
	public static Admin loadAdminBySessionAdminId(HttpSession session, AdminService adminService){
		Admin admin = null;
		Object adminIdObj = session.getAttribute("adminId");
		if(null != adminIdObj){
			Integer adminId = (Integer)adminIdObj;
			admin = adminService.selectByPrimaryKey(adminId);
		}
		return admin;
	}
	public static Admin loadUserBySessionUserId(HttpServletRequest request, AdminService adminService){
		return loadAdminBySessionAdminId(request.getSession(), adminService);
	}
	
	/**
	 * 得到request中的所有参数（包括分页相关参数）</p>
	 * Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
	 * @param request
	 * @return map
	 */
	public static Map<String, Object> getAllParam(HttpServletRequest request){
		return Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
	}
	
	
	
	/** 具体业务逻辑对应方法  */
	
	/** 组建动态URL */
	public static String buildPromoterUrl(String url, String r){
		String symbol = "?";
		if(url.indexOf('?') > 0)symbol = "&";
		if(Check.isNotNull(r))url += symbol+"r="+r;
		return url;
	}
	
}
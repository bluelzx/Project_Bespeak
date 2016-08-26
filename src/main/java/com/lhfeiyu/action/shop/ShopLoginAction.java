package com.lhfeiyu.action.shop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.base.BasePage;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：后台登陆 Admin
 * <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华
 * <p>
 * <strong> 编写时间：</strong> 2016年4月13日09:21:01
 * <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com
 * <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0
 * <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>
 * 包路径：com.lhfeiyu.action.back.sys.BackLoginAction
 * <p>
 */
@Controller
public class ShopLoginAction {

	@Autowired
	private ShopService shopService;

	private static Logger logger = Logger.getLogger("R");

	/**
	 * 后台登陆页面
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/shopLogin", method = RequestMethod.GET)
	public ModelAndView shopLogin(HttpServletRequest request, ModelMap modelMap) {
		String path = LhPage.shop_login;
		try {
			Shop sessionShop = ActionUtil.checkSession4Shop(request.getSession());// 验证session中的user，存在即返回

		} catch (Exception e) {
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-Login-PAGE-/back/page/login-加载登陆页面出现异常", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	@RequestMapping(value = "/shop/main", method = RequestMethod.GET)
	public ModelAndView shopMain(HttpServletRequest request, ModelMap modelMap, HttpSession session, @RequestParam(required = false) String r) {
		String path = LhPage.shop_main;
		try {
			Shop sessionShop = ActionUtil.checkSession4Shop(session);
			if (null == sessionShop) {
				// return Result.userSessionInvalid(modelMap,"/shopLogin");
				return new ModelAndView("redirect:/shopLogin");
			}
			modelMap.put("shop", sessionShop);
		} catch (Exception e) {
			path = BasePage.back_error;
			Result.catchError(e, logger, "LH_ERROR-Main-PAGE-/shop/main-加载后台主页面出现异常", modelMap);
		}
		return new ModelAndView(path);
	}

	/**
	 * 后台执行登陆 , 只接受POST请求。同一IP最多能登录错误20次（Session范围）
	 * 
	 * @param request
	 * @param session
	 * @param verificationCode
	 *            验证码
	 * @param loginAccount
	 *            登陆账号
	 * @param password
	 *            登陆密码
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/doShopLogin", method = RequestMethod.POST)
	public JSONObject doBackLogin(HttpServletRequest request, HttpSession session, @RequestParam String verificationCode, @RequestParam String phone, @RequestParam String password,
			ModelMap modelMap) {
		JSONObject json = new JSONObject();
		try {
			int errorNum = 1;
			Object errorNumObj = session.getAttribute("errorNum");
			if (null != errorNumObj) {// 已经记录了该IP有输入错误记录
				errorNum = (Integer) errorNumObj;
				if (errorNum > 20) {
					return Result.failure(json, "输入的错误次数太多，无法进行登陆", "max_errors");
				}
			}
			String ip = request.getRemoteAddr();
			Object codeObj = session.getAttribute("randomCode");
			if (null == codeObj) {
				return Result.failure(json, "验证码输入错误", "randomCode_error");// 页面需刷新验证码
			}
			String randomCode = codeObj.toString();
			if (!(randomCode.equalsIgnoreCase(verificationCode)) && !"1".equals(verificationCode)) {// TODO
																									// 1:方便测试，后期删除
				return Result.failure(json, "验证码输入错误", "randomCode_error");// 页面需刷新验证码
			}
			phone = phone.trim();
			password = password.trim();// 去除空格
			if (!Check.isNotNull(phone)) {
				return Result.failure(json, "用户名和密码不能为空", "input_isneed");
			}
			shopService.doLogin(json, phone, password, ip, true);// 执行登陆
			if (json.containsKey("success")) {
				session.removeAttribute("errorNum");// 清除登陆错误次数
				Shop shop = (Shop) json.get("shop");
				session.setAttribute("shop", shop);// 将用户存入Session
				session.setAttribute("shopId", shop.getId());
				modelMap.put("shop", shop);
			} else {
				session.setAttribute("errorNum", ++errorNum);// 增加登陆错误次数
				return Result.failure(json, "用户名或密码输入错误", "input_error");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Login-AJAX-/back/doBackLogin-后台执行登陆出现异常", json);
		}
		return json;
	}

	// 商家个人中心
	@RequestMapping(value = "/shop/shopCenters/{shopId}")
	public ModelAndView shopCenters(ModelMap modelMap, HttpServletRequest request, @PathVariable Integer shopId, @RequestParam(required = false) String r, HttpSession session) {
		String path = LhPage.shopCenters;
		try {
			Shop sessionShop = ActionUtil.checkSession4Shop(session);
			if (null == sessionShop) {
				return new ModelAndView("redirect:/shopLogin");
			}
			if(!Check.integerEqual(shopId, sessionShop.getId())){ //如果不是当前用户
				return new ModelAndView("redirect:/shopLogin");
			}
			// JSONObject json = new JSONObject();
			Shop shop = shopService.selectByPrimaryKey(sessionShop.getId());
			modelMap.put("shop", shop);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/shopCenters" + "/" + shopId, modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/**
	 * 保存地图标注点
	 */
	@ResponseBody
	@RequestMapping(value = "/shop/saveOrCanelCoordinate", method = RequestMethod.POST)
	public JSONObject saveOrCanelCoordinate(HttpServletRequest request, ModelMap modelMap, HttpSession session, @RequestParam(required = false) String r) {
		JSONObject json = new JSONObject();
		try {
			Shop sessionShop = ActionUtil.checkSession4Shop(session);
			if (null == sessionShop) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}

			Map<String, Object> map = ActionUtil.getAllParam(request);
			// type:1,id:id,coordinate:coordinate
			Integer type = Integer.valueOf((String) map.get("type"));
			Integer id = Integer.valueOf((String) map.get("id"));
			Shop shop = shopService.selectByPrimaryKey(id);
			if (type == 1) {
				String coordinate = (String) map.get("coordinate");
				shop.setCoorDinate(coordinate);
			} else if (type == 2) {
				shop.setCoorDinate("");
			}
			shopService.updateByPrimaryKeySelective(shop);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/saveOrCanelCoordinate", json);
		}
		// System.out.println(json);
		return Result.success(json);
	}
	/**
	 * 页面 ：修改商家信息
	 * */
	// 商家个人中心
		@RequestMapping(value = "/shop/shoperUpdatePwd")
		public ModelAndView shoperUpdatePwd(ModelMap modelMap, HttpServletRequest request
				, @RequestParam(required = false) String r, 
				HttpSession session) {
			String path = LhPage.setBusiness;
			try {
				Shop sessionShop = ActionUtil.checkSession4Shop(session);
				if (null == sessionShop) {
					return new ModelAndView("redirect:/shopLogin");
				}
				// JSONObject json = new JSONObject();
				Shop shop = shopService.selectByPrimaryKey(sessionShop.getId());
				modelMap.put("shop", shop);
			} catch (Exception e) {
				Result.catchError(e, logger, this.getClass().getName() + "/shopCenters", modelMap);
			}
			return new ModelAndView(path, modelMap);
		}
			/**
			 * 页面 ：修改商家信息
			 * */
			// 商家个人中心
			@RequestMapping(value = "/shop/shoperUpdate")
			public ModelAndView shoperUpdate(ModelMap modelMap, HttpServletRequest request
					, @RequestParam(required = false) String r, 
					HttpSession session) {
				String path = LhPage.businessMiddle;
				try {
					Shop sessionShop = ActionUtil.checkSession4Shop(session);
					if (null == sessionShop) {
						return new ModelAndView("redirect:/shopLogin");
					}
					// JSONObject json = new JSONObject();
					Shop shop = shopService.selectByPrimaryKey(sessionShop.getId());
					modelMap.put("shop", shop);
				} catch (Exception e) {
					Result.catchError(e, logger, this.getClass().getName() + "/shopCenters", modelMap);
				}
				return new ModelAndView(path, modelMap);
		}
	/**
	 * 修改商家信息
	 * */ 
	@ResponseBody
	@RequestMapping(value = "/shop/updateShoper", method = RequestMethod.POST)
	public JSONObject updateShoper(HttpServletRequest request, ModelMap modelMap
			,HttpSession session,@PathVariable Shop shop,
			@RequestParam(required = false) String r) {
		JSONObject json = new JSONObject();
		try {
			Shop sessionShop = ActionUtil.checkSession4Shop(session);
			if (null == sessionShop) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			
			
			shopService.updateByPrimaryKeySelective(shop);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/saveOrCanelCoordinate", json);
		}
		// System.out.println(json);
		return Result.success(json);
	}
	
	
	
	

}
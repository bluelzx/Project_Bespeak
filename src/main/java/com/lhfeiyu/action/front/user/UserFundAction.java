package com.lhfeiyu.action.front.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.domain.YtxMessage;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.UserFundService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.SendMsgUtil;

@Controller
public class UserFundAction {
	@Autowired
	private UserFundService ufService;
	@Autowired
	private AuthCheckService authCheckService;
	@Autowired
	private UserService userService;

	private static Logger logger = Logger.getLogger("R");

	/**
	 * 申请提现
	 */
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public ModelAndView withdraw(ModelMap modelMap, HttpSession session, @RequestParam(required = false) String r, HttpServletRequest request) {
		String path = LhPage.applyWithdrawals;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if (null == sessionUser) {
				String jumpUrl = "/withdraw";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", sessionUser.getId());
			List<UserFund> userFundList = ufService.selectListByCondition(map);
			if (userFundList.size() > 0) {
				UserFund userFund = userFundList.get(0);
				modelMap.put("userFund", userFund);
			}
			/*
			 * map.clear(); map.put("parentId", 1); map.put("id",2);
			 * List<SysDict> sysDictList =
			 * sysDictService.selectListByCondition(map); if(sysDictList.size()
			 * > 0){ SysDict sysDict = sysDictList.get(0);
			 * modelMap.put("sysDict", sysDict); }
			 */
			modelMap.put("user", sessionUser);
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_绑定提现银行卡页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/**
	 * 绑定提现银行卡
	 */
	@RequestMapping(value = "/bindBankCard", method = RequestMethod.GET)
	public ModelAndView bindBankCard(ModelMap modelMap, HttpSession session, @RequestParam(required = false) String r, HttpServletRequest request) {
		String path = LhPage.bindBankCard;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if (null == sessionUser) {
				String jumpUrl = "/bindBankCard";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", sessionUser.getId());
			List<UserFund> userFundList = ufService.selectListByCondition(map);
			if (userFundList.size() > 0) {
				UserFund userFund = userFundList.get(0);
				modelMap.put("userFund", userFund);
			}
			modelMap.put("user", sessionUser);
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_绑定提现银行卡页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/**
	 * 修改用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateUserFund", method = RequestMethod.POST)
	public JSONObject addOrUpdateUserFund(@ModelAttribute UserFund userFund, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			userFund.setMainStatus(1);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			if (null == userFund.getId()) {// 添加
				if (null == userFund.getCreatedAt()) {
					userFund.setCreatedAt(new Date());
				}
				userFund.setCreatedBy(sessionUser.getUsername());
				String userFundPassword = Md5Util.encrypt(userFund.getPayPassword());
				userFund.setPayPassword(userFundPassword);
				ufService.insert(userFund);
				json.put("status", "success");
				json.put("msg", "操作成功");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_新增修改用户资金表出现异常_", json);
		}
		return Result.success(json);
	}

	/**
	 * 绑定用户银行卡
	 **/
	@ResponseBody
	@RequestMapping(value = "/bindBankUserCard")
	public JSONObject bindBankUserCard(HttpServletRequest request, @ModelAttribute UserFund userFund) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", sessionUser.getId());
			List<UserFund> userFundList = ufService.selectListByCondition(map);
			if (userFundList.size() > 0) {// 查看用户是否已绑定过银行卡,有就修改,没有就新增
				UserFund userFund1 = userFundList.get(0);
				userFund.setId(userFund1.getId());
				ufService.updateBankById(userFund);
			} else {
				ufService.insert(userFund);
			}
			json.put("status", "success");
			json.put("msg", "操作成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_绑定用户银行卡出现异常_", json);
		}
		return Result.success(json);
	}

	
	
	
	private JSONObject checkVerifycode(HttpSession session, String code) {
		JSONObject json = new JSONObject();
		try {
			int verifyCodeCheckNum = 1;
			Object verifyCodeCheckNumObj = session.getAttribute("verifyCodeCheckNum");
			if (null != verifyCodeCheckNumObj) {
				verifyCodeCheckNum = (Integer) verifyCodeCheckNumObj;
				if (verifyCodeCheckNum > 20) {
					json.put("status", "max_errors");
					json.put("msg", "请求验证次数太多，请直接联系管理员进行相应操作！");
					return Result.success(json);
				}
			}
			Object verifycodeObj = session.getAttribute("verifycode");
			if (null == verifycodeObj) {
				json.put("status", "failure");
				json.put("msg", "验证码已过期，请重新获取验证码!");
				return Result.success(json);
			} else {
				String verifycode = (String) verifycodeObj;
				if (verifycode.equals(code)) {
					json.put("status", "success");
				} else {
					session.setAttribute("verifyCodeCheckNum", ++verifyCodeCheckNum);
					json.put("status", "failure");
					json.put("msg", "验证码不正确!");
					return Result.success(json);
				}
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_找回支付密码验证码异常_", json);
		}
		return Result.success(json);
	}

	
	
	
	/**
	 * 页面：设置支付密码
	 */
	@RequestMapping(value = "/payPasswordSet", method = RequestMethod.GET)
	public ModelAndView payPasswordSet(ModelMap modelMap, HttpSession session, @RequestParam(required = false) String r, HttpServletRequest request) {
		String path = LhPage.payPasswordSet;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if (null == sessionUser) {
				String jumpUrl = "/payPasswordRetrieve";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", sessionUser.getId());
			List<UserFund> userFundList = ufService.selectListByCondition(map);
			if (userFundList.size() > 0) {
				UserFund userFund = userFundList.get(0);
				modelMap.put("userFund", userFund);
			}
			modelMap.put("user", sessionUser);
			json.put("user", sessionUser);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_找回支付密码页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/**
	 * 设置支付密码
	 */
	@ResponseBody
	@RequestMapping(value = "/payPasswordSet", method = RequestMethod.POST)
	public JSONObject payPasswordSet(HttpServletRequest request,@ModelAttribute UserFund nowUserFund) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			UserFund userFund = ufService.selectUserFundByUserId(sessionUser.getId());
			System.out.println(userFund.getPayPassword());
			if(userFund.getPayPassword() != null || userFund.getPayPassword() != null){
				json.put("msg", "您已经设置过支付密码!！");
				return Result.failure(json, "您已经设置过支付密码！", "payPassword is setting");
			}
//			Md5Util.encrypt(newPassword)加密
			userFund.setPayPassword(Md5Util.encrypt(nowUserFund.getPayPassword()));
			System.out.println(userFund);
			
			ufService.updatePayPasswordById(userFund);
			json.put("msg", "支付密码设置成功!！");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_设置支付密码出现异常_", json);
		}
		return Result.success(json);
	}

	/**
	 * 页面：找回支付密码
	 */
	@RequestMapping(value = "/payPasswordRetrieve", method = RequestMethod.GET)
	public ModelAndView payPasswordRetrieve(ModelMap modelMap, HttpSession session, 
			@RequestParam(required = false) String r, HttpServletRequest request) {
		String path = LhPage.payPasswordRetrieve;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if (null == sessionUser) {
				String jumpUrl = "/payPasswordRetrieve";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			User baseUser = userService.selectByPrimaryKey(sessionUser.getId());
//			if(baseUser.getPhone().equals("") || baseUser.getPhone() == ""){
//				return new ModelAndView("/user/safety/phoneValidate");
//			}
			modelMap.put("userPhone", baseUser.getPhone());
			System.out.println(baseUser);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", sessionUser.getId());
			List<UserFund> userFundList = ufService.selectListByCondition(map);
			if (userFundList.size() > 0) {
				UserFund userFund = userFundList.get(0);
				modelMap.put("userFund", userFund);
			}
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_找回支付密码页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

//	/** 找回密码发送验证码 */
//	@ResponseBody
//	@RequestMapping(value = "/getPayPasswordVerifycode", method = RequestMethod.POST)
//	public static JSONObject getPayPasswordVerifycode(HttpServletRequest request, HttpSession session,
//			@RequestParam String phone) {
//		JSONObject json = new JSONObject();
//		try {
//			int verifyCodeNum = 1;
//			Object verifyCodeNumObj = session.getAttribute("verifyCodeNum");
//			if (null != verifyCodeNumObj) {
//				verifyCodeNum = (Integer) verifyCodeNumObj;
//				if (verifyCodeNum > 20) {
//					json.put("status", "max_errors");
//					json.put("msg", "请求验证码的次数太多，请直接联系管理员进行修改密码！");
//					return Result.success(json,"请求验证码的次数太多，请直接联系管理员进行修改密码！");
//				}
//			}
//			// phone : 短信接口发送短信验证码
//			String verifycode = SendMsgUtil.createRandomVcode();
//			System.out.println("reg_verifycode: " + verifycode);
//			String mobanId = LhConst.rl_ytx_msg_moban_id;
//			String[] params = new String[] {verifycode, "30" };
//
//			YtxMessage ytx = new YtxMessage();
//			ytx.setPhones(phone);
//			ytx.setMobanId(mobanId);
//			ytx.setParams(params);
//			ytx.setIp(LhConst.rl_ytx_ip);
//			//ytx.setPort(Const.rl_ytx_ip_port);
//			ytx.setPort(LhConst.rl_ytx_port);
//			ytx.setSid(LhConst.rl_ytx_sid);
//			ytx.setAppId(LhConst.rl_ytx_appId);
//			ytx.setAuthToken(LhConst.rl_ytx_authToken);
//			//TODO 发送短信  修改支付密码
////			YTX_MSG.send(ytx);
//			session.setAttribute("verifycode", verifycode);
//			json.put("status", "success");
//		//	System.out.println(json);
//			session.setAttribute("verifyCodeNum", ++verifyCodeNum);
//			
//		} catch (Exception e) {
//			Result.catchError(e, logger, "LH_ERROR_找回支付密码验证码异常_", json);
//		}
//		return Result.success(json,"验证码已发送到您的手机,请及时查看！");
//	}
//	/**
//	 * 校验短信验证码
//	 * */
//	@ResponseBody
//	@RequestMapping(value = "/checkVerifyCodeNum", method = RequestMethod.POST)
//	public JSONObject checkverifyCodeNum(HttpServletRequest request,HttpSession session,
//			@RequestParam String userVerifyCode) {
//		JSONObject json = new JSONObject();
//		try {
//			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
//			if (null == sessionUser) {
//				return Result.userSessionInvalid(json);// 返回session过期的json提示
//			}
//			String verifycode =  String.valueOf(session.getAttribute("verifycode"));	
//			System.out.println("验证码："+verifycode);
//			System.out.println("验证码1："+userVerifyCode);
//			if(userVerifyCode.equals(verifycode)){
//				json.put("checkedSuccess", "匹配成功");
//			}
//		} catch (Exception e) {
//			Result.catchError(e, logger, "LH_ERROR_设置支付密码出现异常_", json);
//		}
//		return Result.success(json,"匹配成功");
//	}
	
	/**
	 * 修改用户支付密码
	 **/
	@ResponseBody
	@RequestMapping(value = "/updateUserFundPayPassword")
	public JSONObject updateUserFundPayPassword(HttpServletRequest request,
			@RequestParam String newPayPassword) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Integer userId = sessionUser.getId();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", sessionUser.getId());
			UserFund userFund = ufService.selectByCondition(map);
			if (null == userFund) {
				return Result.failure(json, "您的资金账户存在异常，请联系客服人员", "uf_null");
			}
			userFund.setPayPassword(Md5Util.encrypt(newPayPassword));
			userFund.setUserId(userId);
			ufService.updatePayPasswordById(userFund);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_修改用户支付密码出现异常_", json);
		}
		return Result.success(json,"支付密码修改成功!");
	}

	/**
	 * 修改用户支付密码
	 **/
	@ResponseBody
	@RequestMapping(value = "/updateUserFundPayPasswordFind")
	public JSONObject updateUserFundPayPasswordFind(HttpServletRequest request, HttpSession session, @RequestParam String code, @RequestParam String payPassword) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			json = checkVerifycode(session, code);
			if ("failure".equals(json.getString("status"))) {// 验证码验证失败
				return Result.success(json);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", sessionUser.getId());
			List<UserFund> userFundList = ufService.selectListByCondition(map);
			if (userFundList.size() > 0) {
				UserFund userFund = userFundList.get(0);
				String payPass = Md5Util.encrypt(payPassword);
				userFund.setPayPassword(payPass);
				ufService.updatePayPasswordById(userFund);
				json.put("status", "success");
				json.put("msg", "操作成功");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_绑定用户银行卡出现异常_", json);
		}
		return Result.success(json);
	}
	
	/**
	 * 修改用户支付密码
	 */
	@RequestMapping(value = "/payPassSet", method = RequestMethod.GET)
	public ModelAndView payPassSet(ModelMap modelMap, HttpSession session, @RequestParam(required = false) String r, HttpServletRequest request) {
		String path = LhPage.payPasswordUpdate;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if (null == sessionUser) {
				String jumpUrl = "/loginPasswordUpdate";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}

			User user = userService.selectByPrimaryKey(sessionUser.getId());
			modelMap.put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_修改支付密码页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	

	/** 获取用户的支付密码 **/
	@ResponseBody
	@RequestMapping(value = "/getPayPass", method = RequestMethod.POST)
	public JSONObject getPayPass(HttpServletRequest request, HttpSession session, @RequestParam String payPassword) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getId());
			List<UserFund> userFundList = ufService.selectListByCondition(map);
			if (userFundList.size() > 0) {
				UserFund userFund = userFundList.get(0);
				String payPasswd = Md5Util.encrypt(payPassword);
				if (payPasswd.equals(userFund.getPayPassword())) {
					json.put("status", "success");
					json.put("msg", "操作成功");
				} else {
					json.put("status", "failure");
					json.put("msg", "当前支付密码不正确,如果忘记支付密码,请点击忘记支付密码");
					return Result.success(json);
				}
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_支付密码验证是否正确异常_", json);
		}
		return Result.success(json);
	}

}

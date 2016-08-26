package com.lhfeiyu.action.front.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.OrderGoods;
import com.lhfeiyu.po.domain.ProvinceCityArea;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserAddress;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.ChargeService;
import com.lhfeiyu.service.domain.OrderGoodsService;
import com.lhfeiyu.service.domain.ProvinceCityAreaService;
import com.lhfeiyu.service.domain.UserAddressService;
import com.lhfeiyu.service.domain.UserFundService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.thirdparty.wx.business.AuthAccess;
import com.lhfeiyu.thirdparty.wx.util.ConfigUtil;
import com.lhfeiyu.thirdparty.wx.util.XMLUtil;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Result;

@Controller
public class ChargeAction {
	@Autowired
	private UserFundService ufService;
	@Autowired
	private ChargeService chargeService;
	@Autowired
	private AuthCheckService authCheckService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private ProvinceCityAreaService provinceCityAreaService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/payway")
	public ModelAndView payway(ModelMap modelMap,HttpSession session
			,HttpServletRequest request
			,@RequestParam(required=false) String r
			,@RequestParam(required=false) Integer amId
			,@RequestParam(required=false) Integer orderGoodsId){
		String path = LhPage.payway;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(session);
			String jumpUrl =  "/index";
			if(null == user){
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			Integer userId = user.getId();
			Map<String,Object> map = new HashMap<String,Object>();
			if(Check.isNotNull(amId)){
				/*map.put("amId", amId);
				AuctionMicro am = amService.selectByCondition(map);
				if(null == am){
					return new ModelAndView(jumpUrl, modelMap);
				}
				modelMap.put("am", am);
				modelMap.put("amId", amId);*/
			}
			if(Check.isNotNull(orderGoodsId)){
				map.put("orderGoodsId", orderGoodsId);
				List<OrderGoods> orderGoodsList = orderGoodsService.selectListByCondition(map);
				if(!Check.isNotNull(orderGoodsList)){
					return new ModelAndView(jumpUrl,modelMap);
				}
				modelMap.put("orderGoods", orderGoodsList.get(0));
				modelMap.put("orderGoodsId", orderGoodsId);
				
				map.clear();
				map.put("userId", userId);
				List<UserAddress> addressList = userAddressService.selectListByCondition(map);//加载收货地址
				if(!Check.isNotNull(addressList)){//TODO 去掉！
					modelMap.put("addressList", addressList);
				}else{
					modelMap.put("user", user);
					map.clear();
					map.put("allProvince", 1);
					List<ProvinceCityArea> provinceList = provinceCityAreaService.selectListByCondition(map);
					modelMap.put("provinceList", provinceList);
					modelMap.put("addressNull", 1);
				}
			}
			
			UserFund uf = ufService.selectUserFundByUserId(userId);
			modelMap.put("orderGoodsId", orderGoodsId);
			BigDecimal moneyBD = uf.getAvaliableMoney();
			double money = 0;
			if(null != moneyBD && moneyBD.doubleValue() > 0){
				money = moneyBD.doubleValue();
			}
			if(!Check.isNotNull(uf.getPayPassword())){
				modelMap.put("noPayPass", 1);
			}
			modelMap.put("money", money);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载支付方式页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	/** 充值  */
	@RequestMapping(value="/fund/charge/recharge")
	public ModelAndView recharge(ModelMap modelMap,HttpServletRequest request,HttpSession session,
			@RequestParam(required=false) String code,@RequestParam(required=false) String r){
		String path = LhPage.recharge;
		try{//TODO 测试
//			if(!Check.isNotNull(code)){//没有传递微信的CODE，直接跳转到首页，可重新检查登陆情况   
//				return new ModelAndView(LhPage.index, modelMap);
//			}
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				String jumpUrl =  "/fund/charge/recharge";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			String openId = AuthAccess.getOpenId(code);//获取用户的openId
			session.setAttribute("openId", openId);
			Integer userId = user.getId();
			User db_user = userService.selectByPrimaryKey(userId);
			if(null == db_user.getPhone() || "".equals(db_user.getPhone())){
				modelMap.put("noPhone", "noPhone");
			}
			UserFund userFund = ufService.selectUserFundByUserId(userId);
			modelMap.put("userFund", userFund);
			if(null == userFund.getPayPassword() || "".equals(userFund.getPayPassword())){
				modelMap.put("noPayPassword", "noPayPassword");
			}
			modelMap.put("user", user);
			modelMap.put("openId", openId);
			String ticket = AuthAccess.getWxDataFromProperty("ticket");//从Property文件中获取ticket,如果文件中没有，则会远程获取
			if(Check.isNotNull(ticket)){
				String url = "http://weipaike.net/fund/charge/recharge?code="+code+"&state=STATE";
				modelMap = AuthAccess.getSign(modelMap, ticket, url);
			}else{
				return new ModelAndView(LhPage.index); 
			}
			
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载充值页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	/** 执行充值 */
	@ResponseBody
	@RequestMapping(value="/fund/charge/doRecharge", method=RequestMethod.POST)
	public JSONObject doRecharge(HttpServletRequest request,HttpSession session,@RequestParam Double money){
		JSONObject json = new JSONObject();
		try{
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Object openIdObj = session.getAttribute("openId");
			if(null == openIdObj){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			if(money <= 0){
				json.put("status", "failure");
				json.put("msg", "充值金额不正确");
				return Result.success(json);
			}else if(money >= 100000){
				json.put("status", "failure");
				json.put("msg", "充值金额不能超过十万元");
				return Result.success(json);
			}
			String openId = openIdObj.toString();//从Session中获得openId
			String spbill_create_ip = request.getRemoteAddr();
			String userAgent = request.getHeader("user-agent");
			
			System.out.println("/fund/charge/doRecharge money: "+money);
			
			chargeService.doWxPay(json, user, openId, spbill_create_ip, userAgent, money, ConfigUtil.NOTIFY_URL, null, null);

		}catch(Exception e){
			e.printStackTrace();
			Result.catchError(e, logger, "LH_ERROR_执行充值出现异常_", json);
		}
		return Result.success(json);
	}
	
	/** 充值成功  */
	@ResponseBody
	@RequestMapping(value="/fund/charge/rechargeSuccess")
	public JSONObject rechargeSuccess(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response){
		try{
			System.out.println("/fund/charge/rechargeSuccess callback");
			String answerStr = chargeService.chargeSuccessNotify(request.getInputStream(), 1);
			response.getWriter().write(answerStr);   //向微信服务器返回数据
		}catch(Exception e){
			e.printStackTrace();
			try { response.getWriter().write(XMLUtil.setXML("FAIL", "出现异常"));} catch (IOException e1) {
				e1.printStackTrace();
			}   
			logger.error("LH_ERROR_执行充值成功回调方法出现异常_"+e.getMessage());
		}
		return null;
	}
	
	
	/**-----------------------------------------直接付款------------------------------------------------------*/
	
	/** 付款  */
	@RequestMapping(value="/fund/charge/pay")
	public ModelAndView pay(ModelMap modelMap,HttpServletRequest request,HttpSession session,
			@RequestParam(required=false) String code,@RequestParam(required=false) String r){
		String path = LhPage.pay;
		//return new ModelAndView(path, modelMap);
		try{
			if(!Check.isNotNull(code)){//没有传递微信的CODE，直接跳转到首页，可重新检查登陆情况
				return new ModelAndView(LhPage.index, modelMap);
			}
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				String jumpUrl =  "/fund/charge/pay";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			String openId = AuthAccess.getOpenId(code);//获取用户的openId
			session.setAttribute("openId", openId);
			Integer userId = user.getId();
			User db_user = userService.selectByPrimaryKey(userId);
			if(null == db_user.getPhone() || "".equals(db_user.getPhone())){
				modelMap.put("noPhone", "noPhone");
			}
			UserFund userFund = ufService.selectUserFundByUserId(userId);
			modelMap.put("userFund", userFund);
			if(null == userFund.getPayPassword() || "".equals(userFund.getPayPassword())){
				modelMap.put("noPayPassword", "noPayPassword");
			}
			modelMap.put("user", user);
			modelMap.put("openId", openId);
			String ticket = AuthAccess.getWxDataFromProperty("ticket");//从Property文件中获取ticket,如果文件中没有，则会远程获取
			if(Check.isNotNull(ticket)){
				String url = "http://weipaike.net/fund/charge/pay?code="+code+"&state=STATE";
				modelMap = AuthAccess.getSign(modelMap, ticket, url);
			}else{
				return new ModelAndView(LhPage.index); 
			}
			
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载支付页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	/** 执行付款 */
	@ResponseBody
	@RequestMapping(value="/fund/charge/doPay", method=RequestMethod.POST)
	public JSONObject doPay(HttpServletRequest request,HttpSession session,
			@RequestParam Double money, @RequestParam Integer payType, 
			@RequestParam(required=false) Integer orderGoodsId){
			//payType备用整型字段（占用：记录直接支付的操作，1交纳保证金，2购买商品）
		JSONObject json = new JSONObject();
		try{
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Object openIdObj = session.getAttribute("openId");
			if(null == openIdObj){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			if(payType != 2){
				if(money <= 0){
					Result.failure(json, "支付金额不正确", null);
				}else if(money >= 100000){
					Result.failure(json, "支付金额不能超过十万元", null);
				}
			}
			String openId = openIdObj.toString();//从Session中获得openId
			String spbill_create_ip = request.getRemoteAddr();
			String userAgent = request.getHeader("user-agent");
			
			System.out.println("/fund/charge/doPay money: "+money+" payType: "+payType);
			
			chargeService.doWxPay(json, user, openId, spbill_create_ip, userAgent, money, ConfigUtil.NOTIFY_URL_PAY, payType, orderGoodsId);

		}catch(Exception e){
			e.printStackTrace();
			Result.catchError(e, logger, "LH_ERROR_执行支付出现异常_", json);
		}
		return Result.success(json);
	}
	
	/** 付款成功  */
	@ResponseBody
	@RequestMapping(value="/fund/charge/paySuccess")
	public JSONObject paySuccess(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response){
		try{
			System.out.println("/fund/charge/paySuccess callback");
			String answerStr = chargeService.chargeSuccessNotify(request.getInputStream(), 2);
			response.getWriter().write(answerStr);   //向微信服务器返回数据
		}catch(Exception e){
			e.printStackTrace();
			try { response.getWriter().write(XMLUtil.setXML("FAIL", "出现异常"));} catch (IOException e1) {
				e1.printStackTrace();
			}   
			logger.error("LH_ERROR_执行支付成功回调方法出现异常_"+e.getMessage());
		}
		return null;
	}
	
	/** 从可用余额中支付 */
	@ResponseBody
	@RequestMapping(value="/fund/payCreditMoneyByAccount", method=RequestMethod.POST)
	public JSONObject payCreditMoneyByAccount(HttpServletRequest request, 
			@RequestParam String payPass, @RequestParam Double money){
		JSONObject json = new JSONObject();
		try{
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			chargeService.payCreditMoneyByAccount(json, user, payPass, money);
		}catch(Exception e){
			e.printStackTrace();
			Result.catchError(e, logger, "LH_ERROR_从可用余额中支付出现异常_", json);
		}
		return Result.success(json);
	}

}

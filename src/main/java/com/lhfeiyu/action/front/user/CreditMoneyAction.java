package com.lhfeiyu.action.front.user;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.UserFundService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Result;

@Controller
public class CreditMoneyAction {
	@Autowired
	private UserFundService ufService;
	@Autowired
	private AuthCheckService authCheckService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/creditMoney")
	public ModelAndView creditMoney(ModelMap modelMap, HttpServletRequest request,
			@RequestParam(required=false) String r,
			@RequestParam(required=false) String from,
			@RequestParam Double bail){
		String path = LhPage.creditMoney;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(request.getSession());
			String jumpUrl =  "/index";
			if(null == user){
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			Integer userId = user.getId();
			//Map<String,Object> map = new HashMap<String,Object>();
			/**
			 * 需要的参数：
			 * 已交纳保证金：${creditMoney}
			 * 剩余需要交纳保证金：${leftMoney}
			 * 支付密码${empty payPass}
			 * 余额不足：${!empty moneyLack}
			 * 可用余额：${avaliableMoney}
			 */
			//检查保证金是否足够，不能则跳转到支付保证金页面
			UserFund uf = ufService.selectUserFundByUserId(userId);
			if(null == uf){
				Result.failure(json, "您的资金账户存在异常，请联系客服人员", "uf_null");
				return new ModelAndView(path, modelMap);
			}
			BigDecimal creditMoney = uf.getOtherFund();
			BigDecimal avaliableMoney = uf.getAvaliableMoney();
			if(null == creditMoney)creditMoney = new BigDecimal(0);
			if(null == avaliableMoney)avaliableMoney = new BigDecimal(0);
			
			modelMap.put("from", from);
			json.put("from", from);//标识来源：与专场对应ap
			
			double leftMoney = bail;
			if(creditMoney.doubleValue() == 0){
				modelMap.put("leftMoney", bail);
			}else{
				leftMoney = bail - creditMoney.doubleValue();
				modelMap.put("leftMoney", leftMoney);
				path = LhPage.creditMoney;
			}
			json.put("leftMoney", leftMoney);
			if(null == avaliableMoney || avaliableMoney.doubleValue() < leftMoney){
				modelMap.put("moneyLack", 1);
				json.put("moneyLack", 1);
			}
			if(Check.isNotNull(uf.getPayPassword())){
				modelMap.put("payPass", 1);
				json.put("payPass", 1);
				modelMap.put("avaliableMoney", avaliableMoney.doubleValue());
				json.put("avaliableMoney", avaliableMoney.doubleValue());
			}
			
			modelMap.put("paramJson", json); 
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载支付方式页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	


}

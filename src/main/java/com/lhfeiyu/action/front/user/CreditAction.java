package com.lhfeiyu.action.front.user;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Credit;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.CreditService;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
public class CreditAction {
	@Autowired
	private CreditService  creditService;
	@Autowired
	private AuthCheckService authCheckService;
	@Autowired
	private ShopService shopService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/credit")
	public ModelAndView credit(ModelMap modelMap,HttpServletRequest request
			,@RequestParam(required=false) String r){
		String path = LhPage.credit;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if(null == sessionUser){
				String jumpUrl = "/credit";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Integer userId = sessionUser.getId();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", userId);
			List<Credit>  creditList = creditService.selectListByCondition(map);
			if(creditList.size() > 0){
				Credit credit = creditList.get(0);
				modelMap.put("credit", credit);
			}
			if(null != sessionUser){
				modelMap.put("currentUserId", sessionUser.getId());
			}
			List<Shop> shopList = shopService.selectListByCondition(map);
			if(null != shopList){
				if(shopList.size() > 0){
					Shop shop = shopList.get(0);
					modelMap.put("shopId", shop.getId());
				}
			}
			modelMap.put("userId", userId);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/credit", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateCredit", method=RequestMethod.POST)
	public JSONObject addOrUpdateCredit(@ModelAttribute Credit credit,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			credit.setMainStatus(1);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			if(null == credit.getId()){//添加
				/*if(null == credit.getCreatedAt()){
					credit.setCreatedAt(new Date()); 
					credit.setLastResetTime(new Date());
				}
				credit.setCreatedBy(sessionUser.getUsername());
				creditService.insert(credit);*/
				String username = sessionUser.getUsername();
				Integer userId = credit.getUserId();
				Integer shopId = credit.getShopId();
				Map<String,Object> map = new HashMap<String,Object>();
				if(null != userId){
					map.put("userId", userId);
					List<Credit> creditList = creditService.selectListByCondition(map);
					if(null == creditList || creditList.size() <= 0){
						commonCredit(userId,null,username);
					}
				}
				if(null != shopId){
					map.put("shopId", shopId);
					List<Credit> creditList = creditService.selectListByCondition(map);
					if(null == creditList || creditList.size() <= 0){
						commonCredit(null,shopId,username);
					}
				}
				json.put("status", "success");
				json.put("msg", "操作成功");
			}else{//修改
				if(null == credit.getUpdatedAt()){
					credit.setUpdatedAt(new Date()); 
				}
				String reset = request.getParameter("reset");
				if(null != reset){
					Credit credit1 = creditService.selectByPrimaryKey(credit.getId());
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, 30);
					Date d = credit1.getLastResetTime();
				    if(c.after(d)){
						credit.setBackRateB(new BigDecimal(0));
						credit.setBackRateC(new BigDecimal(0));
						credit.setDoneRateB(new BigDecimal(0));
						credit.setDoneRateC(new BigDecimal(0));
						credit.setGoodRateB(new BigDecimal(0));
						credit.setGoodRateC(new BigDecimal(0));
						credit.setBreakRateB(new BigDecimal(0));
						credit.setBreakRateC(new BigDecimal(0));
						credit.setLastResetTime(new Date());
						credit.setUpdatedBy(sessionUser.getUsername());
						creditService.updateByPrimaryKeySelective(credit);
						json.put("status", "success");
						json.put("msg", "操作成功");
				    }else{
				    	json.put("status", "failure");
						json.put("msg", "每隔30天后才可重置");
				    }
				}
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_添加或修改信誉数据出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getCreditList", method=RequestMethod.POST)
	public JSONObject getCreditList(HttpServletRequest request) {
		List<Credit> creditList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			creditList = creditService.selectListByCondition(map);
			Integer total = creditService.selectCountByCondition(map);
			json.put("rows", creditList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载信誉列表出现异常_", json);
		}
		return Result.success(json);
	}
	
	private void commonCredit(Integer userId, Integer shopId,String username){
		Date d = new Date();
		Credit credit = new Credit();
		credit.setBackRateB(new BigDecimal(0));
		credit.setBackRateC(new BigDecimal(0));
		credit.setDoneRateB(new BigDecimal(0));
		credit.setDoneRateC(new BigDecimal(0));
		credit.setGoodRateB(new BigDecimal(0));
		credit.setGoodRateC(new BigDecimal(0));
		credit.setBreakRateB(new BigDecimal(0));
		credit.setBreakRateC(new BigDecimal(0));
		credit.setCreatedAt(d); 
		credit.setUserId(userId);
		credit.setShopId(shopId);
		credit.setLastResetTime(d);
		credit.setCreatedBy(username);
		creditService.insert(credit);
	}
}

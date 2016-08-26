
package com.lhfeiyu.action.front.provider;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Apply;
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.Provider;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.base.BaseApplyService;
import com.lhfeiyu.service.base.BaseProviderService;
import com.lhfeiyu.service.domain.CommentService;
import com.lhfeiyu.service.domain.GoodsService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
@Controller
public class ProviderAction {

	@Autowired
	private BaseProviderService providerService;
	@Autowired
	private BaseApplyService applyService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CommentService commentService;

	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value = "/providerIndex")
	public ModelAndView providerIndex(ModelMap modelMap, HttpServletRequest request,
			@RequestParam(required = false) String r) {
		String path = LhPage.providerIndex;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			
			modelMap.put("user", sessionUser);
			json.put("user", sessionUser);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/providerIndex", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	//获取所有技师信息
	@ResponseBody
	@RequestMapping(value = "/getProviderList", method = RequestMethod.POST)
	public JSONObject getProviderList(HttpServletRequest request,ModelMap modelMap) {
		List<Provider> providerList = null;
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());
			Map<String, Object> map = ActionUtil.getAllParam(request);
			providerList = providerService.selectListByCondition(map);
			modelMap.put("paramJson", json);
			Integer total = providerService.selectCountByCondition(map);
			Result.gridData(providerList, total, json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/getProviderList", json);
		}
		return Result.success(json);
	}
	//技师详情
	@RequestMapping(value = "/getProvider/{providerId}")
	public ModelAndView forum(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer providerId,
			@RequestParam(required = false) String r) {
		String path = LhPage.providerInfo;
		try {
			JSONObject json = new JSONObject();
			User user = ActionUtil.checkSession4User(request.getSession());
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("providerId", providerId);
			map.put("receiverId", providerId);
			Provider provider = providerService.selectByCondition(map);
			if(null!=provider.getTotalRank()){
			Integer providerTotal=provider.getTotalRank();
			Integer count = commentService.selectCountByCondition(map);
			float total=count*5;
			float  stars=providerTotal/total*5;
			json.put("stars", stars);
			modelMap.addAttribute("stars", stars);
			}
			json.put("providerId", providerId);
			modelMap.addAttribute("provider", provider);
			modelMap.addAttribute("user", user);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/provider" + "/" + providerId, modelMap);
		}
		
		return new ModelAndView(path, modelMap);
	}
	//技师预约下单
	@RequestMapping(value = "/providerBespeak/{providerId}/{goodsId}")
	public ModelAndView provider(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer providerId,@PathVariable Integer goodsId,
			@RequestParam(required = false) String r) {
		String path = LhPage.bespeak;
		try {
			JSONObject json = new JSONObject();
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if(null == sessionUser){
				return Result.userSessionInvalid(modelMap,LhPage.login);
			}
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			Goods goods = goodsService.selectByPrimaryKey(goodsId);
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("providerId", providerId);
			map.put("receiverId", providerId);
			Provider provider = providerService.selectByCondition(map);
			if(null!=provider.getTotalRank()){
			Integer providerTotal=provider.getTotalRank();
			Integer count = commentService.selectCountByCondition(map);
			float total=count*5;
			float  stars=providerTotal/total*5;
			json.put("stars", stars);
			modelMap.addAttribute("stars", stars);
			}
			modelMap.put("user", sessionUser);
			json.put("providerId", providerId);
			modelMap.addAttribute("provider", provider);
			modelMap.addAttribute("goods", goods);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			
			Result.catchError(e, logger, this.getClass().getName() + "/provider" + "/" + providerId, modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	@RequestMapping(value = "/providerAdd")
	public ModelAndView providerAdd(ModelMap modelMap, HttpServletRequest request,
			@RequestParam(required = false) String r) {
		String path = LhPage.addProvider;
		try {
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/providerAdd", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	//申请技师处理
	@ResponseBody
	@RequestMapping(value = "/addProvider", method = RequestMethod.POST)
	public JSONObject addProvider(HttpServletRequest request,ModelMap modelMap,@ModelAttribute Apply apply) {
		JSONObject json = new JSONObject();
		try {
			Date date = new Date();
			apply.setCreatedAt(date);
			apply.setApplyDate(date);
			String userPassword = Md5Util.encrypt("000000");
			apply.setAttr4(userPassword);
			apply.setApplyType(5);
			apply.setCreatedBy(apply.getAttr1());
			apply.setMainStatus(1);//
			applyService.insert(apply);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/addProvider", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value = "/updataFocusNum", method = RequestMethod.POST)
	public JSONObject updataFocusNum(@ModelAttribute Provider provider,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的goods，存在即返回
			providerService.updateByPrimaryKeySelective(provider);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/updataFocusNum", json);
		}
		return Result.success(json);
	}

}

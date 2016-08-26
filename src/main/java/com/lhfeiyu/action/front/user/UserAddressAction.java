package com.lhfeiyu.action.front.user;

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
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserAddress;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.UserAddressService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
public class UserAddressAction {
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private AuthCheckService authCheckService;
	private static Logger logger = Logger.getLogger("R");
	
	
	/**收货地址*/
	@RequestMapping(value="/address")
	public ModelAndView receiveAddressList(ModelMap modelMap,HttpServletRequest request
			,@RequestParam(required=false) String r) {
		String path = LhPage.receiveAddressList;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if(null == sessionUser){
				String jumpUrl = "/login";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			modelMap.put("user", sessionUser);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/address", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	
	
	@RequestMapping(value="/addOrUpdateAddress/{id}", method=RequestMethod.GET)
	public ModelAndView addOrUpdateAddress(ModelMap modelMap
			,HttpServletRequest request
			,@PathVariable Integer id
			,@RequestParam(required=false) String r) {
		String path = LhPage.updateUserAddress;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if(null == sessionUser){
				String jumpUrl = "/address";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			UserAddress userAddress = userAddressService.selectByPrimaryKey(id);
			modelMap.put("userAddress", userAddress);
			modelMap.put("user", sessionUser);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/address", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	@RequestMapping(value="/addOrUpdateUserAddress")
	public ModelAndView addOrUpdateUserAddress(ModelMap modelMap
			,HttpServletRequest request
			,@RequestParam(required=false) String r) {
		String path = LhPage.AddAddress;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if(null == sessionUser){
				String jumpUrl = "/address";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			modelMap.put("user", sessionUser);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/address", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateUserAddress", method=RequestMethod.POST)
	public JSONObject addOrUpdateUserAddress(@ModelAttribute UserAddress userAddress, 
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);
			}
			Integer userId = sessionUser.getId();
			String username = sessionUser.getUsername();
			userAddress.setUserId(userId);
			userAddress.setMainStatus(1);
			userAddressService.addUpdateUserAddress(json, userAddress, username);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/addOrUpdateUserAddress", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/setDefultAddress", method=RequestMethod.POST)
	public JSONObject setDefultAddress(@ModelAttribute UserAddress userAddress, 
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);
			}
			Integer userId = sessionUser.getId();
			String username = sessionUser.getUsername();
			userAddress.setUserId(userId);
			userAddress.setMainStatus(1);//是否启用
			UserAddress address = userAddressService.selectByPrimaryKey(userAddress.getId());
			System.out.println(address);
			userAddressService.cleanDefultAddress(address.getUserId());
			
			address.setIsDefault(1);
			userAddressService.addUpdateUserAddress(json, address, username);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/addOrUpdateUserAddress", json);
		}
		return Result.success(json);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getUserAddressList", method=RequestMethod.POST)
	public JSONObject getUserAddressList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
//			if(null == sessionUser){
//				return Result.userSessionInvalid(json);//返回session过期的json提示
//			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			map.put("userId", sessionUser.getId());
			List<UserAddress> userAddress = userAddressService.selectListByCondition(map);
			json.put("rows", userAddress);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/getUserAddressList", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value="/getDefaultAddress", method=RequestMethod.POST)
	public JSONObject getDefaultAddress(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			map.put("userId", sessionUser.getId());
			map.put("isDefault", 1);
			UserAddress userAddress = userAddressService.selectByCondition(map);
			json.put("rows", userAddress);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/login", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteUserAddressThorough", method=RequestMethod.POST)
	public JSONObject deleteUserAddressThorough(HttpServletRequest request,
			@RequestParam Integer id) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			userAddressService.deleteMyUserAddressThorough(json, sessionUser, id);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/deleteUserAddressThorough", json);
		}
		return Result.success(json);
	}
	
	
}

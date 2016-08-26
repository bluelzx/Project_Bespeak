package com.lhfeiyu.action.front.shop;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.lhfeiyu.po.domain.CollectGoods;
import com.lhfeiyu.po.domain.ForumMember;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.CollectGoodsService;
import com.lhfeiyu.service.domain.CommentService;
import com.lhfeiyu.service.domain.FansService;
import com.lhfeiyu.service.domain.ForumMemberService;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
public class CollectGoodsAction {
	@Autowired
	private CollectGoodsService service;
	@Autowired
	private ShopService shopService;
	@Autowired
	private FansService fansService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ForumMemberService forumMemberService;
	@Autowired
	private AuthCheckService authCheckService;
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/like/{userId}")
	public ModelAndView collectGoods(ModelMap modelMap, HttpServletRequest request
			,@PathVariable Integer userId
			,@RequestParam(required=false) String r) {
		String path = LhPage.collectGoods;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
			User user = ActionUtil.checkSession4User(request.getSession());
			/*
			if(null == sessionUser){
				return Result.userSessionInvalid(modelMap, "/collectGoods?userId="+userId);
			}*/
			if(null != user){
				modelMap.put("currentUserId", user.getId());
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", userId);
			List<Shop> shopList = shopService.selectListByCondition(map);
			List<ForumMember> forumMemberList = forumMemberService.selectListByCondition(map);
			if(null != forumMemberList){
				if(forumMemberList.size() > 0){
					ForumMember forumMember = forumMemberList.get(0);
					modelMap.put("forumMember", forumMember);
				}
			}
			Integer meFocusCount =  fansService.selectCountByCondition(map);
			if(shopList.size() > 0){
				Shop shop = shopList.get(0);
				if(null == shop.getCreditMargin() || shop.getCreditMargin() <= 0)shop.setCreditMargin(0);
				modelMap.put("shop", shop);
			}
			map.clear();
			map.put("objectId", userId);
			map.put("commentTypeId", 4);
			int commentCount = commentService.selectCountByCondition(map);
			modelMap.put("commentCount", commentCount);
			map.clear();
			map.put("fansId", userId);
			Integer focusMeCount =  fansService.selectCountByCondition(map);
			map.put("count", 5);
			map.put("start", 0);
			modelMap.put("focusMeCount", focusMeCount);
			modelMap.put("meFocusCount", meFocusCount);
			modelMap.put("userId", userId);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载收藏(喜欢)商品页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateCollectGoods", method=RequestMethod.POST)
	public JSONObject addOrUpdateCollectGoods(@ModelAttribute CollectGoods collectGoods,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			collectGoods.setMainStatus(1);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			if(null == collectGoods.getId()){//添加
				if(null == collectGoods.getCreatedAt()){
					collectGoods.setCreatedAt(new Date()); 
				}
				collectGoods.setUserId(sessionUser.getId());
				collectGoods.setCreatedBy(sessionUser.getUsername());
				service.insert(collectGoods);
				json.put("status", "success");
				json.put("msg", "操作成功");
			}else{//修改
				if(null == collectGoods.getUpdatedAt()){
					collectGoods.setUpdatedAt(new Date()); 
				}
				collectGoods.setUpdatedBy(sessionUser.getUsername());
				service.updateByPrimaryKeySelective(collectGoods);
				json.put("status", "success");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_修改古玩城数据出现异常_", json);
		}
		return Result.success(json);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getCollectGoodsList", method=RequestMethod.POST)
	public JSONObject getCollectGoodsList(HttpServletRequest request,HttpServletResponse response) {
		List<CollectGoods> collectGoodsList = null;
		JSONObject json = new JSONObject();
		try {
			/*User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}*/
			Map<String, Object> map = ActionUtil.getAllParam(request);
			map.put("selfOrder", 1);
			collectGoodsList = service.selectListByCondition(map);
			Integer total = service.selectCountByCondition(map);
			json.put("rows", collectGoodsList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_getCollectGoodsList_获取喜好信息出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteCollectGoods", method=RequestMethod.POST)
	public JSONObject deleteCollectGoods(HttpServletRequest request,@RequestParam(required=false) Integer id
			,@RequestParam(required=false) Integer userId) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			int sessionUserId = user.getId();
			Integer db_id = null;
			if(null != id){
				CollectGoods collectGoods = service.selectByPrimaryKey(id);
				if(null != collectGoods){
					int id1 = collectGoods.getUserId();
					if(sessionUserId != id1 ){
						json.put("status","failure");
						json.put("msg","您没有该权限");
						return Result.success(json);
					}else{
						db_id = id;
					}
				}
			}else if(null != userId){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userId", userId);
				List<CollectGoods> collectGoodsList = service.selectListByCondition(map);
				if(null != collectGoodsList && collectGoodsList.size()>0){
					db_id = collectGoodsList.get(0).getId();
				}
			}
			if(null != db_id){
				service.deleteByPrimaryKey(db_id);
			}
			json.put("status","success");
			json.put("msg","已成功取消喜欢");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_取消喜欢出现异常_", json);
		}
		return Result.success(json);
	}
	
	
}

package com.lhfeiyu.action.front.coupon;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Coupon;
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.base.BaseCouponService;
import com.lhfeiyu.service.domain.GoodsService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
public class CouponAction {
	@Autowired
	private BaseCouponService couponService;
	@Autowired
	private GoodsService goodsService;
	private static Logger logger = Logger.getLogger("R");

	@RequestMapping(value = "/coupon/{goodsId}")
	public ModelAndView couponIndex(ModelMap modelMap, HttpServletRequest request,
			@PathVariable Integer goodsId,@RequestParam(required = false) String r) {
		String path = LhPage.coupon;
		try {
			JSONObject json = new JSONObject();
			// modelMap = authCheckService.checkWxLogin(request, modelMap, json,
			// true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/providerIndex";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Goods goods=goodsService.selectByPrimaryKey(goodsId);
			modelMap.put("user", sessionUser);
			json.put("user", sessionUser);
			modelMap.put("paramJson", json);
			modelMap.put("goods", goods);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/coupon", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	// 获取所有优惠券信息
	@ResponseBody
	@RequestMapping(value = "/getCouponLists/{shopId}/{typeCode}", method = RequestMethod.POST)
	public JSONObject getCouponLists(HttpServletRequest request, ModelMap modelMap,
			@PathVariable Integer shopId,@PathVariable String typeCode) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", sessionUser.getId());
			map.put("shopId", shopId);
			map.put("attrStr", typeCode);
			map.put("dealStatus", 1);
			List<Coupon> couponList = couponService.selectListByCondition(map);
			modelMap.put("paramJson", json);
			modelMap.put("couponList", couponList);
			Integer total = couponService.selectCountByCondition(map);
			Result.gridData(couponList, total, json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/getCouponList", json);
		}
		return Result.success(json);
	}
	// 获取所有优惠券信息
	@ResponseBody
	@RequestMapping(value = "/getCoupon", method = RequestMethod.POST)
	public JSONObject getCoupon(HttpServletRequest request, ModelMap modelMap) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", sessionUser.getId());
			map.put("dealStatus", 1);
			List<Coupon> couponList = couponService.selectListByCondition(map);
			modelMap.put("paramJson", json);
			modelMap.put("couponList", couponList);
			Integer total = couponService.selectCountByCondition(map);
			Result.gridData(couponList, total, json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/getCouponList", json);
		}
		System.out.println(json);
		return Result.success(json);
	}
	@RequestMapping(value = "/coupon")
	public ModelAndView coupon(ModelMap modelMap, HttpServletRequest request
			,@RequestParam(required = false) String r) {
		String path = LhPage.coupon;
		try {
			JSONObject json = new JSONObject();
			// modelMap = authCheckService.checkWxLogin(request, modelMap, json,
			// true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/login";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			modelMap.put("user", sessionUser);
			json.put("user", sessionUser);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/coupon", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
}

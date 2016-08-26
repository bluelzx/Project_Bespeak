package com.lhfeiyu.action.front.order;

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
import com.lhfeiyu.po.domain.OrderGoods;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.OrderGoodsService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;
@Controller
public class OrderGoodsAction {
	@Autowired
	private OrderGoodsService  orderGoodsService;
	private static Logger logger = Logger.getLogger("R");
	@Autowired
	private AuthCheckService authCheckService;
	@ResponseBody
	@RequestMapping(value="/addOrUpdateOrderGoods", method=RequestMethod.POST)
	public JSONObject addOrUpdateOrderGoods(@ModelAttribute OrderGoods orderGoods,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			if(null == orderGoods.getId()){//添加
				orderGoods.setMainStatus(1);
				orderGoods.setCreatedAt(new Date());
				orderGoods.setCreatedBy(user.getUsername());
				orderGoodsService.insert(orderGoods);
			}else{//修改
				orderGoods.setUpdatedAt(new Date());
				orderGoods.setUpdatedBy(user.getUsername());
				orderGoodsService.updateByPrimaryKeySelective(orderGoods);
			}
			json.put("status", "success");
			json.put("id",orderGoods.getId());
			json.put("msg", "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改商品订单出现异常_", json);
		}
		return Result.success(json);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getOrderGoodsById", method=RequestMethod.POST)
	public JSONObject getOrderGoodsById(@RequestParam(required = false) Integer id,
			HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			OrderGoods orderGoods = orderGoodsService.selectByPrimaryKeyAndOrder(id);
			json.put("status", "success");
			json.put("orderGoods",orderGoods);
			json.put("msg", "操作成功");
	
//			modelMap.put("", orderGoods)
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改商品订单出现异常_", json);
		}
		return Result.success(json);
	}
	
	//TODO 选择订单 晒好物
	@RequestMapping(value = "/chooseOrderGoods/{forumId}")
	public ModelAndView chooseOrderGoods(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer forumId,
			@RequestParam(required = false) String r) {
		String path = LhPage.chooseOrderGoods;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/chooseOrderGoods";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			modelMap.put("user", sessionUser);
			modelMap.put("forumId", forumId);
			json.put("user", sessionUser);
			json.put("forumId", forumId);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/forumDetail", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/getOrderGoodsList", method=RequestMethod.POST)
	public JSONObject getOrderGoodsList(HttpServletRequest request,@RequestParam(required=false)String pageFrom) {
		List<OrderGoods> orderGoodsList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Integer userId = user.getId();
//			if(Check.isNotNull(pageFrom)){
//				if(pageFrom.equals("shipping")){
//					map.put("orderStatus", 1);
//					map.put("shippingStatus", 1);
//					map.put("payStatus", 3);
//					map.put("orderGoodsStatus", 1);
//					map.put("shopUserId", userId);
//					map.put("expressStateNotOver", 1);
//					map.put("orderInfoMainStatus", 1);
//				}else if(pageFrom.equals("shipped")){
//					map.put("orderStatus", 1);
//					map.put("shippingStatus", 2);
//					map.put("payStatus", 3);
//					map.put("orderGoodsStatus", 1);
//					map.put("userId", userId);
//					map.put("expressStateNotOver", 1);
//					map.put("orderInfoMainStatus", 1);
//				}else if(pageFrom.equals("waitPayMoney")){
//					map.put("orderStatus", 1);
//					map.put("shippingStatus", 1);
//					map.put("payStatus", 1);
//					map.put("orderGoodsStatus", 1);
//					map.put("userId", userId);
//					map.put("expressStateNotOver", 1);
//					map.put("orderInfoMainStatus", 1);
//				}else if(pageFrom.equals("returnGoods")){
//					map.put("orderStatus", 6);
//					map.put("payStatus", 3);
//					map.put("orderGoodsStatus", 5);
//					map.put("sellerOrbuyer", userId);
//					map.put("orderInfoMainStatus", 1);
//				}
//			}
			map.put("userId", userId);
			orderGoodsList = orderGoodsService.selectListByCondition(map);
			Integer total = orderGoodsService.selectCountByCondition(map);
			json.put("rows", orderGoodsList);
			json.put("total", total);
			json.put("status", "success");
			System.out.println(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载商品订单列表出现异常_", json);
		}
		return Result.success(json);
	}
	
}

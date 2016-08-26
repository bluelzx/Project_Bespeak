/*package com.lhfeiyu.action.front.shop;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.po.GoodsOffers;
import com.lhfeiyu.po.base.User;
import com.lhfeiyu.service.GoodsOffersService;
import com.lhfeiyu.tools.base.ActionUtil;
import com.lhfeiyu.tools.base.Pagination;
import com.lhfeiyu.tools.base.Result;
import com.lhfeiyu.util.base.RequestUtil;

@Controller
public class GoodsOffersAction {
	@Autowired
	private GoodsOffersService goodsOffersService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@ResponseBody
	@RequestMapping(value="/addGoodsOffers", method=RequestMethod.POST)
	public JSONObject addGoodsOffers(@ModelAttribute GoodsOffers goodsOffers, HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);
			}
			goodsOffersService.addGoodsOffer(json, user, goodsOffers);
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_新增商品报价出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/disgreeGoodsOffer", method=RequestMethod.POST)
	public JSONObject disgreeGoodsOffer(@RequestParam Integer goodsOffersId, HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);
			}
			goodsOffersService.disgreeGoodsOffer(json, user, goodsOffersId);
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_拒绝商品报价出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/agreeGoodsOffer", method=RequestMethod.POST)
	public JSONObject agreeGoodsOffer(@ModelAttribute GoodsOffers goodsOffers, HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);
			}
			goodsOffersService.agreeGoodsOffer(json, user, goodsOffers);
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_同意商品报价出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getGoodsOffersList", method=RequestMethod.POST)
	public JSONObject getGoodsOffersList(HttpServletRequest request) {
		List<GoodsOffers> goodsOffersList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			goodsOffersList = goodsOffersService.selectListByCondition(map);
			Integer total = goodsOffersService.selectCountByCondition(map);
			json.put("rows", goodsOffersList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载商品竞价列表出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteGoodsOffers", method=RequestMethod.POST)
	public JSONObject deleteGoodsOffers(HttpServletRequest request, @RequestParam(value="id") Integer id) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);
			}
			int sessionUserId = user.getId();
			GoodsOffers goodsOffers = goodsOffersService.selectByPrimaryKey(id);
			if(null != goodsOffers && null != goodsOffers.getUserId() && sessionUserId == goodsOffers.getUserId().intValue()){
				goodsOffersService.deleteByPrimaryKey(id);
				json.put("status","success");
				json.put("msg","彻底删除成功");
				return Result.success(json);
			}
			json.put("status","failure");
			json.put("msg","您没有该权限或者该数据已经被删除");
			return Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_彻底删除商品出价出现异常_", json);
		}
		return Result.success(json);
	}
	
}
*/
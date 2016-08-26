package com.lhfeiyu.action.front.goods;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Dict;
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AA_UtilService;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.CommentService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.service.domain.GoodsPictureService;
import com.lhfeiyu.service.domain.GoodsService;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.service.domain.TreatmentService;
import com.lhfeiyu.thirdparty.wx.business.AuthAccess;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

@Controller
public class GoodsAction {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private TreatmentService treatmentService;
	@Autowired
	private DictService dictService;
	@Autowired
	private AuthCheckService authCheckService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/goodsIndex")
	public ModelAndView goodsStore(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(required=false) Integer shopId,
			@RequestParam(required=false) String r) {
		String path = LhPage.goodsList;
		try{
			JSONObject json = new JSONObject();
			User user = ActionUtil.checkSession4User(request.getSession());
			if(null == user){
				String jumpUrl = "/goodsIndex";
				if(Check.isNotNull(shopId)){
					jumpUrl += "?shopId="+shopId;
					json.put("shopId", shopId);
				}
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if(Check.isNotNull(shopId)){
				json.put("shopId", shopId);
			}
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("parentCode", "goods_type");
			List<Dict> goodsTypeList = dictService.selectListByCondition(map);
			modelMap.put("goodsTypeList", goodsTypeList);
			modelMap.put("paramJson", json);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/goodsIndex", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	@ResponseBody
	@RequestMapping(value = "/getTreatment",method=RequestMethod.POST)
	public JSONObject getTreatment(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			System.out.println(map);
			treatmentService.getTreatmentListSimple(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getTreatmentList", json);
		}
		System.out.println(json);
		return json;
	}
	//关注
	@ResponseBody
	@RequestMapping(value = "/updatahits", method = RequestMethod.POST)
	public JSONObject updatahits(@ModelAttribute Goods goods,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的goods，存在即返回
			goodsService.updateByPrimaryKeySelective(goods);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/updatahits", json);
		}
		return Result.success(json);
	}
	//获取所有服务信息
	@ResponseBody
	@RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
	public JSONObject getGoodsList(HttpServletRequest request,ModelMap modelMap) {
		List<Goods> goodsList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			map.put("mainStatus", 1);
			goodsList = goodsService.selectListByCondition(map);
			Integer total = goodsService.selectCountByCondition(map);
			Result.gridData(goodsList, total, json);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/getGoodsList", json);
		}
		return Result.success(json);
	}
	
	@RequestMapping(value = "/goods/{goodsId}")
	public ModelAndView goods(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer goodsId,
			@RequestParam(required = false) String r) {
		String path = LhPage.goodsInfo;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			Goods goods = goodsService.selectByPrimaryKey(goodsId);
			
			json.put("goodsId", goodsId);
			modelMap.addAttribute("goods", goods);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/goods" + "/" + goodsId, modelMap);
		}
		
		return new ModelAndView(path, modelMap);
	}
	//技师选择
	@RequestMapping(value = "/choiceProvider/{goodsId}")
	public ModelAndView choiceProvider(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer goodsId,
			@RequestParam(required = false) String r) {
		String path = LhPage.choiceProvider;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			Goods goods = goodsService.selectByPrimaryKey(goodsId);
			
			json.put("goodsId", goodsId);
			modelMap.addAttribute("goods", goods);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/choiceProvider" + "/" + goodsId, modelMap);
		}
		
		return new ModelAndView(path, modelMap);
	}
	@RequestMapping(value = "/goodsBespeak/{goodsId}")
	public ModelAndView goodsBespeak(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer goodsId,
			@RequestParam(required = false) String r) {
		String path = LhPage.goodsBespeak;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(modelMap,"/login");
			}
			Goods goods = goodsService.selectByPrimaryKey(goodsId);
			
			json.put("goodsId", goodsId);
			modelMap.addAttribute("goods", goods);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/goods" + "/" + goodsId, modelMap);
		}
		
		return new ModelAndView(path, modelMap);
	}
	
	@RequestMapping(value="/page/add")
	public ModelAndView goodsAdd(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(required=false) String from,
			@RequestParam(required=false) String r) {
		String path = LhPage.goodsAdd;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(request.getSession());
			if(null == user){
				String jumpUrl = "/goods/page/add";
				if(Check.isNotNull(from)){
					jumpUrl += "?from="+from;
					json.put("from", from);
				}
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if(Check.isNotNull(from)){
				json.put("from", from);
			}
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("parentCode", "goods_type");
			List<Dict> goodsTypeList = dictService.selectListByCondition(map);
			modelMap.put("goodsTypeList", goodsTypeList);
			modelMap.put("paramJson", json);
			
			//加载拍品类型下拉列表数据
			map.put("parentCode", "goods_type");
			String[] keys = {"value", "title", "parent"};
			//JSONArray goodsTypeAry = dictService.getDictArrayByParentCode("goods_type", keys);
			JSONArray array = new JSONArray();
			List<Dict> dictList = dictService.selectListByCondition(map);
			for(Dict d : dictList){
				JSONObject jsonItem = new JSONObject();
				jsonItem.put(keys[0], d.getCode());
				String codeName = d.getCodeName();
				if(Check.isNotNull(d.getDictValue())){
					codeName += "<span>"+d.getDictValue()+"</span>";
				}
				jsonItem.put(keys[1], codeName);
				jsonItem.put(keys[2], d.getParentCode());
				array.add(jsonItem);
			}
			json.put("goodsTypeAry", array);
			
			//微信验证
			String ticket = AuthAccess.getWxDataFromProperty("ticket");//从Property文件中获取ticket,如果文件中没有，则会远程获取
			String url = "http://weipaike.net/goods/page/add";
			if(Check.isNotNull(from)){
				url += "?from="+from;
			}
			url = ActionUtil.buildPromoterUrl(url, r);
			modelMap = AuthAccess.getSign(modelMap, ticket, url);
			modelMap.put("paramJson", json);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/goods/page/add", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	@RequestMapping(value="/page/baseUpdate")
	public ModelAndView goodsbaseUpdate(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(required=false) String from,
			@RequestParam Integer goodsId,
			@RequestParam(required=false) String r) {
		String path = LhPage.goodsUpdate;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(request.getSession());
			if(null == user){
				String jumpUrl = "/goods/page/baseUpdate";
				if(Check.isNotNull(from)){
					jumpUrl += "?from="+from;
					json.put("from", from);
				}
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if(Check.isNotNull(from)){
				json.put("from", from);
			}
			
			Map<String, Object> map = CommonGenerator.getHashMap();
			if(null != goodsId){
				map.put("id", goodsId);
				Goods goods = goodsService.selectByCondition(map);
				modelMap.put("goods", goods);
				json.put("goods", goods);
			}
			
			//加载拍品类型下拉列表数据
			map.clear();
			map.put("parentCode", "goods_type");
			String[] keys = {"value", "title", "parent"};
			JSONArray goodsTypeAry = dictService.getDictArrayByParentCode("goods_type", keys);
			json.put("goodsTypeAry", goodsTypeAry);
			
			/*Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("parentCode", "goods_type");
			List<Dict> goodsTypeList = dictService.selectListByCondition(map);
			modelMap.put("goodsTypeList", goodsTypeList);
			modelMap.put("paramJson", json);
			
			//加载拍品类型下拉列表数据
			map.put("parentCode", "goods_type");
			String[] keys = {"value", "title", "parent"};
			JSONArray goodsTypeAry = dictService.getDictArrayByParentCode("goods_type", keys);
			json.put("goodsTypeAry", goodsTypeAry);
			
			//微信验证
			String ticket = AuthAccess.getWxDataFromProperty("ticket");//从Property文件中获取ticket,如果文件中没有，则会远程获取
			String url = "http://weipaike.net/goods/page/add";
			if(Check.isNotNull(from)){
				url += "?from="+from;
			}
			url = ActionUtil.buildPromoterUrl(url, r);
			System.out.println("page/goods/add url: "+url);
			modelMap = AuthAccess.getSign(modelMap, ticket, url);*/
			modelMap.put("paramJson", json);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/goods/page/baseUpdate", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/getMyGoodsList", method=RequestMethod.POST)
	public JSONObject getMyGoodsList(HttpServletRequest request, 
			@RequestParam(required=false) String goodsTypeCode,
			@RequestParam(required=false) String from) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Integer userId = user.getId();
			Map<String, Object> map = ActionUtil.getAllParam(request);
			/*if(Check.isNotNull(from) && from.equals("market")){
				map.put("orderBy", "bonus_type_id DESC,go.offer_price DESC,A.created_at");
				map.put("ascOrdesc", "DESC");
				map.put("forMarket", 1);
			}*/
			map.put("userId", userId);//自加载属于自己的藏品
			//goodsService.getGoodsList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/goods/getMyGoodsList", json);
		}
		return Result.success(json);
	}

	
	@ResponseBody
	@RequestMapping(value="/addGoods", method=RequestMethod.POST)
	public JSONObject addGoods(HttpServletRequest request, 
			@ModelAttribute Goods goods,
			@RequestParam(required=false) String apSerial){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			String basePath = request.getServletContext().getRealPath("/");
			//TODO 修改
			//goodsService.addGoods(json, user, goods, apSerial, basePath);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/goods/addGoods", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/addGoodsForPc", method=RequestMethod.POST)
	public JSONObject addGoodsForPc(HttpServletRequest request, 
			@ModelAttribute Goods goods,
			@RequestParam String phone){
		JSONObject json = new JSONObject();
		try {
			//TODO 修改
			//service.addGoodsForPC(json, goods, phone);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/goods/addGoodsForPc", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateGoods", method=RequestMethod.POST)
	public JSONObject updateGoods(HttpServletRequest request, 
			@ModelAttribute Goods goods,
			@RequestParam(required=false) String amSerial,
			@RequestParam(required=false) String apSerial){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			//goodsService.updateGoods(json, goods, user);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/goods/updateGoods", json);
		}
		return Result.success(json);
	}
}

package com.lhfeiyu.action.front.shop;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.lhfeiyu.po.domain.Apply;
import com.lhfeiyu.po.domain.Dict;
import com.lhfeiyu.po.domain.Fans;
import com.lhfeiyu.po.domain.ForumMember;
import com.lhfeiyu.po.domain.Picture;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserRelation;
import com.lhfeiyu.service.domain.ApplyService;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.CommentService;
import com.lhfeiyu.service.domain.CreditService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.service.domain.FansService;
import com.lhfeiyu.service.domain.ForumMemberService;
import com.lhfeiyu.service.domain.PictureService;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.service.domain.UserRelationService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Result;

@Controller
public class ShopAction {
	@Autowired
	private ShopService shopService;
	@Autowired
	private FansService fansService;
	@Autowired
	private CommentService commentService;
	private CreditService creditService;
	@Autowired
	private DictService dictService;
	@Autowired
	private PictureService pictureService;
	@Autowired
	private ForumMemberService forumMemberService;
	@Autowired
	private AuthCheckService authCheckService;
	@Autowired
	private UserRelationService userRelationService;
	@Autowired
	private ApplyService applyService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/allShop", method=RequestMethod.GET)
	public ModelAndView showMarket(ModelMap modelMap,HttpServletRequest request
			,@RequestParam(required=false) String r) {
		String path = LhPage.allShop;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(request.getSession());
			/*if(null == sessionUser){
				return Result.userSessionInvalid(modelMap, "/allShop");
			}*/
			if(null != user && !"".equals(user)){
				modelMap.put("userId", user.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载所有店铺页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/creditShop/{shopId}")
	public ModelAndView credit(ModelMap modelMap,HttpSession session,
			@PathVariable Integer shopId
			,@RequestParam(required=false) String r,HttpServletRequest request){
		String path = LhPage.creditShop;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(session);
			/*if(null == sessionUser){
				return Result.userSessionInvalid(modelMap, "/antiqueCityDetail?relationId="+relationId);
			}*/
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("shopId", shopId);
			/*List<Credit>  creditList = creditService.selectListByCondition(map);
			if(creditList.size() > 0){
				Credit credit = creditList.get(0);
				modelMap.put("credit", credit);
			}
			if(null != user){
				modelMap.put("currentUserId", user.getId());
			}*/
			modelMap.put("userId", shopId);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载店铺信誉页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/agentAddProduct")
	public ModelAndView agentAddProduct(ModelMap modelMap,HttpSession session
			,@RequestParam(required=false) String r,HttpServletRequest request){
		String path = LhPage.agentAddProduct;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				String jumpUrl = "/agentAddProduct";
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentId", 120);
			List<Dict> goodsDidtList = dictService.selectListByCondition(map);
			modelMap.put("goodsDidtList", goodsDidtList);
			modelMap.put("user", user);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载代发产品申请页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/editShop")
	public ModelAndView editShop(ModelMap modelMap,HttpSession session,
			@RequestParam(required=false) String r,HttpServletRequest request){
		String path = LhPage.editShop;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				String jumpUrl = "/editShop";
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("relationType", 64);
			map.put("userId", user.getId());
			List<UserRelation> userRelationList = userRelationService.selectListByCondition(map);
			if(null != userRelationList){
				if(userRelationList.size() >0){
					UserRelation userRelation = userRelationList.get(0);
					modelMap.put("antiqueCityId", userRelation.getRelationId());
				}
			}
			map.clear();
			map.put("userId", user.getId());
			List<Shop> shopList = shopService.selectListByCondition(map);
			if(null != shopList){
				if(shopList.size() > 0){
					modelMap.put("shop", shopList.get(0));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载修改店铺信息页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/myShop", method=RequestMethod.GET)
	public ModelAndView myShop(ModelMap modelMap,HttpServletRequest request
			,@RequestParam(required=false) String r) {
		String path = LhPage.shop;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(request.getSession());
			if(null == user){
				String jumpUrl = "/myShop";
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			Integer userId = user.getId();
			modelMap.put("userId", userId);
			modelMap.put("currentUserId", userId);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", userId);
			List<Shop> shopList = shopService.selectListByCondition(map);
			if(Check.isNotNull(shopList)){
				Shop shop = shopList.get(0);
				if(null == shop.getCreditMargin() || shop.getCreditMargin() <= 0)shop.setCreditMargin(0);
				modelMap.put("shop", shop);
			}
			List<ForumMember> forumMemberList = forumMemberService.selectListByCondition(map);
			if(null != forumMemberList){
				if(forumMemberList.size() > 0){
					ForumMember forumMember = forumMemberList.get(0);
					modelMap.put("forumMember", forumMember);
				}
			}
			
			Integer meFocusCount =  fansService.selectCountByCondition(map);
			map.clear();
			map.put("objectId", userId);
			map.put("commentTypeId", 4);
			int	commentCount = commentService.selectCountByCondition(map);
			modelMap.put("commentCount", commentCount);
			map.clear();
			map.put("fansId", userId);
			Integer focusMeCount =  fansService.selectCountByCondition(map);
			if(null != user){
				map.put("userFansId", userId);
				map.put("fansUserId", user.getId());
				map.put("focus", 1);
				modelMap.put("currentUserId", user.getId());
			}
			if(null != user){
				List<Fans> fansUserList = fansService.selectListByCondition(map);//我的粉丝还关注了那些
				map.clear();
				map.put("userId", userId);
				map.put("fansId", user.getId());
				List<Fans> fansList =  fansService.selectListByCondition(map);
				if(fansList.size() > 0){
					Fans fans = fansList.get(0);
					modelMap.put("fans", fans);
				}else{
					Fans fans = new Fans();
					fans.setOverFoucs(0);
					modelMap.put("fans", fans);
				}
				modelMap.put("fansList", fansUserList);
			}
			modelMap.put("focusMeCount", focusMeCount);
			modelMap.put("meFocusCount", meFocusCount);
			modelMap.put("url", "/shop/"+userId);
			
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载我的店铺页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/market", method=RequestMethod.GET)
	public ModelAndView market(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(required=false) String searchName,
			@RequestParam(required=false) Integer searchType,
			@RequestParam(required=false) Integer searchGoodsType
			,@RequestParam(required=false) String r) {
		String path = LhPage.market;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			if(null != searchType && !"".equals(searchType)){
				if(searchType == 2){
					//path = LhPagePath.market;
				}else{
					path = LhPage.goods;
				}
				if(null != searchName && !"".equals(searchName)){
					modelMap.put("searchName", searchName);
				}
				if(null != searchGoodsType){
					modelMap.put("searchGoodsType", searchGoodsType);
				}
				modelMap.put("searchType", searchType);
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载所有店铺页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/goods", method=RequestMethod.GET)
	public ModelAndView goods(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(required=false) String searchName,
			@RequestParam(required=false) Integer searchType,
			@RequestParam(required=false) Integer searchGoodsType
			,@RequestParam(required=false) String r) {
		String path = LhPage.goods;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			if(null != searchType && !"".equals(searchType)){
				if(searchType == 1){
					//path = LhPagePath.goods;
				}else{
					path = LhPage.market;
				}
				if(null != searchName && !"".equals(searchName)){
					modelMap.put("searchName", searchName);
				}
				if(null != searchGoodsType){
					modelMap.put("searchGoodsType", searchGoodsType);
				}
				modelMap.put("searchType", searchType);
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载所有店铺页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/shopComment/{userId}", method=RequestMethod.GET)
	public ModelAndView shopComment(ModelMap modelMap,HttpServletRequest request
			,@PathVariable Integer userId
			,@RequestParam(required=false) String r) {
		String path = LhPage.shopComment;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(request.getSession());
			if(null == user){
				String jumpUrl = "/shopComment/"+userId;
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			modelMap.put("userId", userId);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载店铺评论页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	
	@RequestMapping(value="/shop/{userId}", method=RequestMethod.GET)
	public ModelAndView showShop(ModelMap modelMap,HttpServletRequest request,
			@PathVariable Integer userId
			,@RequestParam(required=false) String r) {
		String path = LhPage.shop;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
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
			map.clear();
			map.put("objectId", userId);
			map.put("commentTypeId", 4);
			int	commentCount = commentService.selectCountByCondition(map);
			modelMap.put("commentCount", commentCount);
			if(null != shopList && shopList.size() > 0){
				Shop shop = shopList.get(0);
				if(null == shop.getCreditMargin() || shop.getCreditMargin() <= 0)shop.setCreditMargin(0);
				modelMap.put("shop", shop);
			}
				//map.clear();
				/*User user = ActionUtil.checkSession4User(request.getSession());
				map.put("userId", userId);
				map.put("fansId", user.getId());
				List<Fans> isFocusList = fansService.selectListByCondition(map);
				if(isFocusList.size() > 0){
					Fans fans = isFocusList.get(0);
					modelMap.put("focusId", "1");
					modelMap.put("fansId", fans.getId());
				}*/
				map.clear();
				map.put("fansId", userId);
				Integer focusMeCount =  fansService.selectCountByCondition(map);
				map.put("count", 10);
				map.put("start", 0);
				User user = ActionUtil.checkSession4User(request.getSession());
				if(null != user){
					map.remove("fansId");
					map.put("userFansId", userId);
					map.put("fansUserId", user.getId());
					map.put("focus", 1);
					modelMap.put("currentUserId", user.getId());
				}else{
					//map.put("focus", 0);
				}
				if(null != user){
					List<Fans> fansUserList = fansService.selectListByCondition(map);//我的粉丝还关注了那些
					map.clear();
					map.put("userId", userId);
					map.put("fansId", user.getId());
					List<Fans> fansList =  fansService.selectListByCondition(map);
					if(fansList.size() > 0){
						Fans fans = fansList.get(0);
						modelMap.put("fans", fans);
					}else{
						Fans fans = new Fans();
						fans.setOverFoucs(0);
						modelMap.put("fans", fans);
					}
					modelMap.put("fansList", fansUserList);
				}
				
				modelMap.put("focusMeCount", focusMeCount);
				modelMap.put("meFocusCount", meFocusCount);
				modelMap.put("userId", userId);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载用户店铺页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/sale/{userId}", method=RequestMethod.GET)
	public ModelAndView onSale(ModelMap modelMap,HttpServletRequest request,
			@PathVariable Integer userId
			,@RequestParam(required=false) String r) {
		String path = LhPage.onSale;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(request.getSession());
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
			modelMap.put("focusMeCount", focusMeCount);
			modelMap.put("meFocusCount", meFocusCount);
			
			modelMap.put("userId", userId);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载用户店铺在售页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateShop", method=RequestMethod.POST)
	public JSONObject addOrUpdateShop(@ModelAttribute Shop shop,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Integer antiqueCityId = shop.getAntiqueCityId();
			Integer userId = user.getId();
			String username = user.getUsername();
			Date date = new Date();
			if(null == shop.getId()){//添加
				shop.setCreatedBy(username);
				shop.setCreatedAt(date);
				shop.setCreditMargin(0);//信誉保证金默认为0
				shopService.insert(shop);
			}else{//修改
				shop.setCreditMargin(null);//此处不能修改信誉保证金，有专门的方法入口修改
				shop.setUserId(null);
				shop.setMainStatus(1);
				/*String logo = shop.getLogo();
				if(null != logo && !"".equals(logo)){
					shop.setLogo(logo);
				}*/
				shop.setUpdatedBy(username);
				shop.setUpdatedAt(date);
				//Shop db_shop = shopService.selectByPrimaryKey(shopId);
				shopService.updateByPrimaryKeySelective(shop);
				if(null != antiqueCityId && !"".equals(antiqueCityId)){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("relationType", 64);
					map.put("userId", userId);
					List<UserRelation> db_userRelationList = userRelationService.selectListByCondition(map);
					if(null != db_userRelationList){
						if(db_userRelationList.size() > 0){
							UserRelation db_userRelation = db_userRelationList.get(0);
							db_userRelation.setRelationId(antiqueCityId);
							db_userRelation.setUpdatedAt(new Date());
							db_userRelation.setUpdatedBy(user.getUsername());
							userRelationService.updateByPrimaryKeySelective(db_userRelation);
						}
					}else{
						UserRelation userRelation = new UserRelation();
						userRelation.setRelationId(antiqueCityId);
						userRelation.setUserId(userId);
						userRelation.setRelationType(64);
						userRelation.setCreatedAt(new Date());
						userRelation.setCreatedBy(user.getUsername());
						userRelationService.insert(userRelation);
					}
				}
				String fileDBIds = request.getParameter("fileDBIds");
				if(null != fileDBIds && !"".equals(fileDBIds)){
					Picture p = new Picture();
					p.setId(Integer.valueOf(fileDBIds));
					p.setTypeId(84);
					pictureService.updateByPrimaryKeySelective(p);
				}
				json.put("status", "success");
				json.put("id",shop.getId());
				json.put("msg", "操作成功");
		  }
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改店铺信息出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/increaseCreditMoney", method=RequestMethod.POST)
	public JSONObject increaseCreditMoney(HttpServletRequest request
			,@RequestParam Integer money,@RequestParam String payPassword){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			if(null == money || money <= 0){
				return Result.failure(json, "金额不能为空", "money_null");
			}
			//TODO 修改
			//shopService.increaseCreditMoney(json, user, money, payPassword);
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_增加信誉保证金出现异常_", json);
		}
		return Result.success(json);
	} 
	
	@ResponseBody
	@RequestMapping(value="/updateShopInfo", method=RequestMethod.POST)
	public JSONObject updateShopInfo(@ModelAttribute Shop shop,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Integer antiqueCityId = shop.getAntiqueCityId();
			Integer userId = user.getId();
			String username = user.getUsername();
			shop.setUpdatedBy(username);
			shop.setUpdatedAt(new Date());
			
			shop.setCreditMargin(null);//只能修改基本信息
			shop.setUserId(null);
			shop.setMainStatus(1);
			
			shopService.updateByPrimaryKeySelective(shop);
			if(null != antiqueCityId && !"".equals(antiqueCityId)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("relationType", 64);
				map.put("userId", userId);
				List<UserRelation> db_userRelationList = userRelationService.selectListByCondition(map);
				if(null != db_userRelationList){
					if(db_userRelationList.size() > 0){
						UserRelation db_userRelation = db_userRelationList.get(0);
						db_userRelation.setRelationId(antiqueCityId);
						db_userRelation.setUpdatedAt(new Date());
						db_userRelation.setUpdatedBy(user.getUsername());
						userRelationService.updateByPrimaryKeySelective(db_userRelation);
					}
				}else{
					UserRelation userRelation = new UserRelation();
					userRelation.setRelationId(antiqueCityId);
					userRelation.setUserId(userId);
					userRelation.setRelationType(64);
					userRelation.setCreatedAt(new Date());
					userRelation.setCreatedBy(user.getUsername());
					userRelationService.insert(userRelation);
				}
			}
			String fileDBIds = request.getParameter("fileDBIds");
			if(null != fileDBIds && !"".equals(fileDBIds)){
				Picture p = new Picture();
				p.setId(Integer.valueOf(fileDBIds));
				p.setTypeId(84);
				pictureService.updateByPrimaryKeySelective(p);
			}
			json.put("status", "success");
			json.put("id",shop.getId());
			json.put("msg", "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改店铺基本信息出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getShopList", method=RequestMethod.POST)
	public JSONObject getShopList(HttpServletRequest request) {
		List<Shop> shopList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
//				String allShop = request.getParameter("allShop");
//				if(null == allShop){
//					map.put("haveGoods", 1);
//				}
			//TODO 修改
			shopList = shopService.selectListByCondition(map);
			System.out.println(shopList);
			Integer total = shopService.selectCountByCondition(map);
			json.put("rows", shopList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载店铺列表出现异常_", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value="/getShop/{shopId}", method=RequestMethod.POST)
	public JSONObject getShop(HttpServletRequest request,@PathVariable Integer shopId) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			map.put("id", shopId);
			Shop shop=shopService.selectByCondition(map);
			json.put("rows", shop);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载店铺列表出现异常_", json);
		}
		return Result.success(json);
	}
	//加盟商申请页面
	@RequestMapping(value = "/ShopApply/{shopId}")
	public ModelAndView courseApply(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer shopId,
			@RequestParam(required = false) String r) {
		String path = LhPage.ShopApply;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			Shop shop = shopService.selectByPrimaryKey(shopId);

			json.put("shopId", shopId);
			modelMap.addAttribute("shop", shop);
			modelMap.put("paramJson", json);
//			System.out.println("ShopApply"+shop);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/ShopApply" + "/" + shopId, modelMap);
		}
		
		return new ModelAndView(path, modelMap);
	}
	
	//添加加盟商申请
	@ResponseBody
	@RequestMapping(value = "/addShopApply", method = RequestMethod.POST)
	public JSONObject addForum(HttpServletRequest request,ModelMap modelMap,
			@ModelAttribute Apply apply) {
		//Investigation investigation
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
////			if(null == user){
////				return Result.userSessionInvalid(json);//返回session过期的json提示
////			}
//			Integer userId = user.getId();
//			String username = user.getUsername();
			Date date = new Date();
			apply.setApplyType(1);
			apply.setMainStatus(1);
			apply.setCreatedAt(date);
			apply.setCreatedBy(apply.getAttr4());
			apply.setApplyDate(date);
//			System.out.println("apply:"+apply);
			applyService.insert(apply);

			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/addInvestigation", json);
		}
		//System.out.println(json);
		return Result.success(json);
	}
	
	
	

	
	
	
	
}

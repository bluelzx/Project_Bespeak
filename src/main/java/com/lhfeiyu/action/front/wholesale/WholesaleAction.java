/*package com.lhfeiyu.action.front.wholesale;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.service.AuthCheckService;
import com.lhfeiyu.service.base.ShopService;
import com.lhfeiyu.service.WholesaleService;
import com.lhfeiyu.service.base.CartService;
import com.lhfeiyu.service.base.CommentService;
import com.lhfeiyu.service.base.FansService;
import com.lhfeiyu.service.base.ForumMemberService;
import com.lhfeiyu.service.base.GoodsPictureService;
import com.lhfeiyu.service.base.GoodsService;
import com.lhfeiyu.service.base.PictureService;
import com.lhfeiyu.tools.base.ActionUtil;
import com.lhfeiyu.tools.base.Check;
import com.lhfeiyu.tools.base.Pagination;
import com.lhfeiyu.tools.base.Result;
import com.lhfeiyu.util.base.RequestUtil;
import com.lhfeiyu.config.LhPage;
import com.lhfeiyu.po.base.Cart;
import com.lhfeiyu.po.base.Comment;
import com.lhfeiyu.po.base.ForumMember;
import com.lhfeiyu.po.base.Goods;
import com.lhfeiyu.po.base.GoodsPicture;
import com.lhfeiyu.po.base.Picture;
import com.lhfeiyu.po.base.Shop;
import com.lhfeiyu.po.base.User;
import com.lhfeiyu.po.Wholesale;

@Controller
public class WholesaleAction {
	@Autowired
	private ShopService  shopService;
	@Autowired
	private WholesaleService wholesaleService;
	@Autowired
	private FansService fansService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private GoodsPictureService goodsPictureService;
	@Autowired
	private CartService	 cartService;
	@Autowired
	private ForumMemberService forumMemberService;
	@Autowired
	private AuthCheckService authCheckService;
	@Autowired
	private PictureService pictureService;
	
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/wholesale")
	public ModelAndView wholesale(ModelMap modelMap,HttpSession session,
			@RequestParam(required=false) String searchName,
			@RequestParam(required=false) Integer searchType
			,@RequestParam(required=false) String r,HttpServletRequest request){
		String path = LhPage.wholesale;
		try{
			modelMap = authCheckService.checkWxLogin(request, modelMap);
			modelMap = Check.actionLhPagePromoterCheck(session, modelMap, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				return Result.userSessionInvalid(modelMap, "/wholesale");
			}
			if(null != searchType && !"".equals(searchType)){
				if(searchType == 2){
					if(null != searchName && !"".equals(searchName)){
						modelMap.put("searchName", searchName);
					}
					path = LhPage.wsGoods;
				}
			}
			if(null != user && !"".equals(user)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userId", user.getId());
				List<Wholesale>	wholesaleList = wholesaleService.selectListByCondition(map);
				if(wholesaleList.size() > 0){
					modelMap.put("wholesale", wholesaleList.get(0));
				}
			}
			modelMap.put("searchName", searchName);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载批发城页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	@RequestMapping(value="/wsGoods")
	public ModelAndView wsGoods(ModelMap modelMap,HttpSession session,
			@RequestParam(required=false) String searchName,
			@RequestParam(required=false) Integer searchType
			,@RequestParam(required=false) String r,HttpServletRequest request){
		String path = LhPage.wsGoods;
		try{
			modelMap = authCheckService.checkWxLogin(request, modelMap);
			modelMap = Check.actionLhPagePromoterCheck(session, modelMap, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				return Result.userSessionInvalid(modelMap, "/wholesale");
			}
			if(null != user && !"".equals(user)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userId", user.getId());
				List<Wholesale>	wholesaleList = wholesaleService.selectListByCondition(map);
				if(wholesaleList.size() > 0){
					modelMap.put("wholesale", wholesaleList.get(0));
				}
			}
			if(null != searchType && !"".equals(searchType)){
				if(searchType == 1){
					if(null != searchName && !"".equals(searchName)){
						modelMap.put("searchName", searchName);
					}
					path = LhPage.wholesale;
				}
			}
			modelMap.put("searchName", searchName);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载批发城页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	
	@RequestMapping(value="/editWholesale/{wholesaleId}")
	public ModelAndView editWholesale(ModelMap modelMap,HttpSession session,
			@PathVariable Integer wholesaleId
			,@RequestParam(required=false) String r,HttpServletRequest request){
		String path = LhPage.editWholesale;
		try{
			modelMap = authCheckService.checkWxLogin(request, modelMap);
			modelMap = Check.actionLhPagePromoterCheck(session, modelMap, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				String jumpUrl = "/editWholesale/"+wholesaleId;
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			Wholesale wholesale = wholesaleService.selectByPrimaryKey(wholesaleId);
			modelMap.put("wholesale", wholesale);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载修改批发城信息页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/applyWholesale")
	public ModelAndView applyWholesale(ModelMap modelMap,HttpSession session
			,@RequestParam(required=false) String r,HttpServletRequest request){
		String path = LhPage.applyWholesale;
		try{
			modelMap = authCheckService.checkWxLogin(request, modelMap);
			modelMap = Check.actionLhPagePromoterCheck(session, modelMap, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				String jumpUrl = "/applyWholesale";
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			Wholesale	wholesale = wholesaleService.selectByPrimaryKey(user.getId());
			if(null != wholesale && !"".equals(wholesale)){
				path = LhPage.addGoods;
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载批发城申请页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/ws/{userId}")
	public ModelAndView wholesaleGoods(ModelMap modelMap,HttpSession session,
			@PathVariable Integer userId
			,@RequestParam(required=false) String r,HttpServletRequest request) {
		String path = LhPage.wholesaleGoods;
		try{
			modelMap = authCheckService.checkWxLogin(request, modelMap);
			modelMap = Check.actionLhPagePromoterCheck(session, modelMap, r);
			User user = ActionUtil.checkSession4User(session);
			
			if(null == sessionUser){
				return Result.userSessionInvalid(modelMap, "/collectGoods?userId="+userId);
			}
			Integer session_userId = null;
			if(null != user){modelMap.put("currentUserId", user.getId());session_userId = user.getId();}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", userId);
			List<Shop> shopList = shopService.selectListByCondition(map);
			List<Wholesale> wholesaleList =  wholesaleService.selectListByCondition(map);
			if(wholesaleList.size() > 0){
				modelMap.put("wholesale", wholesaleList.get(0));
			}
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
			if(null != session_userId && !"".equals(session_userId)){
				map.clear();
				map.put("userId", session_userId);
				map.put("mainStatus", 1);//
				List<Shop> db_shopList = shopService.selectListByCondition(map);//查询该用户是否开通批发城
				if(db_shopList.size() > 0){
					modelMap.put("hasShop", "hasShop");
				}
				List<Wholesale> db_wholesaleList =  wholesaleService.selectListByCondition(map);//查询该用户是否开通批发城
				if(db_wholesaleList.size() > 0){
					modelMap.put("hasWholesale","hasWholesale");
				}
			}
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
	
	@RequestMapping(value="/wsg/{id}/{wholesaleId}/{wholesaleUserId}")
	public ModelAndView wholesaleDetail(ModelMap modelMap,HttpServletRequest request,
			@PathVariable Integer id,
			@PathVariable Integer wholesaleId,
			@PathVariable Integer wholesaleUserId
			,@RequestParam(required=false) String r
			,@RequestParam(required=false) Integer  moduleId) {
		String path = LhPage.wholesaleDetail;
		try{
			modelMap = authCheckService.checkWxLogin(request, modelMap);
			
			User user = ActionUtil.checkSession4User(request.getSession());
			if(null == user){
				return Result.userSessionInvalid(modelMap, "/wsg/"+id+"/"+wholesaleId);
			}
			if(null != user){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userId", user.getId());
				List<Cart> cartList =  cartService.selectListByCondition(map);
				if(cartList.size() > 0){
					modelMap.put("cartNum", cartList.size());
				}
			}
			Map<String, Object> map = RequestUtil.getRequestParam(request);//自动获取所有参数（查询条件）
			//map.put("mainStatus", 79);
			if(null != wholesaleId && !"".equals(wholesaleId)){
				map.put("wholesaleId", wholesaleId);
				if(null != user){
					map.put("wholesaleUserId", user.getId());
				}
				map.put("wholesaleUserId", wholesaleUserId);
				map.put("wholesaleId", wholesaleId);
				map.put("id", id);
			}
			List<Goods> GoodsList = goodsService.selectListByCondition(map);
			if(GoodsList.size() > 0){
				Goods goods= GoodsList.get(0);
				map.clear();
				if(moduleId == null){
					map.put("goodsId", goods.getId());
				}else{
					map.put("goodsId", goods.getGoodsFrom());
				}
				//map.put("isNOTCover", 2);
				List<GoodsPicture> GoodsPictureList = goodsPictureService.selectListByCondition(map);
				modelMap.put("GoodsPictureList", GoodsPictureList);
				modelMap.put("goods", goods);
				map.clear();
				map.put("objectId", goods.getId());
				map.put("commentTypeId", 3);
				map.put("start", 0);
				map.put("count", 6);
				List<Comment> commentList =  commentService.selectListByCondition(map);
				modelMap.put("commentList", commentList);
				if(null != user){
					modelMap.put("currentUserId", user.getId());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载批发城商品详情页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateWholesale", method=RequestMethod.POST)
	public JSONObject addOrUpdateWholesale(@ModelAttribute Wholesale wholesale,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			if(null == wholesale.getId()){//添加
				wholesale.setCreatedBy(user.getUsername());
				wholesale.setCreatedAt(new Date());
				wholesaleService.insert(wholesale);
			}else{//修改
				String logo = wholesale.getLogo();
				if(null != logo && !"".equals(logo)){
					wholesale.setLogo(logo);
				}
				wholesale.setUpdatedBy(user.getUsername());
				wholesale.setUpdatedAt(new Date());
				wholesaleService.updateByPrimaryKeySelective(wholesale);
				String fileDBIds = request.getParameter("fileDBIds");
				if(null != fileDBIds && !"".equals(fileDBIds)){
					Picture p = new Picture();
					p.setId(Integer.valueOf(fileDBIds));
					p.setTypeId(84);
					pictureService.updateByPrimaryKeySelective(p);
				}
				json.put("status", "success");
				json.put("id",wholesale.getId());
				json.put("msg", "操作成功");
		  }
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改批发城信息出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/addWholesaleGoods", method=RequestMethod.POST)
	public JSONObject addWholesaleGoods(HttpServletRequest request,@RequestParam String GoodsAry){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			String username = user.getUsername();
			Integer userId = user.getId();
			Date date = new Date();
			if(null != GoodsAry){
				List<Goods> goodsList = JSONArray.parseArray(GoodsAry, Goods.class);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userId", userId);
				List<Wholesale> wholesaleList = wholesaleService.selectListByCondition(map);
				if(Check.isNotNull(wholesaleList)){
					Wholesale wholesale = wholesaleList.get(0);
					goodsService.addBatchGoodsInfo(goodsList,userId,username,date,wholesale);
					json.put("status", "success");
					json.put("msg", "操作成功");
				}else{
					json.put("status", "failure");
					json.put("msg", "您尚未开通批发城，无法发布批发城商品");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改批发城商品出现异常_", json);
		}
		return Result.success(json);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getWholesaleList", method=RequestMethod.POST)
	public JSONObject getWholesaleList(HttpServletRequest request) {
		List<Wholesale> wholesaleList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			wholesaleList = wholesaleService.getWholesaleList(map);
			Integer total = wholesaleService.selectCountByCondition(map);
			json.put("rows", wholesaleList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载批发城列表出现异常_", json);
		}
		return Result.success(json);
	}
	
}
*/
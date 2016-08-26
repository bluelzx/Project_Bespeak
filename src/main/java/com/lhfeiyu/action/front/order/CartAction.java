package com.lhfeiyu.action.front.order;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.config.domain.LhTable;
import com.lhfeiyu.po.domain.Cart;
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.CartService;
import com.lhfeiyu.service.domain.GoodsService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
public class CartAction {
	@Autowired
	private CartService	 cartService;
	@Autowired
	private GoodsService  goodsService;
	@Autowired
	private AuthCheckService authCheckService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/cart")
	public ModelAndView cart(ModelMap modelMap,HttpSession session
			,@RequestParam(required=false) String r,HttpServletRequest request) {
		String path = LhPage.cart;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				String jumpUrl = "/cartHistory";
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载已卖商品页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	@RequestMapping(value="/purchased")
	public ModelAndView purchased(ModelMap modelMap,HttpSession session
			,@RequestParam(required=false) String r,HttpServletRequest request) {
		String path = LhPage.purchased;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				String jumpUrl = "/purchased";
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载已买商品页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	@RequestMapping(value="/myCart")
	public ModelAndView myCart(ModelMap modelMap,HttpSession session
			,@RequestParam(required=false) String r,HttpServletRequest request) {
		String path = LhPage.myCart;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				String jumpUrl = "/myCart";
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载我的购物车页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateCart", method=RequestMethod.POST)
	public JSONObject addOrUpdateCart(@ModelAttribute Cart cart,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Integer userId = user.getId();
			String username = user.getUsername();
			Date date = new Date();
			if(userId !=  cart.getUserId() && userId.equals(cart.getUserId()) == false){
				if(null == cart.getId()){//添加
					cart.setCreatedBy(username);
					cart.setGoodsNumber(1);
					cart.setCreatedAt(date);
					cart.setUserId(userId);
					cart.setSessionId(request.getSession().getId());
					cart.setMainStatus(1);
					cartService.insert(cart);
				}else{//修改
					cart.setUpdatedBy(username);
					cart.setUpdatedAt(date);
					cartService.updateByPrimaryKeySelective(cart);
				}
				json.put("status", "success");
				json.put("id",cart.getId());
				json.put("msg", "操作成功");
			}else{
				json.put("status", "falure");
				json.put("msg", "自己不能把自己的商品加入购物车");
			}
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改购物车物品出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/cartBatchCheckOut", method=RequestMethod.POST)
	public JSONObject cartBatchCheckOut(@RequestParam String list,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			List<Cart> cartList = JSONArray.parseArray(list, Cart.class);//获取到购物车信息
			if(null != cartList && cartList.size() > 0){
				String goodsIds = "";
				Double total = 0.0;
				Double db_total = 0.0;
				for(int i=0;i < cartList.size();i++){
					goodsIds += ","+cartList.get(i).getGoodsId().toString();
					Double price =  cartList.get(i).getShopPrice().doubleValue();
					Double num = cartList.get(i).getGoodsNumber().doubleValue();
					total += price * num;
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("goodsIds", goodsIds.substring(1));
				List<Goods> goodsList = goodsService.selectListByCondition(map);
				for(int k=0;k<goodsList.size();k++){
					Double price =  goodsList.get(k).getShopPrice().doubleValue();
					Double num = cartList.get(k).getGoodsNumber().doubleValue();
					db_total += price * num;
				}
				if(total.equals(db_total)){
					//Integer userId = user.getId();
					//String username = user.getUsername();
					for(int j=0;j<goodsList.size();j++){
						/*GoodsOffers goodsOffers = new GoodsOffers();
						goodsOffers.setUserId(userId);
						goodsOffers.setUsername(username);
						goodsOffers.setShopId(goodsList.get(j).getShopId());
						goodsOffers.setGoodsId(goodsList.get(j).getId());
						goodsOffers.setOfferStatus(1);
						goodsOffers.setOfferPrice(cartList.get(j).getShopPrice().multiply(new BigDecimal(cartList.get(j).getGoodsNumber())));
						goodsOffers.setOfferAt(new Date());
						goodsOffers.setCreatedAt(new Date());
						goodsOffers.setCreatedBy(username);
						goodsOffers.setMainStatus(1);*/
						//goodsOffersService.insert(goodsOffers);
						//goodsList.get(j).setMainStatus(78);//冻结商品
						//goodsService.updateByPrimaryKeySelective(goodsList.get(j));
						Cart cart = new Cart();
						cart.setId(cartList.get(j).getId());
						cart.setMainStatus(2);
						cartService.updateByPrimaryKeySelective(cart);
						
					}
					json.put("status", "success");
					json.put("msg", "等待卖家的同意,即可付款");
					/*Integer userId = user.getId();
					Date d = new Date();
					OrderInfo orderInfo = new OrderInfo();
					orderInfo.setUserId(userId);
					orderInfo.setPayStatus(1);
					orderInfo.setOrderStatus(1);
					orderInfo.setShippingStatus(1);
					orderInfo.setCreatedAt(d);
					orderInfo.setCreatedBy("-SYS-");
					orderInfo.setMainStatus(1);
					orderInfoService.insert(orderInfo);//生成订单
					Integer orderId = orderInfo.getId();
					List<OrderGoods> orderGoodsList = new ArrayList<OrderGoods>(goodsList.size());
					for(int j=0;j<goodsList.size();j++){
						OrderGoods orderGoods = new OrderGoods();
						orderGoods.setGoodsFrom(1);//批发城
						orderGoods.setGoodsType(2);//批发城商品
						orderGoods.setOrderId(orderId);
						orderGoods.setCreatedAt(d);
						orderGoods.setMainStatus(1);
						orderGoods.setCreatedBy("-SYS-");
						orderGoods.setGoodsId(goodsList.get(j).getId());
						orderGoods.setGoodsName(goodsList.get(j).getGoodsName());
						orderGoods.setGoodsNumber(cartList.get(j).getGoodsNumber());
						orderGoods.setShopPrice(cartList.get(j).getShopPrice());
						orderGoodsList.add(orderGoods);
					}
					orderGoodsService.insertBatch(orderGoodsList);
					//更新商品状态
					for(Goods g:goodsList){
						g.setMainStatus(78);//冻结商品
						goodsService.updateByPrimaryKeySelective(g);
					}
					//发消息通知
					String title = "恭喜您购物车商品系统已经为您生成订单";
					String content = "恭喜您购物车商品系统已经为您生成订单，可以到个人中心查看订单并付款";
					Notice notice = new Notice();
					notice.setSerial(CommonGenerator.getSerialByDate("n"));
					notice.setReceiverId(userId);
					notice.setContent(content);
					notice.setReadStatus(1);//未阅读
					notice.setTypeId(33);//新订单生成
					notice.setTitle(title);
					notice.setCreatedAt(d);
					notice.setCreatedBy("-SYS-");
					noticeService.insert(notice);//新增通知*/	
					
			}else{
					json.put("status", "failure");
					json.put("msg", "商品价格有变动,请重新购买商品");
				}
		}else{
			json.put("status", "failure");
			json.put("msg", "当前购物车暂无商品,请先去购买商品");
		}
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_购物车物品结算出现异常_", json);
		}
		return Result.success(json);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/addBatchCart", method=RequestMethod.POST)
	public JSONObject addBatchCart(@RequestParam String list,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", user.getId());
			map.put("table", LhTable.cart);
			cartService.deleteByUserId(map);
			List<Cart> cartList = JSONArray.parseArray(list, Cart.class);
			Integer mainStatus = 1;
			Date date = new Date();
			String createdBy = user.getUsername();
			Integer userId = user.getId();
			for(Cart c:cartList){
				c.setUserId(userId);
				c.setMainStatus(mainStatus);
				c.setCreatedAt(date);
				c.setCreatedBy(createdBy);
			}
			cartService.addBatchCart(cartList);
			json.put("status", "success");
			json.put("msg", "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_批量添加购物车物品出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getCartList", method=RequestMethod.POST)
	public JSONObject getCartList(HttpServletRequest request) {
		List<Cart> cartList = null;
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			map.put("userId", user.getId());
			cartList = cartService.selectListByCondition(map);
			Integer total = cartService.selectCountByCondition(map);
			json.put("rows", cartList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载购物车商品列表出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteCartGoods", method=RequestMethod.POST)
	public JSONObject deleteCartGoods(HttpServletRequest request,@RequestParam(required=false) Integer id
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
				Cart cart = cartService.selectByPrimaryKey(id);
				if(null != cart){
					int id1 = cart.getUserId();
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
				map.put("id", id);
				List<Cart> cartList = cartService.selectListByCondition(map);
				if(null != cartList && cartList.size()>0){
					db_id = cartList.get(0).getId();
				}
			}
			if(null != db_id){
				cartService.deleteByPrimaryKey(db_id);
			}
			json.put("status","success");
			json.put("msg","已成功移除该商品");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_购物车删除商品出现异常_", json);
		}
		return Result.success(json);
	}
	
}

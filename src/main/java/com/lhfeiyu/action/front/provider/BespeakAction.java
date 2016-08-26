package com.lhfeiyu.action.front.provider;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.Bespeak;
import com.lhfeiyu.po.domain.Coupon;
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.OrderInfo;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.service.base.BaseBespeakService;
import com.lhfeiyu.service.base.BaseCouponService;
import com.lhfeiyu.service.base.BaseUserFundService;
import com.lhfeiyu.service.base.BaseUserService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.service.domain.GoodsService;
import com.lhfeiyu.service.domain.OrderInfoService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：预约技师 Bespeak <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.bespeak.BackBespeakAction <p>
 */
@Controller
public class BespeakAction {
	
	@Autowired
	private BaseBespeakService bespeakService;
	@Autowired
	private DictService dictService;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private BaseCouponService couponService;
	@Autowired
	private BaseUserFundService userFundService;
	@Autowired
	private BaseUserService userService;
	@Autowired
	private GoodsService goodsService;
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 新增或修改预约技师（新增和修改方法对应Serivce中的不同方法）
	 * @param bespeak ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateBespeak", method = RequestMethod.POST)
	public JSONObject addUpdateBespeak(@ModelAttribute Bespeak bespeak,HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date startTime = bespeak.getBespeakStartTime();
		Integer hourse = bespeak.getTimeNum();
		try {
			Date d = new Date();
			User user = ActionUtil.checkSession4User(request.getSession());
			if(null != bespeak.getId()){//更新
				bespeak.setUpdatedAt(d); 
				bespeak.setUpdatedBy(user.getUsername());
				Date endTimeBefor = new Date(startTime.getTime() + (hourse * 60 * 60 * 1000));
				bespeak.setBespeakEndTime(endTimeBefor);
				bespeakService.updateByPrimaryKeySelective(bespeak);
			}else{//新增
				//新增订单
				
				Map<String, Object> map = CommonGenerator.getHashMap();
				map.put("id",bespeak.getShopId());
				Goods goods=goodsService.selectByCondition(map);
				BigDecimal shopPrice=goods.getShopPrice();
				BigDecimal GoodsNumber= new BigDecimal(bespeak.getGoodsNumber());
				BigDecimal price = null;
				if (null!=bespeak.getCouponId()) {
					Integer couponId=bespeak.getCouponId();
					Coupon coupon=couponService.selectByPrimaryKey(couponId);
					coupon.setDealStatus(2);
					couponService.updateByPrimaryKeySelective(coupon);
					if(null!=coupon.getMoney() ){
						price=shopPrice.multiply(GoodsNumber).subtract(coupon.getMoney());
					}else if(null!=coupon.getDiscount()){
						BigDecimal discount=new BigDecimal(coupon.getDiscount()/100);
						price=shopPrice.multiply(GoodsNumber).multiply(discount);
					}
				}else{
				 price=shopPrice.multiply(GoodsNumber);
				}
				System.out.println("付款金额："+price);
				OrderInfo orderInfo=new OrderInfo();
				orderInfo.setProviderId(bespeak.getProviderId());
				orderInfo.setUserId(user.getId());
				orderInfo.setGoodsId(bespeak.getGoodsId());
				orderInfo.setGoodsName(bespeak.getGoodsName());
				orderInfo.setShopId(bespeak.getShopId());
				orderInfo.setGoodsNumber(bespeak.getGoodsNumber());
				orderInfo.setTypeId(bespeak.getTypeId());
				
				orderInfo.setTotalPrice(price);
				
				orderInfo.setAddress(bespeak.getAddress());
				orderInfo.setSerial(CommonGenerator.getSerialByDate("od"));
				orderInfo.setOrderSn(CommonGenerator.getSerialByDate("od"));
				orderInfo.setCouponId(bespeak.getCouponId());
				orderInfo.setCouponDiscount(bespeak.getCouponDiscount());
				orderInfo.setCity(bespeak.getCity()+"");
				orderInfo.setProvince(bespeak.getProvince()+"");
				orderInfo.setUsername(bespeak.getUserName());//获取创建预约订单人
				orderInfo.setPhone(user.getPhone());
				orderInfo.setMainStatus(1);
				orderInfo.setCreatedAt(d);
				//在预约付款完成后即设置初始订单状态
				orderInfo.setOrderStatusCode("order_status_todo");//商家未接单
				orderInfo.setOrderDoneStausCode("order_done_status_no");//默认总状态为订单未完成
				orderInfo.setPayStatusCode("pay_status_done");//已付款
				orderInfo.setShippingStatusCode(LhConst.shipping_status_todo);//服务未开始
				
//				order.setOrderStatusCode(LhConst.order_status_todo);		// 数据字典代码-订单状态：商家未接单，商家已接单，派单中，已派单，订单完成,取消订单，已结束
//				order.setShippingStatusCode(LhConst.shipping_status_todo);// 数据字典代码-服务情况：服务中，服务已完成
//				order.setPayStatusCode(LhConst.pay_status_todo);			// 数据字典代码-支付状态：未付款，已付款，申请退款中,已退款，拒绝退款
//				order.setOrderDoneStausCode(LhConst.order_done_status_no);// 数据字典代码-订单完成大状态：未完成，完成
				
				Date endTimeBefor = new Date(startTime.getTime() + (hourse * 60 * 60 * 1000));
				bespeak.setBespeakEndTime(endTimeBefor);
				bespeak.setShopId(goods.getShopId());
				bespeak.setPrice(price);
				bespeak.setUserId(user.getId());
				bespeak.setOrderSn(orderInfo.getOrderSn());
				bespeak.setCreatedAt(d); 
				bespeak.setSerial(CommonGenerator.getSerialByDate("bp"));
				bespeak.setCreatedBy(user.getUsername());
				
				bespeakService.insert(bespeak);
				orderInfoService.insert(orderInfo);
			}
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/addUpdateBespeak", json);
		}
		return Result.success(json);
	}

	/**
	 * 逻辑删除预约技师数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateBespeakDelete", method=RequestMethod.POST)
	public JSONObject updateBespeakDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的bespeak，存在即返回
			bespeakService.updateDeletedNowByIds(ids, user.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/updateBespeakDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除预约技师
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBespeakThorough",method=RequestMethod.POST)
	public JSONObject deleteBespeakThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//User user = ActionUtil.checkSession4User(request.getSession());//验证session中的bespeak，存在即返回
			bespeakService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/deleteBespeakThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复预约技师（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateBespeakRecover", method=RequestMethod.POST)
	public JSONObject updateBespeakRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的bespeak，存在即返回
			bespeakService.updateDeletedNullByIds(ids, user.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/updateBespeakRecover", json);
		}
		return json;
	}
	
	//通过指定id查询预约
	@ResponseBody
	@RequestMapping(value = "/getbespeaknameArray", method=RequestMethod.POST)
	public JSONArray getbespeaknameArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Bespeak> bespeaknameList = bespeakService.selectListByCondition(map);
			for(Bespeak h:bespeaknameList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
//				json.put("name",h.getUsername());
//				System.out.println(h.getBespeakname());
//				System.out.println(h.getBespeakname());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/getbespeaknameArray-加载作者名列表出现异常", array);
		}
		return array;
	}

}


package com.lhfeiyu.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhfeiyu.dao.domain.AccountLogMapper;
import com.lhfeiyu.dao.domain.CartMapper;
import com.lhfeiyu.dao.domain.GoodsMapper;
import com.lhfeiyu.dao.domain.OrderGoodsMapper;
import com.lhfeiyu.dao.domain.OrderInfoMapper;
import com.lhfeiyu.dao.domain.UserFundMapper;
import com.lhfeiyu.po.domain.Cart;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：购物车 Cart <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseCartService")
public class BaseCartService extends CommonService<Cart>{
	@Autowired
	CartMapper mapper;
	@Autowired
	OrderInfoMapper orderInfoMapper;
	@Autowired
	OrderGoodsMapper orderGoodsMapper;
	@Autowired
	AccountLogMapper accountLogMapper;
	@Autowired
	UserFundMapper userFundMapper;
	@Autowired
	GoodsMapper goodsMapper;
	
	public void addBatchCart(List<Cart> cartList) {
		mapper.addBatchCart(cartList);
	}

	public void deleteByUserId(Map<String, Object> map) {
		mapper.deleteByUserId(map);
	}

	/*public JSONObject addCheckOut(List<Cart> cartList,User user) {
		JSONObject json = new JSONObject();
		UserFund userFund =  userFundMapper.selectByPrimaryKey(user.getId());
		String goodsIds = "";
		Integer total = 0;
		Integer db_total = 0;
		for(int i=0;i < cartList.size();i++){
			goodsIds += ","+cartList.get(i).getGoodsId().toString();
			Integer price =  cartList.get(i).getShopPrice().intValue();
			Integer num = cartList.get(i).getGoodsNumber();
			total += price * num;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsIds", goodsIds.substring(1));
		List<Goods> goodsList = goodsMapper.selectListByCondition(map);
		for(int k=0;k<goodsList.size();k++){
			Integer price =  goodsList.get(k).getShopPrice().intValue();
			Integer num = cartList.get(k).getGoodsNumber();
			db_total += price * num;
		}
		if(userFund.getAvaliableMoney().intValue() > total){
			if(total == db_total){
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setUserId(user.getId());
			orderInfo.setPayStatus(3);
			orderInfo.setOrderStatus(2);
			orderInfo.setShippingStatus(2);
			orderInfoMapper.insert(orderInfo);//生成订单
			Integer orderId = orderInfo.getId();
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setOrderId(orderId);
			for(int j=0;j<goodsList.size();j++){
				orderGoods.setGoodsId(goodsList.get(j).getId());
				orderGoods.setGoodsName(goodsList.get(j).getGoodsName());
				orderGoods.setGoodsNumber(cartList.get(j).getGoodsNumber());
				orderGoods.setShopPrice(cartList.get(j).getShopPrice());
				orderGoodsMapper.insert(orderGoods);//商品订单
			}
			AccountLog  accountLog = new AccountLog();
			String serial = CommonGenerator.getSerialByDate("ac");
			accountLog.setSerial(serial);
			accountLog.setUserId(user.getId());
			accountLog.setUsername(user.getUsername());
			accountLog.setMoney(new BigDecimal(total));
			accountLog.setInOrOut(2);
			accountLogMapper.insert(accountLog);//操作记录
			userFund.setAvaliableMoney(new BigDecimal(userFund.getAvaliableMoney().intValue() - total));
			userFund.setFrozenMoney(new BigDecimal(userFund.getFrozenMoney().intValue() + total));
			userFundMapper.updateByPrimaryKeySelective(userFund);//用户资金
			mapper.updateByCartMainStatus(user.getId());
			json.put("status", "success");
			json.put("msg", "操作成功");
			}else{
				json.put("status", "failure");
				json.put("msg", "商品价格有变动,请重新购买商品");
			}
		}else{
			json.put("status", "failure");
			json.put("msg", "您的余额不足,请充值.");
		}
		return json;
	}
	
	public JSONObject addOrderByCart(String payPass, int userId, String username
			, Integer theOtherId, String theOtherName, JSONObject json, boolean noPassword,String linkUrl){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", userId);
			List<Cart> cartList = cartMapper.selectListByCondition(map);//查出用户购物车中的所有商品
			Date date = new Date();
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrderSn(CommonGenerator.getSerialByDate("os"));
			orderInfo.setUserId(userId);
			orderInfo.setUsername(username);
			orderInfo.setOrderStatus(1);//订单的状态;1未完成,2已完成,3已取消,4无效,5退货
			orderInfo.setShippingStatus(1);//商品配送情况;1未发货,2已发货,3已收货,4退货
			orderInfo.setPayStatus(1);//支付状态;1未付款;2付款中;3已付款
			orderInfo.setCreatedAt(date);
			orderInfo.setCreatedBy(username);
			orderInfoMapper.insert(orderInfo);//生成订单
			Integer orderId = orderInfo.getId();
			
			List<OrderGoods> odList = new ArrayList<OrderGoods>();
			BigDecimal total = new BigDecimal(0);
			for(Cart c : cartList){
				BigDecimal price = c.getShopPrice();
				if(null == price || price.doubleValue()<0)continue;//过滤到没有价格的商品
				total = total.add(price);
				OrderGoods od = new OrderGoods();
				od.setOrderId(orderId);
				od.setGoodsId(c.getGoodsId());
				od.setGoodsName(c.getGoodsName());
				od.setGoodsNumber(c.getGoodsNumber());
				od.setShopPrice(price);
				odList.add(od);
			}
			orderGoodsMapper.insertBatch(odList);//批量新增订单商品
			json = freezeMoney(payPass, total, userId, username, orderId, theOtherId, theOtherName, json, noPassword,linkUrl);//冻结资金并新增账户变动记录
			if(json.containsKey("error_desc")){ return json; }
			OrderInfo oiPayed = new OrderInfo();
			oiPayed.setId(orderId);
			oiPayed.setMainStatus(3);//已付款
			oiPayed.setUpdatedAt(date);
			oiPayed.setUpdatedBy(username);
			orderInfoMapper.updateByPrimaryKeySelective(oiPayed);
			cartMapper.updateByCartMainStatus(userId);//更新购物车状态
			return json;
		}*/
	
	
}

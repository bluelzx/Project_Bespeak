package com.lhfeiyu.service.base;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhfeiyu.dao.domain.CreditMapper;
import com.lhfeiyu.dao.domain.OrderInfoMapper;
import com.lhfeiyu.dao.domain.ShopMapper;
import com.lhfeiyu.po.domain.Credit;

@Service
public class BaseCreditService extends CommonService<Credit> {

	@Autowired
	CreditMapper mapper;
	@Autowired
	OrderInfoMapper orderInfoMapper;
	@Autowired
	ShopMapper shopMapper;
	
	
	/**返回数量**/
	private Double TotalCreditNUm(Integer userId,Integer orderStatus){
		Double count = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		if(null != orderStatus){
			if(orderStatus == 2){
				map.put("orderStatus", 2);//已完成的订单
				Integer finish = orderInfoMapper.selectCountByCondition(map);
				count =  finish.doubleValue();
			}else if(orderStatus == 3){
				map.put("orderStatus", 3);//取消的订单
				Integer cancel = orderInfoMapper.selectCountByCondition(map);
				count =  cancel.doubleValue();
			}else if(orderStatus == 5){
				map.put("orderStatus", 5);//退货的订单
				Integer returnGoods = orderInfoMapper.selectCountByCondition(map);
				count =  returnGoods.doubleValue();
			}
		}else{
			Integer sum = orderInfoMapper.selectCountByCondition(map);
			count =  sum.doubleValue();
		}
		return count;
	}
	
	
	private Credit selectedCreditByuserId(Integer userId){
		Credit credit = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		List<Credit> creditUserList = mapper.selectListByCondition(map);//查询买家是否有信誉数据
		if(creditUserList.size() > 0){
			credit = creditUserList.get(0);
		}
		return credit;
	}
	
	private Credit selectedCreditByShopId(Integer shopId){
		Credit credit = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("shopId", shopId);
		List<Credit> creditShopList = mapper.selectListByCondition(map);//查询商家是否有信誉数据
		if(creditShopList.size() > 0){
			credit = creditShopList.get(0);
		}
		return credit;
	}
	
	/**更新买家的信誉*/
	public void updateUserCredit(Integer userId){
		Double sum = TotalCreditNUm(userId,null);
		Double finishCount = TotalCreditNUm(userId,2);
		Double cancelCount = TotalCreditNUm(userId,3);
		Double returnGoodsCount = TotalCreditNUm(userId,5);
		Credit credit = selectedCreditByuserId(userId);
		if(null != sum){
			if(sum > 0){
				Double finish = finishCount / sum;
				Double cancel = cancelCount / sum;
				Double returnGoods = returnGoodsCount / sum;
				credit.setBackRateC(new BigDecimal(returnGoods));
				credit.setBreakRateC(new BigDecimal(cancel));
				credit.setDoneRateC(new BigDecimal(finish));
			}
		}
		Date date = new Date();
		if(null != credit ){//更新用户的信誉
			credit.setUpdatedAt(date);
			credit.setUpdatedBy("SYS");
			mapper.updateByPrimaryKeySelective(credit);
		}else{//新增用户的信誉
			Credit credit1 = new Credit();
			credit1.setUserId(userId);
			credit1.setBackRateC(new BigDecimal(0));
			credit1.setBreakRateC(new BigDecimal(0));
			credit1.setDoneRateC(new BigDecimal(0));
			credit1.setLastResetTime(date);
			credit1.setCreatedAt(date);
			credit1.setCreatedBy("SYS");
			mapper.insert(credit1);
		}
	}
	
	
	/**更新商家信誉**/
	public void updateShopCredit(Integer ShopId,Integer userId){
		Double sum = TotalCreditNUm(userId,null);
		Double finishCount = TotalCreditNUm(userId,2);
		Double cancelCount = TotalCreditNUm(userId,3);
		Double returnGoodsCount = TotalCreditNUm(userId,5);
		Credit credit = selectedCreditByShopId(ShopId);
		if(null != sum){
			if(sum > 0){
				Double finish = finishCount / sum;
				Double cancel = cancelCount / sum;
				Double returnGoods = returnGoodsCount / sum;
				credit.setBackRateB(new BigDecimal(returnGoods));
				credit.setBreakRateB(new BigDecimal(cancel));
				credit.setDoneRateB(new BigDecimal(finish));
			}
		}
		Date date = new Date();
		if(null != credit){//更新商家的信誉
			credit.setUpdatedAt(date);
			credit.setUpdatedBy("SYS");
			mapper.updateByPrimaryKeySelective(credit);
		}else{//新增商家的信誉
			Credit credit1 = new Credit();
			credit1.setShopId(ShopId);
			credit1.setBackRateB(new BigDecimal(0));
			credit1.setBreakRateB(new BigDecimal(0));
			credit1.setDoneRateB(new BigDecimal(0));
			credit1.setLastResetTime(date);
			credit1.setCreatedAt(date);
			credit1.setCreatedBy("SYS");
			mapper.insert(credit1);
		}
	}
	


}

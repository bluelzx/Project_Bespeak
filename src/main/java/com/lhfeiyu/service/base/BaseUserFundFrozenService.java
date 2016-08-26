package com.lhfeiyu.service.base;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhfeiyu.dao.domain.UserFundFrozenMapper;
import com.lhfeiyu.po.domain.UserFundFrozen;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户资金冻结表 UserFundFrozen <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseUserFundFrozenService")
public class BaseUserFundFrozenService extends CommonService<UserFundFrozen> {
	@Autowired
	UserFundFrozenMapper userFundFrozenMapper;
	
	public UserFundFrozen selectUserFundFrozenByUserId(Integer userId){
		return userFundFrozenMapper.selectUserFundFrozenByUserId(userId);
	}

	public void updateStatusById(UserFundFrozen userFundFrozen) {
		userFundFrozenMapper.updateStatusById(userFundFrozen);
	}
	
	/**
	 * 根据用户ID等到用户的诚信保证金
	 * @param userId 用户ID
	 * userFundFrozenMapper.selectUFFByUserIdAndTypeCode(userId, "frozenMoney_type_credit");//诚信保证金
	 * @return double creditMoney
	 */
	public double getUserCreditMoney(Integer userId){
		double creditMoney = 0;
		UserFundFrozen uf = userFundFrozenMapper.selectUFFByUserIdAndTypeCode(userId, "frozenMoney_type_credit");//诚信保证金
		if(null != userId && null != uf){
			BigDecimal creditMoneyBD = uf.getFrozenMoney();
			if(null != creditMoneyBD){
				creditMoney = creditMoneyBD.doubleValue();
			}
		}
		return creditMoney;
	}
	
}
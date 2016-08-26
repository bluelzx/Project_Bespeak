package com.lhfeiyu.service.base;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.AccountLogMapper;
import com.lhfeiyu.dao.domain.UserFundMapper;
import com.lhfeiyu.dao.domain.UserMapper;
import com.lhfeiyu.domain.Trade;
import com.lhfeiyu.po.domain.AccountLog;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：资金记录 AccountLog <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseAccountLogService")
public class BaseAccountLogService extends CommonService<AccountLog> {

	@Autowired
	AccountLogMapper accountLogMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserFundMapper userFundMapper;
	
	public JSONObject getAccountLogList(JSONObject json, Map<String, Object> map) {
		List<AccountLog> accountLogList = accountLogMapper.selectListByCondition(map);
		Integer total = accountLogMapper.selectCountByCondition(map);
		return Result.gridData(accountLogList, total, json);
	}
	
	/**
	 * 新增或修改资金记录
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param accountLog 资金记录对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateAccountLog(JSONObject json, AccountLog accountLog, String username){
		/*String content = accountLog.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == accountLog.getId()){//添加
			return addAccountLog(json, accountLog, username);
		}else{//修改
			return updateAccountLog(json, accountLog, username);
		}
	}
	
	/**
	 * 新增资金记录（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param accountLog 资金记录对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addAccountLog(JSONObject json, AccountLog accountLog, String createdBy){
		
		//交易状态(1冻结，2解冻，3实际支出，4实际收入，5冻结资金中支出)
		
		Integer userId = accountLog.getUserId();
		BigDecimal totalMoney = accountLog.getMoney();//交易总金额
		BigDecimal moneyBD = accountLog.getMoney();
		
		if(null == userId){
			return Result.failure(json, LhTip.msg_userId_null, LhTip.code_userId_null);
		}
		UserFund uf = userFundMapper.selectUserFundByUserId(userId);//获取用户资金账户信息
		if(null == uf){
			return Result.failure(json, LhTip.msg_userFund_null, LhTip.code_userFund_null);
		}
		if(null == totalMoney){
			return Result.failure(json, LhTip.msg_tradeMoney_null, LhTip.code_tradeMoney_null);
		}
		if(totalMoney.doubleValue() <= 0 || totalMoney.doubleValue() >= 100000000){
			return Result.failure(json, LhTip.msg_tradeMoney_invalid, LhTip.code_tradeMoney_invalid);
		}
		if(null == moneyBD){
			return Result.failure(json, LhTip.msg_money_null, LhTip.code_money_null);
		}
		
		totalMoney = totalMoney.setScale(2, BigDecimal.ROUND_HALF_UP);//交易总金额保留两位小数
		
		User user = userMapper.selectByPrimaryKey(userId);//从数据库中获取用户信息，用于填充资金记录的用户相关信息
		Date date = new Date();
		accountLog.setId(null);
		accountLog.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_accountLog));
		accountLog.setUsername(user.getUsername());
		accountLog.setEmail(user.getEmail());
		accountLog.setPhone(user.getPhone());
		accountLog.setTel(user.getTel());
		accountLog.setMainStatus(1);
		accountLog.setAvaliableMoneyLog(uf.getAvaliableMoney());
		accountLog.setFrozenMoneyLog(uf.getFrozenMoney());
		accountLog.setCreatedBy(createdBy);
		accountLog.setCreatedAt(date);
		accountLogMapper.insert(accountLog);
		json.put("id", accountLog.getId());
		return Result.success(json);
	}
	
	/**
	 * 新增账户资金变动记录
	 * @return JSONObject json
	 */
	public JSONObject addAccountLog(JSONObject json, Trade trade, String createdBy){
		AccountLog  accountLog = new AccountLog();
		accountLog.setUserId(trade.getUserId());
		accountLog.setMoney(trade.getMoney());
		accountLog.setInOrOut(trade.getInOrOut());
		accountLog.setPayTime(trade.getPayTime());
		accountLog.setTradeUserId(trade.getTradeUserId());
		accountLog.setTradeUsername(trade.getTradeUsername());
		accountLog.setTradeUserPhone(trade.getTradeUserPhone());
		accountLog.setTradeStatusCode(trade.getTradeStatusCode());
		return addAccountLog(json, accountLog, trade.getCreatedBy());
	}
	
	/**
	 * 修改资金记录 - 资金记录不提供整体修改方法，只能修改状态等操作，需要专门的修改方法（从安全方面考虑）
	 */
	public JSONObject updateAccountLog(JSONObject json, AccountLog accountLog , String username){
		//Date date = new Date();
		Integer accountLogId = accountLog.getId();
		if(null == accountLogId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		AccountLog dbAccountLog = accountLogMapper.selectByPrimaryKey(accountLogId);
		if(null == dbAccountLog){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		/*accountLog.setUpdatedBy(username);
		accountLog.setUpdatedAt(date);
		accountLogMapper.updateByPrimaryKeySelective(accountLog);
		return Result.success(json);*/
		return null;
	}
	
}

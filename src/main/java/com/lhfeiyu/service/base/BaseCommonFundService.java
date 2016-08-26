package com.lhfeiyu.service.base;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.UserFundMapper;
import com.lhfeiyu.domain.Trade;
import com.lhfeiyu.domain.TradeAfterCheck;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.po.domain.UserFundFrozen;
import com.lhfeiyu.service.domain.AccountLogService;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：资金操作公共方法（所有资金相关操作尽量使用此类中的公共方法） <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
 * <strong> 编写时间：</strong>2016年3月20日22:22:22<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 所有资金相关操作尽量使用此类中的公共方法  <p>
 */
@Service("baseCommonFundService")
public class BaseCommonFundService extends CommonService<UserFund> {
	
	@Autowired
	UserFundMapper userFundMapper;
	@Autowired
	AccountLogService accountLogService;
	
	/**
	 * 根据userId查询用户资金数据
	 * @param userId
	 * @return UserFund
	 */
	public UserFund selectUserFundByUserId(Integer userId){
		return userFundMapper.selectUserFundByUserId(userId);
	}
	
	/**
	 * @param json
	 * @param trade : userId,money,fundOptLevelCode(fund_opt_level_sys,fund_opt_level_admin),payPass,
	 * @param trc
	 * @param updatedBy
	 * @return
	 */
	private JSONObject preCheck(JSONObject json, Trade trade, TradeAfterCheck trc, String updatedBy){
		Date date = new Date();
		Integer userId = trade.getUserId();
		if(null == userId){
			return Result.failure(json, LhTip.msg_userId_null, LhTip.code_userId_null);//用户ID为空
		}
		UserFund uf = userFundMapper.selectUserFundByUserId(userId);//获取用户资金账户信息
		if(null == uf){
			return Result.failure(json, LhTip.msg_userFund_null, LhTip.code_userFund_null);//用户资金账户为空
		}
		BigDecimal tradeMoney = trade.getMoney();
		if(null == tradeMoney || tradeMoney.doubleValue() <= 0){ 
			return Result.failure(json, LhTip.msg_tradeMoney_null, LhTip.code_tradeMoney_null);//交易金额为空
		}
		String levelCode = trade.getFundOptLevelCode();//资金操作等级
		String fundOptTypeCode = trade.getFundOptTypeCode();//资金操作类型
		if(Check.strEqual(LhConst.fund_opt_level_sys, levelCode) || Check.strEqual(LhConst.fund_opt_level_admin, levelCode)){// 系统操作或后台管理员操作，不用输入密码

		}else{
			String iptPayPass = trade.getPayPass();//用户输入的支付密码
			String payPass = uf.getPayPassword();//用户已经加密的支付密码
			if(Check.isNull(fundOptTypeCode)){ 
				return Result.failure(json, "无法识别的资金操作类型", "fundOptType_null"); 
			}
			if(!fundOptTypeCode.equals(LhConst.fund_opt_type_incomeMoney) && !fundOptTypeCode.equals(LhConst.fund_opt_type_outcomeFrozenMoney)){//收入资金和从冻结资金中支出不用验证密码
				if(Check.isNull(payPass)){ 
					return Result.failure(json, LhTip.msg_pay_password_null, LhTip.code_pay_password_null); 
				}
				if(Check.isNull(iptPayPass)){ 
					return Result.failure(json, LhTip.msg_pay_password_ipt_null, LhTip.code_pay_password_ipt_null); 
				}
				iptPayPass = Md5Util.encrypt(iptPayPass);
				if(!Check.strEqual(payPass, iptPayPass)){ 
					return Result.failure(json, LhTip.msg_pay_password_wrong, LhTip.code_pay_password_wrong); 
				}
			}
		}
		
		tradeMoney = tradeMoney.setScale(2, BigDecimal.ROUND_HALF_UP);//交易金额保留两位小数
		
		if(null == uf.getAvaliableMoney())uf.setAvaliableMoney(new BigDecimal(0));//可用金额为空则设置为零
		if(null == uf.getFrozenMoney())uf.setFrozenMoney(new BigDecimal(0));
		
		double avaliableMoney = uf.getAvaliableMoney().doubleValue();//可用资金
		double frozenMoney = uf.getFrozenMoney().doubleValue();//冻结资金
		double money = tradeMoney.doubleValue();//交易金额
		uf.setAvaliableMoney(null);//先清空资金，下面跟据情况更新
		uf.setFrozenMoney(null);
		
		//TradeAfterCheck trc = new TradeAfterCheck();
		trc.setUf(uf);
		trc.setDate(date);
		trc.setUserId(userId);
		trc.setTradeMoney(tradeMoney);
		trc.setAvaliableMoney(avaliableMoney);
		trc.setFrozenMoney(frozenMoney);
		trc.setMoney(money);
		
		return json;
	}
	
	private JSONObject updateUserFundAndAddLog(JSONObject json, UserFund uf, Trade trade, Date date, String updatedBy){
		if( Result.hasError(json) ){return json;}
		uf.setUpdatedBy(updatedBy);
		uf.setUpdatedAt(date);
		userFundMapper.updateMoneyById(uf);//更新资金
		accountLogService.addAccountLog(json, trade, updatedBy);//新增资金变动记录
		return Result.success(json);
	
	}
	
	/**
	 * 私用方法-用户账户资金变更
	 * @param totalMoney 支付密码
	 * @param totalMoney 资金金额
	 * @param userId 用户ID
	 * @param username 用户名
	 * @param msg 消息字符串
	 * @return JSONObject msg,code
	 */
	/*private JSONObject updateUserFundMoney(JSONObject json, Trade trade, String updatedBy){
		Date date = new Date();
		Integer userId = trade.getUserId();
		if(null == userId){
			return Result.failure(json, LhTip.msg_userId_null, LhTip.code_userId_null);//用户ID为空
		}
		UserFund uf = userFundMapper.selectUserFundByUserId(userId);//获取用户资金账户信息
		if(null == uf){
			return Result.failure(json, LhTip.msg_userFund_null, LhTip.code_userFund_null);//用户资金账户为空
		}
		BigDecimal tradeMoney = trade.getMoney();
		if(null == tradeMoney || tradeMoney.doubleValue() <= 0){ 
			return Result.failure(json, LhTip.msg_tradeMoney_null, LhTip.code_tradeMoney_null);//交易金额为空
		}
		String levelCode = trade.getFundOptLevelCode();//资金操作等级
		String fundOptTypeCode = trade.getFundOptTypeCode();//资金操作类型
		if(Check.strEqual("fund_opt_level_sys", levelCode) || Check.strEqual("fund_opt_level_admin", levelCode)){// 系统操作或后台管理员操作，不用输入密码

		}else{
			String iptPayPass = trade.getPayPass();//用户输入的支付密码
			String payPass = uf.getPayPassword();//用户已经加密的支付密码
			if(null != fundOptTypeCode && 
					!fundOptTypeCode.equals("fund_opt_type_income") && 
					!fundOptTypeCode.equals("fund_opt_type_outcomeFrozen")){//收入资金和从冻结资金中支出不用验证密码
				if(Check.isNotNull(payPass)){ 
					return Result.failure(json, LhTip.msg_pay_password_null, LhTip.code_pay_password_null); 
				}
				if(Check.isNotNull(iptPayPass)){ 
					return Result.failure(json, LhTip.msg_pay_password_ipt_null, LhTip.code_pay_password_ipt_null); 
				}
				iptPayPass = Md5Util.encrypt(iptPayPass);
				if(!Check.strEqual(payPass, iptPayPass)){ 
					return Result.failure(json, LhTip.msg_pay_password_wrong, LhTip.code_pay_password_wrong); 
				}
			}
		}
		tradeMoney = tradeMoney.setScale(2, BigDecimal.ROUND_HALF_UP);//交易金额保留两位小数
		
		if(null == uf.getAvaliableMoney())uf.setAvaliableMoney(new BigDecimal(0));//可用金额为空则设置为零
		if(null == uf.getFrozenMoney())uf.setFrozenMoney(new BigDecimal(0));
		
		double avaliableMoney = uf.getAvaliableMoney().doubleValue();//可用资金
		double frozenMoney = uf.getFrozenMoney().doubleValue();//冻结资金
		double money = tradeMoney.doubleValue();//交易金额
		uf.setAvaliableMoney(null);//先清空资金，下面跟据情况更新
		uf.setFrozenMoney(null);
		//String tip_type = "";//提示类型
		if(Check.strEqual(LhConst.fund_opt_type_freezeMoney, fundOptTypeCode)){//冻结资金
			//tip_type = "冻结资金";
			if(avaliableMoney <= 0 || avaliableMoney < money){
				return Result.failure(json, LhTip.msg_avaliableMoney_lack, LhTip.code_avaliableMoney_lack);
			}
			avaliableMoney = avaliableMoney - money;
			frozenMoney = frozenMoney + money;
			uf.setFrozenMoney(new BigDecimal(frozenMoney));
			uf.setAvaliableMoney(new BigDecimal(avaliableMoney));
			//TODO 增加冻结资金记录：UserFundFrozen
			
		}else if(Check.strEqual(LhConst.fund_opt_type_activateMoney, fundOptTypeCode)){//解冻资金（激活资金）
			//tip_type = "解冻资金";
			if(frozenMoney <= 0 || frozenMoney < money){
				return Result.failure(json, LhTip.msg_frozenMoney_lack, LhTip.code_frozenMoney_lack);
			}
			avaliableMoney = avaliableMoney + money;
			frozenMoney = frozenMoney - money;
			uf.setFrozenMoney(new BigDecimal(frozenMoney));
			uf.setAvaliableMoney(new BigDecimal(avaliableMoney));
			//TODO 更新冻结资金记录：UserFundFrozen
		}else if(Check.strEqual(LhConst.fund_opt_type_incomeMoney, fundOptTypeCode)){//增加可用资金
			//tip_type = "增加可用资金";
			avaliableMoney = avaliableMoney + money;
			uf.setAvaliableMoney(new BigDecimal(avaliableMoney));
		}else if(Check.strEqual(LhConst.fund_opt_type_outcomeMoney, fundOptTypeCode)){//支出可用资金
			//tip_type = "支出可用资金";
			if(avaliableMoney <= 0 || avaliableMoney < money){
				return Result.failure(json, LhTip.msg_avaliableMoney_lack, LhTip.code_avaliableMoney_lack);
			}
			avaliableMoney = avaliableMoney - money;
			uf.setAvaliableMoney(new BigDecimal(avaliableMoney));
		}else if(Check.strEqual(LhConst.fund_opt_type_outcomeFrozenMoney, fundOptTypeCode)){//支出冻结资金
			//tip_type = "支出冻结资金";
			if(frozenMoney <= 0 || frozenMoney < money){
				return Result.failure(json, LhTip.msg_frozenMoney_lack, LhTip.code_frozenMoney_lack);
			}
			frozenMoney = frozenMoney - money;
			uf.setFrozenMoney(new BigDecimal(frozenMoney));
			//TODO 更新冻结资金记录：UserFundFrozen
		}else{
			return Result.failure(json, LhTip.msg_operation_undefined, LhTip.code_operation_undefined); 
		}
		uf.setUpdatedBy(updatedBy);
		uf.setUpdatedAt(date);
		userFundMapper.updateMoneyById(uf);//更新资金
		accountLogService.addAccountLog(json, trade, updatedBy);//新增资金变动记录
		if(Result.hasError(json))return json;//如果出现错误，直接返回错误信息
		return Result.success(json);
	}*/
	
	private JSONObject addUserFundFrozen(JSONObject json, UserFund uf, Trade trade){
		Date date = new Date();
		UserFundFrozen uff = new UserFundFrozen();
		uff.setAvaliableMoneyLog(uf.getAvaliableMoney());
		uff.setFrozenMoneyLog(uf.getFrozenMoney());
		BigDecimal tradeMoney = trade.getMoney().setScale(2, BigDecimal.ROUND_HALF_UP);//交易金额保留两位小数
		uff.setFrozenMoney(tradeMoney);
		uff.setUserId(trade.getUserId());
		uff.setPhone(trade.getPhone());
		uff.setUsername(trade.getUsername());
		uff.setFreezeTime(date);
		uff.setFrozenMoneyStatusCode(LhConst.frozenMoney_status_frozen);
		uff.setFrozenTypeCode(LhConst.frozenMoney_type_credit);
		/*uff.setTheOtherId(theOtherId);
		uff.setTheOtherType(theOtherType);
		uff.setTheOtherName(theOtherName);*/
		
		
		
		return null;
			
	}
	private JSONObject updateUserFundFrozen(JSONObject json, UserFundFrozen uff){
		return null;
	}
	
	/*private JSONObject updateMoneyNotice(Integer userId, String tipType, String money, String tradeWhoName, String linkUrl, String createdBy){
		Notice notice = new Notice();//账户资金变动通知
		notice.setNoticeTypeCode("");//dict-13:账户资金变动通知
		notice.setReceiverId(userId);
		notice.setReadStatus(1);
		String serial = CommonGenerator.getSerialByDate("n");
		notice.setSerial(serial);
		String content = "【账户资金变动-"+tipType+"】金额："+money+"元，对方名称："+tradeWhoName;
		notice.setLinkUrl(linkUrl);
		notice.setTitle(content);
		notice.setContent(content);
		notice.setCreatedAt(new Date());
		if(Check.isNotNull(createdBy))createdBy = "-SYS-";
		notice.setCreatedBy(createdBy);
		noticeMapper.insertSelective(notice);
		//User user = userMapper.selectByPrimaryKey(userId);
		//String openId = user.getThirdName();
		//微信消息通知
		if(Check.isNotNull(openId)){
			Date d = new Date();
			String dateStr = DateFormat.daySdf.format(d);
			String firstValue = "账户资金变动通知";
			String attr1Value = tip_type;
			String attr2Value = money+"元";
			JSONObject messageJson = Message.buildMsg(openId, LhConstField.wx_moban_2, LhConstField.page_user_center, 
							 "first", firstValue, "tradeType", attr1Value, "curAmount", attr2Value, 
							 "tradeDateTime", dateStr, null, null, "remark", LhConstField.wx_message_remark);
			Message.sendMessage(LhConstField.wx_moban_2, messageJson);//JSONObject resultJson = Message.sendMessage(LhConstField.wx_moban_10, messageJson);
		}
	}*/
	
	/**
	 * 冻结用户账户资金（同时新增账户资金变动记录）
	 * @param json 
	 * @param trade 资金业务领域对象(userId,money,fundOptLevelCode(fund_opt_level_sys,fund_opt_level_admin),
	 * 							payPass,inOrOut,payTime,tradeUserId,tradeUsername,tradeUserPhone,tradeStatusCode, greatedBy)
	 * @param updatedBy 操作人
	 * @return JSONObject msg,code
	 */
	public JSONObject freezeMoney(JSONObject json, Trade trade, Notice notice, String updatedBy){
		trade.setTradeTypeCode(LhConst.fund_opt_type_freezeMoney);
		trade.setFundOptTypeCode(LhConst.fund_opt_type_freezeMoney);
		TradeAfterCheck trc = new TradeAfterCheck();
		preCheck(json, trade, trc, updatedBy);
		if(Result.hasError(json))return json;//如果出现错误，直接返回错误信息
		
		Date date = trc.getDate();
		UserFund uf = trc.getUf();//获取用户资金账户信息
		
		double avaliableMoney = trc.getAvaliableMoney();//可用资金
		double frozenMoney = trc.getFrozenMoney();//冻结资金
		double money = trc.getMoney();//交易金额
		uf.setAvaliableMoney(null);//先清空资金，下面跟据情况更新
		uf.setFrozenMoney(null);
		//冻结资金
		if(avaliableMoney <= 0 || avaliableMoney < money){
			return Result.failure(json, LhTip.msg_avaliableMoney_lack, LhTip.code_avaliableMoney_lack);
		}
		avaliableMoney = avaliableMoney - money;
		frozenMoney = frozenMoney + money;
		uf.setFrozenMoney(new BigDecimal(frozenMoney));
		uf.setAvaliableMoney(new BigDecimal(avaliableMoney));
		//TODO 增加冻结资金记录：UserFundFrozen

		return updateUserFundAndAddLog(json, uf, trade, date, updatedBy);//更新资金,新增资金变动记录
		
	}
	
	/**
	 * 激活用户账户资金-解冻（同时新增账户资金变动记录）
	 * @param json 
	 * @param trade 资金业务领域对象
	 * @param updatedBy 操作人
	 * @return JSONObject msg,code
	 */
	public JSONObject activateMoney(JSONObject json, Trade trade, Notice notice, String updatedBy){
		trade.setTradeTypeCode(LhConst.fund_opt_type_activateMoney);
		trade.setFundOptTypeCode(LhConst.fund_opt_type_activateMoney);
		TradeAfterCheck trc = new TradeAfterCheck();
		preCheck(json, trade, trc, updatedBy);
		if(Result.hasError(json))return json;//如果出现错误，直接返回错误信息
		
		Date date = trc.getDate();
		Integer userId = trc.getUserId();
		BigDecimal tradeMoney = trc.getTradeMoney();
		UserFund uf = trc.getUf();//获取用户资金账户信息
		
		double avaliableMoney = trc.getAvaliableMoney();//可用资金
		double frozenMoney = trc.getFrozenMoney();//冻结资金
		double money = trc.getMoney();//交易金额
		uf.setAvaliableMoney(null);//先清空资金，下面跟据情况更新
		uf.setFrozenMoney(null);
		
		//解冻资金（激活资金）
		if(frozenMoney <= 0 || frozenMoney < money){
			return Result.failure(json, LhTip.msg_frozenMoney_lack, LhTip.code_frozenMoney_lack);
		}
		avaliableMoney = avaliableMoney + money;
		frozenMoney = frozenMoney - money;
		uf.setFrozenMoney(new BigDecimal(frozenMoney));
		uf.setAvaliableMoney(new BigDecimal(avaliableMoney));
		//TODO 更新冻结资金记录：UserFundFrozen

		return updateUserFundAndAddLog(json, uf, trade, date, updatedBy);//更新资金,新增资金变动记录
	}
	
	/**
	 * 增加用户账户资金-收入（同时新增账户资金变动记录）
	 * @param json 
	 * @param trade 资金业务领域对象
	 * @param updatedBy 操作人
	 * @return JSONObject msg,code
	 */
	public JSONObject incomeMoney(JSONObject json, Trade trade, Notice notice, String updatedBy){
		trade.setTradeTypeCode(LhConst.fund_opt_type_incomeMoney);
		trade.setFundOptTypeCode(LhConst.fund_opt_type_incomeMoney);
		TradeAfterCheck trc = new TradeAfterCheck();
		preCheck(json, trade, trc, updatedBy);
		if(Result.hasError(json))return json;//如果出现错误，直接返回错误信息
		
		Date date = trc.getDate();
		Integer userId = trc.getUserId();
		BigDecimal tradeMoney = trc.getTradeMoney();
		UserFund uf = trc.getUf();//获取用户资金账户信息
		
		double avaliableMoney = trc.getAvaliableMoney();//可用资金
		double frozenMoney = trc.getFrozenMoney();//冻结资金
		double money = trc.getMoney();//交易金额
		uf.setAvaliableMoney(null);//先清空资金，下面跟据情况更新
		uf.setFrozenMoney(null);
		
		//tip_type = "增加可用资金";
		avaliableMoney = avaliableMoney + money;
		uf.setAvaliableMoney(new BigDecimal(avaliableMoney));

		return updateUserFundAndAddLog(json, uf, trade, date, updatedBy);//更新资金,新增资金变动记录
	}
	
	/**
	 * 减少用户账户资金-支出（同时新增账户资金变动记录）
	 * @param json 
	 * @param trade 资金业务领域对象
	 * @param updatedBy 操作人
	 * @return JSONObject msg,code
	 */
	public JSONObject outcomeMoney(JSONObject json, Trade trade, Notice notice, String updatedBy){
		trade.setTradeTypeCode(LhConst.fund_opt_type_outcomeMoney);
		trade.setFundOptTypeCode(LhConst.fund_opt_type_outcomeMoney);
		TradeAfterCheck trc = new TradeAfterCheck();
		preCheck(json, trade, trc, updatedBy);
		if(Result.hasError(json))return json;//如果出现错误，直接返回错误信息
		
		Date date = trc.getDate();
		Integer userId = trc.getUserId();
		BigDecimal tradeMoney = trc.getTradeMoney();
		UserFund uf = trc.getUf();//获取用户资金账户信息
		
		double avaliableMoney = trc.getAvaliableMoney();//可用资金
		double frozenMoney = trc.getFrozenMoney();//冻结资金
		double money = trc.getMoney();//交易金额
		uf.setAvaliableMoney(null);//先清空资金，下面跟据情况更新
		uf.setFrozenMoney(null);
		
		//tip_type = "支出可用资金";
		if(avaliableMoney <= 0 || avaliableMoney < money){
			return Result.failure(json, LhTip.msg_avaliableMoney_lack, LhTip.code_avaliableMoney_lack);
		}
		avaliableMoney = avaliableMoney - money;
		uf.setAvaliableMoney(new BigDecimal(avaliableMoney));

		return updateUserFundAndAddLog(json, uf, trade, date, updatedBy);//更新资金,新增资金变动记录
	}
	
	/**
	 * 从用户账户冻解资金中扣除（同时新增账户资金变动记录）
	 * @param json 
	 * @param trade 资金业务领域对象
	 * @param updatedBy 操作人
	 * @return JSONObject msg,code
	 */
	public JSONObject outcomeFrozenMoney(JSONObject json, Trade trade, Notice notice, String updatedBy){
		trade.setTradeTypeCode(LhConst.fund_opt_type_outcomeFrozenMoney);
		trade.setFundOptTypeCode(LhConst.fund_opt_type_outcomeFrozenMoney);
		TradeAfterCheck trc = new TradeAfterCheck();
		preCheck(json, trade, trc, updatedBy);
		if(Result.hasError(json))return json;//如果出现错误，直接返回错误信息
		
		Date date = trc.getDate();
		Integer userId = trc.getUserId();
		BigDecimal tradeMoney = trc.getTradeMoney();
		UserFund uf = trc.getUf();//获取用户资金账户信息
		
		double avaliableMoney = trc.getAvaliableMoney();//可用资金
		double frozenMoney = trc.getFrozenMoney();//冻结资金
		double money = trc.getMoney();//交易金额
		uf.setAvaliableMoney(null);//先清空资金，下面跟据情况更新
		uf.setFrozenMoney(null);
		
		//tip_type = "支出冻结资金";
		if(frozenMoney <= 0 || frozenMoney < money){
			return Result.failure(json, LhTip.msg_frozenMoney_lack, LhTip.code_frozenMoney_lack);
		}
		frozenMoney = frozenMoney - money;
		uf.setFrozenMoney(new BigDecimal(frozenMoney));
		//TODO 更新冻结资金记录：UserFundFrozen

		return updateUserFundAndAddLog(json, uf, trade, date, updatedBy);//更新资金,新增资金变动记录
	}
	
	/**
	 * 从用户账户可用资金中转移资金到指定用户可用资金中（同时新增账户资金变动记录）
	 * @param json 
	 * @param trade 资金业务领域对象
	 * @param updatedBy 操作人
	 * @return JSONObject msg,code
	 */
	public JSONObject outcomeMoneyToUserAvaliable(JSONObject json, Trade trade, Notice notice, String updatedBy){
		outcomeMoney(json, trade, notice, updatedBy);
		if( Result.hasError(json) ){return json;}
		trade.switchUser();//切换交易方和被交易方
		incomeMoney(json, trade, notice, updatedBy);
		return json;
	}
	
	/**
	 * 从用户冻结资金中转移资金到用户可用资金中（同时新增账户资金变动记录）
	 * @param json 
	 * @param trade 资金业务领域对象
	 * @param updatedBy 操作人
	 * @return JSONObject msg,code
	 */
	public JSONObject outcomeFrozenMoneyToUserAvaliable(JSONObject json, Trade trade, Notice notice, String updatedBy){
		outcomeFrozenMoney(json, trade, notice, updatedBy);
		if( Result.hasError(json) ){return json;}
		trade.switchUser();//切换交易方和被交易方
		incomeMoney(json, trade, notice, updatedBy);
		return json;
	}
	
}
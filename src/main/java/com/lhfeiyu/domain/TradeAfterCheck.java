package com.lhfeiyu.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.lhfeiyu.po.domain.UserFund;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务模型层：交易相关字段 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
public class TradeAfterCheck {
	
	private Integer id;
	private UserFund uf;
	private BigDecimal tradeMoney;//交易金额
	private double avaliableMoney;//可用资金
	private double frozenMoney;//冻结资金
	private double money;//交易金额
	private Integer userId;
	private Date date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserFund getUf() {
		return uf;
	}
	public void setUf(UserFund uf) {
		this.uf = uf;
	}
	public double getAvaliableMoney() {
		return avaliableMoney;
	}
	public void setAvaliableMoney(double avaliableMoney) {
		this.avaliableMoney = avaliableMoney;
	}
	public double getFrozenMoney() {
		return frozenMoney;
	}
	public void setFrozenMoney(double frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}	
	
}
	
package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Credit <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 业务：信誉表 <p>
 */
public class Credit extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	

	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 店铺ID  */
	private Integer shopId;
	
	/** 好评率-作为商家  */
	private BigDecimal goodRateB;
	
	/** 退货率-作为商家  */
	private BigDecimal backRateB;
	
	/** 成交率-作为商家  */
	private BigDecimal doneRateB;
	
	/** 违约率-作为商家  */
	private BigDecimal breakRateB;
	
	/** 好评率-作为客户  */
	private BigDecimal goodRateC;
	
	/** 退货率-作为客户  */
	private BigDecimal backRateC;
	
	/** 成交率-作为客户  */
	private BigDecimal doneRateC;
	
	/** 违约率-作为客户  */
	private BigDecimal breakRateC;
	
	/**   */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastResetTime;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public BigDecimal getGoodRateB() {
		return goodRateB;
	}
	public void setGoodRateB(BigDecimal goodRateB) {
		this.goodRateB = goodRateB;
	}
	public BigDecimal getBackRateB() {
		return backRateB;
	}
	public void setBackRateB(BigDecimal backRateB) {
		this.backRateB = backRateB;
	}
	public BigDecimal getDoneRateB() {
		return doneRateB;
	}
	public void setDoneRateB(BigDecimal doneRateB) {
		this.doneRateB = doneRateB;
	}
	public BigDecimal getBreakRateB() {
		return breakRateB;
	}
	public void setBreakRateB(BigDecimal breakRateB) {
		this.breakRateB = breakRateB;
	}
	public BigDecimal getGoodRateC() {
		return goodRateC;
	}
	public void setGoodRateC(BigDecimal goodRateC) {
		this.goodRateC = goodRateC;
	}
	public BigDecimal getBackRateC() {
		return backRateC;
	}
	public void setBackRateC(BigDecimal backRateC) {
		this.backRateC = backRateC;
	}
	public BigDecimal getDoneRateC() {
		return doneRateC;
	}
	public void setDoneRateC(BigDecimal doneRateC) {
		this.doneRateC = doneRateC;
	}
	public BigDecimal getBreakRateC() {
		return breakRateC;
	}
	public void setBreakRateC(BigDecimal breakRateC) {
		this.breakRateC = breakRateC;
	}
	public Date getLastResetTime() {
		return lastResetTime;
	}
	public void setLastResetTime(Date lastResetTime) {
		this.lastResetTime = lastResetTime;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}
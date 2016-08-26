/**
* @mbggenerated
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层PO类 <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 成都蓝海飞鱼科技有限公司开发人员 <p>
* <strong> 编写时间：</strong> Mon Jul 20 15:55:12 CST 2015 <p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
package com.lhfeiyu.dao.domain;

import org.apache.ibatis.annotations.Param;

import com.lhfeiyu.dao.base.CommonMapper;
import com.lhfeiyu.po.domain.UserFundFrozen;

public interface UserFundFrozenMapper extends CommonMapper<UserFundFrozen> {

	UserFundFrozen selectUserFundFrozenByUserId(Integer userId);
	
	UserFundFrozen selectUFFByUserIdAndTypeCode(@Param(value="userId")Integer userId, @Param(value="typeCode") String typeCode);

	int updateStatusById(UserFundFrozen uf);
	
}
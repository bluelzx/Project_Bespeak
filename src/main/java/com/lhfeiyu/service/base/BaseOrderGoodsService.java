package com.lhfeiyu.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhfeiyu.dao.domain.OrderGoodsMapper;
import com.lhfeiyu.po.domain.OrderGoods;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：订单商品 OrderGoods <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseOrderGoodsService")
public class BaseOrderGoodsService extends CommonService<OrderGoods>{
	@Autowired
	OrderGoodsMapper mapper;

	public OrderGoods selectByPrimaryKeyAndOrder(Integer goodsId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKeyAndOrder(goodsId);
	}
}

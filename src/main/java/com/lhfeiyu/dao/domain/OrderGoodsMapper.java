package com.lhfeiyu.dao.domain;

import com.lhfeiyu.dao.base.CommonMapper;
import com.lhfeiyu.po.domain.OrderGoods;

public interface OrderGoodsMapper extends CommonMapper<OrderGoods>{

	OrderGoods selectByPrimaryKeyAndOrder(Integer goodsId);

}

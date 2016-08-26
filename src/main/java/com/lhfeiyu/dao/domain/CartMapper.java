package com.lhfeiyu.dao.domain;

import java.util.List;
import java.util.Map;

import com.lhfeiyu.dao.base.CommonMapper;
import com.lhfeiyu.po.domain.Cart;

public interface CartMapper extends CommonMapper<Cart>{

	void addBatchCart(List<Cart> cartList);

	void deleteByUserId(Map<String, Object> map);

	void updateByCartMainStatus(Integer id);

	
}

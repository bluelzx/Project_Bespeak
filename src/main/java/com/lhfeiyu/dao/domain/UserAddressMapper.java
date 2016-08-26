package com.lhfeiyu.dao.domain;

import com.lhfeiyu.dao.base.CommonMapper;
import com.lhfeiyu.po.domain.UserAddress;

public interface UserAddressMapper extends CommonMapper<UserAddress>{

	 Integer cleanDefultAddress(Integer userId);

}

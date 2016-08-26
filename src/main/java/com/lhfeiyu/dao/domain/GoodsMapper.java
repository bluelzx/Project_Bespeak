package com.lhfeiyu.dao.domain;

import java.util.Map;

import com.lhfeiyu.dao.base.CommonMapper;
import com.lhfeiyu.po.domain.Goods;

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层Mapper：Goods <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年3月20日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：商品表 <p>
 */

public interface GoodsMapper extends CommonMapper<Goods>{
	
	int updateStatusByIds(Map<String,Object> map);

}
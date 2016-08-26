package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.dao.domain.ExpressMapper;
import com.lhfeiyu.dao.domain.OrderInfoMapper;
import com.lhfeiyu.po.domain.Express;
import com.lhfeiyu.po.domain.OrderInfo;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.thirdparty.kuaidi.business.HttpRequest;
import com.lhfeiyu.thirdparty.kuaidi.pojo.NoticeRequest;
import com.lhfeiyu.thirdparty.kuaidi.pojo.Result;
import com.lhfeiyu.thirdparty.kuaidi.pojo.TaskRequest;
import com.lhfeiyu.thirdparty.kuaidi.pojo.TaskResponse;
import com.lhfeiyu.tools.Check;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：快递公司 Express<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseExpressService")
public class BaseExpressService extends CommonService<Express> {
	@Autowired
	ExpressMapper mappper;
	@Autowired
	OrderInfoMapper orderInfoMapper;

	public TaskResponse postOrder(User user, int orderId, String expressCode, String from, String to, String number,
			String kuaidi_key, String callbackurl, String kuaidi_pollurl) throws Exception {
		TaskRequest req = new TaskRequest();
		req.setCompany(expressCode);
		if(Check.isNotNull(from))req.setFrom(from);
		if(Check.isNotNull(to))req.setFrom(to);
		req.setNumber(number);
		req.getParameters().put("callbackurl", callbackurl);
		req.setKey(kuaidi_key);

		HashMap<String, String> p = new HashMap<String, String>();
		p.put("schema", "json");
		p.put("param", JSONObject.toJSONString(req));
		String ret = HttpRequest.postData(kuaidi_pollurl, p, "UTF-8");
		System.out.println("ExpressService-postOrder: "+ret);
		TaskResponse resp = JSONObject.parseObject(ret, TaskResponse.class);
		
		if (resp.getResult() != true) {
			return resp;
		}
		
		OrderInfo oi = new OrderInfo();//更新订单订阅状态
		oi.setId(orderId);
		oi.setPollStatus(resp.getReturnCode());
		oi.setUpdatedAt(new Date());
		oi.setUpdatedBy(user.getUsername());
		orderInfoMapper.updateByPrimaryKeySelective(oi);
		
		//TODO 自动任务，定期重新订阅，订阅失败的订单
		//pollStatus : 
		//快递100接口的推送订阅状态（200提交成功，701:拒绝订阅的快递公司，700: 订阅方的订阅数据存在错误（如不支持的快递公司、单号为空、单号超长等），
		//600: 您不是合法的订阅者（即授权Key出错），500: 服务器错误，501:重复订阅）
		
		//setExpressStatus 监控状态:polling:监控中，shutdown:结束，abort:中止，updateall：重新推送。
		//其中当快递单为已签收时status=shutdown，当message为“3天查询无记录”或“60天无变化时”status= abort
		
		/*if (resp.getResult() == true) {
			System.out.println("订阅成功");
		} else {
			System.out.println("订阅失败");
		}*/
		return resp;
	}
	
	public boolean kuaidiCallback(String expressOrder, NoticeRequest nReq){
		Result result = nReq.getLastResult();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("expressOrder", expressOrder);
		List<OrderInfo> orderInfoList = orderInfoMapper.selectListByCondition(map);
		if(!Check.isNotNull(orderInfoList))return false;
		for(OrderInfo oi : orderInfoList){
			OrderInfo newOi = new OrderInfo();
			newOi.setId(oi.getId());
			newOi.setExpressJson(result.toString());
			newOi.setExpressStatus(nReq.getStatus());
			newOi.setExpressState(result.getState());//快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单
			orderInfoMapper.updateByPrimaryKeySelective(newOi);//更新订单的物流状态
		}
		return true;
	}

}

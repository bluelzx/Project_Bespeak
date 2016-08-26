package com.lhfeiyu.action.front.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Express;
import com.lhfeiyu.po.domain.ProvinceCityArea;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserAddress;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.ExpressService;
import com.lhfeiyu.service.domain.ProvinceCityAreaService;
import com.lhfeiyu.service.domain.UserAddressService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.thirdparty.kuaidi.pojo.NoticeRequest;
import com.lhfeiyu.thirdparty.kuaidi.pojo.NoticeResponse;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Result;
@Controller
public class ExpressAction {
	@Autowired
	private ExpressService  expressService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private AuthCheckService authCheckService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProvinceCityAreaService provinceCityAreaService;
	@Autowired
	//private OrderInfoService orderInfoService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**收货地址*/
	@RequestMapping(value="/addReceiveAddress")
	public ModelAndView addReceiveAddress(ModelMap modelMap,HttpSession session,
			@RequestParam(required = false,value="id") Integer id
			,@RequestParam(required=false) String r,HttpServletRequest request) {
		String path = LhPage.addReceiveAddress;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				 String jumpUrl = "/addReceiveAddress?id="+id;
				if(null != r)jumpUrl += "&r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			if(null != sessionUser.getId() && !"".equals(sessionUser.getId())){
				User user = userService.selectByPrimaryKey(sessionUser.getId());
				modelMap.put("user", user);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("allProvince", 1);
				List<ProvinceCityArea> provinceList = provinceCityAreaService.selectListByCondition(map);
				modelMap.put("provinceList", provinceList);
			}
			if(null != id && !"".equals(id)){
				UserAddress userAddress = userAddressService.selectByPrimaryKey(id);
				modelMap.put("userAddress", userAddress);
				modelMap.put("id", id);
			}
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载收货地址页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**选择发货地址**/
	@RequestMapping(value="/settleAccounts/{orderGoodsId}")
	public ModelAndView settleAccounts(ModelMap modelMap,HttpSession session,HttpServletRequest request,
			@PathVariable Integer orderGoodsId ,@RequestParam(required=false) String r) {
		String path = LhPage.settleAccounts;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				String jumpUrl =  "/settleAccounts/"+orderGoodsId;
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			modelMap.put("userId", user.getId());
			modelMap.put("orderGoodsId", orderGoodsId);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载账户信息页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	/** 发货-添加快递单号  **/
	@RequestMapping(value="/courierNum/{orderId}/{orderGoodsId}")
	public ModelAndView courierNum(ModelMap modelMap,HttpSession session,
			@PathVariable Integer orderId, @PathVariable Integer orderGoodsId
			,@RequestParam(required=false) String r,HttpServletRequest request) {
		String path = LhPage.courierNum;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				String jumpUrl =  "/courierNum/"+orderId+"/"+orderGoodsId;
				if(null != r)jumpUrl += "?r="+r;
				return Result.userSessionInvalid(modelMap, jumpUrl);
			}
			//OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
			//TODO 修改
			modelMap.put("orderId", orderId);
			modelMap.put("orderGoodsId", orderGoodsId);
			//modelMap.put("orderInfo", orderInfo);
			
			//TODO 加载快递公司,省市区
			List<Express> expressList = expressService.selectListByCondition(null);
			modelMap.put("expressList", expressList);
			
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载添加快递单号页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/** 获取快递公司数组 **/
	@ResponseBody
	@RequestMapping(value="/getExpressArray")
	public JSONArray getExpressArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			List<Express> expressList = expressService.selectListByCondition(null);
			for(Express e : expressList){
				JSONObject json = new JSONObject();
				json.put("id", e.getId());
				json.put("name", e.getBriefName());
				json.put("code", e.getCode());//快递公司编码
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_获取快递公司数组出现异常_", array);
		}
		return array;
	}
	
	/** 快递100推送回调接口 **/
	@ResponseBody
	@RequestMapping(value="/kuaidi/callback", method=RequestMethod.POST)
	public JSONObject kuaidiCallback(HttpServletRequest request, HttpServletResponse response
			,@RequestParam String param) {
		NoticeResponse resp = new NoticeResponse();
		resp.setResult(false);
		resp.setReturnCode("500");
		resp.setMessage("保存失败");
		try {
			NoticeRequest nReq = JSONObject.parseObject(param, NoticeRequest.class);
			System.out.println("/kuaidi/callback-param: "+param);
			com.lhfeiyu.thirdparty.kuaidi.pojo.Result result = nReq.getLastResult();
			// 处理快递结果
			String expressOrder = result.getNu();
			if(!Check.isNotNull(expressOrder)){
				response.getWriter().print(JSONObject.toJSON(resp));return null;
			}
			boolean flag = expressService.kuaidiCallback(expressOrder, nReq);
			if(!flag){
				response.getWriter().print(JSONObject.toJSON(resp));return null;
			}
			resp.setResult(true);
			resp.setReturnCode("200");
			resp.setMessage("提交成功");
			response.getWriter().print(JSONObject.toJSON(resp)); //这里必须返回，否则认为失败，过30分钟又会重复推送。
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("LH_ERROR_快递100推送数据接口出现异常_"+e.getMessage());
			resp.setMessage("保存失败" + e.getMessage());
			try {
				response.getWriter().print(JSONObject.toJSON(resp));//保存失败，服务端等30分钟会重复推送。
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error("LH_ERROR_快递100推送数据接口返回失败状态出现异常_"+e.getMessage());
			}
		}
		return null;
	}

	
}

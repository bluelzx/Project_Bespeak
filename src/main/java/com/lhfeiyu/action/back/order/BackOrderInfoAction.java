package com.lhfeiyu.action.back.order;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Apply;
import com.lhfeiyu.po.domain.Course;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.OrderInfo;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.service.domain.NoticeService;
import com.lhfeiyu.service.domain.OrderInfoService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：订单 OrderInfo <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.orderinfo.BackOrderInfoAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackOrderInfoAction {
	
	@Autowired
	private OrderInfoService orderinfoService;
	//字典
	@Autowired
	private DictService dictService;
	//通知消息peopleService
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;
	
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台订单页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/orderinfo")
	public ModelAndView orderinfo(ModelMap modelMap,@RequestParam(required = false) String ordertype){
		String path = LhPage.back_orderinfo;
		JSONObject json = new JSONObject();
		try{
			if(null!=ordertype&&""!=ordertype){
			path = LhPage.back_refundorderinfo;
			json.put("orderStatusCode", ordertype+"_status_apply_return");
			modelMap.put("paramJson", json);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/orderinfo", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	
	/**
	 * 加载订单列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderInfoList",method=RequestMethod.POST)
	public JSONObject getOrderInfoList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
//			System.out.println("查询："+map);
			orderinfoService.getOrderInfoList(json, map);
//			System.out.println("json："+json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getOrderInfoList", json);
		}
//		System.out.println(json);
		return json;
	}
	
	//审核退款申请
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateAuditRefund", method = RequestMethod.POST)
	public JSONObject addOrUpdateAuditRefund(@ModelAttribute OrderInfo orderinfo,HttpServletRequest request){
		JSONObject json = new JSONObject();
//		String path = LhPage.back_refundorderinfo;
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			Notice notice=new Notice();
			notice.setSendTime(d);
			notice.setSenderId(admin.getId());
			notice.setSenderName(username);
			notice.setReceiverId(orderinfo.getUserId());
			notice.setTitle("退款申请通知");
			notice.setContent(orderinfo.getHowOos());
			noticeService.insert(notice);
			int AttrInt= orderinfo.getAttrInt();
			//为2为禁用会不查询
			//所以为1
			if(AttrInt==1){
			orderinfo.setOrderStatusCode(LhConst.order_status_return);
			//更新退款状态
			json.put("howOos", orderinfo.getHowOos());
			json.put("id", orderinfo.getId());
			json.put("orderStatusCode", orderinfo.getOrderStatusCode());
			orderinfoService.addUpdateOrderInfo(json,orderinfo,username);
			
			User user2= userService.selectByPrimaryKey(orderinfo.getUserId());
			//退给用户金额
			User user = new User();
			int usermo=0;
			try {
			if(null!=user2.getAttrInt()){
					usermo=user2.getAttrInt();
					}
//			System.out.println("orderinfo.getTotalPrice():"+orderinfo.getTotalPrice());
			int orderinfomo=orderinfo.getTotalPrice().intValue();
			user.setAttrInt((usermo+orderinfomo));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
//			orderinfo.getTotalPrice()
//			int orderinfomo=orderinfo.getTotalPrice().intValue();
////			user.setAttrInt((usermo+orderinfomo));
//			user.setAttrInt(orderinfomo);
//			course.setUpdatedBy(username);
//			course.setUpdatedAt(new Date());
//			user.setIsEnterpriseAuth(-1);
//			orderinfoService.insertSelective(course);
			}else if(AttrInt==2){
				//更新退款状态
				orderinfo.setOrderStatusCode(LhConst.order_status_noreturn);//不同意订单退款申请，设置订单结束
				json.put("howOos", orderinfo.getHowOos());
				json.put("id", orderinfo.getId());
				json.put("orderStatusCode", orderinfo.getOrderStatusCode());
				
				orderinfoService.addUpdateOrderInfo(json,orderinfo,username);
			}
//			orderinfoService.updateByPrimaryKeySelective(orderinfo);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdateAuditRefund", json);
		}
//		return new ModelAndView(path, json);
		return json;
	}
	/**
	 * 新增或修改订单（新增和修改方法对应Serivce中的不同方法）
	 * @param orderinfo ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateOrderInfo", method = RequestMethod.POST)
	public JSONObject addUpdateOrderInfo(@ModelAttribute OrderInfo orderinfo,HttpServletRequest request){
		JSONObject json = new JSONObject();
//		System.out.println("orderInfo:"+orderinfo);
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			orderinfoService.addUpdateOrderInfo(json, orderinfo, username);
//		JSONObject json = new JSONObject();
//		try {
//			Date d = new Date();
//			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的orderinfo，存在即返回
//			if(null != orderinfo.getId()){//更新
//				orderinfo.setUpdatedAt(d); 
//				orderinfo.setUpdatedBy(admin.getUsername());
//				orderinfoService.updateByPrimaryKeySelective(orderinfo);
//			}else{//新增
////				String picPath = orderinfo.getPicPath();//如果没有上传拍卖机构图片,则使用订单的头像
////				if(null != picPath && !"".equals(picPath)){
////					orderinfo.setPicPath(picPath);
////				}else{
//////					int orderinfoId = orderinfo.getId();
//////					 orderinfo = orderinfoService.selectByPrimaryKey(orderinfoId);
//////					if(null != orderinfo){
//////						auctionInst.setPicPaths(orderinfo.getAvatar());
//////					}
////				}
//				orderinfo.setCreatedAt(d); 
//				orderinfo.setOrderSn(CommonGenerator.getSerialByDate("or"));
//				orderinfo.setCreatedBy(admin.getUsername());
//				orderinfoService.insert(orderinfo);
//			}
//			json.put("status", "success");
//			json.put("msg", "操作成功");
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateOrderInfo", json);
		}
		return json;
	}

	/**
	 * 逻辑删除订单数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateOrderInfoDelete", method=RequestMethod.POST)
	public JSONObject updateOrderInfoDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的orderinfo，存在即返回
			orderinfoService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateOrderInfoDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除订单
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteOrderInfoThorough",method=RequestMethod.POST)
	public JSONObject deleteOrderInfoThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的orderinfo，存在即返回
			orderinfoService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteOrderInfoThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复订单（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateOrderInfoRecover", method=RequestMethod.POST)
	public JSONObject updateOrderInfoRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的orderinfo，存在即返回
			orderinfoService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateOrderInfoRecover", json);
		}
		return json;
	}
	
//	//通过指定id查询订单名
//	@ResponseBody
//	@RequestMapping(value = "/getorderinfonameArray", method=RequestMethod.POST)
//	public JSONArray getorderinfonameArray(HttpServletRequest request) {
//		JSONArray array = new JSONArray();
//		try {
//			Map<String,Object> map = new HashMap<String,Object>();
//			List<OrderInfo> orderinfonameList = orderinfoService.selectListByCondition(map);
//			for(OrderInfo h:orderinfonameList){
//				JSONObject json = new JSONObject();
//				json.put("id",h.getId());
//				json.put("name",h.getUsername());
//				array.add(json);
//			}
//		} catch (Exception e) {
//			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getorderinfonameArray-加载作者名列表出现异常", array);
//		}
//		return array;
//	}

}


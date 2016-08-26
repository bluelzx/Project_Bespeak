package com.lhfeiyu.action.front.article;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Comment;
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.OrderInfo;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.service.domain.CommentService;
import com.lhfeiyu.service.domain.GoodsService;
import com.lhfeiyu.service.domain.OrderInfoService;
import com.lhfeiyu.service.domain.UserFundService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

@Controller
public class CommentAction {
	@Autowired
	private CommentService commentService;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private UserFundService userFundService;
	@Autowired
	private GoodsService goodsService;
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/addComment/{orderInfoId}")
	public ModelAndView addComment(ModelMap modelMap,HttpServletRequest request,
			@PathVariable Integer orderInfoId) {
		String path = LhPage.comment;
		try{
			JSONObject json = new JSONObject();
			User user = ActionUtil.checkSession4User(request.getSession());
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("parentCode", "goods_type");
			OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(orderInfoId);
			Goods goods=goodsService.selectByPrimaryKey(orderInfo.getGoodsId());
			orderInfo.setGoodsName(goods.getGoodsName());
			orderInfo.setPicPath(goods.getPicPath());
			modelMap.put("orderInfo", orderInfo);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/addComment", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/getCommentList", method=RequestMethod.POST)
	public JSONObject getCommentList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			commentService.getCommentList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/getCommentList", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateComment", method=RequestMethod.POST)
	public JSONObject addOrUpdateComment(@ModelAttribute Comment comment, HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			String username = sessionUser.getUsername();
			int userId = sessionUser.getId();
			comment.setUserId(userId);
			if(null == comment.getId()){
				//comment.setCommentTypeCode("comment_forumArticle");
				commentService.addComment(json, comment, username);
				UserFund userFund=userFundService.selectUserFundByUserId(comment.getUserId());
				OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(comment.getOrderInfoId());
				if(userFund != null){
				BigDecimal integralFund=userFund.getIntegralFund();
				integralFund=integralFund.add(orderInfo.getTotalPrice());
				userFund.setIntegralFund(integralFund);
				userFund.setUpdatedAt(new Date());
				userFund.setUpdatedBy(sessionUser.getUsername());
//				userFundService.updateByIdsSelective(userFund);
				userFundService.updateByPrimaryKeySelective(userFund);
				orderInfo.setOrderStatusCode("order_status_done");//设置评价完成后订单完成
				orderInfo.setLinkId(userFund.getId());
				orderInfoService.updateByPrimaryKeySelective(orderInfo);
				}else {
					UserFund userFund1=new UserFund();
					userFund1.setCreatedAt(new Date());
					userFund1.setCreatedBy(sessionUser.getUsername());
					userFund1.setUserId(comment.getUserId());
					userFund1.setSerial(CommonGenerator.getSerialByDate("uf"));
					userFund1.setIntegralFund(orderInfo.getTotalPrice());
					userFundService.insert(userFund1);
				}
			}else{
				commentService.updateComment(json, comment, username);
			}
			Result.success(json);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/comment/addOrUpdateComment", json);
		}
		System.out.println("添加成功："+json);
		return Result.success(json);
	}
	
}

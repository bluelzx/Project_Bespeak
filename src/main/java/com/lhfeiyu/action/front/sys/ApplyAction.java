package com.lhfeiyu.action.front.sys;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.po.domain.Apply;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.Picture;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.ApplyService;
import com.lhfeiyu.service.domain.CommonFundService;
import com.lhfeiyu.service.domain.NoticeService;
import com.lhfeiyu.service.domain.PictureService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

@Controller
public class ApplyAction {
	@Autowired
	private ApplyService applyService;
	@Autowired
	private CommonFundService commonFundService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private PictureService pictureService;
	//@Autowired
	//private AuctionQuickService aqService;
	private static Logger logger = Logger.getLogger("R");
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateApply")
	public JSONObject addOrUpdateApply(@ModelAttribute Apply apply,HttpServletRequest request
		,@RequestParam(required=false) String payPass){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Integer type = apply.getApplyType();
			int typeId = 0;
			if(null != type){
				typeId = type;
			}
			Integer userId = user.getId();
			String username = user.getUsername();
			if(null == apply.getId()){//添加
				if(typeId == 94){//判断是否是批发城的申请
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("userId", userId);
					map.clear();
					map.put("userId", userId);
					map.put("applyType", typeId);
					map.put("mainStatus", 1);
					List<Apply> applyList = applyService.selectListByCondition(map);
					if(applyList.size() > 0){
						json.put("status", "failure");
						json.put("msg", "您已经申请了开通批发城,正在等待管理员的审核.");
						return Result.success(json);
					}
					//BigDecimal totalMoney = new BigDecimal(sysDict.getDictValue());
					//String payPass = request.getParameter("payPass");
					//TODO 修改
					//json = commonFundService.freezeMoney(payPass,totalMoney , userId, username, 144, 0, "SYS", json, false,null);
					if(json.containsKey("error_desc")){ return Result.success(json); }
				}else if(typeId == 97){//判断是否是申请开通产品库
					//SysDict sysDict = sysDictService.selectByPrimaryKey(7);
					BigDecimal totalMoney = new BigDecimal(apply.getFile4());//file4存的计算的费用
					//String payPass = request.getParameter("payPass");
					Map<String,Object> map = new HashMap<String,Object>();
					/*map.put("userId", userId);
					List<AuctionInst> aiList = aiService.selectListByCondition(map);
					if(aiList.size() > 0){
						json.put("status", "failure");
						json.put("msg", "您已经开通专场,无需再开通.");
						return Result.success(json);
					}
					map.clear();*/
					map.put("userId", userId);
					map.put("applyType", typeId);
					map.put("mainStatus", 1);
					List<Apply> applyList = applyService.selectListByCondition(map);
					if(applyList.size() > 0){
						json.put("status", "failure");
						json.put("msg", "您已经申请了开通产品库,正在等待管理员的审核.");
						return Result.success(json);
					}
					//TODO 修改
//					/json = commonFundService.freezeMoney(payPass,totalMoney , userId, username, 146, 0, "SYS", json, false,null);
					if(json.containsKey("error_desc")){ return Result.success(json); }
				}else if(typeId == 92){//92专场开通
					//String payPass = request.getParameter("payPass");
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("userId", userId);
					map.clear();
					map.put("userId", userId);
					map.put("applyType", typeId);
					map.put("mainStatus", 1);
					List<Apply> applyList = applyService.selectListByCondition(map);
					if(applyList.size() > 0){
						json.put("status", "failure");
						json.put("msg", "您已经申请了开通专场,正在等待管理员的审核.");
						return Result.success(json);
					}
					if(null == apply.getFile1()){
						apply.setFile1(user.getAvatar());
					}
					//TODO 修改
					//json = commonFundService.freezeMoney(payPass,new BigDecimal(1000) , userId, username, 140, 0, "SYS", json, false,null);
					if(json.containsKey("error_desc")){ return Result.success(json); }
				}/*//TODO 修改
				else if(typeId == 93){//93即时拍开通
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("userId", userId);
					List<AuctionQuick> aqList = aqService.selectListByCondition(map);
					if(aqList.size() > 0){
						json.put("status", "failure");
						json.put("msg", "您已经开通即时拍,无需再开通.");
						return Result.success(json);
					}
					map.clear();
					map.put("userId", userId);
					map.put("applyType", typeId);
					map.put("mainStatus", 1);
					List<Apply> applyList = applyService.selectListByCondition(map);
					if(applyList.size() > 0){
						json.put("status", "failure");
						json.put("msg", "您已经申请了开通即时拍,正在等待管理员的审核.");
						return Result.success(json);
					}
					if(null == apply.getFile1()){
						apply.setFile1(user.getAvatar());
					}
					//String payPass = request.getParameter("payPass");
					//String username = user.getUsername();
					//json = commonFundService.freezeMoney(payPass,new BigDecimal(1000) , userId, username, 140, 0, "SYS", json, false);
					//if(json.containsKey("error_desc")){ return Result.success(json); }
				}*/
			}/*else{//修改
				apply.setUpdatedAt(new Date());
				apply.setUpdatedBy(user.getUsername());
				applyService.updateByPrimaryKeySelective(apply);
			}*/
			Date date = new Date();
			apply.setSerial(CommonGenerator.getSerialByDate("a"));
			apply.setUserId(user.getId());
			apply.setApplyDate(date);
			apply.setCreatedAt(date);
			apply.setCreatedBy(user.getUsername());
			apply.setMainStatus(1);
			applyService.insert(apply);
			if(typeId == 93){
				commonSendNotic("即时拍开通",userId,username);
			}else if(typeId == 92){
				commonSendNotic("专场开通",userId,username);
			}else if(typeId == 94){
				commonSendNotic("批发城开通",userId,username);
			}else if(typeId == 95){
				commonSendNotic("论坛开通",userId,username);
			}else if(typeId == 91){
				commonSendNotic("实名认证",userId,username);
			}
			String fileDBIds = request.getParameter("fileDBIds");
			if(null != fileDBIds){//更新图片的类型
				if(apply.getApplyType() == 95){
					Picture p = new Picture();
					p.setTypeId(89);
					p.setId(Integer.valueOf(fileDBIds.substring(1)));
					pictureService.updateByPrimaryKeySelective(p);
				}
			}
			json.put("status", "success");
			json.put("id",apply.getId());
			json.put("msg", "申请提交成功");
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "申请提交失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改申请处理出现异常_", json);
		}
		return Result.success(json);
	}
	
	private void  commonSendNotic(String applyTypeName,Integer userId,String username){
		String title = "您的["+applyTypeName+"]申请提交成功,正在等待管理员的审核";
		Notice notice = new Notice();
		String serial = CommonGenerator.getSerialByDate("n");
		notice.setSerial(serial);
		notice.setTitle(title);
		notice.setContent(title);
		notice.setReceiverId(userId);
		notice.setReadStatus(1);
		notice.setCreatedAt(new Date());
		notice.setCreatedBy(username);
		noticeService.insert(notice);
	}
	
}

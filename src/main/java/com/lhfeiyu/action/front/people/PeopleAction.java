package com.lhfeiyu.action.front.people;

import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Apply;
import com.lhfeiyu.po.domain.BespeakSimple;
import com.lhfeiyu.po.domain.Dict;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.People;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.base.BaseBespeakSimpleService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.service.domain.NoticeService;
import com.lhfeiyu.service.domain.PeopleService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

@Controller
public class PeopleAction {

	@Autowired
	private PeopleService peopleService;
	@Autowired
	private DictService dictService;
	//通知消息noticeService
	@Autowired
	private BaseBespeakSimpleService bespeakSimpleService;
	//通知消息dbService
	@Autowired
	private NoticeService noticeService;
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value = "/peopleIndex")
	public ModelAndView peopleIndex(ModelMap modelMap, HttpServletRequest request,
			@RequestParam(required = false) String r) {
		String path = LhPage.peopleIndex;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
//			if (null == sessionUser) {
//				String jumpUrl = "/providerIndex";
//				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
//			}
			modelMap.put("user", sessionUser);
			json.put("user", sessionUser);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/providerIndex", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	//月嫂详情
	@RequestMapping(value = "/people/{peopleId}")
	public ModelAndView peoplexx(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer peopleId,
			@RequestParam(required = false) String r) {
		String path = LhPage.peopleInfo;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			People people = peopleService.selectByPrimaryKey(peopleId);
			json.put("peopleId", peopleId);
//			json.put("TitleName", people.getTitleName());
			if(people.getTitleCode()!=null){
			Dict dict1 = dictService.selectByPrimaryKey(Integer.parseInt(people.getTitleCode()));
			people.setTitleName(dict1.getCodeName());
			}
			modelMap.addAttribute("people", people);
			modelMap.put("paramJson", people);
//			System.out.println(people);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/people" + "/" + peopleId, modelMap);
		}
		
		return new ModelAndView(path, modelMap);
	}
	//月嫂课程申请页面
		@RequestMapping(value = "/peopleApply/{peopleId}")
		public ModelAndView peopleApply(ModelMap modelMap, HttpServletRequest request, 
				@PathVariable Integer peopleId,
				@RequestParam(required = false) String r) {
			String path = LhPage.peopleApply;
			try {
				JSONObject json = new JSONObject();
//				modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
				People people = peopleService.selectByPrimaryKey(peopleId);

				json.put("peopleId", peopleId);
				modelMap.addAttribute("people", people);
				modelMap.put("paramJson", json);
//				System.out.println("peopleApply"+people);
			} catch (Exception e) {
				Result.catchError(e, logger, this.getClass().getName() + "/peopleApply" + "/" + peopleId, modelMap);
			}
			
			return new ModelAndView(path, modelMap);
		}
	//获取所有月嫂信息
	@ResponseBody
	@RequestMapping(value = "/getPeopleLists", method = RequestMethod.POST)
	public JSONObject getPeopleLists(HttpServletRequest request,ModelMap modelMap) {
		List<People> peopleList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			
			peopleList = peopleService.selectListByCondition(map);
//			System.out.println(peopleList);
			modelMap.put("paramJson", json);
			modelMap.put("peopleList", peopleList);
			Integer total = peopleService.selectCountByCondition(map);
			Result.gridData(peopleList, total, json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/getPeopleList", json);
		}
//		System.out.println("json:"+json);
		return Result.success(json);
	}	
	
	//添加月嫂雇佣申请
		@ResponseBody
		@RequestMapping(value = "/addPeopleApply", method = RequestMethod.POST)
		public JSONObject addForum(HttpServletRequest request,ModelMap modelMap,
				@ModelAttribute BespeakSimple bespeakSimple) {
			//Apply apply
			JSONObject json = new JSONObject();
			try {
				User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
//				if(null == user){
//					return Result.userSessionInvalid(json);//返回session过期的json提示
//				}
//				Integer userId = user.getId();
//				String username = user.getUsername();
				Date date = new Date();
				Notice notice=new Notice();
				notice.setSendTime(date);
//				notice.setSenderId(bespeakSimple.getUserId());//发送人id
				notice.setSenderName(bespeakSimple.getName());//发送人名
				notice.setContent(bespeakSimple.getRemark());//发送的加备注详细信息
//				notice.setReceiverId(bespeakSimple.getProviderId());//接受的月嫂id
				notice.setAttrInt(bespeakSimple.getProviderId());//接受的月嫂id
				notice.setTypeCode("PeopleNotice");//设置类型Code
				People people1= peopleService.selectByPrimaryKey(bespeakSimple.getProviderId());
				notice.setReceiverIds(people1.getRealName());//设置接受的月嫂名
				notice.setTitle("来自"+bespeakSimple.getName()+"一个新的雇佣通知！");
				notice.setAttrStr(bespeakSimple.getPhone());//记录一个联系的电话号码
				notice.setAttrStr2(bespeakSimple.getAddress());//记录一个详细地址
				noticeService.insert(notice);
//				Investigation investigationset = new Investigation();
//				Integer LinkId = Investigation.getLinkId();
//				investigationset.setLinkId(LinkId);// 设置链接
//				investigationset.setUserAge(UserAge);
//				investigationset.setUsername(Username);
//				investigationset.setUserSex(UserSex);
				bespeakSimple.setCreatedAt(date);
				bespeakSimple.setCreatedBy(bespeakSimple.getName());
				bespeakSimple.setSerial(CommonGenerator.getSerialByDate("bs"));
				bespeakSimple.setMainStatus(1);//
				bespeakSimpleService.insert(bespeakSimple);
//				System.out.println(Username);
//				Investigation investigation= investigationService.selectByName(forumName);		
//				BaseForumMember baseForumMember = new BaseForumMember();
//				baseForumMember.setForumId(forum.getId());
//				baseForumMember.setUserId(userId);
////				forumMemberService.insertSelective(baseForumMember);
//				}else{
//					return Result.failure(json, "论坛名已存在", "The Forum Name is exist");
//				}
				modelMap.put("paramJson", json);
			} catch (Exception e) {
				Result.catchError(e, logger, this.getClass().getName() + "/addPeopleApply", json);
			}
			//System.out.println(json);
			return Result.success(json);
		}
}

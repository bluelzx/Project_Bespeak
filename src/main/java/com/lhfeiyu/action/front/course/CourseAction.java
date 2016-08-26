package com.lhfeiyu.action.front.course;

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
import com.lhfeiyu.po.domain.Course;
import com.lhfeiyu.po.domain.Investigation;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.base.BaseCourseService;
import com.lhfeiyu.service.base.BaseInvestigationService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

@Controller
public class CourseAction {

	@Autowired
	private BaseCourseService courseService;
	@Autowired
	private BaseInvestigationService investigationService;

	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value = "/courseIndex")
	public ModelAndView courseIndex(ModelMap modelMap, HttpServletRequest request,
			@RequestParam(required = false) String r) {
		String path = LhPage.courseIndex;
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
	//培训详情
	@RequestMapping(value = "/course/{courseId}")
	public ModelAndView coursexx(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer courseId,
			@RequestParam(required = false) String r) {
		String path = LhPage.courseInfo;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			Course course = courseService.selectByPrimaryKey(courseId);

			json.put("courseId", courseId);
			modelMap.addAttribute("course", course);
			modelMap.put("paramJson", json);
//			System.out.println(course);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/course" + "/" + courseId, modelMap);
		}
		
		return new ModelAndView(path, modelMap);
	}
	//培训课程申请页面
		@RequestMapping(value = "/courseApply/{courseId}")
		public ModelAndView courseApply(ModelMap modelMap, HttpServletRequest request, 
				@PathVariable Integer courseId,
				@RequestParam(required = false) String r) {
			String path = LhPage.courseApply;
			try {
				JSONObject json = new JSONObject();
//				modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
				Course course = courseService.selectByPrimaryKey(courseId);

				json.put("courseId", courseId);
				modelMap.addAttribute("course", course);
				modelMap.put("paramJson", json);
//				System.out.println("courseApply"+course);
			} catch (Exception e) {
				Result.catchError(e, logger, this.getClass().getName() + "/courseApply" + "/" + courseId, modelMap);
			}
			
			return new ModelAndView(path, modelMap);
		}
	//获取所有培训信息
	@ResponseBody
	@RequestMapping(value = "/getCourseLists", method = RequestMethod.POST)
	public JSONObject getCourseLists(HttpServletRequest request,ModelMap modelMap) {
		List<Course> courseList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
//			System.out.println(map);
			courseList = courseService.selectListByCondition(map);
			modelMap.put("paramJson", json);
			modelMap.put("courseList", courseList);
			Integer total = courseService.selectCountByCondition(map);
			Result.gridData(courseList, total, json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/getCourseList", json);
		}
		System.out.println("json:"+json);
		return Result.success(json);
	}	
	
	//添加课程申请
		@ResponseBody
		@RequestMapping(value = "/addInvestigation", method = RequestMethod.POST)
		public JSONObject addForum(HttpServletRequest request,ModelMap modelMap,
				@ModelAttribute Investigation Investigation) {
			//Investigation investigation
			JSONObject json = new JSONObject();
			try {
//				System.out.println(Investigation);
				User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
////				if(null == user){
////					return Result.userSessionInvalid(json);//返回session过期的json提示
////				}
//				Integer userId = user.getId();
//				String username = user.getUsername();
				Date date = new Date();
				Investigation investigationset = new Investigation();
				Integer LinkId = Investigation.getLinkId();
				String Username = Investigation.getUsername();
				String UserPhone=Investigation.getUserPhone();
				Integer UserAge=Investigation.getUserAge();
				String CourseName=Investigation.getCourseName();
				String UserSex=Investigation.getUserSex();
//				if(UserSex.equals("1")){
//					UserSex="男";
//				}else{
//					UserSex="女";
//				}
				investigationset.setUsername(Username);
				investigationset.setUserPhone(UserPhone);
				investigationset.setLinkId(LinkId);// 设置链接
				investigationset.setUserAge(UserAge);
				investigationset.setCourseName(CourseName);//数据库无此字段
				investigationset.setUsername(Username);
				investigationset.setUserSex(UserSex);
				investigationset.setCreatedAt(date);
				investigationset.setCreatedBy(Username);
				investigationset.setApplyTime(date);
				investigationset.setSerial(CommonGenerator.getSerialByDate("i"));
				investigationset.setMainStatus(1);//
				investigationService.insert(investigationset);
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
				Result.catchError(e, logger, this.getClass().getName() + "/addInvestigation", json);
			}
			//System.out.println(json);
			return Result.success(json);
		}
}

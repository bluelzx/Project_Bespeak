package com.lhfeiyu.action.back.forum;

import java.util.List;
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
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.ForumMember;
import com.lhfeiyu.service.domain.ForumMemberService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：论坛成员 ForumMember <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.forumMember.BackForumMemberAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackForumMemberAction {
	
	@Autowired
	private ForumMemberService forumMemberService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台论坛成员页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/forumMember")
	public ModelAndView forumMember(ModelMap modelMap){
		String path = LhPage.back_forumMember;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/forumMember", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 加载论坛成员列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getForumMemberList",method=RequestMethod.POST)
	public JSONObject getForumMemberList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
//			String ascOrdesc = request.getParameter("ascOrdesc");
//			System.out.println(ascOrdesc+"11111");
			List<ForumMember> ForumMember = forumMemberService.selectListByCondition(map);
			Integer total = forumMemberService.selectCountByCondition(map);
			Result.gridData(ForumMember, total, json);
			Result.success(json, "数据加载成功", null);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getForumMemberList", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改论坛成员（新增和修改方法对应Serivce中的不同方法）
	 * @param forumMember ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateForumMember", method = RequestMethod.POST)
	public JSONObject addUpdateForumMember(@ModelAttribute ForumMember forumMember,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			forumMemberService.addUpdateForumMember(json, forumMember, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateForumMember", json);
		}
		return json;
	}

	/**  后台
	 * 新增或修改论坛成员（新增和修改方法对应Serivce中的不同方法）
	 * @param forumMember ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateForumMember", method = RequestMethod.POST)
	public JSONObject addOrUpdateForumMember(@ModelAttribute ForumMember forumMember,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			forumMemberService.addUpdateForumMember(json, forumMember, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateForumMember", json);
		}
		return json;
	}
	/**
	 * 逻辑删除论坛成员数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateForumMemberDelete", method=RequestMethod.POST)
	public JSONObject updateForumMemberDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			forumMemberService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateForumMemberDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除论坛成员
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteForumMemberThorough",method=RequestMethod.POST)
	public JSONObject deleteForumMemberThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			forumMemberService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteForumMemberThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复论坛成员（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateForumMemberRecover", method=RequestMethod.POST)
	public JSONObject updateForumMemberRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			forumMemberService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateForumMemberRecover", json);
		}
		return json;
	}
	

}


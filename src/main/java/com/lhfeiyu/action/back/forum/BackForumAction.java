package com.lhfeiyu.action.back.forum;

import java.util.HashMap;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Forum;
import com.lhfeiyu.service.domain.ForumService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：论坛 Forum <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.forum.BackForumAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackForumAction {
	
	@Autowired
	private ForumService forumService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台论坛页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/forum")
	public ModelAndView forum(ModelMap modelMap){
		String path = LhPage.back_forum;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/forum", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getForumArray", method=RequestMethod.POST)
	public JSONArray getForumArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Forum> forumList = forumService.selectListByCondition(map);
			for(Forum h:forumList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
				json.put("name",h.getName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getForumArray-加载圈子数组列表出现异常", array);
		}
		return array;
	}
	
	
	
	/**
	 * 加载论坛列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getForumList",method=RequestMethod.POST)
	public JSONObject getForumList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			String ascOrdesc = request.getParameter("ascOrdesc");
			if(null != ascOrdesc){
				if(ascOrdesc.equals("1")){
					map.put("orderBy", "user_id");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("2")){
					map.put("orderBy", "user_id");
					map.put("ascOrdesc", "DESC");
				}else if(ascOrdesc.equals("3")){
					map.put("orderBy", "created_at");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("4")){
					map.put("orderBy", "created_at");
					map.put("ascOrdesc", "DESC");
				}
			}
			forumService.getForumList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getForumList", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改论坛（新增和修改方法对应Serivce中的不同方法）
	 * @param forum ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateForum", method = RequestMethod.POST)
	public JSONObject addUpdateForum(@ModelAttribute Forum forum,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			forumService.addUpdateForum(json, forum, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateForum", json);
		}
		return json;
	}
	
	/**后台
	 * 新增或修改论坛（新增和修改方法对应Serivce中的不同方法）
	 * @param forum ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateForum", method = RequestMethod.POST)
	public JSONObject addOrUpdateForum(@ModelAttribute Forum forum,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			forumService.addUpdateForum(json, forum, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdateForum", json);
		}
		return json;
	}

	/**
	 * 逻辑删除论坛数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateForumDelete", method=RequestMethod.POST)
	public JSONObject updateForumDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			forumService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateForumDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除论坛
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteForumThorough",method=RequestMethod.POST)
	public JSONObject deleteForumThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			forumService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteForumThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复论坛（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateForumRecover", method=RequestMethod.POST)
	public JSONObject updateForumRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			forumService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateForumRecover", json);
		}
		return json;
	}
	

}


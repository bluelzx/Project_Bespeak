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
import com.lhfeiyu.po.domain.ForumArticle;
import com.lhfeiyu.service.domain.ForumArticleService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：论坛文章 ForumArticle <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.forumArticle.BackForumArticleAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackForumArticleAction {
	
	@Autowired
	private ForumArticleService forumArticleService;
	@Autowired
	private UserService userService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台论坛文章页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/forumArticle")
	public ModelAndView forumArticle(ModelMap modelMap){
		String path = LhPage.back_forumArticle;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/forumArticle", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 加载论坛文章列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getForumArticleList",method=RequestMethod.POST)
	public JSONObject getForumArticleList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
//			map.put("parentCodeNotNull", 1);//不查跟节点
//			forumArticleService.getForumArticleList(json, map);
			String ascOrdesc = request.getParameter("ascOrdesc");
			if(null != ascOrdesc){
				if(ascOrdesc.equals("1")){
					map.put("orderBy", "forum_name");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("2")){
					map.put("orderBy", "forum_name");
					map.put("ascOrdesc", "DESC");
				}else if(ascOrdesc.equals("3")){
					map.put("orderBy", "forum_id");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("4")){
					map.put("orderBy", "forum_id");
					map.put("ascOrdesc", "DESC");
				}else if(ascOrdesc.equals("5")){
					map.put("orderBy", "created_at");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("6")){
					map.put("orderBy", "created_at");
					map.put("ascOrdesc", "DESC");
				}
			}
			List<ForumArticle> ForumArticle = forumArticleService.selectListByCondition(map);
			Integer total = forumArticleService.selectCountByCondition(map);
			Result.gridData(ForumArticle, total, json);
			Result.success(json, "数据加载成功", null);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getForumArticleList", json);
		}
		return json;
	}
	
//	//通过指定id查询用户名
//	@ResponseBody
//	@RequestMapping(value = "/getusernameArray", method=RequestMethod.POST)
//	public JSONArray getusernameArray(HttpServletRequest request) {
//		JSONArray array = new JSONArray();
//		try {
//			Map<String,Object> map = new HashMap<String,Object>();
//			List<User> usernameList = userService.selectListByCondition(map);
//			for(User h:usernameList){
//				JSONObject json = new JSONObject();
//				json.put("id",h.getId());
//				json.put("name",h.getUsername());
////				System.out.println(h.getUsername());
////				System.out.println(h.getUsername());
//				array.add(json);
//			}
//		} catch (Exception e) {
//			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getusernameArray-加载作者名列表出现异常", array);
//		}
//		return array;
//	}
	
	/**
	 * 新增或修改论坛文章（新增和修改方法对应Serivce中的不同方法）
	 * @param forumArticle ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateForumArticle", method = RequestMethod.POST)
	public JSONObject addUpdateForumArticle(@ModelAttribute ForumArticle forumArticle,HttpServletRequest request){
		JSONObject json = new JSONObject();
		forumArticle.getShowArticleCount();
		System.out.println(json);
		try {	
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
//			forumArticle.setShowArticleCount(1);
//			forumArticle.setPraiseNum(1);
//			forumArticle.setIsPraise(1);
//			mainObj.praiseType=null;
//			mainObj.forumName=null;
			forumArticleService.addUpdateForumArticle(json, forumArticle, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateForumArticle", json);
		}
		return json;
	}
	/**后台
	 * 新增或修改论坛文章（新增和修改方法对应Serivce中的不同方法）
	 * @param forumArticle ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateForumArticle", method = RequestMethod.POST)
	public JSONObject addOrUpdateForumArticle(@ModelAttribute ForumArticle forumArticle,HttpServletRequest request){
		JSONObject json = new JSONObject();
		System.out.println(json);
		try {	
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
//			forumArticle.setShowArticleCount(1);
//			forumArticle.setPraiseNum(1);
//			forumArticle.setIsPraise(1);
//			mainObj.praiseType=null;
//			mainObj.forumName=null;
			forumArticleService.addUpdateForumArticle(json, forumArticle, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateForumArticle", json);
		}
		return json;
	}

	/**
	 * 逻辑删除论坛文章数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateForumArticleDelete", method=RequestMethod.POST)
	public JSONObject updateForumArticleDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			forumArticleService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateForumArticleDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除论坛文章
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteForumArticleThorough",method=RequestMethod.POST)
	public JSONObject deleteForumArticleThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			forumArticleService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteForumArticleThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复论坛文章（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateForumArticleRecover", method=RequestMethod.POST)
	public JSONObject updateForumArticleRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			forumArticleService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateForumArticleRecover", json);
		}
		return json;
	}
	
}


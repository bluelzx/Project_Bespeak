package com.lhfeiyu.action.front.article;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.lhfeiyu.po.domain.Article;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.ArticleService;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-前台：文章 Article <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包含：活动activity，新闻news <p>
 */
@Controller
public class ArticleAction {
	@Autowired
	private ArticleService  articleService;
	@Autowired
	private AuthCheckService authCheckService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/activity")
	public ModelAndView activity(ModelMap modelMap,HttpServletRequest request
			,@RequestParam(required=false) String r){
		String path = LhPage.activities;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			if(null != sessionUser){
				modelMap.put("userId", sessionUser.getId());
			}
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/article/activity", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	@RequestMapping(value="/activity/{id}")
	public ModelAndView activityDetail(ModelMap modelMap,HttpServletRequest request
			,@PathVariable Integer id
			,@RequestParam(required=false) String r){
		String path = LhPage.activitiesDetail;
		try{
			/*User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				return Result.userSessionInvalid(modelMap, "/activitiesDetail?id="+id);
			}*/
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
			Article article = articleService.selectByPrimaryKey(id);
			modelMap.put("article",article);
			modelMap.put("id",id);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/article/activity/"+id, modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/news")
	public ModelAndView news(ModelMap modelMap,HttpServletRequest request
			,@RequestParam(required=false) String r){
		String path = LhPage.news;
		try{
			//User sessionUser = ActionUtil.checkSession4User(session);
			/*if(null == sessionUser){
				return Result.userSessionInvalid(modelMap, "/news");
			}*/
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/article/news", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/news/{id}")
	public ModelAndView newsDetail(ModelMap modelMap,HttpServletRequest request
			,@PathVariable Integer id
			,@RequestParam(required=false) String r){
		String path = LhPage.newsDetail;
		try{
			/*User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				return Result.userSessionInvalid(modelMap, "/newsDetail?id="+id);
			}*/
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
			Article article = articleService.selectByPrimaryKey(id);
			modelMap.put("article", article);
			modelMap.put("articleId", id);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/article/news/"+id, modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	@RequestMapping(value="/addActivity")
	public ModelAndView addActivity(ModelMap modelMap,HttpSession session
			,@RequestParam(required=false) String r,HttpServletRequest request){
		String path = LhPage.addActivities;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				String jumpUrl = "/addActivity";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/article/addActivity", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	@RequestMapping(value="/addNews/{catId}")
	public ModelAndView addNews(ModelMap modelMap,HttpServletRequest request
			,@PathVariable Integer catId
			,@RequestParam(required=false) String r){
		String path = LhPage.addNews;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if(null == sessionUser){
				String jumpUrl = "/addNews/"+catId;
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			modelMap.put("catId", catId);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/article/addNews/"+catId, modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/getArticleList", method=RequestMethod.POST)
	public JSONObject getArticleList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			articleService.getArticleList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/article/getArticleList", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateArticle", method=RequestMethod.POST)
	public JSONObject addOrUpdateArticle(@ModelAttribute Article article, HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Integer userId = sessionUser.getId();
			String username = sessionUser.getUsername();
			article.setUserId(userId);
			article.setAuthor(username);
			if(null == article.getId()){//添加
				article.setIsTop(1);
				article.setIsHot(1);
				article.setIsRecommend(1);
				article.setIsGood(1);
			}
			articleService.addUpdateArticle(json, article, username);
			Result.success(json);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/article/addOrUpdateArticle", json);
		}
		return Result.success(json);
	}
	
	/*@ResponseBody
	@RequestMapping(value="/getArticleIsTopIsRecommendList", method=RequestMethod.POST)
	public JSONObject getArticleIsTopIsRecommendList(HttpServletRequest request) {
		List<Article> articleList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			articleList = articleService.getArticleIsTopIsRecommendList(map);
			//Integer total = articleService.selectCountByCondition(map);
			json.put("rows", articleList);
			//json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载活动或新闻(文章)列表出现异常_", json);
		}
		return Result.success(json);
	}*/
	
}

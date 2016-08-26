package com.lhfeiyu.action.back.article;

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
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Article;
import com.lhfeiyu.service.domain.ArticleService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：文章 Article <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.article.BackArticleAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackArticleAction {
	
	@Autowired
	private ArticleService articleService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台文章页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/article")
	public ModelAndView article(ModelMap modelMap){
		String path = LhPage.back_article;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/article", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 新增修改文章
	 * @param modelMap
	 * @param typeId 类型ID
	 * @param articleId 文章ID
	 * @param operation String 操作
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/articleAddUpdate")
	public ModelAndView articleAddUpdate(ModelMap modelMap,
			@RequestParam(required=false) Integer typeId,
			@RequestParam(required=false) Integer articleId,
			@RequestParam(required=false) String operation){
		String path = LhPage.back_articleAddUpdate;
		try{
			modelMap.put("typeId", typeId);
			if(Check.isNotNull(articleId)){
				modelMap.put("articleId", articleId);
				Article article = articleService.selectByPrimaryKey(articleId);
				if(null != article){
					modelMap.put("article", article);
				}
			}
			if(Check.isNotNull(operation)){
				modelMap.put("operation", operation);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/articleAddUpdate", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 新增修改文章（根据ID加载文章存入modelMap）
	 * @param modelMap
	 * @param articleId 文章ID
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/articleAddUpdate/{articleId}")
	public ModelAndView articleAddUpdate(ModelMap modelMap, @PathVariable Integer articleId){
		String path = LhPage.back_articleAddUpdate;
		System.out.println(articleId+"and +id");
		try{
			Article article = articleService.selectByPrimaryKey(articleId);
			if(null != article){
				modelMap.put("article", article);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/page/articleAddUpdate/{articleId}", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/page/article/{articleId}")
	public ModelAndView articleDetail(ModelMap modelMap,@PathVariable Integer articleId){
		String path = LhPage.back_articleDetail;
		try{
			Article article = articleService.selectByPrimaryKey(articleId);
			if(null != article){
				modelMap.put("article", article);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/page/article/{articleId}", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载文章列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getArticleList",method=RequestMethod.POST)
	public JSONObject getArticleList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			articleService.getArticleList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getArticleList", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改文章（新增和修改方法对应Serivce中的不同方法）
	 * @param article ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateArticle", method = RequestMethod.POST)
	public JSONObject addUpdateArticle(@ModelAttribute Article article,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			articleService.addUpdateArticle(json, article, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateArticle", json);
		}
		return json;
	}
	
	/**后台
	 * 新增或修改文章（新增和修改方法对应Serivce中的不同方法）
	 * @param article ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateArticle", method = RequestMethod.POST)
	public JSONObject addOrUpdateArticle(@ModelAttribute Article article,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			articleService.addUpdateArticle(json, article, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdateArticle", json);
		}
		return json;
	}

	/**
	 * 逻辑删除文章数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateArticleDelete", method=RequestMethod.POST)
	public JSONObject updateArticleDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			articleService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateArticleDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除文章
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteArticleThorough",method=RequestMethod.POST)
	public JSONObject deleteArticleThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			articleService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteArticleThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复文章（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateArticleRecover", method=RequestMethod.POST)
	public JSONObject updateArticleRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			articleService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateArticleRecover", json);
		}
		return json;
	}
	

}


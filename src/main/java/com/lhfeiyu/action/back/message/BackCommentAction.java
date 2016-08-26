package com.lhfeiyu.action.back.message;

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
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Comment;
import com.lhfeiyu.service.domain.CommentService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：评论回复 Comment <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.message.BackCommentAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackCommentAction {
	
	@Autowired
	private CommentService commentService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台评论回复页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/comment")
	public ModelAndView comment(ModelMap modelMap){
		String path = LhPage.back_comment;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-Comment-PAGE-/back/page/comment-加载评论回复页面出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载评论回复列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getCommentList",method=RequestMethod.POST)
	public JSONObject getCommentList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			commentService.getCommentList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Comment-AJAX-/back/getCommentList-加载评论回复列表出现异常", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改评论回复（新增和修改方法对应Serivce中的不同方法）
	 * @param comment ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateComment", method = RequestMethod.POST)
	public JSONObject addUpdateComment(@ModelAttribute Comment comment,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			if(null!=comment.getId()){
				comment.setUpdatedAt(new Date());
				comment.setUpdatedBy(username);
			commentService.updateByPrimaryKeySelective(comment);
			}else{
				comment.setSerial(CommonGenerator.getSerialByDate("cm"));
				comment.setCreatedAt(new Date());
				comment.setCreatedBy(username);
				commentService.insert(comment);
			}
		}catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Comment-AJAX-/back/addUpdateComment-添加或修改评论回复出现异常", json);
		}
		return Result.success(json);
	}
	
	/**
	 * 逻辑删除评论回复数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCommentDelete",method=RequestMethod.POST)
	public JSONObject updateCommentDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			commentService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Comment-AJAX-/back/updateCommentDelete-删除评论回复出现异常", json);
		}
		return json;
	}
	
	/**
	 * 物理删除评论回复
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCommentThorough",method=RequestMethod.POST)
	public JSONObject deleteCommentThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			commentService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Comment-AJAX-/back/deleteCommentThorough-彻底删除评论回复出现异常", json);
		}
		return json;
	}
	
	/**
	 * 恢复数字字典数据（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCommentRecover",method=RequestMethod.POST)
	public JSONObject updateCommentRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			commentService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Comment-AJAX-/back/updateCommentRecover-恢复评论回复出现异常", json);
		}
		return json;
	}
	
}

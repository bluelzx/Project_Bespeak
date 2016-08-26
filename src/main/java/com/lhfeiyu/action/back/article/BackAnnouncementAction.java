package com.lhfeiyu.action.back.article;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

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
import com.lhfeiyu.po.domain.Announcement;
import com.lhfeiyu.service.domain.AnnouncementService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：公告 Announcement <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.announcement.BackAnnouncementAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackAnnouncementAction {
	
	@Autowired
	private AnnouncementService announcementService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台公告页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/announcement")
	public ModelAndView announcement(ModelMap modelMap){
		String path = LhPage.back_announcement;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/announcement", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 新增修改公告
	 * @param modelMap
	 * @param typeId 类型ID
	 * @param announcementId 公告ID
	 * @param operation String 操作
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/annoAddUpdate")
	public ModelAndView announcementAddUpdate(ModelMap modelMap,
			@RequestParam(required=false) Integer typeId,
			@RequestParam(required=false) Integer announcementId,
			@RequestParam(required=false) String operation){
		String path = LhPage.back_annoAddUpdate;
		try{
			modelMap.put("typeId", typeId);
			if(Check.isNotNull(announcementId)){
				modelMap.put("announcementId", announcementId);
				Announcement announcement = announcementService.selectByPrimaryKey(announcementId);
				if(null != announcement){
					modelMap.put("announcement", announcement);
				}
			}
			if(Check.isNotNull(operation)){
				modelMap.put("operation", operation);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/annoAddUpdate", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 新增修改公告（根据ID加载公告存入modelMap）
	 * @param modelMap
	 * @param announcementId 公告ID
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/announcementAddUpdate/{announcementId}")
	public ModelAndView announcementAddUpdate(ModelMap modelMap, @PathParam(value="announcementId") Integer announcementId){
		String path = LhPage.back_annoAddUpdate;
		try{
			Announcement announcement = announcementService.selectByPrimaryKey(announcementId);
			if(null != announcement){
				modelMap.put("announcement", announcement);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/page/announcementAddUpdate/{announcementId}", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/page/announcement/{announcementId}")
	public ModelAndView announcementDetail(ModelMap modelMap,@PathParam(value = "announcementId") Integer announcementId){
		String path = LhPage.back_annoDetail;
		try{
			Announcement announcement = announcementService.selectByPrimaryKey(announcementId);
			if(null != announcement){
				modelMap.put("announcement", announcement);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/page/announcement/{announcementId}", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载公告列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getAnnouncementList",method=RequestMethod.POST)
	public JSONObject getAnnouncementList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			announcementService.getAnnouncementList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getAnnouncementList", json);
		}
		return json;
	}
	
	/**后台
	 * 新增或修改公告（新增和修改方法对应Serivce中的不同方法）
	 * @param announcement ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateAnnouncement", method = RequestMethod.POST)
	public JSONObject addOrUpdateAnnouncement(@ModelAttribute Announcement announcement,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			announcementService.addUpdateAnnouncement(json, announcement, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdateAnnouncement", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改公告（新增和修改方法对应Serivce中的不同方法）
	 * @param announcement ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateAnnouncement", method = RequestMethod.POST)
	public JSONObject addUpdateAnnouncement(@ModelAttribute Announcement announcement,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			announcementService.addUpdateAnnouncement(json, announcement, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateAnnouncement", json);
		}
		return json;
	}


	/**
	 * 逻辑删除公告数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAnnouncementDelete", method=RequestMethod.POST)
	public JSONObject updateAnnouncementDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			announcementService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateAnnouncementDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除公告
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAnnouncementThorough",method=RequestMethod.POST)
	public JSONObject deleteAnnouncementThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			announcementService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteAnnouncementThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复公告（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAnnouncementRecover", method=RequestMethod.POST)
	public JSONObject updateAnnouncementRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			announcementService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateAnnouncementRecover", json);
		}
		return json;
	}
	

}


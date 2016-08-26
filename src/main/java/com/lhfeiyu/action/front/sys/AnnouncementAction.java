package com.lhfeiyu.action.front.sys;

import java.text.SimpleDateFormat;
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
import com.lhfeiyu.po.domain.Announcement;
import com.lhfeiyu.po.domain.Forum;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AnnouncementService;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.ForumService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
public class AnnouncementAction {
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private ForumService baseForumService;
	@Autowired
	private AuthCheckService authCheckService;
	private static Logger logger = Logger.getLogger("R");

	@RequestMapping(value = "/announcement/{forumId}")
	public ModelAndView announcement(ModelMap modelMap, HttpServletRequest request, @PathVariable Integer forumId,
			@RequestParam(required = false) String r) {
		String path = LhPage.announcement;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/announcement" + forumId;
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Forum baseForum = baseForumService.selectByPrimaryKey(forumId);
			if ((sessionUser.getId() == baseForum.getModeratorId())
					|| (sessionUser.getId() == baseForum.getSubModeratorId())) {
				modelMap.put("isManager", 1);
			}
			modelMap.put("forum", baseForum);
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/announcement", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	@RequestMapping(value = "/announcementInfo/{announcementId}")
	public ModelAndView announcementInfo(ModelMap modelMap, HttpServletRequest request,
			@PathVariable Integer announcementId, @RequestParam(required = false) String r) {
		String path = LhPage.announcementInfo;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/announcement" + announcementId;
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Announcement baseAnnouncement = announcementService.selectByPrimaryKey(announcementId);
			Integer forumId=baseAnnouncement.getTypeId();
			Forum baseForum = baseForumService.selectByPrimaryKey(forumId);
			if ((sessionUser.getId() == baseForum.getModeratorId())
					|| (sessionUser.getId() == baseForum.getSubModeratorId())) {
				modelMap.put("isManager", 1);
			}
			String createdAt = "";
			String datePattern = "yyyy-MM-dd  HH:mm:ss";

			SimpleDateFormat df = new SimpleDateFormat(datePattern);
			createdAt = df.format(baseAnnouncement.getCreatedAt());
			modelMap.put("announcement", baseAnnouncement);
			modelMap.put("createdAt", createdAt);
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/announcementInfo", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	@RequestMapping(value = "/addAnnouncement/{forumId}")
	public ModelAndView addAnnouncement(ModelMap modelMap, HttpServletRequest request, @PathVariable Integer forumId,
			@RequestParam(required = false) String r) {
		String path = LhPage.addAnnouncement;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/addAnnouncement";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Forum baseForum = baseForumService.selectByPrimaryKey(forumId);
			modelMap.put("forum", baseForum);
			modelMap.put("user", sessionUser);
			/*
			 * Map<String,Object> map = new HashMap<String,Object>();
			 * map.put("dictType", "announcement"); List<Dict>
			 * announcementDictList = dictService.selectListByCondition(map);
			 * modelMap.put("announcementDictList", announcementDictList);
			 */
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/addAnnouncement", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/addOrUpdateAnnouncement", method = RequestMethod.POST)
	public JSONObject addOrUpdateAnnouncement(@ModelAttribute Announcement announcement,
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			if (null != user && !"".equals(user)) {
				if (null == announcement.getId()) {// 添加
					announcement.setMainStatus(2);
					announcement.setCreatedAt(new Date());
					announcement.setCreatedBy(user.getUsername());
					announcementService.insert(announcement);
				} else {// 修改
					announcement.setUpdatedAt(new Date());
					announcement.setUpdatedBy(user.getUsername());
					announcementService.updateByPrimaryKeySelective(announcement);
				}
				json.put("status", "success");
				json.put("id", announcement.getId());
				json.put("msg", "操作成功");
			} else {
				json.put("msg", "会话过期，请重新登陆。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改公告出现异常_", json);
		}
		return Result.success(json);
	}

	@ResponseBody
	@RequestMapping(value = "/getAnnouncementList", method = RequestMethod.POST)
	public JSONObject getAnnouncementList(HttpServletRequest request) {
		List<Announcement> announcementList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			announcementList = announcementService.selectListByCondition(map);
			Integer total = announcementService.selectCountByCondition(map);
			json.put("rows", announcementList);
			json.put("total", total);
			json.put("status", "success");
			if (announcementList.size() < 1) {
				return Result.failure(json, "暂时没有公告", "null");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载公告列表出现异常_", json);
		}
		return Result.success(json);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAnnouncement")
	public JSONObject deleteAnnouncement(HttpServletRequest request, @RequestParam Integer id) {
		JSONObject json = new JSONObject();
		System.out.println("删除：" + id);
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}

			int sessionUserId = user.getId();
			if (null != id) {
				announcementService.deleteByPrimaryKey(id);
				json.put("status", "success");
				json.put("msg", "已成功删除该公告");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_删除商品出现异常_", json);
		}
		return Result.success(json);
	}
}

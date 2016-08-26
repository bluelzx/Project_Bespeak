package com.lhfeiyu.action.back.message;

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
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.NoticeService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：通知消息 Notice <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.message.BackNoticeAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackNoticeAction {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台通知消息页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/notice")
	public ModelAndView notice(ModelMap modelMap){
		String path = LhPage.back_notice;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-Notice-PAGE-/back/page/notice-加载通知消息页面出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载论坛列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getNoticeList",method=RequestMethod.POST)
	public JSONObject getNoticeList(HttpServletRequest request) {
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
			noticeService.getNoticeList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getNoticeList", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改通知消息（新增和修改方法对应Serivce中的不同方法）
	 * @param notice ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateNotice", method = RequestMethod.POST)
	public JSONObject addOrUpdateNotice(@ModelAttribute Notice notice,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			noticeService.addUpdateNotice(json, notice, username);
		}catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Notice-AJAX-/back/addOrUpdateNotice-添加或修改通知消息出现异常", json);
		}
		return json;
	}

	
	/**
	 * 新增或修改通知消息（新增和修改方法对应Serivce中的不同方法）
	 * @param notice ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateNotice", method = RequestMethod.POST)
	public JSONObject addUpdateNotice(@ModelAttribute Notice notice,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			noticeService.addUpdateNotice(json, notice, username);
		}catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Notice-AJAX-/back/addUpdateNotice-添加或修改通知消息出现异常", json);
		}
		return json;
	}
	
	/**
	 * 逻辑删除通知消息数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateNoticeDelete",method=RequestMethod.POST)
	public JSONObject updateNoticeDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			noticeService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Notice-AJAX-/back/updateNoticeDelete-删除通知消息出现异常", json);
		}
		return json;
	}
//	/**
//	 * 加载通知消息列表数据
//	 * @param request
//	 * @return JSONObject(rows,total,status,success)
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/getNoticeList",method=RequestMethod.POST)
//	public JSONObject getNoticeList(HttpServletRequest request) {
//		JSONObject json = new JSONObject();
//		try {
//			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
//			noticeService.getNoticeList(json, map);
//		} catch (Exception e) {
//			Result.catchError(e, logger, "LH_ERROR-Notice-AJAX-/back/getNoticeList-加载通知消息列表出现异常", json);
//		}
//		return json;
//	}
	/**
	 * 物理删除通知消息
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNoticeThorough",method=RequestMethod.POST)
	public JSONObject deleteNoticeThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			noticeService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Notice-AJAX-/back/deleteNoticeThorough-彻底删除通知消息出现异常", json);
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
	@RequestMapping(value = "/updateNoticeRecover",method=RequestMethod.POST)
	public JSONObject updateNoticeRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			noticeService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Notice-AJAX-/back/updateNoticeRecover-恢复通知消息出现异常", json);
		}
		return json;
	}
	
	//通过指定id查询用户名
	@ResponseBody
	@RequestMapping(value = "/getUserArray", method=RequestMethod.POST)
	public JSONArray getusernameArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<User> usernameList = userService.selectListByCondition(map);
			for(User h:usernameList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
				json.put("name",h.getUsername());
//				System.out.println(h.getUsername());
//				System.out.println(h.getUsername());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getusernameArray-加载作者名列表出现异常", array);
		}
		return array;
	}
	
}

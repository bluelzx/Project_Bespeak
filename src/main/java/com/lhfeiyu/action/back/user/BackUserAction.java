package com.lhfeiyu.action.back.user;

import java.util.Date;
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
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：用户 User <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.user.BackUserAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackUserAction {
	
	@Autowired
	private UserService userService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台用户页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/userInfo")
	public ModelAndView user(ModelMap modelMap){
		String path = LhPage.back_user;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/user", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	
	/**
	 * 加载用户列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserList",method=RequestMethod.POST)
	public JSONObject getUserList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			userService.getUserList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getUserList", json);
		}
		System.out.println("user:"+json);
		return json;
	}
	
	
	/**
	 * 新增或修改用户（新增和修改方法对应Serivce中的不同方法）
	 * @param user ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
	public JSONObject addUpdateUser(@ModelAttribute User user,HttpServletRequest request){
		JSONObject json = new JSONObject();
		
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			if(null != user.getId()){//更新
				user.setUpdatedAt(d); 
				user.setUpdatedBy(admin.getUsername());
				if (null != user.getResetPassword() && !"".equals(user.getResetPassword())) {
					String userPassword = Md5Util.encrypt(user.getResetPassword());
					user.setPassword(userPassword);
				}
				userService.updateByPrimaryKeySelective(user);
			}else{//新增
				String picPaths = user.getPicPaths();//如果没有上传拍卖机构图片,则使用用户的头像
				if(null != picPaths && !"".equals(picPaths)){
					user.setPicPaths(picPaths);
				}else{
//					int userId = user.getId();
//					 user = userService.selectByPrimaryKey(userId);
//					if(null != user){
//						auctionInst.setPicPaths(user.getAvatar());
//					}
				}
				if (null != user.getResetPassword() && !"".equals(user.getResetPassword())) {
					String userPassword = Md5Util.encrypt(user.getResetPassword());
					user.setPassword(userPassword);
				}
				user.setCreatedAt(d); 
				user.setSerial(CommonGenerator.getSerialByDate("u"));
				user.setCreatedBy(admin.getUsername());
				userService.insert(user);
			}
			json.put("status", "success");
			json.put("msg", "操作成功");
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateUser", json);
		}
		return json;
	}

	/**
	 * 逻辑删除用户数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserDelete", method=RequestMethod.POST)
	public JSONObject updateUserDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			userService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateUserDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除用户
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUserThorough",method=RequestMethod.POST)
	public JSONObject deleteUserThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			userService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteUserThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复用户（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserRecover", method=RequestMethod.POST)
	public JSONObject updateUserRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			userService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateUserRecover", json);
		}
		return json;
	}
	
	//通过指定id查询用户名
	@ResponseBody
	@RequestMapping(value = "/getusernameArray", method=RequestMethod.POST)
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


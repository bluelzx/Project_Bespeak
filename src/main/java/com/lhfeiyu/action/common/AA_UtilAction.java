package com.lhfeiyu.action.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lhfeiyu.config.base.BasePage;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-通用：通用方法 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Controller
public class AA_UtilAction {
	
	/*@Autowired
	private AA_UtilService service;
	
	private static Logger logger = Logger.getLogger("R");*/
	
	/**
	 * 后台通用页面访问（1层）
	 * @param page 页面名称
	 * @return ModelAndView '/back/{page}'
	 */
	@RequestMapping(value = "/back/pages/{page}", method = RequestMethod.GET)
	public ModelAndView backCommonPage1(@PathVariable String page) {
		return new ModelAndView("/back/"+page);
	}
	
	/**
	 * 后台通用页面访问（2层）
	 * @param folder 上级名称
	 * @param page 页面名称
	 * @return ModelAndView '/back/{folder}/{page}'
	 */
	@RequestMapping(value = "/back/pages/{folder}/{page}", method = RequestMethod.GET)
	public ModelAndView backCommonPage2(@PathVariable String folder, @PathVariable String page) {
		return new ModelAndView("/back/"+folder+"/"+page);
	}
	
	/**
	 * 前端通用页面访问（1层）
	 * @param page 页面名称
	 * @return ModelAndView '/{page}'
	 */
	@RequestMapping(value = "/pages/{page}", method = RequestMethod.GET)
	public ModelAndView frontCommonPage1(@PathVariable String page) {
		return new ModelAndView("/"+page);
	}
	
	/**
	 * 前端通用页面访问（2层）
	 * @param folder 上级名称
	 * @param page 页面名称
	 * @return ModelAndView '/{folder}/{page}'
	 */
	@RequestMapping(value={"/pages/{folder}/{page}.html","/pages/{folder}/{page}"}, method = RequestMethod.GET)
	public ModelAndView frontCommonPage2(@PathVariable String folder, @PathVariable String page) {
		return new ModelAndView("/"+folder+"/"+page);
	}
	
	/**
	 * 跳转到前端登陆页面
	 * @return ModelAndView PagePath.jumpToLogin
	 */
	@RequestMapping(value = "/jumpToLogin")
	public ModelAndView jumpToLogin() {
		return new ModelAndView(BasePage.jumpToLogin, null);
	}
	
	/**
	 * 跳转到后台登陆页面
	 * @return ModelAndView PagePath.jumpToBackLogin
	 */
	@RequestMapping(value = "/jumpToBackLogin")
	public ModelAndView jumpToBackLogin() {
		return new ModelAndView(BasePage.back_jumpToBackLogin, null);
	}
	
	/**
	 * 没有任何控制器方法匹配成功，则重定向到首页
	 * @return ModelAndView "redirect:/index"
	 */
	@RequestMapping(value = "*")
	public ModelAndView page404() {
		 return new ModelAndView("redirect:/index");
	}
	
	/*
	 * 考虑到安全因素，暂时不开通数据库操作的通用方法
	@ResponseBody
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	public JSONObject deleteById(@RequestParam("id") Integer id,@RequestParam("table") String table) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			map.put("id", id);
			map.put("username", "admin");
			service.deleteById(map);
			json.put("status", "success");
			json.put("msg", "强制删除成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_删除表："+table+",ID："+id+" 出现异常_", json);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
	public JSONObject deleteByIds(@RequestParam("ids") String ids,@RequestParam("table") String table) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			map.put("ids", ids);
			map.put("username", "admin");
			service.deleteByIds(map);
			json.put("status", "success");
			json.put("msg", "强制删除成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_删除表："+table+",ID串："+ids+" 出现异常_", json);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateDeletedNowById", method = RequestMethod.POST)
	public JSONObject updateDeletedNowById(@RequestParam("id") Integer id,@RequestParam("table") String table) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			map.put("id", id);
			map.put("username", "admin");
			service.updateDeletedNowById(map);
			json.put("status", "success");
			json.put("msg", "删除成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_更新删除标识-删除，表："+table+",ID："+id+" 出现异常_", json);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateDeletedNowByIds", method = RequestMethod.POST)
	public JSONObject updateDeletedNowByIds(@RequestParam("ids") String ids,@RequestParam("table") String table) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			map.put("ids", ids);
			map.put("username", "admin");
			service.updateDeletedNowByIds(map);
			json.put("status", "success");
			json.put("msg", "删除成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_更新删除标识-删除，表："+table+",ID串："+ids+" 出现异常_", json);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateDeletedNullById", method = RequestMethod.POST)
	public JSONObject updateDeletedNullById(@RequestParam("id") Integer id,@RequestParam("table") String table) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			map.put("id", id);
			map.put("username", "admin");
			service.updateDeletedNullById(map);
			json.put("status", "success");
			json.put("msg", "恢复成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_更新删除标识-恢复，表："+table+",ID："+id+" 出现异常_", json);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateDeletedNullByIds", method = RequestMethod.POST)
	public JSONObject updateDeletedNullByIds(@RequestParam("ids") String ids,@RequestParam("table") String table) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			map.put("ids", ids);
			map.put("username", "admin");
			service.updateDeletedNullByIds(map);
			json.put("status", "success");
			json.put("msg", "恢复成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_更新删除标识-恢复，表："+table+",ID串："+ids+" 出现异常_", json);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateMainStatusById", method = RequestMethod.POST)
	public JSONObject updateMainStatusById(@RequestParam("id") Integer id,@RequestParam("table") String table) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			map.put("id", id);
			map.put("username", "admin");
			service.updateMainStatusById(map);
			json.put("status", "success");
			json.put("msg", "更新成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_更新业务状态，表："+table+",ID："+id+" 出现异常_", json);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateMainStatusByIds", method = RequestMethod.POST)
	public JSONObject updateMainStatusByIds(@RequestParam("ids") String ids,@RequestParam("table") String table) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			map.put("ids", ids);
			map.put("username", "admin");
			service.updateMainStatusByIds(map);
			json.put("status", "success");
			json.put("msg", "更新成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_更新业务状态，表："+table+",ID串："+ids+" 出现异常_", json);
		}
		return json;
	}*/
	

}

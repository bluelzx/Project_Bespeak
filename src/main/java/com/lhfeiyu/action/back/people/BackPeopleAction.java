package com.lhfeiyu.action.back.people;

import java.util.HashMap;
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
import com.lhfeiyu.po.domain.Apply;
import com.lhfeiyu.po.domain.Dict;
import com.lhfeiyu.po.domain.People;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.service.domain.PeopleService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：月嫂信息 People <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 修改时间：</strong> 2016年8月3日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.people.BackPeopleAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackPeopleAction {
	
	@Autowired
	private PeopleService peopleService;
	@Autowired
	private DictService dictService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台月嫂信息页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/people")
	public ModelAndView people(ModelMap modelMap){
		String path = LhPage.backpeople;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/people", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
//	/**
//	 * 新增修改月嫂信息
//	 * @param modelMap
//	 * @param typeId 类型ID
//	 * @param peopleId 月嫂信息ID
//	 * @param operation String 操作
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value="/page/admentAddUpdate")
//	public ModelAndView peopleAddUpdate(ModelMap modelMap,
//			@RequestParam(required=false) Integer typeId,
//			@RequestParam(required=false) Integer peopleId,
//			@RequestParam(required=false) String operation){
//		String path = LhPage.back_admentAddUpdate;
//		try{
//			modelMap.put("typeId", typeId);
//			if(Check.isNotNull(peopleId)){
//				modelMap.put("peopleId", peopleId);
//				People people = peopleService.selectByPrimaryKey(peopleId);
//				if(null != people){
//					modelMap.put("people", people);
//				}
//			}
//			if(Check.isNotNull(operation)){
//				modelMap.put("operation", operation);
//			}
//		}catch(Exception e){
//			path = LhPage.back_error;
//			Result.catchError(e, logger, this.getClass().getName()+"/back/page/admentAddUpdate", modelMap);
//		}
//		return new ModelAndView(path, modelMap);
//	}
//	
//	/**
//	 * 新增修改月嫂信息（根据ID加载月嫂信息存入modelMap）
//	 * @param modelMap
//	 * @param peopleId 月嫂信息ID
//	 * @return ModelAndView
//	 */
//	@RequestMapping(value="/page/peopleAddUpdate/{peopleId}")
//	public ModelAndView peopleAddUpdate(ModelMap modelMap, @PathParam(value="peopleId") Integer peopleId){
//		String path = LhPage.back_admentAddUpdate;
//		try{
//			People people = peopleService.selectByPrimaryKey(peopleId);
//			if(null != people){
//				modelMap.put("people", people);
//			}
//		}catch(Exception e){
//			path = LhPage.back_error;
//			Result.catchError(e, logger, this.getClass().getName()+"/page/peopleAddUpdate/{peopleId}", modelMap);
//		}
//		return new ModelAndView(path,modelMap);
//	}
	
	@RequestMapping(value="/page/people/{peopleId}")
	public ModelAndView peopleDetail(ModelMap modelMap,@PathParam(value = "peopleId") Integer peopleId){
		String path = LhPage.back_admentDetail;
		try{
			People people = peopleService.selectByPrimaryKey(peopleId);
			if(null != people){
				modelMap.put("people", people);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/page/people/{peopleId}", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载月嫂信息列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getPeopleList",method=RequestMethod.POST)
	public JSONObject getPeopleList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
//			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
//			map.put("parentCodeNotNull", 1);//不查跟节点
//			peopleService.getPeopleList(json, map);

			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			String ascOrdesc = request.getParameter("ascOrdesc");
			if(null != ascOrdesc){
				if(ascOrdesc.equals("1")){
					map.put("orderBy", "expiry_date_to");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("2")){
					map.put("orderBy", "expiry_date_to");
					map.put("ascOrdesc", "DESC");
				}else if(ascOrdesc.equals("3")){
					map.put("orderBy", "created_at");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("4")){
					map.put("orderBy", "created_at");
					map.put("ascOrdesc", "DESC");
				}else if(ascOrdesc.equals("5")){
					map.put("orderBy", "price");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("6")){
					map.put("orderBy", "price");
					map.put("ascOrdesc", "DESC");
				}else if(ascOrdesc.equals("7")){
					map.put("orderBy", "hits");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("8")){
					map.put("orderBy", "hits");
					map.put("ascOrdesc", "DESC");
				}
			}
			peopleService.getPeopleList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getPeopleList", json);
		}
		return json;
	}
	
	/**后台
	 * 新增或修改月嫂信息（新增和修改方法对应Serivce中的不同方法）
	 * @param people ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdatePeople", method = RequestMethod.POST)
	public JSONObject addOrUpdatePeople(@ModelAttribute People people,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
//			System.out.println(people.getId());
//			People people2= peopleService.selectByPrimaryKey(people.getId());
			Dict dict = dictService.getDictValueByCode1(people.getTypeCode());//通过代码取到dict对应一条数据
//			System.out.println("一条dict+code:"+dict);
//			Dict dict = dictService.selectByPrimaryKey(Integer.parseInt(people.getTitleCode()));//通过代码取到dict对应一条数据
			people.setPositionNames(dict.getCodeName());//根据改变后的code拿到类型名并赋值到职位名
			people.setTitleNames(dict.getCodeName());//
			people.setTypeId(dict.getId());
			//			System.out.println(dict.getCodeName());//拿到类型名
			peopleService.addUpdatePeople(json, people, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdatePeople", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改月嫂信息（新增和修改方法对应Serivce中的不同方法）
	 * @param people ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdatePeople", method = RequestMethod.POST)
	public JSONObject addUpdatePeople(@ModelAttribute People people,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			peopleService.addUpdatePeople(json, people, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdatePeople", json);
		}
		return json;
	}


	/**
	 * 逻辑删除月嫂信息数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePeopleDelete", method=RequestMethod.POST)
	public JSONObject updatePeopleDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			peopleService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updatePeopleDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除月嫂信息
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deletePeopleThorough",method=RequestMethod.POST)
	public JSONObject deletePeopleThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			peopleService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deletePeopleThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复月嫂信息（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePeopleRecover", method=RequestMethod.POST)
	public JSONObject updatePeopleRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			peopleService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updatePeopleRecover", json);
		}
		return json;
	}
	/*密码的重置
	 * */
	@ResponseBody
	@RequestMapping(value = "/updatePeoplePassword",method=RequestMethod.POST)
	public JSONObject updateUserPassword(HttpServletRequest request,@RequestParam Integer id,
			@RequestParam String password) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			if(null == admin)return Result.adminSessionInvalid(json);
			if(Check.isNull(password) || Check.isNull(id)){
				return Result.failure(json, "请输入新密码", "password_null");
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			map.put("expression1","password = '"+Md5Util.encrypt(password)+"'");
			peopleService.updateFieldById(map);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-User-AJAX-/back/updatePeoplePassword-修改月嫂密码出现异常", json);
		}
		return json;
	}
	

}


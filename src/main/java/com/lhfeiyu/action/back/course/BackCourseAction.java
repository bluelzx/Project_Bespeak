package com.lhfeiyu.action.back.course;

import java.math.BigDecimal;
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
import com.lhfeiyu.po.domain.Course;
import com.lhfeiyu.service.base.BaseCourseService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：培训课程 Course <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.course.BackCourseAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackCourseAction {
	
	@Autowired
	private BaseCourseService courseService;
	@Autowired
	private DictService dictService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台培训课程页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	
	@RequestMapping(value="/page/course")
	public ModelAndView course(ModelMap modelMap){
		String path = LhPage.back_course;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/course", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	
	/**
	 * 加载培训课程列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getCourseList",method=RequestMethod.POST)
	public JSONObject getCourseList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			System.out.println("查询："+map);
			courseService.getCourseListSimple(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getCourseList", json);
		}
		System.out.println(json.toJSONString());
		return json;
	}
	
	
	/**
	 * 加载培训课程类型下拉列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getCourseTypeList",method=RequestMethod.POST)
	public JSONArray getCourseTypeList(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			array=dictService.getDictArray(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 新增或修改培训课程（新增和修改方法对应Serivce中的不同方法）
	 * @param course ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateCourse", method = RequestMethod.POST)
	public JSONObject addUpdateCourse(@ModelAttribute Course course,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的course，存在即返回
			if(null != course.getId()){//更新
				course.setUpdatedAt(d); 
				course.setUpdatedBy(admin.getUsername());
				courseService.updateByPrimaryKeySelective(course);
			}else{//新增
				course.setCreatedAt(d); 
				course.setSerial(CommonGenerator.getSerialByDate("c"));
				course.setCreatedBy(admin.getUsername());
				courseService.insert(course);
			}
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateCourse", json);
		}
		return Result.success(json);
	}

	/**
	 * 逻辑删除培训课程数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCourseDelete", method=RequestMethod.POST)
	public JSONObject updateCourseDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的course，存在即返回
			courseService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateCourseDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除培训课程
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCourseThorough",method=RequestMethod.POST)
	public JSONObject deleteCourseThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的course，存在即返回
			courseService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteCourseThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复培训课程（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCourseRecover", method=RequestMethod.POST)
	public JSONObject updateCourseRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的course，存在即返回
			courseService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateCourseRecover", json);
		}
		return json;
	}
	
	//通过指定id查询培训课程名
	@ResponseBody
	@RequestMapping(value = "/getcoursenameArray", method=RequestMethod.POST)
	public JSONArray getcoursenameArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Course> coursenameList = courseService.selectListByCondition(map);
			for(Course h:coursenameList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
				json.put("name",h.getName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getcoursenameArray-加载作者名列表出现异常", array);
		}
		return array;
	}
	//通过指定id查询培训课程名
	@ResponseBody
	@RequestMapping(value = "/getCourse", method=RequestMethod.POST)
	public JSONObject getCourse(HttpServletRequest request,@RequestParam Integer id) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			Course course = courseService.selectByCondition(map);
			json.put("rows", course);
			Result.success(json);
			System.out.println(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getcoursenameArray-加载作者名列表出现异常", json);
		}
		return json;
	}

}


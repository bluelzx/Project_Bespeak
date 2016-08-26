package com.lhfeiyu.action.back.sys;

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
import com.lhfeiyu.po.domain.ProvinceCityArea;
import com.lhfeiyu.service.domain.ProvinceCityAreaService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：省市区 ProvinceCityArea <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.sys.BackProvinceCityAreaAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackProvinceCityAreaAction {
	
	@Autowired
	private ProvinceCityAreaService provinceCityAreaService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台省市区页面
	 * @param modelMap
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/provinceCityArea")
	public ModelAndView provinceCityArea(ModelMap modelMap){
		String path = LhPage.back_provinceCityArea;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-ProvinceCityArea-PAGE-/back/page/provinceCityArea-加载省市区页面出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载省市区列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getProvinceCityAreaList",method=RequestMethod.POST)
	public JSONObject getProvinceCityAreaList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			provinceCityAreaService.getProvinceCityAreaList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-ProvinceCityArea-AJAX-/back/getProvinceCityAreaList-加省市区列表出现异常", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改省市区（新增和修改方法对应Serivce中的不同方法）
	 * @param provinceCityArea ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateProvinceCityArea", method = RequestMethod.POST)
	public JSONObject addUpdateProvinceCityArea(@ModelAttribute ProvinceCityArea provinceCityArea,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			provinceCityAreaService.addUpdateProvinceCityArea(json, provinceCityArea, username);
		}catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-ProvinceCityArea-AJAX-/back/addUpdateProvinceCityArea-添加或修改省市区出现异常", json);
		}
		return json;
	}
	
	/**
	 * 逻辑删除省市区数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateProvinceCityAreaDelete",method=RequestMethod.POST)
	public JSONObject updateProvinceCityAreaDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			provinceCityAreaService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-ProvinceCityArea-AJAX-/back/updateProvinceCityAreaDelete-删除省市区出现异常", json);
		}
		return json;
	}
	
	/**
	 * 物理删除省市区
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteProvinceCityAreaThorough",method=RequestMethod.POST)
	public JSONObject deleteProvinceCityAreaThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			provinceCityAreaService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-ProvinceCityArea-AJAX-/back/deleteProvinceCityAreaThorough-彻底删除省市区出现异常", json);
		}
		return json;
	}
	
	/**
	 * 恢复省市区（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateProvinceCityAreaRecover",method=RequestMethod.POST)
	public JSONObject updateProvinceCityAreaRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			provinceCityAreaService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-ProvinceCityArea-AJAX-/back/updateProvinceCityAreaRecover-恢复省市区出现异常", json);
		}
		return json;
	}
	
	/**
	 * 加载所有的省市区
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllProvinceCityAreaArray")
	public JSONArray getAllProvinceCityAreaArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			array = provinceCityAreaService.getProvinceCityAreaArray(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 加载省市区的根节点
	 * @param request
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getRootProvinceCityAreaArray")
	public JSONArray getRootProvinceCityAreaArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentIdNull", 1);
			array = provinceCityAreaService.getProvinceCityAreaArray(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 根据父类型加载省市区，返回数组，一般用于下拉列表
	 * @param request
	 * @param parentCode
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getProvinceCityAreaArrayByParentId")
	public JSONArray getProvinceCityAreaArrayByParentId(HttpServletRequest request, @RequestParam Integer parentId) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = CommonGenerator.getHashMap();
			map.put("parentId", parentId);
			array = provinceCityAreaService.getProvinceCityAreaArray(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	@ResponseBody
	@RequestMapping(value="/getProvince")
	public JSONArray getProvince(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentIdNull", 1); 
			List<ProvinceCityArea> provinceList = provinceCityAreaService.selectListByCondition(map);
			for(ProvinceCityArea a : provinceList){
				JSONObject obj = new JSONObject();
				obj.put("id", a.getId());
				obj.put("name", a.getAreaName());
//				System.out.println("00"+a.getAreaName());
				array.add(obj);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载省市（省）出现异常_", array);
		}
		return array;
	}
	@ResponseBody
	@RequestMapping(value="/getCity")
	public JSONArray getCity(HttpServletRequest request,@RequestParam Integer provinceId) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentId", provinceId);
			map.put("parentIdNotNull", 1);
			List<ProvinceCityArea> cityList = provinceCityAreaService.selectListByCondition(map);
			for(ProvinceCityArea a : cityList){
				JSONObject obj = new JSONObject();
				obj.put("id", a.getId());
				obj.put("name", a.getAreaName());
				array.add(obj);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载省市（市）出现异常_", array);
		}
		return array;
	}

}

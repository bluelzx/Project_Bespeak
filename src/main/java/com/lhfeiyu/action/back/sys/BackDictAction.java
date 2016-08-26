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
import com.lhfeiyu.po.domain.Dict;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：数据字典 Dict <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.sys.BackDictAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackDictAction {
	
	@Autowired
	private DictService dictService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台数据字典页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/dict")
	public ModelAndView dict(ModelMap modelMap, @RequestParam(required = false) String parentCode){
		String path = LhPage.back_dict;
		JSONObject json = new JSONObject();
		try{
			json.put("parentCode", parentCode);
			modelMap.put("paramJson", json);
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-Dict-PAGE-/back/page/dict-加载数据字典页面出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载数据字典列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getDictList",method=RequestMethod.POST)
	public JSONObject getDictList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			dictService.getDictList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Dict-AJAX-/back/getDictList-加载数据字典列表出现异常", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改数据字典（新增和修改方法对应Serivce中的不同方法）
	 * @param dict ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateDict", method = RequestMethod.POST)
	public JSONObject addUpdateDict(@ModelAttribute Dict dict,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			dictService.addUpdateDict(json, dict, username);
		}catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Dict-AJAX-/back/addUpdateDict-添加或修改数据字典出现异常", json);
		}
		return json;
	}
	
	/**
	 * 逻辑删除数据字典数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateDictDelete",method=RequestMethod.POST)
	public JSONObject updateDictDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			Map<String,Object> map = CommonGenerator.getHashMap();
			map.put("dictIds", ids);
			map.put("authority", 3);//只读
			int count = dictService.selectCountByCondition(map);
			if(count > 0){
				return Result.failure(json, "不能删除权限为只读的数据", "authority_readonly");
			}
			dictService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Dict-AJAX-/back/updateDictDelete-删除数据字典出现异常", json);
		}
		return json;
	}
	
	/**
	 * 物理删除数据字典
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteDictThorough",method=RequestMethod.POST)
	public JSONObject deleteDictThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			Map<String,Object> map = CommonGenerator.getHashMap();
			map.put("dictIds", ids);
			map.put("authority", 3);//只读
			int count = dictService.selectCountByCondition(map);
			if(count > 0){
				return Result.failure(json, "不能删除权限为只读的数据", "authority_readonly");
			}
			dictService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Dict-AJAX-/back/deleteDictThorough-彻底删除数据字典出现异常", json);
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
	@RequestMapping(value = "/updateDictRecover",method=RequestMethod.POST)
	public JSONObject updateDictRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			dictService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Dict-AJAX-/back/updateDictRecover-恢复数据字典出现异常", json);
		}
		return json;
	}
	
	/**
	 * 加载所有的数据字典
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllDictArray")
	public JSONArray getAllDictArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			array = dictService.getDictArray(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 加载数据字典的根节点
	 * @param request
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getRootDictArray")
	public JSONArray getRootDictArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentCodeNull", 1);
			array = dictService.getDictArray(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 加载可扩展的数据字典数组
	 * @param request
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getRootDictArrayForExpand")
	public JSONArray getRootDictArrayForExpand(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentCodeNull", 1);
			map.put("canExpand", 1);//允许扩展
			array = dictService.getDictArray(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 根据父类型加载数据字典，返回数组，一般用于下拉列表
	 * @param request
	 * @param parentCode
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getDictArrayByParentCode")
	public JSONArray getDictArrayByParentCode(HttpServletRequest request, @RequestParam String parentCode) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = CommonGenerator.getHashMap();
			map.put("parentCode", parentCode);
			array = dictService.getDictArray(map);
			System.out.println("类型"+array);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
//	@RequestMapping(value="/page/dict/id")
//		public ModelAndView activityDetail1(ModelMap modelMap,HttpServletRequest request){
//			String path = LhPage.back_TypeFromDict;
//			try{
//				
//			}catch(Exception e){
//				path = LhPage.back_error;
//				Result.catchError(e, logger, "LH_ERROR-Dict-PAGE-/back/page/dict-加载数据字典页面出现异常", modelMap);
//			}
//			return new ModelAndView(path,modelMap);
//		}
	/**
	 * 加载文章类型
	 * @param request
	 * @param parentCode
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getArticleTypeArray", method=RequestMethod.POST)
	public JSONArray getArticleTypeArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentCode", "article_type");
			map.put("orderBy", "id");
			map.put("ascOrdesc", "asc");
			List<Dict> dictList = dictService.selectListByCondition(map);
			for(Dict d:dictList){
				JSONObject json = new JSONObject();
				json.put("id", d.getId());
				json.put("name", d.getCodeName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Dict-AJAX-/back/getArticleTypeArray-加载数据字典文章类型列表出现异常", array);
		}
		return array;
	}
	
	/**
	 * 通过typecode加载月嫂类型
	 * @param request
	 * @param parentCode
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getPeopletypeCodeTypeArray", method=RequestMethod.POST)
	public JSONArray getPeopletypeCodeTypeArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentCode", "people_typeCode");
			map.put("orderBy", "id");
			map.put("ascOrdesc", "asc");
			List<Dict> dictList = dictService.selectListByCondition(map);
			for(Dict d:dictList){
				JSONObject json = new JSONObject();
				json.put("code", d.getCode());
				json.put("name", d.getCodeName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Dict-AJAX-/back/getPeopletypeCodeTypeArray-加载数据字典月嫂类型列表出现异常", array);
		}
		return array;
	}

//	/**
//	 * 通过titlecode加载月嫂类型
//	 * @param request
//	 * @param parentCode
//	 * @return JSONArray
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/getPeopletitleCodeTypeArray", method=RequestMethod.POST)
//	public JSONArray getPeopletitleCodeTypeArray(HttpServletRequest request) {
//		JSONArray array = new JSONArray();
//		try {
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("parentCode", "people_typeCode");
//			map.put("orderBy", "id");
//			map.put("ascOrdesc", "asc");
//			List<Dict> dictList = dictService.selectListByCondition(map);
//			for(Dict d:dictList){
//				JSONObject json = new JSONObject();
//				json.put("id", d.getId());
//				json.put("name", d.getCodeName());
//				array.add(json);
//			}
//		} catch (Exception e) {
//			Result.catchError(e, logger, "LH_ERROR-Dict-AJAX-/back/getArticleTypeArray-加载数据字典文章类型列表出现异常", array);
//		}
//		return array;
//	}

}

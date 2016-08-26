package com.lhfeiyu.action.back.course;

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
import com.lhfeiyu.po.domain.Investigation;
import com.lhfeiyu.service.base.BaseInvestigationService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：申请报名 Investigation <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.investigation.BackInvestigationAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackInvestigationAction {
	
	@Autowired
	private BaseInvestigationService investigationService;
	@Autowired
	private DictService dictService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台申请报名页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/investigation")
	public ModelAndView investigation(ModelMap modelMap){
		String path = LhPage.back_investigation;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/investigation", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	
	/**
	 * 加载申请报名列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getInvestigationList",method=RequestMethod.POST)
	public JSONObject getInvestigationList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			System.out.println("查询："+map);
			investigationService.getInvestigationList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getInvestigationList", json);
		}
		System.out.println(json);
		return json;
	}
	
	
	/**
	 * 加载申请报名类型下拉列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getInvestigationTypeList",method=RequestMethod.POST)
	public JSONArray getInvestigationTypeList(HttpServletRequest request) {
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
	 * 新增或修改申请报名（新增和修改方法对应Serivce中的不同方法）
	 * @param investigation ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateInvestigation", method = RequestMethod.POST)
	public JSONObject addUpdateInvestigation(@ModelAttribute Investigation investigation,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的investigation，存在即返回
			if(null != investigation.getId()){//更新
				investigation.setUpdatedAt(d); 
				investigation.setUpdatedBy(admin.getUsername());
				
				investigationService.updateByPrimaryKeySelective(investigation);
			}else{//新增
				String picPaths = investigation.getUserAvatar();
				if(null != picPaths && !"".equals(picPaths)){
					investigation.setUserAvatar(picPaths);
				}else{
//					int investigationId = investigation.getId();
//					 investigation = investigationService.selectByPrimaryKey(investigationId);
//					if(null != investigation){
//						auctionInst.setPicPaths(investigation.getAvatar());
//					}
				}
				investigation.setCreatedAt(d); 
				investigation.setSerial(CommonGenerator.getSerialByDate("i"));
				investigation.setCreatedBy(admin.getUsername());
				investigationService.insert(investigation);
			}
			json.put("status", "success");
			json.put("msg", "操作成功");
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateInvestigation", json);
		}
		return json;
	}

	/**
	 * 逻辑删除申请报名数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateInvestigationDelete", method=RequestMethod.POST)
	public JSONObject updateInvestigationDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的investigation，存在即返回
			investigationService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateInvestigationDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除申请报名
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteInvestigationThorough",method=RequestMethod.POST)
	public JSONObject deleteInvestigationThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的investigation，存在即返回
			investigationService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteInvestigationThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复申请报名（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateInvestigationRecover", method=RequestMethod.POST)
	public JSONObject updateInvestigationRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的investigation，存在即返回
			investigationService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateInvestigationRecover", json);
		}
		return json;
	}
	
	//通过指定id查询申请报名名
	@ResponseBody
	@RequestMapping(value = "/getinvestigationnameArray", method=RequestMethod.POST)
	public JSONArray getinvestigationnameArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Investigation> investigationnameList = investigationService.selectListByCondition(map);
			for(Investigation h:investigationnameList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
				json.put("name",h.getUsername());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getinvestigationnameArray-加载作者名列表出现异常", array);
		}
		return array;
	}

}


package com.lhfeiyu.action.back.message;

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
import com.lhfeiyu.po.domain.Advertisement;
import com.lhfeiyu.service.domain.AdvertisementService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：广告 Advertisement <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 张领国<p>
 * <strong> 编写时间：</strong> 2016年8月2日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.advertisement.BackAdvertisementAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackAdvertisementAction {
	
	@Autowired
	private AdvertisementService advertisementService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台广告页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/advertisement")
	public ModelAndView advertisement(ModelMap modelMap){
		String path = LhPage.back_advertisement;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/advertisement", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 新增修改广告
	 * @param modelMap
	 * @param typeId 类型ID
	 * @param advertisementId 广告ID
	 * @param operation String 操作
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/admentAddUpdate")
	public ModelAndView advertisementAddUpdate(ModelMap modelMap,
			@RequestParam(required=false) Integer typeId,
			@RequestParam(required=false) Integer advertisementId,
			@RequestParam(required=false) String operation){
		String path = LhPage.back_admentAddUpdate;
		try{
			modelMap.put("typeId", typeId);
			if(Check.isNotNull(advertisementId)){
				modelMap.put("advertisementId", advertisementId);
				Advertisement advertisement = advertisementService.selectByPrimaryKey(advertisementId);
				if(null != advertisement){
					modelMap.put("advertisement", advertisement);
				}
			}
			if(Check.isNotNull(operation)){
				modelMap.put("operation", operation);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/admentAddUpdate", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 新增修改广告（根据ID加载广告存入modelMap）
	 * @param modelMap
	 * @param advertisementId 广告ID
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/advertisementAddUpdate/{advertisementId}")
	public ModelAndView advertisementAddUpdate(ModelMap modelMap, @PathParam(value="advertisementId") Integer advertisementId){
		String path = LhPage.back_admentAddUpdate;
		try{
			Advertisement advertisement = advertisementService.selectByPrimaryKey(advertisementId);
			if(null != advertisement){
				modelMap.put("advertisement", advertisement);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/page/advertisementAddUpdate/{advertisementId}", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/page/advertisement/{advertisementId}")
	public ModelAndView advertisementDetail(ModelMap modelMap,@PathParam(value = "advertisementId") Integer advertisementId){
		String path = LhPage.back_admentDetail;
		try{
			Advertisement advertisement = advertisementService.selectByPrimaryKey(advertisementId);
			if(null != advertisement){
				modelMap.put("advertisement", advertisement);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/page/advertisement/{advertisementId}", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载广告列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getAdvertisementList",method=RequestMethod.POST)
	public JSONObject getAdvertisementList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
//			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
//			map.put("parentCodeNotNull", 1);//不查跟节点
//			advertisementService.getAdvertisementList(json, map);

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
			advertisementService.getAdvertisementList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getAdvertisementList", json);
		}
		return json;
	}
	
	/**后台
	 * 新增或修改广告（新增和修改方法对应Serivce中的不同方法）
	 * @param advertisement ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateAdvertisement", method = RequestMethod.POST)
	public JSONObject addOrUpdateAdvertisement(@ModelAttribute Advertisement advertisement,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			advertisementService.addUpdateAdvertisement(json, advertisement, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdateAdvertisement", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改广告（新增和修改方法对应Serivce中的不同方法）
	 * @param advertisement ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateAdvertisement", method = RequestMethod.POST)
	public JSONObject addUpdateAdvertisement(@ModelAttribute Advertisement advertisement,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			advertisementService.addUpdateAdvertisement(json, advertisement, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateAdvertisement", json);
		}
		return json;
	}


	/**
	 * 逻辑删除广告数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAdvertisementDelete", method=RequestMethod.POST)
	public JSONObject updateAdvertisementDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			advertisementService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateAdvertisementDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除广告
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAdvertisementThorough",method=RequestMethod.POST)
	public JSONObject deleteAdvertisementThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			advertisementService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteAdvertisementThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复广告（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAdvertisementRecover", method=RequestMethod.POST)
	public JSONObject updateAdvertisementRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			advertisementService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateAdvertisementRecover", json);
		}
		return json;
	}
	

}


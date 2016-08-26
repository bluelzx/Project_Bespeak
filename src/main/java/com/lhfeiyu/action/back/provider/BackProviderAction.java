package com.lhfeiyu.action.back.provider;

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
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.Provider;
import com.lhfeiyu.service.base.BaseProviderService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：技师 Provider <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.provider.BackProviderAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackProviderAction {
	
	@Autowired
	private BaseProviderService providerService;
	@Autowired
	private DictService dictService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台技师页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/provider")
	public ModelAndView provider(ModelMap modelMap){
		String path = LhPage.back_provider;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/provider", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	
	/**
	 * 加载技师列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getProviderList",method=RequestMethod.POST)
	public JSONObject getProviderList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			System.out.println("查询："+map);
			providerService.getProviderList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getProviderList", json);
		}
		System.out.println(json);
		return json;
	}
	
	
	/**
	 * 加载技师类型下拉列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getProviderTypeList",method=RequestMethod.POST)
	public JSONArray getProviderTypeList(HttpServletRequest request) {
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
	 * 新增或修改技师（新增和修改方法对应Serivce中的不同方法）
	 * @param provider ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateProvider", method = RequestMethod.POST)
	public JSONObject addUpdateProvider(@ModelAttribute Provider provider,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的provider，存在即返回
			String username = admin.getUsername();
			if(null != provider.getId()){//更新
				provider.setUpdatedAt(d); 
				provider.setUpdatedBy(admin.getUsername());
				if (null != provider.getResetPassword() && !"".equals(provider.getResetPassword())) {
					String userPassword = Md5Util.encrypt(provider.getResetPassword());
					provider.setPassword(userPassword);
				}
				providerService.updateByPrimaryKeySelective(provider);
			}else{//新增
				String picPaths = provider.getPicPaths();//如果没有上传拍卖机构图片,则使用技师的头像
				if(null != picPaths && !"".equals(picPaths)){
					provider.setPicPaths(picPaths);
				}else{
//					int providerId = provider.getId();
//					 provider = providerService.selectByPrimaryKey(providerId);
//					if(null != provider){
//						auctionInst.setPicPaths(provider.getAvatar());
//					}
				}
				if (null != provider.getResetPassword() && !"".equals(provider.getResetPassword())) {
					String userPassword = Md5Util.encrypt(provider.getResetPassword());
					provider.setPassword(userPassword);
				}
				provider.setCreatedAt(d); 
				provider.setSerial(CommonGenerator.getSerialByDate("p"));
				provider.setCreatedBy(admin.getUsername());
				provider.setFocusNum(0);
				providerService.addUpdateProvider(json, provider, username);
			}
			json.put("status", "success");
			json.put("msg", "操作成功");
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateProvider", json);
		}
		return json;
	}

	/**
	 * 逻辑删除技师数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateProviderDelete", method=RequestMethod.POST)
	public JSONObject updateProviderDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的provider，存在即返回
			providerService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateProviderDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除技师
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteProviderThorough",method=RequestMethod.POST)
	public JSONObject deleteProviderThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的provider，存在即返回
			providerService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteProviderThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复技师（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateProviderRecover", method=RequestMethod.POST)
	public JSONObject updateProviderRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的provider，存在即返回
			providerService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateProviderRecover", json);
		}
		return json;
	}
	
	//通过指定id查询技师名
	@ResponseBody
	@RequestMapping(value = "/getprovidernameArray", method=RequestMethod.POST)
	public JSONArray getprovidernameArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Provider> providernameList = providerService.selectListByCondition(map);
			for(Provider h:providernameList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
				json.put("name",h.getUsername());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getprovidernameArray-加载作者名列表出现异常", array);
		}
		return array;
	}
	//通过店铺取店铺下所有技师
	@ResponseBody
	@RequestMapping(value="/getproviders")
	public JSONArray getCity(HttpServletRequest request,@RequestParam Integer shopId) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("shopId", shopId);
			map.put("parentIdNotNull", 1);
			List<Provider> providerList = providerService.selectListByCondition(map);
			for(Provider a : providerList){
				JSONObject obj = new JSONObject();
				obj.put("id", a.getId());
				obj.put("name", a.getRealName());
				array.add(obj);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_按店铺id搜寻技师出现异常_", array);
		}
		return array;
	}

}


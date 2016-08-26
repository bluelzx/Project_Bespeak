package com.lhfeiyu.action.back.shop;

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
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.RequestUtil;


/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：加盟商 Shop <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.Shop.BackShopAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackShopAction {
	
	@Autowired
	private ShopService ShopService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台加盟商页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/shop")
	public ModelAndView Shop(ModelMap modelMap){
		String path = LhPage.backShop;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/Shop", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getShopArray", method=RequestMethod.POST)
	public JSONArray getShopArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Shop> ShopList = ShopService.selectListByCondition(map);
			for(Shop h:ShopList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
				json.put("name",h.getName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getShopArray-加载圈子数组列表出现异常", array);
		}
		return array;
	}
	
	
	
	/**
	 * 加载加盟商列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getShopList",method=RequestMethod.POST)
	public JSONObject getShopList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			String ascOrdesc = request.getParameter("ascOrdesc");
			if(null != ascOrdesc){
				if(ascOrdesc.equals("1")){
					map.put("orderBy", "created_at");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("2")){
					map.put("orderBy", "created_at");
					map.put("ascOrdesc", "DESC");
				}else if(ascOrdesc.equals("3")){
					map.put("orderBy", "score");
					map.put("ascOrdesc", "ASC");
				}else if(ascOrdesc.equals("4")){
					map.put("orderBy", "score");
					map.put("ascOrdesc", "DESC");
				}
			}
			ShopService.getShopListSimple(json, map);
//			System.out.println("json"+json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getShopList", json);
		}
		return json;
	}
	/*加盟商密码的重置
	 * */
	@ResponseBody
	@RequestMapping(value = "/updateShopPassword",method=RequestMethod.POST)
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
			ShopService.updateFieldById(map);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-User-AJAX-/back/updateShopPassword-修改加盟商密码出现异常", json);
		}
		return json;
	}
	/**
	 * 新增或修改加盟商（新增和修改方法对应Serivce中的不同方法）
	 * @param Shop ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateShop", method = RequestMethod.POST)
	public JSONObject addUpdateShop(@ModelAttribute Shop Shop,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			ShopService.addOrUpdateShopSimple(json, Shop, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateShop", json);
		}
		return json;
	}
	
	/**后台
	 * 新增或修改加盟商（新增和修改方法对应Serivce中的不同方法）
	 * @param Shop ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateShop", method = RequestMethod.POST)
	public JSONObject addOrUpdateShop(@ModelAttribute Shop Shop,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			ShopService.addOrUpdateShopSimple(json, Shop, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdateShop", json);
		}
		return json;
	}

	/**
	 * 逻辑删除加盟商数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateShopDelete", method=RequestMethod.POST)
	public JSONObject updateShopDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			ShopService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateShopDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除加盟商
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteShopThorough",method=RequestMethod.POST)
	public JSONObject deleteShopThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			ShopService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteShopThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复加盟商（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateShopRecover", method=RequestMethod.POST)
	public JSONObject updateShopRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			ShopService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateShopRecover", json);
		}
		return json;
	}
	

}


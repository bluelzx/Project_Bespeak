package com.lhfeiyu.action.back.marketing;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Coupon;
import com.lhfeiyu.service.base.BaseCouponService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：服务 Coupon <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.coupon.BackCouponAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackCouponAction {
	
	@Autowired
	private BaseCouponService couponService;
	@Autowired
	private DictService dictService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台服务页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/coupon")
	public ModelAndView coupon(ModelMap modelMap){
		String path = LhPage.back_coupon;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/coupon", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	
	/**
	 * 加载服务列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getCouponList",method=RequestMethod.POST)
	public JSONObject getCouponList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			System.out.println("查询："+map);
			couponService.getCouponListSimple(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getCouponList", json);
		}
		System.out.println(json.toJSONString());
		return json;
	}
	
	
	/**
	 * 加载服优惠券型下拉列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getCouponTypeList",method=RequestMethod.POST)
	public JSONArray getCouponTypeList(HttpServletRequest request) {
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
	 * 新增或修改服务（新增和修改方法对应Serivce中的不同方法）
	 * @param coupon ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateCoupon", method = RequestMethod.POST)
	public JSONObject addUpdateCoupon(@ModelAttribute Coupon coupon,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的coupon，存在即返回
			if(null != coupon.getId()){//更新
				coupon.setUpdatedAt(d); 
				coupon.setUpdatedBy(admin.getUsername());
				couponService.updateByPrimaryKeySelective(coupon);
			}else{//新增
				coupon.setCreatedAt(d); 
				coupon.setCode(CommonGenerator.getSerialByDate("cp"));
				coupon.setCreatedBy(admin.getUsername());
				couponService.insert(coupon);
			}
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateCoupon", json);
		}
		return Result.success(json);
	}

	/**
	 * 逻辑删除服务数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCouponDelete", method=RequestMethod.POST)
	public JSONObject updateCouponDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的coupon，存在即返回
			couponService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateCouponDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除服务
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCouponThorough",method=RequestMethod.POST)
	public JSONObject deleteCouponThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的coupon，存在即返回
			couponService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteCouponThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复服务（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCouponRecover", method=RequestMethod.POST)
	public JSONObject updateCouponRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的coupon，存在即返回
			couponService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateCouponRecover", json);
		}
		return json;
	}
	
	//通过指定id查询优惠码
	@ResponseBody
	@RequestMapping(value = "/getcouponnameArray/{suerId}/{shopId}", method=RequestMethod.POST)
	public JSONArray getcouponnameArray(HttpServletRequest request ,
			@PathVariable Integer suerId,@PathVariable Integer shopId) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", suerId);
			map.put("shopId", shopId);
			map.put("dealStatus", 1);
			List<Coupon> couponnameList = couponService.selectListByCondition(map);
			for(Coupon h:couponnameList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
				json.put("name",h.getName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getcouponnameArray-加载作者名列表出现异常", array);
		}
		return array;
	}
	//通过指定id查询服务名
	@ResponseBody
	@RequestMapping(value = "/getCoupon", method=RequestMethod.POST)
	public JSONObject getCoupon(HttpServletRequest request,@RequestParam Integer id) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			Coupon coupon = couponService.selectByCondition(map);
			json.put("rows", coupon);
			Result.success(json);
			System.out.println(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getcouponnameArray-加载作者名列表出现异常", json);
		}
		return json;
	}

}


package com.lhfeiyu.action.shop;

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
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.po.domain.Treatment;
import com.lhfeiyu.service.domain.TreatmentService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：疗程 Treatment <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.TreatmentAction <p>
 */
@Controller
@RequestMapping(value="/shop")
public class ShopTreatmentAction {
	
	@Autowired
	private TreatmentService treatmentService;
	@Autowired
	private DictService dictService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台疗程页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/treatment")
	public ModelAndView treatment(ModelMap modelMap,HttpServletRequest request){
		String path = LhPage.shop_treatment;
		try{
			Shop shop = ActionUtil.checkSession4Shop(request.getSession());//验证session中的treatment，存在即返回
			modelMap.put("shop", shop);
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/treatment", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	
	/**
	 * 加载疗程列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getTreatmentList",method=RequestMethod.POST)
	public JSONObject getTreatmentList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Shop shop = ActionUtil.checkSession4Shop(request.getSession());//验证session中的treatment，存在即返回
			Integer shopId=shop.getId();
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			map.put("shopId", shopId);
			List<Treatment> treatment= treatmentService.selectListByShopId(map);
			json.put("rows", treatment);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/getTreatmentList", json);
		}
		return Result.success(json);
	}
	
	
	/**
	 * 加载疗程类型下拉列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getTreatmentTypeList",method=RequestMethod.POST)
	public JSONArray getTreatmentTypeList(HttpServletRequest request) {
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
	 * 新增或修改疗程（新增和修改方法对应Serivce中的不同方法）
	 * @param treatment ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateTreatment", method = RequestMethod.POST)
	public JSONObject addUpdateTreatment(@ModelAttribute Treatment treatment,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Shop shop = ActionUtil.checkSession4Shop(request.getSession());//验证session中的treatment，存在即返回
			if(null != treatment.getId()){//更新
				treatment.setUpdatedAt(d); 
				treatment.setUpdatedBy(shop.getName());
				int groupNum=treatment.getGroupNum();
//				Double shopPrice=(treatment.getShopPrice()).doubleValue();
//				BigDecimal groupPrice=BigDecimal.valueOf(groupNum*shopPrice);
//				treatment.setGroupPrice(groupPrice);
				treatmentService.updateByPrimaryKeySelective(treatment);
			}else{//新增
				
				int groupNum=treatment.getGroupNum();
//				Double shopPrice=(treatment.getShopPrice()).doubleValue();
//				BigDecimal groupPrice=BigDecimal.valueOf(groupNum*shopPrice);
//				treatment.setGroupPrice(groupPrice);
				treatment.setCreatedAt(d); 
				treatment.setSerial(CommonGenerator.getSerialByDate("g"));
				treatment.setCreatedBy(shop.getName());
				treatmentService.insert(treatment);
			}
			json.put("status", "success");
			json.put("msg", "操作成功");
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/addUpdateTreatment", json);
		}
		return json;
	}

	private BigDecimal BigDecimal(Integer groupNum) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 逻辑删除疗程数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTreatmentDelete", method=RequestMethod.POST)
	public JSONObject updateTreatmentDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Shop shop = ActionUtil.checkSession4Shop(request.getSession());//验证session中的treatment，存在即返回
			treatmentService.updateDeletedNowByIds(ids, shop.getName());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/updateTreatmentDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除疗程
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteTreatmentThorough",method=RequestMethod.POST)
	public JSONObject deleteTreatmentThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Shop shop = ActionUtil.checkSession4Shop(request.getSession());//验证session中的treatment，存在即返回
			treatmentService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/deleteTreatmentThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复疗程（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTreatmentRecover", method=RequestMethod.POST)
	public JSONObject updateTreatmentRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Shop shop = ActionUtil.checkSession4Shop(request.getSession());//验证session中的treatment，存在即返回
			treatmentService.updateDeletedNullByIds(ids, shop.getName());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/updateTreatmentRecover", json);
		}
		return json;
	}
	
	//通过指定id查询疗程名
	@ResponseBody
	@RequestMapping(value = "/gettreatmentnameArray", method=RequestMethod.POST)
	public JSONArray gettreatmentnameArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Treatment> treatmentnameList = treatmentService.selectListByCondition(map);
			for(Treatment h:treatmentnameList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
				json.put("name",h.getTreatmentName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/gettreatmentnameArray-加载疗程名列表出现异常", array);
		}
		return array;
	}
	//通过指定id查询疗程名
	@ResponseBody
	@RequestMapping(value = "/getTreatment", method=RequestMethod.POST)
	public JSONObject getTreatment(HttpServletRequest request,@RequestParam Integer id) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			Treatment treatment = treatmentService.selectByCondition(map);
			json.put("rows", treatment);
			Result.success(json);
			System.out.println(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/gettreatmentnameArray-加载作者名列表出现异常", json);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping(value="/gettreatments")
	public JSONArray getCity(HttpServletRequest request,@RequestParam Integer shopId) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("shopId", shopId);
			map.put("parentIdNotNull", 1);
			List<Treatment> treatmentList = treatmentService.selectListByCondition(map);
			for(Treatment a : treatmentList){
				JSONObject obj = new JSONObject();
				obj.put("id", a.getId());
				obj.put("name", a.getTreatmentName());
				array.add(obj);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_按店铺id搜寻疗程treatment出现异常_", array);
		}
		return array;
	}

}


package com.lhfeiyu.action.back.goods;

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
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.ProvinceCityArea;
import com.lhfeiyu.service.base.BaseGoodsService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：服务 Goods <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.goods.BackGoodsAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackGoodsAction {
	
	@Autowired
	private BaseGoodsService goodsService;
	@Autowired
	private DictService dictService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台服务页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/goods")
	public ModelAndView goods(ModelMap modelMap){
		String path = LhPage.back_goods;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/goods", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	
	/**
	 * 加载服务列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getGoodsList",method=RequestMethod.POST)
	public JSONObject getGoodsList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			goodsService.getGoodsListSimple(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getGoodsList", json);
		}
		System.out.println(json.toJSONString());
		return json;
	}
	
	
	/**
	 * 加载服务类型下拉列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getGoodsTypeList",method=RequestMethod.POST)
	public JSONArray getGoodsTypeList(HttpServletRequest request) {
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
	 * @param goods ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateGoods", method = RequestMethod.POST)
	public JSONObject addUpdateGoods(@ModelAttribute Goods goods,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的goods，存在即返回
			if(null != goods.getId()){//更新
				goods.setUpdatedAt(d); 
				goods.setUpdatedBy(admin.getUsername());
				int groupNum=goods.getGroupNum();
				Double shopPrice=(goods.getShopPrice()).doubleValue();
				BigDecimal groupPrice=BigDecimal.valueOf(groupNum*shopPrice);
				goods.setGroupPrice(groupPrice);
				goodsService.updateByPrimaryKeySelective(goods);
			}else{//新增
				String picPaths = goods.getPicPaths();//如果没有上传拍卖机构图片,则使用服务的头像
				if(null != picPaths && !"".equals(picPaths)){
					goods.setPicPaths(picPaths);
				}else{
//					int goodsId = goods.getId();
//					 goods = goodsService.selectByPrimaryKey(goodsId);
//					if(null != goods){
//						auctionInst.setPicPaths(goods.getAvatar());
//					}
				}
				goods.setHits(0);
				goods.setCreatedAt(d); 
				goods.setSerial(CommonGenerator.getSerialByDate("g"));
				goods.setCreatedBy(admin.getUsername());
				goodsService.insert(goods);
			}
			json.put("status", "success");
			json.put("msg", "操作成功");
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateGoods", json);
		}
		return json;
	}

	private BigDecimal BigDecimal(Integer groupNum) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 逻辑删除服务数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateGoodsDelete", method=RequestMethod.POST)
	public JSONObject updateGoodsDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的goods，存在即返回
			goodsService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateGoodsDelete", json);
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
	@RequestMapping(value = "/deleteGoodsThorough",method=RequestMethod.POST)
	public JSONObject deleteGoodsThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的goods，存在即返回
			goodsService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteGoodsThorough", json);
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
	@RequestMapping(value = "/updateGoodsRecover", method=RequestMethod.POST)
	public JSONObject updateGoodsRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的goods，存在即返回
			goodsService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateGoodsRecover", json);
		}
		return json;
	}
	
	//通过指定id查询服务名
	@ResponseBody
	@RequestMapping(value = "/getgoodsnameArray", method=RequestMethod.POST)
	public JSONArray getgoodsnameArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Goods> goodsnameList = goodsService.selectListByCondition(map);
			for(Goods h:goodsnameList){
				JSONObject json = new JSONObject();
				json.put("id",h.getId());
				json.put("name",h.getGoodsName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getgoodsnameArray-加载服务名列表出现异常", array);
		}
		return array;
	}
	//通过指定id查询服务名
	@ResponseBody
	@RequestMapping(value = "/getGoods", method=RequestMethod.POST)
	public JSONObject getGoods(HttpServletRequest request,@RequestParam Integer id) {
		JSONObject json = new JSONObject();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			Goods goods = goodsService.selectByCondition(map);
			json.put("rows", goods);
			Result.success(json);
			System.out.println(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Hospital-AJAX-/back/getgoodsnameArray-加载作者名列表出现异常", json);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping(value="/getgoodss")
	public JSONArray getCity(HttpServletRequest request,@RequestParam Integer shopId) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("shopId", shopId);
			map.put("parentIdNotNull", 1);
			List<Goods> goodsList = goodsService.selectListByCondition(map);
			for(Goods a : goodsList){
				JSONObject obj = new JSONObject();
				obj.put("id", a.getId());
				obj.put("name", a.getGoodsName());
				array.add(obj);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_按店铺id搜寻服务goods出现异常_", array);
		}
		return array;
	}

}


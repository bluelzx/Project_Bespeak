package com.lhfeiyu.action.front.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.po.domain.ProvinceCityArea;
import com.lhfeiyu.service.domain.ProvinceCityAreaService;
import com.lhfeiyu.tools.Result;

@Controller
public class ProvinceCityAreaAction {
	@Autowired
	private ProvinceCityAreaService service;
	
	private static Logger logger = Logger.getLogger("R");
	
	@ResponseBody
	@RequestMapping(value="/getProvince")
	public JSONArray getProvince(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentIdNull", 1); 
			List<ProvinceCityArea> provinceList = service.selectListByCondition(map);
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
	@RequestMapping(value="/getCitys")
	public JSONArray getCitys(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentIdNotNull", 1);
			List<ProvinceCityArea> cityList = service.selectListByCondition(map);
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
	
	@ResponseBody
	@RequestMapping(value="/getCity")
	public JSONArray getCity(HttpServletRequest request,@RequestParam Integer provinceId) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentId", provinceId);
			map.put("parentIdNotNull", 1);
			List<ProvinceCityArea> cityList = service.selectListByCondition(map);
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

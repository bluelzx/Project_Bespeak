package com.lhfeiyu.action.front.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Dict;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
public class DictAtion {
	@Autowired
	private DictService dictService;
	@Autowired
	private AuthCheckService authCheckService;
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/forumDict")
	public ModelAndView ForumDict(ModelMap modelMap,HttpSession session
			,@RequestParam(required=false) String r,HttpServletRequest request) {
		String path = LhPage.forumDict;
		try{
			/*User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				return Result.userSessionInvalid(modelMap, "/forumDict");
			}*/
			/*Map<String,Object> map = new HashMap<String,Object>();
			map.put("dictType", "forum");
			List<Dict> forumDictList = dictService.selectListByCondition(map);
			modelMap.put("forumDictList", forumDictList);*/
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/forumDict", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/getDictCombo", method=RequestMethod.POST)
	public JSONArray getDictCombo(HttpServletRequest request) {
		List<Dict> dictList = null;
		JSONArray array = new JSONArray();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			dictList = dictService.selectListByCondition(map);
			for(Dict d : dictList){
				JSONObject json = new JSONObject();
				json.put("id",d.getId());
				json.put("name",d.getDictName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载数据字典下拉列表出现异常_", array);
		}
		return array;
	}
	
	@ResponseBody
	@RequestMapping(value="/getDictList", method=RequestMethod.POST)
	public JSONObject getDictList(HttpServletRequest request) {
		List<Dict> dictList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			dictList = dictService.selectListByCondition(map);
			Integer total = dictService.selectCountByCondition(map);
			json.put("rows", dictList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载数据字典列表出现异常_", json);
		}
		return Result.success(json);
	}
	
}

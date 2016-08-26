package com.lhfeiyu.action.front.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.po.domain.UserRelation;
import com.lhfeiyu.service.domain.UserRelationService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
public class UserRelationAction {
	@Autowired
	private UserRelationService userRelationService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@ResponseBody
	@RequestMapping(value="/getUserRelationList", method=RequestMethod.POST)
	public JSONObject getAntiqueCityList(HttpServletRequest request) {
		List<UserRelation> userRelationList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			userRelationList = userRelationService.selectListByCondition(map);
			Integer total = userRelationService.selectCountByCondition(map);
			json.put("rows", userRelationList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载古玩城列表出现异常_", json);
		}
		return Result.success(json);
	}
}

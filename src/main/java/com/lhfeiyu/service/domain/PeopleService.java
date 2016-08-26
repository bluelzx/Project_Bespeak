package com.lhfeiyu.service.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.PeopleMapper;
import com.lhfeiyu.po.domain.People;
import com.lhfeiyu.service.base.CommonService;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：月嫂信息消息 people<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("basePeopleService")
public class PeopleService extends CommonService<People> {
	
	@Autowired
	PeopleMapper peopleMapper;
	
	public JSONObject getPeopleList(JSONObject json, Map<String, Object> map) {
		List<People> peopleList = peopleMapper.selectListByCondition(map);
		Integer total = peopleMapper.selectCountByCondition(map);
		return Result.gridData(peopleList, total, json);
	}
	
	/**
	 * 新增或修改月嫂信息消息
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param people 月嫂信息消息对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdatePeople(JSONObject json, People people, String username){
//		String realName = people.getRealName();
		String introduction = people.getIntroduction();
		String password = people.getPassword();
//		System.out.println("PeopleService中password："+password);
		if(Check.isNull(username)){
			return Result.failure(json, LhTip.msg_loginName_null, LhTip.msg_loginName_null);
		}
//		if(Check.isNull(password)){
//			return Result.failure(json, LhTip.msg_password_length_lack, LhTip.code_password_length_lack);
//		}
//		if(password.length()<6){
//			return Result.failure(json, LhTip.msg_password_length_lack, LhTip.code_password_length_lack);
//		}
//		if(Check.isNull(introduction)){
//			return Result.failure(json, LhTip.msg_introduction_null, LhTip.msg_introduction_null);
//		}
		if(null == people.getId()){//添加
			return addPeople(json, people, username);
		}else{//修改
			return updatePeople(json, people, username);
		}
	}
	
	/**
	 * 新增月嫂信息消息（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param people 月嫂信息消息对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addPeople(JSONObject json, People people, String username){
		Date date = new Date();
		people.setId(null);
		people.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_people));
		people.setMainStatus(1);
//		String TitleName=people.getTitleName();
//		System.out.println(TitleName);
//		people.setPositionNames(TitleName);
		people.setAttrInt(0);//评价星
		people.setAttrInt2(0);//喜欢数
		people.setCreatedBy(username);
		people.setCreatedAt(date);
		peopleMapper.insert(people);
		json.put("peopleId", people.getId());
		return Result.success(json);
	}
	
	public People buildPeople(Integer receiverId, String title, String introduction, String linkUrl){
		People people = new People();
//		people.setReceiverId(receiverId);
//		people.setTitle(title);
//		people.setLinkUrl(linkUrl);
//		people.setContent(content);
		return people;
	}
	
	public People buildPeople(String receiverIds, String title, String introduction, String linkUrl){
		People people = new People();
//		people.setReceiverIds(receiverIds);
//		people.setTitle(title);
//		people.setLinkUrl(linkUrl);
//		people.setContent(content);
		return people;
	}
	
	/**
	 * 修改月嫂信息消息（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param people 月嫂信息消息对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updatePeople(JSONObject json, People people, String username){
		Date date = new Date();
		Integer peopleId = people.getId();
		if(null == peopleId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		People dbPeople = peopleMapper.selectByPrimaryKey(peopleId);
		if(null == dbPeople){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}
		String PositionNames=people.getPositionNames();
		System.out.println(PositionNames);
		people.setPositionNames(PositionNames);
		people.setUpdatedBy(username);
		people.setUpdatedAt(date);
		peopleMapper.updateByPrimaryKeySelective(people);
		return Result.success(json);
	}
	
	
}


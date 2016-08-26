package com.lhfeiyu.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhfeiyu.dao.domain.AA_UtilMapper;
import com.lhfeiyu.po.domain.AaTemplate;
import com.lhfeiyu.po.domain.AaTemplateMain;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 业务层：通用-通用功能方法 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年3月3日21:23:48 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 */
@Service("baseAA_UtilService")
public class BaseAA_UtilService {
	
	@Autowired
	AA_UtilMapper mapper;
    
    /** 根据 ID 删除数据  */
	public int deleteById(Map<String, Object> map) {
		
		return mapper.deleteById(map);
	}

	/** 根据 ID串 删除数据  */
	public int deleteByIds(Map<String, Object> map) {
		
		return mapper.deleteByIds(map);
	}

	 /** 根据 条件参数删除 数据（sql片断） */
	public int deleteByCondition(Map<String, Object> map) {
		
		return mapper.deleteByCondition(map);
	}

	/** 根据 ID将删除时间设置为当前时间 更新数据  */
	public int updateDeletedNowById(Map<String, Object> map) {
		
		return mapper.updateDeletedNowById(map);
	}

	/** 根据  ID串将删除时间设置为当前时间 更新数据  */
	public int updateDeletedNowByIds(Map<String, Object> map) {
		
		return mapper.updateDeletedNowByIds(map);
	}

	 /** 根据 ID置空删除时间 更新数据  */
	public int updateDeletedNullById(Map<String, Object> map) {
		
		return mapper.updateDeletedNullById(map);
	}

	/** 根据 ID串置空删除时间 更新数据  */
	public int updateDeletedNullByIds(Map<String, Object> map) {
		
		return mapper.updateDeletedNullByIds(map);
	}

	/** 根据 ID修改主状态 更新数据  */
	public int updateMainStatusById(Map<String, Object> map) {
		
		return mapper.updateMainStatusById(map);
	}

	/** 根据 ID串修改主状态 更新数据  */
	public int updateMainStatusByIds(Map<String, Object> map) {
		
		return mapper.updateMainStatusByIds(map);
	}

	/** 根据 ID修改逻辑状态 更新数据  */
	public int updateLogicStatusById(Map<String, Object> map) {
		
		return mapper.updateLogicStatusById(map);
	}

	/** 根据 ID串修改逻辑状态 更新数据  */
	public int updateLogicStatusByIds(Map<String, Object> map) {
		
		return mapper.updateLogicStatusByIds(map);
	}

	/** 根据 条件参数修改字段 更新数据（sql片断）  */
	public int updateFieldByCondition(Map<String, Object> map) {
		
		return mapper.updateFieldByCondition(map);
	}

	/** 新增数据（传入完整sql）  */
	public int insertBySql(Map<String, Object> map) {
		
		return mapper.insertBySql(map);
	}

	 /** 删除数据（传入完整sql）  */
	public int deleteBySql(Map<String, Object> map) {
		
		return mapper.deleteBySql(map);
	}

	 /** 修改数据（传入完整sql）  */
	public int updateBySql(Map<String, Object> map) {
		
		return mapper.updateBySql(map);
	}

	 /** 查询数据，返回字符串  */
	public String selectForString(Map<String, Object> map) {
		
		return mapper.selectForString(map);
	}

	/** 查询数据，返回整型  */
	public Integer selectForInteger(Map<String, Object> map) {
		
		return mapper.selectForInteger(map);
	}

	 /** 查询数据，返回Double  */
	public Double selectForDouble(Map<String, Object> map) {
		
		return mapper.selectForDouble(map);
	}

	 /** 查询数据，返回模板基本型对象  */
	public AaTemplate selectForTemplate(Map<String, Object> map) {
		
		return mapper.selectForTemplate(map);
	}

	 /** 查询数据，返回模板详细型对象  */
	public AaTemplateMain selectForTemplateMain(Map<String, Object> map) {
		
		return mapper.selectForTemplateMain(map);
	}

	 /** 查询数据，返回模板基本型对象集合  */
	public List<AaTemplate> selectForTemplateList(Map<String, Object> map) {
		
		return mapper.selectForTemplateList(map);
	}

	/** 查询数据，返回模板详细型对象集合  */
	public List<AaTemplateMain> selectForTemplateMainList(Map<String, Object> map) {
		
		return mapper.selectForTemplateMainList(map);
	}

}

package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.AlbumMapper;
import com.lhfeiyu.po.domain.Album;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：相册 Album <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseAlbumService")
public class BaseAlbumService extends CommonService<Album> {

	@Autowired
	AlbumMapper albumMapper;
	
	public JSONObject getAlbumList(JSONObject json, Map<String, Object> map) {
		List<Album> albumList = albumMapper.selectListByCondition(map);
		Integer total = albumMapper.selectCountByCondition(map);
		return Result.gridData(albumList, total, json);
	}
	
	/**
	 * 新增或修改相册
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param album 相册对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateAlbum(JSONObject json, Album album, String username){
		//String content = album.getContent();
		//if(!Check.isNotNull(content)){
		//	return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		//}
		if(null == album.getId()){//添加
			return addAlbum(json, album, username);
		}else{//修改
			return updateAlbum(json, album, username);
		}
	}
	
	/**
	 * 新增相册（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param album 相册对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addAlbum(JSONObject json, Album album, String username){
		Date date = new Date();
		album.setId(null);
		album.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_album));
		album.setHits(0);
		album.setScans(0);
		album.setMainStatus(1);
		album.setCreatedBy(username);
		album.setCreatedAt(date);
		albumMapper.insert(album);
		json.put("id", album.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改相册（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param album 相册对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateAlbum(JSONObject json, Album album, String username){
		Date date = new Date();
		Integer albumId = album.getId();
		if(null == albumId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Album dbAlbum = albumMapper.selectByPrimaryKey(albumId);
		if(null == dbAlbum){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		album.setUpdatedBy(username);
		album.setUpdatedAt(date);
		albumMapper.updateByPrimaryKeySelective(album);
		return Result.success(json);
	}
	
}

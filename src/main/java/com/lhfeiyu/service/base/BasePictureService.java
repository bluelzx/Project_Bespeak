package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.PictureMapper;
import com.lhfeiyu.domain.AliyunOSS;
import com.lhfeiyu.po.domain.Picture;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.ImgFromUrl;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：图片 Picture <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("basePictureService")
public class BasePictureService extends CommonService<Picture> {

	@Autowired
	PictureMapper pictureMapper;
	
	public JSONObject getPictureList(JSONObject json, Map<String, Object> map) {
		List<Picture> pictureList = pictureMapper.selectListByCondition(map);
		Integer total = pictureMapper.selectCountByCondition(map);
		return Result.gridData(pictureList, total, json);
	}
	
	/**
	 * 新增或修改图片
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param picture 图片对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdatePicture(JSONObject json, Picture picture, String username){
		String content = picture.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}
		if(null == picture.getId()){//添加
			return addPicture(json, picture, username);
		}else{//修改
			return updatePicture(json, picture, username);
		}
	}
	
	/**
	 * 新增图片（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param picture 图片对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addPicture(JSONObject json, Picture picture, String username){
		Date date = new Date();
		picture.setId(null);
		picture.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_picture));
		picture.setHits(0);
		picture.setScans(0);
		picture.setMainStatus(1);
		picture.setCreatedBy(username);
		picture.setCreatedAt(date);
		pictureMapper.insert(picture);
		json.put("id", picture.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改图片（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param picture 图片对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updatePicture(JSONObject json, Picture picture, String username){
		Date date = new Date();
		Integer pictureId = picture.getId();
		if(null == pictureId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Picture dbPicture = pictureMapper.selectByPrimaryKey(pictureId);
		if(null == dbPicture){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		picture.setUpdatedBy(username);
		picture.setUpdatedAt(date);
		pictureMapper.updateByPrimaryKeySelective(picture);
		return Result.success(json);
	}
	
	/**
	 * 根据微信图片服务器的serverIds下载图片到本地服务器，返回图片路径
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param serverIds 图片服务器的serverIds
	 * @param basePath 基础路径：request.getServletContext().getRealPath("/");
	 * @return JSONObject 图片路径picPaths：json.put("picPaths", picPaths);
	 */
	public JSONObject getPicPathsByWxServerIds(JSONObject json, String serverIds, String basePath, AliyunOSS oss){
		//String basePath = request.getServletContext().getRealPath("/");
		if(Check.isNull(serverIds))return json;
		String filePaths[] = serverIds.split(",");
		System.out.println("getPicPathsByWxServerIds-filePaths: "+filePaths);
		String picPaths = "";
		for(int j = 0 ; j < filePaths.length ;j++){
			String mediaId = filePaths[j];
			if(Check.isNotNull(mediaId) && !mediaId.equalsIgnoreCase("undefined")){//此处为微信服务器获取用户上传的图片
				String picPath = ImgFromUrl.saveImgFromUrl(basePath, mediaId, null, oss);//图片存放服务器的路径，可直接新增入数据库中
				picPaths += ","+picPath;
			}
		}
		json.put("picPaths", picPaths);
		return Result.success(json);
	}
	
}

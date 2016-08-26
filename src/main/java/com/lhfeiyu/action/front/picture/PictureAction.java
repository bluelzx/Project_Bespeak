package com.lhfeiyu.action.front.picture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.po.domain.Picture;
import com.lhfeiyu.service.domain.PictureService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

@Controller
public class PictureAction {
	@Autowired
	private PictureService pictureService;

	private static Logger logger = Logger.getLogger("R");
	
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdatePicture", method=RequestMethod.POST)
	public JSONObject addOrUpdatePicture(@ModelAttribute Picture picture,HttpServletRequest request,
								@RequestParam(required=false,value="isAdmin") Integer isAdmin){
		JSONObject json = new JSONObject();
		try {
			/*User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				Object adminObj = request.getSession().getAttribute("admin");
				if(null != adminObj){
				}else{
					return Result.userSessionInvalid(modelMap, "/addOrUpdatePicture?isAdmin="+isAdmin);
				}
				auctionInst.setUpdatedBy(admin.getAuctionInstname());
				isAdmin
			}*/
			String username = "";
			if(null == picture.getId()){//添加
				Date d = new Date();
				String picPaths = picture.getPicUrls();
				if(null == picPaths || "".equals(picPaths.trim())){
					json.put("status", "failure");
					json.put("msg", "图片路径不能为空");
					return Result.success(json);
				}
				String[] picAry = picPaths.split(",");
				if(picAry.length>1){
					List<Picture> picList = new ArrayList<Picture>();
					Integer albumId = picture.getAlbumId();
					Integer linkId = picture.getLinkId();
					Integer typeId = picture.getTypeId();
					Integer userId = picture.getUserId();
					for(String path : picAry){
						if(null == path || "".equals(path.trim()))continue;
						String serial = CommonGenerator.getSerialByDate("p");
						Picture pic = new Picture();
						int idx1 = path.indexOf("__");
						int idx2 = path.lastIndexOf(".");
						String title = "";
						if(idx1 > 1 && idx2 > 1)title = path.substring(idx1+2,idx2);
						pic.setPicPath(path);
						pic.setSerial(serial);
						pic.setHits(0);
						pic.setScans(0);
						pic.setCreatedBy(username);
						pic.setAlbumId(albumId);
						pic.setLinkId(linkId);
						pic.setTypeId(typeId);
						pic.setUserId(userId);
						pic.setTitle(title);
						picList.add(pic);
					}
					pictureService.insertBatch(picList);//批量新增图片
				}else{
					picture.setHits(0);
					picture.setScans(0);
					picture.setCreatedBy(username);
					picture.setCreatedAt(d);
					pictureService.insert(picture);
				}
			}else{//修改 - 暂时不支持修改
				//picture.setUpdatedBy(admin.getUsername());
				//picture.setUpdatedAt(new Date());
				//pictureService.updateByPrimaryKeySelective(picture);
			}
			json.put("status", "success");
			json.put("id",picture.getId());
			json.put("msg", "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改图片出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getPictureList", method=RequestMethod.POST)
	public JSONObject getPictureList(HttpServletRequest request) {
		List<Picture> pictureList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			pictureList = pictureService.selectListByCondition(map);
			json.put("rows", pictureList);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载文章列表出现异常_", json);
		}
		return Result.success(json);
	}
	
	
}

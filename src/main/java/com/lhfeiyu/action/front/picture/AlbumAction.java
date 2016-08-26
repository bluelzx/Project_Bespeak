package com.lhfeiyu.action.front.picture;

import java.util.Date;

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
import com.lhfeiyu.po.domain.Album;
import com.lhfeiyu.service.domain.AlbumService;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

@Controller
public class AlbumAction {
	@Autowired
	private AlbumService albumService;

	private static Logger logger = Logger.getLogger("R");
	
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateAlbum", method=RequestMethod.POST)
	public JSONObject addOrUpdateAlbum(@ModelAttribute Album album,HttpServletRequest request,
								@RequestParam(required=false,value="isAdmin") Integer isAdmin){
		JSONObject json = new JSONObject();
		try {
			/*User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				Object adminObj = request.getSession().getAttribute("admin");
				if(null != adminObj){
				}else{
					return Result.userSessionInvalid(modelMap, "/addOrUpdateAlbum?isAdmin="+isAdmin);
				}
				auctionInst.setUpdatedBy(admin.getAuctionInstname());
				isAdmin
			}*/
			String username = "";
			Date d = new Date();
			if(null == album.getId()){//添加
				String serial = CommonGenerator.getSerialByDate("a");
				album.setSerial(serial);
				album.setHits(0);
				album.setScans(0);
				album.setCreatedBy(username);
				album.setCreatedAt(d);
				albumService.insert(album);
			}else{//修改 - 暂时不支持修改
				//album.setUpdatedBy(admin.getUsername());
				album.setUpdatedAt(d);
				albumService.updateByPrimaryKeySelective(album);
			}
			json.put("status", "success");
			json.put("id",album.getId());
			json.put("msg", "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改相册出现异常_", json);
		}
		return Result.success(json);
	}
	
}

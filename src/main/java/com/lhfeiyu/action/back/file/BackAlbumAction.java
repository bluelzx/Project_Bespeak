package com.lhfeiyu.action.back.file;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Album;
import com.lhfeiyu.service.domain.AlbumService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：相册 Album <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.album.BackAlbumAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackAlbumAction {
	
	@Autowired
	private AlbumService albumService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台相册页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/album")
	public ModelAndView album(ModelMap modelMap){
		String path = LhPage.back_album;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/album", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 新增修改相册
	 * @param modelMap
	 * @param typeId 类型ID
	 * @param albumId 相册ID
	 * @param operation String 操作
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/albumAddUpdate")
	public ModelAndView albumAddUpdate(ModelMap modelMap,
			@RequestParam(required=false) Integer typeId,
			@RequestParam(required=false) Integer albumId,
			@RequestParam(required=false) String operation){
		String path = LhPage.back_albumAddUpdate;
		try{
			modelMap.put("typeId", typeId);
			if(Check.isNotNull(albumId)){
				modelMap.put("albumId", albumId);
				Album album = albumService.selectByPrimaryKey(albumId);
				if(null != album){
					modelMap.put("album", album);
				}
			}
			if(Check.isNotNull(operation)){
				modelMap.put("operation", operation);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/albumAddUpdate", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 新增修改相册（根据ID加载相册存入modelMap）
	 * @param modelMap
	 * @param albumId 相册ID
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/albumAddUpdate/{albumId}")
	public ModelAndView albumAddUpdate(ModelMap modelMap, @PathVariable Integer albumId){
		String path = LhPage.back_albumAddUpdate;
		try{
			Album album = albumService.selectByPrimaryKey(albumId);
			if(null != album){
				modelMap.put("album", album);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/page/albumAddUpdate/{albumId}", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	@RequestMapping(value="/page/album/{albumId}")
	public ModelAndView albumDetail(ModelMap modelMap,@PathVariable Integer albumId){
		String path = LhPage.back_albumDetail;
		try{
			Album album = albumService.selectByPrimaryKey(albumId);
			if(null != album){
				modelMap.put("album", album);
			}
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/page/album/{albumId}", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载相册列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getAlbumList",method=RequestMethod.POST)
	public JSONObject getAlbumList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			albumService.getAlbumList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getAlbumList", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改相册（新增和修改方法对应Serivce中的不同方法）
	 * @param album ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateAlbum", method = RequestMethod.POST)
	public JSONObject addUpdateAlbum(@ModelAttribute Album album,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			albumService.addUpdateAlbum(json, album, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateAlbum", json);
		}
		return json;
	}

	/**
	 * 逻辑删除相册数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAlbumDelete", method=RequestMethod.POST)
	public JSONObject updateAlbumDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			albumService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateAlbumDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除相册
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAlbumThorough",method=RequestMethod.POST)
	public JSONObject deleteAlbumThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			albumService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteAlbumThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复相册（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAlbumRecover", method=RequestMethod.POST)
	public JSONObject updateAlbumRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			albumService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateAlbumRecover", json);
		}
		return json;
	}
	

}


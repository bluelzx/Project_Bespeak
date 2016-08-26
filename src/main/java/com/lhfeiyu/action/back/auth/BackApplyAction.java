package com.lhfeiyu.action.back.auth;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Apply;
import com.lhfeiyu.po.domain.Coupon;
import com.lhfeiyu.po.domain.Course;
import com.lhfeiyu.po.domain.Dict;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.People;
import com.lhfeiyu.po.domain.Provider;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.service.base.BaseCouponService;
import com.lhfeiyu.service.base.BaseCourseService;
import com.lhfeiyu.service.base.BaseProviderService;
import com.lhfeiyu.service.domain.ApplyService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.service.domain.NoticeService;
import com.lhfeiyu.service.domain.PeopleService;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：申请 Apply <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.auth.BackApplyAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackApplyAction {
	
	@Autowired
	private ApplyService applyService;
	@Autowired
	private DictService dictService;
	//企业及用户优惠券couponService
	@Autowired
	private BaseCouponService couponService;
	//月嫂peopleService
	@Autowired
	private PeopleService peopleService;
	//技师BaseProviderService
	@Autowired
	private BaseProviderService providerService;
	//培训BaseCourseService
	@Autowired
	private BaseCourseService courseService;
	//加盟商ShopService
	@Autowired
	private ShopService shopService;
	//通知消息peopleService
	@Autowired
	private NoticeService noticeService;

	
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台申请页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/apply")
	public ModelAndView apply(ModelMap modelMap){
		String path = LhPage.back_apply;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/apply", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 加载申请列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getApplyList",method=RequestMethod.POST)
	public JSONObject getApplyList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
//			map.put("mainStatus", 1);//只查询已处理
//			System.out.println("sss:"+map);
			applyService.getApplyList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getApplyList", json);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/getApplyTypeList")
	public JSONArray getApplyTypeList(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			map.put("parentCode", "apply_type");
			List<Dict> dict = dictService.selectListByCondition(map);
			for(Dict u : dict){
				JSONObject json = new JSONObject();
				json.put("id",u.getId());
				json.put("name",u.getCodeName());
				array.add(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_获取申请类型出现异常_", array);
		}
		return array;
	}
//	/**
//	 * 新增或修改申请（新增和修改方法对应Serivce中的不同方法）
//	 * @param apply ModelAttribute
//	 * @param request
//	 * @return JSONObject
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/addOrUpdateApply", method = RequestMethod.POST)
//	public JSONObject addUpdateApply(@ModelAttribute Apply apply,HttpServletRequest request){
//		JSONObject json = new JSONObject();
//		try {
//			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
//			String username = admin.getUsername();
//			System.out.println("添加"+apply);
//			applyService.addUpdateApply(json, apply, username);
//		}catch (Exception e) {
//			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateApply", json);
//		}
//		return json;
//	}
	/**
	 * 加盟商认证type：1
	 * @param apply ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateShopApply", method = RequestMethod.POST)
	public JSONObject addOrUpdateProfessionApply(@ModelAttribute Apply apply,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			Notice notice=new Notice();
			notice.setSendTime(d);
			notice.setSenderId(admin.getId());
			notice.setSenderName(username);
			notice.setReceiverId(apply.getUserId());
			notice.setTitle("加盟商认证申请通知");
			notice.setContent(apply.getReplyDesc());
			noticeService.insert(notice);
			int mainStatus= apply.getMainStatus();
			if(mainStatus==2){
			Shop shop = new Shop();
			Apply apply2= applyService.selectByPrimaryKey(apply.getId());
			shop.setName(apply2.getShopName());//加盟店名
			shop.setUserName(apply2.getAttr4());//负责人
			shop.setDescription(apply2.getDescription());//说明
			shop.setPhone(apply2.getPhone());//电话
			shop.setProvince(apply2.getProvince());//省
			shop.setCity(apply2.getCity());//市
			shop.setAddress(apply2.getAddress());//地址
			shop.setUserId(apply2.getUserId());
			shop.setPicPaths(apply2.getFile1());//pic
			shop.setCreatedBy(username);
			shop.setCreatedAt(new Date());
			shop.setSerial(CommonGenerator.getSerialByDate("ai"));
			shopService.insertSelective(shop);
//			json.put("mainStatus",mainStatus);
			}else if(mainStatus==3){
//			json.put("mainStatus",mainStatus);	
			}
			applyService.updateByPrimaryKeySelective(apply);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdateShopApply", json);
		}
		return json;
	}
	//申请新培训课程type：2
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateCourseCouponApply", method = RequestMethod.POST)
	public JSONObject addOrUpdateCourseCouponApply(@ModelAttribute Apply apply,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			Notice notice=new Notice();
			notice.setSendTime(d);
			notice.setSenderId(admin.getId());
			notice.setSenderName(username);
			notice.setReceiverId(apply.getUserId());
			notice.setTitle("申请新培训课程申请通知");
			notice.setContent(apply.getReplyDesc());
			noticeService.insert(notice);
			int mainStatus= apply.getMainStatus();
			if(mainStatus==2){
			Course course = new Course();
			Apply apply2= applyService.selectByPrimaryKey(apply.getId());
			course.setId(apply.getUserId());
			course.setSerial(CommonGenerator.getSerialByDate("c"));//设置编号必要
			course.setName(apply2.getTitleNames());//课程名
			course.setTypeCode(apply2.getTypeCode());
			course.setShopName(apply2.getShopName());//机构名
			course.setDescription(apply2.getDescription());
			course.setAddress(apply2.getAddress());//地址
			course.setUpdatedBy(username);
			course.setUpdatedAt(new Date());
//			user.setIsEnterpriseAuth(-1);
			courseService.insertSelective(course);
			}
			applyService.updateByPrimaryKeySelective(apply);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdateCourseApply", json);
		}
		return json;
	}
	//月嫂申请type：3
	@ResponseBody
	@RequestMapping(value = "/addOrUpdatePeopleApply", method = RequestMethod.POST)
	public JSONObject addOrUpdatePeopleApply(@ModelAttribute Apply apply,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			Notice notice=new Notice();
			notice.setSendTime(d);
			notice.setSenderId(admin.getId());
			notice.setSenderName(username);
			notice.setReceiverId(apply.getUserId());
			notice.setTitle("月嫂申请通知");
			notice.setContent(apply.getReplyDesc());
			noticeService.insert(notice);
			int mainStatus= apply.getMainStatus();
			if(mainStatus==2){
			People people = new People();
			Apply apply2= applyService.selectByPrimaryKey(apply.getId());
			people.setId(apply.getUserId());
			people.setSerial(CommonGenerator.getSerialByDate("pe"));//设置编号必要
			people.setMainStatus(apply2.getMainStatus());
			people.setUsername(apply2.getUserName());//用户名
			people.setTypeId(apply2.getTypeId());//拿到类型的int的Code
			people.setTitleCode(people.getTypeCode());//职称代码
			people.setPhone(apply2.getPhone());//手机电话
//			people.setIntroduction(apply2.getAttr3());//简介
			people.setPriceText(apply2.getDescription());//收费说明
			people.setSex(apply2.getSex());//性别
			people.setIdcardNum(String.valueOf(apply2.getIdcardNum()));//身份证
			people.setBirthday(apply2.getBirthday());//出生日期
			//根据拿到的code取到职位名称
			Dict dict = dictService.selectByPrimaryKey(Integer.parseInt(people.getTitleCode()));//通过代码取到dict对应一条数据
			people.setPositionNames(dict.getCodeName());//根据改变后的code拿到类型名并赋值到职位名
//			people.setPositionNames(apply2.getAttrStr2()+" ");//职位名称
			people.setUpdatedBy(username);
			people.setUpdatedAt(new Date());
//			user.setIsEnterpriseAuth(-1);
			peopleService.insertSelective(people);
			}
			applyService.updateByPrimaryKeySelective(apply);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdatePeopleApply", json);
		}
		return json;
	}
	//企业优惠申请type：4
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateEnterpriseCouponApply", method = RequestMethod.POST)
	public JSONObject addOrUpdateEnterpriseCouponApply(@ModelAttribute Apply apply,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			Notice notice=new Notice();
			notice.setSendTime(d);
			notice.setSenderId(admin.getId());
			notice.setSenderName(username);
			notice.setReceiverId(apply.getUserId());
			notice.setTitle("企业优惠申请通知");
			notice.setContent(apply.getReplyDesc());
			noticeService.insert(notice);
			int mainStatus= apply.getMainStatus();
			if(mainStatus==2){
			Coupon coupon = new Coupon();
			Apply apply2= applyService.selectByPrimaryKey(apply.getId());
			coupon.setSerial(CommonGenerator.getSerialByDate("cp"));//设置编号必要
//			coupon.setId(apply.getUserId());
			coupon.setShopName(apply2.getShopName());
			coupon.setMoney(apply2.getMoney());
			coupon.setDiscount(apply2.getAttrInt());//折扣
//			coupon.setLinkCode4(apply2.getFile2());
			coupon.setUpdatedBy(username);
			coupon.setUpdatedAt(new Date());
//			user.setIsEnterpriseAuth(-1);
			couponService.insertSelective(coupon);
			}
			applyService.updateByPrimaryKeySelective(apply);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addOrUpdateEnterpriseCouponApply", json);
		}
		return json;
	}
	//技师申请type：5
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateProviderApply", method = RequestMethod.POST)
	public JSONObject addOrUpdateProviderApply(@ModelAttribute Apply apply,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Date d = new Date();
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			Notice notice=new Notice();
			notice.setSendTime(d);
			notice.setSenderId(admin.getId());
			notice.setSenderName(username);
			notice.setReceiverId(apply.getUserId());
			notice.setTitle("技师申请通知");
			notice.setContent(apply.getReplyDesc());
			noticeService.insert(notice);
			int mainStatus= apply.getMainStatus();
			if(mainStatus==2){
			Provider provider = new Provider();
			Apply apply2= applyService.selectByPrimaryKey(apply.getId());
			provider.setId(apply.getUserId());
			provider.setSerial(CommonGenerator.getSerialByDate("p"));//设置编号必要
			provider.setMainStatus(apply2.getMainStatus());
			provider.setUsername(apply2.getUserName());//用户名
			provider.setRealName(apply2.getReplyUsername());//真实姓名
			provider.setShopName(apply2.getShopName());
			provider.setTypeId(apply2.getTypeId());//拿到类型的int的Code
			provider.setTypeCode(apply2.getTypeCode());//职位代码
			provider.setPhone(apply2.getPhone());//手机电话
			provider.setAttrStr3(apply2.getAttr3());//证书
			provider.setPassword(apply2.getAttr4());//密码
//			people.setIntroduction(apply2.getAttr3());//简介
			provider.setPriceText(apply2.getDescription());//收费说明
			provider.setProvince(apply2.getProvince());//省
			provider.setCity(apply2.getCity());//市
			provider.setSex(apply2.getSex());//性别
//			provider.setIdcardNum(apply2.getIdcardNum());//身份证
			provider.setIdcardNum(String.valueOf(apply2.getIdcardNum()));//身份证
			provider.setBirthday(apply2.getBirthday());//出生日期
			//根据拿到的code取到职位名称
			Dict dict = dictService.selectByPrimaryKey(Integer.parseInt(provider.getTitleCode()));//通过代码取到dict对应一条数据
			provider.setPositionNames(dict.getCodeName());//根据改变后的code拿到类型名并赋值到职位名
//			people.setPositionNames(apply2.getAttrStr2()+" ");//职位名称
			provider.setUpdatedBy(username);
			provider.setUpdatedAt(new Date());
//			user.setIsEnterpriseAuth(-1);
			providerService.insertSelective(provider);
			}
			applyService.updateByPrimaryKeySelective(apply);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdatePeopleApply", json);
		}
		return json;
	}
	
	/**
	 * 逻辑删除申请数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateApplyDelete", method=RequestMethod.POST)
	public JSONObject updateApplyDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			applyService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateApplyDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除申请
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteApplyThorough",method=RequestMethod.POST)
	public JSONObject deleteApplyThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			applyService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteApplyThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复申请（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateApplyRecover", method=RequestMethod.POST)
	public JSONObject updateApplyRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			applyService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateApplyRecover", json);
		}
		return json;
	}
	

}


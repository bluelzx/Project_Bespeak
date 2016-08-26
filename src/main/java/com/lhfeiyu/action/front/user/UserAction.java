package com.lhfeiyu.action.front.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.base.BaseTip;
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.domain.YtxMessage;
import com.lhfeiyu.po.domain.Fans;
import com.lhfeiyu.po.domain.ForumMember;
import com.lhfeiyu.po.domain.Provider;
import com.lhfeiyu.po.domain.ProvinceCityArea;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.po.domain.UserRelation;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.CreditService;
import com.lhfeiyu.service.domain.FansService;
import com.lhfeiyu.service.domain.ForumMemberService;
import com.lhfeiyu.service.domain.GoodsService;
import com.lhfeiyu.service.domain.OrderInfoService;
import com.lhfeiyu.service.domain.ProvinceCityAreaService;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.service.domain.UserFundService;
import com.lhfeiyu.service.domain.UserRelationService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.MapUtil;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.SendMsgUtil;
import com.lhfeiyu.util.UploadUtil;
import com.lhfeiyu.util.YTX_MSG;

@Controller
public class UserAction {
	@Autowired
	private UserService userService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private FansService fansService;
	@Autowired
	private ProvinceCityAreaService provinceCityAreaService;
	@Autowired
	private UserFundService userFundService;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private UserRelationService userRelationService;
	@Autowired
	private AuthCheckService authCheckService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ForumMemberService forumMemberService;
	@Autowired
	private CreditService creditService;
	
	private static Logger logger = Logger.getLogger("R");

	//用户个人中心
	@RequestMapping(value="/userHome")
	public ModelAndView  userHome(ModelMap modelMap,HttpServletRequest request){
		String path = LhPage.frontUser;
		try{
			JSONObject json = new JSONObject();
			User sessionuser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			Provider provider = ActionUtil.checkSession4Provider(request.getSession());//验证session中的user，存在即返回
			Map<String,Object> map = new HashMap<String,Object>();
			if(null == sessionuser&&null==provider){
				return Result.userSessionInvalid(modelMap,LhPage.login);//没有登陆的session则跳转到登录
				}
			if(null!=sessionuser ){
					map.put("id", sessionuser.getId());
					User user=userService.selectByCondition(map);
					modelMap.put("user", user);
					json.put("user", user);
				}
			if(null!=provider ){
				modelMap.put("provider", provider);
				System.out.println(provider);
				json.put("provider", provider);
			}
			modelMap.put("paramJson", json);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, "LH_ERROR-User-PAGE-/userHome-加载用户中心出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/** 用户主页 */
	@RequestMapping(value = "/user/{serial}")
	public ModelAndView user(ModelMap modelMap, HttpServletRequest request,
			@PathVariable String serial,
			@RequestParam(required = false) String r) {
		String path = LhPage.userCenterShop;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/index";
				new ModelAndView(jumpUrl, modelMap);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("serial", serial);
			User user = userService.selectByCondition(map);
			Integer id = user.getId();
			map.put("id", id);
			modelMap.put("user", user);// 用户
			if (null == user.getIsRealAuth()) {
				modelMap.put("isRealAuth", "没有认证");
			} else {
				modelMap.put("isRealAuth", "实名认证");
			}
			map.clear();
			map.put("userId", id);
			modelMap.put("focusCount", fansService.selectCountByCondition(map));// 粉丝数
			map.clear();
			map.put("fansId", id);
			modelMap.put("fansCount", fansService.selectCountByCondition(map));// 关注数
			map.clear();
			map.put("userId", id);
			modelMap.put("userFun", userFundService.selectByCondition(map));
			//是否关注
			map.clear();
			map.put("fansId", id);
			map.put("userId", sessionUser.getId());
			if(id == sessionUser.getId()){
				modelMap.put("isFans", -1);
			}else{
			modelMap.put("isFans", fansService.selectCountByCondition(map));
			Integer num = user.getAttrInt();
			user.setAttrInt(++num);
			userService.updateByPrimaryKey(user);
			}
			map.clear();
			map.put("userId", id);
			modelMap.put("credit", creditService.selectByCondition(map));
			
			MapUtil.printMap(modelMap);
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/index", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}	
	/** 用户主页 */
	@RequestMapping(value = "/userCenter/{serial}")
	public ModelAndView userCenter(ModelMap modelMap, HttpServletRequest request,
			@PathVariable String serial,
			@RequestParam(required = false) String r) {
		String path = LhPage.userCenterUser;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/index";
				new ModelAndView(jumpUrl, modelMap);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("serial", serial);
			User user = userService.selectByCondition(map);
			Integer id = user.getId();
			map.put("id", id);
			modelMap.put("user", user);// 用户
			if (null == user.getIsRealAuth()) {
				modelMap.put("isRealAuth", "没有认证");
			} else {
				modelMap.put("isRealAuth", "实名认证");
			}
			map.clear();
			map.put("userId", id);
			modelMap.put("focusCount", fansService.selectCountByCondition(map));// 粉丝数
			map.clear();
			map.put("fansId", id);
			modelMap.put("fansCount", fansService.selectCountByCondition(map));// 关注数
			map.clear();
			map.put("userId", id);
			modelMap.put("userFun", userFundService.selectByCondition(map));
			//是否关注
			map.clear();
			map.put("fansId", id);
			map.put("userId", sessionUser.getId());
			if(id == sessionUser.getId()){
				modelMap.put("isFans", -1);
			}else{
				modelMap.put("isFans", fansService.selectCountByCondition(map));
			}
			map.clear();
			map.put("userId", id);
			modelMap.put("credit", creditService.selectByCondition(map));
			
			
			MapUtil.printMap(modelMap);
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/index", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}	
	/**
	 * 取消关注
	 * */
	@ResponseBody
	@RequestMapping(value = "/cancelFans", method = RequestMethod.POST)
	public JSONObject cancelFans(HttpServletRequest request,
			@RequestParam(value="fansId") String fansId) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer userId = sessionUser.getId();
			fansService.deleteByCondition("1=1 AND user_id = "+userId + " AND fans_id = " + fansId);
			
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_获取用户微拍商品出现异常_", json);
		}
		return Result.success(json,"已取消");
	}
	/**
	 * 添加关注
	 * */
	@ResponseBody
	@RequestMapping(value = "/joinFans", method = RequestMethod.POST)
	public JSONObject joinFans(HttpServletRequest request,
			@RequestParam(value="fansId") Integer fansId) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer userId = sessionUser.getId();
//			fansService.deleteByCondition("1=1 AND user_id = "+userId + " AND fans_id = " + fansId);
			Fans fans = new Fans();
			fans.setUserId(userId);
			fans.setFansId(fansId);
			fansService.insert(fans);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_获取用户微拍商品出现异常_", json);
		}
		return Result.success(json,"已关注");
	}
	
	
	@RequestMapping(value = "/user")
	public ModelAndView user(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.userIndex;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/user";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			// int userId = sessionUser.getId();
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("id", sessionUser.getId());
			map.put("uf_money", "1");
			map.put("nowOrderCount", "1");
			User user = userService.selectByCondition(map);
			System.out.println(user);
			if (null == user) {
				new ModelAndView(LhPage.index, modelMap);
			}
			modelMap.put("user", user);
			json.put("user", user);
			json.put("paramJson", json);

		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/user", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	@RequestMapping(value = "/userMerchantsIndex")
	public ModelAndView userMerchants(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.userMerchantsIndex;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/user";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			// int userId = sessionUser.getId();
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("id", sessionUser.getId());
			map.put("uf_money", "1");
			map.put("nowOrderCount", "1");
			User user = userService.selectByCondition(map);
			System.out.println(user);
			if (null == user) {
				new ModelAndView(LhPage.index, modelMap);
			}
			modelMap.put("user", user);
			json.put("user", user);
			json.put("paramJson", json);
			
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/user", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 个人信息 */
	@RequestMapping(value = "/userInfo")
	public ModelAndView userInfo(ModelMap modelMap, @RequestParam(required = false) String r, HttpServletRequest request) {
		String path = LhPage.personInformation;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/userInfo";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Integer userId = sessionUser.getId();
			if (null != userId && !"".equals(userId)) {
				User user = userService.selectByPrimaryKey(userId);
				if (null == user.getPhone() || "".equals(user.getPhone())) {
					modelMap.put("noPhone", "noPhone");
				}
				String passwd = Md5Util.encrypt("888888");
				if (passwd == user.getPassword() || passwd.equals(user.getPassword())) {
					modelMap.put("noEditPassword", "noEditPassword");
				}
				modelMap.put("user", user);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", sessionUser.getId());
				List<Fans> focusList = fansService.selectListByCondition(map);
				List<UserFund> userFundList = userFundService.selectListByCondition(map);
				if (userFundList.size() > 0) {
					UserFund userFund = userFundList.get(0);
					modelMap.put("userFund", userFund);
					if (null == userFund.getPayPassword() || "".equals(userFund.getPayPassword())) {
						modelMap.put("noPayPassword", "noPayPassword");
					}
				}
				modelMap.put("focusCount", focusList.size());
				map.clear();
				map.put("fansId", userId);
				List<Fans> fansList = fansService.selectListByCondition(map);
				modelMap.put("fansCount", fansList.size());
				map.clear();
				map.put("userId", userId);
				map.put("withGoods", 1);
				List<Shop> shopList = shopService.selectListByCondition(map);
				modelMap.put("shopList", shopList);
				map.clear();
				map.put("userId", userId);
				List<ForumMember> ForumMemberList = forumMemberService.selectListByCondition(map);
				modelMap.put("ForumMemberList", ForumMemberList);
				modelMap.put("userId", sessionUser.getId());
			}
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/user", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 我的交易 */
	@RequestMapping(value = "/myTrade")
	public ModelAndView myTrade(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.myTrade;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/myTrade";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Integer userId = sessionUser.getId();
			if (null != userId && !"".equals(userId)) {
				User user = userService.selectByPrimaryKey(userId);
				modelMap.put("user", user);
				if (null == user.getPhone() || "".equals(user.getPhone())) {
					modelMap.put("noPhone", "noPhone");
				}
				String passwd = Md5Util.encrypt("888888");
				if (passwd == user.getPassword() || passwd.equals(user.getPassword())) {
					modelMap.put("noEditPassword", "noEditPassword");
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", userId);
				List<Fans> focusList = fansService.selectListByCondition(map);
				List<UserFund> userFundList = userFundService.selectListByCondition(map);
				if (userFundList.size() > 0) {
					UserFund userFund = userFundList.get(0);
					modelMap.put("userFund", userFund);
					if (null == userFund.getPayPassword() || "".equals(userFund.getPayPassword())) {
						modelMap.put("noPayPassword", "noPayPassword");
					}
				}
				modelMap.put("focusCount", focusList.size());
				map.clear();
				map.put("fansId", userId);
				List<Fans> fansList = fansService.selectListByCondition(map);
				modelMap.put("fansCount", fansList.size());
				map.clear();
				map.put("userId", userId);
				List<ForumMember> ForumMemberList = forumMemberService.selectListByCondition(map);
				modelMap.put("ForumMemberList", ForumMemberList);
				modelMap.put("userId", sessionUser.getId());
			}
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/myTrade", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 账户信息 */
	@RequestMapping(value = "/accountInformation")
	public ModelAndView accountInformation(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.accountInformation;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/accountInformation";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (null != sessionUser.getId() && !"".equals(sessionUser.getId())) {
				User user = userService.selectByPrimaryKey(sessionUser.getId());
				modelMap.put("user", user);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("relationType", 64);
				map.put("userId", sessionUser.getId());

				map.clear();
				map.put("allCity", 1);
				List<ProvinceCityArea> provinceCityAreaList = provinceCityAreaService.selectListByCondition(map);
				modelMap.put("provinceCityAreaList", provinceCityAreaList);
				map.clear();
				map.put("allProvince", 1);
				List<ProvinceCityArea> provinceList = provinceCityAreaService.selectListByCondition(map);
				modelMap.put("provinceList", provinceList);
			}
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/accountInformation", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	@RequestMapping(value = "/editUser")
	public ModelAndView editUser(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.editUser;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/editUser";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Integer userId = sessionUser.getId();
			User user = userService.selectByPrimaryKey(userId);
			modelMap.put("user", user);
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/editUser", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 绑定用户手机号码 **/
	@RequestMapping(value = "/bindUserPhone")
	public ModelAndView bindUserPhone(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.bindUserPhone;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(request.getSession());
			if (null == user) {
				String jumpUrl = "/bindUserPhone/";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			modelMap.put("userId", user.getId());
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/bindUserPhone", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 资金明细 */
	@RequestMapping(value = "/fundDetails")
	public ModelAndView FundDetails(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.FundDetails;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/fundDetails";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (null != sessionUser.getId() && !"".equals(sessionUser.getId())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", sessionUser.getId());
				List<UserFund> userFundList = userFundService.selectListByCondition(map);
				UserFund userFund = userFundList.get(0);
				modelMap.put("userFund", userFund);
			}
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/fundDetails", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 资金明细 */
	@RequestMapping(value = "/logisticsResults")
	public ModelAndView logisticsResults(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.logisticsResults;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/logisticsResults";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (null != sessionUser.getId() && !"".equals(sessionUser.getId())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", sessionUser.getId());
				List<UserFund> userFundList = userFundService.selectListByCondition(map);
				UserFund userFund = userFundList.get(0);
				modelMap.put("userFund", userFund);
			}
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/logisticsResults", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 实名认证 */
	@RequestMapping(value = "/realName")
	public ModelAndView realName(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.realName;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/realName";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (null != sessionUser.getId() && !"".equals(sessionUser.getId())) {
				User user = userService.selectByPrimaryKey(sessionUser.getId());
				modelMap.put("user", user);
			}
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/realName", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/*
	 * 买家报价
	 * 
	 * @RequestMapping(value="/buyerBid") public ModelAndView buyerBid(ModelMap
	 * modelMap,HttpSession session ,@RequestParam(required=false) String
	 * r,HttpServletRequest request) { String path = LhPage.buyerBid; try{
	 * modelMap = authCheckService.checkWxLogin(request, modelMap); modelMap =
	 * Check.actionLhPagePromoterCheck(session, modelMap, r); User sessionUser
	 * = ActionUtil.checkSession4User(session); if(null == sessionUser){ String
	 * jumpUrl = "/buyerBid"; if(null != r)jumpUrl += "?r="+r; return
	 * Result.userSessionInvalid(modelMap, jumpUrl); } if(null !=
	 * sessionUser.getId() && !"".equals(sessionUser.getId())){ User user =
	 * userService.selectByPrimaryKey(sessionUser.getId()); modelMap.put("user",
	 * user); } modelMap.put("userId", sessionUser.getId()); }catch(Exception
	 * e){ e.printStackTrace(); path = LhPage.error;
	 * logger.error("LH_ERROR_加载用户报价页面出现异常_"+e.getMessage()); } return new
	 * ModelAndView(path, modelMap); }
	 */

	/** 待付款 */
	@RequestMapping(value = "/waitPayMoney")
	public ModelAndView waitPayMoney(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.waitPayMoney;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/waitPayMoney";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (null != sessionUser.getId() && !"".equals(sessionUser.getId())) {
				User user = userService.selectByPrimaryKey(sessionUser.getId());
				modelMap.put("user", user);
			}
			modelMap.put("userId", sessionUser.getId());
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/waitPayMoney", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 待发货 */
	@RequestMapping(value = "/shipping")
	public ModelAndView shipping(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.shipping;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/shipping";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (null != sessionUser.getId() && !"".equals(sessionUser.getId())) {
				User user = userService.selectByPrimaryKey(sessionUser.getId());
				modelMap.put("user", user);
			}
			modelMap.put("userId", sessionUser.getId());
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/shipping", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 待收货 */
	@RequestMapping(value = "/shipped")
	public ModelAndView shipped(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.shipped;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/shipped";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (null != sessionUser.getId() && !"".equals(sessionUser.getId())) {
				User user = userService.selectByPrimaryKey(sessionUser.getId());
				modelMap.put("user", user);
			}
			modelMap.put("userId", sessionUser.getId());
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/shipped", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 待评价 */
	@RequestMapping(value = "/commenting")
	public ModelAndView commenting(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.commenting;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/commenting";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (null != sessionUser.getId() && !"".equals(sessionUser.getId())) {
				User user = userService.selectByPrimaryKey(sessionUser.getId());
				modelMap.put("user", user);
			}
			modelMap.put("userId", sessionUser.getId());
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/commenting", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 退货 */
	@RequestMapping(value = "/returnGoods")
	public ModelAndView returnGoods(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.returnGoods;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/returnGoods";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (null != sessionUser.getId() && !"".equals(sessionUser.getId())) {
				User user = userService.selectByPrimaryKey(sessionUser.getId());
				modelMap.put("user", user);
			}
			modelMap.put("userId", sessionUser.getId());
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/returnGoods", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	/** 找回密码发送验证码 */
	@ResponseBody
	@RequestMapping(value = "/getFindPasswordVerifycode", method = RequestMethod.POST)
	public JSONObject getFindPasswordVerifycode(HttpServletRequest request, HttpSession session, @RequestParam String phone) {
		JSONObject json = new JSONObject();
		try {
			int verifyCodeNum = 1;
			Object verifyCodeNumObj = session.getAttribute("verifyCodeNum");
			if (null != verifyCodeNumObj) {
				verifyCodeNum = (Integer) verifyCodeNumObj;
				if (verifyCodeNum > 20) {
					json.put("status", "max_errors");
					json.put("msg", "请求验证码的次数太多，请直接联系管理员进行修改密码");
					return Result.success(json);
				}
			}
			// phone : 短信接口发送短信验证码
			String verifycode = SendMsgUtil.createRandomVcode();
			System.out.println("reg_verifycode: " + verifycode);
			String mobanId = LhConst.rl_ytx_msg_moban_id;
			String[] params = new String[] { verifycode, "30" };
			// TODO 修改
			// YTX_MSG.send(phone, mobanId, params);
			session.setAttribute("verifycode", verifycode);
			json.put("verifycode", verifycode);
			json.put("status", "success");
			json.put("msg", "验证码已发送到您的手机,请及时查看.!");
			session.setAttribute("verifyCodeNum", ++verifyCodeNum);

		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_找回密码验证码异常_", json);
		}
		return Result.success(json);
	}

	/** 绑定手机号码发送验证码 */
	@ResponseBody
	@RequestMapping(value = "/getBindUserPhoneVerifycode", method = RequestMethod.POST)
	public JSONObject getBindUserPhoneVerifycode(HttpServletRequest request, HttpSession session, @RequestParam String phone) {
		JSONObject json = new JSONObject();
		try {
			int verifyCodeNum = 1;
			Object verifyCodeNumObj = session.getAttribute("verifyCodeNum");
			if (null != verifyCodeNumObj) {
				verifyCodeNum = (Integer) verifyCodeNumObj;
				if (verifyCodeNum > 20) {
					json.put("status", "max_errors");
					json.put("msg", "请求验证码的次数太多，请直接联系管理员进行修改密码");
					return Result.success(json);
				}
			}
			// phone : 短信接口发送短信验证码
			String verifycode = SendMsgUtil.createRandomVcode();
			System.out.println("reg_verifycode: " + verifycode);
			String mobanId = LhConst.rl_ytx_msg_moban_id;
			String[] params = new String[] { verifycode, "30" };
			// TODO 修改
			// YTX_MSG.send(phone, mobanId, params);
			session.setAttribute("verifycode", verifycode);
			session.setAttribute("verifyPhone", phone);
			json.put("verifycode", verifycode);
			json.put("phone", phone);
			json.put("status", "success");
			json.put("msg", "验证码已发送到您的手机,请及时查看.!");
			session.setAttribute("verifyCodeNum", ++verifyCodeNum);

		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_找回密码验证码异常_", json);
		}
		return Result.success(json);
	}

	@ResponseBody
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	public JSONObject getUserList(HttpServletRequest request, HttpServletResponse response) {
		List<User> userList = null;
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			if (null != user && !"".equals(user)) {
				map.put("currentUserId", user.getId());
			}
			userList = userService.selectListByCondition(map);
			Integer total = userService.selectCountByCondition(map);
			json.put("rows", userList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_获取用户信息出现异常_", json);
		}
		return Result.success(json);
	}

	@RequestMapping(value = "/initSafetyLhPage", method = RequestMethod.GET)
	public ModelAndView initSafetyLhPage(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.initSafety;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if (null == sessionUser) {
				String jumpUrl = "/initSafetyLhPage";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			if (!Check.isNotNull(sessionUser.getPhone())) {
				path = LhPage.userCenterShop;
			}
		} catch (Exception e) {
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName() + "/initSafetyLhPage", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/initSafety", method = RequestMethod.POST)
	public JSONObject initSafety(HttpServletRequest request, HttpSession session, @RequestParam String verifyCode, @RequestParam String loginPass, @RequestParam String payPass) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Integer userId = user.getId();

			String verifyPhone = null;
			Object verifyCodeObj = session.getAttribute("verifyCode");
			if (null != verifyCodeObj) {
				String sessionVerifyCode = (String) verifyCodeObj;
				if (sessionVerifyCode.equals(verifyCode)) {
					verifyPhone = (String) session.getAttribute("verifyPhone");
				}
			}
			if (Check.isNull(verifyPhone)) {
				return Result.failure(json, "验证码输入不正确，请重新获取验证码", "code_error");
			}
			Map<String, Object> map = new HashMap<String, Object>();// 验证手机号是否重复
			map.put("phone", verifyPhone);
			int userCount = userService.selectCountByCondition(map);
			if (userCount > 0) {
				return Result.failure(json, "该手机号已经被绑定，请确认手机号是否输入正确", "phone_exist");
			}

			if (loginPass.length() < 5) {
				return Result.failure(json, "支付密码不能小于5个字符", "loginPass_short");
			}
			if (!Check.haveNoSpecialChar(loginPass)) {
				return Result.failure(json, "支付密码不能包含特殊字符", "loginPass_specialChar");
			}
			UserFund userFund = userFundService.selectUserFundByUserId(userId);
			if (null == userFund) {
				return Result.failure(json, "您的账户存在异常，无法修改", "userFund_null");
			}
			if (payPass.length() < 5) {
				return Result.failure(json, "支付密码不能小于5个字符", "payPass_short");
			}
			if (!Check.haveNoSpecialChar(payPass)) {
				return Result.failure(json, "支付密码不能包含特殊字符", "payPass_specialChar");
			}
			User newUser = new User();
			newUser.setId(userId);
			newUser.setPassword(Md5Util.encrypt(loginPass));
			newUser.setPhone(verifyPhone);
			userService.updateByPrimaryKeySelective(newUser);

			userFund.setPayPassword(Md5Util.encrypt(payPass));
			userFundService.updatePayPasswordById(userFund);

		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_设置用户基本安全数据（手机，登陆密码，支付密码）出现异常_", json);
		}
		return Result.success(json);
	}

	/**
	 * 修改用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
	public JSONObject addOrUpdateUser(@ModelAttribute User user, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			user.setMainStatus(1);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Integer sessionUserId = sessionUser.getId();
			Integer antiqueCityId = user.getAntiqueCityId();
			Integer userId = user.getId();
			String username = user.getUsername();
			Date date = new Date();
			if (null == user.getId()) {// 添加
				if (null == user.getCreatedAt()) {
					user.setCreatedAt(date);
				}
				user.setCreatedBy(username);
				String userPassword = Md5Util.encrypt(user.getPassword());
				user.setPassword(userPassword);
				user.setRoleId(1);
				userService.insert(user);
				if (null != antiqueCityId && !"".equals(antiqueCityId)) {
					UserRelation userRelation = new UserRelation();
					userRelation.setRelationId(antiqueCityId);
					userRelation.setUserId(userId);
					userRelation.setRelationType(64);
					userRelation.setCreatedBy(username);
					userRelation.setCreatedAt(date);
					userRelationService.insert(userRelation);
				}
				json.put("status", "success");
				json.put("msg", "操作成功");
			} else {// 修改
					// if(1 != user.getId() && !"1".equals(user.getId())){
				if (null == userId) {
					return Result.failure(json, "用户为空", "userId_null");
				}
				if (sessionUserId.intValue() != userId.intValue()) {
					return Result.failure(json, "您没有权限修改该用户数据", "authority_error");
				}

				if (null == user.getUpdatedAt()) {
					user.setUpdatedAt(date);
				}
				if (null != user.getPassword() && !"".equals(user.getPassword())) {
					String userPassword = Md5Util.encrypt(user.getPassword());
					user.setPassword(userPassword);
				}
				user.setUpdatedBy(username);
				userService.updateByPrimaryKeySelective(user);
				if (null != antiqueCityId && !"".equals(antiqueCityId)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("relationType", 64);
					map.put("userId", userId);
					List<UserRelation> db_userRelationList = userRelationService.selectListByCondition(map);
					if (db_userRelationList.size() > 0) {
						UserRelation db_userRelation = db_userRelationList.get(0);
						db_userRelation.setRelationId(antiqueCityId);
						db_userRelation.setUpdatedAt(date);
						db_userRelation.setUpdatedBy(username);
						userRelationService.updateByPrimaryKeySelective(db_userRelation);
					} else {
						UserRelation userRelation = new UserRelation();
						userRelation.setRelationId(antiqueCityId);
						userRelation.setUserId(userId);
						userRelation.setRelationType(64);
						userRelation.setCreatedAt(date);
						userRelation.setCreatedBy(username);
						userRelationService.insert(userRelation);
					}
				}
				if (null != user.getPassword() && !"".equals(user.getPassword())) {
					json.put("msg", "修改成功,请重新登录,3秒后将跳转到登录页面.");
				}
				Result.success(json, null, null);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_新增修改用户出现异常_", json);
		}
		return Result.success(json);
	}

	/**
	 * 上传头像
	 * 
	 * @param filePortrait
	 * @return
	 **/
	@ResponseBody
	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
	public JSONObject uploadAvatar(HttpServletRequest request, @RequestParam MultipartFile filePortrait) {
		JSONObject json = new JSONObject();
		String avatar = "";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String userportrait = formatter.format(new Date()) + "_userPortrait_";
			avatar = UploadUtil.uploadFile(request, filePortrait, userportrait, "/file/userPortrait/");// 执行上传图片
			// 更新用户
			json.put("filePath", avatar);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_上传头像出现异常_", json);
		}
		return Result.success(json);
	}

	/**
	 * 修改用户密码
	 **/
	@ResponseBody
	@RequestMapping(value = "/updateUserPassword")
	public JSONObject updateUserInfo(HttpServletRequest request, @RequestParam(required = false, value = "newPassword") String newPassword,
			@RequestParam(required = false, value = "oldPassword") String oldPassword) {
		JSONObject json = new JSONObject();
		try {
			if (null != oldPassword && !"".equals(oldPassword)) {
				User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
				if (null == sessionUser) {
					return Result.userSessionInvalid(json);// 返回session过期的json提示
				}
				if (1 != sessionUser.getId() && !"1".equals(sessionUser.getId())) {
					String password = Md5Util.encrypt(oldPassword.toString());
					User user = userService.selectByPrimaryKey(sessionUser.getId());
					if (password.equals(user.getPassword())) {
						user.setPassword(Md5Util.encrypt(newPassword.toString()));
						user.setId(sessionUser.getId());
						userService.updateByPrimaryKeySelective(user);
						json.put("status", "success");
						json.put("msg", "修改成功,请重新登录.");
						request.getSession().invalidate();
					} else {
						json.put("msg", "当前密码不正确,如果忘记当前密码,请到修改密码重新设置密码");
					}
				} else {
					json.put("status", "forbidden");
					json.put("msg", "默认管理员不能进行密码修改");
				}
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_修改用户密码出现异常_", json);
		}
		return Result.success(json);
	}

	@ResponseBody
	@RequestMapping(value = "/getOrderCount")
	public JSONObject getOrderCount(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Integer userId = user.getId();
			Map<String, Object> map = new HashMap<String, Object>();
			/*
			 * map.put("offerStatus", 1); map.put("userId", userId); int
			 * goodsOffersCount =
			 * goodsOffersService.selectCountByCondition(map);//买家报价
			 * map.clear();
			 */
			map.put("orderStatus", 1);
			map.put("payStatus", 1);
			map.put("orderGoodsStatus", 1);
			map.put("mainStatus", 1);
			map.put("userId", userId);
			map.put("expressStateNotOver", 1);
			int waitPayMoneyCount = orderInfoService.selectCountByCondition(map);// 待付款
			map.clear();
			map.put("orderStatus", 1);
			map.put("shippingStatus", 2);
			map.put("payStatus", 3);
			map.put("orderGoodsStatus", 1);
			map.put("userId", userId);
			map.put("mainStatus", 1);
			map.put("expressStateNotOver", 1);
			int shipedCount = orderInfoService.selectCountByCondition(map);// 待收货
			map.clear();
			map.put("orderStatus", 1);
			map.put("shippingStatus", 3);
			map.put("payStatus", 3);
			map.put("overComment", 1);
			map.put("orderGoodsStatus", 2);
			map.put("userId", user.getId());
			map.put("mainStatus", 1);
			// map.put("commentUserId", userId);
			int commentingCount = orderInfoService.selectCountByCondition(map);// 待评价
			map.clear();
			// map.put("payStatus", 3);
			map.put("orderStatus", 6);
			map.put("orderGoodsStatus", 5);
			map.put("sellerOrbuyer", userId);
			map.put("mainStatus", 1);
			int returnGoodsCount = orderInfoService.selectCountByCondition(map);// 退货
			map.clear();
			map.put("orderStatus", 1);
			map.put("payStatus", 3);
			map.put("shippingStatus", 1);
			map.put("shopUserId", userId);
			map.put("orderGoodsStatus", 1);
			map.put("mainStatus", 1);
			map.put("expressStateNotOver", 1);
			int shippingCount = orderInfoService.selectCountByCondition(map);// 待发货
			json.put("returnGoodsCount", returnGoodsCount);
			// json.put("goodsOffersCount", goodsOffersCount);
			json.put("shippingCount", shippingCount);
			json.put("shipedCount", shipedCount);
			json.put("commentingCount", commentingCount);
			json.put("waitPayMoneyCount", waitPayMoneyCount);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_获取订单数量异常_", json);
		}
		return Result.success(json);
	}
	@RequestMapping(value="/aboutUs")
	public ModelAndView  aboutUs(ModelMap modelMap,HttpServletRequest request){
		String path = LhPage.aboutUs;
		try{
			JSONObject json = new JSONObject();
			User sessionuser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionuser){
				return Result.userSessionInvalid(modelMap,LhPage.login);
				}//没有登陆的session则跳转到登录
			modelMap.put("paramJson", json);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, "LH_ERROR-User-PAGE-/userHome-加载用户中心出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	@RequestMapping(value="/changPhone")
	public ModelAndView  changPhone(ModelMap modelMap,HttpServletRequest request){
		String path = LhPage.changPhone;
		try{
			JSONObject json = new JSONObject();
			User sessionuser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionuser){
				return Result.userSessionInvalid(modelMap,LhPage.login);
				}//没有登陆的session则跳转到登录
			modelMap.put("paramJson", json);
			modelMap.put("user", sessionuser);
			User user = userService.selectByPrimaryKey(sessionuser.getId());
			String userPhone = user.getPhone();
			if(null == userPhone){
				modelMap.put("userPhone", "userPhoneIsNull");
			}else{
				modelMap.put("userPhone", subPhoneNum(userPhone));
			}
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, "LH_ERROR-User-PAGE-/userHome-加载用户中心出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	private String subPhoneNum(String mobile) {
		String str = "";
        for (int i = 0; i < mobile.length(); i++) {
            if (i == mobile.length()-11) {
                str += mobile.charAt(i);
            } else if(i == mobile.length()-10) {
             str += mobile.charAt(i);
            }else if(i == mobile.length()-9) {
             str += mobile.charAt(i);
            }else if(i == mobile.length()-3) {
             str += mobile.charAt(i);
            }else if(i == mobile.length()-2) {
             str += mobile.charAt(i);
            }else if(i == mobile.length()-1) {
             str += mobile.charAt(i);
            }else {
              str += "*";
            }
        }
		return str;
	}
	/** 验证手机号码是否已经注册 */
	@ResponseBody
	@RequestMapping(value = "/checkUserPhoneExist")
	public JSONObject checkUserPhoneExist(HttpServletRequest request, @RequestParam String phone) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			int userCount = userService.selectCountByCondition(map);
			if (userCount > 0) {
				return Result.success(json, "手机号码已占用", BaseTip.code_exist_yes);
			} else {
				return Result.success(json, BaseTip.msg_exist_no, BaseTip.code_exist_no);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/user/checkPhoneExist", json);
		}
		return Result.success(json);
	} 
	
	@ResponseBody
	@RequestMapping(value = "/getPhoneVerifycode", method = RequestMethod.POST)
	public static JSONObject getPhoneVerifycode(HttpServletRequest request, HttpSession session,
			@RequestParam String phone) {
		JSONObject json = new JSONObject();
		try {
			int verifyCodeNum = 1;
			Object verifyCodeNumObj = session.getAttribute("verifyCodeNum");
			if (null != verifyCodeNumObj) {
				verifyCodeNum = (Integer) verifyCodeNumObj;
				if (verifyCodeNum > 20) {
					json.put("status", "max_errors");
					json.put("msg", "请求验证码的次数太多，请直接联系管理员进行修改密码！");
					return Result.success(json,"请求验证码的次数太多，请直接联系管理员进行修改密码！");
				}
			}
			// phone : 短信接口发送短信验证码
			String verifycode = SendMsgUtil.createRandomVcode();
			//System.out.println("reg_verifycode: " + verifycode);
			String mobanId = LhConst.rl_ytx_msg_moban_id;
			String[] params = new String[] {verifycode, "30" };

			YtxMessage ytx = new YtxMessage();
			ytx.setPhones(phone);
			ytx.setMobanId(mobanId);
			ytx.setParams(params);
			ytx.setIp(LhConst.rl_ytx_ip);
			//ytx.setPort(Const.rl_ytx_ip_port);
			ytx.setPort(LhConst.rl_ytx_port);
			ytx.setSid(LhConst.rl_ytx_sid);
			ytx.setAppId(LhConst.rl_ytx_appId);
			ytx.setAuthToken(LhConst.rl_ytx_authToken);
			//TODO 发送短信  
			YTX_MSG.send(ytx);
			session.setAttribute("verifycode", verifycode);
			json.put("status", "success");
			session.setAttribute("verifyCodeNum", ++verifyCodeNum);
			
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_获取验证码异常_", json);
		}
		return Result.success(json,"验证码已发送到您的手机,请及时查看！");
	}
	/**
	 * 校验短信验证码
	 * */
	@ResponseBody
	@RequestMapping(value = "/checkVerifyCodeNum", method = RequestMethod.POST)
	public JSONObject checkverifyCodeNum(HttpServletRequest request,HttpSession session,
			@RequestParam String userVerifyCode) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			String verifycode =  String.valueOf(session.getAttribute("verifycode"));	
			if(userVerifyCode.equals(verifycode)){
				json.put("checkedSuccess", "匹配成功");
			}else{
				return Result.failure(json, "验证码输入有误", "code error");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_设置支付密码出现异常_", json);
		}
		return Result.success(json,"匹配成功");
	}
	@ResponseBody
	@RequestMapping(value = "/updateUserPhone")
	public JSONObject updateUserPhone(HttpServletRequest request, 
			@RequestParam String phone) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == sessionUser) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
//			BaseUser user = userService.selectByPrimaryKey(sessionUser.getId());
			sessionUser.setPhone(phone);
			userService.updateByPrimaryKey(sessionUser);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/user/checkPhoneExist", json);
		}
		return Result.success(json,"修改成功!");
	}

	
	@RequestMapping(value="/myWallet")
	public ModelAndView  myWallet(ModelMap modelMap,HttpServletRequest request){
		String path = LhPage.myWallet;
		try{
			JSONObject json = new JSONObject();
			User sessionuser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionuser){
				return Result.userSessionInvalid(modelMap,LhPage.login);
			}//没有登陆的session则跳转到登录
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", sessionuser.getId());
			User user=userService.selectByCondition(map);
			modelMap.put("user", user);
			json.put("user", user);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, "LH_ERROR-User-PAGE-/userHome-加载用户中心出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	
	@RequestMapping(value="/addressSelect")
	public ModelAndView addressSelect(ModelMap modelMap,HttpServletRequest request){
		String path = LhPage.indexAddressSelect;
		try{
//			JSONObject json = new JSONObject();
//			User sessionuser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
//			if(null == sessionuser){
//				return Result.userSessionInvalid(modelMap,LhPage.login);
//			}//没有登陆的session则跳转到登录
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, "LH_ERROR-User-PAGE-/userHome-加载用户中心出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
}

package com.lhfeiyu.action.front.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.po.domain.Withdraw;
import com.lhfeiyu.service.domain.UserFundService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.service.domain.WithdrawService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;

@Controller
public class WithdrawAction {
	@Autowired
	private WithdrawService withdrawService;
	@Autowired
	private UserFundService userFundService;
	@Autowired
	private UserService userService;
	private static Logger logger = Logger.getLogger("R");
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateWithdraw", method=RequestMethod.POST)
	public JSONObject addOrUpdateWithdraw(@ModelAttribute Withdraw withdraw,HttpServletRequest request,
			@RequestParam(required=false) String payPassword){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			if(null == withdraw.getId()){//添加
				if(null != payPassword && !"".equals(payPassword)){
					int userId = user.getId();
					User db_user = userService.selectByPrimaryKey(userId);
					if(null == db_user.getPhone() || "".equals(db_user.getPhone())){
						json.put("status", "failure");
						json.put("noPhone", "noPhone");
						json.put("msg", "请先绑定手机号码");
						return Result.success(json);
					}
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("userId", user.getId());
					List<UserFund> userFundList = userFundService.selectListByCondition(map);
					if(userFundList.size() > 0){
						UserFund userFund = userFundList.get(0);
						if(null == userFund.getPayPassword() || "".equals(userFund.getPayPassword()) ){
							json.put("status", "failure");
							json.put("noPayPassword", "noPayPassword");
							json.put("msg", "请先设置支付密码");
							return Result.success(json);
						}
						String payPasswd = Md5Util.encrypt(payPassword);
						if(payPasswd.equals(userFund.getPayPassword())){
							BigDecimal applyMoney = withdraw.getApplyMoney();//提现金额
							BigDecimal fee = withdraw.getFee();
							if(null != applyMoney){//先判断是否为null
								if(applyMoney.doubleValue() < userFund.getAvaliableMoney().doubleValue()){
									if(applyMoney.doubleValue() > 0 && applyMoney.doubleValue() < 1000000){//判读是否大于0
										String serial = CommonGenerator.getSerialByDate("wd");
										BigDecimal applyRealMoney = applyMoney.subtract(applyMoney.multiply(fee));
										withdraw.setApplyMoney(applyMoney);
										withdraw.setFee(applyMoney.multiply(fee));
										withdraw.setApplyRealMoney(applyRealMoney);
										withdraw.setApplyDate(new Date());
										withdraw.setSerial(serial);
										withdraw.setUserId(user.getId());
										withdraw.setMainStatus(1);
										withdraw.setDealStatus(1);
										withdraw.setCreatedAt(new Date());
										withdraw.setCreatedBy(user.getUsername());
										withdrawService.insert(withdraw);
										json.put("status", "success");
										json.put("msg", "操作成功");
										return Result.success(json);
									}else{
										json.put("status", "failure");
									    json.put("msg", "提现金额在1~100万之间");
									    return Result.success(json);
									}
								}else{
									json.put("status", "failure");
								    json.put("msg", "提现金额必须小于当前可用余额。");
									return Result.success(json);
								}
							}else{
								json.put("status", "failure");
							    json.put("msg", "提现金额必须填写");
								return Result.success(json);
							}
						}else{
							json.put("status","failure");
							json.put("msg", "当前支付密码不正确,如果忘记支付密码,请点击忘记支付密码");
							return Result.success(json);
						}
					}
				}
			}/*else{//修改
				withdraw.setUpdatedAt(new Date());
				withdraw.setDealDate(new Date());
				withdraw.setDealUser(user.getUsername());
				withdraw.setUpdatedBy(user.getUsername());
				withdrawService.updateByPrimaryKeySelective(withdraw);
			}*/
			/*json.put("status", "success");
			json.put("id",withdraw.getId());
			json.put("msg", "操作成功");*/
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改订单信息出现异常_", json);
		}
		return Result.success(json);
	}
	
}

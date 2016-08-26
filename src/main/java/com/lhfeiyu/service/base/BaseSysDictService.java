package com.lhfeiyu.service.base;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.dao.domain.SysAccountLogMapper;
import com.lhfeiyu.dao.domain.SysDictMapper;
import com.lhfeiyu.po.domain.SysAccountLog;
import com.lhfeiyu.po.domain.SysDict;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：官方数据字典 SysDict <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseSysDictService")
public class BaseSysDictService extends CommonService<SysDict> {

	@Autowired
	SysDictMapper mapper;
	@Autowired
	SysAccountLogMapper sysAccountLogMapper;


	public List<SysDict> selectList(Map<String, Object> map) {
		return mapper.selectList(map);
	}
	
	public double getProfessionDealFeeScale(){
		SysDict dict = mapper.selectByPrimaryKey(3);
		if(null != dict && Check.isNotNull(dict.getDictValue())){
			double scale = Double.valueOf(dict.getDictValue());
			if(scale > 0 && scale < 1){
				return scale;
			}
		}
		return 0;
	}
	
	public double getQuickDealFeeMoney(){
		SysDict dict = mapper.selectByPrimaryKey(4);
		if(null != dict && Check.isNotNull(dict.getDictValue())){
			double money = Double.valueOf(dict.getDictValue());
			if(money > 0){
				return money;
			}
		}
		return 0;
	}
	
	
	/**
	 * @param  totalMoney   收取的费用
	 * @param  inOrOut    1.收入2.支出
	 * @return json
	 */
	public JSONObject addSysAccountLog(BigDecimal Fee, Integer inOrOut,Integer typeId,JSONObject json){
		Date date = new Date();
		SysAccountLog  sysAccountLog = new SysAccountLog();
		String serial = CommonGenerator.getSerialByDate("al");
		sysAccountLog.setSerial(serial);
		sysAccountLog.setTypeId(typeId);
		sysAccountLog.setMoney(Fee);
		sysAccountLog.setInOrOut(inOrOut);
		sysAccountLog.setPayTime(date);
		sysAccountLog.setCreatedAt(date);
		sysAccountLog.setCreatedBy("SYS");
		sysAccountLogMapper.insert(sysAccountLog);
		return json;
	}
	/**
	 * 收入
	 * @param Fee 费用
	 * @param json
	 * @return
	 */
	public JSONObject incomeMoney(BigDecimal Fee,Integer typeId, JSONObject json){
		json = updateSysMoney("incomeMoney", Fee, json);
		json = addSysAccountLog(Fee, 1,typeId, json);
		return json;
	}
	
	/**
	 * 支出
	 * @param Fee 费用
	 * @param json
	 * @return
	 */
	public JSONObject outcomeMoney(BigDecimal Fee,Integer typeId, JSONObject json){
		json = updateSysMoney("outcomeMoney", Fee, json);
		json = addSysAccountLog(Fee, 2,typeId, json);
		return json;
	}
	
	/**
	 * 私有方法--更改平台的资金
	 * @param optType 收入还是支出
	 * @param Fee  费用
	 * @param json
	 * @return json
	 */
	private JSONObject updateSysMoney(String optType,BigDecimal Fee,JSONObject json){
		SysDict sysMoney = new SysDict();
		if(optType.equals("incomeMoney")){
			SysDict sysTotalMoney = mapper.selectByPrimaryKey(12);//查询平台总的收入
			sysMoney.setId(12);
			if(null != sysTotalMoney.getDictValue()){
				String totalMoney = new BigDecimal(sysTotalMoney.getDictValue()).add(Fee).toString();
				sysMoney.setDictValue(totalMoney);
			}else{
				sysMoney.setDictValue(Fee.toString());
			}
		}else if(optType.equals("outcomeMoney")){
			SysDict sysTotalMoney = mapper.selectByPrimaryKey(13);//查询平台总的支出
			sysMoney.setId(13);
			if(null != sysTotalMoney.getDictValue()){
				String totalMoney = new BigDecimal(sysTotalMoney.getDictValue()).add(Fee).toString();
				sysMoney.setDictValue(totalMoney);
			}else{
				sysMoney.setDictValue(Fee.toString());
			}
		}
		mapper.updateByPrimaryKeySelective(sysMoney);//更新资金
		return json;
	}
	
}


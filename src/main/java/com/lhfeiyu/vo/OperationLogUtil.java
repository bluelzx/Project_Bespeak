package com.lhfeiyu.vo;

import java.util.Calendar;
import java.util.List;

import com.lhfeiyu.po.domain.OperationLog;

public class OperationLogUtil {

	public static List<OperationLog> compareNow(List<OperationLog> logList){
		if(null == logList || logList.size() <= 0)return null;
		Calendar cd = Calendar.getInstance();
		Calendar ld = Calendar.getInstance();
		OperationLog log = null;
		for(int i = 0;i<logList.size();i++){
			log = logList.get(i);
			ld.setTime(log.getCreatedAt());
			/*int monthDiff = ld.get(Calendar.MONTH) - cd.get(Calendar.MONTH);
			int yearDiff = ld.get(Calendar.YEAR) - cd.get(Calendar.YEAR);
			int months = 12 * yearDiff + monthDiff;
			int years = months/12;
			//int yearDiff = cd.get(Calendar.YEAR) - ld.get(Calendar.YEAR);
			if(years > 0){//年
				log.setCompareNow(yearDiff+"年前");
			}else{
				//int monthDiff = cd.get(Calendar.MONTH) - ld.get(Calendar.MONTH);
				
				if(monthDiff > 0){//月
					long between_days = (cd.getTimeInMillis() - ld.getTimeInMillis())/(1000*3600*24);
					if(between_days<28){
						log.setCompareNow(between_days+"天前");
					}else{
						log.setCompareNow(monthDiff+"月前");
					}
				}else{
					int dayDiff = cd.get(Calendar.DAY_OF_MONTH) - ld.get(Calendar.DAY_OF_MONTH);
					if(dayDiff > 0){//日
						log.setCompareNow(dayDiff+"天前");
					}else{
						int hourDiff = cd.get(Calendar.HOUR_OF_DAY) - ld.get(Calendar.HOUR_OF_DAY);
						if(hourDiff > 0){//小时
							log.setCompareNow(dayDiff+"小时前");
						}else{
							int minuteDiff = cd.get(Calendar.MINUTE) - ld.get(Calendar.MINUTE);
							if(minuteDiff >= 0){//分钟
								if(minuteDiff == 0)minuteDiff = 1;
								log.setCompareNow(minuteDiff+"分钟前");
							}
						}
					}
				}
			}*/
			long between_minutes = (cd.getTimeInMillis() - ld.getTimeInMillis())/(1000*60);
			long between_hours = between_minutes/60;
			long between_days = between_hours/24;
			if(between_minutes < 60){
				if(between_minutes<=0){
					between_minutes = 1;
				}
				log.setCompareNow(between_minutes+"分钟前");
			}else if(between_hours < 24){
				log.setCompareNow(between_hours+"小时前");
			}else if(between_days < 28){
				log.setCompareNow(between_days+"天前");
			}else{
				int yearDiff = cd.get(Calendar.YEAR) - ld.get(Calendar.YEAR);
				int monthDiff = cd.get(Calendar.MONTH) - ld.get(Calendar.MONTH);
				if(monthDiff > 0){
					ld.set(Calendar.MONTH, cd.get(Calendar.MONTH)+monthDiff);
					if(cd.after(ld)){
						//monthDiff = monthDiff - 1;
						if(monthDiff<=0){
							log.setCompareNow(between_days+"天前");
						}else{
							log.setCompareNow(monthDiff+"月前");
						}
					}
				}
				int months = 12 * yearDiff + monthDiff;
				int years = months/12;
				if(years > 0){//年
					log.setCompareNow(yearDiff+"年前");
				}else{
					log.setCompareNow(monthDiff+"月前");
				}
			}
		}
		return logList;
	}
}

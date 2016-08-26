package com.lhfeiyu.vo;

import java.util.Calendar;
import java.util.List;

import com.lhfeiyu.po.domain.ForumArticle;

public class ForumArticleUtil {

	public static List<ForumArticle> compareNow(List<ForumArticle> forumArticleList){
		if(null == forumArticleList || forumArticleList.size() <= 0)return null;
		Calendar cd = Calendar.getInstance();
		Calendar ld = Calendar.getInstance();
		ForumArticle forumArticle = null;
		for(int i = 0;i<forumArticleList.size();i++){
			forumArticle = forumArticleList.get(i);
			ld.setTime(forumArticle.getCreatedAt());
			long between_minutes = (cd.getTimeInMillis() - ld.getTimeInMillis())/(1000*60);
			long between_hours = between_minutes/60;
			long between_days = between_hours/24;
			if(between_minutes < 60){
				if(between_minutes<=0){
					between_minutes = 1;
				}
				forumArticle.setCompareNow(between_minutes+"分钟前");
			}else if(between_hours < 24){
				forumArticle.setCompareNow(between_hours+"小时前");
			}else if(between_days < 28){
				forumArticle.setCompareNow(between_days+"天前");
			}else{
				int yearDiff = ld.get(Calendar.YEAR) - cd.get(Calendar.YEAR);
				int monthDiff = ld.get(Calendar.MONTH) - cd.get(Calendar.MONTH);
				if(monthDiff > 0){
					cd.set(Calendar.MONTH, cd.get(Calendar.MONTH)+monthDiff);
					if(cd.after(ld)){
						monthDiff = monthDiff - 1;
						if(monthDiff<=0){
							forumArticle.setCompareNow(between_days+"天前");
						}
					}
					forumArticle.setCompareNow(monthDiff+"月前");
				}
				int months = 12 * yearDiff + monthDiff;
				int years = months/12;
				if(years > 0){//年
					forumArticle.setCompareNow(yearDiff+"年前");
				}
			}
		}
		return forumArticleList;
	}
}

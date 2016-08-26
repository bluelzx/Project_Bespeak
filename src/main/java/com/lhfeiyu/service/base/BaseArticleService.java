package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ArticleMapper;
import com.lhfeiyu.po.domain.Article;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：文章 Article <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseArticleService")
public class BaseArticleService extends CommonService<Article> {

	@Autowired
	ArticleMapper articleMapper;
	
	public JSONObject getArticleList(JSONObject json, Map<String, Object> map) {
		List<Article> articleList = articleMapper.selectListByCondition(map);
		Integer total = articleMapper.selectCountByCondition(map);
		return Result.gridData(articleList, total, json);
	}
	
	/**
	 * 新增或修改文章
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param article 文章对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateArticle(JSONObject json, Article article, String username){
		String content = article.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}
		if(null == article.getId()){//添加
			return addArticle(json, article, username);
		}else{//修改
			return updateArticle(json, article, username);
		}
	}
	
	/**
	 * 新增文章（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param article 文章对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addArticle(JSONObject json, Article article, String username){
		Date date = new Date();
		article.setId(null);
		article.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_article));
		article.setHits(0);
		article.setScans(0);
		article.setMainStatus(1);
		article.setCreatedBy(username);
		article.setCreatedAt(date);
		articleMapper.insert(article);
		json.put("articleId", article.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改文章（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param article 文章对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateArticle(JSONObject json, Article article, String username){
		Date date = new Date();
		Integer articleId = article.getId();
		if(null == articleId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Article dbArticle = articleMapper.selectByPrimaryKey(articleId);
		if(null == dbArticle){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		article.setUpdatedBy(username);
		article.setUpdatedAt(date);
		articleMapper.updateByPrimaryKeySelective(article);
		return Result.success(json);
	}
	
}

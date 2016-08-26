package com.lhfeiyu.service.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhfeiyu.dao.domain.ForumArticleCollectMapper;
import com.lhfeiyu.service.base.BaseForumArticleCollectService;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：论坛文章收藏 ForumArticleCollect <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2015-7-4 10:08:21 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service
public class ForumArticleCollectService extends BaseForumArticleCollectService{
	
	@Autowired
	ForumArticleCollectMapper mapper;
}

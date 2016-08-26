package com.lhfeiyu.action.front.sys;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.service.domain.AuthCheckService;

@Controller
public class IndexAction {
	//@Autowired
	//private PictureService pictureService;
	/*@Autowired
	private ArticleService articleService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ForumService forumService;
	@Autowired
	private AuctionProfessionService apService;*/
	//@Autowired
	//private GoodsService goodsService;
	@Autowired
	private AuthCheckService authCheckService;

	private static Logger logger = Logger.getLogger("R");
	
	/** 主页面  */
	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)//,"/index"
	public ModelAndView index(ModelMap modelMap,HttpServletRequest request
			,@RequestParam(required=false) String path
			,@RequestParam(required=false) String r){
		try{
			JSONObject json = new JSONObject();
			//modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			//加载首页Banner图
			//Map<String,Object> map = new HashMap<String,Object>();
			//map.put("typeId", 88);//88:首页Banner图
			//map.put("orderBy", "moduleId");
			//map.put("ascOrdesc", "asc");
			//List<Picture> bannerList = pictureService.selectListByCondition(map);
			//modelMap.put("bannerList", bannerList);
			//加载推荐微拍
			
			/*//TODO tempHide
			 * //加载活动资讯
			map.clear();
			map.put("typeId", 41);//41:展会活动
			//map.put("orderBy", "moduleId");
			//map.put("ascOrdesc", "desc");
			map.put("isShowIndex", 2);
			map.put("count", 2);
			map.put("start", 0);
			List<Article> articleList = articleService.selectListByCondition(map);
			modelMap.put("articleList", articleList);
			//加载推荐店铺
			map.clear();
			map.put("orderBy", "credit_margin DESC,u.is_real_auth DESC,A.created_at");
			map.put("ascOrdesc", "desc");
			map.put("creditMarginISNOTNULLAndIsRealAuth", 1);
			map.put("count", 6);
			map.put("start", 0);
			List<Shop> shopList = shopService.selectListByCondition(map);
			modelMap.put("shopList", shopList);
			//加载推荐论坛
			map.clear();
			map.put("orderBy", "visit_num");
			map.put("ascOrdesc", "DESC");
			map.put("count", 6);
			map.put("start", 0);
			List<Forum> forumList = forumService.selectListByCondition(map);
			modelMap.put("forumList", forumList);
			//加载推荐专场
			map.clear();
			map.put("orderBy", "sequence");
			map.put("ascOrdesc", "asc");
			map.put("notOver", 1);
			List<AuctionProfession> apList = apService.selectListByCondition(map);
			if(apList.size() > 0){
				for(AuctionProfession ap:apList){
					if(null != ap.getPicPath()){
						String picPaths[] = ap.getPicPath().split(",");
						if(picPaths.length > 0){
							List<String> picPathList = new ArrayList<String>(picPaths.length);
							for(int i = 0;i<picPaths.length;i++){
								picPathList.add(picPaths[i]);
							}
							ap.setApPicPaths(picPathList);
						}
					}
				}
				modelMap.put("apList", apList);
			}*/
			//加载推荐藏品
			/*map.clear();
			map.put("orderBy", "sequence");
			map.put("ascOrdesc", "asc");
			List<Goods> goodsList = goodsService.selectListByCondition(map);
			modelMap.put("goodsList", goodsList);*/
		}catch(Exception e){
			e.printStackTrace();
			logger.error("LH_ERROR_首页加载异常_"+e.getMessage());
		}
		return new ModelAndView(LhPage.frontIndex,modelMap);
	}
	
}
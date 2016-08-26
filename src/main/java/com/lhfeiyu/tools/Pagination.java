package com.lhfeiyu.tools;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lhfeiyu.util.RegexUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：分页 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.tools.Pagination <p>
 */
public class Pagination {
	
	public static int defaultLhPage = 1;
	public static int defaultRows = 10;
	
	/**
	 * @param map
	 * @param request 用于分页
	 * @return map
	 */
	public static Map<String, Object> getOrderByAndLhPage(HashMap<String, Object> map,HttpServletRequest request){
		String orderBy = request.getParameter("orderBy");
		String ascOrdesc = request.getParameter("ascOrdesc");
		return getOrderByAndLhPage(orderBy, ascOrdesc, map,request);
	}
	
	/**
	 * @param map
	 * @param request 用于分页
	 * @return map
	 */
	public static Map<String, Object> getOrderByAndLhPage(String orderBy, String ascOrdesc, Map<String, Object> map, HttpServletRequest request){
		if(Check.isNotNull(orderBy) && orderBy.matches(RegexUtil.non_special_char_regexp)){
			map.put("orderBy", orderBy);
		}else{
			map.put("orderBy", "id");
		}
		if(Check.isNotNull(ascOrdesc) && ascOrdesc.matches(RegexUtil.non_special_char_regexp)){
			map.put("ascOrdesc", ascOrdesc);
		}else{
			map.put("ascOrdesc", "DESC");
		}
		map = getLhPageParam(request,map);
		return map;
	}
	
	/**
	 * 描述：公共方法：分页.</P>
	 * 传入request，返回包含分页起始数和每页条数的MAP
	 * @param request
	 * @param map
	 * @return map(包含以start,count为key值的两条键值对)
	 */
	public static Map<String, Object> getLhPageParam(HttpServletRequest request, Map<String, Object> map){
		if(null == map){
			 map = new HashMap<String, Object>();
		}
		if(null == request){
			return getLhPageParams(defaultLhPage, defaultRows, map);
		}
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
		Integer page = defaultLhPage;
		Integer rows = defaultRows;
		if(Check.isNotNull(pageStr)){
			page = Integer.parseInt(pageStr);
		}
		if(Check.isNotNull(rowsStr)){
			rows = Integer.parseInt(rowsStr);
		}
		return getLhPageParams(page, rows, map);
	}
	
	/**
	 * 描述：公共方法：分页.</P>
	 * 传入页数和每页记录数，返回包含分页起始数和每页条数的MAP
	 * @param page
	 * @param rows
	 * @return map(包含以start,count为key值的两条键值对)
	 */
	public static Map<String, Object> getLhPageParams(Integer page,Integer rows){
		Map<String,Object> map = new HashMap<String,Object>(4);
		if(Check.isNullZero(page))page = 1;	//如果page为空，则默认为1
		if(Check.isNullZero(rows) || rows > 100)rows = 10;	//如果rows为空或大于100，则默认为10
		map.put("start", (page-1)*rows);//组装参数 - 分页起始值
		map.put("count", rows);			//组装参数 - 每页条数
		return map;
	}
	
	/**
	 * 描述：公共方法：分页.</P>
	 * 传入页数和每页记录数和Map，返回包含分页起始数和每页条数的MAP
	 * @param page
	 * @param rows
	 * @param map
	 * @return map(包含以start,count为key值的两条键值对)
	 */
	public static Map<String, Object> getLhPageParams(Integer page,Integer rows, Map<String, Object> map){
		if(null == map){map = new HashMap<String,Object>(4);}
		if(Check.isNullZero(page))page = 1;	//如果page为空，则默认为1
		if(Check.isNullZero(rows) || rows > 100)rows = 10;	//如果rows为空或大于100，则默认为10
		map.put("start", (page-1)*rows);//组装参数 - 分页起始值
		map.put("count", rows);			//组装参数 - 每页条数
		return map;
	}
	
}